package fr.NEXOmega.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.NEXOmega.Main;

public class RingSlot implements Listener {
	
	@SuppressWarnings("unused")
	private static int task;
	private static Main main;
	
	public RingSlot(Main pmain) {
		RingSlot.main = pmain;
	}

	public static void scan() {
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

			@Override
			public void run() {
				
				for(Player pls : Bukkit.getOnlinePlayers()) {
					if(pls.getInventory().getItem(8) != null) {
						if(pls.getInventory().getItem(8).getType().equals(Material.RECORD_10)  ) {
						
							
						
							scanAnneau(pls);								
							
						}
					}
				}
				
			}	

			private void scanAnneau(Player pls) {
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(0).charAt(0) == '+') {
					for(int i = 0; i < pls.getInventory().getItem(8).getItemMeta().getLore().size(); i++) {
						if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("+")) {
						pls.addPotionEffect(new PotionEffect(effect(pls, i), 250, power(pls, i)));
						}
					}
					
				}
				
			}

			private int power(Player pls, int i) {
				
				int dmg = 0;
				
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Vitesse")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(12, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Haste")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(10, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("NightVision")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(16, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Saut")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(9, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				
				
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Force")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(10, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Defense")) {
					String effect = pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString();
					String test = effect.substring(12, pls.getInventory().getItem(8).getItemMeta().getLore().get(i).toString().length());
					dmg = Integer.valueOf(test);
				}
				
				
				return dmg;
			}

			private PotionEffectType effect(Player pls, int i) {
				PotionEffectType effect = PotionEffectType.NIGHT_VISION;
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Vitesse")) {
					effect = PotionEffectType.SPEED;
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Haste")) {
					effect = PotionEffectType.FAST_DIGGING;
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("NightVision")) {
					effect = PotionEffectType.NIGHT_VISION;
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Saut")) {
					effect = PotionEffectType.JUMP;
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Force")) {
					effect = PotionEffectType.INCREASE_DAMAGE;
				}
				if(pls.getInventory().getItem(8).getItemMeta().getLore().get(i).contains("Defense")) {
					effect = PotionEffectType.DAMAGE_RESISTANCE;
				}
				return effect;
			}
			
		}, 20, 20);
		
	}

}
