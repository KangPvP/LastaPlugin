package fr.kangpvp.lastarria.utils;

import fr.kangpvp.lastarria.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClaimManager {
    // w/nom du monde/x/20/z/30

    public static HashMap<String, String> chunks = new HashMap<>();
    public static String createChunkId(Chunk chunk) {

        // on crée un id avec le monde et les coordonées du chunk, séparées par des séparateurs ("w/","/w/" et "/y/")
        return "w/" + chunk.getWorld().getName() + "/x/" + chunk.getX() + "/z/" + chunk.getZ();
    }

    public static Chunk getChunkFromId(String id) {
        // tout ce code c'est pour récuperer les éléments qui nous intéressent dans l'id

        // ici on compte le nombre de caractères avant le "/x/" et pareil pour le "/y/"
        int xposFromStr = id.indexOf("/x/");
        int yposFromStr = id.indexOf("/z/");
        // ici on récupère le nom du world grace a la variable au dessus
        String worldName = id.substring(2, xposFromStr);
        World world = Bukkit.getServer().getWorld(worldName);

        // ici on récupère les coordonnées
        int xpos = Integer.parseInt(id.substring(xposFromStr + 3, yposFromStr));
        int ypos = Integer.parseInt(id.substring(yposFromStr + 3));

        // là on n'a plus qu'à return le chunk dans le monde extrait de l'id aux coordonnées extraites de l'id
        return world.getChunkAt(xpos, ypos);
    }

    public static void claimChunk(Chunk chunk, Player player) {

        if(ClaimManager.isChunkClaimed(chunk)) return;

        String id = ClaimManager.createChunkId(chunk);
        chunks.put(id, player.getUniqueId().toString());
    }

    public static void unclaimChunk(Chunk chunk) {

        if(ClaimManager.isChunkClaimed(chunk)) {
            chunks.remove(ClaimManager.createChunkId(chunk));
        }

    }


    public static boolean isChunkClaimed(Chunk chunk) {

        return chunks.containsKey(ClaimManager.createChunkId(chunk));
    }

    public static Player getChunkOwner(Chunk chunk) {

        return Bukkit.getPlayer(UUID.fromString(chunks.get(ClaimManager.createChunkId(chunk)) ));
    }

    public static boolean isPlayerChunkOwner(Player player, Chunk chunk) {
        return chunks.get(ClaimManager.createChunkId(chunk)).equals(player.getUniqueId().toString());
    }

}
