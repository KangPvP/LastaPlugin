package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.PlayerUtils;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class CommandAtm implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            int playTimeAll = PlayerUtils.getTimePlayed(player);

            int playTimeReset = 0;

            String sql_1 = "SELECT uuid, atm FROM player WHERE uuid = '" + uuid + "'";

            DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();

            try {
                Connection connection = playerConnection.getConnection();

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql_1);

                while (rs.next()) {
                    playTimeReset = rs.getInt("atm");
                }

                rs.close();
                st.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            double playTime = playTimeAll - playTimeReset;


            System.out.println("playTimeAll:" + playTimeAll);
            System.out.println("playTimeReset:" + playTimeReset);

            System.out.println("playTime:" + playTime);

            double playTimeHour = playTime/72000;
            double playTimeHourRound = Math.round(playTimeHour*100.0)/100.0; //Arrondi 2 chiffres ex: 0.00

            System.out.println("playTimeHour: " + playTimeHour);

            double atm = 30;

            double money = playTimeHour*atm;
            double moneyRound = Math.round(money*100.0)/100.0; //Arrondi 2 chiffres ex: 0.00

            System.out.println("money: " + money);

            if(money > 2500){
                money = 2500;

                PlayerUtils.addMoney(player, money);

                actualiseATM(uuid, playTimeAll, playerConnection);

                player.sendMessage("Tu as dépasser la limite de money sur ATM");
                player.sendMessage("Vous avez donc récupérer " + moneyRound + " pour " + playTimeHourRound + " heures de jeu");


            }else{

                PlayerUtils.addMoney(player, money);

                actualiseATM(uuid, playTimeAll, playerConnection);

                player.sendMessage("Vous avez récupérer " + moneyRound + " pour " + playTimeHourRound + " heures de jeu");
            }
        }



        return false;
    }

    private void actualiseATM(String uuid, int playTimeAll, DbConnection playerConnection){

        long time = System.currentTimeMillis();
        String sql_2 = "UPDATE player SET atm = ?, update_at = ? WHERE uuid = '" + uuid + "'";

        try {
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql_2);

            preparedStatement.setInt(1, playTimeAll);
            preparedStatement.setTimestamp(2, new Timestamp(time));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
