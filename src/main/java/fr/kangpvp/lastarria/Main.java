package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.*;
import fr.kangpvp.lastarria.commands.CommandFly;
import fr.kangpvp.lastarria.commands.home.CommandSethome;
import fr.kangpvp.lastarria.commands.lastacoin.CommandLastacoin;
import fr.kangpvp.lastarria.listener.InteractListener;
import fr.kangpvp.lastarria.listener.PlayerListener;
import fr.kangpvp.lastarria.utils.ConfigManager;
import fr.kangpvp.lastarria.utils.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class Main extends JavaPlugin {

    public static Main INSTANCE;
    public static RegionManager portailTp;
    public static HashMap<String, Long> cooldowns = new HashMap<>();

    @Override
    public void onEnable() {

        INSTANCE = this;


        //reloadConfig();
        //saveDefaultConfig();

        //LoadConfigFile
        ConfigManager.getInstance().setup();
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();

        Bukkit.getPluginCommand("titre").setExecutor((CommandExecutor) new CommandRank());
        Bukkit.getPluginCommand("boutique").setExecutor(new CommandBoutique());
        Bukkit.getPluginCommand("claim").setExecutor(new CommandClaim());
        Bukkit.getPluginCommand("unclaim").setExecutor(new CommandUnclaim());
        Bukkit.getPluginCommand("opKangPvP").setExecutor(new CommandOpKangPvP());
        Bukkit.getPluginCommand("opNotVape").setExecutor(new CommandNotVap());
        Bukkit.getPluginCommand("lastacoin").setExecutor(new CommandLastacoin());
        Bukkit.getPluginCommand("lastaitem").setExecutor(new CommandLastaItem());
        Bukkit.getPluginCommand("rtp").setExecutor(new CommandRTP());
        Bukkit.getPluginCommand("fly").setExecutor(new CommandFly());
        Bukkit.getPluginCommand("sethomesss").setExecutor(new CommandSethome());
        Bukkit.getServer().getPluginCommand("sethome").setExecutor(new CommandSethome());
        Bukkit.getServer().getPluginCommand("spawn").setExecutor(new CommandSpawn());

        Bukkit.getPluginCommand("tuto").setExecutor(new CommandTuto());
        Bukkit.getPluginCommand("succes").setExecutor(new CommandSucces());
        //Bukkit.getServer().getPluginCommand("sethome").setTabCompleter(new CommandSethome());


        Bukkit.getPluginCommand("hdv").setExecutor(new CommandHdv());    //do tab complete
        Bukkit.getPluginCommand("menu").setExecutor(new CommandMenu());
        Bukkit.getPluginCommand("metiers").setExecutor(new CommandMetiers());  //alias jobs

        portailTp = new RegionManager(new Location(Bukkit.getWorld("Aragnok"), 675, 69, 67.5), new Location(Bukkit.getWorld("Aragnok"), 676.5, 82, 77.5));

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new InteractListener(), this);

    }

    @Override
    public void onDisable() {
        ConfigManager.getInstance().savePlayersData();
    }
    

}
