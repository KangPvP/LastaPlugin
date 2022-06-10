package fr.kangpvp.lastarria.commands;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandBoutique implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            long time=player.getPlayerTime()/20;
            int secs= (int) (time%60);
            time/=60;
            int mins= (int) (time%60);
            time/=60;
            int hours= (int) (time%24);
            time/=24;
            int days= (int) time;
            String totalTime=days+" days, "+hours+":"+mins+":"+secs+" hour.";

            System.out.println(player.getStatistic(Statistic.PLAY_ONE_MINUTE));




        }

        return false;
    }
}
