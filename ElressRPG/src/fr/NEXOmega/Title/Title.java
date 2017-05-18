package fr.NEXOmega.Title;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

import fr.NEXOmega.Main;

public class Title implements Listener {
	
	@SuppressWarnings("unused")
	private static Main main;
	
	public Title(Main pmain) {
		Title.main = pmain;
	}
	
	Zombie ent;
	ArrayList<String> titlel = Lists.newArrayList();
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
			ItemStack item = new ItemStack(Material.RECORD_9);
        	ItemMeta itemMeta = item.getItemMeta();
        	itemMeta.setDisplayName("Pierre de dieu");
        	itemMeta.setLore(Arrays.asList("§cDieu du temps"));
        	item.setItemMeta(itemMeta);
        	p.getInventory().addItem(item);
		
	}
	
	@EventHandler
	public void title(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType() != null) {
			if(p.getItemInHand().getType().equals(Material.RECORD_9)) {
				if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
					String title = p.getInventory().getItemInHand().getItemMeta().getLore().get(0).toString();
					
					
					if(!titlel.contains(p.getName().toString())) {
						
						ent = (Zombie)p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
						ent.setCustomName(title);
						ent.setCustomNameVisible(true);
						
						ent.setBaby(true);
						p.setPassenger(ent);
						p.sendMessage("Votre Titre est : " + title);
						titlel.add(p.getName().toString());
					}
					}
				}
			}
		}
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
			if(titlel.contains(p.getName().toString())) {
				p.getPassenger().remove();
				titlel.remove(p.getName().toString());
			}
		
	}

}
