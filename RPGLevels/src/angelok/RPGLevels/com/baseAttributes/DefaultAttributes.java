package angelok.RPGLevels.com.baseAttributes;

import angelok.RPGLevels.com.AttributeManager.AttributeManager;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class DefaultAttributes {
	public static ArrayList<ItemStack> getSlots(Player p) {
		PlayerInventory inv = p.getInventory();
		ArrayList<ItemStack> item = new ArrayList<ItemStack>();
		item.add(inv.getHelmet());
		item.add(inv.getChestplate());
		item.add(inv.getLeggings());
		item.add(inv.getBoots());
		item.add(inv.getItemInMainHand());
		return item;
	}

	public static double getAttributesValueOfDouble(Player player, String attributeType) {
		double boost = 0.0;
		for (ItemStack i : getSlots(player)) {
			if (AttributeManager.hasAttribute(i, attributeType)) {
				boost = boost + AttributeManager.getAttributeValueDouble(i, attributeType);
			}
		}
		return boost;
	}

	public static int getAttributesValueOfInt(Player player, String attributeType) {
		int boost = 0;
		for (ItemStack i : getSlots(player)) {
			if (AttributeManager.hasAttribute(i, attributeType)) {
				boost = boost + AttributeManager.getAttributeValueInt(i, attributeType);
			}
		}
		return boost;
	}

}
