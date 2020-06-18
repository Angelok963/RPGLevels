package angelok.RPGLevels.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class DataManager {

	private static String type = RPGLevels.plugin.getConfig().getString("StorageType");

	public static void setPlayerData(String player, String DataName, String value) {

                  if(type.equals("file")){

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

                  }else {
			try {
				ResultSet r = SQLConnection.getResult("SELECT count(*) FROM players WHERE name = '" + player + "';");
				r.next();

				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO players (name, lvl, mana, exp, skills, class, heal, lastheal) VALUES ('"
									+ player + "', '0', '0.0', '0.0', '0', '', '20.0', '20.0');");

				SQLConnection.runQuery(
						"UPDATE players SET " + DataName + " = '" + value + "' WHERE name = '" + player + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static void setPlayerData(String player, String DataName, int value) {

		if(type.equals("file")){

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection.getResult("SELECT count(*) FROM players WHERE name = '" + player + "';");
				r.next();
				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO players (name, lvl, mana, exp, skills, class, heal, lastheal) VALUES ('"
									+ player + "', '0', '0.0', '0.0', '0', '', '20.0', '20.0');");

				SQLConnection.runQuery(
						"UPDATE players SET " + DataName + " = '" + value + "' WHERE name = '" + player + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static void setPlayerData(String player, String DataName, double value) {

		if(type.equals("file")){

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection.getResult("SELECT count(*) FROM players WHERE name = '" + player + "';");
				r.next();
				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO players (name, lvl, mana, exp, skills, class, heal, lastheal) VALUES ('"
									+ player + "', '0', '0.0', '0.0', '0', '', '20.0', '20.0');");

				SQLConnection.runQuery(
						"UPDATE players SET " + DataName + " = '" + value + "' WHERE name = '" + player + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static int getPlayerDataInt(String player, String DataName) {

		if(type.equals("file")){

			return RPGLevels.datap.getInt(player + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM players WHERE name='" + player + "';");

				res.next();
				return res.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static double getPlayerDataDouble(String player, String DataName) {

		if(type.equals("file")){
			return RPGLevels.datap.getDouble(player + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM players WHERE name='" + player + "';");

				res.next();
				return res.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static String getPlayerDataString(String player, String DataName) {

		if(type.equals("file")){

			return RPGLevels.datap.getString(player + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM players WHERE name='" + player + "';");

				res.next();
				return res.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static ArrayList<String> getPlayers() {

		if(type.equals("file")){

			ArrayList<String> l = new ArrayList<>();

			for (String s : RPGLevels.datap.getConfigurationSection("").getKeys(false))
				l.add(s);
			return l;

		}else {

			ArrayList<String> name = new ArrayList<>();
			try {
				ResultSet res = SQLConnection.getResult("select name FROM players;");

				while (res.next())
					name.add(res.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return name;
		}

	}

	public static void setClassData(String classname, String DataName, String value) {

		if(type.equals("file")){

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection
						.getResult("SELECT count(*) FROM classes WHERE classname = '" + classname + "';");
				r.next();

				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO classes (classname, info, item, defaultheal, changehealtolvl, defaultmana, changemanatolvl, manapersecond) VALUES ('"
									+ classname
									+ "', '&aИнформация по умолчанию \\можно менять', 'AIR', '20.0', '0.0', '20.0', '0.0', '1.0');");

				SQLConnection.runQuery(
						"UPDATE classes SET " + DataName + " = '" + value + "' WHERE classname = '" + classname + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static void setClassData(String classname, String DataName, int value) {

		if(type.equals("file")){

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection
						.getResult("SELECT count(*) FROM classes WHERE classname = '" + classname + "';");
				r.next();

				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO classes (classname, info, item, defaultheal, changehealtolvl, defaultmana, changemanatolvl, manapersecond) VALUES ('"
									+ classname
									+ "', '&aИнформация по умолчанию \\можно менять', 'AIR', '20.0', '0.0', '20.0', '0.0', '1.0');");

				SQLConnection.runQuery(
						"UPDATE classes SET " + DataName + " = '" + value + "' WHERE classname = '" + classname + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static void setClassData(String classname, String DataName, double value) {

		if(type.equals("file")){

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection
						.getResult("SELECT count(*) FROM classes WHERE classname = '" + classname + "';");
				r.next();

				if (r.getInt(1) == 0)
					SQLConnection.runQuery(
							"INSERT INTO classes (classname, info, item, defaultheal, changehealtolvl, defaultmana, changemanatolvl, manapersecond) VALUES ('"
									+ classname
									+ "', '&aИнформация по умолчанию \\можно менять', 'AIR', '20.0', '0.0', '20.0', '0.0', '1.0');");

				SQLConnection.runQuery(
						"UPDATE classes SET " + DataName + " = '" + value + "' WHERE classname = '" + classname + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static String getClassDataString(String classname, String DataName) {

		if(type.equals("file")){

			return RPGLevels.classes.getString(classname + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM classes WHERE classname='" + classname + "';");

				res.next();
				return res.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static int getClassDataInt(String classname, String DataName) {

		if(type.equals("file")){

			return RPGLevels.classes.getInt(classname + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM classes WHERE classname='" + classname + "';");

				res.next();
				return res.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static double getClassDataDouble(String classname, String DataName) {

		if(type.equals("file")){

			return RPGLevels.classes.getDouble(classname + "." + DataName);

		}else {
			try {
				ResultSet res = SQLConnection
						.getResult("SELECT " + DataName + " FROM classes WHERE classname='" + classname + "';");

				res.next();
				return res.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static ArrayList<String> getClasses() {

		if(type.equals("file")){

			ArrayList<String> l = new ArrayList<>();

			for (String s : RPGLevels.classes.getConfigurationSection("").getKeys(false))
				l.add(s);
			return l;

		}else {

			ArrayList<String> name = new ArrayList<>();
			try {
				ResultSet res = SQLConnection.getResult("select classname FROM classes;");

				while (res.next())
					name.add(res.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return name;
		}

	}

	public static void RemoveClass(String classname) {

		if(type.equals("file")){

			RPGLevels.classes.set(classname, null);
			RPGLevels.saveClassData();
			return;

		}else {
			try {
				ResultSet r = SQLConnection
						.getResult("SELECT count(*) FROM classes WHERE classname = '" + classname + "';");
				r.next();

				if (r.getInt(1) != 0)

					SQLConnection.runQuery("DELETE FROM classes WHERE classname = '" + classname + "';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

	}

	public static void savePlayerData(Player p, HashMap<Player, RPGPlayer> rpgp) {
		RPGPlayer rpg = rpgp.get(p);
		String player = p.getName();
		
		double maxheal = rpg.getHeal();
		
		double lastheal = rpg.getLastheal();
		if(lastheal > maxheal)
		lastheal = maxheal;
		
		setPlayerData(player, "exp", rpg.getExp());
		setPlayerData(player, "heal", maxheal);
		setPlayerData(player, "lastheal", lastheal);
		setPlayerData(player, "lvl", rpg.getLvl());
		setPlayerData(player, "mana", rpg.getMana());
		setPlayerData(player, "class", rpg.getPclass());
		setPlayerData(player, "skills", rpg.getSkills());

	}

	public static HashMap<Player, RPGPlayer> loadPlayerData(Player p, HashMap<Player, RPGPlayer> rpg) {

		String player = p.getName();

		double maxheal = DataManager.getPlayerDataDouble(player, "heal");
		double lastheal = DataManager.getPlayerDataDouble(player, "lastheal");
		if(lastheal > maxheal)
			lastheal = maxheal;
		
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxheal);

		p.setHealth(lastheal);
		p.setLevel(DataManager.getPlayerDataInt(player, "lvl"));


		rpg.put(p, new RPGPlayer(DataManager.getPlayerDataInt(player, "lvl"),
				DataManager.getPlayerDataDouble(player, "mana"), DataManager.getPlayerDataInt(player, "exp"),
				DataManager.getPlayerDataInt(player, "skills"), DataManager.getPlayerDataString(player, "class"),
				DataManager.getPlayerDataDouble(player, "heal"), DataManager.getPlayerDataDouble(player, "lastheal")));
return rpg;
	}

	public static void saveClassData(String classname, HashMap<String, RPGClasses> rpgclass) {

		RPGClasses rpg = rpgclass.get(classname);

		DataManager.setClassData(classname, "changehealtolvl", rpg.getChangehealtolvl());
		DataManager.setClassData(classname, "changemanatolvl", rpg.getChangemanatolvl());
		DataManager.setClassData(classname, "defaultheal", rpg.getDefaultheal());
		DataManager.setClassData(classname, "defaultmana", rpg.getDefaultmana());
		DataManager.setClassData(classname, "info", rpg.getInfo());
		DataManager.setClassData(classname, "item", rpg.getItem());
		DataManager.setClassData(classname, "manapersecond", rpg.getManapersecond());

	}

	public static HashMap<String, RPGClasses> loadClassData(String classname, HashMap<String, RPGClasses> rpgclass) {

		rpgclass.put(classname,
				new RPGClasses(DataManager.getClassDataString(classname, "info"),
						DataManager.getClassDataString(classname, "item"),
						DataManager.getClassDataDouble(classname, "defaultheal"),
						DataManager.getClassDataDouble(classname, "changehealtolvl"),
						DataManager.getClassDataDouble(classname, "defaultmana"),
						DataManager.getClassDataDouble(classname, "changemanatolvl"),
						DataManager.getClassDataDouble(classname, "manapersecond")));

		return rpgclass;
	}

}
