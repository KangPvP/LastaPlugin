package fr.kangpvp.lastarria.shop;

import fr.kangpvp.lastarria.utils.PlayerUtils;
import org.bukkit.entity.Player;

public class Key {

    public static void buyKey(Player player, int prix, int nbkey, int amount){
        int money = (int) PlayerUtils.getLastaCoin(player);

        if(money >= prix){
            PlayerUtils.removeLastaCoin(player, prix);
            PlayerUtils.giveKey(player, nbkey, amount);
            player.sendMessage("Voila tu as bien recu " + amount + " Clé LootBox");
        }else{
            player.sendMessage("Vous n'avez pas assez de LastaCoin ce pack de Clé LootBox");
        }
    }

}
