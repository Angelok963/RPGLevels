package angelok.RPGLevels.com;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaUpdateTask extends BukkitRunnable {
	private RPGLevels plugin;

	public ManaUpdateTask(RPGLevels plugin) {
		plugin = this.plugin;
	}

	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			RPGPlayer rpg = RPGLevels.rpg.get(p);
			String clas = rpg.getPclass();
			if (clas.isEmpty()) {
				return;
			}
			double maxMana = Utilities.getPlayerMaxMana(p.getName());
			double pmana = rpg.getMana();
			double manaPerSecond = RPGLevels.rpgclass.get(clas).getManapersecond();
			ManaUpdateEvent event = new ManaUpdateEvent(maxMana, pmana, manaPerSecond, p);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
			
			rpg.setMana(event.getSecondMana());
			RPGLevels.rpg.put(p, rpg);
			}
		}
	}
}
