package angelok.RPGLevels.com;

import org.bukkit.event.EventHandler;
import org.bukkit.command.CommandSender;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.Listener;

public class TabCompeteCMD implements Listener {
	public TabCompeteCMD(RPGLevels plugin) {
	}

	@EventHandler
	public void expChange(TabCompleteEvent e) {
		CommandSender sender = e.getSender();
		String cmd = e.getBuffer();
		if (cmd.equals("/level ")) {
			List<String> list = new ArrayList<String>();
			if (sender.hasPermission("rpglevels.cmd.reload")) {
				list.add("reload");
			}
			if (sender.hasPermission("rpglevels.cmd.help")) {
				list.add("help");
			}
			if (sender.hasPermission("rpglevels.cmd.set")) {
				list.add("set");
			}
			if (sender.hasPermission("rpglevels.cmd.see")) {
				list.add("see");
			}
			if (sender.hasPermission("rpglevels.cmd.setfirstspawn")) {
				list.add("setfirstspawn");
			}
			if (sender.hasPermission("rpglevels.cmd.setrpgspawn")) {
				list.add("setrpgspawn");
			}
			e.setCompletions(list);
		}
		if ((cmd.equals("/level see ") && sender.hasPermission("rpglevels.cmd.see"))
				|| (cmd.equals("/level set ") && sender.hasPermission("rpglevels.cmd.set"))) {
			e.setCompletions(DataManager.getPlayers());
		}
		if ((cmd.equals("/classcreate ") && sender.hasPermission("rpglevels.cmd.classcreate"))
				|| (cmd.equals("/classremove ") && sender.hasPermission("rpglevels.cmd.classremove"))
				|| (cmd.equals("/classinfo ") && sender.hasPermission("rpglevels.cmd.classinfo"))
				|| (cmd.equals("/classedit ") && sender.hasPermission("rpglevels.cmd.classedit"))) {
			e.setCompletions(DataManager.getClasses());
		}
	}
}
