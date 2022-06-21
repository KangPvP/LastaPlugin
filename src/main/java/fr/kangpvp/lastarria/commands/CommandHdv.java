package fr.kangpvp.lastarria.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHdv implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.chat("/ca gui");
                return false;
            }

            if(args.length == 1){
                if(args[1].equalsIgnoreCase("box")){
                    player.performCommand("ca mail");

                }else if(args[1].equalsIgnoreCase("ventes")){
                    player.performCommand("ca listed");
                }else if(args[1].equalsIgnoreCase("sell")){
                    if(args.length == 2){
                        player.performCommand("ca sell" + args[2]);
                    }else if(args.length == 3){
                        player.performCommand("ca sell " + args[2] + " " + args[3]);
                    }else{
                        player.performCommand("ca sell");
                    }
                }else if (args[1].equalsIgnoreCase("help")){
                    player.performCommand("ca help");
                }else{
                    System.out.println("test");
                }
            }else{
                System.out.println("test");
            }




        }


        return false;
    }
}
