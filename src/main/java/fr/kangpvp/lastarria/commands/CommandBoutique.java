package fr.kangpvp.lastarria.commands;

import com.google.common.collect.Lists;

import fr.kangpvp.lastarria.utils.Grade;
import me.clip.placeholderapi.PlaceholderAPI;
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

            String test = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");
            Bukkit.broadcastMessage("Yo tu es: " + test);

            if(loc.getBlock().getType().equals(Material.CHEST)){

                Chest ChestGrade = (Chest) loc.getBlock().getState();
                Inventory InvGradeData = ChestGrade.getInventory();

                ItemStack cle = new ItemStack(Material.OAK_BUTTON, 64);
                ItemMeta cleM = cle.getItemMeta();
                cleM.setDisplayName("§6Cle");
                cle.setItemMeta(cleM);
                InvGradeData.addItem(cle);

                ItemStack Vilain = InvGradeData.getItem(0);
                ItemMeta vilainMeta = Vilain.getItemMeta();
                vilainMeta.setDisplayName("§8§lVilain");
                vilainMeta.setLore(Arrays.asList(
                        "                                                        ",
                        "§7 • de base",
                        "§7 • 2 items en vente",
                        "§7 • ATM 0.5$/min",
                        "§7 • /tpa cooldown de 10min"));
                Vilain.setItemMeta(vilainMeta);

                List<String> listPaysan = new ArrayList<>();
                listPaysan.add("3 items en vente");
                listPaysan.add("2 homes");
                Grade paysanGrade = new Grade(1, "Paysan", "2", 0, 300, null, listPaysan);

                List<String> listCommercant = new ArrayList<>();
                listCommercant.add("4 items en vente");
                listCommercant.add("ATM 1$/min");
                Grade commercantGrade = new Grade(2, "Commercant", "2", 3, 1400, null, listCommercant);

                List<String> listArtisan = new ArrayList<>();
                listArtisan.add("5 items en vente");
                listArtisan.add("3 homes");
                listArtisan.add("/tpa cooldown 3 min");
                Grade artisanGrade = new Grade(3, "Artisan", "2", 7, 1400, null, listArtisan);

                List<String> listEcuyer = new ArrayList<>();
                listEcuyer.add("6 items en vente");
                listEcuyer.add("ATM 1.5$/min");
                Grade ecuyerGrade = new Grade(4, "Ecuyer", "2", 20, 1400, null, listEcuyer);

                List<String> listVassal = new ArrayList<>();
                listVassal.add("7 items en vente");
                listVassal.add("+ 1 home (Votre nombre de home)");
                listVassal.add("Acces au /tpahere (cooldown 10 min)");
                Grade vassalGrade = new Grade(5, "Vassal", "9", 32, 1400, null, listVassal);

                List<String> listChevalier = new ArrayList<>();
                listChevalier.add("8 items en vente");
                listChevalier.add("ATM 2$/min");
                Grade chevalierGrade = new Grade(6, "Chevalier", "9", 52, 1400, null, listChevalier);

                List<String> listChatelain = new ArrayList<>();
                listChatelain.add("9 items en vente");
                listChatelain.add("5 homes");
                listChatelain.add("/tpa 30s de Cooldown");
                Grade chatelainGrade = new Grade(7, "Chatelain", "9", 84, 1400, null, listChatelain);

                List<String> listBaron = new ArrayList<>();
                listBaron.add("10 items en vente");
                listBaron.add("ATM 2.5$/min");
                Grade baronGrade = new Grade(8, "Baron", "e", 135, 1400, null, listBaron);

                List<String> listCompte = new ArrayList<>();
                listCompte.add("11 items en vente");
                listCompte.add("6 homes");
                listCompte.add("/tpahere 3min de Cooldown");
                Grade comteGrade = new Grade(9, "Comte", "e", 216, 1400, null, listChevalier);

                List<String> listDuc = new ArrayList<>();
                listDuc.add("12 items en vente");
                listDuc.add("ATM 3$/min");
                Grade ducGrade = new Grade(10, "Duc", "e", 345, 1400, null, listDuc);

                List<String> listSeigneur = new ArrayList<>();
                listDuc.add("13 items en vente");
                listDuc.add("7 homes");
                Grade seigneurGrade = new Grade(10, "Seigneur", "c", 553, 1400, null, listSeigneur);

                Inventory inv = Bukkit.createInventory(null, 54, "§e§lTitres");

                inv.setItem(11, Vilain);
                inv.setItem(12, paysanGrade.getItem());
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

                inv.setItem(0, blackWindows);
                inv.setItem(1, blackWindows);
                inv.setItem(2, blackWindows);
                inv.setItem(3, blackWindows);
                inv.setItem(4, blackWindows);
                inv.setItem(5, blackWindows);
                inv.setItem(6, blackWindows);
                inv.setItem(7, blackWindows);
                inv.setItem(8, blackWindows);

                inv.setItem(17, blackWindows);
                inv.setItem(26, blackWindows);
                inv.setItem(35, blackWindows);
                inv.setItem(45, blackWindows);
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
