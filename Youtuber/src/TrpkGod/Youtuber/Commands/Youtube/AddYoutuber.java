package TrpkGod.Youtuber.Commands.Youtube;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.YoutubeCommand;

public class AddYoutuber extends YoutubeCommand {

	public AddYoutuber() {
		super("Add", "Add an YouTuber to the list", "<player> <link>");
	}

	public void run(CommandSender sender, String[] args) {
		if(sender.hasPermission("youtuber.admin")) {
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "You did not enter enough args.");
				return;
			}
			
			String name = args[0];
			String link = args[1];
			
			SettingsManager.getInstance().addYoutuber(name, link);
			sender.sendMessage("You've successfully added " + name + " to the YouTube List.");
		}
	}

}
