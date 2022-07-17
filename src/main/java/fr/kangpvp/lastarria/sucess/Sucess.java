package fr.kangpvp.lastarria.sucess;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Sucess {

    private String name;
    private int money;
    private int key;
    private int maxValue;
    private ItemStack item;
    private String color;
    private String lore;

    public Sucess(String name, int money, int key, int maxValue, ItemStack item, String color, String lore) {

        this.name = name;
        this.money = money;
        this.key = key;
        this.maxValue = maxValue;
        this.item = item;
        this.color = color;
        this.lore = lore;

    }

    public ItemStack getItem() {

        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName("§fSuccès - " + this.color + this.name);
        List<String> loreList = new ArrayList<>();
        loreList.add("");
        loreList.add("§f§lInformations");
        loreList.add("&7 - " + this.lore);



        String recompense = "aucune";
        if(this.key != 0 && this.money == 0) {
            switch (this.key) {

                case 1:
                    recompense = "§aClé Commune";
                    break;
                case 2:
                    recompense = "§5Clé Epique";
                    break;
                case 3:
                    recompense = "§6Clé Légendaire";
                    break;

                default:

                    break;

            }
        } else if(this.key == 0 && this.money != 0) {
            recompense = "§e" + this.money + "$";
        }




        loreList.add("§7 - Récompense: " + recompense);


    }

    public void actionPerformed() {}


    public String getName() {
        return name;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getColor() {
        return color;
    }
}
