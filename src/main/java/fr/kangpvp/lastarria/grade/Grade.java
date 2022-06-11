package fr.kangpvp.lastarria.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import fr.kangpvp.lastarria.grade.Grades;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class Grade {

    private ItemStack item;
    private String name;
    private String nameWithoutColor;
    private String color;
    private int price;
    private int hours;
    private List<String> content;
    private String previousName;
    private Grade previousGrade;


    public Grade(int slot, String name, String color, int hours, int price, Grade previousGrade, List<String> avantages) {

        // Obtenir l'item du coffre (la tete)
        Location loc = new Location(Bukkit.getWorld("Aragnok"), 736, 144, 15);
        Chest ChestGrade = (Chest) loc.getBlock().getState();
        Inventory InvGradeData = ChestGrade.getInventory();

        if(!loc.getBlock().getType().equals(Material.CHEST)) return;

        // Définir toutes les variables
        this.nameWithoutColor = name;
        this.item = InvGradeData.getItem(slot);
        this.previousGrade = previousGrade;
        this.name = "§" + color + "§l" + name;
        this.price = price;
        this.hours = hours;

        if(previousGrade == null){
            this.previousName = "§8§lVagabond";
        } else {
            this.previousName = previousGrade.getName();
        }

        // Créer la desc de l'item qui s'adapte aux variables
        this.content = Lists.newArrayList(
                "",
                "§c§nConditions",
                "§7 • Titre: " + this.previousName,
                "§7 • " + hours + " heures de jeu",
                "§7 • prix" + price,
                "",
                "§a§nAvantages"
        );

        // on y ajoute la liste des avantages
        avantages.forEach((avantage) -> {
            avantage = "§7 • " + avantage;
            this.content.add(avantage);
        });

        this.content.add("");
        this.content.add("§eClic gauche §fpour acheter le grade " + this.name);

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
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(this.content);
        this.item.setItemMeta(itemMeta);

        List<String> listTest = new ArrayList<String>();

        return this.item;

    }

    public Grade getPreviousGrade() {
        return this.previousGrade;
    }

    // fonction pour quand un item de la boutique de titres sera cliquée
    public void performAction(Player player) {

        // on get toutes les conditions

        int hoursPlayed = (int) PlayerUtils.getTimePlayed(player) / 20 / 3600;

        //%vault_eco_balance%

        double money = Double.parseDouble((String) PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%"));

        String luckGrade = PlaceholderAPI.setPlaceholders(player, "%luckperms_inherited_groups%"); //string List
        System.out.println(luckGrade);
        Grade actualGrade = Grades.getGradeFromName(luckGrade); //bug actualGrade = null
        System.out.println("Test 756");
        if(luckGrade.contains(this.nameWithoutColor.toLowerCase())) {
            player.sendMessage("Vous avez déja ce grade");
            return;
        }
        System.out.println("Test 762");
        System.out.println(this.previousName.substring(4).toString());
        if(luckGrade.contains(this.previousName.substring(4).toString())) {
            player.sendMessage("C'est bien vous avez le grade Précédant celui ci");
            System.out.println("Test 773");
            // on test si le joueur a bien réuni les conditions
            if(hoursPlayed >= this.hours && money >= this.price) {
                System.out.println("Test 774");
                // on add le grade + on send le message de réussite
                Bukkit.broadcastMessage("luckperms user " + player.getName() + " parent add " + this.nameWithoutColor.toLowerCase());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + player.getName() + " parent add " + this.nameWithoutColor.toLowerCase());
                Bukkit.broadcastMessage("&eSuper! " + player.getDisplayName() + " a débloqué le Titre de " + this.name + ".");

            } else {
                // on send le message d'erreur
                player.sendMessage("&7Tu n'as pas les conditions nécéssaires pour acheter ce grade !");
            }
        }
    }

}
