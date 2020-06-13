package angelok.RPGLevels.com;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class LevelUp implements Listener {

	private static RPGLevels plugin;

	public LevelUp(RPGLevels plugin) {
		LevelUp.plugin = plugin;
	}

	@EventHandler
	public void expChange(PlayerExpChangeEvent e) {


		Player p = e.getPlayer();
		RPGPlayer rpg = RPGLevels.rpg.get(p);

		String clas = rpg.getPclass();
		
		
		if(clas.isEmpty()){
			e.setAmount(0);
			return;
		}
		
		
		int in = e.getAmount();

		int maxlvl = plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();

		
		int lvl1 = rpg.getLvl();
		
		int exp1 = rpg.getExp();
		
		if (lvl1 >= maxlvl) {
			e.setAmount(0);
			p.setLevel(maxlvl);
			p.setExp(0.999F);
			return;
		}

		
		rpg.setLvl(lvl1);
		rpg.setExp(exp1 + in);
        RPGLevels.rpg.put(p, rpg);

		e.setAmount(0);

		if (lvl1 >= maxlvl) {
			return;
		}

		

		boolean isUp = false;
		
		while(!isUp){
		 
			RPGPlayer rpg2 = RPGLevels.rpg.get(p);

			int lvl2 = rpg2.getLvl();
			
           int exp2 = rpg2.getExp();
			
			
			if(maxlvl <= lvl2){
				e.setAmount(0);
				p.setLevel(maxlvl);
				p.setExp(0.999F);
				
				return;
			}
			
			
			int cost = plugin.getConfig().getInt("Levels." + String.valueOf(lvl2 + 1) + ".cost");
		
			
			if (exp2 >= cost ) {

				rpg2.setLvl(lvl2+1);
				rpg2.setExp(exp2-cost);
				
				RPGLevels.rpg.put(p, rpg2);
				
				
			p.setLevel(lvl2 + 1);
			p.setExp(0F);
			
			ChangeLvL.lvlUp(p);

			if (lvl2 +1 != maxlvl)
				p.sendMessage("§c(§eRPGLevels§c) §7Новый уровень достигнут: §c" + String.valueOf(lvl2 + 1) + "§7!");
			
			else
				p.sendMessage(
						"§c(§eRPGLevels§c) §7Вы достигли максимальный уровень: §c" + String.valueOf(lvl2 + 1) + "§7!");

			for (String q : plugin.getConfig().getStringList("Levels." + String.valueOf(lvl2 + 1) + ".commands")) {

				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), q.replace("{Player}", p.getName()));

			}

		} else isUp = true;
			
			if(cost>exp2) isUp = true;
		
		}
		VisualLVL(p);

	}

	public static void VisualLVL(Player p) {

		RPGPlayer rpg = RPGLevels.rpg.get(p);
		
		int exp = rpg.getExp();

		int maxlvl = plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();

		int lvl = rpg.getLvl();
		
		if(lvl==0) return;
		
		int cost = plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");

		if (maxlvl <= lvl)
			p.setExp(0.999F);
		else
			p.setExp(exp / (float) cost);

	}

}