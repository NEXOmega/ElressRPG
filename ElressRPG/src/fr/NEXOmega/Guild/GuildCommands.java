package fr.NEXOmega.Guild;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.NEXOmega.Main;

public class GuildCommands implements CommandExecutor {
	
	String[] rank = {"Leader", "Adjoint", "Membre"};
	
	
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
						
						if(main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name").equals("NoGuild")) {
							GuildUtil.createGuild(args[1], p, main);
							
							GuildUtil.setRank(p, rank[0], main);
							p.sendMessage("La Guilde : " + args[1] + " à était crée");
						} else {
							
							p.sendMessage("Vous devez quitter votre guilde avant d'en crée une !");
						}
					} else if (args[0].equalsIgnoreCase("sethome")) { 
						if(main.getGuildConfiguration().getBoolean("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Ranks" + "." + GuildUtil.getRank(p, main) + ".sethome")) {
							main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Base", p.getLocation());
						p.sendMessage("Guild spawn set.");
					} else {
						p.sendMessage("Vous n'avez pas la permission de définir la base de guilde");
					}
					} else if (args[0].equalsIgnoreCase("home")) {
						if(main.getGuildConfiguration().getBoolean("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Ranks" + "." + GuildUtil.getRank(p, main) + ".home")) {
							p.sendMessage("Téléportation a la base.");
							p.teleport((Location) main.getGuildConfiguration().get("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Base"));
						} else {
							p.sendMessage("Vous n'avez pas les permisions pour faire sa.");
						}
					}
					
					
					else if(args[0].equalsIgnoreCase("leave")) {
						if(!main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name").equals("NoGuild")) {
							GuildUtil.removePlayer(p, main);
							
							p.sendMessage("Vous avez quitté votre guilde.");
						} else {
							p.sendMessage("Erreur, Vous n'êtes dans aucune guilde !");
						}
					}
					
					else if(args[0].equalsIgnoreCase("invite")) {
						if(!main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name").equals("NoGuild")) {
							if(main.getGuildConfiguration().getBoolean("globalGuildConfig" + ".GuildList" +"." + GuildUtil.getGuild(p, main) + ".Ranks" + "." + GuildUtil.getRank(p, main) + ".invite")) {
							GuildUtil.invite(p, args);
							} else {
								p.sendMessage("Votre rang ne vous permet pas cette commande !");
							}
						} else {
							p.sendMessage("Vous n'avez pas les permissions pour faire sa.");
						}
					}
					
					else if(args[0].equalsIgnoreCase("accept")) {
						if(main.getPlayerConfiguration().getString("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild" + ".Name").equals("NoGuild")) {
							GuildUtil.accept(p, main);
						} else {
							p.sendMessage("Vous devez quitté votre guilde avant d'en rejoindre une autre.");
						}
					}
					
					else if(args[0].equalsIgnoreCase("deny")) {
						GuildUtil.deny(p);
					}
					
					else if(args[0].equalsIgnoreCase("view")) {
						p.sendMessage(GuildUtil.getGuild(p, main) + "  " + GuildUtil.getRank(p, main) + "  " + p);
					}
					
					
					
					main.saveGuildConfiguration();
					
				}
			}
		}
	
		return false;
	}
	

}
