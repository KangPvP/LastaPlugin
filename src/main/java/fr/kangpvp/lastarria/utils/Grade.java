package fr.kangpvp.lastarria.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class Grade {

    private ItemStack item;
    private String name;
    private String color;
    private List<String> content;
    private String previousName;

    public Grade(int slot, String name, String color, int hour, int price, Grade previousGrade, List<String> avantages) {
        Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);
        Chest ChestGrade = (Chest) loc.getBlock().getState();
        Inventory InvGradeData = ChestGrade.getInventory();

        if(!loc.getBlock().getType().equals(Material.CHEST)) return;

        this.item = item;

        this.name = "§" + color + "§l" + name;

        if(previousGrade == null){
            this.previousName = "Aucun";

        } else {
            this.previousName = previousGrade.getName();
        }

        this.content = Lists.newArrayList(
                "",
                "§c§nConditions",
                "§7 • Titre: " + this.previousName,
                "§7 • " + hour + " heures de jeu",
                "§7 • prix",
                "",
                "§a§nAvantages"
        );

        avantages.forEach((avantage) -> {
            avantage = "§7 • " + avantage;
            this.content.add(avantage);
        });

        this.content.add("");
        this.content.add("§eClic gauche §fpour acheter le grade " + this.name);


    }

    public String getName() {
        return this.name;
    }

    public List<String> getContent() {
        return this.content;
    }

    public ItemMeta getMeta() {

        ItemMeta meta = this.item.getItemMeta();

        meta.setDisplayName(this.name);
        meta.setLore(this.content);
        return meta;

    }
}
