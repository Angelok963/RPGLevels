package angelok.RPGLevels.com;

import java.io.IOException;

import org.bukkit.ChatColor;

public class Lang {

	public static String prefix() {
		return RPGLevels.lang.getString("prefix");
	}
	
	public static String cmd_sync_end() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + RPGLevels.lang.getString("cmd_sync_end"));
	}
	public static String cmd_sync_start() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("cmd_sync_start"));
	}
	public static String cmd_save_end() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("cmd_save_end"));
	}
	public static String cmd_save_start() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("cmd_save_start"));
	}
	public static String stateupdate_class() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_class"));
	}
	public static String infoupdate_class() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_class"));
	}
	public static String stateupdate_class_reset() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_class_reset"));
	}
	public static String stateupdate_mana() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_mana"));
	}
	public static String infoupdate_mana() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_mana"));
	}
	public static String allowedamountmana() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("allowedamountmana"));
	}
	public static String stateupdate_lvl() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_lvl"));
	}
	public static String infoupdate_lvl() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_lvl"));
	}
	public static String stateupdate_exp() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_exp"));
	}
	public static String infoupdate_exp() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_exp"));
	}
	public static String lvlnotexist() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("lvlnotexist"));
	}
	public static String allowedamountexp() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("allowedamountexp"));
	}
	public static String chooseclasstitle() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("chooseclasstitle"));
	}
	public static String noupdateexp() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("noupdateexp"));
	}
	public static String infoupdate_skills() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_skills"));
	}
	public static String stateupdate_skills() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("stateupdate_skills"));
	}
	public static String warn_nuber() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("warn_nuber"));
	}
	public static String cmdsee_heal() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_heal"));
	}
	public static String cmdsee_mana() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_mana"));
	}
	public static String cmdsee_exp() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_exp"));
	}
	public static String cmdsee_skills() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_skills"));
	}
	public static String cmdsee_lvl() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_lvl"));
	}
	public static String cmdsee_class() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("cmdsee_class"));
	}
	public static String playernotfound() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("playernotfound"));
	}
	public static String cfgreload_warn() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("cfgreload_warn"));
	}
	public static String cfgreload() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("cfgreload"));
	}
	public static String saverpgspawn() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("saverpgspawn"));
	}
	public static String savefirstspawn() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("savefirstspawn"));
	}
	public static String maxlvlattain() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("maxlvlattain"));
	}
	public static String newlvlattain() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("newlvlattain"));
	}
	public static String classremoved() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classremoved"));
	}
	public static String classinfo_manapersecond() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo_manapersecond"));
	}
	public static String classinfo_defaultheal() {
		return ChatColor.translateAlternateColorCodes('&',RPGLevels.lang.getString("classinfo_defaultheal"));
	}
	public static String classinfo_defaultmana() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo_defaultmana"));
	}
	public static String classinfo_changemanatolvl() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo_changemanatolvl"));
	}
	public static String classinfo_changehealtolvl() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo_changehealtolvl"));
	}
	public static String classinfo_item() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo_item"));
	}
	public static String empty() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("empty"));
	}
	public static String classinfo() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("classinfo"));
	}
	public static String classinfoset() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classinfoset"));
	}
	public static String classinforeset() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classinforeset"));
	}
	public static String setmanapersecond() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setmanapersecond"));
	}
	public static String setchangemanatolvl() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setchangemanatolvl"));
	}
	public static String setdefaultmana() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setdefaultmana"));
	}
	public static String setchangehealtolvl() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setchangehealtolvl"));
	}
	public static String setdefaultheal() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setdefaultheal"));
	}
	public static String saveddata() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("saveddata"));
	}
	public static String savingdata() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("savingdata"));
	}
	public static String nopermission() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("nopermission"));
	}
	public static String notconsole() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notconsole"));
	}
	public static String notiteminhand() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notiteminhand"));
	}
	public static String notvalidcmd() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notvalidcmd"));
	}
	public static String notattributs() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notattributs"));
	}
	public static String attributesee_top() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("attributesee_top"));
	}
	public static String attributesee_list() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("attributesee_list"));
	}
	public static String notattribute() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notattribute"));
	}
	public static String removeattribute() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("removeattribute"));
	}
	public static String setattribute() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("setattribute"));
	}
	public static String notcmdargument() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("notcmdargument"));
	}
	public static String personalinfo() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("personalinfo"));
	}
	public static String personalinfo_exp() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("personalinfo_exp"));
	}
	public static String choosedclass() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("choosedclass"));
	}
	public static String classalreadycreated() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classalreadycreated"));
	}
	public static String classcreated() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classcreated"));
	}
	public static String classnotfound() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("classnotfound"));
	}
	public static String itemnotexist() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("itemnotexist"));
	}
	public static String iconset() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("iconset"));
	}
	public static String numberwarn() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("numberwarn"));
	}
	public static String infoupdate_class_reset() {
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("infoupdate_class_reset"));
	}

	public static String getwithereffect() {
		if(!RPGLevels.lang.getString("getwithereffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("getwithereffect"));
		else return "";
	}

	public static String givewithereffect() {
		if(!RPGLevels.lang.getString("givewithereffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("givewithereffect"));
		else return "";
	}
	
	
	public static String getposioneffect() {
		if(!RPGLevels.lang.getString("getposioneffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("getposioneffect"));
		else return "";
	}

	public static String giveposioneffect() {
		if(!RPGLevels.lang.getString("giveposioneffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("giveposioneffect"));
		else return "";
	}
	
	public static String getfireeffect() {
		if(!RPGLevels.lang.getString("getfireeffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("getfireeffect"));
		else return "";
	}

	public static String givefireeffect() {
		if(!RPGLevels.lang.getString("givefireeffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("givefireeffect"));
		else return "";
	}
	
	public static String getblindnesseffect() {
		if(!RPGLevels.lang.getString("getblindnesseffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("getblindnesseffect"));
		else return "";
	}

	public static String giveblindnesseffect() {
		if(!RPGLevels.lang.getString("giveblindnesseffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("giveblindnesseffect"));
		else return "";
	}
	
	public static String getslownesseffect() {
		if(!RPGLevels.lang.getString("getslownesseffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("getslownesseffect"));
		else return "";
	}

	public static String giveslownesseffect() {
		if(!RPGLevels.lang.getString("giveslownesseffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("giveslownesseffect"));
		else return "";
	}
	
	public static String giveregenerationeffect() {
		if(!RPGLevels.lang.getString("giveregenerationeffect").isEmpty())
		return ChatColor.translateAlternateColorCodes('&',prefix() + RPGLevels.lang.getString("giveregenerationeffect"));
		else return "";
	}
	
	public static String creativemenutitle() {
		return ChatColor.translateAlternateColorCodes('&',RPGLevels.lang.getString("creativemenutitle"));
	}
	
	public static String creativemenunext() {
		return ChatColor.translateAlternateColorCodes('&',RPGLevels.lang.getString("creativemenunext"));
	}
	
	public static String creativemenuundo() {
		return ChatColor.translateAlternateColorCodes('&',RPGLevels.lang.getString("creativemenuundo"));
	}
	
	
	public static void loadDefautlLang() {
		saveLang();
		RPGLevels.lang.set("prefix", "&a&lRPG &r&b>>  ");
		
		RPGLevels.lang.set("creativemenuundo", "&7Предыдущая страница");
		RPGLevels.lang.set("creativemenunext", "&7Следующая страница");
		
		RPGLevels.lang.set("creativemenutitle", "&6[&7Список &cRPG &7предметов&6]");
		
		RPGLevels.lang.set("giveregenerationeffect", "&7Вы получили эффект &cрегенерации &7на &c{time} &7секунд!");
		
		RPGLevels.lang.set("giveslownesseffect", "&7Вы замедлили цель на &c{percent}% &7на &c{time} &7секунд!");
		RPGLevels.lang.set("getslownesseffect", "&7Вас замедлили на &c{percent}% &7на &c{time} &7секунд!");
		
		RPGLevels.lang.set("giveblindnesseffect", "&7Вы ослепили цель на &c{time} &7секунд!");
		RPGLevels.lang.set("getblindnesseffect", "&7Вас ослепили &7на &c{time} &7секунд!");
		
		RPGLevels.lang.set("givefireeffect", "&7Вы подожгли цель на &c{time} &7секунд!");
		RPGLevels.lang.set("getfireeffect", "&7Вас подожгли &7на &c{time} &7секунд!");
		
		RPGLevels.lang.set("giveposioneffect", "&7Вы наложили эффект &cотравления &7на &c{time} &7секунд!");
		RPGLevels.lang.set("getposioneffect", "&7На вас наложен эффект &cотравления &7на &c{time} &7секунд!");
		
		RPGLevels.lang.set("givewithereffect", "&7Вы наложили эффект &cиссушения &7на &c{time} &7секунд!");
		RPGLevels.lang.set("getwithereffect", "&7На вас наложен эффект &cиссушения &7на &c{time} &7секунд!");
		RPGLevels.lang.set("savingdata", "&7Сохраняем ваши данные...");
		RPGLevels.lang.set("saveddata", "&7Данные &cуспешно &7сохранены...");
		RPGLevels.lang.set("notpermission", "&7У вас &cнедостаточно &7прав!");
		RPGLevels.lang.set("notconsole", "&7Команда доступна только &cигроку&7!");
		RPGLevels.lang.set("notiteminhand", "&7Вы должны держать предмет в руке!");
		RPGLevels.lang.set("notvalidcmd",
				"&7Команда введена &cневерно&7. Используйте &c/level help &7для просмотра доступных команд.");
		RPGLevels.lang.set("notattributs", "&7В этом предмете &cнет &7атрибутов!");
		RPGLevels.lang.set("attributesee_top", "&cПросмотр атрибутов предмета");
		RPGLevels.lang.set("attributesee_list", "&8{Number} &7{AttributeName}&c: {AttributeValue}");
		RPGLevels.lang.set("notattribute", "&7Атрибута &c{AttributeName} &7на предмете нет!");
		RPGLevels.lang.set("removeattribute", "&7Атрибут &c{AttributeName} &7удалён с предмета!");
		RPGLevels.lang.set("setattribute",
				"&7Атрибут &c{AttributeName} &7добавлен к предмету с значением \"&c{AttributeValue}&7\"!");
		RPGLevels.lang.set("notcmdargument",
				"&7Подкоманда &e{CmdArg} &cне существует&7. Используйте &c/level help &7для помощи.");
		RPGLevels.lang.set("chooseclasstitle", "&6[&0Выберите класс, за который будете играть&6]");
		RPGLevels.lang.set("personalinfo", "&7Ваш класс: &c{Class}&7. Уровень: &c{lvl}&7.");
		RPGLevels.lang.set("personalinfo_exp", "&7Опыт: &c{Exp} &7из &c{ExpTolvl}");
		RPGLevels.lang.set("choosedclass", "&7Выбран класс &c{Class}&7!");

		RPGLevels.lang.set("classalreadycreated", "&7Класс &c{Class} &7уже существует!");
		RPGLevels.lang.set("classcreated", "&7Класс &c{Class} &7успешно создан!");
		RPGLevels.lang.set("classnotfound", "&7Класс &c{Class} &7не найден!");
		RPGLevels.lang.set("itemnotexist", "&7Предмета &c{Item} &7не существует!");
		RPGLevels.lang.set("iconset", "Предмет &c{Item} &7установлен иконкой класса.");
		RPGLevels.lang.set("numberwarn", "&7Значение должно быть &cчисловое&7. Пример: &c10.5");
		RPGLevels.lang.set("setdefaultheal", "&7Установлено значение здоровья в классе по умолчанию: &c{Heal}");
		RPGLevels.lang.set("setchangehealtolvl",
				"&7Установлено значение здоровья, добавляемого за повышение уровня: &c{Heal}");
		RPGLevels.lang.set("setdefaultmana", "&7Установлено значение маны в классе по умолчанию: &c{Mana}");
		RPGLevels.lang.set("setchangemanatolvl",
				"&7Установлено значение маны, добавляемой за повышение уровня: &c{Mana}");
		RPGLevels.lang.set("setmanapersecond", "&7Установлена скорость регенерации маны: &c{Mana}/сек.");
		RPGLevels.lang.set("classinforeset", "&7Информация о классе &cсброшена&7.");
		RPGLevels.lang.set("classinfoset", "&7Установлена информация о классе:");
		RPGLevels.lang.set("classinfo", "&c> &8[info]&7Информация о классе: ");
		RPGLevels.lang.set("empty", "&c<Не задано>");

		RPGLevels.lang.set("classinfo_item", "&c> &8[item]&7Предмет в качестве иконки класса: &c{info}");
		RPGLevels.lang.set("classinfo_changehealtolvl",
				"&c> &8[changehealtolvl]&7Количество добавляемого здоровья за повышение уровня: &c{info}");
		RPGLevels.lang.set("classinfo_changemanatolvl",
				"&c> &8[changemanatolvl]&7Количество добавляемой маны за повышение уровня: &c{info}");
		RPGLevels.lang.set("classinfo_defaultmana", "&c> &8[defaultmana]&7Лимит маны в классе по умолчанию: &c{info}");
		RPGLevels.lang.set("classinfo_defaultheal",
				"&c> &8[defaultheal]&7Лимит здоровья в классе по умолчанию: &c{info}");
		RPGLevels.lang.set("classinfo_manapersecond",
				"&c> &8[manapersecond]&7Скорость регенерации маны в классе: &c{info}/сек.");

		RPGLevels.lang.set("classremoved", "&7Класс &c{Class} &7удалён!");

		RPGLevels.lang.set("newlvlattain", "&7Новый уровень достигнут: &c{lvl}&7!");
		RPGLevels.lang.set("maxlvlattain", "&7Вы достигли максимальный уровень &c{lvl} &7уровень!");
		RPGLevels.lang.set("savefirstspawn", "&7Ваша локация &aуспешно &7сохранена как первичная точка спавна.");
		RPGLevels.lang.set("saverpgspawn", "&7Ваша локация &aуспешно &7сохранена как RPG точка спавна.");

		RPGLevels.lang.set("cfgreload", "&7Конфигурация &cуспешно &7перезагружена.");
		RPGLevels.lang.set("cfgreload_warn",
				"&7Конфигурация перезагружена, однако обнаружена попытка смены типа хранения данных и была &cподавлена&7! \nДля смены типа данных, пожалуйста, &cвыключите &7сервер, смените параметр в конфигурации и запустите сервер!");

		RPGLevels.lang.set("playernotfound", "&7Игрок &e{Player} &cне найден&7.");

		RPGLevels.lang.set("cmdsee_class", "&c> &7Класс игрока: &c{info}");
		RPGLevels.lang.set("cmdsee_lvl", "&c> &7Уровень игрока: &c{info}");
		RPGLevels.lang.set("cmdsee_skills", "&c> &7Скилы игрока: &c{info}");
		RPGLevels.lang.set("cmdsee_exp", "&c> &7Опыт игрока: &c{exp} &7из &c{exptolvl}");
		RPGLevels.lang.set("cmdsee_mana", "&c> &7Мана игрока: &c{mana} &7из &c{maxmana}");
		RPGLevels.lang.set("cmdsee_heal", "&c> &7Жизни игрока: &c{heal} &7из &c{maxheal}");

		RPGLevels.lang.set("warn_nuber", "&7Количество должно быть больше &c0&7!");

		RPGLevels.lang.set("stateupdate_skills",
				"&7Количество скилов обновлено. Теперь вы имеете &c{amount} &7скилов.");
		RPGLevels.lang.set("infoupdate_skills", "&7Теперь игрок &c{Player} &7имеет &c{amount} &7cкилов.");

		RPGLevels.lang.set("noupdateexp",
				"&7Игрок &c{Player} &7имеет максимальный уровень. Установить опыт &cневозможно&7.");

		RPGLevels.lang.set("allowedamountexp",
				"&7На текущий момент игроку &c{Player} &7можно установить от &c0 до &c{maxexp} &7опыта.");

		RPGLevels.lang.set("stateupdate_exp", "&7Количество опыта обновлено. Теперь вы имеете &c{amount} &7опыта.");
		RPGLevels.lang.set("infoupdate_exp", "&7Теперь игрок &c{Player} &7имеет &c{amount} &7опыта.");

		RPGLevels.lang.set("lvlnotexist", "&7Уровень &e{lvl} &cне существует&7. Максимальный уровень: &c{maxlvl}&7.");

		RPGLevels.lang.set("stateupdate_lvl", "&7Уровень обновлён. Теперь у вас &c{amount} &7уровень.");
		RPGLevels.lang.set("infoupdate_lvl", "&7Теперь игрок &c{Player} &7имеет &c{amount} &7уровень.");

		RPGLevels.lang.set("allowedamountmana",
				"&7На текущий момент игроку &c{Player} &7можно установить от &c0 до &c{maxmana} &7маны.");

		RPGLevels.lang.set("stateupdate_mana", "&7Мана обновлёна. Теперь у вас &c{amount} &7маны.");
		RPGLevels.lang.set("infoupdate_mana", "&7Теперь игрок &c{Player} &7имеет &c{amount} &7маны.");

		RPGLevels.lang.set("stateupdate_class_reset", "&7Ваш класс был &cсброшен&7!");
		RPGLevels.lang.set("infoupdate_class_reset", "&7Класс игрока &c{Player} &7сброшен!");

		RPGLevels.lang.set("stateupdate_class", "&7Класс обновлён. Теперь вы в классе &c{Class}");
		RPGLevels.lang.set("infoupdate_class", "&7Теперь игрок &c{Player} &7находится в классе &c{Class}");

		RPGLevels.lang.set("cmd_save_start", "&cСохраняем данные в хранилище...");
		RPGLevels.lang.set("cmd_save_end", "&cСохранение &cуспешно &7завершено!");

		RPGLevels.lang.set("cmd_sync_start", "&cСинхронизируемся с хранилищем данных...");
		RPGLevels.lang.set("cmd_sync_end", "&cСинхронизация &cуспешно &7завершена!");	
		saveLang();
	}
	
	protected static void saveLang() {

		try {
			RPGLevels.lang.save(RPGLevels.langfile);
		} catch (IOException r) {
			r.printStackTrace();
		}

	}
}