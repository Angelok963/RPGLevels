package angelok.RPGLevels.com;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import angelok.RPGLevels.com.AttributeManager.AttributsManager;
import angelok.RPGLevels.com.baseAttributes.BlindnessEffect;
import angelok.RPGLevels.com.baseAttributes.DamageAbsorption;
import angelok.RPGLevels.com.baseAttributes.DamageBoost;
import angelok.RPGLevels.com.baseAttributes.FireEffect;
import angelok.RPGLevels.com.baseAttributes.MagicShield;
import angelok.RPGLevels.com.baseAttributes.PoisonEffect;
import angelok.RPGLevels.com.baseAttributes.RegenerationDamage;
import angelok.RPGLevels.com.baseAttributes.SlownessEffect;
import angelok.RPGLevels.com.baseAttributes.WitherEffect;
import angelok.RPGLevels.com.cmds.CmdAttributeManage;
import angelok.RPGLevels.com.cmds.CmdClass;
import angelok.RPGLevels.com.cmds.CmdClassCreate;
import angelok.RPGLevels.com.cmds.CmdClassEdit;
import angelok.RPGLevels.com.cmds.CmdClassInfo;
import angelok.RPGLevels.com.cmds.CmdClassRemove;
import angelok.RPGLevels.com.cmds.CmdCreativeMenu;
import angelok.RPGLevels.com.cmds.CmdLevel;
import angelok.RPGLevels.com.skills.SelectSkill;

public class RPGLevels extends JavaPlugin implements RPGLevelsAPI, AttributsManager {

	private final String s = File.separator;

	protected static Plugin plugin;

	protected static HashMap<Player, RPGPlayer> rpg = new HashMap<>();

	protected static HashMap<String, RPGClasses> rpgclass = new HashMap<>();

	private static HashMap<LivingEntity, Double> freezingmobs = new HashMap<>();
	private static HashMap<Player, Float> freezingplayers = new HashMap<>();

	private File playerdata = new File(getDataFolder() + s + "Data" + s + "players.yml");
	private File classdata = new File(getDataFolder() + s + "Data" + s + "classes.yml");
	private File filelang = new File(getDataFolder() + s + "lang.yml");
	private File fileitems = new File(getDataFolder() + s + "Data" + s + "customitems.yml");

	private File skillsCfg = new File(getDataFolder() + s + "SkillsCfg.yml");

	protected static YamlConfiguration datap;
	private static File pdata;

	protected static YamlConfiguration skillscfg;
	private static File skillscfgfile;

	protected static YamlConfiguration lang;
	protected static File langfile;

	protected static YamlConfiguration items;
	private static File itemsfile;

	protected static YamlConfiguration classes;
	private static File cdata;

	private static BukkitTask saveTask;

	@Override
	public void onEnable() {

		// загрузка креатив меню

		saveResource("Data" + s + "customitems.yml", false);

		// загрузка языкового файла

		saveResource("lang.yml", false);

		// Загрузка конфигурации скилов
		saveResource("SkillsCfg.yml", false);

		RPGLevels.plugin = this;
		skillscfgfile = skillsCfg;
		langfile = filelang;

		pdata = playerdata;
		cdata = classdata;
		itemsfile = fileitems;
		YamlConfiguration items = YamlConfiguration.loadConfiguration(itemsfile);
		YamlConfiguration datap = YamlConfiguration.loadConfiguration(playerdata);
		YamlConfiguration classes = YamlConfiguration.loadConfiguration(classdata);
		YamlConfiguration lang = YamlConfiguration.loadConfiguration(langfile);
		YamlConfiguration skillscfg = YamlConfiguration.loadConfiguration(skillscfgfile);

		RPGLevels.lang = lang;
		RPGLevels.classes = classes;
		RPGLevels.datap = datap;
		RPGLevels.items = items;
		RPGLevels.skillscfg = skillscfg;

		// Если конфиг не существует достаём его из jar и сохраняем
		File config = new File(getDataFolder() + s + "config.yml");
		if (!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			saveConfig();
			getServer().getConsoleSender().sendMessage("§с(§eRPGLevels§с) §7Создан конфигурационный файл плагина.");

		}
		switch (getConfig().getString("StorageType")) {
		case "file":

			if (!classdata.exists())
				saveResource("Data" + s + "classes.yml", false);

			if (!pdata.exists())
				savePlayerData();

			RPGLevels.classes = classes;

			RPGLevels.datap = datap;

			break;

		case "sqlite":

			File dbfile = new File(getDataFolder() + s + "data.db");

			if (!dbfile.exists()) {

				try {
					if (dbfile.createNewFile()) {
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			try {
				SQLConnection db = new SQLConnection();
				if (db != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
				getLogger().severe("Произошла критическая ошибка при подключении к SQlite. Плагин был отключён.");
				getServer().getPluginManager().disablePlugin(this);

			}
			break;

		case "mysql":

			try {
				SQLConnection db = new SQLConnection();
				if (db != null) {
				}
			} catch (Exception e) {
				e.printStackTrace();
				getLogger().severe("Произошла критическая ошибка при подключении к MySQL. Плагин был отключён.");
				getServer().getPluginManager().disablePlugin(this);

			}
			break;

		default:
			getLogger().severe("Тип хранения \"" + getConfig().getString("StorageType")
					+ "\" не существует! Плагин был отключён. Пожалуйста, укажите в конфигурации плагина тип"
					+ "хранения: [mysql / sqlite / file] и перезагрузите сервер!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		PluginManager m = Bukkit.getPluginManager();

		m.registerEvents(new TabCompeteCMD(this), this);
		m.registerEvents(new PlayerLeave(rpg, skillscfg), this);
		m.registerEvents(new LevelUp(this, rpgclass, rpg), this);
		m.registerEvents(new PlayerJoin(this, rpg, rpgclass, skillscfg), this);
		m.registerEvents(new DeathExpSave(), this);
		m.registerEvents(new CmdClass(this, rpg, rpgclass), this);
		m.registerEvents(new DamageBoost(), this);
		m.registerEvents(new WitherEffect(), this);
		m.registerEvents(new PoisonEffect(), this);
		m.registerEvents(new FireEffect(), this);
		m.registerEvents(new BlindnessEffect(), this);
		m.registerEvents(new SlownessEffect(freezingmobs, freezingplayers), this);
		new SlownessEffect(freezingmobs, freezingplayers).runTaskTimer(this, 0, 1);
		m.registerEvents(new RegenerationDamage(), this);
		m.registerEvents(new DamageAbsorption(), this);
		m.registerEvents(new MagicShield(rpg), this);
		m.registerEvents(new CmdCreativeMenu(items), this);
		m.registerEvents(new SelectSkill(rpg, skillscfg), this);
		getCommand("level").setExecutor(new CmdLevel(this, rpg, rpgclass, lang, datap, saveTask, skillscfg));
		getCommand("class").setExecutor(new CmdClass(this, rpg, rpgclass));
		getCommand("classcreate").setExecutor(new CmdClassCreate(rpgclass));
		getCommand("classremove").setExecutor(new CmdClassRemove(rpgclass));
		getCommand("classinfo").setExecutor(new CmdClassInfo(rpgclass));
		getCommand("classedit").setExecutor(new CmdClassEdit(rpgclass));
		getCommand("attributemanage").setExecutor(new CmdAttributeManage());

		new ManaUpdateTask(this, rpg, rpgclass).runTaskTimerAsynchronously(this, 0, 20);

		if (getConfig().getBoolean("AutoSaveDataModule.enabled"))
			saveTask = new AutoSaveData(this, rpgclass, rpg, skillscfg).runTaskTimerAsynchronously(this, 0,
					getConfig().getInt("AutoSaveDataModule.period") * 1200);
		else
			saveTask = null;

		ArrayList<String> list = DataManager.getClasses();

		if (list.size() == 0) {
			CreateClass("Маг", "&aИнформация по умолчанию \\\\&7можно менять", Material.DIAMOND, 20, 0, 20, 0, 1);

			rpgclass.put("Маг",
					new RPGClasses("&aИнформация по умолчанию \\\\&7можно менять", "DIAMOND", 20, 0, 20, 0, 1));
		}
		for (String classname : list) {

			rpgclass = DataManager.loadClassData(classname, rpgclass);

		}

		getServer().getConsoleSender()
				.sendMessage("§с(§eRPGLevels§с) §7Плагин успешно запущен. Используемый тип хранения данных: §c"
						+ getConfig().getString("StorageType") + "§7Зарегистрировано §c"
						+ ((list.size() != 0) ? list.size() : 1) + "§7 классов, §c"
						+ skillscfg.getConfigurationSection("Skills").getKeys(false).size() + " §7скилов.");

	}

	@Override
	public void onDisable() {

		for (String classname : rpgclass.keySet()) {
			DataManager.saveClassData(classname, rpgclass);
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			DataManager.savePlayerData(p, rpg, skillscfg);
		}
		for (LivingEntity e : freezingmobs.keySet()) {
			e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(freezingmobs.get(e));
			if (e.hasPotionEffect(PotionEffectType.SLOW))
				e.removePotionEffect(PotionEffectType.SLOW);
		}
		for (Player p : freezingplayers.keySet()) {
			p.setWalkSpeed(freezingplayers.get(p));
			if (p.hasPotionEffect(PotionEffectType.SLOW))
				p.removePotionEffect(PotionEffectType.SLOW);
		}
		for (Player p : Bukkit.getOnlinePlayers()) {

			p.kickPlayer("§7Сервер перезагружается! Пожалуйста, переподключитесь.");

		}
		if (getConfig().getString("StorageType").equals("mysql")
				|| getConfig().getString("StorageType").equals("sqlite")) {
			try {
				SQLConnection.c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Метод сохранения данных игроков (файл datap.yml)
	public static void savePlayerData() {

		try {
			datap.save(pdata);
		} catch (IOException r) {
			r.printStackTrace();
		}

	}

	public static void saveClassData() {

		try {
			classes.save(cdata);

		} catch (IOException r) {
			r.printStackTrace();
		}

	}

	public static void saveItemsData() {

		try {
			items.save(itemsfile);

		} catch (IOException r) {
			r.printStackTrace();
		}

	}

	public static void saveSkillsCfg() {

		try {
			skillscfg.save(skillscfgfile);

		} catch (IOException r) {
			r.printStackTrace();
		}

	}

}
