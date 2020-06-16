package angelok.RPGLevels.com.cmds;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGClasses;

public class CmdClassInfo implements CommandExecutor {

	private HashMap<String, RPGClasses> clas;

	public CmdClassInfo(HashMap<String, RPGClasses> clas) {
		this.clas = clas;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		
		if(!sender.hasPermission("rpglevels.cmd.classinfo")){
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
		
		RPGClasses info = clas.get(args[0]);
		
		sender.sendMessage("§7======================§c (§e "+ args[0] +" §c) §7======================");
		sender.sendMessage("");

		//Выводим инфо о классе если имеется
		if(info.getInfo().isEmpty())
		sender.sendMessage(Lang.classinfo() + Lang.empty());
		else{
    	sender.sendMessage(Lang.classinfo());
    	String[] m = info.getInfo().split("\\\\");
    	for(int a = 0; a<m.length; a++)
    		if(!m[a].isEmpty())
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m[a]));
		}
		
		sender.sendMessage(Lang.classinfo_item().replace("{info}", info.getItem()));
		
		sender.sendMessage(Lang.classinfo_changehealtolvl().replace("{info}", String.valueOf(info.getChangehealtolvl())));
		
		sender.sendMessage(Lang.classinfo_changemanatolvl().replace("{info}", String.valueOf(info.getChangemanatolvl())));
		
		sender.sendMessage(Lang.classinfo_defaultmana().replace("{info}", String.valueOf(info.getDefaultmana())));
		
		sender.sendMessage(Lang.classinfo_defaultheal().replace("{info}", String.valueOf(info.getDefaultheal())));

		sender.sendMessage(Lang.classinfo_manapersecond().replace("{info}", String.valueOf(info.getManapersecond())));
		
		sender.sendMessage("\n§7===========================================================");
		return true;
	}

}
