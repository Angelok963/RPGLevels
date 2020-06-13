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

			p.setLevel(0);
			p.setExp(0.0F);
			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
			p.setHealth(20.0);

			DataManager.setPlayerData(p.getName(), "lvl", 0);
			DataManager.setPlayerData(p.getName(), "mana", 0.0);
			DataManager.setPlayerData(p.getName(), "exp", 0);
			DataManager.setPlayerData(p.getName(), "skills", 0);
			DataManager.setPlayerData(p.getName(), "class", "");
			DataManager.setPlayerData(p.getName(), "heal", 20.0);
			DataManager.setPlayerData(p.getName(), "lastheal", 20.0);

			String[] loc = plugin.getConfig().getString("firstspawn").split(":");
			
			
			p.teleport(new Location(Bukkit.getWorld(loc[0]), Integer.valueOf(loc[1]), Integer.valueOf(loc[2]), Integer.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));
			
		} else {
			
			p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(DataManager.getPlayerDataDouble(player, "heal"));
			
			RPGLevels.rpg.put(p, new RPGPlayer( 
					DataManager.getPlayerDataInt(player, "lvl"), 
					DataManager.getPlayerDataDouble(player, "mana"), 
					DataManager.getPlayerDataInt(player, "exp"), 
					DataManager.getPlayerDataInt(player, "skills"), 
					DataManager.getPlayerDataString(player, "class"), 
					DataManager.getPlayerDataDouble(player, "heal"), 
					DataManager.getPlayerDataDouble(player, "lastheal")));

		
			
			p.setLevel(DataManager.getPlayerDataInt(player, "lvl"));
			LevelUp.VisualLVL(p);
		}

	}

}
