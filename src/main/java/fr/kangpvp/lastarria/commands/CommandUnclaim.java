package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUnclaim implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Chunk chunk = player.getLocation().getChunk();

            String chunkID = chunk.getX() + "." + chunk.getZ();

            if (!Main.INSTANCE.getOwner(chunkID).equals(player.getUniqueId())) {
                player.sendMessage("§3Ce chunk appartient déjà à quelqu'un");
            }else {
                Main.INSTANCE.removeChunk(chunkID, player.getUniqueId());
                player.sendMessage("§3Tu as unclaim ce chunk");
            }
        }

        return true;
    }
}
