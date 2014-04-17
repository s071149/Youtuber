package TrpkGod.Youtuber.Commands.Twitch;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.TwitchCommand;

public class AddLiveStreamer extends TwitchCommand {
	
	public AddLiveStreamer() {
		super("Add", "Add an Livestreamer to the list", "<player> <link>");
	}
	
	public void run(CommandSender sender, String[] args) {
		if(sender.hasPermission("youtuber.admin")) {
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "You did not enter enough args.");
				return;
			}
			
			String name = args[0];
			String link = args[1];
			
			SettingsManager.getInstance().addLivestreamer(name, link);
			sender.sendMessage("You've successfully added " + name + " to the Twitch List");
		}
	}
	
}
