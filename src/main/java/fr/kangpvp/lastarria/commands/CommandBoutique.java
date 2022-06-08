package fr.kangpvp.lastarria.commands;

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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

                    ItemMeta VilainMeta = Vilain.getItemMeta();
                    VilainMeta.setDisplayName("§6Cle");

                    Vilain.setItemMeta(cleM);


                    ItemStack Paysan = InvGradeData.getItem(1); //Paysan
                    List<String> listPaysan = new ArrayList<String>();
                    listPaysan.add("3 items en vente");
                    listPaysan.add("2 homes");

                    ItemStack Commercant = InvGradeData.getItem(2); //Commercant
                    List<String> listCommercant = new ArrayList<String>();
                    listCommercant.add("4 items en vente");
                    listCommercant.add("ATM 1.5$/min");

                    ItemStack Artisan = InvGradeData.getItem(3); //Artisans


                    ItemStack Ecuyer = InvGradeData.getItem(4); //Ecuyer
                    ItemStack Vassal = InvGradeData.getItem(5); //Vassal
                    ItemStack Chevalier = InvGradeData.getItem(6); //Chevalier
                    ItemStack Chatelain = InvGradeData.getItem(7); //Chatelain
                    ItemStack Baron = InvGradeData.getItem(8); //Baron
                    ItemStack Comte = InvGradeData.getItem(9); //Comte
                    ItemStack Duc = InvGradeData.getItem(10); //Duc
                    ItemStack Seigneur = InvGradeData.getItem(11); //Seigneur


                    Grade grade = new Grade(Paysan, "Paysan", "§2", 0, 300, null, listPaysan);
                    Paysan.setItemMeta(grade.getMeta());



                    Inventory inv = Bukkit.createInventory(null, 54, "Boutique");

                   // inv.setItem(11, Vilain);
                    inv.setItem(12, Paysan);
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
                }else {
                    player.sendMessage("Erreur: Les datas de cette commande ne sont pas chargé\nMerci de contacter KangPvP pour régler ce bug");
                }
            }





        return false;
    }
}
