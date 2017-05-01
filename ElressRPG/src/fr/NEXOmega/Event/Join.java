package fr.NEXOmega.Event;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.NEXOmega.Main;
import fr.NEXOmega.Inventory.InventoryManager;

public class Join implements Listener {
	
	private static Main main;
	
	public Join(Main pmain) {
		Join.main = pmain;
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		InventoryManager.scan();

		
		
		Player p = e.getPlayer();
		main.getPlayerConfiguration().set("globalPlayerConfig" + ".PlayerList" + "." + p.getName() + ".Guild", "NoGuild");
		main.savePlayerConfiguration();
		ItemStack test = new ItemStack(Material.SADDLE,1);
		ArrayList<String> lore = new ArrayList<>();
		ItemMeta testM = test.getItemMeta();
		lore.add("Zombie");
		lore.add("Padanus");
		testM.setLore(lore);
		test.setItemMeta(testM);

		p.getInventory().addItem(test);
	}

}
