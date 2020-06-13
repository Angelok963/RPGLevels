package angelok.RPGLevels.com;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;

public interface RPGLevelsAPI {

	/**
	 * @param nickname
	 *            - никнейм игрока
	 * @return Возвращает уровень игрока по его никнейму. Поддерживаются оффлайн
	 *         игроки. Если игрока не существует вернёт 0.
	 * @throws SQLException
	 */
	public default int getLvl(String nickname) {
		int r = 0;

		if (!getPlayersNicks().contains(nickname))
			return 0;

		r = (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getLvl()
				: DataManager.getPlayerDataInt(nickname, "lvl");

		return r;
	}

	/**
	 * Устанавливает уровень игроку по его никнейму. Если уровня не существует -
	 * устанавливается максимальный доступный уровень.
	 * 
	 * @param nickname
	 *            - никнейм игрока
	 * @param lvl
	 *            - уровень
	 * @throws SQLException
	 */

	public default void setLvl(String nickname, int lvl) {

		if (lvl >= RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size())
			lvl = RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();

		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setLvl(lvl);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else
			DataManager.setPlayerData(nickname, "lvl", lvl);

	}

	/**
	 * @param nickname
	 *            - никнейм игрока
	 * @return Возвращает количество скилов игрока по его никнейму.
	 *         Поддерживаются оффлайн игроки. Если игрока не существует вернёт
	 *         0.
	 * @throws SQLException
	 */

	public default int getSkills(String nickname) {

		if (getPlayersNicks().contains(nickname))

			return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getSkills()
					: DataManager.getPlayerDataInt(nickname, "skills");
		return 0;

	}

	/**
	 * Устанавливает количество скилов игроку по его никнейму. Если значение
	 * скилов меньше 0 - устанавливается 0 значение.
	 * 
	 * @param nickname
	 *            - никнейм игрока
	 * @param skills
	 *            - количество скилов
	 * @throws SQLException
	 */

	public default void setSkills(String nickname, int skills) {

		if (skills < 0)
			skills = 0;

		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setSkills(skills);

			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else
			DataManager.setPlayerData(nickname, "skills", skills);

	}

	/**
	 * @param nickname
	 *            - никнейм игрока
	 * @return Возвращает количество опыта игрока по его никнейму.
	 *         Поддерживаются оффлайн игроки. Если игрока не существует вернёт
	 *         0.
	 * @throws SQLException
	 */

	public default int getExp(String nickname) {

		if (getPlayersNicks().contains(nickname))

			return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getExp()
					: DataManager.getPlayerDataInt(nickname, "exp");

		return 0;

	}

	/**
	 * Устанавливает количество опыта игроку по его никнейму. Если значение у
	 * игрока максимальный уровень или параметр выше чем требование опыта на
	 * переход на слебующий уровень - устанавливается необходимое количество
	 * опыта для перехода на новый уровень (уровень не обновляется)
	 * 
	 * @param nickname
	 *            - никнейм игрока
	 * @param exp
	 *            - количество скилов
	 * @throws SQLException
	 */

	public default void setExp(String nickname, int exp) {

		int lvl = 0;
		lvl = (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getLvl()
				: DataManager.getPlayerDataInt(nickname, "lvl");

		if (lvl >= RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size())
			exp = 0;
		else {

			int cost = RPGLevels.plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");

			if (exp >= cost)
				exp = cost;

		}

		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setExp(exp);

			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);

			LevelUp.VisualLVL(Bukkit.getPlayer(nickname));

		} else
			DataManager.setPlayerData(nickname, "exp", exp);

	}

	/**
	 * @param nickname
	 *            - никнейм игрока
	 * @return Возвращает название класса, выбранного игроком. Если игрок ещё не
	 *         выбрал класс или игрока не существует - вернётся пустая строка
	 * @throws SQLException
	 */
	public default String getPlayerClass(String nickname) {

		String clas = "";
		
		if (getPlayersNicks().contains(nickname)){

			
			clas = (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getPclass()
					: DataManager.getPlayerDataString(nickname, "class");
		if(DataManager.getClasses().contains(clas))
			return clas;
		}
		return "";
	}

	/**
	 * Устанавливает класс игроку. Если класса не существует - класс игрока
	 * сбрасывается.
	 * 
	 * @param nickname
	 *            - никнейм игрока
	 * @param classname
	 *            - название класса
	 * @throws SQLException
	 */
	public default void setPlayerClass(String nickname, String classname) {

		if (!DataManager.getClasses().contains(classname))
			classname = "";

		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setPclass(classname);

			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else
			DataManager.setPlayerData(nickname, "class", classname);

	}

	/**
	 * @param lvl
	 *            - уровень
	 * @return Возвращает количество требуемого опыта для перехода на следующий
	 *         уровень. Если указанный уровень максимальный - вернёт 0
	 */

	public default int getLvlCost(int lvl) {

		return RPGLevels.plugin.getConfig().getInt("Levels." + String.valueOf(lvl));
	}

	/**
	 * Устанавливает необходимое количество опыта для перехода на указанный
	 * уровень
	 * 
	 * @param lvl
	 *            - уровень
	 * @param cost
	 *            - необходимое количество опыта для перехода на указанный
	 *            уровень
	 */
	public default void setLvlCost(int lvl, int cost) {

		RPGLevels.plugin.getConfig().set("Levels." + String.valueOf(lvl) + ".cost", cost);
		RPGLevels.plugin.saveConfig();
	}

	/**
	 * @return Возвращает номер максимального существующего уровня
	 */
	public default int getMaxLvl() {

		return RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
	}

	/**
	 * Получает List<String>, содержащий список команд, которые выполняются при
	 * достижении указанного уровня
	 * 
	 * @param lvl
	 *            - уровень
	 * @return List<String> команд
	 */
	public default List<String> getLvlCommands(int lvl) {

		return RPGLevels.plugin.getConfig().getStringList("Levels." + String.valueOf(lvl) + ".commands");
	}

	/**
	 * Устанавливает список команд, которые будут выполняться при достижении
	 * уровня
	 * 
	 * @param lvl
	 *            - уровень
	 * @param commands
	 *            - List<String> с командами. Поддерживается плейсхолдер
	 *            {Player}
	 */
	public default void setLvlCommands(int lvl, List<String> commands) {

		RPGLevels.plugin.getConfig().set("Levels." + String.valueOf(lvl) + ".commands", commands);
		RPGLevels.plugin.saveConfig();
	}

	/**
	 * Устанавливает локацию первого появления игроков
	 * 
	 * @param loc
	 *            - Объект Location
	 */
	public default void setFirstSpawn(Location loc) {

		RPGLevels.plugin.getConfig().set("firstspawn", loc.getWorld().getName() + ":" + loc.getBlockX() + ":"
				+ loc.getBlockY() + ":" + loc.getBlockZ() + ":" + loc.getYaw() + ":" + loc.getPitch());
		RPGLevels.plugin.saveConfig();
	}

	/**
	 * Получает объект Location, содержащий локацию точки первого появления
	 * игроков
	 * 
	 * @return Объект Location
	 */
	public default Location getFirstSpawnLocation() {

		String l[] = RPGLevels.plugin.getConfig().getString("firstspawn").split(":");

		return new Location(Bukkit.getWorld(l[0]), Integer.valueOf(l[1]), Integer.valueOf(l[2]), Integer.valueOf(l[3]),
				Float.valueOf(l[4]), Float.valueOf(l[5]));
	}

	/**
	 * Устанавливает локацию, куда будут телепортироваться игроки при выборе
	 * класса
	 * 
	 * @param loc
	 *            - Объект Location
	 */
	public default void setRpgSpawn(Location loc) {

		RPGLevels.plugin.getConfig().set("rpgspawn", loc.getWorld().getName() + ":" + loc.getBlockX() + ":"
				+ loc.getBlockY() + ":" + loc.getBlockZ() + ":" + loc.getYaw() + ":" + loc.getPitch());
		RPGLevels.plugin.saveConfig();
	}

	/**
	 * Получает локацию, куда будут телепортироваться игроки при выборе класса
	 * 
	 * @return Объект Location
	 */
	public default Location getRpgSpawnLocation() {

		String l[] = RPGLevels.plugin.getConfig().getString("rpgspawn").split(":");

		return new Location(Bukkit.getWorld(l[0]), Integer.valueOf(l[1]), Integer.valueOf(l[2]), Integer.valueOf(l[3]),
				Float.valueOf(l[4]), Float.valueOf(l[5]));
	}

	/**
	 * Получает List<Sring>, содержащий ник-неймы игроков, которые хотя бы 1 раз
	 * заходили в игру
	 * 
	 * @return List<Sring>
	 * @throws SQLException
	 */
	public default List<String> getPlayersNicks() {

		return DataManager.getPlayers();
	}

	/**
	 * Получает List<Sring>, содержащий названия зарегистрированных классов
	 * 
	 * @return List<Sring>
	 */
	public default List<String> getClassesList() {


		return DataManager.getClasses();

	}

	/**
	 * Удаляет класс по его названию
	 * 
	 * @param classname
	 *            - Объект Location
	 */
	public default void RemoveClass(String classname) {
		DataManager.RemoveClass(classname);
		RPGLevels.rpgclass.remove(classname);
		
	}

	/**
	 * Создаёт новый класс
	 * 
	 * @param classname
	 *            - Название класса
	 * @param info
	 *            - Список строк, которые будут выводиться в информации о классе
	 * @param icon
	 *            - Material предмета, который будет отображаться в качестве
	 *            иконки класса
	 * @param defaultheal
	 *            - Количество жизней в классе на первом уровне
	 * @param changehealtolvl
	 *            - Количество жизней, которые будут добавляться к имеющимся за
	 *            каждое повышение уровня
	 * @param defaultmana
	 *            - Максимальное количество маны на 1 уровне в классе
	 * @param changemanatolvl
	 *            - Количество маны, добавляемое к максимуму за повышение уровня
	 * @param manapersecond
	 *            - Скорость восполнения маны (количество в секунду) в классе
	 */
	public default void CreateClass(String classname, String info, Material icon, double defaultheal,
			double changehealtolvl, double defaultmana, double changemanatolvl, double manapersecond) {

		RPGLevels.rpgclass.put(classname, new RPGClasses(info, icon.toString(), defaultheal, changehealtolvl, defaultmana, changemanatolvl, manapersecond));
		
		DataManager.setClassData(classname, "info", info);
		DataManager.setClassData(classname, "item", icon.toString());
		DataManager.setClassData(classname, "defaultheal", defaultheal);
		DataManager.setClassData(classname, "changehealtolvl", changehealtolvl);
		DataManager.setClassData(classname, "defaultmana", defaultmana);
		DataManager.setClassData(classname, "changemanatolvl", changemanatolvl);
		DataManager.setClassData(classname, "manapersecond", manapersecond);
	}

	/**
	 * Получает List<Sring>, содержащий информацию о классе
	 * 
	 * @param classname
	 *            - название класса
	 * @return List<Sring>
	 */
	public default String getClassInfo(String classname) {

		return RPGLevels.rpgclass.get(classname).getInfo();
		
	}

	/**
	 * Устанавливает List<Sring>, содержащий информацию о классе
	 * 
	 * @param classname
	 *            - название класса
	 * @param info
	 *            - список строк
	 */
	public default void setClassInfo(String classname, String info) {
		
		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setInfo(info);
		 RPGLevels.rpgclass.put(classname, s);
	}

	/**
	 * Получает Material, который используется в качестве иконки класса
	 * 
	 * @param classname
	 *            - название класса
	 * @return Material
	 */
	public default Material getClassIcon(String classname) {

		return Material.getMaterial(RPGLevels.rpgclass.get(classname).getItem());
	}

	/**
	 * Устанавливает Material, который используется в качестве иконки класса
	 * 
	 * @param classname
	 *            - название класса
	 * @param icon
	 *            - Material предмета
	 */
	public default void setClassIcon(String classname, Material icon) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setItem(icon.toString());
		 RPGLevels.rpgclass.put(classname, s);
	}

	/**
	 * Устанавливает количество жизней в классе по умолчанию
	 * 
	 * @param classname
	 *            - название класса
	 * @param heal
	 *            - количество жизней
	 */
	public default void setClassDefaultHeal(String classname, double heal) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setDefaultheal(heal);
		 RPGLevels.rpgclass.put(classname, s);

	}

	/**
	 * Получает количество жизней в классе по умолчанию
	 * 
	 * @param classname
	 *            - название класса
	 * @return количество жизней (double)
	 */
	public default double getClassDefaultHeal(String classname) {

		return RPGLevels.rpgclass.get(classname).getDefaultheal();
		
	}

	/**
	 * Устанавливает количество жизней в классе, добавляемых за повышение уровня
	 * 
	 * @param classname
	 *            - название класса
	 * @param heal
	 *            - количество жизней
	 */
	public default void setСhangeHealToLvl(String classname, double heal) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setChangehealtolvl(heal);
		 RPGLevels.rpgclass.put(classname, s);

	}

	/**
	 * Получает количество жизней в классе, добавляемых за повышение уровня
	 * 
	 * @param classname
	 *            - название класса
	 * @return количество жизней (double)
	 */
	public default double getСhangeHealToLvl(String classname) {

		return RPGLevels.rpgclass.get(classname).getChangehealtolvl();
		
	}


	/**
	 * Получает текущее количество жизней игрока. Поддерживаются оффлайн-игроки
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @return количество жизней (double)
	 * @throws SQLException
	 */
	public default double getPlayerHeal(String nickname) {

		return Double.valueOf(
				String.format("%.1f", (Bukkit.getPlayer(nickname) != null) ? Bukkit.getPlayer(nickname).getHealth()
						: DataManager.getPlayerDataDouble(nickname, "lastheal")).replace(",", "."));

	}

	/**
	 * Устанавливает текущее количество жизней игроку. Поддерживаются оффлайн
	 * игроки. Если значение ниже 0.5 устанавливается 0.5
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @param heal
	 *            - количество жизней
	 * @throws SQLException
	 */
	public default void setPlayerHeal(String nickname, double heal) {
		if (heal < 0.5) {
			heal = 0.5;
		}

		if (Bukkit.getPlayer(nickname) != null)
			Bukkit.getPlayer(nickname).setHealth(heal);
		else
			DataManager.setPlayerData(nickname, "lastheal", heal);

	}

	/**
	 * Получает максимальное количество жизней игрока. Поддерживаются
	 * оффлайн-игроки
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @return количество жизней (double)
	 * @throws SQLException
	 */
	public default double getPlayerMaxHeal(String nickname) {

		return (Bukkit.getPlayer(nickname) != null)
				? Bukkit.getPlayer(nickname).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()
				: DataManager.getPlayerDataDouble(nickname, "heal");

	}

	/**
	 * Устанавливает максимальное количество жизней игроку. Поддерживаются
	 * оффлайн игроки. Если значение ниже 0 - устанавливается 0.5
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @param heal
	 *            - количество жизней
	 * @throws SQLException
	 */
	public default void setPlayerMaxHeal(String nickname, double heal) {

		if (heal < 0.5) {
			heal = 0.5;

		}

		if (Bukkit.getPlayer(nickname) != null)
			Bukkit.getPlayer(nickname).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(heal);
		else
			DataManager.setPlayerData(nickname, "heal", heal);

	}

	/**
	 * Получает текущее количество маны игрока. Поддерживаются оффлайн-игроки
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @return количество маны (double)
	 * @throws SQLException
	 */
	public default double getPlayerMana(String nickname) {

		return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getMana()
				: DataManager.getPlayerDataDouble(nickname, "mana");

	}

	/**
	 * Устанавливает текущее количество маны игроку. Поддерживаются оффлайн
	 * игроки. Если значение ниже 0 устанавливается 0
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @param mana
	 *            - количество маны
	 * @throws SQLException
	 */
	public default void setPlayerMana(String nickname, double mana) {
		if (mana < 0) {
			mana = 0;
		}

		if (Bukkit.getPlayer(nickname) != null) {

			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setMana(mana);

			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else
			DataManager.setPlayerData(nickname, "mana", mana);

	}

	/**
	 * Получает максимальное возможное количество маны игрока. Поддерживаются
	 * оффлайн-игроки. Если у игрока не выбран класс вернёт 0.0
	 * 
	 * @param nickname
	 *            - ник-нейм игрока
	 * @return количество маны (double)
	 * @throws SQLException
	 */
	public default double getPlayerMaxMana(String nickname) {

		String clas = "";
		clas = getPlayerClass(nickname);

		int lvl = 0;
		lvl = getLvl(nickname);

		return (!clas.isEmpty()) ? (RPGLevels.rpgclass.get(clas).getDefaultmana()
				+ (RPGLevels.rpgclass.get(clas).getChangemanatolvl() * (lvl - 1))) : 0.0;

	}

	/**
	 * Устанавливает количество маны в классе, добавляемой за повышение уровня
	 * 
	 * @param classname
	 *            - название класса
	 * @param mana
	 *            - количество маны
	 */
	public default void setСhangeManaToLvl(String classname, double mana) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setChangemanatolvl(mana);
		 RPGLevels.rpgclass.put(classname, s);
		

	}

	/**
	 * Получает количество маны в классе, добавляемой за повышение уровня
	 * 
	 * @param classname
	 *            - название класса
	 * @return количество маны (double)
	 */
	public default double getСhangeManaToLvl(String classname) {

		return RPGLevels.rpgclass.get(classname).getChangemanatolvl();
		
		
	}

	/**
	 * Устанавливает количество маны в классе по умолчанию
	 * 
	 * @param classname
	 *            - название класса
	 * @param mana
	 *            - количество маны
	 */
	public default void setDefaultMana(String classname, double mana) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setDefaultmana(mana);
		 RPGLevels.rpgclass.put(classname, s);
		
	}

	/**
	 * Получает количество маны в классе по умолчанию
	 * 
	 * @param classname
	 *            - название класса
	 * @return количество маны (double)
	 */
	public default double getDefaultMana(String classname) {

		return RPGLevels.rpgclass.get(classname).getDefaultmana();
		
	}

	/**
	 * Устанавливает количество маны в классе, добавляемой игроку каждую секунду
	 * 
	 * @param classname
	 *            - название класса
	 * @param mana
	 *            - количество маны
	 */
	public default void setManaPerSecond(String classname, double mana) {

		 RPGClasses s = RPGLevels.rpgclass.get(classname);
		 s.setManapersecond(mana);
		 RPGLevels.rpgclass.put(classname, s);
		
	}

	/**
	 * Получает количество маны в классе, добавляемой игроку каждую секунду
	 * 
	 * @param classname
	 *            - название класса
	 * @return количество маны (double)
	 */
	public default double getManaPerSecond(String classname) {

		return RPGLevels.rpgclass.get(classname).getManapersecond();
		
	}

}
