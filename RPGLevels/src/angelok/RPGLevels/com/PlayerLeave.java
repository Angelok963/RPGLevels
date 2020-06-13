package angelok.RPGLevels.com;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	public PlayerLeave(RPGLevels plugin) {}

	@EventHandler
	public void leave(PlayerQuitEvent e){

		Player p = e.getPlayer();
		String player = p.getName();
		RPGPlayer rpg = RPGLevels.rpg.get(p);

		
		DataManager.setPlayerData(player, "exp", rpg.getExp());
		DataManager.setPlayerData(player, "heal", rpg.getHeal());
		DataManager.setPlayerData(player, "lastheal", rpg.getLastheal());
		DataManager.setPlayerData(player, "lvl", rpg.getLvl());
		DataManager.setPlayerData(player, "mana", rpg.getMana());
		DataManager.setPlayerData(player, "class", rpg.getPclass());
		DataManager.setPlayerData(player, "skills", rpg.getSkills());
		
	}
}
