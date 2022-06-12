package fr.kangpvp.lastarria.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class CommandBoutique implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String label, java.lang.String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            player.getLocation().getChunk();
            ItemStack myAwesomeSkull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta myAwesomeSkullMeta = (SkullMeta) myAwesomeSkull.getItemMeta();
            UUID uuid = UUID.fromString("069741bd-360b-4a0c-b99f-acee9ea5d872");
            myAwesomeSkullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            myAwesomeSkull.setItemMeta(myAwesomeSkullMeta);

            Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);

            if(loc.getBlock().getType().equals(Material.CHEST)){
                Chest ChestGrade = (Chest) loc.getBlock().getState();
                Inventory InvGradeData = ChestGrade.getInventory();

                ItemStack itemVip = InvGradeData.getItem(13);
                ItemStack itemHeros = InvGradeData.getItem(14);
                ItemStack itemLegende = InvGradeData.getItem(15);

                Inventory invBoutique = Bukkit.createInventory(null, 54, "§eBoutique");
                invBoutique.setItem(20, itemVip);
                invBoutique.setItem(22, itemHeros);
                invBoutique.setItem(24, itemLegende);

                player.openInventory(invBoutique);

            }




            String luckGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");

            PlaceholderAPI.setPlaceholders(player, "%luckperms_in_group%");
 
        }

        return false;
    }

}
