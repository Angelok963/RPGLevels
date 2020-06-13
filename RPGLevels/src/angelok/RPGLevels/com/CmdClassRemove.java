package angelok.RPGLevels.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClassRemove implements CommandExecutor {


	public CmdClassRemove(RPGLevels plugin) {}

	String noperms = "§c(§eRPGLevels§c) §7У вас §cнедостаточно §7прав!";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		
		if(!sender.hasPermission("rpglevels.cmd.classremove")){
			sender.sendMessage(noperms);
			return false;
		}
		
		if(args.length != 1){
			sender.sendMessage("§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/classremove <название>");
			return false;
		}
		
		if(!DataManager.getClasses().contains(args[0])){
			sender.sendMessage("§c(§eRPGLevels§c) §7Класс §c" + args[0] + " §7не найден!");
			return false;
		}
		
		DataManager.RemoveClass(args[0]);
		RPGLevels.rpgclass.remove(args[0]);
		sender.sendMessage("§c(§eRPGLevels§c) §7Класс §c" + args[0] + " §7удалён!");
		
		return true;
	}

}
