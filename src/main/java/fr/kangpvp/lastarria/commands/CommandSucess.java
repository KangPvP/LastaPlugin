package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.utils.GuiStyle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CommandSucess implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Inventory sucessGui = Bukkit.createInventory(null, 53);

        GuiStyle.contour(sucessGui);

        return false;
    }
}
