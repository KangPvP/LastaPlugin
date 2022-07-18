package fr.kangpvp.lastarria.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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
            case 1:
                clename = "clé_épique";
                break;
            case 2:
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
    public static int getSuccesPeche(Player player){
        return player.getStatistic(Statistic.FISH_CAUGHT);
    }







}
