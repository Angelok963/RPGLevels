package angelok.RPGLevels.com;

import angelok.RPGLevels.com.baseAttributes.DefaultAttributes;
import org.bukkit.Bukkit;

public class Utilities
{
    public static double getPlayerMaxMana(String player) {
        String clas = "";
        boolean isOnline = false;
        if (Bukkit.getPlayer(player) != null) {
            isOnline = true;
        }
        int lvl = 0;
        if (isOnline) {
            RPGPlayer rpg = RPGLevels.rpg.get(Bukkit.getPlayer(player));
            clas = rpg.getPclass();
            lvl = rpg.getLvl();
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
}
