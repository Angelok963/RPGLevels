package angelok.RPGLevels.com.baseAttributes;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import angelok.RPGLevels.com.RPGPlayer;

public class MagicShield implements Listener {

	private HashMap<Player, RPGPlayer> rpg;

	public MagicShield(HashMap<Player, RPGPlayer> rpg) {
		this.rpg = rpg;
	}

	@EventHandler
	public void magicShield(EntityDamageEvent e) {

		DamageCause c = e.getCause();

		if (c == DamageCause.FALL || c == DamageCause.DROWNING)
			return;

		Entity d = e.getEntity();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		RPGPlayer rpg = this.rpg.get(p);

		int shield = DefaultAttributes.getAttributesValueOfInt(p, "MagicShield");

		double mana = rpg.getMana();

		double damage = e.getDamage();

		if (mana < shield || shield <= 0)
			return;

		if (damage < shield)
			e.setDamage(0);

		else
			e.setDamage(damage - shield * 2);

		rpg.setMana(mana - damage / 2);
		this.rpg.put(p, rpg);

	}
}