package angelok.RPGLevels.com;


import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ChangeLvL {

	public static void lvlUp(Player p)  {
		RPGPlayer rpg = RPGLevels.rpg.get(p);

		String clas = rpg.getPclass();
				
		
		double heal = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

        double boostheal = RPGLevels.rpgclass.get(clas).getChangehealtolvl();

		int skills = rpg.getSkills();
		
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(heal + boostheal);


		rpg.setSkills(skills+1);
		
		rpg.setHeal(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());;

		RPGLevels.rpg.put(p, rpg);
	}

}
