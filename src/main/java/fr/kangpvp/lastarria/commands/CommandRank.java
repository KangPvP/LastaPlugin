package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.grade.Titres;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import java.util.Arrays;
import java.util.UUID;

public class CommandRank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String label, java.lang.String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            ItemStack myAwesomeSkull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
            UUID uuid = UUID.fromString("069741bd-360b-4a0c-b99f-acee9ea5d872");
            myAwesomeSkullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);

            Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);

            if(loc.getBlock().getType().equals(Material.CHEST)){
                Chest ChestGrade = (Chest) loc.getBlock().getState();
                Inventory InvGradeData = ChestGrade.getInventory();


                //Create ItemVilain
                ItemStack Vilain = InvGradeData.getItem(0);
                ItemMeta vilainMeta = Vilain.getItemMeta();
                vilainMeta.setDisplayName("§8§lVilain");
                vilainMeta.setLore(Arrays.asList(
                        "                                                        ",
                        "§eAccès de base",
                        "§7 • 2 items en vente",
                        "§7 • ATM 0.5$/min",
                        "§7 • /tpa cooldown de 10min"));
                Vilain.setItemMeta(vilainMeta);


                //set Item in inv
                Inventory inv = Bukkit.createInventory(null, 54, "§e§lTitres");

                inv.setItem(11, Vilain);
                inv.setItem(12, Grades.PAYSAN.getGrade().getItem());
                inv.setItem(13, commercantGrade.getItem());
                inv.setItem(14, artisanGrade.getItem());
                inv.setItem(15, ecuyerGrade.getItem());
                inv.setItem(21, vassalGrade.getItem());
                inv.setItem(22, chevalierGrade.getItem());
                inv.setItem(23, chatelainGrade.getItem());
                inv.setItem(30, baronGrade.getItem());
                inv.setItem(31, comteGrade.getItem());
                inv.setItem(32, ducGrade.getItem());
                inv.setItem(40, seigneurGrade.getItem());

                ItemStack blackWindows = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta blackWindow = blackWindows.getItemMeta();
                blackWindow.setDisplayName(" ");
                blackWindows.setItemMeta(blackWindow);

                for(int i = 0 ; i < 10 ; i ++) {
                    inv.setItem(i, blackWindows);
                }

                inv.setItem(17, blackWindows);
                inv.setItem(26, blackWindows);
                inv.setItem(35, blackWindows);
                inv.setItem(44, blackWindows);
                inv.setItem(53, blackWindows); //fermer le menu

                inv.setItem(52, blackWindows);
                inv.setItem(51, blackWindows);
                inv.setItem(50, blackWindows);
                inv.setItem(49, blackWindows);
                inv.setItem(48, blackWindows);
                inv.setItem(47, blackWindows);
                inv.setItem(46, blackWindows);
                inv.setItem(45, blackWindows); //retour au menu principal

                inv.setItem(36, blackWindows);
                inv.setItem(27, blackWindows);
                inv.setItem(18, blackWindows);
                inv.setItem(9, blackWindows);



                player.openInventory(inv);
            } else {
                player.sendMessage("Erreur: Les datas de cette commande ne sont pas chargé\nMerci de contacter KangPvP pour régler ce bug");
            }
        }

        return false;
    }

}
