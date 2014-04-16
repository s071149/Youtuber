package TrpkGod.Youtuber.Commands.Youtube;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import TrpkGod.Youtuber.Commands.YoutubeCommand;

public class Record extends YoutubeCommand {
	
	private boolean recording = false;
	
	public Record() {
		super("Record", "Let the server know you're recording", "");
	}

	public void run(CommandSender sender, String[] args) {
		if (sender.hasPermission("youtuber.record")) {
			if (recording == false) {
				recording = true;
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + sender.getName() + " has just started there recording, they may not respond.");
			} else if(recording == true) {
				recording = false;
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + sender.getName() + " has just ended there recording.");
			} else {
				sender.sendMessage("Error occurred.");
			}
		} else {
			sender.sendMessage("You don't have permission for this command.");
		}
	}

}
