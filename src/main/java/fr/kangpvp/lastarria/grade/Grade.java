package fr.kangpvp.lastarria.grade;

import fr.kangpvp.lastarria.utils.ConfigManager;
import fr.kangpvp.lastarria.utils.PlayerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Grade {

    private final ItemStack itemGrade;
    private final String name;

    private String displayName;

    private final String color;
    private final int prix;
    private final Player player;
    private int nbHome;
    private List<String> aventages;
    private List<String> content = new ArrayList<String>();

    public Grade(ItemStack itemGrade, String name, String color, int prix, Player player, @Nullable Grade previousGrade, int nbHome, List<String> aventages){

        this.itemGrade = itemGrade;
        this.name = name;
        this.color = color;
        this.prix = prix;
        this.player = player;
        this.nbHome = nbHome;
        this.aventages = aventages;

        this.displayName = color + name.toUpperCase();

        this.content.add("§7 • Pseudo: " + this.color + player.getName());

        this.aventages.forEach(aventage ->
                this.content.add("§7 • " + aventage)
        );

        this.content.add("§7 • Accès à " + this.nbHome + " homes");
        this.content.add("§7 • + 1 clé Légendaire");
        this.content.add(" ");

        Boolean groupPerm = player.hasPermission("group." + this.name);
        String playerGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_grades%");

        if(groupPerm){
            this.content.add("§7Prix: §e§m" + prix + " LC§r §7§o(Vous avez déja ce grade)");
        }else{


            if(this.name.equals("vip")){ //ItemVIP
                this.content.add("§7Prix: §e" + prix + " LC");

            }else if(this.name.equals("heros")){ //ItemHeros
                if(player.hasPermission("group.vip")){ //PlayerPermVIP
                    this.content.add("§7Prix: §e§m" + prix + " LC§r §7Réduction: §e" + (prix - 1000) + " LC");
                }else{
                    this.content.add("§7Prix: §e" + prix + " LC");
                }

            }else if(this.name.equals("legende")){ //ItemLegende
                if(player.hasPermission("group.vip")){ //PlayerPermVIP
                    this.content.add("§7Prix: §e§m" + prix + " LC§r §7Réduction: §e" + (prix - 1000) + " LC");
                }else if(player.hasPermission("group.heros")){ //PlayerPermHeros
                    this.content.add("§7Prix: §e§m" + prix + " LC§r §7Reduction: §e" + (prix - 2000) + " LC");
                }else{
                    this.content.add("§7Prix: §e" + prix + " LC");
                }
            }
        }
    }

    public ItemStack getItem() {
        ItemMeta itemMeta = this.itemGrade.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(this.content);
        this.itemGrade.setItemMeta(itemMeta);

        return this.itemGrade;
    }

    public int getPrix() {
        return prix;
    }

    public static void sellGrade(Player player, String name, int prix){
        Double money = PlayerUtils.getMoney(player);

        if(money >= prix){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + player.getName() + " parent add " + name);
            PlayerUtils.takeMoney(player, prix);
            Bukkit.broadcastMessage("§7Bravo a " + player.getName() + " qui a acheté le grade §e" + name.toUpperCase());
        }else{
            player.sendMessage("Vous n'avez pas assez d'argent");
        }

    }

}
