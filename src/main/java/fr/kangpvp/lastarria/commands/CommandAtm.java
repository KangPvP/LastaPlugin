package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.PlayerUtils;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandAtm implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            String uuid = player.getUniqueId().toString();

            int playTimeAll = PlayerUtils.getTimePlayed(player);



            int playTimeReset = 0;

            String sql = "SELECT uuid, atm FROM player WHERE uuid = '" + uuid + "'";

            DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();

            try {
                Connection connection = playerConnection.getConnection();

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    playTimeReset = rs.getInt("atm");
                }

                rs.close();
                st.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            int playTime = playTimeAll - playTimeReset;

            double money = playTime*0.00083;





            player.sendMessage("Vous avez récuperer");

        }



        return false;
    }
}
