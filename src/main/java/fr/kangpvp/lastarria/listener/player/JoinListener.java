package fr.kangpvp.lastarria.listener.player;

import com.mongodb.client.MongoCollection;
import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.GamePlayer;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import fr.kangpvp.lastarria.utils.database.MongoConnection;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        new GamePlayer(player.getName());

        DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();
        MongoConnection mongoConnection = Main.INSTANCE.getMongoManager().getMongoConnection();
        MongoCollection<Document> collection = mongoConnection.getCollection("PlayerHomes");

        Document filter = new Document("name", "KangPvP2116");
        collection.find(filter).forEach(document -> {
            System.out.println(document.getString("id"));
        });

        try{
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name, lastacoin FROM player WHERE uuid = ?");

            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){                   //test First Co
                //If is the player hasplayedbefore
                int lastacoin = resultSet.getInt("lastacoin");

                if(!Main.INSTANCE.playerLastaCoin.containsKey(uuid)){
                    Main.INSTANCE.playerLastaCoin.put(uuid, lastacoin);
                }

            }else{
                //If is the First Connection
                createUserGrade(connection, player); //Add player in SQL DB
                createUserMongoDB(collection, player);
                Main.INSTANCE.playerLastaCoin.put(uuid, 0);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createUserGrade(Connection connection, Player player){
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT id FROM player ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);

            int id = 0;

            while (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
            st.close();

            System.out.println(id + " : TEST");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player (id, uuid, name, lastacoin, atm, join_at, update_at) VALUES (?, ?, ?, ?, ?, ?, ?)");
            long time = System.currentTimeMillis();

            preparedStatement.setInt(1, id + 1);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.setString(3, player.getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setTimestamp(6, new Timestamp(time));
            preparedStatement.setTimestamp(7, new Timestamp(time));
            preparedStatement.executeUpdate();

            String uuid = player.getUniqueId().toString();

            PreparedStatement preStatCo = connection.prepareStatement("INSERT INTO lastco (uuid, world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?, ?)");

            //Location loc = new Location(Bukkit.getWorld("Aragnok"), 718.5, 74, 72.5, -90, 0);

            preStatCo.setString(1, uuid);
            preStatCo.setString(2, "Aragnok");
            preStatCo.setDouble(3, 718.5);
            preStatCo.setDouble(4, 74);
            preStatCo.setDouble(5, 72.5);
            preStatCo.setFloat(6, -90);
            preStatCo.setFloat(7, 0);
            preStatCo.executeUpdate();

            System.out.println(" : TEST PreparedStatement");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createUserMongoDB(MongoCollection<Document> mongoCollection, Player player){

        Document document = new Document()
                .append("id", player.getUniqueId().toString())
                .append("name", player.getName());

        mongoCollection.insertOne(document);
    }
}
