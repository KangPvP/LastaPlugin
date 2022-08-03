package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPvp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length < 1){
                player.sendMessage("Veuillez préciser on ou off");
                return false;
            }

            boolean pvp = player.hasPermission("lastarria.player.pvp");

            if(args[0].equalsIgnoreCase("on")){
                if(pvp){
                    player.sendMessage("Votre PvP est déjà activé");
                    return false;
                }else{
                    PlayerUtils.addPermission(player, "lastarria.player.pvp");
                    player.sendMessage("Vous avez activer votre PvP");
                }
            }else if(args[0].equalsIgnoreCase("off")){
                if(pvp){
                    PlayerUtils.removePermission(player, "lastarria.player.pvp");
                    player.sendMessage("Vous avez désactivé votre PvP");
                }else{
                    player.sendMessage("Votre PvP est déjà désactivé");
                    return false;
                }
            }else {
                player.sendMessage("Argument non valide");
            }



            if(player.hasPermission("lastarria.player.pvp")){

            }else{

            }



        }


        return false;
    }
}
