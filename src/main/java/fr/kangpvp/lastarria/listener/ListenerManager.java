package fr.kangpvp.lastarria.listener;

import fr.kangpvp.lastarria.listener.player.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    public Plugin plugin;
    public PluginManager pluginManager;

    public ListenerManager(Plugin plugin) {
        this.plugin = plugin;
        this.pluginManager= Bukkit.getPluginManager();
    }

    public void RegisterListeners() {
        //Players
        this.pluginManager.registerEvents(new JoinListener(), this.plugin);
        this.pluginManager.registerEvents(new QuitListener(), this.plugin);
        this.pluginManager.registerEvents(new DeathListener(), this.plugin);
        this.pluginManager.registerEvents(new TeleportListener(), this.plugin);
        this.pluginManager.registerEvents(new MoveListener(), this.plugin);
        this.pluginManager.registerEvents(new DamageListener(), this.plugin);

        this.pluginManager.registerEvents(new InteractListener(), this.plugin);
        this.pluginManager.registerEvents(new ClickListener(), this.plugin);



        //this.pluginManager.registerEvents(new FoodChangeListener(), this.plugin);
        //this.pluginManager.registerEvents(new DropItemListener(), this.plugin);
        //this.pluginManager.registerEvents(new FallListener(), this.plugin);
        //this.pluginManager.registerEvents(new BreakBlockListener(), this.plugin);
        //this.pluginManager.registerEvents(new PlaceBlockListener(), this.plugin);



        //Worlds
        //this.pluginManager.registerEvents(new WorldLoadListener(), this.plugin);

    }




}
