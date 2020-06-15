package angelok.RPGLevels.com;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import angelok.RPGLevels.com.AttributeManager.AttributsManager;

public class RPGLevels extends JavaPlugin implements RPGLevelsAPI, AttributsManager {

	protected static Plugin plugin;

	protected static HashMap<Player, RPGPlayer> rpg = new HashMap<>();

	protected static HashMap<String, RPGClasses> rpgclass = new HashMap<>();

	private File playerdata = new File(getDataFolder() + File.separator + "Data" + File.separator + "players.yml");
	private File classdata = new File(getDataFolder() + File.separator + "Data" + File.separator + "classes.yml");

	protected static YamlConfiguration datap;
	private static File pdata;

	protected static YamlConfiguration classes;
	private static File cdata;

	protected static BukkitTask saveTask;

	@Override
	public void onEnable() {

		pdata = playerdata;
		cdata = classdata;
		RPGLevels.plugin = this;

		switch (getConfig().getString("StorageType")) {
		case "file":

			if (!classdata.exists()) {


				classes.set("Лучник.info", "Класс по умолчанию\\можно менять");
				classes.set("Лучник.item", "BOW");
				classes.set("Лучник.defaultheal", 20.5);
				classes.set("Лучник.changehealtolvl", 5.5);
				classes.set("Лучник.defaultmana", 10.5);
				classes.set("Лучник.changemanatolvl", 10.5);
				classes.set("Лучник.manapersecond", 2.5);

				saveClassData();

			}

			YamlConfiguration datap = YamlConfiguration.loadConfiguration(playerdata);
			YamlConfiguration classes = YamlConfiguration.loadConfiguration(classdata);

			RPGLevels.classes = classes;

			RPGLevels.datap = datap;

			break;

		case "sqlite":

			File dbfile = new File(getDataFolder() + File.separator + "data.db");

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

		Bukkit.getPluginManager().registerEvents(new TabCompeteCMD(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(this), this);
		Bukkit.getPluginManager().registerEvents(new LevelUp(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getPluginManager().registerEvents(new DeathExpSave(this), this);
		Bukkit.getPluginManager().registerEvents(new CmdClass(this), this);
		getCommand("level").setExecutor(new CmdLevel(this));
		getCommand("class").setExecutor(new CmdClass(this));
		getCommand("classcreate").setExecutor(new CmdClassCreate(this));
		getCommand("classremove").setExecutor(new CmdClassRemove(this));
		getCommand("classinfo").setExecutor(new CmdClassInfo(this));
		getCommand("classedit").setExecutor(new CmdClassEdit(this));


		// Если конфиг не существует достаём его из jar и сохраняем
		File config = new File(getDataFolder() + File.separator + "config.yml");
		if (!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			saveConfig();
			getServer().getConsoleSender().sendMessage("§с(§eRPGLevels§с) §7Создан конфигурационный файл плагина.");

		}

		new ManaUpdateTask(this).runTaskTimerAsynchronously(this, 0, 20);

		if (getConfig().getBoolean("AutoSaveDataModule.enabled"))
			saveTask = new AutoSaveData(this).runTaskTimerAsynchronously(this, 0,
					getConfig().getInt("AutoSaveDataModule.period") * 1200);
		else
			saveTask = null;

		ArrayList<String> list = DataManager.getClasses();
		
		if(list.size() == 0){
			CreateClass("Маг", "&aИнформация по умолчанию \n&7можно менять", Material.DIAMOND, 20, 0, 20, 0, 1);
		
		rpgclass.put("Маг", new RPGClasses("&aИнформация по умолчанию \n&7можно менять", "DIAMOND", 20, 0, 20, 0, 1));
		} 
		for (String classname : list) {

			DataManager.loadClassData(classname);

		}

		getServer().getConsoleSender()
				.sendMessage("§с(§eRPGLevels§с) §7Плагин успешно запущен. Используемый тип хранения данных: §c"
						+ getConfig().getString("StorageType") + "§7Зарегистрировано §c" + ((list.size() != 0) ? list.size() : 1)  + "§7 классов.");
	}

	@Override
	public void onDisable() {
		
		
		for(String classname : rpgclass.keySet()){
		DataManager.saveClassData(classname);
		}
		
		for(Player p : Bukkit.getOnlinePlayers()){
			DataManager.savePlayerData(p);
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {

			p.kickPlayer("§7Сервер перезагружается! Пожалуйста, переподключитесь.");

		}
		
		try {
			SQLConnection.c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Метод сохранения данных игроков (файл datap.yml)
	protected static void savePlayerData() {

		try {
			datap.save(pdata);
		} catch (IOException r) {
			r.printStackTrace();
		}

	}

	protected static void saveClassData() {

		try {
			classes.save(cdata);

		} catch (IOException r) {
			r.printStackTrace();
		}

	}

}
