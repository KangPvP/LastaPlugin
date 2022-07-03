package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CommandClose implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;

            Bukkit.getScheduler().runTaskLater (Main.INSTANCE, () -> player.closeInventory(), 5);
        }

        return false;
    }
}
