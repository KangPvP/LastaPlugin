package fr.kangpvp.lastarria.utils;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static int getTimePlayed(Player player) {

        return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
    }

}
