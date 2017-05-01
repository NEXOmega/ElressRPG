package fr.NEXOmega.Guild;

import org.bukkit.entity.Player;

import fr.NEXOmega.Main;

public class GuildUtil {
		

	public static String getGuild(Player p, Main main) {
		String GName = "NoGuild";
		
		GName = main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild");
		
		return GName;
		
		
	}


	public static void addPlayer(Player p, String args, Main main) {
		main.getPlayerConfiguration().set("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild", args);
		main.savePlayerConfiguration();
		
	}

	public static void createGuild(String args, Player p, Main main) {
		String[] gcontent = {".Name", ".Base", ".Player"};
		main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args);
		
		for(int i = 0; i < gcontent.length; i++) {
			main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args + gcontent[i]);
			
		
		}
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Name", args);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Player" + "." + p.getName(), p.getUniqueId().toString());
			
	}

}
