package fr.kangpvp.lastarria.customitems;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemEnchant {

    private Enchantment enchant;
    private int level;

    public CustomItemEnchant(Enchantment enchant, int level) {
        this.enchant = enchant;
        this.level = level;
    }

    public ItemMeta getMeta(ItemMeta previousMeta) {
        previousMeta.addEnchant(this.enchant, this.level, true);
        return previousMeta;
    }

    public Enchantment getEnchant() {
        return this.enchant;
    }

    public int getLevel() {
        return this.level;
    }

}
