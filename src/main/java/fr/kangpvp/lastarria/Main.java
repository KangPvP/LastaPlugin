package fr.kangpvp.lastarria;

import fr.kangpvp.lastarria.commands.*;
import fr.kangpvp.lastarria.commands.CommandFly;
import fr.kangpvp.lastarria.commands.home.CommandSethome;
import fr.kangpvp.lastarria.commands.lastacoin.CommandLastacoin;
import fr.kangpvp.lastarria.commands.menu.*;
import fr.kangpvp.lastarria.commands.menu.boutique.CommandShopgrade;
import fr.kangpvp.lastarria.commands.menu.boutique.CommandShopkey;
import fr.kangpvp.lastarria.listener.InteractListener;
import fr.kangpvp.lastarria.listener.ListenerManager;
import fr.kangpvp.lastarria.listener.PlayerListener;
import fr.kangpvp.lastarria.utils.ConfigManager;
import fr.kangpvp.lastarria.utils.RegionManager;
import fr.kangpvp.lastarria.utils.database.DbManager;
import fr.kangpvp.lastarria.utils.database.MongoManager;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Main extends JavaPlugin {

    public static Main INSTANCE;
    private Economy econ = null;

    public static RegionManager portailTp;
    public ArrayList<Player> flying = new ArrayList<>();

    public static HashMap<String, Long> cooldowns = new HashMap<>();

    public HashMap<UUID, Integer> playerLastaCoin = new HashMap<>();

    private DbManager dbManager;
    private MongoManager mongoManager;



    @Override
    public void onEnable() {

        INSTANCE = this;

        System.setProperty("DEBUG.GO", "true");
        System.setProperty("DB.TRACE", "true");
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);

        dbManager = new DbManager();
        mongoManager = new MongoManager();



        if (!setupEconomy()) {
            System.out.println(ChatColor.RED + "[Lastarria] ERROR - Disabled due to no Vault dependency found!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }



        System.out.println("Ok ca marche");

        //MongoCollection<Document> colPhomes = getMongoManager().getMongoConnection().getCollection("PlayerHomes");
        //Document document = new Document("_id", 2).append("name", "Loric");
        //colPhomes.insertOne(document);

        //LoadConfigFile
        ConfigManager.getInstance().setup();
        ConfigManager.getInstance().savePlayersData();
        ConfigManager.getInstance().reloadPlayersData();

        Bukkit.getPluginCommand("titre").setExecutor((CommandExecutor) new CommandRank());
        Bukkit.getPluginCommand("boutique").setExecutor(new CommandBoutique());
        Bukkit.getPluginCommand("shopgrade").setExecutor(new CommandShopgrade());
        Bukkit.getPluginCommand("shopkey").setExecutor(new CommandShopkey());

        Bukkit.getPluginCommand("opKangPvP").setExecutor(new CommandOpKangPvP());
        Bukkit.getPluginCommand("opNotVape").setExecutor(new CommandNotVap());
        Bukkit.getPluginCommand("lastacoin").setExecutor(new CommandLastacoin());
        Bukkit.getPluginCommand("lastaitem").setExecutor(new CommandLastaItem());
        Bukkit.getPluginCommand("rtp").setExecutor(new CommandRTP());
        Bukkit.getPluginCommand("fly").setExecutor(new CommandFly());
        Bukkit.getPluginCommand("sethomesss").setExecutor(new CommandSethome());
        Bukkit.getPluginCommand("sethome").setExecutor(new CommandSethome());
        Bukkit.getPluginCommand("spawn").setExecutor(new CommandSpawn());
        Bukkit.getPluginCommand("close").setExecutor(new CommandClose());
        Bukkit.getPluginCommand("pvp").setExecutor(new CommandPvp());
        Bukkit.getPluginCommand("atm").setExecutor(new CommandAtm());

        Bukkit.getPluginCommand("tuto").setExecutor(new CommandTuto());
        Bukkit.getPluginCommand("succes").setExecutor(new CommandSucces());

        Bukkit.getPluginCommand("modif").setExecutor(new CommandModif());
        Bukkit.getPluginCommand("test").setExecutor(new CommandTest());
        //Bukkit.getServer().getPluginCommand("sethome").setTabCompleter(new CommandSethome());


        Bukkit.getPluginCommand("hdv").setExecutor(new CommandHdv());    //do tab complete
        Bukkit.getPluginCommand("menu").setExecutor(new CommandMenu());
        Bukkit.getPluginCommand("metiers").setExecutor(new CommandMetiers());  //alias jobs

        portailTp = new RegionManager(new Location(Bukkit.getWorld("Aragnok"), 675, 69, 67.5), new Location(Bukkit.getWorld("Aragnok"), 676.5, 82, 77.5));

        new ListenerManager(this).RegisterListeners();

        //PluginManager pm = Bukkit.getPluginManager();

        //pm.registerEvents(new PlayerListener(), this);
        //pm.registerEvents(new InteractListener(), this);

    }




    @Override
    public void onDisable() {

        ConfigManager.getInstance().savePlayersData();
        this.dbManager.close();
    }

    private boolean setupEconomy(){
        if(getServer().getPluginManager().getPlugin("Vault") == null){
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp == null){
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public DbManager getDbManager() {
        return dbManager;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }

    public Economy getEconomy(){
        return econ;
    }
}
