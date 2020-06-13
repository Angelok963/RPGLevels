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
			String player = p.getName();
			RPGPlayer rpg = RPGLevels.rpg.get(p);

			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage("§c(§eRPGLevels§c) §7Сохраняем ваши данные...");

			DataManager.setPlayerData(player, "exp", rpg.getExp());
			DataManager.setPlayerData(player, "heal", rpg.getHeal());
			DataManager.setPlayerData(player, "lastheal", rpg.getLastheal());
			DataManager.setPlayerData(player, "lvl", rpg.getLvl());
			DataManager.setPlayerData(player, "mana", rpg.getMana());
			DataManager.setPlayerData(player, "class", rpg.getPclass());
			DataManager.setPlayerData(player, "skills", rpg.getSkills());
			if (RPGLevels.plugin.getConfig().getBoolean("AutoSaveDataModule.broadcasted"))
				p.sendMessage("§c(§eRPGLevels§c) §7Данные §cуспешно §7сохранены...");
		}

		for (String classname : RPGLevels.rpgclass.keySet()) {
			DataManager.setClassData(classname, "changehealtolvl",
					RPGLevels.rpgclass.get(classname).getChangehealtolvl());
			DataManager.setClassData(classname, "changemanatolvl",
					RPGLevels.rpgclass.get(classname).getChangemanatolvl());
			DataManager.setClassData(classname, "defaultheal", RPGLevels.rpgclass.get(classname).getDefaultheal());
			DataManager.setClassData(classname, "defaultmana", RPGLevels.rpgclass.get(classname).getDefaultmana());
			DataManager.setClassData(classname, "info", RPGLevels.rpgclass.get(classname).getInfo());
			DataManager.setClassData(classname, "item", RPGLevels.rpgclass.get(classname).getItem());
			DataManager.setClassData(classname, "manapersecond", RPGLevels.rpgclass.get(classname).getManapersecond());

		}
	}
}
