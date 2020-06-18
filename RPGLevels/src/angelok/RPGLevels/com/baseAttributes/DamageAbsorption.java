package angelok.RPGLevels.com.baseAttributes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageAbsorption implements Listener {

	public DamageAbsorption() {

	}

	@EventHandler
	public void damageAbsorption(EntityDamageEvent e) {

		DamageCause c = e.getCause();

		if (c == DamageCause.FALL || c == DamageCause.DROWNING)
			return;

		Entity d = e.getEntity();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		double shield = DefaultAttributes.getAttributesValueOfInt(p, "DamageAbsorption") * 1.5;

		if (e.getDamage() > shield)
			e.setDamage(e.getDamage() - shield);
		else
			e.setDamage(0);
	}
}