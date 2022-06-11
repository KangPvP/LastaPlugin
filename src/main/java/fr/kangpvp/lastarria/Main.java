package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.CommandBoutique;
import fr.kangpvp.lastarria.commands.CommandClaim;
import fr.kangpvp.lastarria.commands.CommandRank;
import fr.kangpvp.lastarria.commands.CommandUnclaim;
import fr.kangpvp.lastarria.listener.InteractListener;
import fr.kangpvp.lastarria.listener.PlayerListener;
import fr.kangpvp.lastarria.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;

    public static HashMap<String, UUID> chunks = new HashMap<>();

    @Override
    public void onEnable() {

        INSTANCE = this;

        reloadConfig();
        saveDefaultConfig();

        //LoadConfigFile
        ConfigManager.getInstance().setup();
        ConfigManager.getInstance().reloadPlayersData();
        ConfigManager.getInstance().savePlayersData();

        getCommand("rank").setExecutor(new CommandRank());
        getCommand("boutique").setExecutor(new CommandBoutique());
        getCommand("claim").setExecutor(new CommandClaim());
        getCommand("unclaim").setExecutor(new CommandUnclaim());

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new InteractListener(), this);

    }

    @Override
    public void onDisable() {

    }
    public void addChunk(String chunk, UUID owner) {
        chunks.put(chunk, owner);
    }

    public boolean isChunk(String chunk) {
        return chunks.containsKey(chunk);
    }

    public UUID getOwner(String chunk) {
        return chunks.get(chunk);
    }

    public void removeChunk(String chunk, UUID owner) {
        chunks.remove(chunk, owner);
    }
}
