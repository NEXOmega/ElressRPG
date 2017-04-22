package fr.NEXOmega.mounts;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;

import fr.NEXOmega.Main;
import fr.NEXOmega.mounts.utils.MountsMethods;

public class Mount implements Listener {
	
	ArrayList<String> mounted = new ArrayList<>();
	
	private static int task;
	
	
private static Main main;
	
	public Mount(Main pmain) {
		Mount.main = pmain;
	}

	@EventHandler
	public void UseSaddle(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getItem() != null) {
			
			if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
				
				if(e.getItem().getType().equals(Material.SADDLE)) {

					ItemStack mount = e.getItem();
				
					createMount(mount, p);
					
					MountMovement(p);
				}
			}
		}
	}


	private void MountMovement(final Player p) {
		final Entity mount = p.getVehicle();
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
		@Override
		public void run() {
			
			Float dirX = (float) (0 - (Math.sin((p.getLocation().getYaw() / 180) * Math.PI) * 3));
			Float dirZ = (float) (Math.cos((p.getLocation().getYaw() / 180) * Math.PI) * 3);
			mount.setVelocity(mount.getVelocity().setX(dirX));
			mount.setVelocity(mount.getVelocity().setZ(dirZ));							
						
					}
				}, 1, 1);
			}
			
			
		
		
	

	private void createMount(ItemStack mount, Player p) {
		Entity e = null;
		if(!mounted.contains(p.getName().toString())) {
			e = p.getWorld().spawnEntity(p.getLocation(), MountsMethods.getEntity(mount));
			p.sendMessage("on");
			mounted.add(p.getName().toString());
			e.setCustomName(mount.getItemMeta().getLore().get(1));
			e.setCustomNameVisible(true);
			
			e.setPassenger(p);
		} else {
			p.sendMessage("off");
			
			delMount(p);
			
			
		}
	
		
	}

	private void delMount(Player p) {
		mounted.remove(p.getName().toString());
		((Damageable) p.getVehicle()).damage(1000);
		
	}
}
