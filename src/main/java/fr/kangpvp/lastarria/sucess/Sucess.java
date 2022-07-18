package fr.kangpvp.lastarria.sucess;

import fr.kangpvp.lastarria.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
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
    private int value;

    public Sucess(String name, int money, int key, int maxValue, Material item, String color, String lore) {

        this.name = name;
        this.money = money;
        this.key = key;
        this.maxValue = maxValue;
        this.item = new ItemStack(item);
        this.color = color;
        this.lore = lore;

    }

    public ItemStack getItem(Player player) {

        ItemMeta meta = this.item.getItemMeta();
        meta.setDisplayName("§fSuccès - §" + this.color + this.name);
        List<String> loreList = new ArrayList<>();
        loreList.add("");
        loreList.add("§f§lInformations");
        loreList.add("§7 - " + this.lore);



        String recompense = "§fAucune";
        if(this.money == 0) {
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
        loreList.add("");
        if(player.hasPermission("lastarria.sucess." + this.name)) {
            loreList.add("§7Progrès: §eAccomplit: &f" + this.value);
            meta.addEnchant(Enchantment.DIG_SPEED, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            loreList.add("§7Progrès: &f" + this.value + "§8/" + this.maxValue);
        }

        loreList.add("");
        loreList.add("§eClic-Gauche §7pour compléter le succès");
        meta.setLore(loreList);


        this.item.setItemMeta(meta);
        return this.item;

    }

    public void actionPerformed(Player player) {

        if(this.value >= this.maxValue) {

            if(this.hasMadeSucess(player)) {

                player.sendMessage("Tu as déjà accomplit ce succès.");

            } else {

                PlayerUtils.addMoney(player, this.money);

                if(this.key > 0) {
                    PlayerUtils.giveKey(player, this.key, 1);
                }
                player.sendMessage("§fFélécitation, tu as accomplit le succès §" + this.color + this.name + "§f. Les récompenses ont été récupérées.");

            }
        }


    }

    public Sucess setValue(int value) {
        this.value = value;
        System.out.println("-------------------------------: " + value);
        return this;
    }


    public String getName() {
        return name;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getColor() {
        return color;
    }

    public boolean hasMadeSucess(Player player) {
        return(player.hasPermission("lastarria.sucess." + this.name));
    }


}
