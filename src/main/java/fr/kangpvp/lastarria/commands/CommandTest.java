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
        }


        return false;
    }
}
