package angelok.RPGLevels.com.cmds;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGClasses;


public class CmdClassEdit implements CommandExecutor {

	private HashMap<String, RPGClasses> clas;

	public CmdClassEdit(HashMap<String, RPGClasses> clas) {
		this.clas = clas;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.classedit")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		if (args.length < 3) {
			sender.sendMessage(Lang.notvalidcmd());
			return false;
		}

		if (!clas.keySet().contains(args[0])) {
			sender.sendMessage(Lang.classnotfound().replace("{Class}", args[0]));
			return false;
		}

		RPGClasses rpg = clas.get(args[0]);

		boolean isNumber = false;
		if (args[2].matches("-?\\d+(\\.\\d+)?"))
			isNumber = true;

		switch (args[1]) {
		case "item":

			if (!sender.hasPermission("rpglevels.cmd.classedit.item")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			try {
				Material.valueOf(args[2]);

			} catch (IllegalArgumentException ex) {
				sender.sendMessage(Lang.itemnotexist().replace("{Item}", args[2]));
				return false;
			}
			rpg.setItem(args[2]);
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.iconset().replace("{Item}", args[2]));
			return true;

		case "defaultheal":

			if (!sender.hasPermission("rpglevels.cmd.classedit.defaultheal")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (!isNumber) {
				sender.sendMessage(Lang.numberwarn());
				return false;
			}

			rpg.setDefaultheal(Double.valueOf(args[2]));
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.setdefaultheal().replace("{Heal}", args[2]));
			return true;

		case "changehealtolvl":

			if (!sender.hasPermission("rpglevels.cmd.classedit.changehealtolvl")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (!isNumber) {
				sender.sendMessage(Lang.numberwarn());
				return false;
			}

			rpg.setChangehealtolvl(Double.valueOf(args[2]));
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.setchangehealtolvl().replace("{Heal}", args[2]));
			return true;

		case "defaultmana":

			if (!sender.hasPermission("rpglevels.cmd.classedit.defaultmana")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (!isNumber) {
				sender.sendMessage(Lang.numberwarn());
				return false;
			}
			rpg.setDefaultmana(Double.valueOf(args[2]));
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.setdefaultmana().replace("{Mana}", args[2]));
			return true;

		case "changemanatolvl":

			if (!sender.hasPermission("rpglevels.cmd.classedit.changemanatolvl")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (!isNumber) {
				sender.sendMessage(Lang.numberwarn());
				return false;
			}

			rpg.setChangemanatolvl(Double.valueOf(args[2]));
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.setchangemanatolvl().replace("{Mana}", args[2]));
			return true;

		case "manapersecond":

			if (!sender.hasPermission("rpglevels.cmd.classedit.manapersecond")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (!isNumber) {
				sender.sendMessage(Lang.numberwarn());
				return false;
			}

			rpg.setManapersecond(Double.valueOf(args[2]));
			clas.put(args[0], rpg);
			sender.sendMessage(Lang.setmanapersecond().replace("{Mana}", args[2]));
			return true;

		case "info":

			if (!sender.hasPermission("rpglevels.cmd.classedit.info")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			if (args[2].equals("\"\"")) {
				rpg.setInfo("");

				clas.put(args[0], rpg);
				sender.sendMessage(Lang.classinforeset());
				return true;

			}

			String info = "";
			for (int a = 2; a < args.length; a++) {
				info = info + " " + args[a];
			}

			info = info.substring(1);

			rpg.setInfo(info);

			clas.put(args[0], rpg);
			sender.sendMessage(Lang.classinfoset());

			String[] v = info.split("\\\\");

			for (int s = 0; s < v.length; s++)
				if (!v[s].isEmpty())
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', v[s]));
			return true;

		default:

			sender.sendMessage(Lang.notcmdargument().replace("{CmdArg}", args[1]));
			return false;
		}

	}

}
