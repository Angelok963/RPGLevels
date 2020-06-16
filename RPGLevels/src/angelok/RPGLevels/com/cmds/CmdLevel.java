package angelok.RPGLevels.com.cmds;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import angelok.RPGLevels.com.AutoSaveData;
import angelok.RPGLevels.com.DataManager;
import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.LevelUp;
import angelok.RPGLevels.com.RPGClasses;
import angelok.RPGLevels.com.RPGLevels;
import angelok.RPGLevels.com.RPGPlayer;
import angelok.RPGLevels.com.Utilities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CmdLevel implements CommandExecutor {
	private RPGLevels plugin;
	private HashMap<Player, RPGPlayer> rpgp;
	private HashMap<String, RPGClasses> clas;
	@SuppressWarnings("unused")
	private YamlConfiguration lang;
	private YamlConfiguration classes;
	@SuppressWarnings("unused")
	private YamlConfiguration datap;
	private BukkitTask saveTask;
	private BukkitTask f;

	public CmdLevel(RPGLevels plugin, HashMap<Player, RPGPlayer> rpgp, HashMap<String, RPGClasses> clas,
			YamlConfiguration lang, YamlConfiguration classes, YamlConfiguration dataplayer, BukkitTask saveTask) {
		this.plugin = plugin;
		this.rpgp = rpgp;
		this.clas = clas;
		this.lang = lang;
		this.classes = classes;
		this.datap = dataplayer;
		this.saveTask = saveTask;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (!sender.hasPermission("rpglevels.cmd.level")) {
			sender.sendMessage(Lang.nopermission());
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(Lang.notvalidcmd());
			return false;
		}
		switch (args[0]) {
		case "setrpgspawn": {
			if (!sender.hasPermission("rpglevels.cmd.setrpgspawn")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(Lang.notconsole());
				return false;
			}
			Player p = (Player) sender;
			Location loc2 = p.getLocation();
			plugin.getConfig().set("rpgspawn",
					(String.valueOf(loc2.getWorld().getName()) + ":" + String.valueOf(loc2.getBlockX()) + ":"
							+ String.valueOf(loc2.getBlockY()) + ":" + String.valueOf(loc2.getBlockZ()) + ":"
							+ String.valueOf(loc2.getYaw()) + ":" + String.valueOf(loc2.getPitch())));
			plugin.saveConfig();
			p.sendMessage(Lang.saverpgspawn());
			return true;
		}
		case "setfirstspawn": {
			if (!sender.hasPermission("rpglevels.cmd.setfirstspawn")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(Lang.notconsole());
				return false;
			}
			Player p = (Player) sender;
			Location loc3 = p.getLocation();
			plugin.getConfig().set("firstspawn",
					(String.valueOf(loc3.getWorld().getName()) + ":" + String.valueOf(loc3.getBlockX()) + ":"
							+ String.valueOf(loc3.getBlockY()) + ":" + String.valueOf(loc3.getBlockZ()) + ":"
							+ String.valueOf(loc3.getYaw()) + ":" + String.valueOf(loc3.getPitch())));
			plugin.saveConfig();
			p.sendMessage(Lang.savefirstspawn());
			return true;
		}
		case "reload": {
			if (!sender.hasPermission("rpglevels.cmd.reload")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			String old_data = plugin.getConfig().getString("StorageType");
			plugin.reloadConfig();

			this.lang = YamlConfiguration
					.loadConfiguration(new File(plugin.getDataFolder() + File.separator + "lang.yml"));

			String new_data = plugin.getConfig().getString("StorageType");
			if (new_data.equals("file")) {
				File playerdata = new File(
						plugin.getDataFolder() + File.separator + "Data" + File.separator + "players.yml");
				File classdata = new File(
						plugin.getDataFolder() + File.separator + "Data" + File.separator + "classes.yml");
				if (!classdata.exists()) {
					RPGLevels.saveClassData();
					this.classes.set("Лучник.info", "Класс по умолчанию\\nможно менять");
					this.classes.set("Лучник.item", "BOW");
					this.classes.set("Лучник.defaultheal", 20.5);
					this.classes.set("Лучник.changehealtolvl", 5.5);
					this.classes.set("Лучник.defaultmana", 10.5);
					this.classes.set("Лучник.changemanatolvl", 10.5);
					this.classes.set("Лучник.manapersecond", 2.5);
					RPGLevels.saveClassData();
				}
				if (!playerdata.exists()) {
					RPGLevels.savePlayerData();
				}
				Utilities.loadDataPlayerYML(YamlConfiguration.loadConfiguration(
						new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "players.yml")));
				Utilities.loadDataClassesYML(YamlConfiguration.loadConfiguration(
						new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "classes.yml")));
			}
			String msg = "";
			if (old_data.equals(new_data)) {
				msg = Lang.cfgreload();
			} else {
				plugin.getConfig().set("StorageType", old_data);
				plugin.saveConfig();
				msg = Lang.cfgreload_warn();
			}
			if (plugin.getConfig().getBoolean("AutoSaveDataModule.enabled")) {
				if(this.saveTask != null)
				if (!this.saveTask.isCancelled()) {
					this.saveTask.cancel();
				}

				if (f != null)
					if (!f.isCancelled())
						f.cancel();

				f = Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

					@Override
					public void run() {

						saveTask = new AutoSaveData(plugin, clas, rpgp).runTaskTimerAsynchronously(plugin, 0L,
								(plugin.getConfig().getInt("AutoSaveDataModule.period") * 1200));

					}
				}, (plugin.getConfig().getInt("AutoSaveDataModule.period") * 1200));

			} else if (!this.saveTask.isCancelled()) {
				this.saveTask.cancel();
			}
			sender.sendMessage(msg);
			return true;
		}
		case "see": {
			if (!sender.hasPermission("rpglevels.cmd.see")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			if (args.length != 2) {
				sender.sendMessage(Lang.notvalidcmd());
				return false;
			}
			if (!DataManager.getPlayers().contains(args[1])) {
				sender.sendMessage(Lang.playernotfound().replace("{Player}", args[1]));
				return false;
			}
			boolean isOnline = false;
			Player p = null;
			RPGPlayer rpg = null;
			if (Bukkit.getPlayer(args[1]) != null) {
				isOnline = true;
				p = Bukkit.getPlayer(args[1]);
				rpg = rpgp.get(p);
			}
			int lvl = 0;
			lvl = (isOnline ? rpg.getLvl() : DataManager.getPlayerDataInt(args[1], "lvl"));
			int exp = 0;
			exp = (isOnline ? rpg.getExp() : DataManager.getPlayerDataInt(args[1], "exp"));
			int skills = 0;
			skills = (isOnline ? rpg.getSkills() : DataManager.getPlayerDataInt(args[1], "skills"));
			int m = plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
			double mana = 0.0;
			mana = (isOnline ? rpg.getMana() : DataManager.getPlayerDataInt(args[1], "mana"));
			String clas = "";
			clas = (isOnline ? rpg.getPclass() : DataManager.getPlayerDataString(args[1], "class"));
			if (clas.isEmpty()) {
				clas = Lang.empty();
			}
			double maxMana = Utilities.getPlayerMaxMana(args[1]);
			sender.sendMessage("§7=============§c (§e " + args[1] + " §c) §7=============");
			sender.sendMessage("");
			if (sender.hasPermission("rpglevels.cmd.see.class")) {
				sender.sendMessage(Lang.cmdsee_class().replace("{info}", clas));
			}
			if (sender.hasPermission("rpglevels.cmd.see.lvl")) {
				sender.sendMessage(Lang.cmdsee_lvl().replace("{info}", String.valueOf(lvl)));
			}
			if (sender.hasPermission("rpglevels.cmd.see.skills")) {
				sender.sendMessage(Lang.cmdsee_skills().replace("{info}", String.valueOf(skills)));
			}
			if (sender.hasPermission("rpglevels.cmd.see.exp") && lvl < m) {

				sender.sendMessage(Lang.cmdsee_exp().replace("{exp}", String.valueOf(exp)).replace("{exptolvl}",
						String.valueOf(plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost"))));
			}
			if (sender.hasPermission("rpglevels.cmd.see.mana")) {
				sender.sendMessage(Lang.cmdsee_mana().replace("{mana}", String.valueOf(mana)).replace("{maxmana}",
						String.valueOf(maxMana)));
			}

			double heal = (isOnline ? p.getHealth() : DataManager.getPlayerDataDouble(args[1], "lastheal"));

			double maxHeal = (isOnline ? rpg.getHeal() : DataManager.getPlayerDataDouble(args[1], "heal"));
			if (sender.hasPermission("rpglevels.cmd.see.heal")) {

				sender.sendMessage(Lang.cmdsee_heal().replace("{heal}", String.format("%.1f", heal).replace(",", "."))
						.replace("{maxheal}", String.format("%.1f", maxHeal).replace(",", ".")));
			}
			sender.sendMessage("\n§7=============================================");
			return true;
		}
		case "set": {
			if (!sender.hasPermission("rpglevels.cmd.set")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			if (args.length != 4) {
				sender.sendMessage(Lang.notvalidcmd());
				return false;
			}
			if (!DataManager.getPlayers().contains(args[1])) {
				sender.sendMessage(Lang.playernotfound().replace("{Player}", args[1]));
				return false;
			}
			if (!args[3].matches("-?\\d+(\\.\\d+)?") && !args[2].equals("class")) {
				sender.sendMessage(Lang.notvalidcmd());
				return false;
			}
			double v = 0.0;
			int value = 0;
			if (!args[2].equals("class")) {
				v = Double.valueOf(args[3]);
				value = (int) v;
			}
			int max = plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
			boolean isOnline = false;
			Player p = null;
			RPGPlayer rpg = null;
			if (Bukkit.getPlayer(args[1]) != null) {
				isOnline = true;
				p = Bukkit.getPlayer(args[1]);
				rpg = rpgp.get(p);
			}
			switch (args[2]) {
			case "skills": {
				if (!sender.hasPermission("rpglevels.cmd.set.skills")) {
					sender.sendMessage(Lang.nopermission());
					return false;
				}
				if (value < 0) {
					sender.sendMessage(Lang.warn_nuber());
					return false;
				}
				if (isOnline) {
					rpg.setSkills(value);
					rpgp.put(p, rpg);

					p.sendMessage(Lang.stateupdate_skills().replace("{amount}", String.valueOf(value)));
				} else {
					DataManager.setPlayerData(args[1], "skills", value);
				}
				sender.sendMessage(Lang.infoupdate_skills().replace("{Player}", args[1]).replace("{amount}",
						String.valueOf(value)));
				return true;
			}
			case "exp": {
				if (!sender.hasPermission("rpglevels.cmd.set.exp")) {
					sender.sendMessage(Lang.nopermission());
					return false;
				}
				int lvl = 0;
				lvl = (isOnline ? rpg.getLvl() : DataManager.getPlayerDataInt(args[1], "lvl"));
				if (!plugin.getConfig().contains("Levels." + String.valueOf(lvl + 1))) {
					sender.sendMessage(Lang.noupdateexp().replace("{Player}", args[1]));
					return false;
				}
				int cost = plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");
				if (value < 0 || value > cost) {
					sender.sendMessage(Lang.allowedamountexp().replace("{Player}", args[1]).replace("{maxexp}",
							String.valueOf(cost)));
					return false;
				}
				if (isOnline) {
					rpg.setExp(value);
					rpgp.put(p, rpg);
					new LevelUp(plugin, clas, rpgp).VisualLVL(p);
					Bukkit.getPlayer(args[1])
							.sendMessage(Lang.stateupdate_exp().replace("{amount}", String.valueOf(value)));

				} else {
					DataManager.setPlayerData(args[1], "exp", value);
				}
				sender.sendMessage(
						Lang.infoupdate_exp().replace("{Player}", args[1]).replace("{amount}", String.valueOf(value)));
				return true;
			}
			case "lvl": {
				if (!sender.hasPermission("rpglevels.cmd.set.lvl")) {
					sender.sendMessage(Lang.nopermission());
					return false;
				}
				if (value <= 0 || value > max) {
					sender.sendMessage(Lang.lvlnotexist().replace("{lvl}", String.valueOf(value)).replace("{maxlvl}",
							String.valueOf(max)));
					return false;
				}
				int lastlvl = 0;

				if (isOnline) {
					lastlvl = rpg.getLvl();
					p.setLevel(value);
					rpg.setLvl(value);
					rpg.setExp(0);
					rpgp.put(p, rpg);

					new LevelUp(plugin, clas, rpgp).VisualLVL(p);
					Bukkit.getPlayer(args[1])
							.sendMessage(Lang.stateupdate_lvl().replace("{amount}", String.valueOf(value)));
				} else {
					lastlvl = DataManager.getPlayerDataInt(args[1], "lvl");
					DataManager.setPlayerData(args[1], "lvl", value);
					DataManager.setPlayerData(args[1], "exp", 0);
				}

				int postlvl = Integer.valueOf(value);

				double editheal = postlvl - lastlvl;

				double f = 0;

				if (isOnline) {
					f = rpg.getHeal() + (editheal * clas.get(rpg.getPclass()).getChangehealtolvl());
						rpg.setHeal(f);
					p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(f);
				} else {
					f = DataManager.getPlayerDataDouble(args[1], "heal") + (editheal
							* clas.get(DataManager.getPlayerDataString(args[1], "class")).getChangehealtolvl());
					DataManager.setPlayerData(args[1], "heal", value);
					DataManager.setPlayerData(args[1], "heal", f);
				}

				sender.sendMessage(
						Lang.infoupdate_lvl().replace("{Player}", args[1]).replace("{amount}", String.valueOf(value)));
				return true;
			}
			case "mana": {
				if (!sender.hasPermission("rpglevels.cmd.set.mana")) {
					sender.sendMessage(Lang.nopermission());
					return false;
				}
				double maxMana2 = Utilities.getPlayerMaxMana(args[1]);
				if (v < 0.0 || v > maxMana2) {
					sender.sendMessage(Lang.allowedamountmana().replace("{Player}", args[1]).replace("{maxmana}",
							String.valueOf(maxMana2)));
					return false;
				}
				if (isOnline) {
					rpg.setMana(v);
					rpgp.put(p, rpg);

					p.sendMessage(Lang.stateupdate_mana().replace("{amount}", String.valueOf(v)));
				} else {
					DataManager.setPlayerData(args[1], "mana", v);
				}
				sender.sendMessage(
						Lang.infoupdate_mana().replace("{Player}", args[1]).replace("{amount}", String.valueOf(v)));
				return true;
			}
			case "class": {
				if (!sender.hasPermission("rpglevels.cmd.set.class")) {
					sender.sendMessage(Lang.nopermission());
					return false;
				}
				if (args[3].equals("\"\"")) {
					if (isOnline) {
						rpg.setPclass("");
						rpgp.put(p, rpg);
						p.sendMessage(Lang.stateupdate_class_reset());
					} else {
						DataManager.setPlayerData(args[1], "class", "");
					}
					sender.sendMessage(Lang.infoupdate_class_reset().replace("{Player}", args[1]));
					return true;
				}
				if (!this.clas.keySet().contains(args[3])) {
					sender.sendMessage(Lang.classnotfound().replace("{Class}", args[3]));
					return false;
				}
				if (isOnline) {
					rpg.setPclass(args[3]);
					rpgp.put(p, rpg);
					p.sendMessage(Lang.stateupdate_class().replace("{Class}", args[3]));
				} else {
					DataManager.setPlayerData(args[1], "class", args[3]);
				}
				sender.sendMessage(Lang.infoupdate_class().replace("{Player}", args[1]).replace("{Class}", args[3]));
				return true;
			}
			default:
				break;
			}
			sender.sendMessage(Lang.notvalidcmd());
			return false;
		}
		case "help": {
			if (!sender.hasPermission("rpglevels.cmd.help")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}

			return CmdHelp.sendHelpMsg(args, sender);

		}
		case "save": {
			if (!sender.hasPermission("rpglevels.cmd.save")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			sender.sendMessage(Lang.cmd_save_start());
			for (String classname : clas.keySet()) {
				DataManager.saveClassData(classname, clas);
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				DataManager.savePlayerData(player, rpgp);
			}
			sender.sendMessage(Lang.cmd_save_end());
			return true;
		}
		case "sync": {
			if (!sender.hasPermission("rpglevels.cmd.sync")) {
				sender.sendMessage(Lang.nopermission());
				return false;
			}
			sender.sendMessage(Lang.cmd_sync_start());

			if (plugin.getConfig().getString("StorageType").equals("file")) {
				Utilities.loadDataPlayerYML(YamlConfiguration.loadConfiguration(
						new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "players.yml")));
				Utilities.loadDataClassesYML(YamlConfiguration.loadConfiguration(
						new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "classes.yml")));
			}

			for (String classname : clas.keySet()) {
				clas = DataManager.loadClassData(classname, clas);
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				rpgp = DataManager.loadPlayerData(player, rpgp);

			}
			for (Player p : Bukkit.getOnlinePlayers())
				new LevelUp(plugin, clas, rpgp).VisualLVL(p);
			sender.sendMessage(Lang.cmd_sync_end());
			return true;
		}
		default:
			break;
		}
		sender.sendMessage(Lang.notcmdargument().replace("{CmdArg}", args[0]));
		return false;
	}
}
