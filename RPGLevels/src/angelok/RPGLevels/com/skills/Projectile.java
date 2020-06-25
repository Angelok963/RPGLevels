package angelok.RPGLevels.com.skills;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;

import angelok.RPGLevels.com.Lang;
import angelok.RPGLevels.com.RPGLevels;
import angelok.RPGLevels.com.RPGPlayer;
import angelok.RPGLevels.com.AttributeManager.AttributeManager;

public class Projectile implements Listener {

	private HashMap<Player, RPGPlayer> rpg;
	private static YamlConfiguration skillscfg;
	private static HashMap<String, Long> cooldown = new HashMap<>();
	private RPGLevels plugin;

	public Projectile(HashMap<Player, RPGPlayer> rpg, YamlConfiguration skillscfg, RPGLevels plugin) {
		this.rpg = rpg;
		Projectile.skillscfg = skillscfg;
		this.plugin = plugin;
	}

	public static void updateSkillsCfg(YamlConfiguration skillscfg) {
		Projectile.skillscfg = skillscfg;
	}

	@EventHandler
	public void onUse(PlayerInteractEvent e) {

		ItemStack i = e.getItem();

		// Активируем скил на пкм
		if (e.getAction() != Action.RIGHT_CLICK_AIR)
			return;

		String name = "";
		// На предмет не привязан навык
		if (!AttributeManager.hasAttribute(i, "Selected_skill"))
			return;
		else
			name = AttributeManager.getAttributeValueString(i, "Selected_skill");

		Player p = e.getPlayer();
		// Игрок сидит - открываем меню выбора навыков, не активируем навык
		if (p.isSneaking())
			return;

		RPGPlayer rpgplayer = rpg.get(p);

		int lvl = rpgplayer.getLvlSkill(name);
		// У игрока не куплен навык
		if (lvl == 0)
			return;
		// У класса игрока нет доступа к навыку
		if (!skillscfg.getStringList("Skills." + name + ".ClassUsage").contains(rpgplayer.getPclass()))
			return;

		long data = new Date().getTime() / 1000;

		// Перезарядка не закончилась
		long cd = ((cooldown.get(p.getName() + "§" + name) == null) ? 0 : cooldown.get(p.getName() + "§" + name));
		
		if (data < cd) {
			
		p.sendMessage(Lang.cooldown().replace("{time}", String.valueOf(cd-data)));
			return;
		}
		double consume = skillscfg.getDoubleList("Skills." + name + ".ManaCost").get(lvl - 1);

		double mana = rpgplayer.getMana();
		// Маны не хватает
		if (mana < consume) {
			p.sendMessage(Lang.notmana());
			return;
		}
		// Тратим ману и устанавливаем перезарядку
		rpgplayer.setMana(mana - consume);
		cooldown.put(p.getName() + "§" + name, data + skillscfg.getLong("Skills." + name + ".Cooldown"));
		rpg.put(p, rpgplayer);

		String type = skillscfg.getString("Skills." + name + ".Type");

		switch (type) {
		case "PROJECTILE":

			String entityType = skillscfg.getString("Skills." + name + ".Projectile");

			Entity entity = null;

			switch (entityType) {
			case "SNOWBALL":

				entity = p.launchProjectile(Snowball.class);

				break;

			case "FIREBALL":

				entity = p.launchProjectile(Fireball.class);

				break;

			case "EGG":
				entity = p.launchProjectile(Egg.class);

				break;
			default:
				return;
			}

			entity.setVelocity(p.getLocation().getDirection());
			entity.setGravity(false);
			entity.setMetadata("skillName", new FixedMetadataValue(plugin, name));
			entity.setMetadata("skillDamage", new FixedMetadataValue(plugin,
					skillscfg.getDoubleList("Skills." + name + ".DamageUpgrade").get(lvl - 1)));
			Entity s = entity;
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

				@Override
				public void run() {
					if (s != null)
						s.remove();

				}
			}, skillscfg.getInt("Skills." + name + ".LiveTime"));
			return;

		case "LASER":

			int live = skillscfg.getInt("Skills." + name + ".LiveTime");

			Location b = p.getLocation().add(0, 1.5, 0);

			BlockIterator iter = new BlockIterator(p, skillscfg.getInt("Skills." + name + ".LiveTime"));

			while (iter.hasNext()) {

				Block k = iter.next();

				if (k.getType() != Material.AIR) {

					break;
				} else if (k.getWorld().getNearbyEntities(k.getLocation(), 1, 1, 1).size() > 1) {
					break;

				} else
					b = k.getLocation();

			}

			Location loc = p.getLocation().add(0, 1.5, 0);
			Location loc2 = loc;

			for (int x = 0; x <= live; x++) {
				loc2 = loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
				b.getWorld().spawnParticle(Particle.REDSTONE, loc2, 0);

			}

			for (Entity j : b.getWorld().getNearbyEntities(b, 2, 2, 2)) {

				if (!(j instanceof LivingEntity))
					continue;

				if (j instanceof Player)
					if (((Player) j).equals(p))
						continue;

				LivingEntity mob = (LivingEntity) j;

				double heal = mob.getHealth();

				double damage = skillscfg.getDoubleList("Skills." + name + ".DamageUpgrade").get(lvl - 1);

				if (heal - damage > 0)
					mob.setHealth(heal - damage);
				else
					mob.setHealth(0);
			}

			return;
		default:
			return;
		}
	}

	// Убираем взрыв от фаербола скила
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {

		if (e.getEntityType() == EntityType.FIREBALL)
			if (e.getEntity().hasMetadata("skillName"))
				e.setCancelled(true);
	}

	// Убираем дефолтный урон от снарядов если они вызываны скилами
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		Entity n = e.getDamager();

		if (n instanceof Fireball || n instanceof Snowball || n instanceof Egg) {

			if (!n.hasMetadata("skillName"))
				return;

			e.setDamage(n.getMetadata("skillDamage").get(0).asDouble());

		}

	}

	// Убираем курей от снаряда EGG
	@EventHandler
	public void noSpawn(PlayerEggThrowEvent e) {

		if (!e.getEgg().hasMetadata("skillName"))
			return;
		e.setHatching(false);
	}
}
