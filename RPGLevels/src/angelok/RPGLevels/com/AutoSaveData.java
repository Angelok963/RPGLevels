package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSaveData extends BukkitRunnable {
	private RPGLevels plugin;
	private HashMap<String, RPGClasses> clas;
	private  HashMap<Player, RPGPlayer> rpg;

	public AutoSaveData(RPGLevels plugin, HashMap<String, RPGClasses> clas, HashMap<Player, RPGPlayer> rpg) {
		plugin = this.plugin;
		this.clas = clas;
		this.rpg = rpg;
	}

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {

			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage(Lang.savingdata());

			DataManager.savePlayerData(p, rpg);

			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage(Lang.saveddata());
		}

		for (String classname : clas.keySet()) {
			DataManager.saveClassData(classname, clas);
		}
	}
}
