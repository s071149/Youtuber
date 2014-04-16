package TrpkGod.Youtuber.Commands.Youtuber;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import TrpkGod.Youtuber.SettingsManager;
import TrpkGod.Youtuber.Commands.YoutuberCommand;

public class Reload extends YoutuberCommand {

	public Reload() {
		super("Reload", "Reload the plugin", "");
	}
	
	public void run(CommandSender sender, String[] args) {
		if(sender.hasPermission("youtuber.reload")) {
			try {
				Plugin p = SettingsManager.getInstance().getPlugin();
				String v = SettingsManager.getInstance().getVersion();
				p.saveConfig();
				p.reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "Youtuber v" + v + " has been reloaded successfully.");
			} catch(Exception e) {
				e.getMessage();
			}
		}
	}

}
