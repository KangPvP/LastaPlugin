package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import fr.kangpvp.lastarria.utils.ClaimManager;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClaim implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Chunk chunk = player.getLocation().getChunk();

            if (ClaimManager.isChunkClaimed(chunk)) {
                player.sendMessage("§3Ce chunk est déja claim !");
            } else {
                ClaimManager.claimChunk(chunk, player);
                player.sendMessage("§3Vous avez claim un chunk.");
            }
        }

        return true;
    }
}
