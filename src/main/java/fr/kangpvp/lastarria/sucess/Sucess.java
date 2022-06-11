package fr.kangpvp.lastarria.sucess;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class Sucess {

    private String name;
    private Player player;
    private SucessCondition condition;
    private String color;
    private List<SucessRecompense> recompenses;

    private static Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);
    private static Chest chestGrade = (Chest) loc.getBlock().getState();
    private static Inventory InvGradeData = chestGrade.getInventory();



    public Sucess(String name, Player player, SucessCondition condition, String color, List<SucessRecompense> recompenses) {

        this.name = name;
        this.player = player;
        this.condition = condition;
        this.color = color;
        this.recompenses = recompenses;

    }

    public ItemStack getItem() {
        ItemStack item = new
    }




}
