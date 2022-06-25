package fr.kangpvp.lastarria.commands.lastacoin;

import fr.kangpvp.lastarria.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandLastacoin implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

            if(args.length != 3){
                System.out.println("/lastacoin [add/remove/set] <target> <amount>");
                return true;
            }

            String element = args[0];
            Player target = Bukkit.getPlayer(args[1]);
            double amount = Double.parseDouble(args[2]);

            switch(element) {

                case "add":
                    PlayerUtils.addLastaCoin(target, amount);

                    break;
                case "remove":
                    PlayerUtils.removeLastaCoin(target, amount);
                    break;
                case "set":
                    PlayerUtils.setLastaCoin(target, amount);
                    break;
                default:
                    System.out.println("Veuillez ajouter un element valide: 'add' 'remove' 'set'");
                    break;
            }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1){
            List<String> action = new ArrayList<>();
            action.add("add");
            action.add("remove");
            action.add("set");

            return action;
        } else if (args.length == 2){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++){
                playerNames.add(players[i].getName());
            }

            return playerNames;
        } else if (args.length == 3){
            List<String> amount = new ArrayList<>();
            amount.add("100");
            amount.add("500");
            amount.add("1000");

            return amount;
        }
        return null;
    }
}
