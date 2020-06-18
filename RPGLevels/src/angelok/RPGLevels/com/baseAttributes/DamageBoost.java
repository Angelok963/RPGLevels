package angelok.RPGLevels.com.baseAttributes;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageBoost implements Listener {

	public DamageBoost() {
	}

	@EventHandler
	public void damageBoost(EntityDamageByEntityEvent e) {

		Entity d = e.getDamager();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		double min = DefaultAttributes.getAttributesValueOfDouble(p, "MinDamageBoost");
		double max = DefaultAttributes.getAttributesValueOfDouble(p, "MaxDamageBoost");

		if (max <= 0 || max <= min || min <= 0)
			return;

		double randomboost = ThreadLocalRandom.current().nextDouble(min, max);

		e.setDamage(e.getDamage() + randomboost);

	}

}
