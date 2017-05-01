package fr.NEXOmega.Guild;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.NEXOmega.Main;

public class GuildCommands implements CommandExecutor {
	
	private static Main main;
	
	public GuildCommands(Main pmain) {
		GuildCommands.main = pmain;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("guild")) {
				
				if(args.length > 0) {
					
					if(args[0].equalsIgnoreCase("create")) {  
						
						if(main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild").equals("NoGuild")) {
							GuildUtil.createGuild(args[1], p, main);
							GuildUtil.addPlayer(p, args[1], main);
							p.sendMessage("La Guilde : " + args[1] + " à était crée");
						} else {
							p.sendMessage("Vous deveez quitter votre guilde avant d'en crée une !");
						}
					} else if (args[0].equalsIgnoreCase("sethome")) {
						
						main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Base", p.getLocation());
						p.sendMessage("Guild spawn set.");
						
					} else if (args[0].equalsIgnoreCase("home")) {
						p.sendMessage("Téléportation a la base.");
						p.teleport((Location) main.getGuildConfiguration().get("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Base"));
					}
					
					
					main.saveGuildConfiguration();
					
				}
			}
		}
		return false;
	}
	

}
