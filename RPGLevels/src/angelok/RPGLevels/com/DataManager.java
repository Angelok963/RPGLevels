package angelok.RPGLevels.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class DataManager {

	private static String type = RPGLevels.plugin.getConfig().getString("StorageType");

	protected static void setPlayerData(String player, String DataName, String value) {

		switch (type) {
		case "file":

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

		default:
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

	protected static void setPlayerData(String player, String DataName, int value) {

		switch (type) {
		case "file":

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

		default:
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

	protected static void setPlayerData(String player, String DataName, double value) {

		switch (type) {
		case "file":

			RPGLevels.datap.set(player + "." + DataName, value);
			RPGLevels.savePlayerData();
			return;

		default:
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

	protected static int getPlayerDataInt(String player, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.datap.getInt(player + "." + DataName);

		default:
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

	protected static double getPlayerDataDouble(String player, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.datap.getDouble(player + "." + DataName);

		default:
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

	protected static String getPlayerDataString(String player, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.datap.getString(player + "." + DataName);

		default:
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

	protected static ArrayList<String> getPlayers() {

		switch (type) {
		case "file":

			ArrayList<String> l = new ArrayList<>();

			for (String s : RPGLevels.datap.getConfigurationSection("").getKeys(false))
				l.add(s);
			return l;

		default:

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

	protected static void setClassData(String classname, String DataName, String value) {

		switch (type) {
		case "file":

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		default:
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

	protected static void setClassData(String classname, String DataName, int value) {

		switch (type) {
		case "file":

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		default:
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

	protected static void setClassData(String classname, String DataName, double value) {

		switch (type) {
		case "file":

			RPGLevels.classes.set(classname + "." + DataName, value);
			RPGLevels.saveClassData();
			return;

		default:
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

	protected static String getClassDataString(String classname, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.classes.getString(classname + "." + DataName);

		default:
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

	protected static int getClassDataInt(String classname, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.classes.getInt(classname + "." + DataName);

		default:
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

	protected static double getClassDataDouble(String classname, String DataName) {

		switch (type) {
		case "file":

			return RPGLevels.classes.getDouble(classname + "." + DataName);

		default:
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

	protected static ArrayList<String> getClasses() {

		switch (type) {
		case "file":

			ArrayList<String> l = new ArrayList<>();

			for (String s : RPGLevels.classes.getConfigurationSection("").getKeys(false))
				l.add(s);
			return l;

		default:

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

	protected static void RemoveClass(String classname) {

		switch (type) {
		case "file":

			RPGLevels.classes.set(classname, null);
			RPGLevels.saveClassData();
			return;

		default:
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

	protected static void savePlayerData(Player p) {
		RPGPlayer rpg = RPGLevels.rpg.get(p);
		String player = p.getName();
		setPlayerData(player, "exp", rpg.getExp());
		setPlayerData(player, "heal", rpg.getHeal());
		setPlayerData(player, "lastheal", rpg.getLastheal());
		setPlayerData(player, "lvl", rpg.getLvl());
		setPlayerData(player, "mana", rpg.getMana());
		setPlayerData(player, "class", rpg.getPclass());
		setPlayerData(player, "skills", rpg.getSkills());

	}

	protected static void loadPlayerData(Player p) {

		String player = p.getName();

		RPGLevels.rpg.put(p, new RPGPlayer(DataManager.getPlayerDataInt(player, "lvl"),
				DataManager.getPlayerDataDouble(player, "mana"), DataManager.getPlayerDataInt(player, "exp"),
				DataManager.getPlayerDataInt(player, "skills"), DataManager.getPlayerDataString(player, "class"),
				DataManager.getPlayerDataDouble(player, "heal"), DataManager.getPlayerDataDouble(player, "lastheal")));

	}

	protected static void saveClassData(String classname) {

		RPGClasses rpg = RPGLevels.rpgclass.get(classname);

		DataManager.setClassData(classname, "changehealtolvl", rpg.getChangehealtolvl());
		DataManager.setClassData(classname, "changemanatolvl", rpg.getChangemanatolvl());
		DataManager.setClassData(classname, "defaultheal", rpg.getDefaultheal());
		DataManager.setClassData(classname, "defaultmana", rpg.getDefaultmana());
		DataManager.setClassData(classname, "info", rpg.getInfo());
		DataManager.setClassData(classname, "item", rpg.getItem());
		DataManager.setClassData(classname, "manapersecond", rpg.getManapersecond());

	}

	protected static void loadClassData(String classname) {

		RPGLevels.rpgclass.put(classname,
				new RPGClasses(DataManager.getClassDataString(classname, "info"),
						DataManager.getClassDataString(classname, "item"),
						DataManager.getClassDataDouble(classname, "defaultheal"),
						DataManager.getClassDataDouble(classname, "changehealtolvl"),
						DataManager.getClassDataDouble(classname, "defaultmana"),
						DataManager.getClassDataDouble(classname, "changemanatolvl"),
						DataManager.getClassDataDouble(classname, "manapersecond")));
	}

}
