package fr.NEXOmega.Commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import fr.NEXOmega.Main;

public class RingCmd implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private static Main main;
	
	public RingCmd(Main pmain) {
		RingCmd.main = pmain;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("ering")) {
				if(p.getItemInHand().getType().equals(Material.RECORD_10)) {
					if(args.length > 0) {
					if(args[0].equals("+")) {
						p.sendMessage("§eAddition");
						ItemMeta i = p.getItemInHand().getItemMeta();
						ArrayList<String> lore = new ArrayList<>();
						if(i.hasLore()) {
							lore = new ArrayList<>(i.getLore());
						}
						if(args.length > 2) {
							switch (args[1]) {
							
							case "spd":
								p.sendMessage("§cSpeed");
								lore.add("+ Vitesse : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							case "str":
								p.sendMessage("§cForce");
								lore.add("+ Force : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							case "hst":
								p.sendMessage("§cHaste");
								lore.add("+ Haste : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							case "def":
								p.sendMessage("§cDefense");
								lore.add("+ Defense : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							case "saut":
								p.sendMessage("§cSaut");
								lore.add("+ Saut : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							case "nv":
								p.sendMessage("§cNightVision");
								lore.add("+ NightVision : " + args[2]);
								i.setLore(lore);
								p.getItemInHand().setItemMeta(i);
								break;
							
							default:
								error(p);
							}
						} else {
							error(p);
						}
					}
					} else {
						p.sendMessage("§c[Erreur]§e Tapez /ering + (effet) (puissance)");
					}
				} else {
					p.sendMessage("§cVous devez avoir un anneau dans la main !");
				}
			}
		}
		return false;
	}

	private void error(Player p) {
		String[] effectList = {"§c[Erreur]§e Effet Invalide ou trop peut d'arguments.", "§cstr§e = Force", "§cdef§e = Defense", "§csaut§e = Saut", "§cnv§e = NightVision", "§cspd§e = Vitesse", "§chst§e = Haste"};
		for(int e = 0; e < effectList.length; e++) {
			p.sendMessage(effectList[e]);
		}
		
	}

}
