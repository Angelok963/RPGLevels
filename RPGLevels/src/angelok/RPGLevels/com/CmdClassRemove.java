package angelok.RPGLevels.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClassRemove implements CommandExecutor {


	public CmdClassRemove(RPGLevels plugin) {}


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
		
		if(!DataManager.getClasses().contains(args[0])){
			sender.sendMessage(Lang.classnotfound().replace("{Class}", args[0]));
			return false;
		}
		
		DataManager.RemoveClass(args[0]);
		RPGLevels.rpgclass.remove(args[0]);
		sender.sendMessage(Lang.classremoved().replace("{Class}", args[0]));
		
		return true;
	}

}
