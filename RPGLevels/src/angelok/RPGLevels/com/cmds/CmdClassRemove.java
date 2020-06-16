package angelok.RPGLevels.com.cmds;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import angelok.RPGLevels.com.DataManager;
import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGClasses;

public class CmdClassRemove implements CommandExecutor {


	private HashMap<String, RPGClasses> clas;

	public CmdClassRemove(HashMap<String, RPGClasses> clas) {
		this.clas = clas;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		
		if(!sender.hasPermission("rpglevels.cmd.classremove")){
			sender.sendMessage(Lang.nopermission());
			return false;
		}
		
		if(args.length != 1){
			sender.sendMessage(Lang.notvalidcmd());
			return false;
		}
		
		if(!clas.keySet().contains(args[0])){
			sender.sendMessage(Lang.classnotfound().replace("{Class}", args[0]));
			return false;
		}
		
		DataManager.RemoveClass(args[0]);
		clas.remove(args[0]);
		sender.sendMessage(Lang.classremoved().replace("{Class}", args[0]));
		
		return true;
	}

}
