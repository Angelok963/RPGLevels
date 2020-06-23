package angelok.RPGLevels.com.cmds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGClasses;
import angelok.RPGLevels.com.RPGLevels;
import angelok.RPGLevels.com.RPGPlayer;
import angelok.RPGLevels.com.Utilities;

public class CmdClass implements CommandExecutor, Listener {

	private RPGLevels plugin;
	private HashMap<Player, RPGPlayer> rpgp;
	private HashMap<String, RPGClasses> clas;

	public CmdClass(RPGLevels plugin, HashMap<Player, RPGPlayer> rpgp, HashMap<String, RPGClasses> clas) {
		this.plugin = plugin;
		this.rpgp = rpgp;
		this.clas = clas;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.class")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		Player p = (Player) sender;
		RPGPlayer rpg = rpgp.get(p);

		if (!rpg.getPclass().isEmpty()) {

			int lvl = rpg.getLvl();

			String msg = Lang.personalinfo().replace("{Class}", rpg.getPclass()).replace("{lvl}", String.valueOf(lvl));

			if (plugin.getConfig().contains("Levels." + String.valueOf(lvl + 1)))
				msg = msg + " " + Lang.personalinfo_exp().replace("{Exp}", String.valueOf(rpg.getExp())).replace(
						"{ExpTolvl}",
						String.valueOf(plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost")));

			p.sendMessage(msg);
			return false;
		}

		int t = 0;
		int s = clas.size();

		if (s <= 9)
			t = 27;
		else if (s <= 18)
			t = 36;
		else if (s <= 27)
			t = 45;
		else
			t = 54;

		Inventory inv = Bukkit.createInventory(null, t, Lang.chooseclasstitle());

		ArrayList<String> name = new ArrayList<>(clas.keySet());

		for (int a = 0; a < t - 9; a++) {

			if (a < name.size()) {

				if (clas.get(name.get(a)).getItem().toUpperCase().equals("AIR"))
					inv.setItem(a + 9, Utilities.getButton(" ", (short) 15));
				else {

					ItemStack i = new ItemStack(Material.getMaterial(clas.get(name.get(a)).getItem().toUpperCase()));

					ItemMeta m = i.getItemMeta();

					String st = clas.get(name.get(a)).getInfo();

					if (!st.isEmpty()) {

						List<String> lore = new ArrayList<>();

						String z[] = st.split("\\\\");

						for (int b = 0; b < z.length; b++)
							if (!z[b].isEmpty())
								lore.add(ChatColor.translateAlternateColorCodes('&', ChatColor.RESET +  z[b]));

						m.setLore(lore);

					}

					m.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + name.get(a)));

					i.setItemMeta(m);

					inv.setItem(a + 9, i);
				}
			}
		}

		for (int g = 0; g < t; g++) {
			if (inv.getItem(g) == null)
				inv.setItem(g, Utilities.getButton(" ", (short) 15));
		}

		p.openInventory(inv);

		return true;
	}

	

	@EventHandler
	public void noDragg(InventoryDragEvent e) {

		if (e.getInventory().getTitle().equals(Lang.chooseclasstitle()))
			e.setCancelled(true);

	}

	@EventHandler
	public void Choose(InventoryClickEvent e) throws SQLException {

		if (!e.getInventory().getTitle().equals(Lang.chooseclasstitle()))
			return;

		e.setCancelled(true);

		Player p = (Player) e.getWhoClicked();
		RPGPlayer rpg = rpgp.get(p);

		ItemStack i = e.getCurrentItem();

		if (e.getAction() != InventoryAction.PICKUP_ALL)
			return;

		if (i == null)
			return;

		if (i.getDurability() == 15)
			return;

		String clas = i.getItemMeta().getDisplayName().substring(2);

		rpg.setPclass(clas);

		p.closeInventory();

		String[] loc = plugin.getConfig().getString("rpgspawn").split(":");

		p.teleport(new Location(Bukkit.getWorld(loc[0]), Integer.valueOf(loc[1]), Integer.valueOf(loc[2]),
				Integer.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

		p.setLevel(1);

		rpg.setLvl(1);

		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.clas.get(clas).getDefaultheal());

		rpg.setHeal(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());

		rpgp.put(p, rpg);

		p.sendMessage(Lang.choosedclass().replace("{Class}", clas));
	}

}
