package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CommandRTP implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            int max = 1024;
            int min = -1024;

            int x = new Random().nextInt(max - min) + min;
            int z = new Random().nextInt(max - min) + min;

            int cooldownTime = 300; // Get number of seconds from wherever you want

            if(Main.cooldowns.get(player.getName()) == null) {
                Location loc = new Location(Bukkit.getWorld("world"), x,0, z);

                int y = loc.getWorld().getHighestBlockYAt(loc);
                loc.setY(y);

                if(loc.getBlock().getType() == Material.WATER){
                    player.performCommand("rtp");
                    return false;
                }

                loc.setY(y+3);
                player.teleport(loc);
                player.sendMessage("Tu as été téléporté aléatoirement en " + x +" "+ y +" "+ z);
                Main.cooldowns.put(player.getName(), System.currentTimeMillis());
                return false;
            }

            if(Main.cooldowns.containsKey(player.getName())) {
                long secondsLeft = ((Main.cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                    // Still cooling down
                    player.sendMessage("Attends "+ secondsLeft +" secondes avant de pouvoir executer cette commande!");
                    return false;
                }else{
                    Location loc = new Location(Bukkit.getWorld("world"), x,0, z);

                    int y = loc.getWorld().getHighestBlockYAt(loc);
                    loc.setY(y);

                    if(loc.getBlock().getType() == Material.WATER){
                        player.performCommand("rtp");
                        return false;
                    }

                    loc.setY(y+3);
                    player.teleport(loc);
                    player.sendMessage("Tu as été téléporté aléatoirement en " + x +" "+ y +" "+ z);
                    Main.cooldowns.put(player.getName(), System.currentTimeMillis());
                }
            }
        }

        return false;
    }
}
