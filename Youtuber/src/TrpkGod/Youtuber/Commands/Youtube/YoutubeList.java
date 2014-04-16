package TrpkGod.Youtuber.Commands.Youtube;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.YoutubeCommand;

public class YoutubeList extends YoutubeCommand {
	
	private Plugin p = SettingsManager.getInstance().getPlugin();
	public List<String> youtube = p.getConfig().getStringList("youtube");
	
	public YoutubeList() {
		super("List", "List of YouTubers", "");
	}

	public void run(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.RED + "YouTube List:");
		for(String youtubeList : youtube) {
			youtubeList.replace(" ", "");
			sender.sendMessage(ChatColor.GRAY + " - " + youtubeList);
		}
	}

}
