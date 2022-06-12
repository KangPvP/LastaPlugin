package fr.kangpvp.lastarria.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

            String luckGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");

            PlaceholderAPI.setPlaceholders(player, "%luckperms_in_group%");
 
        }

        return false;
    }

}
