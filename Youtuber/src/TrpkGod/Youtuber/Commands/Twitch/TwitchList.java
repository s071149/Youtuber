package TrpkGod.Youtuber.Commands.Twitch;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.TwitchCommand;

public class TwitchList extends TwitchCommand {
	
	private Plugin p = SettingsManager.getInstance().getPlugin();
	public List<String> twitch = p.getConfig().getStringList("twitch");
	
	public TwitchList() {
		super("List", "List of LiveStreamers", "");
	}

	public void run(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.RED + "Twitch List:");
		for(String twitchList : twitch) {
			twitchList.replace(" ", "");
			sender.sendMessage(ChatColor.GRAY + " - " + twitchList);
		}
	}

}
