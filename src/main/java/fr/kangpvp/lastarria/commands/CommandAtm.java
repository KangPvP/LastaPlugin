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
import java.util.UUID;

public class CommandAtm implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();

            DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();

            int playTimeAll = PlayerUtils.getTimePlayed(player);
            int playTimeReset = getDbAtm(uuid, playerConnection);
            int playTime = playTimeAll - playTimeReset;

            if(playTime < 6000){
                player.sendMessage("Attendes " + (300-playTime/20) + " secondes avant de pouvoir faire cela");
                return false;
            }

            double playTimeMin = playTime/1200;

            double atm = playerAtmFacteur(player);
            double money = playTimeMin*atm;
            double moneyRound = Math.round(money*100.0)/100.0; //Arrondi 2 chiffres ex: 0.00

            String formatPlayTime = displayTime(playTime);

            /*System.out.println("playTimeAll:" + playTimeAll);
            System.out.println("playTimeReset:" + playTimeReset);
            System.out.println("playTime:" + playTime);
            System.out.println("playTimeHour: " + playTimeMin);
            System.out.println("money: " + money);*/

            if(money > 2500){
                money = 2500+(money-2500)/10;
                player.sendMessage("Tu as dépasser la limite de money sur ATM");
                player.sendMessage("Vous avez donc récupérer §e" + money + "$ §fpour §2" + formatPlayTime + "§f de temps de jeu");
            }else{
                player.sendMessage("Vous avez récupérer §e" + moneyRound + " §fpour §2" + formatPlayTime + "§f de temps de jeu");
            }

                PlayerUtils.addMoney(player, money);
                updateDbAtm(uuid, playTimeAll, playerConnection);
                Main.cooldowns.put(player.getName(), System.currentTimeMillis());
        }

        return false;
    }

    private Integer getDbAtm(UUID uuid, DbConnection playerConnection){
        String sql = "SELECT uuid, atm FROM player WHERE uuid = '" + uuid + "'";

        int playTimeReset = 0;

        try {
            Connection connection = playerConnection.getConnection();

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                playTimeReset = rs.getInt("atm");
            }

            rs.close();
            st.close();

            return playTimeReset;

        } catch (SQLException e) {
            e.printStackTrace();
            return playTimeReset;
        }
    }

    private void updateDbAtm(UUID uuid, int playTimeAll, DbConnection playerConnection){

        long time = System.currentTimeMillis();
        String sql = "UPDATE player SET atm = ?, update_at = ? WHERE uuid = '" + uuid + "'";

        try {
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, playTimeAll);
            preparedStatement.setTimestamp(2, new Timestamp(time));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Double playerAtmFacteur(Player player){
        double atm = 0.5; // 30$ / heures

        if(player.hasPermission("displayname.Seigneur")){
            atm = 2; // 120$ / heures
        }else if(player.hasPermission("displayname.Baron")){
            atm = 1.33; // 80$ / heures
        }else if(player.hasPermission("displayname.Écuyer")){
            atm = 0.83; // 50$ / heures
        }

        return atm;
    }

    private String displayTime(int ticks) {

        double ticksSec = Math.floor(ticks/20);
        int hh = (int) Math.floor(ticksSec/3600);
        int mm = (int) Math.floor((ticksSec % 3600) / 60);

        if(hh == 0){
            return mm + "min";
        }else{
            return "" + hh + "h et " + mm + "min";
        }
    }
}
