package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.CommandBoutique;
import fr.kangpvp.lastarria.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static File dataFolder;

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("boutique").setExecutor((CommandExecutor) new CommandBoutique());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), (Plugin)this);

        dataFolder = getDataFolder();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
