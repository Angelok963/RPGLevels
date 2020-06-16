package angelok.RPGLevels.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class LevelUp implements Listener {

	private static RPGLevels plugin;
	private HashMap<String, RPGClasses> clas;
	private static  HashMap<Player, RPGPlayer> rpgp;
	
	public LevelUp(RPGLevels plugin, HashMap<String, RPGClasses> clas, HashMap<Player, RPGPlayer> rpgp) {
		LevelUp.plugin = plugin;
		this.clas = clas;
		LevelUp.rpgp = rpgp;
	}

	@EventHandler
	public void expChange(PlayerExpChangeEvent e) {

		Player p = e.getPlayer();
		RPGPlayer rpg = rpgp.get(p);

		String clas = rpg.getPclass();

		if (clas.isEmpty()) {
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
		rpgp.put(p, rpg);

		e.setAmount(0);

		if (lvl1 >= maxlvl) {
			return;
		}

		boolean isUp = false;

		while (!isUp) {

			RPGPlayer rpg2 = rpgp.get(p);

			int lvl2 = rpg2.getLvl();

			int exp2 = rpg2.getExp();

			if (maxlvl <= lvl2) {
				e.setAmount(0);
				p.setLevel(maxlvl);
				p.setExp(0.999F);

				return;
			}

			int cost = plugin.getConfig().getInt("Levels." + String.valueOf(lvl2 + 1) + ".cost");

			if (exp2 >= cost) {

				rpg2.setLvl(lvl2 + 1);
				rpg2.setExp(exp2 - cost);

				rpgp.put(p, rpg2);

				p.setLevel(lvl2 + 1);
				p.setExp(0F);

				ChangeLvL.lvlUp(p, rpgp, this.clas);

				if (lvl2 + 1 != maxlvl)
					p.sendMessage(Lang.newlvlattain().replace("{lvl}", String.valueOf(lvl2 + 1)));

				else
					p.sendMessage(Lang.maxlvlattain().replace("{lvl}", String.valueOf(lvl2 + 1)));

				for (String q : plugin.getConfig().getStringList("Levels." + String.valueOf(lvl2 + 1) + ".commands")) {

					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), q.replace("{Player}", p.getName()));

				}

			} else
				isUp = true;

			if (cost > exp2)
				isUp = true;

		}
		VisualLVL(p);

	}

	public  void VisualLVL(Player p) {

		RPGPlayer rpg = rpgp.get(p);

		int exp = rpg.getExp();

		int maxlvl = plugin.getConfig().getConfigurationSection("Levels").getKeys(false).size();

		int lvl = rpg.getLvl();

		if (lvl == 0)
			return;

		int cost = plugin.getConfig().getInt("Levels." + String.valueOf(lvl + 1) + ".cost");

		if (maxlvl <= lvl)
			p.setExp(0.999F);
		else
			p.setExp(exp / (float) cost);

	}

}