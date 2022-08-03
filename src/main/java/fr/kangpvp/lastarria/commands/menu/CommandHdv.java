package fr.kangpvp.lastarria.commands.menu;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHdv implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.chat("/ca gui");
                return true;
            }

            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("box")){
                    player.performCommand("ca mail");

                }else if(args[0].equalsIgnoreCase("ventes")){
                    player.performCommand("ca listed");

                }else if(args[0].equalsIgnoreCase("sell")){
                    if(args.length == 2){
                        player.performCommand("ca sell " + args[1]);

                    }else if(args.length == 3){
                        player.performCommand("ca sell " + args[1] + " " + args[2]);

                    }else{
                        player.performCommand("ca sell");

                    }
                }else if (args[0].equalsIgnoreCase("help")){
                    player.performCommand("ca help");

                }else{
                    System.out.println("test2");
                }
            }else{
                System.out.println("test");
            }

        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1){
            List<String> action = new ArrayList<>();
            action.add("sell");
            action.add("box");
            action.add("ventes");
            action.add("help");

            return action;

        } else if (args.length == 2 && args[0].equalsIgnoreCase("sell")){
            List<String> price = new ArrayList<>();
            price.add("10");
            price.add("200");
            price.add("1000");


            return price;
        } else if (args.length == 3 && args[0].equalsIgnoreCase("sell")){
            List<String> amount = new ArrayList<>();
            amount.add("1");
            amount.add("32");
            amount.add("64");

            return amount;
        }
        return null;
    }

}
