package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.customitems.CustomItem;
import fr.kangpvp.lastarria.customitems.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLastaItem implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage("Utilisation: /lastaitems [give/see]");
            return true;
        }

        String element = args[0];


        if(element.equals("give")) {

            if(args.length != 4) {
                player.sendMessage("Utilisation: /lastaitems give <nom de l'item> <quantité> <nom du joueur>");
                return true;
            } else {

                Player target = Bukkit.getPlayer(args[3]);
                int amount = Integer.parseInt(args[2]);
                CustomItem customItem = CustomItems.getCustomItemFromId(args[1]);

                if(!CustomItems.doesCustomItemExist(customItem)) {//bug
                    sender.sendMessage("Veuillez indiquer un item custom valide.");
                    return true;
                }

                if(target == null) {
                    sender.sendMessage("Veuillez indiquer un joueur valide.");
                    return true;
                }

                target.getInventory().addItem(customItem.getItem(amount));
                player.sendMessage(amount + " " + customItem.getName() + " ont été donnés avec succès à " + target.getDisplayName() + ".");

            }

        }

        return true;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> action = new ArrayList<>();
            action.add("give");
            action.add("see");
            return action;

        } else if (args[0].equals("give")) {

            if (args.length == 2) {
                List<String> customItem = new ArrayList<>();
                CustomItems[] itemList = CustomItems.values();

                for (int i = 0; i < itemList.length; i++) {
                    customItem.add(itemList[i].getCustomItem().getId());
                }
                return customItem;

            } else if (args.length == 3) {
                List<String> amount = new ArrayList<>();
                amount.add("1");
                amount.add("32");
                amount.add("64");
                return amount;

            } else if (args.length == 4) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (int i = 0; i < players.length; i++) {
                    playerNames.add(players[i].getName());
                }
                return playerNames;

            }
        }

        return null;
    }
}
