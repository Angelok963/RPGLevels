package angelok.RPGLevels.com;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSaveData extends BukkitRunnable {
	private RPGLevels plugin;

	public AutoSaveData(RPGLevels plugin) {
		plugin = this.plugin;
	}

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {

			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage("§c(§eRPGLevels§c) §7Сохраняем ваши данные...");

			DataManager.savePlayerData(p);
			
			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage("§c(§eRPGLevels§c) §7Данные §cуспешно §7сохранены...");
		}

		for (String classname : RPGLevels.rpgclass.keySet()) {
			DataManager.saveClassData(classname);
		}
	}
}
