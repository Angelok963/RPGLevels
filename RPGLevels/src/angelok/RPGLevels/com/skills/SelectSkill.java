package angelok.RPGLevels.com.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGPlayer;
import angelok.RPGLevels.com.Utilities;
import angelok.RPGLevels.com.AttributeManager.AttributeManager;

public class SelectSkill implements Listener {

	private HashMap<Player, RPGPlayer> rpg;
	private static YamlConfiguration skillscfg;
	private static HashMap<Player, Integer> pageSkills = new HashMap<>();
	private static HashMap<Player, String> MenuTitle = new HashMap<>();
	private ArrayList<ItemStack> skills;

	public SelectSkill(HashMap<Player, RPGPlayer> rpg, YamlConfiguration skillscfg) {
		this.rpg = rpg;
		SelectSkill.skillscfg = skillscfg;
	}

	public static void updateSkillsCfg(YamlConfiguration skillscfg) {
		SelectSkill.skillscfg = skillscfg;
	}

	@EventHandler
	public void select(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		RPGPlayer rpgplayer = this.rpg.get(p);

		ItemStack i = e.getItem();

		if (i == null)
			return;

		if (e.getAction() != Action.RIGHT_CLICK_AIR || !p.isSneaking())
			return;

		e.setCancelled(true);

		pageSkills.put(p, 1);

		getList(rpgplayer, i);

		int maxpage = (skills.size() - 1) / 36;
		maxpage++;

		String title = Lang.skillsmenutitle().replace("{skills}", String.valueOf(rpgplayer.getSkills()));

		MenuTitle.put(p, title);

		p.openInventory(Utilities.getPageMenu(1, maxpage, title, skills));

	}

	@EventHandler
	public void onClick(InventoryDragEvent e) {
		if (e.getInventory().getTitle().equals(MenuTitle.get((Player) e.getWhoClicked())))
			e.setCancelled(true);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Inventory inv = e.getInventory();

		if (!inv.getTitle().equals(MenuTitle.get((Player) e.getWhoClicked())))
			return;

		if (e.getClickedInventory() == null)
			return;

		e.setCancelled(true);

		if (e.getClickedInventory().getType() == InventoryType.PLAYER)
			return;

		Player p = (Player) e.getWhoClicked();

		RPGPlayer rpgplayer = rpg.get(p);

		int page = pageSkills.get(p);

		ItemStack click = e.getCurrentItem();

		if (click == null)
			return;
		if (!click.hasItemMeta())
			return;

		int maxpage = (skills.size() - 1) / 36;
		maxpage++;

		if (click.isSimilar(Utilities.getButton(Lang.creativemenuundo(), (short) 5))) {

			p.openInventory(Utilities.getPageMenu(page - 1, maxpage, Lang.creativemenutitle(), skills));
			pageSkills.put(p, page - 1);
			return;
		} else if (click.isSimilar(Utilities.getButton(Lang.creativemenunext(), (short) 5))) {

			p.openInventory(Utilities.getPageMenu(page + 1, maxpage, Lang.creativemenutitle(), skills));
			pageSkills.put(p, page + 1);
			return;
		}

		if (click.isSimilar(Utilities.getButton(" ", (short) 14)))
			return;
		if (click.isSimilar(Utilities.getButton(" ", (short) 14)))
			return;

		if (click.isSimilar(Utilities.getButton(" ", (short) 15)))
			return;
		ClickType a = e.getClick();

		String name = click.getItemMeta().getDisplayName().substring(2);

		String clas = rpgplayer.getPclass();

		if (!skillscfg.getStringList("Skills." + name + ".ClassUsage").contains(clas))
			return;

		PlayerInventory in = p.getInventory();

		switch (a) {
		case LEFT:

			in.setItemInMainHand(AttributeManager.setAttributeValue(in.getItemInMainHand(), "Selected_skill", name));

			p.closeInventory();

			return;

		case RIGHT:

			int lvl = rpgplayer.getLvlSkill(name);

			List<Integer> list = skillscfg.getIntegerList("Skills." + name + ".CostUpgrade");

			int maxlvl = list.size();

			if (lvl >= maxlvl)
				return;

			int cost = list.get(lvl);

			int skills = rpgplayer.getSkills();

			if (skills < cost) {

				p.sendMessage(Lang.not_skills());
				p.closeInventory();
				return;
			}

			rpgplayer.setSkills(skills - cost);

			rpgplayer.setLvlSkill(name, lvl + 1);

			rpg.put(p, rpgplayer);

			String title = Lang.skillsmenutitle().replace("{skills}", String.valueOf(rpgplayer.getSkills()));

			MenuTitle.put(p, title);

			p.openInventory(Utilities.getPageMenu(pageSkills.get(p), maxpage, title,
					getList(rpgplayer, in.getItemInMainHand())));

			return;
		default:
			return;
		}

	}

	public ArrayList<ItemStack> getList(RPGPlayer rpgplayer, ItemStack item_in_hand) {

		String clas = rpgplayer.getPclass();

		ArrayList<ItemStack> skills = new ArrayList<>();
		skills.add(new ItemStack(Material.AIR));

		for (String skill : skillscfg.getConfigurationSection("Skills").getKeys(false)) {

			if (!skillscfg.getStringList("Skills." + skill + ".ClassUsage").contains(clas))
				continue;

			ItemStack item = new ItemStack(Material.getMaterial(skillscfg.getString("Skills." + skill + ".ITEM")));
			ItemMeta meta = item.getItemMeta();

			String name = ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + skill);

			meta.setDisplayName(name);
			ArrayList<String> lore = new ArrayList<>();

			lore.add("§7=====( §c" + Lang.skillinfo() + " §7)=====");

			for (String l : skillscfg.getStringList("Skills." + skill + ".Info"))
				lore.add(ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + l));

			lore.add("§7================================");

			int lvl = rpgplayer.getLvlSkill(skill);

			List<Integer> cost = skillscfg.getIntegerList("Skills." + skill + ".CostUpgrade");

			int maxlvl = cost.size();

			if (lvl == 0)
				lore.add(Lang.skillinfo_lvl_not());
			else if (lvl != maxlvl)
				lore.add(Lang.skillinfo_lvl().replace("{lvl}", String.valueOf(lvl)));

			if (lvl == maxlvl)
				lore.add(Lang.skillinfo_lvl_max());
			else {
				lore.add(Lang.skillinfo_cost().replace("{cost}",
						String.valueOf(skillscfg.getIntegerList("Skills." + skill + ".CostUpgrade").get(lvl))));
				lore.add(Lang.skillsmenuinfo1());
			}

			String select = (AttributeManager.hasAttribute(item_in_hand, "Selected_skill"))
					? AttributeManager.getAttributeValueString(item_in_hand, "Selected_skill")
					: "";

			if (select.equals(name.substring(2)))
				lore.add(Lang.skillsmenuinfo3());
			else if (lvl != 0)
				lore.add(Lang.skillsmenuinfo2());

			meta.setLore(lore);
			item.setItemMeta(meta);
			skills.add(item);
		}

		this.skills = skills;
		return skills;
	}

}
