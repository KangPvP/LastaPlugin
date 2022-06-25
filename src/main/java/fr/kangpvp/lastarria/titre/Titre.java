package fr.kangpvp.lastarria.titre;


import com.google.common.collect.Lists;
import fr.kangpvp.lastarria.utils.PlayerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Titre {

    private ItemStack item;
    private String name;
    private String nameWithoutColor;
    private String color;
    private int price;
    private int hours;
    private List<String> content;
    private String previousName;
    private Titre previousTitre;


    public Titre(int slot, String name, String color, int hours, int price, Titre previousTitre, List<String> avantages) {

        // Obtenir l'item du coffre (la tete)
        Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);
        Chest ChestGrade = (Chest) loc.getBlock().getState();
        Inventory InvGradeData = ChestGrade.getInventory();

        if(!loc.getBlock().getType().equals(Material.CHEST)) return;

        // Définir toutes les variables
        this.nameWithoutColor = name;
        this.item = InvGradeData.getItem(slot);
        this.previousTitre = previousTitre;
        this.name = "§" + color + "§l" + name;
        this.price = price;
        this.hours = hours;

        if(previousTitre == null){
            this.previousName = "§fTitre - " + "§8§lVagabond";
        } else {
            this.previousName = previousTitre.getName();
        }

        // Créer la desc de l'item qui s'adapte aux variables
        this.content = Lists.newArrayList(
                "                    ",
                "§" + color + "§lAvantages",
                "§7 • Préfix : §8[" + this.name + "§8] §7Pseudo"
        );

        avantages.forEach((avantage) -> {
            avantage = "§7 • " + avantage;
            this.content.add(avantage);
        });
        this.content.add("     ");
        this.content.add("§" + color + "§lConditions");
        this.content.add("§7 • Posseder le §f§ltitre: " + this.previousName);
        this.content.add("§7 • Temps de jeu Requis: §f"  + hours);
        this.content.add("§7 • Prix: §f" + price + "$");

        this.content.add("");
        this.content.add("§" + color + "Clic gauche §7pour acheter ce Titre");
    }

    // fonctions pour get les noms, avec ou sans couleur
    public String getName() {
        return this.name;
    }

    public String getNameWithoutColor() {
        return this.nameWithoutColor;
    }

    // sert a rien pour l'instant, mais sert a obtenir la liste de la description de l'item
    public List<String> getContent() {
        return this.content;
    }

    // pour obtenir l'item (pour le mettre dans l'inventaire de la boutique)
    public ItemStack getItem() {

        ItemMeta itemMeta = this.item.getItemMeta();
        itemMeta.setDisplayName("§fTitre - " + this.name);
        itemMeta.setLore(this.content);
        this.item.setItemMeta(itemMeta);

        return this.item;
    }

    public Titre getPreviousGrade() {
        return this.previousTitre;
    }

    //Quand item est cliqué
    public void performAction(Player player) {

        int hoursPlayed = (int) PlayerUtils.getTimePlayed(player) / 20 / 3600;

        double money = Double.parseDouble((String) PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%"));

        if(player.hasPermission("group." + this.nameWithoutColor.toLowerCase())){
            player.sendMessage("§7Vous avez déja le grade " + this.name);
            return;
        }

        if(!player.hasPermission("group." + this.previousName.substring(4).toLowerCase())){
            player.sendMessage("§7Vous devez d'abord avoir le titre " + this.previousName + " §7pour acheter le titre " + this.name);
            return;
        }

        if(hoursPlayed < this.hours){
            player.sendMessage("§7Tu n'as pas assez d'heures de jeu pour débloquer le titre " + this.name);
            return;
        }

        if(money < this.price) {
            player.sendMessage("§7Tu n'as pas assez d'argent pour débloquer le titre " + this.name);
            return;
        }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + player.getName() + " parent add " + this.nameWithoutColor.toLowerCase());
            PlayerUtils.takeMoney(player, this.price);
            Bukkit.broadcastMessage("§7Bravo a " + player.getName() + " §7qui a débloqué le Titre " + this.name);
    }

}
