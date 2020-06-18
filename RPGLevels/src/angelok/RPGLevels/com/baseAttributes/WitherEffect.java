package angelok.RPGLevels.com.baseAttributes;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.Utilities;

public class WitherEffect implements Listener {

	public WitherEffect() {

	}

	@EventHandler
	public void witherAttac(EntityDamageByEntityEvent e) {

		Entity d = e.getDamager();
		Entity n = e.getEntity();

		if (!(d instanceof Player))
			return;

		Player p = (Player) d;

		if (!(n instanceof LivingEntity))
			return;

		LivingEntity entity = (LivingEntity) n;

		double chance = DefaultAttributes.getAttributesValueOfDouble(p, "WitherEffectChance");

		int time = DefaultAttributes.getAttributesValueOfInt(p, "WitherEffectTime");

		double random = ThreadLocalRandom.current().nextDouble(0, 100);

		if (random <= chance) {

			Utilities.addEffect(entity, PotionEffectType.WITHER, time);

			if (!Lang.getwithereffect().isEmpty())
				entity.sendMessage(Lang.getwithereffect().replace("{time}", String.valueOf(time)));
			if (!Lang.givewithereffect().isEmpty())
				p.sendMessage(Lang.givewithereffect().replace("{time}", String.valueOf(time)));
		}

	}
}
