package TrpkGod.Youtuber;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Youtuber extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static File pluginFolder;
	public static File configFile;

	public static FileConfiguration config;

	public static Youtuber plugin;
	protected YoutuberLogger log;

	public boolean recording = false;
	public String gender = null;
	public String youtube;
	public String twitch;
	public String incMsg = "Incorrect format: /<command> <subcommand> <args>";

	public void onEnable() {
		log = new YoutuberLogger(this);

		getLogger().info("Youtuber Enabled.");

		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void onDisable() {
		getLogger().info("Youtuber Disabled");
		saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (!(p instanceof Player)) {
			p.sendMessage(ChatColor.RED + "Must be a player to use commands.");
			return true;
		}
		if (gender == null) {
			gender = "they";
		} else if (gender == "boy") {
			gender = "he";
		} else if (gender == "girl") {
			gender = "she";
		}
		if (cmd.getName().equalsIgnoreCase("youtuber")) {
			p.sendMessage(ChatColor.GRAY + "-------:[" + ChatColor.RED + "Youtuber Plugin" + ChatColor.GRAY + "]:-------");
			p.sendMessage(ChatColor.GRAY + "/youtube");
			if(p.hasPermission("youtuber.record")) {
				p.sendMessage(ChatColor.GRAY + "/youtube record [start / stop]");
			}
			p.sendMessage(ChatColor.GRAY + "/youtube list");
			p.sendMessage(ChatColor.GRAY + " ");
			p.sendMessage(ChatColor.GRAY + "/twitch");
			if(p.hasPermission("youtuber.record")) {
				p.sendMessage(ChatColor.GRAY + "/twitch record [start / stop]");
			}
			p.sendMessage(ChatColor.GRAY + "/twitch list");
			p.sendMessage(ChatColor.GRAY + "");
			if(p.hasPermission("youtuber.api")) {
				p.sendMessage(ChatColor.GRAY + "/api");
				if(p.hasPermission("youtuber.api.gender")) {
					p.sendMessage(ChatColor.GRAY + "/api gender [boy / girl]");
				}
				if(p.hasPermission("youtuber.api.setup")) {
					p.sendMessage(ChatColor.GRAY + "/api setup [install / reinstall / uninstall]");
				}
				p.sendMessage(ChatColor.GRAY + "");
			}
			p.sendMessage(ChatColor.GRAY + "Created by TrpkGod.");
		} else if (cmd.getName().equalsIgnoreCase("youtube")) {
			if (args.length > 0) {
				if(p.hasPermission("youtuber.record")) {
					if (args[0].equalsIgnoreCase("record")) {
						if (args[1].equalsIgnoreCase("start")) {
							if (recording == true) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're already recording, use [/youtube record stop] to stop your recording");
							} else {
								recording = true;
								Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just started to record, " + gender + " may not respond.");
							}
						} else if (args[1].equalsIgnoreCase("stop")) {
							if (recording == false) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're not recording, please start recording to stop recording.");
							} else {
								recording = false;
								Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just stop recording.");
							}
						} else {
							p.sendMessage(ChatColor.RED + incMsg);
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
				}
				
				if (args[0].equalsIgnoreCase("list")) {
					p.sendMessage(ChatColor.RED + "Youtube List:");
					p.sendMessage(ChatColor.GRAY + getConfig().getString(youtube));
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("twitch")) {
			if (args.length > 0) {
				if(p.hasPermission("youtuber.record")) {
					if (args[0].equalsIgnoreCase("record")) {
						if (args[1].equalsIgnoreCase("start")) {
							if (recording == true) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're already livestreaming, use [/twitch record stop] to stop your livestream");
							} else {
								recording = true;
								Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just started to livestream, " + gender + " may not respond.");
							}
						} else if (args[1].equalsIgnoreCase("stop")) {
							if (recording == false) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're not livestreaming, please start livestreaming to stop livestreaming.");
							} else {
								recording = false;
								Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just stop livestreaming.");
							}
						} else {
							p.sendMessage(ChatColor.RED + incMsg);
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
				}
				if (args[0].equalsIgnoreCase("list")) {
					p.sendMessage(ChatColor.RED + "Twitch List:");
					p.sendMessage(ChatColor.GRAY + getConfig().getString(twitch));
				}
			}	
		}
		if(p.hasPermission("youtuber.api")) {
			if (cmd.getName().equalsIgnoreCase("api")) {
				if(args.length > 0) {
					if(p.hasPermission("youtuber.api.gender")) {
						if(args[0].equalsIgnoreCase("gender")) {
							if(args[1].equalsIgnoreCase("boy")) {
								gender = "boy";
								p.sendMessage(ChatColor.GREEN + "Gender set to " + gender + ".");
							} else if(args[1].equalsIgnoreCase("girl")) {
								gender = "girl";
								p.sendMessage(ChatColor.GREEN + "Gender set to " + gender + ".");
							} else {
								p.sendMessage(ChatColor.RED + incMsg);
							}
						}
					} else {
						p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
					}
					if(p.hasPermission("youtuber.api.setup")) {
						if(args[0].equalsIgnoreCase("setup")) {
							p.sendMessage("Coming Soon");
						}
					} else {
						p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
					}
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
		}
		return false;
	}
}