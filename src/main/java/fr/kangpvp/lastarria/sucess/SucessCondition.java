package fr.kangpvp.lastarria.sucess;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class SucessCondition {

    private String name;
    private int value;
    private Statistic statistic;
    private int amount;
    private int modifier = 1;
    private String description;

    public SucessCondition(String name, int value, int amount, int modifier, String description) {

        this.name = name;
        this.value = value;
        this.amount = amount;
        this.modifier = modifier;
        this.description = description;

    }

    public boolean conditionValidated() {
        return this.value >= this.amount;
    }

    public double getProgress() {
        return this.value / this.modifier;
    }

    public String getDescription() {
        String finalDescription = this.getProgress() + "/" + (this.amount / this.modifier) + this.description;
        if(this.conditionValidated()) {
            return "§2" + finalDescription;
        } else {
            return "§c" + finalDescription;
        }
    }

}
