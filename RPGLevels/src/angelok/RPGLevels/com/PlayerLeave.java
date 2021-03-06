package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	private  HashMap<Player, RPGPlayer> rpg;
	private YamlConfiguration skillscfg;
	
	public PlayerLeave( HashMap<Player, RPGPlayer> rpg, YamlConfiguration skillscfg) {
		this.rpg = rpg;
		this.skillscfg = skillscfg;
	}

	@EventHandler
	public void leave(PlayerQuitEvent e) {

		DataManager.savePlayerData(e.getPlayer(), rpg, skillscfg);

	}
}
