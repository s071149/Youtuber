package TrpkGod.Youtuber;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Youtuber extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static File pluginFolder;
	public static File configFile;

	public static FileConfiguration config;

	public static Youtuber plugin;
	protected YoutuberLogger log;

	public boolean recording = false;
	public boolean installed = false;
	public String gender = null;
	public String incMsg = "Incorrect format: /<command> <subcommand> <args>";
	public String record;

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
			if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				if (!(p instanceof Player)) {
					reloadConfig();
					saveConfig();
					p.sendMessage(ChatColor.GRAY + "[Youtuber] Config Reloaded! ");
					return true;
				} else if (p.hasPermission("youtuber.reload")) {
					reloadConfig();
					saveConfig();
					p.sendMessage(ChatColor.GRAY + "[Youtuber] Config reloaded!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.GRAY + "-------:[" + ChatColor.RED + "Youtuber Plugin v" + getDescription().getVersion() + ChatColor.GRAY + "]:-------");
				p.sendMessage(ChatColor.GRAY + "/youtube");
				if (p.hasPermission("youtuber.record")) {
					p.sendMessage(ChatColor.GRAY + "/youtube -r [start / stop]");
				}
				p.sendMessage(ChatColor.GRAY + "/youtube list");
				p.sendMessage(ChatColor.GRAY + " ");
				p.sendMessage(ChatColor.GRAY + "/twitch");
				if (p.hasPermission("youtuber.record")) {
					p.sendMessage(ChatColor.GRAY + "/twitch -r [start / stop]");
				}
				p.sendMessage(ChatColor.GRAY + "/twitch list");
				p.sendMessage(ChatColor.GRAY + "");
				if(p.hasPermission("youtuber.reload")) {
					p.sendMessage(ChatColor.GRAY + "/youtuber reload");
					p.sendMessage(ChatColor.GRAY + "");
				}
				if (p.hasPermission("youtuber.api")) {
					p.sendMessage(ChatColor.GRAY + "/api");
					if (p.hasPermission("youtuber.api.gender")) {
						p.sendMessage(ChatColor.GRAY + "/api gender [boy / girl]");
					}
					if (p.hasPermission("youtuber.api.setup")) {
						p.sendMessage(ChatColor.GRAY + "/api setup [install / reinstall / uninstall]");
					}
					p.sendMessage(ChatColor.GRAY + "");
				}
				p.sendMessage(ChatColor.GRAY + "Created by TrpkGod.");
			}
		} else if (cmd.getName().equalsIgnoreCase("youtube")) {
			if (args.length > 0) {
				if (p.hasPermission("youtuber.record")) {
					if (args[0].equalsIgnoreCase("-r")) {
						if (args[1].equalsIgnoreCase("start")) {
							if (recording == true) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're already recording, use [/youtube record stop] to stop your recording");
							} else {
								recording = true;
								record = "recording";
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
					List<String> youtube = getConfig().getStringList("youtube");
					for (String msgYoutube : youtube) {
						msgYoutube.replace(" ", "");
						p.sendMessage(ChatColor.GRAY + " - " + msgYoutube);
					}
					return true;
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("twitch")) {
			if (args.length > 0) {
				if (p.hasPermission("youtuber.record")) {
					if (args[0].equalsIgnoreCase("-r")) {
						if (args[1].equalsIgnoreCase("start")) {
							if (recording == true) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You're already livestreaming, use [/twitch record stop] to stop your livestream");
							} else {
								recording = true;
								record = "livestream";
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
					List<String> twitch = getConfig().getStringList("twitch");
					for (String msgTwitch : twitch) {
						msgTwitch.replace(" ", "");
						p.sendMessage(ChatColor.GRAY + " - " + msgTwitch);
					}
					return true;
				}
			}
		}
		if (p.hasPermission("youtuber.api")) {
			if (cmd.getName().equalsIgnoreCase("api")) {
				if (args.length > 0) {
					if (p.hasPermission("youtuber.api.gender")) {
						if (args[0].equalsIgnoreCase("gender")) {
							if (args[1].equalsIgnoreCase("boy")) {
								gender = "boy";
								p.sendMessage(ChatColor.GREEN + "Gender set to " + gender + ".");
							} else if (args[1].equalsIgnoreCase("girl")) {
								gender = "girl";
								p.sendMessage(ChatColor.GREEN + "Gender set to " + gender + ".");
							} else {
								p.sendMessage(ChatColor.RED + incMsg);
							}
						}
					} else {
						p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
					}
					if (p.hasPermission("youtuber.api.setup")) {
						if (args[0].equalsIgnoreCase("setup")) {
							if (args[1].equalsIgnoreCase("install")) {
								boolean workspace = true;
								if (installed == false) {
									p.sendMessage(ChatColor.GREEN + "Enabling workspace.");
									if (workspace == true) {
										p.sendMessage(ChatColor.GREEN + "Workspace installed successfully.");
									} else {
										p.sendMessage(ChatColor.RED + "Error occured while enabling workspace.");
									}
								} else {
									p.sendMessage(ChatColor.RED + "Youtuber's API is already installed.");
								}
							} else if (args[1].equalsIgnoreCase("reinstall")) {
								if (installed = true) {

								} else {
									p.sendMessage(ChatColor.RED + "Youtuber's API isn't installed.");
								}
							} else if (args[1].equalsIgnoreCase("uninstall")) {
								if (installed = true) {

								} else {
									p.sendMessage(ChatColor.RED + "Youtuber's API isn't installed.");
								}
							}
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
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(recording == true) {
			recording = false;
			Bukkit.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + e.getPlayer() + "has left the server without ending there " + record + ".");
		}
	}
}