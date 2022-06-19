package fr.kangpvp.lastarria.customitems;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItem {

    private ItemStack item;
    private String name;
    private String color;
    private List<String> description;
    private int customModelData;


    public CustomItem(ItemStack item, String name, String color, List<String> description, int customModelData) {

        this.item = item;
        this.name = name;
        this.color = color;
        this.description = description;
        this.customModelData = customModelData;

    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.getName().toLowerCase().replace(" ", "_");
    }


    public ItemStack getItem(int amount) {

        ItemStack itemReturn = this.item;
        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName("§" + this.color + this.name);
        meta.setLore(this.description);
        meta.setCustomModelData(this.customModelData);

        itemReturn.setItemMeta(meta);
        itemReturn.setAmount(amount);

        return itemReturn;

    }


}
