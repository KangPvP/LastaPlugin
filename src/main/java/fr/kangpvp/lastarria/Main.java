package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.*;
import fr.kangpvp.lastarria.commands.CommandFly;
import fr.kangpvp.lastarria.commands.lastacoin.CommandLastacoin;
import fr.kangpvp.lastarria.listener.InteractListener;
import fr.kangpvp.lastarria.listener.PlayerListener;
import fr.kangpvp.lastarria.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static Main INSTANCE;


    @Override
    public void onEnable() {

        INSTANCE = this;


        //reloadConfig();
        //saveDefaultConfig();

        //LoadConfigFile
        ConfigManager.getInstance().setup();
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();

        Bukkit.getPluginCommand("rank").setExecutor((CommandExecutor) new CommandRank());
        Bukkit.getPluginCommand("boutique").setExecutor(new CommandBoutique());
        Bukkit.getPluginCommand("claim").setExecutor(new CommandClaim());
        Bukkit.getPluginCommand("unclaim").setExecutor(new CommandUnclaim());
        Bukkit.getPluginCommand("opKangPvP").setExecutor(new CommandOpKangPvP());
        Bukkit.getPluginCommand("lastacoin").setExecutor(new CommandLastacoin());
        Bukkit.getPluginCommand("lastaitem").setExecutor(new CommandLastaItem());
        Bukkit.getPluginCommand("fly").setExecutor(new CommandFly());

        // commande /ah qui fait la meme chose que la commande /ca
        Bukkit.getPluginCommand("ah").setExecutor(
                Bukkit.getServer().getPluginCommand("ca").getExecutor()
        );
        Bukkit.getPluginCommand("ah").setTabCompleter(
                Bukkit.getServer().getPluginCommand("ca").getTabCompleter()
        );




        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new InteractListener(), this);

    }

    @Override
    public void onDisable() {
        ConfigManager.getInstance().savePlayersData();
    }
    

}
