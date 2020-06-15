package angelok.RPGLevels.com;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import angelok.RPGLevels.com.AttributeManager.AttributeManager;

public class CmdAttributeManage implements CommandExecutor {

	public CmdAttributeManage(RPGLevels plugin) {}

	String noperms = "§c(§eRPGLevels§c) §7У вас §cнедостаточно §7прав!";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
	if(!sender.hasPermission("rpglevels.cmd.attributemanage")) {
		sender.sendMessage(noperms);
		return false;
	}

	    if(!(sender instanceof Player)) {
	    sender.sendMessage("§c(§eRPGLevels§c) §7Команда доступна только §cигроку§7!");
	    return false;
	    }
	    
	    Player p = (Player)sender;
	
	    ItemStack i = p.getInventory().getItemInMainHand();
	    
	    if(i == null) {
	    	sender.sendMessage("§c(§eRPGLevels§c) §7Вы должны держать предмет в руке!");
		    return false;	
	    }
	
		if(args.length < 1) {
			p.sendMessage("§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/amanage <list | set | remove> [Название] [значение]");
			return false;
		}
		
		
		switch (args[0]) {
		case "list":
			
			if(!p.hasPermission("rpglevels.cmd.attributemanage.list")) {
				p.sendMessage(noperms);
				return false;	
			}
			
			ArrayList<String> list = AttributeManager.getAttributeList(i);
			
			if(list == null) {
				p.sendMessage("§c(§eRPGLevels§c) §7В этом предмете §cнет §7атрибутов!");
				return false;
			}
			
			p.sendMessage("§7==================( §cПросмотр атрибутов предмета §7)==================");
			for(int a = 0; a<list.size(); a++)
				p.sendMessage("§8" + (a+1) + " §7" + list.get(a) + "§c: " + (!AttributeManager.getAttributeValueString(i, list.get(a)).isEmpty() ? AttributeManager.getAttributeValueString(i, list.get(a)) : AttributeManager.getAttributeValueDouble(i, list.get(a))));
			p.sendMessage("§7=========================================================================");
			return true;

		case "remove":	
		
			if(!p.hasPermission("rpglevels.cmd.attributemanage.remove")) {
				p.sendMessage(noperms);
				return false;	
			}
			
			if(args.length != 2) {
				
				p.sendMessage("§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/amanage <list | set | remove> [Название] [значение]");
				return false;	
			}
			
			if(!AttributeManager.hasAttribute(i, args[1])) {
				p.sendMessage("§c(§eRPGLevels§c) §7Атрибута§c " + args[1] + " §7на предмете нет!");
				return false;	
			}
			
			p.getInventory().setItemInMainHand(AttributeManager.setAttributeValue(i, args[1], null));
			p.sendMessage("§c(§eRPGLevels§c) §7Атрибут§c " + args[1] + " §7удалён с предмета!");
			return true;
			
		case "set":
			if(!p.hasPermission("rpglevels.cmd.attributemanage.set")) {
				p.sendMessage(noperms);
				return false;	
			}
			
			if(args.length != 3) {
				
				p.sendMessage("§c(§eRPGLevels§c) §7Команда введена §cневерно§7. Используйте §c/amanage <list | set | remove> [значение]");
				return false;	
			}
			ItemStack t = null;
		if(args[2].matches("-?\\d+(\\.\\d+)?"))
			t = AttributeManager.setAttributeValue(i, args[1], Double.valueOf(args[2]));
		else t = AttributeManager.setAttributeValue(i, args[1], args[2]);
		
		p.getInventory().setItemInMainHand(t);
		p.sendMessage("§c(§eRPGLevels§c) §7Атрибут§c " + args[1] + " §7добавлен к предмету с значением \"§c"+ args[2] +"§7\"!");
		return true;
		default:
			sender.sendMessage("§c(§eRPGLevels§c) §7Подкоманда " + args[0]
					+ " §cне существует §7. Используйте §c/level help §7для помощи.");
			return false;
		}
		
	}
}
