package angelok.RPGLevels.com.baseAttributes;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.potion.PotionEffectType;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.Utilities;

public class BlindnessEffect implements Listener {

	public BlindnessEffect() {

	}

	@EventHandler
	public void blindnessAttac(EntityDamageByEntityEvent e) {

		Entity d = e.getDamager();
		Entity n = e.getEntity();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		if (!(n instanceof LivingEntity))
			return;

		LivingEntity entity = (LivingEntity) n;

		double chance = DefaultAttributes.getAttributesValueOfDouble(p, "BlindnessEffectChance");

		int time = DefaultAttributes.getAttributesValueOfInt(p, "BlindnessEffectTime");

		double random = ThreadLocalRandom.current().nextDouble(0, 100);

		if (random <= chance) {

			Utilities.addEffect(entity, PotionEffectType.BLINDNESS, time);

			if (entity instanceof Monster) {

				((Monster) entity).setTarget(null);
				
			}

			if (!Lang.getblindnesseffect().isEmpty())
				entity.sendMessage(Lang.getblindnesseffect().replace("{time}", String.valueOf(time)));
			if (!Lang.giveblindnesseffect().isEmpty())
				p.sendMessage(Lang.giveblindnesseffect().replace("{time}", String.valueOf(time)));
		}

	}

	@EventHandler
	public void noTargetMobs(EntityTargetEvent e) {

		Entity b = e.getEntity();

		if (!(b instanceof LivingEntity) || b instanceof Player)
			return;

		LivingEntity mob = (LivingEntity) b;

		if (!mob.hasPotionEffect(PotionEffectType.BLINDNESS))
			return;
		e.setCancelled(true);
	}
}