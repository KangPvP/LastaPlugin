package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.CommandBoutique;
import fr.kangpvp.lastarria.commands.CommandRank;
import fr.kangpvp.lastarria.listener.PlayerListener;
import fr.kangpvp.lastarria.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;
    @Override
    public void onEnable() {
        INSTANCE = this;
        //LoadConfigFile
        ConfigManager.getInstance().setup();
        ConfigManager.getInstance().reloadPlayersData();
        ConfigManager.getInstance().savePlayersData();

        Bukkit.getPluginCommand("rank").setExecutor((CommandExecutor) new CommandRank());
        Bukkit.getPluginCommand("boutique").setExecutor((CommandExecutor) new CommandBoutique());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), (Plugin)this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
