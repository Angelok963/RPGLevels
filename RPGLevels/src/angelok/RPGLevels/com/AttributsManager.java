package angelok.RPGLevels.com;

import java.util.ArrayList;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public interface AttributsManager {

	/**
	 * Устанавливает значение атрибута если такой существует. Если атрибут уже
	 * установлен - переопределяет значение
	 * 
	 * @param item
	 *            - Объект ItemStack
	 * @param AttributeName
	 *            - Название атрибута
	 * @param Value
	 *            - Значение атрибута
	 * @return ItemStack с атрибутом
	 */
	default ItemStack setAttributeValue(ItemStack item, String AttributeName, String Value) {

		if (item != null) {

			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemTags = (baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound());

			NBTTagCompound CustomTags = new NBTTagCompound();

			CustomTags.setString(AttributeName, Value);

			itemTags.set("CustomTags", CustomTags);

			baseItem.setTag(itemTags);
			return CraftItemStack.asBukkitCopy(baseItem);

		}
		return null;
	}
	
	
	
	
	
	/**
	 * Устанавливает значение атрибута если такой существует. Если атрибут уже
	 * установлен - переопределяет значение
	 * 
	 * @param item
	 *            - Объект ItemStack
	 * @param AttributeName
	 *            - Название атрибута
	 * @return Значение атрибута. Если атрибута нет - null
	 */
	default String getAttributeValue(ItemStack item, String AttributeName) {

		if (item != null) {

			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemTags = (baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound());

			if (!itemTags.hasKey("CustomTags"))
				return null;

			NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");

			if (!CustomTags.hasKey(AttributeName))
				return null;

		return CustomTags.getString(AttributeName);

		}
		return null;
	}
	/**
	 * В разработке!
	 */
	@SuppressWarnings("unused")
	default ArrayList<String> getAttributeList(ItemStack item){
	
		
		if (item != null) {

			net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemTags = (baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound());

			if (!itemTags.hasKey("CustomTags"))
				return null;

			NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");
		/*	
			NBTTagList list = (NBTTagList)CustomTags.get("");
			
			System.out.print(list.size());
			
			for(int s = 0; s<list.size(); s++){
				NBTTagCompound f = list.get(s);
				System.out.println(f);
			}
		*/
	}
		return null;
	}
	/**
	 * Проверяет наличие атрибута в ItemStack
	 * @param item
	 *            - Объект ItemStack
	 * @param AttributeName
	 *            - Название атрибута
	 * @return true если в ItemStack имеется указанный атрибут, иначе false
	 */
	default boolean hasAttribute(ItemStack item, String AtributeName){
		
		if(item == null) return false;
		
		net.minecraft.server.v1_12_R1.ItemStack baseItem = CraftItemStack.asNMSCopy(item);

		NBTTagCompound itemTags = (baseItem.hasTag() ? baseItem.getTag() : new NBTTagCompound());

		if (!itemTags.hasKey("CustomTags"))
			return false;

		NBTTagCompound CustomTags = itemTags.getCompound("CustomTags");

		if (CustomTags.hasKey(AtributeName))
			return true;
		
		return false;
	}
}
