package angelok.RPGLevels.com;

import java.io.IOException;

import org.bukkit.ChatColor;

public class Lang {

	public static String prefix() {
		return RPGLevels.lang.getString("prefix");
	}

	public static String not_skills() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("not_skills"));
	}
	
	public static String skillsmenuinfo3() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillsmenuinfo3"));
	}
	
	public static String skillsmenuinfo2() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillsmenuinfo2"));
	}
	
	public static String skillsmenuinfo1() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillsmenuinfo1"));
	}
	
	public static String skillsmenutitle() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillsmenutitle"));
	}

	public static String skillinfo_cost() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillinfo_cost"));
	}

	public static String skillinfo_lvl_max() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillinfo_lvl_max"));
	}

	public static String skillinfo_lvl() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("skillinfo_lvl"));
	}

	public static String skillinfo_lvl_not() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("skillinfo_lvl_not"));
	}

	public static String cmd_sync_end() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cmd_sync_end"));
	}

	public static String cmd_sync_start() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cmd_sync_start"));
	}

	public static String cmd_save_end() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cmd_save_end"));
	}

	public static String cmd_save_start() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cmd_save_start"));
	}

	public static String stateupdate_class() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_class"));
	}

	public static String infoupdate_class() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_class"));
	}

	public static String stateupdate_class_reset() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_class_reset"));
	}

	public static String stateupdate_mana() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_mana"));
	}

	public static String infoupdate_mana() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_mana"));
	}

	public static String allowedamountmana() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("allowedamountmana"));
	}

	public static String stateupdate_lvl() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_lvl"));
	}

	public static String infoupdate_lvl() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_lvl"));
	}

	public static String stateupdate_exp() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_exp"));
	}

	public static String infoupdate_exp() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_exp"));
	}

	public static String lvlnotexist() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("lvlnotexist"));
	}

	public static String allowedamountexp() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("allowedamountexp"));
	}

	public static String chooseclasstitle() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("chooseclasstitle"));
	}

	public static String noupdateexp() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("noupdateexp"));
	}

	public static String infoupdate_skills() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_skills"));
	}

	public static String stateupdate_skills() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("stateupdate_skills"));
	}

	public static String warn_nuber() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("warn_nuber"));
	}

	public static String cmdsee_heal() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_heal"));
	}

	public static String cmdsee_mana() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_mana"));
	}

	public static String cmdsee_exp() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_exp"));
	}

	public static String cmdsee_skills() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_skills"));
	}

	public static String cmdsee_lvl() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_lvl"));
	}

	public static String cmdsee_class() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("cmdsee_class"));
	}

	public static String playernotfound() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("playernotfound"));
	}

	public static String cfgreload_warn() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cfgreload_warn"));
	}

	public static String cfgreload() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("cfgreload"));
	}

	public static String saverpgspawn() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("saverpgspawn"));
	}

	public static String savefirstspawn() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("savefirstspawn"));
	}

	public static String maxlvlattain() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("maxlvlattain"));
	}

	public static String newlvlattain() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("newlvlattain"));
	}

	public static String classremoved() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classremoved"));
	}

	public static String classinfo_manapersecond() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_manapersecond"));
	}

	public static String classinfo_defaultheal() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_defaultheal"));
	}

	public static String classinfo_defaultmana() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_defaultmana"));
	}

	public static String classinfo_changemanatolvl() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_changemanatolvl"));
	}

	public static String classinfo_changehealtolvl() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_changehealtolvl"));
	}

	public static String classinfo_item() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("classinfo_item"));
	}

	public static String empty() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("empty"));
	}

	public static String classinfo() {
		return ChatColor.translateAlternateColorCodes('&', ChatColor.RESET + RPGLevels.lang.getString("classinfo"));
	}

	public static String classinfoset() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classinfoset"));
	}

	public static String classinforeset() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classinforeset"));
	}

	public static String setmanapersecond() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setmanapersecond"));
	}

	public static String setchangemanatolvl() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setchangemanatolvl"));
	}

	public static String setdefaultmana() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setdefaultmana"));
	}

	public static String setchangehealtolvl() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setchangehealtolvl"));
	}

	public static String setdefaultheal() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setdefaultheal"));
	}

	public static String saveddata() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("saveddata"));
	}

	public static String savingdata() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("savingdata"));
	}

	public static String nopermission() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("nopermission"));
	}

	public static String notconsole() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notconsole"));
	}

	public static String notiteminhand() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notiteminhand"));
	}

	public static String notvalidcmd() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notvalidcmd"));
	}

	public static String notattributs() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notattributs"));
	}

	public static String attributesee_top() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("attributesee_top"));
	}

	public static String attributesee_list() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("attributesee_list"));
	}

	public static String notattribute() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notattribute"));
	}

	public static String removeattribute() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("removeattribute"));
	}

	public static String setattribute() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("setattribute"));
	}

	public static String notcmdargument() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("notcmdargument"));
	}

	public static String personalinfo() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("personalinfo"));
	}

	public static String personalinfo_exp() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("personalinfo_exp"));
	}

	public static String choosedclass() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("choosedclass"));
	}

	public static String classalreadycreated() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classalreadycreated"));
	}

	public static String classcreated() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classcreated"));
	}

	public static String classnotfound() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("classnotfound"));
	}

	public static String itemnotexist() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("itemnotexist"));
	}

	public static String iconset() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("iconset"));
	}

	public static String numberwarn() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("numberwarn"));
	}

	public static String infoupdate_class_reset() {
		return ChatColor.translateAlternateColorCodes('&',
				prefix() + RPGLevels.lang.getString("infoupdate_class_reset"));
	}

	public static String getwithereffect() {
		if (!RPGLevels.lang.getString("getwithereffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("getwithereffect"));
		else
			return "";
	}

	public static String givewithereffect() {
		if (!RPGLevels.lang.getString("givewithereffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("givewithereffect"));
		else
			return "";
	}

	public static String getposioneffect() {
		if (!RPGLevels.lang.getString("getposioneffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("getposioneffect"));
		else
			return "";
	}

	public static String giveposioneffect() {
		if (!RPGLevels.lang.getString("giveposioneffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("giveposioneffect"));
		else
			return "";
	}

	public static String getfireeffect() {
		if (!RPGLevels.lang.getString("getfireeffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("getfireeffect"));
		else
			return "";
	}

	public static String givefireeffect() {
		if (!RPGLevels.lang.getString("givefireeffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("givefireeffect"));
		else
			return "";
	}

	public static String getblindnesseffect() {
		if (!RPGLevels.lang.getString("getblindnesseffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("getblindnesseffect"));
		else
			return "";
	}

	public static String giveblindnesseffect() {
		if (!RPGLevels.lang.getString("giveblindnesseffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("giveblindnesseffect"));
		else
			return "";
	}

	public static String getslownesseffect() {
		if (!RPGLevels.lang.getString("getslownesseffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("getslownesseffect"));
		else
			return "";
	}

	public static String giveslownesseffect() {
		if (!RPGLevels.lang.getString("giveslownesseffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("giveslownesseffect"));
		else
			return "";
	}

	public static String giveregenerationeffect() {
		if (!RPGLevels.lang.getString("giveregenerationeffect").isEmpty())
			return ChatColor.translateAlternateColorCodes('&',
					prefix() + RPGLevels.lang.getString("giveregenerationeffect"));
		else
			return "";
	}

	public static String creativemenutitle() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("creativemenutitle"));
	}

	public static String creativemenunext() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("creativemenunext"));
	}

	public static String creativemenuundo() {
		return ChatColor.translateAlternateColorCodes('&',
				ChatColor.RESET + RPGLevels.lang.getString("creativemenuundo"));
	}

	public static String skillinfo() {
		return ChatColor.translateAlternateColorCodes('&', RPGLevels.lang.getString("skillinfo"));
	}

	protected static void saveLang() {

		try {
			RPGLevels.lang.save(RPGLevels.langfile);
		} catch (IOException r) {
			r.printStackTrace();
		}

	}
}