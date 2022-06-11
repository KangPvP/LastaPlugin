package fr.kangpvp.lastarria.sucess;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class SucessRecompense {

    private int money;
    private ItemStack item;
    private Player player;

    public SucessRecompense(int money, ItemStack item, Player player) {
        this.money = money;
        this.item = item;
        this.player = player;
    }

    public SucessRecompense(int money, Player player) {
        this.money = money;
        this.player = player;
    }

    public SucessRecompense(ItemStack item, Player player) {
        this.item = item;
        this.player = player;

    }

}
