package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import angelok.RPGLevels.com.baseAttributes.DefaultAttributes;

public class Utilities
{
	
	public static void loadDataPlayerYML(YamlConfiguration datap) {
		RPGLevels.datap = datap;
	}
	
	public static void loadDataClassesYML(YamlConfiguration classes) {
		RPGLevels.classes = classes;
	}
	
    public static double getPlayerMaxMana(String player) {
        String clas = "";
        boolean isOnline = false;
        if (Bukkit.getPlayer(player) != null) {
            isOnline = true;
        }
        int lvl = 0;
        if (isOnline) {
            RPGPlayer rpgp = RPGLevels.rpg.get(Bukkit.getPlayer(player));
            clas = rpgp.getPclass();
            lvl = rpgp.getLvl();
        }
        else {
            clas = DataManager.getPlayerDataString(player, "class");
            lvl = DataManager.getPlayerDataInt(player, "lvl");
        }
        double max = clas.isEmpty() ? 0.0 : (RPGLevels.rpgclass.get(clas).getDefaultmana() + RPGLevels.rpgclass.get(clas).getChangemanatolvl() * (lvl - 1));
        if (isOnline) {
            return max + max / 100.0 * DefaultAttributes.getManaStorageBoost(Bukkit.getPlayer(player));
        }
        return max;
    }
    
    
    public static double getManaPerSecond(Player player, HashMap<Player, RPGPlayer> rpg,  HashMap<String, RPGClasses> rpgclass) {
    	double s = rpgclass.get(rpg.get(player).getPclass()).getManapersecond();
		return s + s / 100.0 * DefaultAttributes.getManaRegenBoost(player);
	}
}
