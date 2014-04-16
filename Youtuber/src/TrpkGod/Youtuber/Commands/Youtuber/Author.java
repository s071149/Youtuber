package TrpkGod.Youtuber.Commands.Youtuber;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.Commands.YoutuberCommand;

public class Author extends YoutuberCommand {

	public Author() {
		super("Author", "Creator of Youtuber", "");
	}

	public void run(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.DARK_GRAY + "Youtuber was created by TrpkGod.");
	}

}
