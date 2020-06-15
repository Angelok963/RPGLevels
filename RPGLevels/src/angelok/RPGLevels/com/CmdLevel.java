package angelok.RPGLevels.com;

import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CmdLevel implements CommandExecutor {
	private RPGLevels plugin;
	String noperms;
	String noconsole;

	public CmdLevel(RPGLevels plugin) {
		this.noperms = "§c(§eRPGLevels§c) §7У вас §cнедостаточно §7прав!";
		this.noconsole = "§c(§eRPGLevels§c) §7Команда §cнедоступна §7из консоли!";
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (!sender.hasPermission("rpglevels.cmd.level")) {
			sender.sendMessage(this.noperms);
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(
					"§c(§eRPGLevels§c) §7Команда введена §cне полностью §7. Используйте §c/level help §7для помощи.");
			return false;
		}
		switch (args[0]) {
		case "setrpgspawn": {
			if (!sender.hasPermission("rpglevels.cmd.setrpgspawn")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(this.noconsole);
				return false;
			}
			Player p = (Player) sender;
			Location loc2 = p.getLocation();
			this.plugin.getConfig().set("rpgspawn",
					(String.valueOf(loc2.getWorld().getName()) + ":" + String.valueOf(loc2.getBlockX()) + ":"
							+ String.valueOf(loc2.getBlockY()) + ":" + String.valueOf(loc2.getBlockZ()) + ":"
							+ String.valueOf(loc2.getYaw()) + ":" + String.valueOf(loc2.getPitch())));
			this.plugin.saveConfig();
			p.sendMessage("§c(§eRPGLevels§c) §7Ваша локация §aуспешно §7сохранена как RPG точка спавна.");
			return true;
		}
		case "setfirstspawn": {
			if (!sender.hasPermission("rpglevels.cmd.setfirstspawn")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(this.noconsole);
				return false;
			}
			Player p = (Player) sender;
			Location loc3 = p.getLocation();
			this.plugin.getConfig().set("firstspawn",
					(String.valueOf(loc3.getWorld().getName()) + ":" + String.valueOf(loc3.getBlockX()) + ":"
							+ String.valueOf(loc3.getBlockY()) + ":" + String.valueOf(loc3.getBlockZ()) + ":"
							+ String.valueOf(loc3.getYaw()) + ":" + String.valueOf(loc3.getPitch())));
			this.plugin.saveConfig();
			p.sendMessage("§c(§eRPGLevels§c) §7Ваша локация §aуспешно §7сохранена как первичная точка спавна.");
			return true;
		}
		case "reload": {
			if (!sender.hasPermission("rpglevels.cmd.reload")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			String old_data = this.plugin.getConfig().getString("StorageType");
			this.plugin.reloadConfig();
			String new_data = this.plugin.getConfig().getString("StorageType");
			if (new_data.equals("file")) {
				File playerdata = new File(
						this.plugin.getDataFolder() + File.separator + "Data" + File.separator + "players.yml");
				File classdata = new File(
						this.plugin.getDataFolder() + File.separator + "Data" + File.separator + "classes.yml");
				if (!classdata.exists()) {
					RPGLevels.saveClassData();
					RPGLevels.classes.set("Лучник.info", "Класс по умолчанию\\nможно менять");
					RPGLevels.classes.set("Лучник.item", "BOW");
					RPGLevels.classes.set("Лучник.defaultheal", 20.5);
					RPGLevels.classes.set("Лучник.changehealtolvl", 5.5);
					RPGLevels.classes.set("Лучник.defaultmana", 10.5);
					RPGLevels.classes.set("Лучник.changemanatolvl", 10.5);
					RPGLevels.classes.set("Лучник.manapersecond", 2.5);
					RPGLevels.saveClassData();
				}
				if (!playerdata.exists()) {
					RPGLevels.savePlayerData();
				}
				RPGLevels.datap = YamlConfiguration.loadConfiguration(playerdata);
				RPGLevels.classes = YamlConfiguration.loadConfiguration(classdata);
			}
			String msg = "";
			if (old_data.equals(new_data)) {
				msg = "§c(§eRPGLevels§c) §7Конфигурация §cуспешно §7перезагружена.";
			} else {
				msg = "§c(§eRPGLevels§c) §7Конфигурация перезагружена, однако обнаружено изменение типа хранения данных. Для корректной работы плагина, пожалуйста, §cперезагрузите сервер§7!";
			}
			if (this.plugin.getConfig().getBoolean("AutoSaveDataModule.enabled")) {
				if (!RPGLevels.saveTask.isCancelled()) {
					RPGLevels.saveTask.cancel();
				}
				RPGLevels.saveTask = new AutoSaveData(this.plugin).runTaskTimerAsynchronously((Plugin) this.plugin, 0L,
						(long) (this.plugin.getConfig().getInt("AutoSaveDataModule.period") * 1200));
			} else if (!RPGLevels.saveTask.isCancelled()) {
				RPGLevels.saveTask.cancel();
			}
			sender.sendMessage(msg);
			return true;
		}
		case "see": {
			if (!sender.hasPermission("rpglevels.cmd.see")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			if (args.length != 2) {
				sender.sendMessage(
						"§c(§eRPGLevels§c) §cНе правильный §7ввод команды. Использование: §c/level see <Игрок>");
				return false;
			}
			if (!DataManager.getPlayers().contains(args[1])) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Игрок §e" + args[1] + " §cне найден§7.");
				return false;
			}
			boolean isOnline = false;
			Player p = null;
			RPGPlayer rpg = null;
			if (Bukkit.getPlayer(args[1]) != null) {
				isOnline = true;
				p = Bukkit.getPlayer(args[1]);
				rpg = RPGLevels.rpg.get(p);
			}
			int lvl = 0;
			lvl = (isOnline ? rpg.getLvl() : DataManager.getPlayerDataInt(args[1], "lvl"));
			int exp = 0;
			exp = (isOnline ? rpg.getExp() : DataManager.getPlayerDataInt(args[1], "exp"));
			int skills = 0;
			skills = (isOnline ? rpg.getSkills() : DataManager.getPlayerDataInt(args[1], "skills"));
			int m = this.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
			double mana = 0.0;
			mana = (isOnline ? rpg.getMana() : DataManager.getPlayerDataInt(args[1], "mana"));
			String clas = "";
			clas = (isOnline ? rpg.getPclass() : DataManager.getPlayerDataString(args[1], "class"));
			if (clas.isEmpty()) {
				clas = "Не выбран";
			}
			double maxMana = Utilities.getPlayerMaxMana(args[1]);
			sender.sendMessage("§7=============§c (§e " + args[1] + " §c) §7=============");
			sender.sendMessage("");
			if (sender.hasPermission("rpglevels.cmd.see.class")) {
				sender.sendMessage("§c> §7Класс игрока: §c" + clas);
			}
			if (sender.hasPermission("rpglevels.cmd.see.lvl")) {
				sender.sendMessage("§c> §7Уровень игрока: §c" + String.valueOf(lvl));
			}
			if (sender.hasPermission("rpglevels.cmd.see.skills")) {
				sender.sendMessage("§c> §7Скилы игрока: §c" + String.valueOf(skills));
			}
			if (sender.hasPermission("rpglevels.cmd.see.exp") && lvl < m) {
				sender.sendMessage("§c> §7Опыт игрока: §c" + String.valueOf(exp) + " §7из §c"
						+ this.plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost"));
			}
			if (sender.hasPermission("rpglevels.cmd.see.mana")) {
				sender.sendMessage(
						"§c> §7Мана игрока: §c" + String.valueOf(mana) + " §7из §c" + String.valueOf(maxMana));
			}

			double heal = (isOnline ? p.getHealth() : DataManager.getPlayerDataDouble(args[1], "lastheal"));

			double maxHeal = (isOnline ? rpg.getHeal() : DataManager.getPlayerDataDouble(args[1], "heal"));
			if (sender.hasPermission("rpglevels.cmd.see.heal")) {
				sender.sendMessage("§c> §7Жизни игрока: §c" + String.format("%.1f", heal).replace(",", ".") + " §7из §c"
						+ String.format("%.1f", maxHeal).replace(",", "."));
			}
			sender.sendMessage("\n§7=============================================");
			return true;
		}
		case "set": {
			if (!sender.hasPermission("rpglevels.cmd.set")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			if (args.length != 4) {
				sender.sendMessage(
						"§c(§eRPGLevels§c) §cНе правильный §7ввод команды. Использование: §c/level set <Игрок> <lvl или exp или skills или mana> <количество>");
				return false;
			}
			if (!DataManager.getPlayers().contains(args[1])) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Игрок §e" + args[1] + " §cне найден§7.");
				return false;
			}
			if (!args[3].matches("-?\\d+(\\.\\d+)?") && !args[2].equals("class")) {
				sender.sendMessage(
						"§c(§eRPGLevels§c) §cНе правильный §7ввод команды. Использование: §c/level set <Игрок> <lvl или exp или skills или mana> <количество>");
				return false;
			}
			double v = 0.0;
			int value = 0;
			if (!args[2].equals("class")) {
				v = Double.valueOf(args[3]);
				value = (int) v;
			}
			int max = this.plugin.getConfig().getConfigurationSection("").getKeys(false).size();
			boolean isOnline = false;
			Player p = null;
			RPGPlayer rpg = null;
			if (Bukkit.getPlayer(args[1]) != null) {
				isOnline = true;
				p = Bukkit.getPlayer(args[1]);
				rpg = RPGLevels.rpg.get(p);
			}
			switch (args[2]) {
			case "skills": {
				if (!sender.hasPermission("rpglevels.cmd.set.skills")) {
					sender.sendMessage(this.noperms);
					return false;
				}
				if (value < 0) {
					sender.sendMessage("§c(§eRPGLevels§c) §7Количество должно быть больше §c0§7!");
					return false;
				}
				if (isOnline) {
					rpg.setSkills(value);
					RPGLevels.rpg.put(p, rpg);
					p.sendMessage("§c(§eRPGLevels§c) §7Количество скилов обновлёно. Теперь вы имеете §c" + value
							+ " §7скилов.");
				} else {
					DataManager.setPlayerData(args[1], "skills", value);
				}
				sender.sendMessage(
						"§c(§eRPGLevels§c) §7Теперь игрок §c" + args[1] + " §7имеет §c" + value + " §7cкилов.");
				return true;
			}
			case "exp": {
				if (!sender.hasPermission("rpglevels.cmd.set.exp")) {
					sender.sendMessage(this.noperms);
					return false;
				}
				int lvl = 0;
				lvl = (isOnline ? rpg.getLvl() : DataManager.getPlayerDataInt(args[1], "lvl"));
				if (!this.plugin.getConfig().contains("Levels." + String.valueOf(lvl + 1))) {
					sender.sendMessage("§c(§eRPGLevels§c) §7Игрок §c" + args[1]
							+ " §7имеет максимальный уровень. Установить опыт §cневозможно§7.");
					return false;
				}
				int cost = this.plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");
				if (value < 0 || value > cost) {
					sender.sendMessage("§c(§eRPGLevels§c) §7На текущий момент игроку §c" + args[1]
							+ " §7 можно установить от §c0 до §c" + String.valueOf(cost) + " §7опыта.");
					return false;
				}
				if (isOnline) {
					rpg.setExp(value);
					RPGLevels.rpg.put(p, rpg);
					LevelUp.VisualLVL(p);
					Bukkit.getPlayer(args[1]).sendMessage(
							"§c(§eRPGLevels§c) §7Ваш опыт обновлён. Теперь вы имеете §c" + value + " §7опыта.");
				} else {
					DataManager.setPlayerData(args[1], "exp", value);
				}
				sender.sendMessage(
						"§c(§eRPGLevels§c) §7Теперь игрок §c" + args[1] + " §7имеет §c" + value + " §7опыта.");
				return true;
			}
			case "lvl": {
				if (!sender.hasPermission("rpglevels.cmd.set.lvl")) {
					sender.sendMessage(this.noperms);
					return false;
				}
				if (value < 0 || value > max) {
					sender.sendMessage("§c(§eRPGLevels§c) §7Уровень §e" + value
							+ " §cне существует§7. Максимальный уровень: §c" + String.valueOf(max) + "§7.");
					return false;
				}
				if (isOnline) {
					p.setLevel(value);
					rpg.setLvl(value);
					rpg.setExp(0);
					RPGLevels.rpg.put(p, rpg);
					LevelUp.VisualLVL(p);
					Bukkit.getPlayer(args[1]).sendMessage(
							"§c(§eRPGLevels§c) §7Ваш уровень обновлён. Теперь вы имеете §c" + value + " §7уровень.");
				} else {
					DataManager.setPlayerData(args[1], "lvl", value);
					DataManager.setPlayerData(args[1], "exp", 0);
				}
				sender.sendMessage(
						"§c(§eRPGLevels§c) §7Теперь игрок §c" + args[1] + " §7имеет §c" + value + " §7уровень.");
				return true;
			}
			case "mana": {
				if (!sender.hasPermission("rpglevels.cmd.set.mana")) {
					sender.sendMessage(this.noperms);
					return false;
				}
				double maxMana2 = Utilities.getPlayerMaxMana(args[1]);
				if (v < 0.0 || v > maxMana2) {
					sender.sendMessage("§c(§eRPGLevels§c) §7На текущий момент игроку §c" + args[1]
							+ " §7 можно установить от §c0 до §c" + String.valueOf(maxMana2) + " §7маны.");
					return false;
				}
				if (isOnline) {
					rpg.setMana(v);
					RPGLevels.rpg.put(p, rpg);
					p.sendMessage(
							"§c(§eRPGLevels§c) §7Количество маны обновлёно. Теперь вы имеете §c§7Количество маны обновлёно. Теперь вы имеете §c"
									+ v + " §7маны.");
				} else {
					DataManager.setPlayerData(args[1], "mana", v);
				}
				sender.sendMessage("§c(§eRPGLevels§c) §7Теперь игрок §c" + args[1] + " §7имеет §c" + v + " §7маны.");
				return true;
			}
			case "class": {
				if (!sender.hasPermission("rpglevels.cmd.set.class")) {
					sender.sendMessage(this.noperms);
					return false;
				}
				if (args[3].equals("\"\"")) {
					if (isOnline) {
						rpg.setPclass("");
						RPGLevels.rpg.put(p, rpg);
						p.sendMessage("§c(§eRPGLevels§c) §7Ваш класс был §cсброшен§7!");
					} else {
						DataManager.setPlayerData(args[1], "class", "");
					}
					sender.sendMessage("§c(§eRPGLevels§c) §7Класс игрока §c" + args[1] + " §7сброшен!");
					return true;
				}
				if (!DataManager.getClasses().contains(args[3])) {
					sender.sendMessage("§c(§eRPGLevels§c) §7Класс §c" + args[3] + "§7 не существует!");
					return false;
				}
				if (isOnline) {
					rpg.setPclass(args[3]);
					RPGLevels.rpg.put(p, rpg);
					p.sendMessage(
							"§c(§eRPGLevels§c) §7Класс обновлён. Теперь вы состоите в §c" + args[3] + " §7классе.");
				} else {
					DataManager.setPlayerData(args[1], "class", args[3]);
				}
				sender.sendMessage(
						"§c(§eRPGLevels§c) §7Теперь игрок §c" + args[1] + " §7состоит в §c" + args[3] + " §7классе.");
				return true;
			}
			default:
				break;
			}
			sender.sendMessage(
					"§c(§eRPGLevels§c) §cНе правильный §7ввод команды. Использование: §c/level set <Игрок> <lvl или exp или skills или mana или class> <количество> §7ввод команды. Использование: §c/level set <Игрок> <lvl или exp или skills > <количество>");
			return false;
		}
		case "help": {
			if (!sender.hasPermission("rpglevels.cmd.help")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			sender.sendMessage("§7=============§c (§eRPGLevels§c) §7=============");
			sender.sendMessage("\n");
			if (sender.hasPermission("rpglevels.cmd.see")) {
				sender.sendMessage("§c> /level see <Игрок> §7- узнать уровень игрока");
			}
			if (sender.hasPermission("rpglevels.cmd.set")) {
				sender.sendMessage(
						"§c> /level set <Игрок> <lvl | exp | skills | mana | class> <значение (для сброса class - \"\")> §7- обновить параметр");
			}
			if (sender.hasPermission("rpglevels.cmd.setfirstspawn")) {
				sender.sendMessage("§c> /level setfirstspawn §7- установить первичный спавн игроков");
			}
			if (sender.hasPermission("rpglevels.cmd.setrpgspawn")) {
				sender.sendMessage("§c> /level setrpgspawn §7- установить точку RPG спавна игроков");
			}
			if (sender.hasPermission("rpglevels.cmd.help")) {
				sender.sendMessage("§c> /level help §7- список доступных команд");
			}
			if (sender.hasPermission("rpglevels.cmd.class")) {
				sender.sendMessage("§c> /class §7- выбор класса / просмотр своей статистики");
			}
			if (sender.hasPermission("rpglevels.cmd.classcreate")) {
				sender.sendMessage("§c> /classcreate <имя класса> §7- создать новый класс");
			}
			if (sender.hasPermission("rpglevels.cmd.classremove")) {
				sender.sendMessage("§c> /classremove <имя класса> §7- удалить класс");
			}
			if (sender.hasPermission("rpglevels.cmd.classinfo")) {
				sender.sendMessage("§c> /classinfo <имя класса> §7- получить информацию о классе");
			}
			if (sender.hasPermission("rpglevels.cmd.classedit")) {
				sender.sendMessage(
						"§c> /classedit <имя класса> <info | item | defaultheal | changehealtolvl | defaultmana | changemanatolvl | manapersecond> <значение (для сброса info - \"\")> §7- редактировать параметр класса");
			}
			if (sender.hasPermission("rpglevels.cmd.reload")) {
				sender.sendMessage("§c> /level reload §7- перезагрузка конфига");
			}
			if (sender.hasPermission("rpglevels.cmd.sync")) {
				sender.sendMessage("§c> /level sync §7- загрузить данные из хранилища в память сервера");
			}
			if (sender.hasPermission("rpglevels.cmd.save")) {
				sender.sendMessage("§c> /level save §7- выгрузить данные из памяти сервера в хранилище");
			}
			sender.sendMessage("\n§7======================================");
			return true;
		}
		case "save": {
			if (!sender.hasPermission("rpglevels.cmd.save")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			sender.sendMessage("§c(§eRPGLevels§c) §cСохраняем данные в хранилище...");
			for (String classname : RPGLevels.rpgclass.keySet()) {
				DataManager.saveClassData(classname);
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				DataManager.savePlayerData(player);
			}
			sender.sendMessage("§c(§eRPGLevels§c) §cСохранение §cуспешно §7завершено!");
			return true;
		}
		case "sync": {
			if (!sender.hasPermission("rpglevels.cmd.sync")) {
				sender.sendMessage(this.noperms);
				return false;
			}
			sender.sendMessage("§c(§eRPGLevels§c) §cСинхронизируемся с хранилищем данных...");
			for (String classname : RPGLevels.rpgclass.keySet()) {
				DataManager.loadClassData(classname);
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				DataManager.loadPlayerData(player);
			}
			sender.sendMessage("§c(§eRPGLevels§c) §cСинхронизация §cуспешно §7завершена!");
			return true;
		}
		default:
			break;
		}
		sender.sendMessage("§c(§eRPGLevels§c) §7Подкоманда " + args[0]
				+ " §cне существует §7. Используйте §c/level help §7для помощи.");
		return false;
	}
}
