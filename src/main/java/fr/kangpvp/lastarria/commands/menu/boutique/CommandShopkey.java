package fr.kangpvp.lastarria.commands.menu.boutique;

import fr.kangpvp.lastarria.utils.GuiStyle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandShopkey implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Inventory invShopKey = Bukkit.createInventory(null, 45, "§e§lClé LootBox");

            invShopKey.setItem(11, onKey(1, 1, 75));
            invShopKey.setItem(20, onKey(1, 4, 225));
            invShopKey.setItem(29, onKey(1, 10, 450));

            invShopKey.setItem(13, onKey(2, 1, 150));
            invShopKey.setItem(22, onKey(2, 4, 450));
            invShopKey.setItem(31, onKey(2, 10, 900));

            invShopKey.setItem(15, onKey(3, 1, 300));
            invShopKey.setItem(24, onKey(3, 4, 900));
            invShopKey.setItem(33, onKey(3, 10, 1800));

            GuiStyle.smallContour(invShopKey);

            player.openInventory(invShopKey);

        }


        return false;
    }

    public ItemStack onKey(int id, int nb, int prix){

        ItemStack key = new ItemStack(Material.NAUTILUS_SHELL, nb);
        ItemMeta metaKey = key.getItemMeta();
        assert metaKey != null;
        metaKey.setCustomModelData(id);

        switch (id) {
            case 1:
                metaKey.setDisplayName("§fClé §a§lCommune");
                break;
            case 2:
                metaKey.setDisplayName("§fClé §5§lÉpique");
                break;
            case 3:
                metaKey.setDisplayName("§fClé §6§lLégendaire");
                break;
            default:
                break;
        }
        metaKey.setLore(Arrays.asList(" ", "§7Prix: §e" + prix + " LC"));
        key.setItemMeta(metaKey);

        return key;
    }

}
