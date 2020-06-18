package angelok.RPGLevels.com.baseAttributes;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.Utilities;

public class RegenerationDamage implements Listener {

	public RegenerationDamage() {

	}

	@EventHandler
	public void regenerationAttac(EntityDamageByEntityEvent e) {

		Entity d = e.getDamager();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		double chance = DefaultAttributes.getAttributesValueOfDouble(p, "RegenerationEffectChance");

		int time = DefaultAttributes.getAttributesValueOfInt(p, "RegenerationEffectTime");

		double random = ThreadLocalRandom.current().nextDouble(0, 100);

		if (random <= chance) {

			Utilities.addEffect(p, PotionEffectType.REGENERATION, time);

			if (!Lang.giveregenerationeffect().isEmpty())
				p.sendMessage(Lang.giveregenerationeffect().replace("{time}", String.valueOf(time)));
		}

	}
}