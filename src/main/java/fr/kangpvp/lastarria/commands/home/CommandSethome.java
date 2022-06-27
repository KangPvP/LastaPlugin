package fr.kangpvp.lastarria.commands.home;

import fr.kangpvp.lastarria.titre.Titre;
import fr.kangpvp.lastarria.titre.Titres;
import fr.kangpvp.lastarria.utils.ConfigManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CommandSethome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

          //  if(!player.getLocation().getWorld().equals("world")) { return false; }
          //  if(!player.getLocation().getWorld().equals("world_nether")) { return false; }
          //  if(!player.getLocation().getWorld().equals("world_the_end")){ return false; }

            String titreName =  PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_titres%");

            player.sendMessage(titreName + " || OK1");

            Titre titre = Titres.getGradeFromName(titreName.toLowerCase());

            player.sendMessage(titre + " || OK2");

            int nbhomes = titre.getHomes();

            player.sendMessage(nbhomes + " || OK2");

            UUID uuid = player.getUniqueId();

            String world = player.getLocation().getWorld().getName();
            Double x = player.getLocation().getX();
            Double y = player.getLocation().getY();
            Double z = player.getLocation().getZ();


            String key = "Joueurs." + uuid + ".data.homes." + args[0];

            ConfigManager.pdatacfg.set(key + ".world", world);
            ConfigManager.pdatacfg.set(key + ".x", x);
            ConfigManager.pdatacfg.set(key + ".y", y);
            ConfigManager.pdatacfg.set(key + ".z", z);
            ConfigManager.getInstance().savePlayersData();
            ConfigManager.getInstance().reloadPlayersData();

            player.sendMessage("Ok final" + " || OK2");

        }



        return false;
    }
}
