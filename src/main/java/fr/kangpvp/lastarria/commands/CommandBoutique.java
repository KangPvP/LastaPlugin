package fr.kangpvp.lastarria.commands;

import com.google.common.collect.Lists;

import fr.kangpvp.lastarria.utils.Grade;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CommandBoutique implements CommandExecutor {

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

                ItemStack cle = new ItemStack(Material.OAK_BUTTON, 64);
                ItemMeta cleM = cle.getItemMeta();
                cleM.setDisplayName("§6Cle");
                cle.setItemMeta(cleM);
                InvGradeData.addItem(cle);

                ItemStack Vilain = InvGradeData.getItem(0); //Vilain
                ItemMeta vilainMeta = Vilain.getItemMeta();
                vilainMeta.setDisplayName("§8§lVilain");
                vilainMeta.setLore(Arrays.asList(
                        "                                                     ",
                        "§8§nAccès de base",
                        "§7 • 2 items en vente",
                        "§7 • ATM 1$/min",
                        "§7 • /tpa cooldown de 10min"));

                Vilain.setItemMeta(vilainMeta);

                //Paysan
                List<String> listPaysan = new ArrayList<String>();
                listPaysan.add("3 items en vente");
                listPaysan.add("2 homes");
                Grade paysanGrade = new Grade(1, "Payson", "2", 0, 300, null, listPaysan);

                //Commercant
                List<String> listCommercant = new ArrayList<String>();
                listCommercant.add("4 items en vente");
                listCommercant.add("ATM 1.5$/min");
                Grade commercantGrade = new Grade(2, "Commercant", "2", 3, 300, null, listPaysan);

                ItemStack Artisan = InvGradeData.getItem(3); //ArtisansG
                List<String> listArtisan = new ArrayList<String>();
                listArtisan.add("5 items en vente");
                listArtisan.add("3 homes");
                listArtisan.add("/tpa cooldown 3 min");

                ItemStack Ecuyer = InvGradeData.getItem(4); //Ecuyer
                List<String> listEcuyer = new ArrayList<String>();
                listEcuyer.add("6 items en vente");
                listEcuyer.add("ATM 2$/min");

                ItemStack Vassal = InvGradeData.getItem(5); //Vassal
                List<String> listVassal = new ArrayList<String>();
                listVassal.add("6 items en vente");
                listVassal.add("ATM 2$/min");

                ItemStack Chevalier = InvGradeData.getItem(6); //Chevalier
                List<String> listChevalier = new ArrayList<String>();
                listChevalier.add("6 items en vente");
                listChevalier.add("ATM 2$/min");

                ItemStack Chatelain = InvGradeData.getItem(7); //Chatelain
                ItemStack Baron = InvGradeData.getItem(8); //Baron
                ItemStack Comte = InvGradeData.getItem(9); //Comte
                ItemStack Duc = InvGradeData.getItem(10); //Duc
                ItemStack Seigneur = InvGradeData.getItem(11); //Seigneur


                Inventory inv = Bukkit.createInventory(null, 54, "Boutique");

                inv.setItem(11, Vilain);
                inv.setItem(12, paysanGrade.getItem());
                /*inv.setItem(13, Commercant);
                inv.setItem(14, Artisan);
                inv.setItem(15, Ecuyer);
                inv.setItem(20, Vassal);
                inv.setItem(21, Chevalier);
                inv.setItem(22, Chatelain);
                inv.setItem(30, Baron);
                inv.setItem(31, Comte);
                inv.setItem(32, Duc);
                inv.setItem(40, Seigneur);*/


                inv.setItem(2, myAwesomeSkull);
                inv.setItem(1, Vilain);
                inv.setItem(0, new ItemStack(Material.NETHERITE_SWORD));
                player.openInventory(inv);
            } else {
                player.sendMessage("Erreur: Les datas de cette commande ne sont pas chargé\nMerci de contacter KangPvP pour régler ce bug");
            }
        }

        return false;
    }

}
