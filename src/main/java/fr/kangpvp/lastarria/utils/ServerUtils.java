package fr.kangpvp.lastarria.utils;

import jdk.nashorn.internal.ir.BlockStatement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ServerUtils {

    public static final Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);
    
    public static ItemStack getChestData(int i){

        if(!loc.getBlock().getType().equals(Material.CHEST)){return new ItemStack(Material.STONE);}
            Chest ChestGrade = (Chest) loc.getBlock().getState();
            Inventory InvGradeData = ChestGrade.getInventory();

        if(InvGradeData.getItem(i) == null){return new ItemStack(Material.STONE);}

            return InvGradeData.getItem(i);

    }
    
}
