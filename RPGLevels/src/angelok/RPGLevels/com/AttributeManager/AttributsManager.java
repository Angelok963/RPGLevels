package angelok.RPGLevels.com.AttributeManager;

import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;

public interface AttributsManager {
	default ItemStack setAttributeValue(ItemStack item, String AttributeName, String Value) {
		return AttributeManager.setAttributeValue(item, AttributeName, Value);
	}

	default ItemStack setAttributeValue(ItemStack item, String AttributeName, int Value) {
		return AttributeManager.setAttributeValue(item, AttributeName, Value);
	}

	default ItemStack setAttributeValue(ItemStack item, String AttributeName, double Value) {
		return AttributeManager.setAttributeValue(item, AttributeName, Value);
	}

	default String getAttributeValueString(ItemStack item, String AttributeName) {
		return AttributeManager.getAttributeValueString(item, AttributeName);
	}

	default int getAttributeValueInt(ItemStack item, String AttributeName) {
		return AttributeManager.getAttributeValueInt(item, AttributeName);
	}

	default double getAttributeValueDouble(ItemStack item, String AttributeName) {
		return AttributeManager.getAttributeValueDouble(item, AttributeName);
	}

	default ArrayList<String> getAttributeList(ItemStack item) {
		return AttributeManager.getAttributeList(item);
	}

	default boolean hasAttribute(ItemStack item, String AtributeName) {
		return AttributeManager.hasAttribute(item, AtributeName);
	}
}
