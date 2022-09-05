package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.PlayerUtils;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.security.Permission;
import java.sql.*;
import java.util.UUID;

public class CommandAtm implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

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

            int playTime = playTimeAll - playTimeReset;


            System.out.println("playTimeAll:" + playTimeAll);
            System.out.println("playTimeReset:" + playTimeReset);

            System.out.println("playTime:" + playTime);

            double playTimeMin = playTime/1200;

            //double playTimeHourRound = Math.round(playTimeHour*100.0)/100.0; //Arrondi 2 chiffres ex: 0.00

            System.out.println("playTimeHour: " + playTimeMin);

            double atm = 0.5; // 30$ / heures

            if(player.hasPermission("displayname.Seigneur")){
                atm = 2; // 120$ / heures
            }else if(player.hasPermission("displayname.Baron")){
                atm = 1.33; // 80$ / heures
            }else if(player.hasPermission("displayname.Écuyer")){
                atm = 0.83; // 50$ / heures
            }



            double money = playTimeMin*atm;
            double moneyRound = Math.round(money*100.0)/100.0; //Arrondi 2 chiffres ex: 0.00

            System.out.println("money: " + money);


            String disTime = displayTime(playTime);

            if(money > 2500){
                player.sendMessage("Tu as dépasser la limite de money sur ATM");
                player.sendMessage("Vous avez donc récupérer §e" + 2500 + "$ §fpour §2" + disTime + "§f de temps de jeu");
            }else{
                player.sendMessage("Vous avez récupérer §e" + moneyRound + " §fpour §2" + disTime + "§f de temps de jeu");
            }

                PlayerUtils.addMoney(player, money);

                actualiseATM(uuid, playTimeAll, playerConnection);

        }

        return false;
    }

    private void actualiseATM(UUID uuid, int playTimeAll, DbConnection playerConnection){

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

    private String displayTime(int ticks) {
        double ticksSec = Math.floor(ticks/20);
        System.out.println("ss: " + ticksSec);
        int hh = (int) Math.floor(ticksSec/3600);
        System.out.println("hh: " + hh);
        int mm = (int) Math.floor((ticksSec % 3600) / 60);
        System.out.println("mm: " + mm);

        //hh = (hh < 10 ? '0' + hh : hh);
        //mm = (mm < 10 ? '0' + mm : mm);

        if(hh == 0){
            return mm + "min";
        }else{
            return "" + hh + "h et " + mm + "min";
        }

    }
}
