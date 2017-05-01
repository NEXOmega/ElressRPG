package fr.NEXOmega;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.NEXOmega.Commands.RingCmd;
import fr.NEXOmega.Event.Join;
import fr.NEXOmega.Guild.GuildCommands;
import fr.NEXOmega.Inventory.InventoryManager;
import fr.NEXOmega.Inventory.RingSlot;
import fr.NEXOmega.Title.Title;
import fr.NEXOmega.mounts.Mount;

public class Main extends JavaPlugin {
	
	File gf = new File(getDataFolder(), "global.yml");
	FileConfiguration global = YamlConfiguration.loadConfiguration(gf);
	
	File playersf = new File(getDataFolder(), "players.yml");
	FileConfiguration players = YamlConfiguration.loadConfiguration(playersf);
	
	File guildf = new File(getDataFolder(), "guild.yml");
	FileConfiguration guild = YamlConfiguration.loadConfiguration(guildf);
	
	public static Main instance;
	
	
	public static Main getInstance() {
		return instance;
	}

	
	public void onEnable() {
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Join(this), this);
		pm.registerEvents(new InventoryManager(this), this);
		pm.registerEvents(new RingSlot(this), this);
		pm.registerEvents(new Mount(this), this);
		pm.registerEvents(new Title(this), this);
		Bukkit.getPluginCommand("ering").setExecutor(new RingCmd(this));
		Bukkit.getPluginCommand("guild").setExecutor(new GuildCommands(this));
		createConfig();
		registerFile();
		
	}
	
	public void registerFile() {
		
		try {
			
			global.save(gf);
			guild.save(guildf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}



	public void createConfig() {
	createGlobalConfig();
	createGuildConfig();
	createPlayerConfig();
	}



	private void createPlayerConfig() {
		getPlayerConfiguration().createSection("globalPlayerConfig" + ".PlayerList");

		
		savePlayerConfiguration();
		
	}
	
	public FileConfiguration getPlayerConfiguration() {
		
		return players;
	}
	
	public void savePlayerConfiguration() {
		try {
			players.save(playersf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	private void createGuildConfig() {

		getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList");

	
		saveGuildConfiguration();
		
	}
	
	public FileConfiguration getGuildConfiguration() {
		
		return guild;
	}
	
	public void saveGuildConfiguration() {
		try {
			guild.save(guildf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	private void createGlobalConfig() {
		String[] list = {".Event", ".Arene"};
		for(int i = 0; i < list.length; i++) {
		getGConfiguration().createSection("globalConfig" + ".Loc"+ list[i]);
					
		}
	
		saveGConfiguration();
	
	}

	public FileConfiguration getGConfiguration() {
	
		return global;
	}

	

	public void saveGConfiguration() {
		try {
			global.save(gf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
