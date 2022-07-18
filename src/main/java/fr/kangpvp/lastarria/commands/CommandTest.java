package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("hasValideSlot: §e" + PlayerUtils.hasValideSlot(player));

            player.sendMessage("Sucess Peche: §e" + PlayerUtils.getSucessPeche(player));
            player.sendMessage("Sucess Minage: §e" + PlayerUtils.getSucessMinage(player));
            player.sendMessage("Sucess Chasse: §e" + PlayerUtils.getSucessChasse(player));

            player.sendMessage("Sucess Saut : §e" + PlayerUtils.getSucessSaut(player));
            //player.sendMessage("Sucess Craft: §e" + PlayerUtils.getSucessCraft(player));
            player.sendMessage("Sucess Sleep: §e" + PlayerUtils.getSucessSleep(player));

            player.sendMessage("Sucess Trade: §e" + PlayerUtils.getSucessTrade(player));
            player.sendMessage("Sucess Time: §e" + PlayerUtils.getSucessTime(player));
            player.sendMessage("Sucess Seigneur: §e" + PlayerUtils.getSucessSeigneur(player));

            player.sendMessage("Sucess AllSucces: §e" + PlayerUtils.getSucessAllSucces(player));
        }


        return false;
    }
}
