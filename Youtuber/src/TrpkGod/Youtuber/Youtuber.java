package TrpkGod.Youtuber;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.lang.time.StopWatch;
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

	public static ArrayList<String> youtube = new ArrayList<String>();
	public static ArrayList<String> twitch = new ArrayList<String>();

	public static StopWatch sw = new StopWatch();
	public static long elapsed = sw.getTime();
	
	public boolean recording = false;

	public void onEnable() {
		this.log = new YoutuberLogger(this);

		getLogger().info("Youtuber Loaded");

		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void onDisable() {
		getLogger().info("Youtuber Disabled");
		saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("youtuber")) {
			p.sendMessage(ChatColor.GRAY + "-------:[" + ChatColor.RED + "Youtuber Plugin" + ChatColor.GRAY + "]:-------");
			p.sendMessage(ChatColor.GRAY + "/youtuber ---> Shows a list of commands.");
			p.sendMessage(ChatColor.GRAY + "/yt ---> Default command.");
			if (p.hasPermission("youtuber.info.reload")) {
				p.sendMessage(ChatColor.GRAY + "/yt info reload ---> Reload the configs.");
			}
			p.sendMessage(ChatColor.GRAY + "/yt info author ---> View who made the plugin.");
			p.sendMessage(ChatColor.GRAY + "/yt list y ---> Shows a list of youtubers in the server.");
			p.sendMessage(ChatColor.GRAY + "/yt list t ---> Shows a list of twicher's in the server.");
			if (p.hasPermission("youtuber.record")) {
				p.sendMessage(ChatColor.GRAY + "/yt record y ---> Announces that you are recoding to the server.");
				p.sendMessage(ChatColor.GRAY + "/yt record t ---> Announces that you are twitching to the server.");
				p.sendMessage(ChatColor.GRAY + "/yt record end ---> Announces to the server that you have ended your Recording/LiveStream.");
			}
			if (p.hasPermission("youtuber.admin")) {
				p.sendMessage(ChatColor.GRAY + "/yt admin gui ---> Open's up a gui for admin options.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("yt")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("info")) {
					if (args[1].equalsIgnoreCase("reload")) {
						if (p.hasPermission("youtuber.info.reload")) {
							try {
								this.reloadConfig();
								this.saveConfig();
								p.sendMessage(ChatColor.GREEN + "Youtuber v" + getDescription().getVersion() + " reloaded.");
							} catch (Exception e) {
								p.sendMessage(ChatColor.RED + "Youtuber v" + getDescription().getVersion() + " could not be reloaded.");
							}
						}
					} else if (args[1].equalsIgnoreCase("author")) {
						p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "Youtuber was created by TrpkGod!");
					} else {
						p.sendMessage(ChatColor.RED + "Please use the correct format.");
					}
				}
				if (args[0].equalsIgnoreCase("list")) {
					if (args[1].equalsIgnoreCase("y")) {
						p.sendMessage(ChatColor.RED + "Youtube List:");
						p.sendMessage(ChatColor.GRAY + getConfig().getString("youtube"));
					} else if (args[1].equalsIgnoreCase("t")) {
						p.sendMessage(ChatColor.RED + "Twitch List:");
						p.sendMessage(ChatColor.GRAY + getConfig().getString("twitch"));
					} else {
						p.sendMessage(ChatColor.RED + "Please use the correct format.");
					}
				}
				if (args[0].equalsIgnoreCase("record")) {
					if (p.hasPermission("youtuber.record")) {
						if (args[1].equalsIgnoreCase("y")) {
							sw.start();
							recording = true;
							this.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just started to record, he/she may not respond.");
						} else if (args[1].equalsIgnoreCase("t")) {
							sw.start();
							recording = true;
							this.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "A TwitchTV livestream has just been put up by " + ChatColor.DARK_RED + p.getName() + ".");
						} else if (args[1].equalsIgnoreCase("end")) {
							sw.stop();
							recording = false;
							this.getServer().broadcastMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " has just ended there Recording.");
						} else if (args[1].equalsIgnoreCase("time")) {
							p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.AQUA + "You've been recording for " + elapsed + "minutes.");
						} else {
							p.sendMessage(ChatColor.RED + "Please use the correct format.");
						}
					} else {
						p.sendMessage(ChatColor.RED + "You don't have permission for this command.");
					}
				}
				if (args[0].equalsIgnoreCase("admin")) {
					if (p.hasPermission("youtuber.admin")) {
						if (args[1].equalsIgnoreCase("help")) {
							p.sendMessage(ChatColor.GRAY + "-------:[" + ChatColor.RED + "Administration" + ChatColor.GRAY + "]:-------");
							p.sendMessage(ChatColor.GRAY + "/yt admin help ---> Display's all the admin commands.");
							p.sendMessage(ChatColor.GRAY + "/yt admin request stop ---> Request user's to stop there recoridng.");
						} else if(args[1].equalsIgnoreCase("request")) {
							if(args[2].equalsIgnoreCase("stop")) {
								
								if(recording) {
								p.sendMessage(ChatColor.RED + "[Youtuber] " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " have requested you to stop you're recording.");
								}
							}
						}
					}
				}
				return false;
			}
		}
		return false;
	}
}