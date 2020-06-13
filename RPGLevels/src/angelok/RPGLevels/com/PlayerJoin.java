package angelok.RPGLevels.com;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	private RPGLevels plugin;

	public PlayerJoin(RPGLevels plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String player = p.getName();

		if (!DataManager.getPlayers().contains(player)) {

			RPGLevels.rpg.put(p, new RPGPlayer(0, 0, 0, 0, "", 20.0, 20.0));

			DataManager.savePlayerData(p);

			String[] loc = plugin.getConfig().getString("firstspawn").split(":");

			p.teleport(new Location(Bukkit.getWorld(loc[0]), Integer.valueOf(loc[1]), Integer.valueOf(loc[2]),
					Integer.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

		} else {

			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(DataManager.getPlayerDataDouble(player, "heal"));

			DataManager.loadPlayerData(p);

			p.setLevel(DataManager.getPlayerDataInt(player, "lvl"));
			LevelUp.VisualLVL(p);
		}

	}

}
