package angelok.RPGLevels.com;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.file.FileConfiguration;

public class SQLConnection {

	private static String url;
	private static String user;
	private static String pass;
	protected static Connection c;
	
	public SQLConnection() throws Exception {

		FileConfiguration cfg = RPGLevels.plugin.getConfig();

		if (!cfg.getString("StorageType").equals("mysql") && !cfg.getString("StorageType").equals("sqlite"))
			return;

		String dataBaseName = cfg.getString("MySQLProperties.DBname");

		if (cfg.getString("StorageType").equals("mysql")) {

			String ip = cfg.getString("MySQLProperties.ip");
			int port = cfg.getInt("MySQLProperties.port");
			user = cfg.getString("MySQLProperties.user");
			pass = cfg.getString("MySQLProperties.password");
			url = "jdbc:mysql://" + ip + ":" + port + "/" + dataBaseName;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} else {

			url = "jdbc:sqlite:" + RPGLevels.plugin.getDataFolder()  + File.separator
					+  "data.db";
			Class.forName("org.sqlite.JDBC").newInstance();
		}

	    c = getConnection();
		Statement st = c.createStatement();

		st.executeUpdate(
				"CREATE TABLE IF NOT EXISTS players (`name` TEXT, `lvl` INT, `mana` DOUBLE, `exp` INT, `skills` INT, `class` TEXT, `heal` DOUBLE, `lastheal` DOUBLE);");

		st.executeUpdate(
				"CREATE TABLE IF NOT EXISTS classes (`classname` TEXT, `info` TEXT, `item` TEXT, `defaultheal` DOUBLE, `changehealtolvl` DOUBLE, `defaultmana` DOUBLE, `changemanatolvl` DOUBLE, `manapersecond` DOUBLE);");

		st.close();
	}

	public static Connection getConnection() throws SQLException {
		
		
		if (user != null)
			return DriverManager.getConnection(url, user, pass);
		else
			return DriverManager.getConnection(url);
	}

	public static void runQuery(String sqlQuery) throws SQLException {

		 Statement st = c.createStatement();

		st.executeUpdate(sqlQuery);
		
	}

	public static ResultSet getResult(String sqlQuery) throws SQLException {


		 Statement st = c.createStatement();

		ResultSet s = st.executeQuery(sqlQuery);

		return s;
	}

}
