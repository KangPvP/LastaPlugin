package fr.kangpvp.lastarria.sucess;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class SucessCondition {

    private Sucess sucess;
    private int proges;

    public SucessCondition(Sucess sucess, Player player, int proges) {

        this.sucess = sucess;
        this.proges = proges;

        String name = this.sucess.getName();
        String color = this.sucess.getColor();
        int maxValue = this.sucess.getMaxValue();

        if(proges >= maxValue){
            if(player.hasPermission("lastarria." + name)){
                player.sendMessage("Vous avez déja complèter ce succes");
            } else {
                player.sendMessage("Bravo vous venez de complèter le succes " + color + name);
            }
        }else{

        }








    }

}
