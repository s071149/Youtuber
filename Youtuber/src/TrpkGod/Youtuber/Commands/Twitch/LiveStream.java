package TrpkGod.Youtuber.Commands.Twitch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.Commands.TwitchCommand;

public class LiveStream extends TwitchCommand {
	
	public boolean recording = false;
	
	public LiveStream() {
		super("Record", "Let the server know you're livestreaming", "");
	}

	public void run(CommandSender sender, String[] args) {
		if (sender.hasPermission("youtuber.record")) {
			if (recording == false) {
				recording = true;
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + sender.getName() + " has just started there livestream, they may not respond.");
			} else if(recording == true) {
				recording = false;
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + sender.getName() + " has just ended there livestream.");
			} else {
				sender.sendMessage("Error occurred.");
			}
		} else {
			sender.sendMessage("You don't have permission for this command.");
		}
	}

}
