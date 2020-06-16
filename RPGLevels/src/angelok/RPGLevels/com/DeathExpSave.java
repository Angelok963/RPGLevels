package angelok.RPGLevels.com;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathExpSave implements Listener{

	public DeathExpSave() {}
		
	@EventHandler
	public void LevelChange(PlayerDeathEvent e){
		
		e.setKeepLevel(true);
		e.setDroppedExp(0);
	}
}
