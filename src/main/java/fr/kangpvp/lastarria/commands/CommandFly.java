package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.advancement.AdvancementDisplayType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(Main.INSTANCE.flying.contains(player)) {
                player.setAllowFlight(false);
                player.sendMessage("§3Tu as désactivé le mode fly");
                Main.INSTANCE.flying.remove(player);
            }
            else if (!Main.INSTANCE.flying.contains(player)){
                player.setAllowFlight(true);
                player.setFlySpeed((float) 0.06);
                player.sendMessage("§3Tu as activé le mode fly");
                Main.INSTANCE.flying.add(player);
            }
        }

        return false;
    }
}
