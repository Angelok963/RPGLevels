package angelok.RPGLevels.com;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CmdHelp {

	private static final int maxpage = 3;

	public static boolean sendHelpMsg(String[] args, CommandSender sender) {

		int page = 1;

		if (args.length > 1)
			if (args[1].matches("[\\d]+"))
				page = Integer.valueOf(args[1]);

		if (page <= maxpage) {
			sender.sendMessage(
					"§7=================================§c (§eRPGLevels§c) §7=================================");
			sender.sendMessage("");
		}
		switch (page) {
		case 1:
			clicableText(sender, "/level see <Игрок>", "узнать уровень игрока");
			clicableText(sender, "/level set <Игрок> lvl <значение>", "установить уровень игроку");
			clicableText(sender, "/level set <Игрок> exp <значение>", "установить опыт игроку");
			clicableText(sender, "/level set <Игрок> skills <значение>", "установить количество скилов игроку");
			clicableText(sender, "/level set <Игрок> mana <значение>", "установить ману игроку");
			clicableText(sender, "/level set <Игрок> class <класс>", "установить класс игроку");
			clicableText(sender, "/level set <Игрок> class \"\"", "сбросить класс игроку");
			clicableText(sender, "/level setfirstspawn", "установить первичный спавн игроков");
			clicableText(sender, "/level setrpgspawn", "установить точку RPG спавна игроков");
			clicableText(sender, "/level help", "открыть список команд плагина RPGLevels");
			clicableText(sender, "/level class", "выбор класса / просмотр своей статистики");
			clicableText(sender, "/classcreate <класс>", "создать новый класс");
			clicableText(sender, "/classremove <класс>", "удалить класс");
			clicableText(sender, "/classinfo <класс>", "получить информацию о классе");
			clicableText(sender, "/classedit <класс> info <значение>", "установить описание класса");
			clicableText(sender, "/classedit <класс> info \"\"", "сбросить описание класса");
			sender.sendMessage("");
			pagechanger(sender, page);

			return true;

		case 2:
			clicableText(sender, "/classedit <класс> item <значение>", "установить предмет, в качестве иконки класса");
			clicableText(sender, "/classedit <класс> defaultheal <значение>",
					"установить начальное количество здоровья в классе");
			clicableText(sender, "/classedit <класс> changehealtolvl <значение>",
					"установить количество здоровья, добавляемого к лимиту за повышение уровня");
			clicableText(sender, "/classedit <класс> defaultmana <значение>",
					"установить начальное количество маны в классе");
			clicableText(sender, "/classedit <класс> changemanatolvl <значение>",
					"установить количество маны, добавляемой к лимиту за повышение уровня");
			clicableText(sender, "/classedit <класс> manapersecond <значение>",
					"установить количество регенерации маны в секунду");
			clicableText(sender, "/level reload", "перезагрузка конфига");
			clicableText(sender, "/level sync", "загрузить данные из хранилища в память сервера");
			clicableText(sender, "/level save", "выгрузить данные из памяти сервера в хранилище");
			clicableText(sender, "/amanage list", "получить список тегов предмета в руке");
			clicableText(sender, "/amanage set <имя атрибута> <значение>", "установить тег предмету в руке");
			sender.sendMessage("");
			pagechanger(sender, page);
			return true;
		case 3:
			clicableText(sender, "/amanage remove <имя атрибута>", "удалить тег с предмета в руке");
			for (int a = 0; a < 16; a++)
				sender.sendMessage("");
			pagechanger(sender, page);
		default:
			return false;
		}

	}

	private static void clicableText(CommandSender sender, String command, String info) {
		TextComponent message = new TextComponent("> ");
		message.setColor(ChatColor.RED);

		TextComponent cmd = new TextComponent(command);
		cmd.setColor(ChatColor.RED);
		cmd.setClickEvent(
				new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command.replace("<Игрок>", sender.getName())));
		cmd.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(org.bukkit.ChatColor.GRAY + "Нажмите, чтобы вставить " + org.bukkit.ChatColor.RED
						+ command.replace("<Игрок>", sender.getName())).create()));
		message.addExtra(cmd);

		TextComponent infocmd = new TextComponent(" - " + info);
		infocmd.setClickEvent((ClickEvent) null);
		infocmd.setHoverEvent((HoverEvent) null);
		infocmd.setColor(ChatColor.GRAY);
		message.addExtra(infocmd);

		sender.spigot().sendMessage(message);

	}

	private static void pagechanger(CommandSender sender, int page) {
		TextComponent message = new TextComponent("==================================== ");
		message.setColor(ChatColor.GRAY);

		TextComponent undo = new TextComponent("«");
		if (page > 1) {
			undo.setClickEvent(
					new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/level help " + String.valueOf(page - 1)));
			undo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(org.bukkit.ChatColor.GRAY + "Предыдущая страница").create()));

			undo.setColor(ChatColor.GREEN);
		} else
			undo.setColor(ChatColor.RED);
		message.addExtra(undo);

		TextComponent number = new TextComponent(" " + String.valueOf(page) + " ");
		number.setColor(ChatColor.YELLOW);
		message.addExtra(number);

		TextComponent redo = new TextComponent("»");
		if (page < maxpage) {
			redo.setClickEvent(
					new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/level help " + String.valueOf(page + 1)));
			redo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder(org.bukkit.ChatColor.GRAY + "Следующая страница").create()));

			redo.setColor(ChatColor.GREEN);
		} else
			redo.setColor(ChatColor.RED);
		message.addExtra(redo);

		TextComponent message2 = new TextComponent(" ====================================");
		message2.setColor(ChatColor.GRAY);
		message.addExtra(message2);

		sender.spigot().sendMessage(message);

	}
}
