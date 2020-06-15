package angelok.RPGLevels.com.AttributeManager;

import java.util.Set;
import java.util.ArrayList;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class AttributeManager {
	public static ItemStack setAttributeValue(ItemStack item, String AttributeName, String Value) {
		if (item != null) {
			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
			NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
			NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
			
			if(Value != null)
			CustomTags.setString(AttributeName, Value);
			else CustomTags.remove(AttributeName);
			itemTags.set("CustomTags", CustomTags);
			baseItem.setTag(itemTags);
			return CraftItemStack.asBukkitCopy(baseItem);
		}
		return null;
	}

	public static String getAttributeValueString(ItemStack item, String AttributeName) {
		if (item == null) {
			return null;
		}
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
		if (!itemTags.hasKey("CustomTags")) {
			return null;
		}
		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		if (!CustomTags.hasKey(AttributeName)) {
			return null;
		}
		return CustomTags.getString(AttributeName);
	}

	public static int getAttributeValueInt(ItemStack item, String AttributeName) {
		if (item == null) {
			return 0;
		}
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
		if (!itemTags.hasKey("CustomTags")) {
			return 0;
		}
		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		if (!CustomTags.hasKey(AttributeName)) {
			return 0;
		}
		return CustomTags.getInt(AttributeName);
	}

	public static double getAttributeValueDouble(ItemStack item, String AttributeName) {
		if (item == null) {
			return 0.0;
		}
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
		if (!itemTags.hasKey("CustomTags")) {
			return 0.0;
		}
		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		if (!CustomTags.hasKey(AttributeName)) {
			return 0.0;
		}
		return CustomTags.getDouble(AttributeName);
	}

	public static ArrayList<String> getAttributeList(ItemStack item) {
		if (item == null) {
			return null;
		}
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
		if (!itemTags.hasKey("CustomTags")) {
			return null;
		}
		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		Set<String> Taglist = CustomTags.c();
		return new ArrayList<String>(Taglist);
	}

	public static boolean hasAttribute(ItemStack item, String AtributeName) {
		if (item == null) {
			return false;
		}
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
		if (!itemTags.hasKey("CustomTags")) {
			return false;
		}
		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		return CustomTags.hasKey(AtributeName);
	}

	public static ItemStack setAttributeValue(ItemStack item, String AttributeName, int Value) {
		if (item != null) {
			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
			NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
			NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
			CustomTags.setInt(AttributeName, Value);
			itemTags.set("CustomTags", CustomTags);
			baseItem.setTag(itemTags);
			return CraftItemStack.asBukkitCopy(baseItem);
		}
		return null;
	}

	public static ItemStack setAttributeValue(ItemStack item, String AttributeName, double Value) {
		if (item != null) {
			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);
			NBTTagCompound itemTags = baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound();
			NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
			CustomTags.setDouble(AttributeName, Value);
			itemTags.set("CustomTags", CustomTags);
			baseItem.setTag(itemTags);
			return CraftItemStack.asBukkitCopy(baseItem);
		}
		return null;
	}
}
