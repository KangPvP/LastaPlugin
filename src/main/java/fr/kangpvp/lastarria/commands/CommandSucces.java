package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.utils.GuiStyle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CommandSucces implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(player, 53, "§eSucces");

            GuiStyle.contour(inv);

            player.openInventory(inv);


        }


        return false;
    }
}
