package angelok.RPGLevels.com.baseAttributes;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.Utilities;

public class SlownessEffect extends BukkitRunnable implements Listener {

	private HashMap<LivingEntity, Double> freezingmobs;
	private HashMap<Player, Float> freezingplayers;

	public SlownessEffect(HashMap<LivingEntity, Double> freezingmobs, HashMap<Player, Float> freezingplayers) {
		this.freezingmobs = freezingmobs;
		this.freezingplayers = freezingplayers;
	}

	@EventHandler
	public void slownessAttac(EntityDamageByEntityEvent e) {

		Entity d = e.getDamager();
		Entity n = e.getEntity();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		if (!(n instanceof LivingEntity))
			return;

		LivingEntity entity = (LivingEntity) n;

		double chance = DefaultAttributes.getAttributesValueOfDouble(p, "SlownessEffectChance");

		int time = DefaultAttributes.getAttributesValueOfInt(p, "SlownessEffectTime");

		int multiple = DefaultAttributes.getAttributesValueOfInt(p, "SlownessEffectPercent");

		double random = ThreadLocalRandom.current().nextDouble(0, 100);

		if (random <= chance) {

			if (entity instanceof Player) {
				Player f = (Player) entity;

				float speed = f.getWalkSpeed();
				freezingplayers.put(p, speed);

				f.setWalkSpeed(speed - (speed / 100 * multiple));
			} else {

				AttributeInstance s = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

				double speed = s.getBaseValue();
				freezingmobs.put(entity, speed);
				s.setBaseValue(speed - (speed / 100 * multiple));

			}

			Utilities.addEffect(entity, PotionEffectType.SLOW, time);

			if (!Lang.getslownesseffect().isEmpty())
				entity.sendMessage(Lang.getslownesseffect().replace("{time}", String.valueOf(time)).replace("{percent}",
						String.valueOf(multiple)));
			if (!Lang.giveslownesseffect().isEmpty())
				p.sendMessage(Lang.giveslownesseffect().replace("{time}", String.valueOf(time)).replace("{percent}",
						String.valueOf(multiple)));
		}

	}

	@Override
	public void run() {

		for (Player p : freezingplayers.keySet()) {

			if (!p.hasPotionEffect(PotionEffectType.SLOW)) {
				p.setWalkSpeed(freezingplayers.get(p));
				freezingplayers.remove(p);
			}

		}

		for (LivingEntity s : freezingmobs.keySet()) {

			if (!s.hasPotionEffect(PotionEffectType.SLOW)) {
				s.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(freezingmobs.get(s));
				freezingmobs.remove(s);
			}

		}

	}

}