package fr.kangpvp.lastarria.utils;

import fr.kangpvp.lastarria.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
	 private ConfigManager() {}
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private static ConfigManager instance = new ConfigManager();
	 
    public static ConfigManager getInstance() {
        return instance;
    }
	
	// File & File config => Here
	public static FileConfiguration pdatacfg;
	private File claimsfile;
	// ......................

	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		claimsfile = new File(plugin.getDataFolder(), "playersdata.yml");
		
		if (!claimsfile.exists()) {
			try {
				claimsfile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le ficher claims.yml a ete cree");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le ficher claims.yml n'a pas pu etre cree");
			}
		}
		
		pdatacfg = YamlConfiguration.loadConfiguration(claimsfile);

		pdatacfg.createSection("claimed", ClaimManager.chunks);
		//pdatacfg.set("tetesssss", "test");
		
	}

	public FileConfiguration getPlayersData() {
		return pdatacfg;
	}

	public void saveClaimsData() {
		try {
			pdatacfg.save(claimsfile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le fichier claims.yml a ete sauvegarde");
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le fichier claims.yml n'a pas pu etre sauvegarde");
		}

	}
	

	public void reloadClaimsData() {
		pdatacfg = YamlConfiguration.loadConfiguration(claimsfile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le ficher claims.yml a ete charger");
	}

}
