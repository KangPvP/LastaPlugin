package fr.kangpvp.lastarria.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static int getTimePlayed(Player player) {

        return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
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

    public static double getMoney(Player player){
        return Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%"));
    }

}
