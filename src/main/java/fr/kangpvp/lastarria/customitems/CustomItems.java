package fr.kangpvp.lastarria.customitems;

import fr.kangpvp.lastarria.titre.Titres;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CustomItems {

    DIAMOND_HEARTH(new CustomItem(new ItemStack(Material.DIAMOND), "Diamond Heart", "b", Arrays.asList(
            "",
            "&fPermet d'obtenir un coeur supplémentaire."
    ), 1)),
    VAMPIRE_SWORD(new CustomItem(new ItemStack(Material.DIAMOND_SWORD), "Vampire Sword", "e", Arrays.asList(
            "",
            "§fUne épée permettant de récupérer en pv",
            "§fune partie des dégats infligés avec cette arme"
    ), 1));

    private CustomItem item;

    CustomItems(CustomItem item) {
        this.item = item;
    }

    public static CustomItem getCustomItemFromId(String id) {

        CustomItem returning = null;
        CustomItems[] itemList = CustomItems.values();

        for(int i = 0 ; i < itemList.length ; i++) {
            if(itemList[i].getCustomItem().getId().equals(id)) {
                returning = itemList[i].getCustomItem();
            }
        }

        return returning;

    }

    public static Boolean doesCustomItemExist(CustomItem customItem) {
        return CustomItems.getCustomItemFromId(customItem.getName()) == null;
    }

    public CustomItem getCustomItem() {
        return item;
    }

}
