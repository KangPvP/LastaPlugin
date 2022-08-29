package fr.kangpvp.lastarria.commands.menu.boutique;

import fr.kangpvp.lastarria.shop.Grade;
import fr.kangpvp.lastarria.utils.GuiStyle;
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

import java.util.Arrays;

public class CommandShopgrade implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String label, java.lang.String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);




            if(loc.getBlock().getType().equals(Material.CHEST)){
                Chest ChestGrade = (Chest) loc.getBlock().getState();
                Inventory InvGradeData = ChestGrade.getInventory();

                ItemStack itemVip = InvGradeData.getItem(13);
                ItemStack itemHeros = InvGradeData.getItem(14);
                ItemStack itemLegende = InvGradeData.getItem(15);

                Grade gradeVip = new Grade(itemVip, "vip", "§e", 1000, player, null,3, Arrays.asList(
                        "Accès au §ekit VIP §7toutes les semaines",
                        "Customisations des armors-stands",
                        "Commande: /ec",
                        "Commande: /craft",
                        "Commande: /feed"));

                Grade gradeHeros = new Grade(itemHeros, "heros", "§6", 2000, player, gradeVip,7, Arrays.asList(
                        "Accès au §6kit Heros §7toutes les semaines",
                        "Tous les avantages du grade §eVIP",
                        "Message color, emoji, color sign",
                        "KeepXP 50%",
                        "Commande: /back",
                        "Commande: /furnace",
                        "Commande: /repair"));

                Grade gradeLegende = new Grade(itemLegende, "legende", "§c", 3500, player, gradeHeros,12, Arrays.asList(
                        "Accès au §ckit VIP §7toutes les semaines",
                        "Tous les avantages du grade §6Heros",
                        "KeepXP 100%",
                        "Commande: /fly",
                        "Commande: /anvil",
                        "Commande: /?"));


                ItemStack itemLeftArrow = InvGradeData.getItem(23);
                ItemStack itemTopArrow = InvGradeData.getItem(24);

                Inventory invBoutique = Bukkit.createInventory(null, 45, "§e§lBoutique");
                invBoutique.setItem(20, gradeVip.getItem());
                invBoutique.setItem(22, gradeHeros.getItem());
                invBoutique.setItem(24, gradeLegende.getItem());

                GuiStyle.smallContour(invBoutique); //Black glass panel


                player.openInventory(invBoutique);

            }




            String luckGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");

            PlaceholderAPI.setPlaceholders(player, "%luckperms_in_group%");
 
        }

        return false;
    }

}
