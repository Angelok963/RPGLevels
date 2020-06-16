package angelok.RPGLevels.com;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import angelok.RPGLevels.com.AttributeManager.AttributeManager;

public class CmdAttributeManage implements CommandExecutor {

	public CmdAttributeManage(RPGLevels plugin) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.attributemanage")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.notconsole());
			return false;
		}

		Player p = (Player) sender;

		ItemStack i = p.getInventory().getItemInMainHand();

		if (i.getType() == Material.AIR) {
			sender.sendMessage(Lang.notiteminhand());
			return false;
		}

		if (args.length < 1) {
			p.sendMessage(Lang.notvalidcmd());
			return false;
		}

		switch (args[0]) {
		case "list":

			if (!p.hasPermission("rpglevels.cmd.attributemanage.list")) {
				p.sendMessage(Lang.nopermission());
				return false;
			}

			ArrayList<String> list = AttributeManager.getAttributeList(i);

			if (list == null) {
				p.sendMessage(Lang.notattributs());
				return false;
			}

			p.sendMessage("§7==================( " + Lang.attributesee_top()+ " §7)==================");
			for (int a = 0; a < list.size(); a++)
				
			p.sendMessage(Lang.attributesee_list().replace("{Number}", String.valueOf(a+1)).replace("{AttributeName}", list.get(a))
					.replace("{AttributeValue}", String.valueOf((!AttributeManager.getAttributeValueString(i, list.get(a)).isEmpty()) ?
							AttributeManager.getAttributeValueString(i, list.get(a)) : AttributeManager.getAttributeValueDouble(i, list.get(a)))));
			
			
			p.sendMessage("§7==================================================================");
			return true;

		case "remove":

			if (!p.hasPermission("rpglevels.cmd.attributemanage.remove")) {
				p.sendMessage(Lang.nopermission());
				return false;
			}

			if (args.length != 2) {

				p.sendMessage(Lang.notvalidcmd());
				return false;
			}

			if (!AttributeManager.hasAttribute(i, args[1])) {
				p.sendMessage(Lang.notattribute().replace("{AttributeName}", args[1]));
				return false;
			}

			p.getInventory().setItemInMainHand(AttributeManager.setAttributeValue(i, args[1], null));
			p.sendMessage(Lang.removeattribute().replace("{AttributeName}", args[1]));
			return true;

		case "set":
			if (!p.hasPermission("rpglevels.cmd.attributemanage.set")) {
				p.sendMessage(Lang.nopermission());
				return false;
			}

			if (args.length != 3) {

				p.sendMessage(Lang.notvalidcmd());
				return false;
			}
			ItemStack t = null;
			if (args[2].matches("-?\\d+(\\.\\d+)?"))
				t = AttributeManager.setAttributeValue(i, args[1], Double.valueOf(args[2]));
			else
				t = AttributeManager.setAttributeValue(i, args[1], args[2]);

			p.getInventory().setItemInMainHand(t);
			p.sendMessage(Lang.setattribute().replace("{AttributeName}", args[1]).replace("{AttributeValue}", args[2]));
			return true;
		default:
			sender.sendMessage(Lang.notcmdargument().replace("{CmdArg}", args[0]));
			return false;
		}

	}
}
