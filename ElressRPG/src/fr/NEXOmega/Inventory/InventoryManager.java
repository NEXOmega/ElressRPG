package fr.NEXOmega.Inventory;

import org.bukkit.event.Listener;

import fr.NEXOmega.Main;

public class InventoryManager implements Listener {
	
	@SuppressWarnings("unused")
	private static Main main;
	
	public InventoryManager(Main pmain) {
		InventoryManager.main = pmain;
	}

	public static void scan() {
		RingSlot.scan();
		
	}


}
