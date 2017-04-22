package fr.NEXOmega.mounts.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class MountsMethods {

	public static EntityType getEntity(ItemStack mount) {
		EntityType entity = EntityType.EGG;
		switch(mount.getItemMeta().getLore().get(0)) {
		case "Zombie":
			entity = EntityType.ZOMBIE;
		break;
		}
		
		
		return entity;
	}

}
