package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import angelok.RPGLevels.com.baseAttributes.DefaultAttributes;

public class Utilities {

	public static void loadDataItemsYML(YamlConfiguration items) {
		RPGLevels.items = items;
	}
	
	public static void loadDataPlayerYML(YamlConfiguration datap) {
		RPGLevels.datap = datap;
	}

	public static void loadLangYML(YamlConfiguration lang) {
		RPGLevels.lang = lang;
	}

	public static void loadDataClassesYML(YamlConfiguration classes) {
		RPGLevels.classes = classes;
	}

	public static double getPlayerMaxMana(String player) {
		String clas = "";
		boolean isOnline = false;
		if (Bukkit.getPlayer(player) != null) {
			isOnline = true;
		}
		int lvl = 0;
		if (isOnline) {
			RPGPlayer rpgp = RPGLevels.rpg.get(Bukkit.getPlayer(player));
			clas = rpgp.getPclass();
			lvl = rpgp.getLvl();
		} else {
			clas = DataManager.getPlayerDataString(player, "class");
			lvl = DataManager.getPlayerDataInt(player, "lvl");
		}
		double max = clas.isEmpty() ? 0.0
				: (RPGLevels.rpgclass.get(clas).getDefaultmana()
						+ RPGLevels.rpgclass.get(clas).getChangemanatolvl() * (lvl - 1));
		if (isOnline) {
			return max + max / 100.0
					* DefaultAttributes.getAttributesValueOfDouble(Bukkit.getPlayer(player), "ManaStorageBoost");
		}
		return max;
	}

	public static double getManaPerSecond(Player player, HashMap<Player, RPGPlayer> rpg,
			HashMap<String, RPGClasses> rpgclass) {
		double s = rpgclass.get(rpg.get(player).getPclass()).getManapersecond();
		return s + s / 100.0 * DefaultAttributes.getAttributesValueOfDouble(player, "ManaRegenBoost");
	}

	public static void addEffect(Player p, PotionEffectType type, int time) {

		time *= 20;
		
		if (p.hasPotionEffect(type)) {
			time += p.getPotionEffect(type).getDuration();
			p.removePotionEffect(type);

		}

		p.addPotionEffect(new PotionEffect(type, time, 0));

	}
	
	public static void addEffect(LivingEntity p, PotionEffectType type, int time) {

		time *= 20;
		
		if (p.hasPotionEffect(type)) {
			time += p.getPotionEffect(type).getDuration();
			p.removePotionEffect(type);

		}

		p.addPotionEffect(new PotionEffect(type, time, 0));

	}
	
	public static ItemStack getButton(String name, short data) {

		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability(data);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(name);
		i.setItemMeta(m);

		return i;
	}
}
