package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.grade.Grade;
import fr.kangpvp.lastarria.titre.Titre;
import fr.kangpvp.lastarria.titre.Titres;
import fr.kangpvp.lastarria.utils.ConfigManager;
import fr.kangpvp.lastarria.utils.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.awt.*;
import java.util.UUID;


public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        new GamePlayer(player.getName());

        if(!player.hasPlayedBefore()) {
            ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin", 0);

            String key = "Joueurs." + uuid + ".data.worlds.survie";
            ConfigManager.pdatacfg.set(key + ".world", "first");
            ConfigManager.getInstance().savePlayersData();
            ConfigManager.getInstance().reloadPlayersData();

            player.teleport(new Location(Bukkit.getWorld("Aragnok"), 400, 70,351, 36, 0));

        } else {
            String key = "Joueurs." + uuid + ".data.lastco";
            String world = (String) ConfigManager.pdatacfg.get(key + ".world");
            Double x = (Double) ConfigManager.pdatacfg.get(key + ".x");
            Double y = (Double) ConfigManager.pdatacfg.get(key + ".y");
            Double z = (Double) ConfigManager.pdatacfg.get(key + ".z");

            player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        String world = player.getLocation().getWorld().getName();
        Double x = player.getLocation().getX();
        Double y = player.getLocation().getY();
        Double z = player.getLocation().getZ();

        String key = "Joueurs." + uuid + ".data.lastco";

        ConfigManager.pdatacfg.set(key + ".world", world);
        ConfigManager.pdatacfg.set(key + ".x", x);
        ConfigManager.pdatacfg.set(key + ".y", y);
        ConfigManager.pdatacfg.set(key + ".z", z);
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();
    }


    @EventHandler
    public void onDead(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(player.hasPermission("group.heros")){
            int xp = player.getTotalExperience();
            int xp50 = xp/2;
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> player.giveExp(xp50), 60);
        }
        if(player.hasPermission("group.legende")){
            int xp = player.getTotalExperience();
            Bukkit.getScheduler().runTaskLater(Main.INSTANCE, () -> player.giveExp(xp), 60);
        }


    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location from = event.getFrom();
        UUID uuid = player.getUniqueId();

        if(from.getWorld().getName().equals("world") || from.getWorld().getName().equals("world_nether") || from.getWorld().getName().equals("world_the_end")){
            String key = "Joueurs." + uuid + ".data.worlds.survie";
            ConfigManager.pdatacfg.set(key + ".world", from.getWorld().getName());
            ConfigManager.pdatacfg.set(key + ".x", from.getX());
            ConfigManager.pdatacfg.set(key + ".y", from.getY());
            ConfigManager.pdatacfg.set(key + ".z", from.getZ());
            ConfigManager.getInstance().savePlayersData();
            ConfigManager.getInstance().reloadPlayersData();
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
        if (Main.portailTp.isInZone(player.getLocation())) {
            if (!gp.isInZone) {
                gp.isInZone = true;
                UUID uuid = player.getUniqueId();

                if(ConfigManager.pdatacfg.get("Joueurs." + uuid + ".data.worlds.survie").equals("first")){
                    player.performCommand("rtp");
                }else{
                    String key = "Joueurs." + uuid + ".data.worlds.survie";
                    String world = (String) ConfigManager.pdatacfg.get(key + ".world");
                    Double x = (Double) ConfigManager.pdatacfg.get(key + ".x");
                    Double y = (Double) ConfigManager.pdatacfg.get(key + ".y");
                    Double z = (Double) ConfigManager.pdatacfg.get(key + ".z");

                    player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                }

                player.sendMessage("§bVous entrez en monde survie.");

            }
        } else {
            if (gp.isInZone) {
                gp.isInZone = false;
            }
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        InventoryView invView = event.getView();

        ItemStack item = event.getCurrentItem();


        if(item == null){
            return;
        }

        int slot = event.getSlot();


        if(item.getItemMeta().getDisplayName().equals("§4§lFermer")){
            event.setCancelled(true);
            player.closeInventory();
        }


        if(item.getItemMeta().getDisplayName().equals("§6§lMenu - §fPrincipale")){
            event.setCancelled(true);
            player.closeInventory();
            player.performCommand("gui open info");
        }


        if(invView.getTitle().equals("§e§lTitres")) {
            event.setCancelled(true);

            if(item.getType().equals(Material.PLAYER_HEAD)){

                String name = item.getItemMeta().getDisplayName().toLowerCase().substring(14);

                Titre titre = Titres.getGradeFromName(name);
                if(titre == null){System.out.println("BUG Titre == null : listener => PlayerListener => lignes 39");return;}
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
                        Grade.buyGrade(player, "vip", 1000.00);
                    }
                }else if(slot == 22){ //Item Heros
                    if(player.hasPermission("group.vip") && !player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "heros", 1000.00);
                    }else if(player.hasPermission("group.heros")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else if(player.hasPermission("group.legende")){

                    }else {
                        Grade.buyGrade(player, "heros", 2000.00);
                    }
                }else if(slot == 24){ //Item Légende
                    if(player.hasPermission("group.vip") && !player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "legende", 2500.00);
                    }else if(player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "legende", 1500.00);
                    }else if(player.hasPermission("group.legende")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else {
                        Grade.buyGrade(player, "legende", 3500.00);
                    }
                }
        }

    }
}}
