package fr.kangpvp.lastarria.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import fr.kangpvp.lastarria.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public class ConfigManager {
	private ConfigManager() {}

	private Main plugin = Main.getPlugin(Main.class);

	private static ConfigManager instance = new ConfigManager();

	public static ConfigManager getInstance() {
		return instance;
	}

	// File & File config => Here
	public static FileConfiguration pdatacfg;
	private File playersdatafile;
	// ......................

	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		playersdatafile = new File(plugin.getDataFolder(), "playersdata.yml");

		if (!playersdatafile.exists()) {
			try {
				playersdatafile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le ficher playersdata.yml a ete cree");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Le ficher playersdata.yml n'a pas pu etre cree");
			}
		}

		pdatacfg = YamlConfiguration.loadConfiguration(playersdatafile);

		pdatacfg.set("tetesssss", "test"); //test ecriture

		for(Map.Entry<String, String> value: ClaimManager.chunks.entrySet()) {
			pdatacfg.set("claims." + value.getKey(), value.getValue());
		}

	}

	public FileConfiguration getPlayersData() {
		return pdatacfg;
	}

	public void savePlayersData() {
		try {
			pdatacfg.save(playersdatafile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le fichier playersdata.yml a ete sauvegarde");
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le fichier playersdata.yml n'a pas pu etre sauvegarde");
		}

	}


	public void reloadPlayersData() {
		pdatacfg = YamlConfiguration.loadConfiguration(playersdatafile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Le ficher playersdata.yml a ete charger");
	}

}