package angelok.RPGLevels.com.cmds;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGClasses;

public class CmdClassCreate implements CommandExecutor {

	private HashMap<String, RPGClasses> clas;

	public CmdClassCreate(HashMap<String, RPGClasses> clas) {
		this.clas = clas;
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

		if (clas.keySet().contains(args[0])) {
			sender.sendMessage(Lang.classalreadycreated().replace("{Class}", args[0]));
			return false;
		}

		clas.put(args[0], new RPGClasses("", "DIAMOND", 20, 0, 20, 0, 1));

		sender.sendMessage(Lang.classcreated().replace("{Class}", args[0]));
		return true;
	}

}
