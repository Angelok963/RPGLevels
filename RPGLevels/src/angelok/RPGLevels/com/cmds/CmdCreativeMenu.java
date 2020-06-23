package angelok.RPGLevels.com.cmds;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGLevels;
import angelok.RPGLevels.com.Utilities;

public class CmdCreativeMenu implements Listener {

	private static YamlConfiguration items;

	private static HashMap<Player, Integer> pageId = new HashMap<>();

	private static ArrayList<ItemStack> item;

	public CmdCreativeMenu(YamlConfiguration items) {
		CmdCreativeMenu.items = items;

	}

	public static boolean creative(CommandSender sender) {

		if (!sender.hasPermission("rpglevels.cmd.menu")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage(Lang.notconsole());
			return false;
		}

		if (!items.contains("Data")) {
			items.set("Data.0", new ItemStack(Material.AIR));
			RPGLevels.saveItemsData();

		}

		ArrayList<ItemStack> item = new ArrayList<>();
		for (String key : items.getConfigurationSection("Data").getKeys(false))
			item.add(items.getItemStack("Data." + key));

		pageId.put(((Player) sender), 1);
		CmdCreativeMenu.item = item;

		int maxpage = (item.size() - 1) / 36;
		maxpage++;
		((Player) sender).openInventory(Utilities.getPageMenu(1, maxpage, Lang.creativemenutitle(), CmdCreativeMenu.item));
		return true;
	}

	@EventHandler
	public void onClick(InventoryDragEvent e) {
		if (e.getInventory().getTitle().equals(Lang.creativemenutitle()))
			e.setCancelled(true);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Inventory inv = e.getInventory();

		if (!inv.getTitle().equals(Lang.creativemenutitle()))
			return;

		if (e.getClickedInventory() == null)
			return;

		if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
			e.setCancelled(true);

		if (e.getClickedInventory().getType() == InventoryType.PLAYER)
			return;

		e.setCancelled(true);

		Player p = (Player) e.getWhoClicked();

		int page = pageId.get(p);

		ItemStack click = e.getCurrentItem();

		if (click == null)
			return;

		if (click.isSimilar(Utilities.getButton(Lang.creativemenuundo(), (short) 5))) {

			int maxpage = (item.size() - 1) / 36;
			maxpage++;
			p.openInventory(Utilities.getPageMenu(pageId.get(p)-1, maxpage, Lang.creativemenutitle(), CmdCreativeMenu.item));
			pageId.put(p, pageId.get(p) - 1);
			return;
		} else if (click.isSimilar(Utilities.getButton(Lang.creativemenunext(), (short) 5))) {
			int maxpage = (item.size() - 1) / 36;
			maxpage++;
			p.openInventory(Utilities.getPageMenu(pageId.get(p)+1, maxpage, Lang.creativemenutitle(), CmdCreativeMenu.item));
			pageId.put(p, pageId.get(p) + 1);
			return;
		}

		if (click.isSimilar(Utilities.getButton(" ", (short) 14)))
			return;
		if (click.isSimilar(Utilities.getButton(" ", (short) 14)))
			return;

		if (click.isSimilar(Utilities.getButton(" ", (short) 15)))
			return;
		ClickType a = e.getClick();

		switch (a) {
		case LEFT: // Взять из меню 1 предмет. Если курсор занят - освободить
			if (e.getWhoClicked().getItemOnCursor().getType() == Material.AIR) {
				click.setAmount(1);
				e.getWhoClicked().setItemOnCursor(click);
			} else
				e.getWhoClicked().setItemOnCursor(null);
			return;

		case SHIFT_LEFT: // Взять стак предметов

			e.getWhoClicked().setItemOnCursor(click);

			ItemStack m = e.getWhoClicked().getItemOnCursor();
			m.setAmount(m.getMaxStackSize());

			e.getWhoClicked().setItemOnCursor(m);

			return;

		case SHIFT_RIGHT: // Удалить предмет

			if (e.getWhoClicked().getItemOnCursor().getType() != Material.AIR)
				return;

			inv.setItem(e.getSlot(), null);

			items.set("Data." + String.valueOf((page - 1) * 36 + e.getSlot() - 7), null);

			ArrayList<ItemStack> oldItem = new ArrayList<>();
			for (String key : items.getConfigurationSection("Data").getKeys(false))
				oldItem.add(items.getItemStack("Data." + key));

			items.set("Data", null);

			items.set("Data.0", new ItemStack(Material.AIR));

			for (int g = 0; g < oldItem.size(); g++) {
				if (oldItem.get(g).getType() != Material.AIR)
					items.set("Data." + String.valueOf(g + 1), oldItem.get(g));
			}

			RPGLevels.saveItemsData();

			ArrayList<ItemStack> item = new ArrayList<>();
			for (String key : items.getConfigurationSection("Data").getKeys(false))
				item.add(items.getItemStack("Data." + key));
			CmdCreativeMenu.item = item;

			int maxpage = (CmdCreativeMenu.item.size() - 1) / 36;
			maxpage++;

			int open = (maxpage > pageId.get(p)) ? pageId.get(p) : maxpage;

			p.openInventory(Utilities.getPageMenu(open, maxpage, Lang.creativemenutitle(), CmdCreativeMenu.item));

			return;

		case RIGHT: // Добавить предмет
			if (e.getWhoClicked().getItemOnCursor().getType() != Material.AIR) {

				int n = e.getSlot();

				while (inv.getItem(n) == null)
					n--;

				int slot = (page - 1) * 36 + n - 8;

				ItemStack f = e.getWhoClicked().getItemOnCursor();
				f.setAmount(1);

				items.set("Data." + String.valueOf(slot + 2), f);
				RPGLevels.saveItemsData();

				ArrayList<ItemStack> item2 = new ArrayList<>();
				for (String key : items.getConfigurationSection("Data").getKeys(false))
					item2.add(items.getItemStack("Data." + key));
				CmdCreativeMenu.item = item2;
				e.getWhoClicked().setItemOnCursor(null);

				int maxpage2 = (CmdCreativeMenu.item.size() - 1) / 36;
				maxpage2++;

				int open2 = (maxpage2 > pageId.get(p)) ? pageId.get(p) : maxpage2;

				p.openInventory(Utilities.getPageMenu(open2, maxpage2, Lang.creativemenutitle(), CmdCreativeMenu.item));

			}

			return;
		default:
			return;
		}

	}

}