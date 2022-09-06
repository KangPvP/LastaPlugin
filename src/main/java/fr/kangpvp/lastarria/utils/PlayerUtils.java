package fr.kangpvp.lastarria.utils;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.sucess.SucessList;
import fr.kangpvp.lastarria.utils.database.DbConnection;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayerUtils {

    public static Economy econ = Main.INSTANCE.getEconomy();

    public static int getTimePlayed(Player player) {

        return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
    }

    public static double getMoney(Player player){
        return econ.getBalance(player);
        //return Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%"));
    }

    public static void addMoney(Player player, double amount) {
        EconomyResponse rep = econ.depositPlayer(player, amount);
        if(!rep.transactionSuccess()){
            System.out.println("[Lastarria] ERROR - addMoney : " + player.getName() + " => " + amount);
        }

        //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + amount);
    }

    public static void takeMoney(Player player, double amount) {
        EconomyResponse rep = econ.withdrawPlayer(player, amount);
        if(!rep.transactionSuccess()){
            System.out.println("[Lastarria] ERROR - addMoney : " + player.getName() + " => " + amount);
        }

        //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " " + amount);
    }

    public static void setMoney(Player player, double amount) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco set " + player.getName() + " " + amount);
    }


    public static double getLastaCoin(Player player){

        String uuid = player.getUniqueId().toString();

        int lastacoin = 0;

        String sql = "SELECT uuid, lastacoin FROM player WHERE uuid = '" + uuid + "'";

        DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();

        try {
            Connection connection = playerConnection.getConnection();

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lastacoin = rs.getInt("lastacoin");
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastacoin;
    }

    public static void addLastaCoin(Player player, int amount){
        int lastacoin = (int) PlayerUtils.getLastaCoin(player);
        int lastacoinAdd = lastacoin + amount;

        setDbLastacoin(player, lastacoinAdd);
    }

    public static void removeLastaCoin(Player player, int amount){
        int lastacoin = (int) PlayerUtils.getLastaCoin(player);
        int lastacoinRemove = lastacoin - amount;

        setDbLastacoin(player, lastacoinRemove);
    }

    public static void setLastaCoin(Player player, int amount){
        int lastacoinSet = amount;

        setDbLastacoin(player, lastacoinSet);
    }

    public static void setDbLastacoin(Player player, int lastacoin){
        String uuid = player.getUniqueId().toString();
        long time = System.currentTimeMillis();

        DbConnection playerConnection = Main.INSTANCE.getDbManager().getPlayerConnection();
        String sql = "UPDATE player SET lastacoin = ?, update_at = ? WHERE uuid = '" + uuid + "'";

        try {
            Connection connection = playerConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, lastacoin);
            preparedStatement.setTimestamp(2, new Timestamp(time));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void giveKey(Player player, int key, int amount) {
        String clename = "";
        switch (key) {
            case 1:
                clename = "clé_commune";
                break;
            case 2:
                clename = "clé_épique";
                break;
            case 3:
                clename = "clé_légendaire";
                break;
            default:
                break;
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + player.getName() + " " + clename + " " + amount);
    }

    public static boolean hasValideSlot(Player player){
        Inventory inv = player.getInventory();
        int check = 0;
        for(ItemStack item : inv.getContents()){
            if(item == null){
                check++;
            }
        }

        return check > 1;
    }

    public static void addPermission(Player player, String permission) {
        // /lp user Shadowhunter130 permission set lastarria.sucess.pêche
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set " + permission.toLowerCase());
    }
    public static void removePermission(Player player, String permission) {
        // /lp user Shadowhunter130 permission set lastarria.sucess.pêche
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset " + permission.toLowerCase());
    }

    //Les Succes
    public static int getSucessPeche(Player player){
        return player.getStatistic(Statistic.FISH_CAUGHT);
    }

    public static int getSucessMinage(Player player){
        return player.getStatistic(Statistic.MINE_BLOCK, Material.STONE);
    }

    public static int getSucessChasse(Player player){
        List<EntityType> monsters = Arrays.asList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.ENDERMAN, EntityType.BLAZE, EntityType.SLIME, EntityType.PILLAGER, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM, EntityType.DROWNED, EntityType.WITCH, EntityType.HUSK, EntityType.GHAST, EntityType.GUARDIAN, EntityType.WITHER_SKELETON, EntityType.SHULKER);
        int num = 0;

        for(int i = 0 ; i < monsters.size() ; i++) {
            num += player.getStatistic(Statistic.KILL_ENTITY, monsters.get(i));
        }
        return num;
    }

    public static int getSucessElvage(Player player){
        List<EntityType> animals = Arrays.asList(EntityType.COW, EntityType.PIG, EntityType.CHICKEN, EntityType.SHEEP, EntityType.HORSE);
        int num = 0;

        for(int i = 0 ; i < animals.size() ; i++) {
            num += player.getStatistic(Statistic.KILL_ENTITY, animals.get(i));
        }
        return num;
    }



    public static int getSucessSaut(Player player){
        return player.getStatistic(Statistic.JUMP);
    }

    public static int getSucessCraft(Player player){
        return player.getStatistic(Statistic.CRAFT_ITEM);
    }

    public static int getSucessSleep(Player player){
        return player.getStatistic(Statistic.SLEEP_IN_BED);
    }

    public static int getSucessTrade(Player player){
        return player.getStatistic(Statistic.TRADED_WITH_VILLAGER);
    }

    public static int getSucessTime(Player player){
        return (player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 3600 / 24);
    }

    public static int getSucessSeigneur(Player player){
        if(player.hasPermission("group.seigneur")){
            return 1;
        } else {
            return 0;
        }
    }

    public static int getSucessAllSucces(Player player){
        SucessList[] sucess = SucessList.values();

        int check = 0;

        for(int i = 0 ; i < sucess.length ; i++) {
            if(sucess[i].getSucess().hasMadeSucess(player)){
                check ++;
            }
        }

        return check;
    }







}
