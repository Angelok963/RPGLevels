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

public class PoisonEffect  implements Listener {

	public PoisonEffect() {
		
	}
	
	@EventHandler
	public void poisonAttac(EntityDamageByEntityEvent e) {
		
		Entity d = e.getDamager();
        Entity n = e.getEntity();
		
		if (!(d instanceof Player))
			return;

		Player p = (Player) d;
		
		if(!(n instanceof LivingEntity)) return;
		
		LivingEntity entity = (LivingEntity)n;
		
	double chance = DefaultAttributes.getAttributesValueOfDouble(p, "PoisonEffectChance");	
		
	int time = DefaultAttributes.getAttributesValueOfInt(p, "PoisonEffectTime");
	
	double random = ThreadLocalRandom.current().nextDouble(0, 100);
	
	if(random <= chance) {
		
		Utilities.addEffect(entity, PotionEffectType.POISON, time);
		
		if(!Lang.getposioneffect().isEmpty())
		entity.sendMessage(Lang.getposioneffect().replace("{time}", String.valueOf(time)));
		if(!Lang.giveposioneffect().isEmpty())
		p.sendMessage(Lang.giveposioneffect().replace("{time}", String.valueOf(time)));
	}
	
	}
}