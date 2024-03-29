package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
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

            if(!player.getLocation().getWorld().getName().equals("Aragnok")) {

                if (Main.INSTANCE.flying.contains(player)) {
                    player.setAllowFlight(false);
                    player.sendMessage("§3Tu as désactivé le mode fly");
                    Main.INSTANCE.flying.remove(player);
                } else if (!Main.INSTANCE.flying.contains(player)) {
                    player.setAllowFlight(true);
                    player.setFlySpeed((float) 0.06);
                    player.sendMessage("§3Tu as activé le mode fly");
                    Main.INSTANCE.flying.add(player);
                }
            } else{
                player.sendMessage("§3Tu ne peux pas fly dans ce monde");
            }
        }

        return false;
    }
}
