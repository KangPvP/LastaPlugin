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

            inv.setItem(20,SucessList.PECHE.getSucess().setValue(PlayerUtils.getSucessPeche(player)).getItem(player));
            inv.setItem(21,SucessList.MINAGE.getSucess().setValue(PlayerUtils.getSucessMinage(player)).getItem(player));
            inv.setItem(22,SucessList.CHASSE.getSucess().setValue(PlayerUtils.getSucessChasse(player)).getItem(player));
            inv.setItem(23,SucessList.ELVAGE.getSucess().setValue(PlayerUtils.getSucessElvage(player)).getItem(player));
            inv.setItem(24,SucessList.JUMP.getSucess().setValue(PlayerUtils.getSucessSaut(player)).getItem(player));
            inv.setItem(29,SucessList.TRADE.getSucess().setValue(PlayerUtils.getSucessTrade(player)).getItem(player));
            inv.setItem(30,SucessList.TIME.getSucess().setValue(PlayerUtils.getSucessTime(player)).getItem(player));
            inv.setItem(31,SucessList.SEIGNEUR.getSucess().setValue(PlayerUtils.getSucessSeigneur(player)).getItem(player));


            player.openInventory(inv);

            //player.performCommand("succes");


        }


        return false;
    }
}
