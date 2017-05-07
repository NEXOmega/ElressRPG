package fr.NEXOmega.Guild;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.NEXOmega.Main;

public class GuildUtil {
	
	static HashMap<Player, Player> grequest = new HashMap<Player, Player>();
		

	public static String getGuild(Player p, Main main) {
		String GName = "NoGuild";
		
		GName = main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name");
		
		return GName;
		
		
	}
	
	public static String getRank(Player p, Main main) {
		String GRank = "Membre";
		
		GRank = main.getGuildConfiguration().getString("globalGuildConfig" + ".GuildList" + "." + getGuild(p, main) + ".Player" + "." + p.getName());
		
		return GRank;
		
		
	}


	public static void addPlayer(Player p, String args, Main main) {
		main.getPlayerConfiguration().set("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name", args);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Name", args);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Player" + "." + p.getName(), "Membre");
		main.savePlayerConfiguration();
		main.saveGuildConfiguration();
		
	}
	
	public static void removePlayer(Player p, Main main) {
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" +"." + getGuild(p, main) + ".Player" + "." + p.getName(), null);
		main.getPlayerConfiguration().set("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name", "NoGuild");
		main.getPlayerConfiguration().set("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Rank", "Membre");	
		main.savePlayerConfiguration();
		main.saveGuildConfiguration();
	}

	public static void createGuild(String args, Player p, Main main) {
		String[] gcontent = {".Name", ".Base",".Ranks", ".Player"};
		String[] granks = {".Leader", ".Adjoint", ".Membre"};
		String[] rperms = {".sethome", ".rename", ".home"};
		main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args);
		
		for(int i = 0; i < gcontent.length; i++) {
			main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args + gcontent[i]);
					
		}
		addPlayer(p, args, main);
		generateRanks(granks, rperms, main, args);
		
			
	}

	public static void generateRanks(String[] granks, String[] rperms, Main main, String args) {
		for(int igr = 0; igr < granks.length; igr++) {
			main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + granks[igr]);
			
			for(int irp = 0; irp < rperms.length; irp++) {
				main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + granks[igr] + rperms[irp]);
			generateBasicRanks(args, granks, rperms, main);
		}
	}
		
	}

	private static void generateBasicRanks(String args, String[] granks, String[] rperms, Main main) {
		//Create Leader Rank
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Leader"  + ".sethome", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Leader"  + ".invite", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Leader"  + ".home", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Leader"  + ".rename", true);
		
		//Create Adjoint Rank
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Adjoint" + ".sethome", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Adjoint" + ".invite", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Adjoint" + ".home", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Adjoint" + ".rename", false);
		
		//Create Membre Rank
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Membre" + ".sethome", false);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Membre" + ".invite", false);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Membre" + ".home", true);
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args + ".Ranks" + ".Membre" + ".rename", false);
	}

	public static void setRank(Player p, String rank, Main main) {
		main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + getGuild(p, main) + ".Player" + "." + p.getName(), rank);
		main.saveGuildConfiguration();
	}

	public static void invite(Player p, String[] args) {
		Player target = p.getServer().getPlayer(args[1]);
		if(target != null) {			
			grequest.put(target, p);
			target.sendMessage(p.getName() + " Vous as invité dans sa guilde, tapez /guild accept pour le rejoindre ou /guild deny pour refuser");
		}
		
	}

	public static void accept(Player p, Main main) {
		if(grequest.get(p) != null) {
			Player target = grequest.get(p);
			addPlayer(p, getGuild(target, main), main);
			grequest.put(p, null);
			p.sendMessage("Vous avez rejoint la guilde.");
			target.sendMessage(p.getName() + " a rejoint la Guilde !");
		}
		
	}

	public static void deny(Player p) {
		if(grequest.get(p) != null) {
			grequest.put(p, null);
			p.sendMessage("Vous avez refusé de rejoindre la guilde.");
		}
		
	}

	public static void guildInfo(Player p, String[] args, Main main) {
		
		p.sendMessage("Nom De Guild : " + main.getGuildConfiguration().getString("globalGuildConfig" + ".GuildList" + "." + args[1] + ".Name"));
	}

}
