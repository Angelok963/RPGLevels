package angelok.RPGLevels.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClassCreate implements CommandExecutor {

	public CmdClassCreate(RPGLevels plugin) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.classcreate")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		if (args.length != 1) {
			sender.sendMessage(Lang.notvalidcmd());
			return false;
		}

		if (DataManager.getClasses().contains(args[0])) {
			sender.sendMessage(Lang.classalreadycreated().replace("{Class}", args[0]));
			return false;
		}

		RPGLevels.rpgclass.put(args[0], new RPGClasses("", "DIAMOND", 20, 0, 20, 0, 1));

		DataManager.setClassData(args[0], "info", "");
		DataManager.setClassData(args[0], "item", "DIAMOND");
		DataManager.setClassData(args[0], "defaultheal", 20);
		DataManager.setClassData(args[0], "changehealtolvl", 0);
		DataManager.setClassData(args[0], "defaultmana", 20);
		DataManager.setClassData(args[0], "changemanatolvl", 0);
		DataManager.setClassData(args[0], "manapersecond", 1);

		sender.sendMessage(Lang.classcreated().replace("{Class}", args[0]));
		return true;
	}

}
