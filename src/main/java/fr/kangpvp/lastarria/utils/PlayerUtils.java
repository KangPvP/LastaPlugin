package fr.kangpvp.lastarria.utils;

import fr.kangpvp.lastarria.sucess.SucessList;
import me.clip.placeholderapi.PlaceholderAPI;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerUtils {

    public static int getTimePlayed(Player player) {

        return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
    }

    public static double getMoney(Player player){
        return Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%"));
    }

    public static void addMoney(Player player, double amount) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + amount);
    }

    public static void takeMoney(Player player, double amount) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " " + amount);
    }

    public static void setMoney(Player player, double amount) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco set " + player.getName() + " " + amount);
    }



    public static double getLastaCoin(Player player){
        return ConfigManager.pdatacfg.getDouble("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin");
    }

    public static void addLastaCoin(Player player, double amount){
        ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin", getLastaCoin(player) + amount);
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();
    }

    public static void removeLastaCoin(Player player, double amount){
        ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin", getLastaCoin(player) - amount);
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();
    }

    public static void setLastaCoin(Player player, double amount){
        ConfigManager.pdatacfg.set("Joueurs." + player.getUniqueId() + ".data" + ".lastacoin", amount);
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();
    }

    public static void giveKey(Player player, int key, int amount) {
        String clename = "";
        switch (key) {
            case 2:
                clename = "clé_épique";
                break;
            case 3:
                clename = "clé_légendaire";
                break;
            default:
                clename = "clé_commune";
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

    public static int getSuccesSaut(Player player){
        return player.getStatistic(Statistic.JUMP);
    }

    public static int getSuccesCraft(Player player){
        return player.getStatistic(Statistic.CRAFT_ITEM);
    }

    public static int getSuccesSleep(Player player){
        return player.getStatistic(Statistic.SLEEP_IN_BED);
    }

    public static int getSucessTrade(Player player){
        return player.getStatistic(Statistic.TRADED_WITH_VILLAGER);
    }

    public static int getSucessTime(Player player){
        return (player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 3600 / 24);
    }

    public static int getSuccesSeigneur(Player player){
        if(player.hasPermission("group.seigneur")){
            return 1;
        } else {
            return 0;
        }
    }

    public static int getSucessAllSucces(Player player){
        SucessList[] sucess = SucessList.values();

        boolean returning = false;

        for(int i = 0 ; i < sucess.length ; i++) {
            returning = sucess[i].getSucess().hasMadeSucess(player);
        }

        if(returning) {
            return 1;
        } else return 0;

    }







}
