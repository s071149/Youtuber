package TrpkGod.Youtuber.Commands.Youtube;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.YoutubeCommand;

public class AddYoutuber extends YoutubeCommand {

	public AddYoutuber() {
		super("Add", "Add an YouTuber to the list", "");
	}

	public void run(CommandSender sender, String[] args) {
		if(sender.hasPermission("youtuber.admin")) {
			if (args.length < 0) {
				sender.sendMessage(ChatColor.RED + "You did not enter enough args.");
				return;
			}
			
			String name = args[0];
			
			SettingsManager.getInstance().addYoutuber(name);
			sender.sendMessage("You've successfully added " + name + " to the YouTube List");
		}
	}

}
