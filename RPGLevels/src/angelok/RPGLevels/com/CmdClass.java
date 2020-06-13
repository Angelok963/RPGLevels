package angelok.RPGLevels.com;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class CmdClass implements CommandExecutor, Listener {

	private RPGLevels plugin;

	public CmdClass(RPGLevels plugin) {
		this.plugin = plugin;
	}

	String noperms = "§c(§eRPGLevels§c) §6У вас §cнедостаточно §7прав!";
	String noconsole = "§c(§eRPGLevels§c) §6Команда §cнедоступна §7из консоли!";
	String Title = "§6[§0Выберите класс, за который будете играть§6]";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if (!sender.hasPermission("rpglevels.cmd.class")) {
			sender.sendMessage(noperms);
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage(noconsole);
			return false;
		}

		Player p = (Player) sender;
		RPGPlayer rpg = RPGLevels.rpg.get(p);

		if (!rpg.getPclass().isEmpty()) {

			int lvl = rpg.getLvl();
			
			String msg = "§c(§eRPGLevels§c) §7Ваш класс: §c" + rpg.getPclass()
					+ "§7. Уровень: §c" + String.valueOf(lvl);

			if (plugin.getConfig().contains("Levels." + String.valueOf(lvl + 1)))
				msg = msg + "§7, опыт: §c" + rpg.getExp() + " §7из §c"
						+ plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost") + "§7.";

			p.sendMessage(msg);
			return false;
		}

		int t = 0;
		int s = RPGLevels.rpgclass.size();

		if (s <= 9)
			t = 27;
		else if (s <= 18)
			t = 36;
		else if (s <= 27)
			t = 45;
		else
			t = 54;

		Inventory inv = Bukkit.createInventory(null, t, Title);

		ArrayList<String> name = new ArrayList<>();

		for (String j : DataManager.getClasses()) {
			name.add(j);
		}

		for (int a = 0; a < t - 9; a++) {

			if (a < name.size()) {

				if (RPGLevels.rpgclass.get(name.get(a)).getItem().toUpperCase().equals("AIR"))
					inv.setItem(a + 9, getButton(" ", (short) 15));
				else {

					
					ItemStack i = new ItemStack(Material.getMaterial(RPGLevels.rpgclass.get(name.get(a)).getItem().toUpperCase()));
					
					ItemMeta m = i.getItemMeta();

					String st = RPGLevels.rpgclass.get(name.get(a)).getInfo();
					
					if (!st.isEmpty()) {

						List<String> lore = new ArrayList<>();

						String z[] = st.split("\\\\");
						
						for(int b = 0; b<z.length; b++)
							if(!z[b].isEmpty())
							lore.add(ChatColor.translateAlternateColorCodes('&', z[b]));
						
						m.setLore(lore);

					}

					m.setDisplayName(ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + name.get(a)));

					i.setItemMeta(m);

					inv.setItem(a + 9, i);
				}
			}
		}
		
		for(int g = 0; g<t; g++){
			if(inv.getItem(g) == null)
				inv.setItem(g, getButton(" ", (short) 15));	
		}
		
		p.openInventory(inv);

		return true;
	}

	private ItemStack getButton(String name, short data) {

		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability(data);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(name);
		i.setItemMeta(m);

		return i;
	}

	@EventHandler
	public void noDragg(InventoryDragEvent e) {

		if (e.getInventory().getTitle().equals(Title))
			e.setCancelled(true);

	}

	@EventHandler
	public void Choose(InventoryClickEvent e) throws SQLException {

		if (!e.getInventory().getTitle().equals(Title))
			return;

		e.setCancelled(true);

		Player p = (Player) e.getWhoClicked();
		RPGPlayer rpg = RPGLevels.rpg.get(p);

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
		
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(RPGLevels.rpgclass.get(clas).getDefaultheal());
		
		rpg.setHeal(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
		
		RPGLevels.rpg.put(p, rpg);
		
		p.sendMessage("§c(§eRPGLevels§c) §7Выбран класс §c" + clas + "§7!");		
	}

}
