package TrpkGod.Youtuber.Commands.Twitch;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.TwitchCommand;

public class AddLiveStreamer extends TwitchCommand {
	
	public AddLiveStreamer() {
		super("Add", "Add an liveStreamer to the list", "");
	}
	
	public void run(CommandSender sender, String[] args) {
		if(sender.hasPermission("youtuber.admin")) {
			if (args.length < 0) {
				sender.sendMessage(ChatColor.RED + "You did not enter enough args.");
				return;
			}
			
			String name = args[0];
			
			SettingsManager.getInstance().addTwitch(name);
			sender.sendMessage("You've successfully added " + name + " to the Twitch List");
		}
	}
	
}
