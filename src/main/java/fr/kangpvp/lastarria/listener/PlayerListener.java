package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.grade.Grades;
import fr.kangpvp.lastarria.grade.Grade;
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
import java.sql.Timestamp;

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
        int slot = event.getSlot();
        ItemStack test = event.getCurrentItem();

        String listGrade = PlaceholderAPI.setPlaceholders(player, "%player_time%");
        Timestamp ts = new Timestamp(Integer.parseInt((String) listGrade));
        Bukkit.broadcastMessage("Yo tu es: OKOKOOKO:" + ts.toString() + test.getItemMeta().getDisplayName().substring(4).toString());

        if(test == null) return;
        Bukkit.broadcastMessage("Test 563" + Grades.getGradeFromName(test.getItemMeta().getDisplayName()));
        if(invtest.getTitle().equals("§e§lTitres")) {
            event.setCancelled(true);
            Grades.getGradeFromName(test.getItemMeta().getDisplayName()).performAction(player);
            Bukkit.broadcastMessage("Test 567");


            /*switch (slot) {
                case 11:
                    if(listGrade.contains("vagabond")) {
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
