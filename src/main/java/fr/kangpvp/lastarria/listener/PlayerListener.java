package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.grade.GradeManagment;
import fr.kangpvp.lastarria.grade.Grades;
import fr.kangpvp.lastarria.utils.Grade;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

public class PlayerListener implements Listener {

    /*private void changeGrade(Player player, Grade grade) {
        if(grade.getPreviousGrade() == GradeManagment.getGrade(player)) {
            GradeManagment.setGrade(player, grade);
        } else {
            player.sendMessage("&cVous ne pouvez pas acheter ce grade !");
        }
    }*/

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        InventoryView invtest = event.getView();
        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();

        String listGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");

        if(item == null) return;
        player.sendMessage("Test tets");
        if(invtest.getTitle().equals("§e§lTitres")) {
            event.setCancelled(true);
            switch (slot) {
                case 11:
                    if(listGrade.contains("barons")) {
                        Bukkit.broadcastMessage("Yo tu es: " + listGrade);
                    } else {
                        player.sendMessage("Vous ne pouvez pas acheter ce grade");
                    }
                    break;

                case 12:

                    break;

                case 13:
                    break;

                default:

                    break;
            }

        }

    }
}
