package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	private RPGLevels plugin;
	private  HashMap<Player, RPGPlayer> rpg;
	private  HashMap<String, RPGClasses> rpgclass;
	private YamlConfiguration skillscfg;

	public PlayerJoin(RPGLevels plugin, HashMap<Player, RPGPlayer> rpg, HashMap<String, RPGClasses> rpgclass, YamlConfiguration skillscfg) {
		this.plugin = plugin;
		this.rpg = rpg;
		this.rpgclass = rpgclass;
		this.skillscfg= skillscfg;
	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String player = p.getName();

		if (!DataManager.getPlayers().contains(player)) {

			HashMap<String, Integer> skill = new HashMap<>();
			
			rpg.put(p, new RPGPlayer(0, 0, 0, 0, "", 20.0, 20.0, skill));

			DataManager.savePlayerData(p, rpg, skillscfg);

			String[] loc = plugin.getConfig().getString("firstspawn").split(":");

			p.teleport(new Location(Bukkit.getWorld(loc[0]), Integer.valueOf(loc[1]), Integer.valueOf(loc[2]),
					Integer.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

		} else {

			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(DataManager.getPlayerDataDouble(player, "heal"));

			 DataManager.loadPlayerData(p, rpg, skillscfg);

			p.setLevel(DataManager.getPlayerDataInt(player, "lvl"));
			new LevelUp(plugin, rpgclass, rpg).VisualLVL(p);
		}

	}

}
