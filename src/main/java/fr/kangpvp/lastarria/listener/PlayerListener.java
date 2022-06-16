package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.grade.Grade;
import fr.kangpvp.lastarria.titre.Titre;
import fr.kangpvp.lastarria.titre.Titres;
import fr.kangpvp.lastarria.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.awt.*;


public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".register", true);
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();

        if(!player.hasPlayedBefore()) {
            ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin", 0);
            ConfigManager.getInstance().savePlayersData();
            ConfigManager.getInstance().reloadPlayersData();
        }

    }


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
                if(titre == null){System.out.println(Color.RED + "BUG Titre == null : listener => PlayerListener => lignes 39");return;}
                    titre.performAction(player);

            }

        }else if (invView.getTitle().equals("§e§lBoutique")){
            event.setCancelled(true);
            if(item.getType().equals(Material.PLAYER_HEAD)){
                if(slot == 20){ //Item VIP
                    if(player.hasPermission("group.vip")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else if(player.hasPermission("group.heros")){

                    }else if(player.hasPermission("group.legende")){

                    }else {
                        Grade.buyGrade(player, "vip", 1000);
                    }
                }else if(slot == 22){ //Item Heros
                    if(player.hasPermission("group.vip")){
                        Grade.buyGrade(player, "heros", 1000);
                    }else if(player.hasPermission("group.heros")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else if(player.hasPermission("group.legende")){

                    }else {
                        Grade.buyGrade(player, "heros", 2000);
                    }
                }else if(slot == 24){ //Item Légende
                    if(player.hasPermission("group.vip")){
                        Grade.buyGrade(player, "legende", 2500);
                    }else if(player.hasPermission("group.heros")){
                        Grade.buyGrade(player, "legende", 1500);
                    }else if(player.hasPermission("group.legende")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else {
                        Grade.buyGrade(player, "legende", 3500);
                    }
                }
        }

    }
}}
