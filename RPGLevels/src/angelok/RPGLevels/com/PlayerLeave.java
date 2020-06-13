package angelok.RPGLevels.com;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	public PlayerLeave(RPGLevels plugin) {
	}

	@EventHandler
	public void leave(PlayerQuitEvent e) {

		DataManager.savePlayerData(e.getPlayer());

	}
}
