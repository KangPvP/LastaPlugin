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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
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

            String titre = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
            //getLa couleur du grade du player


            if(loc.getBlock().getType().equals(Material.CHEST)){
                Chest ChestGrade = (Chest) loc.getBlock().getState();
                Inventory InvGradeData = ChestGrade.getInventory();

                ItemStack itemVip = InvGradeData.getItem(13);
                ItemStack itemHeros = InvGradeData.getItem(14);
                ItemStack itemLegende = InvGradeData.getItem(15);

                ItemMeta itemMetaVip = itemVip.getItemMeta();
                itemMetaVip.setDisplayName("§fGrade §7- §eVIP");
                itemMetaVip.setLore(Arrays.asList(
                        "§7 • Pseudo: §e" + player.getName(),
                        "§7 • Accès au §ekit VIP §7toutes les semaines",
                        "§7 • Customisations des armors-stands",
                        "§7 • Commande: /ec",
                        "§7 • Commande: /craft",
                        "§7 • Commande: /feed",
                        "§7 • Accès à 3 homes",
                        "§7 • + 1 clé Légendaire",
                        "",
                        "§7Prix: §e1000 LC §7(LastaCoin)"

                        ));
                itemVip.setItemMeta(itemMetaVip);

                ItemMeta itemMetaHeros = itemHeros.getItemMeta();
                itemMetaHeros.setDisplayName("§fGrade §7- §6Heros");
                itemMetaHeros.setLore(Arrays.asList(
                        "§7 • Pseudo: §6" + player.getName(),
                        "§7 • Accès au §6kit Heros §7toutes les semaines",
                        "§7 • Tous les avantages du grade §eVIP",
                        "§7 • Message color, emoji, color sign",
                        "§7 • KeepXP 50%",
                        "§7 • Commande: /back",
                        "§7 • Commande: /furnace",
                        "§7 • Commande: /repair",
                        "§7 • Accès à 7 homes",
                        "§7 • + 1 clé Légendaire",
                        "",
                        "§7Prix: §e2000 LC §7(LastaCoin)"

                ));
                itemHeros.setItemMeta(itemMetaHeros);

                ItemMeta itemMetaLegende = itemLegende.getItemMeta();
                itemMetaLegende.setDisplayName("§fGrade §7- §cLegende");
                itemMetaLegende.setLore(Arrays.asList(
                        "§7 • Pseudo: §6" + player.getName(),
                        "§7 • Accès au §6kit VIP §7toutes les semaines",
                        "§7 • Tous les avantages du grade §6Heros",
                        "§7 • KeepXP 100%",
                        "§7 • Commande: /fly",
                        "§7 • Commande: /anvil",
                        "§7 • Commande: /?",
                        "§7 • Accès à 12 homes",
                        "§7 • + 1 clé Légendaire",
                        "",
                        "§7Prix: §e3500 LC §7(LastaCoin)"

                ));
                itemLegende.setItemMeta(itemMetaLegende);





                ItemStack itemLeftArrow = InvGradeData.getItem(23);
                ItemStack itemTopArrow = InvGradeData.getItem(24);

                Inventory invBoutique = Bukkit.createInventory(null, 54, "§eBoutique");
                invBoutique.setItem(20, itemVip);
                invBoutique.setItem(22, itemHeros);
                invBoutique.setItem(24, itemLegende);
                invBoutique.setItem(30, itemLeftArrow);
                invBoutique.setItem(32, itemLeftArrow);
                invBoutique.setItem(40, itemTopArrow);


                player.openInventory(invBoutique);

            }




            String luckGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%");

            PlaceholderAPI.setPlaceholders(player, "%luckperms_in_group%");
 
        }

        return false;
    }

}
