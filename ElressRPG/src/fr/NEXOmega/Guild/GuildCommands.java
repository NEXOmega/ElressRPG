package fr.NEXOmega.Guild;

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
			
			if(cmd.getName().equalsIgnoreCase("guild")) {
				if(args.length > 0) {
					
					createGuild(args);
					main.saveGuildConfiguration();
				}
			}
		}
		return false;
	}

	private void createGuild(String[] args) {
		String[] gcontent = {".Name", ".Player"};
		main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args[0]);
		
		for(int i = 0; i < gcontent.length; i++) {
			main.getGuildConfiguration().createSection("globalGuildConfig" + ".GuildList" + "." + args[0] + gcontent[i]);
			main.getGuildConfiguration().set("globalGuildConfig" + ".GuildList" + "." + args[0] + ".Name", args[0]);
		
		}
			
	}

}
