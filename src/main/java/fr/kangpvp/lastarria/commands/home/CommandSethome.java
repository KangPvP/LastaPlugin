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

            if(args.length != 1){
                return false;
            }

            int nbhomes = getMaxHome(player);

            player.sendMessage(nbhomes + " || OK2");

            //if(nbhomeactuel < nbhomes)

            UUID uuid = player.getUniqueId();

           // ConfigManager.pdatacfg.get("Joueurs." + uuid + ".data.homes");

            String key = "Joueurs." + uuid + ".data.homes." + args[0];

            ConfigManager.pdatacfg.set(key, player.getLocation());

            ConfigManager.getInstance().savePlayersData();
            ConfigManager.getInstance().reloadPlayersData();

            player.sendMessage("Ok final" + " || OK2");

        }



        return false;
    }

    public int getMaxHome(Player player){
        String titreName =  PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_titres%");
        String gradeName = PlaceholderAPI.setPlaceholders(player, "%luckperms_first_group_on_tracks_grades%");   //if gradeName == null  =>  gradeName == ""

        player.sendMessage(titreName + " ||" + gradeName + ".");

        Titre titre = Titres.getGradeFromName(titreName.toLowerCase());

        player.sendMessage(titre + " || OK2");

        if(gradeName.equals("VIP")) {
            return titre.getHomes() + 3;
        }else if(gradeName.equals("Heros")){
            return titre.getHomes() + 7;
        }else if(gradeName.equals("Legende")){
            return titre.getHomes() + 12;
        }else {
            return titre.getHomes();
        }

    }


}
