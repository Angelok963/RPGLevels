package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ManaUpdateTask extends BukkitRunnable {
	private RPGLevels plugin;
	private  HashMap<Player, RPGPlayer> rpg;
	private HashMap<String, RPGClasses> rpgclass;

	public ManaUpdateTask(RPGLevels plugin, HashMap<Player, RPGPlayer> rpg, HashMap<String, RPGClasses> rpgclass) {
		plugin = this.plugin;
		this.rpg = rpg;
		this.rpgclass = rpgclass;
	}

	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			RPGPlayer rpg = this.rpg.get(p);
			String clas = rpg.getPclass();
			if (clas.isEmpty()) {
				return;
			}
			double maxMana = Utilities.getPlayerMaxMana(p.getName());
			double pmana = rpg.getMana();
			double manaPerSecond = Utilities.getManaPerSecond(p, this.rpg, this.rpgclass);
			ManaUpdateEvent event = new ManaUpdateEvent(maxMana, pmana, manaPerSecond, p);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
			
			rpg.setMana(event.getSecondMana());
			this.rpg.put(p, rpg);
			}
		}
	}
}
