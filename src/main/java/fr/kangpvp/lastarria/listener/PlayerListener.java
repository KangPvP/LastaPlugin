package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.shop.Grade;
import fr.kangpvp.lastarria.shop.Key;
import fr.kangpvp.lastarria.sucess.Sucess;
import fr.kangpvp.lastarria.sucess.SucessList;
import fr.kangpvp.lastarria.titre.Titre;
import fr.kangpvp.lastarria.titre.Titres;
import fr.kangpvp.lastarria.utils.ConfigManager;
import fr.kangpvp.lastarria.utils.GamePlayer;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;
import java.util.UUID;


public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        new GamePlayer(player.getName());

        DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();


        try{

            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name, lastacoin FROM player WHERE uuid = ?");

            System.out.println("Test PreparedStatement");
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int lastacoin = resultSet.getInt("lastacoin");

                if(Main.INSTANCE.getPlayerLastaCoin().containsKey(uuid)){
                    //if player is allready in Cache
                }else{
                    Main.INSTANCE.getPlayerLastaCoin().put(uuid, lastacoin);
                }

            }else{
                createUserGrade(connection, player);
                Main.INSTANCE.getPlayerLastaCoin().put(uuid, 0);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }




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

    private void createUserGrade(Connection connection, Player player){


        try {

            Statement st = connection.createStatement();
            String sql = "SELECT id FROM player ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(sql);

            int id = 0;

            while (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
            st.close();

            System.out.println(id + " : TEST");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player (id, uuid, name, lastacoin, atm, join_at, update_at) VALUES (?, ?, ?, ?, ?, ?, ?)");
            long time = System.currentTimeMillis();

            preparedStatement.setInt(1, id + 1);
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.setString(3, player.getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setTimestamp(6, new Timestamp(time));
            preparedStatement.setTimestamp(7, new Timestamp(time));
            preparedStatement.executeUpdate();

            String uuid = player.getUniqueId().toString();

            PreparedStatement preStatCo = connection.prepareStatement("INSERT INTO lastco (uuid, world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?, ?)");

            //Location loc = new Location(Bukkit.getWorld("Aragnok"), 718.5, 74, 72.5, -90, 0);



            preStatCo.setString(1, uuid);
            preStatCo.setString(2, "Aragnok");
            preStatCo.setDouble(3, 718.5);
            preStatCo.setDouble(4, 74);
            preStatCo.setDouble(5, 72.5);
            preStatCo.setFloat(6, -90);
            preStatCo.setFloat(7, 0);
            preStatCo.executeUpdate();



            System.out.println(" : TEST PreparedStatement");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        String world = player.getLocation().getWorld().getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();

        try {
            Connection connection = playerConnection.getConnection();

            String sql = "UPDATE lastco SET world = ?, x = ?, y = ?, z = ?, yaw = ?, pitch = ? WHERE uuid = '" + uuid + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, world);
            preparedStatement.setDouble(2, x);
            preparedStatement.setDouble(3, y);
            preparedStatement.setDouble(4, z);
            preparedStatement.setFloat(5, yaw);
            preparedStatement.setFloat(6, pitch);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }















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
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getItem() == null){
            return;
        }

        ItemStack item = event.getItem();

        if(item.getType().equals(Material.DIAMOND_AXE)){
            player.sendMessage("Hello 2");
            if(item.getItemMeta().getCustomModelData() == 1){
                player.sendMessage("Hello 3");
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 5, false, false ,false));
                player.sendMessage("Hello 4");


            }
        }


    }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        InventoryView invView = event.getView();


        if(event.getCurrentItem() == null){
            return;
        }

        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();

        if(invView.getTitle().equals("§e§lTitres")) {
            event.setCancelled(true);

            if(item.getItemMeta().getDisplayName().equals("§4§lFermer")){
                player.closeInventory();
            }


            if(item.getItemMeta().getDisplayName().equals("§6§lMenu")){
                player.closeInventory();
                player.performCommand("gui open info");
            }

            if(item.getType().equals(Material.PLAYER_HEAD)){

                String name = item.getItemMeta().getDisplayName().toLowerCase().substring(14);

                Titre titre = Titres.getGradeFromName(name);
                if(titre == null){System.out.println("BUG Titre == null : listener => PlayerListener => lignes 39");return;}
                    titre.performAction(player);

            }

        }else if(invView.getTitle().equals("§e§lClé LootBox")){
            event.setCancelled(true);
            if(item.getItemMeta().getDisplayName().equals("§4§lFermer")){
                player.closeInventory();
            }

            if(item.getItemMeta().getDisplayName().equals("§6§lMenu")){
                player.closeInventory();
                player.performCommand("gui open info");
            }

            switch (slot){
                case 11:
                    Key.buyKey(player, 75, 1, 1);
                    break;
                case 20:
                    Key.buyKey(player, 225, 1, 4);
                    break;
                case 29:
                    Key.buyKey(player, 450, 1, 10);
                    break;

                case 13:
                    Key.buyKey(player, 150, 2, 1);
                    break;
                case 22:
                    Key.buyKey(player, 450, 2, 4);
                    break;
                case 31:
                    Key.buyKey(player, 900, 2, 10);
                    break;

                case 15:
                    Key.buyKey(player, 300, 3, 1);
                    break;
                case 24:
                    Key.buyKey(player, 900, 3, 4);
                    break;
                case 33:
                    Key.buyKey(player, 1800, 3, 10);
                    break;

                default:
                    System.out.println("Erreur buy Key");
                    break;
            }

        }else if (invView.getTitle().equals("§e§lBoutique")){
            event.setCancelled(true);

            if(item.getItemMeta().getDisplayName().equals("§4§lFermer")){
                player.closeInventory();
            }

            if(item.getItemMeta().getDisplayName().equals("§6§lMenu")){
                player.closeInventory();
                player.performCommand("gui open info");
            }

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
                    if(player.hasPermission("group.vip") && !player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "heros", 1000);
                    }else if(player.hasPermission("group.heros")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else if(player.hasPermission("group.legende")){

                    }else {
                        Grade.buyGrade(player, "heros", 2000);
                    }
                }else if(slot == 24){ //Item Légende
                    if(player.hasPermission("group.vip") && !player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "legende", 2500);
                    }else if(player.hasPermission("group.heros") && !player.hasPermission("group.legende")){
                        Grade.buyGrade(player, "legende", 1500);
                    }else if(player.hasPermission("group.legende")){
                        player.sendMessage("Vous avez déja ce grade");
                    }else {
                        Grade.buyGrade(player, "legende", 3500);
                    }
                }
            }

        } else if(invView.getTitle().equals("§eSucces")) {
            event.setCancelled(true);
            if(item.getItemMeta().getDisplayName().equals("§4§lFermer")){
                player.closeInventory();
            }

            if(item.getItemMeta().getDisplayName().equals("§6§lMenu")){
                player.closeInventory();
                player.performCommand("gui open info");
            }


            Sucess sucess = SucessList.getSucessFromName(item.getItemMeta().getDisplayName());
            if(sucess != null) {
                sucess.actionPerformed(player);
                Bukkit.getScheduler().runTaskLater (Main.INSTANCE, () -> {
                    player.closeInventory();
                    player.performCommand("succes");
                } , 8);
            }

        }
}

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        Entity victime = event.getEntity();
        Entity killer = event.getDamager();

        if(victime instanceof Player && killer instanceof Player){

            if(victime.hasPermission("lastarria.player.pvp") && killer.hasPermission("lastarria.player.pvp")){

            } else {
                killer.sendMessage("Votre PvP ou le PvP de votre adversaire est désactivé");
                event.setCancelled(true);
            }
        }


    }
}
