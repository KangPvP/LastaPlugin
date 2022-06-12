package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.grade.Titre;
import fr.kangpvp.lastarria.grade.Titres;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class PlayerListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        InventoryView invView = event.getView();
        int slot = event.getSlot();
        ItemStack item = event.getCurrentItem();


        if(item == null) return;

        if(invView.getTitle().equals("§e§lTitres")) {
            event.setCancelled(true);

            if(item.getType().equals(Material.PLAYER_HEAD)){
                Titre titre = Titres.getGradeFromName(item.getItemMeta().getDisplayName());
                titre.performAction(player);
            }

        }

    }
}
