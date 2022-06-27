package fr.kangpvp.lastarria.customitems;

import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

public class CustomItem {

    private ItemStack item;
    private String name;
    private String color;
    private List<String> description;
    private int customModelData;
    private List<CustomItemEnchant> enchants;

    public CustomItem(ItemStack item, String name, String color, List<String> description, int customModelData, @Nullable List<CustomItemEnchant> enchants) {

        this.item = item;
        this.name = name;
        this.color = color;
        this.description = description;
        this.customModelData = customModelData;
        if(enchants != null) {
            this.enchants = enchants;
        }

    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.getName().toLowerCase().replace(" ", "_");
    }


    public ItemStack getItem(int amount) {

        ItemStack itemReturn = this.item;
        final ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName("§" + this.color + this.name);
        this.description.add(0, "");
        for(int i = 0 ; i > this.description.size() ; i++) {
            this.description.set(i, "§r§f" + this.description.get(i));
        }
        meta.setLore(this.description);
        meta.setCustomModelData(this.customModelData);
        if(this.enchants != null) {
            this.enchants.forEach(enchant -> {
                meta.addEnchant(enchant.getEnchant(), enchant.getLevel(), true);
            });
        }

        itemReturn.setItemMeta(meta);
        itemReturn.setAmount(amount);

        return itemReturn;

    }


}
