package angelok.RPGLevels.com;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdClassInfo implements CommandExecutor {

	public CmdClassInfo(RPGLevels plugin) {}

	String noperms = "§c(§eRPGLevels§c) §7У вас §cнедостаточно §7прав!";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		
		if(!sender.hasPermission("rpglevels.cmd.classinfo")){
			sender.sendMessage(noperms);
			return false;
		}
		
		if(args.length != 1){
			sender.sendMessage("§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/classinfo <название>");
			return false;
		}
		
		
		
		if(!DataManager.getClasses().contains(args[0])){
			sender.sendMessage("§c(§eRPGLevels§c) §7Класс §c" + args[0] + " §7не найден!");
			return false;
		}
		
		RPGClasses info = RPGLevels.rpgclass.get(args[0]);
		
		sender.sendMessage("§7======================§c (§e "+ args[0] +" §c) §7======================");
		sender.sendMessage("");

		//Выводим инфо о классе если имеется
		if(info.getInfo().isEmpty())
		sender.sendMessage("§c> §8[info]§7Информация о классе: §c<Не задана>");
		else{
    	sender.sendMessage("§c> §8[info]§7Информация о классе:");
    	String[] m = info.getInfo().split("\\\\");
    	for(int a = 0; a<m.length; a++)
    		if(!m[a].isEmpty())
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m[a]));
		}
		
		sender.sendMessage("§c> §8[item]§7Предмет в качестве иконки класса: §c" + info.getItem());
		
		sender.sendMessage("§c> §8[changehealtolvl]§7Количество добавляемого здоровья за повышение уровня: §c" + info.getChangehealtolvl());
		
		sender.sendMessage("§c> §8[changemanatolvl]§7Количество добавляемой маны за повышение уровня: §c" + info.getChangemanatolvl());
		
		sender.sendMessage("§c> §8[defaultmana]§7Лимит маны в классе по умолчанию: §c" + info.getDefaultmana());
		
		sender.sendMessage("§c> §8[defaultheal]§7Лимит здоровья в классе по умолчанию: §c" + info.getDefaultheal());

		sender.sendMessage("§c> §8[manapersecond]§7Скорость регенерации маны в классе: §c" + info.getManapersecond() + "/сек.");
		
		sender.sendMessage("\n§7===========================================================");
		return true;
	}

}
