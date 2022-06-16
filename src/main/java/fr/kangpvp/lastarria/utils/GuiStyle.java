package fr.kangpvp.lastarria.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiStyle {
    public static void contour(Inventory inv){

        ItemStack blackWindows = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackWindow = blackWindows.getItemMeta();
        blackWindow.setDisplayName(" ");
        blackWindows.setItemMeta(blackWindow);

        ItemStack menu = ServerUtils.getChestData(25);
        ItemStack fermer = ServerUtils.getChestData(26);

        inv.setItem(0, blackWindows);
        inv.setItem(1, blackWindows);
        inv.setItem(2, blackWindows);
        inv.setItem(3, blackWindows);
        inv.setItem(4, blackWindows);
        inv.setItem(5, blackWindows);
        inv.setItem(6, blackWindows);
        inv.setItem(7, blackWindows);
        inv.setItem(8, blackWindows);
        inv.setItem(9, blackWindows);

        inv.setItem(17, blackWindows);
        inv.setItem(26, blackWindows);
        inv.setItem(35, blackWindows);
        inv.setItem(44, blackWindows);
        inv.setItem(53, fermer); //fermer le menu

        inv.setItem(52, blackWindows);
        inv.setItem(51, blackWindows);
        inv.setItem(50, blackWindows);
        inv.setItem(49, blackWindows);
        inv.setItem(48, blackWindows);
        inv.setItem(47, blackWindows);
        inv.setItem(46, blackWindows);
        inv.setItem(45, menu); //retour au menu principal

        inv.setItem(36, blackWindows);
        inv.setItem(27, blackWindows);
        inv.setItem(18, blackWindows);
    }

}
