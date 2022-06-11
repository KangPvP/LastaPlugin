package fr.kangpvp.lastarria.grade;


import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        this.item = InvGradeData.getItem(slot);

        this.name = "§" + color + "§l" + name;

        if(previousGrade == null){
            this.previousName = "§8§lVilain";

        } else {
            this.previousName = previousGrade.getName();
        }

        this.content = Lists.newArrayList(
                "",
                "§c§nConditions",
                "§7 • Titre: " + this.previousName,
                "§7 • " + hour + " heures de jeu",
                "§7 • prix" + price,
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

    public String getNameWithoutColor() {
        return name;
    }

    public List<String> getContent() {
        return this.content;
    }

    public ItemStack getItem() {
        ItemMeta itemMeta = this.item.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.content);
        this.item.setItemMeta(itemMeta);

        List<String> listTest = new ArrayList<String>();

        return this.item;

    }
}
