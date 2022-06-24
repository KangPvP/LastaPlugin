package fr.kangpvp.lastarria.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    if(player.getAllowFlight()) {
                        player.sendMessage("§bVous pouvez maintenant voler.");
                    } else {
                        player.sendMessage("§bVous ne pouvez maintenant plus voler.");
                    }
                    player.setAllowFlight(!player.getAllowFlight());
                    player.setFlySpeed(0.06F);
                } else {
                    System.out.println("test pas la perm");
                    sender.sendMessage("§cVous n'avez pas la permission requise.");
                }
                System.out.println("test final");

















        return false;
    }
}
