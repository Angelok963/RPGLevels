package angelok.RPGLevels.com;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaUpdateTask extends BukkitRunnable {

	private RPGLevels plugin;

	public ManaUpdateTask(RPGLevels plugin) {
		plugin = this.plugin;
	}

	@Override
	public void run() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			RPGPlayer rpg = RPGLevels.rpg.get(p);

        
				String clas = rpg.getPclass();

			
			if (clas.isEmpty())
				return;

			double ClassMana = RPGLevels.rpgclass.get(clas).getDefaultmana();

			double addManaTolvl = RPGLevels.rpgclass.get(clas).getChangemanatolvl();
			
			int plvl = rpg.getLvl()-1;
			
			double maxMana = ClassMana + (addManaTolvl * plvl);

			double pmana = rpg.getMana();

			double manaPerSecond = RPGLevels.rpgclass.get(clas).getManapersecond();
			
			if (pmana >= maxMana)
				return;

			ManaUpdateEvent event = new ManaUpdateEvent(maxMana, pmana, manaPerSecond, p);
			Bukkit.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {

				rpg.setMana(event.getSecondMana());
				RPGLevels.rpg.put(p, rpg);

			}
		}

	}
}