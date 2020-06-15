package angelok.RPGLevels.com;

import org.bukkit.attribute.Attribute;
import org.bukkit.Material;
import org.bukkit.Location;
import java.util.List;
import org.bukkit.Bukkit;

public interface RPGLevelsAPI {
	default int getLvl(String nickname) {

		if (!this.getPlayersNicks().contains(nickname))
			return 0;

		return ((Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getLvl()
				: DataManager.getPlayerDataInt(nickname, "lvl"));

	}

	default void setLvl(String nickname, int lvl) {
		if (lvl >= RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size()) {
			lvl = RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
		}
		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setLvl(lvl);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else {
			DataManager.setPlayerData(nickname, "lvl", lvl);
		}
	}

	default int getSkills(String nickname) {
		if (this.getPlayersNicks().contains(nickname)) {
			return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getSkills()
					: DataManager.getPlayerDataInt(nickname, "skills");
		}
		return 0;
	}

	default void setSkills(String nickname, int skills) {
		if (skills < 0) {
			skills = 0;
		}
		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setSkills(skills);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else {
			DataManager.setPlayerData(nickname, "skills", skills);
		}
	}

	default int getExp(String nickname) {
		if (this.getPlayersNicks().contains(nickname)) {
			return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getExp()
					: DataManager.getPlayerDataInt(nickname, "exp");
		}
		return 0;
	}

	default void setExp(String nickname, int exp) {
		int lvl = 0;
		lvl = ((Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getLvl()
				: DataManager.getPlayerDataInt(nickname, "lvl"));
		if (lvl >= RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size()) {
			exp = 0;
		} else {
			int cost = RPGLevels.plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");
			if (exp >= cost) {
				exp = cost;
			}
		}
		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setExp(exp);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
			LevelUp.VisualLVL(Bukkit.getPlayer(nickname));
		} else {
			DataManager.setPlayerData(nickname, "exp", exp);
		}
	}

	default String getPlayerClass(String nickname) {
		String clas = "";
		if (this.getPlayersNicks().contains(nickname)) {
			clas = ((Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getPclass()
					: DataManager.getPlayerDataString(nickname, "class"));
			if (DataManager.getClasses().contains(clas)) {
				return clas;
			}
		}
		return "";
	}

	default void setPlayerClass(String nickname, String classname) {
		if (!DataManager.getClasses().contains(classname)) {
			classname = "";
		}
		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setPclass(classname);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else {
			DataManager.setPlayerData(nickname, "class", classname);
		}
	}

	default int getLvlCost(int lvl) {
		return RPGLevels.plugin.getConfig().getInt("Levels." + String.valueOf(lvl));
	}

	default void setLvlCost(int lvl, int cost) {
		RPGLevels.plugin.getConfig().set("Levels." + String.valueOf(lvl) + ".cost", cost);
		RPGLevels.plugin.saveConfig();
	}

	default int getMaxLvl() {
		return RPGLevels.plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();
	}

	default List<String> getLvlCommands(int lvl) {
		return (List<String>) RPGLevels.plugin.getConfig().getStringList("Levels." + String.valueOf(lvl) + ".commands");
	}

	default void setLvlCommands(int lvl, List<String> commands) {
		RPGLevels.plugin.getConfig().set("Levels." + String.valueOf(lvl) + ".commands", commands);
		RPGLevels.plugin.saveConfig();
	}

	default void setFirstSpawn(Location loc) {
		RPGLevels.plugin.getConfig().set("firstspawn", (String.valueOf(loc.getWorld().getName()) + ":" + loc.getBlockX()
				+ ":" + loc.getBlockY() + ":" + loc.getBlockZ() + ":" + loc.getYaw() + ":" + loc.getPitch()));
		RPGLevels.plugin.saveConfig();
	}

	default Location getFirstSpawnLocation() {
		String[] l = RPGLevels.plugin.getConfig().getString("firstspawn").split(":");
		return new Location(Bukkit.getWorld(l[0]), (double) Integer.valueOf(l[1]), (double) Integer.valueOf(l[2]),
				(double) Integer.valueOf(l[3]), (float) Float.valueOf(l[4]), (float) Float.valueOf(l[5]));
	}

	default void setRpgSpawn(Location loc) {
		RPGLevels.plugin.getConfig().set("rpgspawn", (String.valueOf(loc.getWorld().getName()) + ":" + loc.getBlockX()
				+ ":" + loc.getBlockY() + ":" + loc.getBlockZ() + ":" + loc.getYaw() + ":" + loc.getPitch()));
		RPGLevels.plugin.saveConfig();
	}

	default Location getRpgSpawnLocation() {
		String[] l = RPGLevels.plugin.getConfig().getString("rpgspawn").split(":");
		return new Location(Bukkit.getWorld(l[0]), (double) Integer.valueOf(l[1]), (double) Integer.valueOf(l[2]),
				(double) Integer.valueOf(l[3]), (float) Float.valueOf(l[4]), (float) Float.valueOf(l[5]));
	}

	default List<String> getPlayersNicks() {
		return DataManager.getPlayers();
	}

	default List<String> getClassesList() {
		return DataManager.getClasses();
	}

	default void RemoveClass(String classname) {
		DataManager.RemoveClass(classname);
		RPGLevels.rpgclass.remove(classname);
	}

	default void CreateClass(String classname, String info, Material icon, double defaultheal, double changehealtolvl,
			double defaultmana, double changemanatolvl, double manapersecond) {
		RPGLevels.rpgclass.put(classname, new RPGClasses(info, icon.toString(), defaultheal, changehealtolvl,
				defaultmana, changemanatolvl, manapersecond));
		DataManager.setClassData(classname, "info", info);
		DataManager.setClassData(classname, "item", icon.toString());
		DataManager.setClassData(classname, "defaultheal", defaultheal);
		DataManager.setClassData(classname, "changehealtolvl", changehealtolvl);
		DataManager.setClassData(classname, "defaultmana", defaultmana);
		DataManager.setClassData(classname, "changemanatolvl", changemanatolvl);
		DataManager.setClassData(classname, "manapersecond", manapersecond);
	}

	default String getClassInfo(String classname) {
		return RPGLevels.rpgclass.get(classname).getInfo();
	}

	default void setClassInfo(String classname, String info) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setInfo(info);
		RPGLevels.rpgclass.put(classname, s);
	}

	default Material getClassIcon(String classname) {
		return Material.getMaterial(RPGLevels.rpgclass.get(classname).getItem());
	}

	default void setClassIcon(String classname, Material icon) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setItem(icon.toString());
		RPGLevels.rpgclass.put(classname, s);
	}

	default void setClassDefaultHeal(String classname, double heal) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setDefaultheal(heal);
		RPGLevels.rpgclass.put(classname, s);
	}

	default double getClassDefaultHeal(String classname) {
		return RPGLevels.rpgclass.get(classname).getDefaultheal();
	}

	default void setChangeHealToLvl(String classname, double heal) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setChangehealtolvl(heal);
		RPGLevels.rpgclass.put(classname, s);
	}

	default double getChangeHealToLvl(String classname) {
		return RPGLevels.rpgclass.get(classname).getChangehealtolvl();
	}

	default double getPlayerHeal(String nickname) {
		return Double.valueOf(
				String.format("%.1f", (Bukkit.getPlayer(nickname) != null) ? Bukkit.getPlayer(nickname).getHealth()
						: DataManager.getPlayerDataDouble(nickname, "lastheal")).replace(",", "."));
	}

	default void setPlayerHeal(String nickname, double heal) {
		if (heal < 0.5) {
			heal = 0.5;
		}
		if (Bukkit.getPlayer(nickname) != null) {
			Bukkit.getPlayer(nickname).setHealth(heal);
		} else {
			DataManager.setPlayerData(nickname, "lastheal", heal);
		}
	}

	default double getPlayerMaxHeal(String nickname) {
		return (Bukkit.getPlayer(nickname) != null)
				? Bukkit.getPlayer(nickname).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()
				: DataManager.getPlayerDataDouble(nickname, "heal");
	}

	default void setPlayerMaxHeal(String nickname, double heal) {
		if (heal < 0.5) {
			heal = 0.5;
		}
		if (Bukkit.getPlayer(nickname) != null) {
			Bukkit.getPlayer(nickname).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(heal);
		} else {
			DataManager.setPlayerData(nickname, "heal", heal);
		}
	}

	default double getPlayerMana(String nickname) {
		return (Bukkit.getPlayer(nickname) != null) ? RPGLevels.rpg.get(Bukkit.getPlayer(nickname)).getMana()
				: DataManager.getPlayerDataDouble(nickname, "mana");
	}

	default void setPlayerMana(String nickname, double mana) {
		if (mana < 0.0) {
			mana = 0.0;
		}
		if (Bukkit.getPlayer(nickname) != null) {
			RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(nickname));
			rpg.setMana(mana);
			RPGLevels.rpg.put(Bukkit.getPlayer(nickname), rpg);
		} else {
			DataManager.setPlayerData(nickname, "mana", mana);
		}
	}

	default double getPlayerMaxMana(String nickname) {
		return Utilities.getPlayerMaxMana(nickname);
	}

	default void setChangeManaToLvl(String classname, double mana) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setChangemanatolvl(mana);
		RPGLevels.rpgclass.put(classname, s);
	}

	default double getChangeManaToLvl(String classname) {
		return RPGLevels.rpgclass.get(classname).getChangemanatolvl();
	}

	default void setDefaultMana(String classname, double mana) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setDefaultmana(mana);
		RPGLevels.rpgclass.put(classname, s);
	}

	default double getDefaultMana(String classname) {
		return RPGLevels.rpgclass.get(classname).getDefaultmana();
	}

	default void setManaPerSecond(String classname, double mana) {
		RPGClasses s = RPGLevels.rpgclass.get(classname);
		s.setManapersecond(mana);
		RPGLevels.rpgclass.put(classname, s);
	}

	default double getManaPerSecond(String classname) {
		return RPGLevels.rpgclass.get(classname).getManapersecond();
	}
}
