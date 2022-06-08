package fr.kangpvp.lastarria.commands;

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

                    ItemStack Vilain = InvGradeData.getItem(2); //Villain
                    ItemStack Paysan = InvGradeData.getItem(3); //Paysan
                    ItemStack Artisans = InvGradeData.getItem(4); //Artisans
                    ItemStack Ecuyer = InvGradeData.getItem(5); //Artisans

                    Inventory inv = Bukkit.createInventory(null, 54, "Boutique");

                    inv.setItem(1, Vilain);

                    inv.setItem(2, myAwesomeSkull);
                    inv.setItem(1, Vilain);
                    inv.setItem(0, new ItemStack(Material.NETHERITE_SWORD));
                    player.openInventory(inv);
                }else {
                    player.sendMessage("Erreur: Les datas de cette commande ne sont pas chargé\nMerci de contacter un Administrateur pour régler ce bug");
                }
            }





        return false;
    }
}
