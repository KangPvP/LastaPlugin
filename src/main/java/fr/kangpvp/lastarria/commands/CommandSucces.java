package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.sucess.Sucess;
import fr.kangpvp.lastarria.sucess.SucessList;
import fr.kangpvp.lastarria.utils.GuiStyle;
import fr.kangpvp.lastarria.utils.PlayerUtils;
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

            Inventory inv = Bukkit.createInventory(player, 54, "§eSucces");

            GuiStyle.contour(inv);

            inv.setItem(22,SucessList.PECHE.getSucess().setValue(PlayerUtils.getSucessPeche(player)).getItem(player));


            player.openInventory(inv);

            //player.performCommand("succes");


        }


        return false;
    }
}
