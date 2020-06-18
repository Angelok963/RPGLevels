package angelok.RPGLevels.com.baseAttributes;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import angelok.RPGLevels.com.Lang;

public class FireEffect  implements Listener {

	public FireEffect() {
		
	}
	
	@EventHandler
	public void fireAttac(EntityDamageByEntityEvent e) {
		
		Entity d = e.getDamager();
        Entity n = e.getEntity();
		
		if (!(d instanceof Player))
			return;

		Player p = (Player) d;
		
		if(!(n instanceof LivingEntity)) return;
		
		LivingEntity entity = (LivingEntity)n;
		
	double chance = DefaultAttributes.getAttributesValueOfDouble(p, "FireEffectChance");	
		
	int time = DefaultAttributes.getAttributesValueOfInt(p, "FireEffectTime");
	
	double random = ThreadLocalRandom.current().nextDouble(0, 100);
	
	if(random <= chance) {
		if(entity.getFireTicks() == 0)
		entity.setFireTicks(time * 20);
		else entity.setFireTicks(entity.getFireTicks() + time * 20);
		
		
		if(!Lang.getfireeffect().isEmpty())
		entity.sendMessage(Lang.getfireeffect().replace("{time}", String.valueOf(time)));
		if(!Lang.givefireeffect().isEmpty())
		p.sendMessage(Lang.givefireeffect().replace("{time}", String.valueOf(time)));
	}
	
	}
}