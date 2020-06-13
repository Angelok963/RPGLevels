package angelok.RPGLevels.com;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClassEdit implements CommandExecutor {

	public CmdClassEdit(RPGLevels plugin) {
	}

	String noperms = "§c(§eRPGLevels§c) §7У вас §cнедостаточно §7прав!";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.classedit")) {
			sender.sendMessage(noperms);
			return false;
		}

		if (args.length < 3) {
			sender.sendMessage(
					"§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/classedit <имя класса> <info | item | defaultheal | changehealtolvl | defaultmana | "
							+ "changemanatolvl | manapersecond> <значение>");
			return false;
		}

		if (!DataManager.getClasses().contains(args[0])) {
			sender.sendMessage("§c(§eRPGLevels§c) §7Класс §c" + args[0] + " §7не найден!");
			return false;
		}

		RPGClasses rpg = RPGLevels.rpgclass.get(args[0]);

		boolean isNumber = false;
		if (args[2].matches("-?\\d+(\\.\\d+)?"))
			isNumber = true;

		switch (args[1]) {
		case "item":

			if (!sender.hasPermission("rpglevels.cmd.classedit.item")) {
				sender.sendMessage(noperms);
				return false;
			}

			try {
				Material.valueOf(args[2]);

			} catch (IllegalArgumentException ex) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Предмета §c" + args[2] + " §7не существует!");
				return false;
			}
			rpg.setItem(args[2]);
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Предмета §c" + args[2] + " §7установлен иконкой класса.");
			return true;

		case "defaultheal":

			if (!sender.hasPermission("rpglevels.cmd.classedit.defaultheal")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (!isNumber) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Значение должно быть §cчисловое§7. Пример: §c10.5");
				return false;
			}

			rpg.setDefaultheal(Double.valueOf(args[2]));
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Установлено значение здоровья в классе по умолчанию: §c" + args[2]);
			return true;

		case "changehealtolvl":

			if (!sender.hasPermission("rpglevels.cmd.classedit.changehealtolvl")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (!isNumber) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Значение должно быть §cчисловое§7. Пример: §c10.5");
				return false;
			}

			rpg.setChangehealtolvl(Double.valueOf(args[2]));
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Установлено значение здоровья, добавляемого за повышение уровня: §c"
					+ args[2]);
			return true;

		case "defaultmana":

			if (!sender.hasPermission("rpglevels.cmd.classedit.defaultmana")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (!isNumber) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Значение должно быть §cчисловое§7. Пример: §c10.5");
				return false;
			}
			rpg.setDefaultmana(Double.valueOf(args[2]));
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Установлено значение маны в классе по умолчанию: §c" + args[2]);
			return true;

		case "changemanatolvl":

			if (!sender.hasPermission("rpglevels.cmd.classedit.changemanatolvl")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (!isNumber) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Значение должно быть §cчисловое§7. Пример: §c10.5");
				return false;
			}

			rpg.setChangemanatolvl(Double.valueOf(args[2]));
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage(
					"§c(§eRPGLevels§c) §7Установлено значение маны, добавляемой за повышение уровня: §c" + args[2]);
			return true;

		case "manapersecond":

			if (!sender.hasPermission("rpglevels.cmd.classedit.manapersecond")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (!isNumber) {
				sender.sendMessage("§c(§eRPGLevels§c) §7Значение должно быть §cчисловое§7. Пример: §c10.5");
				return false;
			}

			rpg.setManapersecond(Double.valueOf(args[2]));
			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Установлено скорость регенерации маны: §c" + args[2] + "/сек.");
			return true;

		case "info":

			if (!sender.hasPermission("rpglevels.cmd.classedit.info")) {
				sender.sendMessage(noperms);
				return false;
			}

			if (args[2].equals("\"\"")) {
				rpg.setInfo("");

				RPGLevels.rpgclass.put(args[0], rpg);
				sender.sendMessage("§c(§eRPGLevels§c) §7Информация о классе §cсброшена§7.");
				return true;

			}

			String info = "";
			for (int a = 2; a < args.length; a++) {
				info = info + " " + args[a];
			}

			info = info.substring(1);

			rpg.setInfo(info);

			RPGLevels.rpgclass.put(args[0], rpg);
			sender.sendMessage("§c(§eRPGLevels§c) §7Установлена информация о классе:");

			String[] v = info.split("\\\\");

			for (int s = 0; s < v.length; s++)
				if(!v[s].isEmpty())
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', v[s]));
			return true;

		default:

			sender.sendMessage("§c(§eRPGLevels§c) §7Параметр §c" + args[1] + " §7не существует!");
			return false;
		}

	}

}
