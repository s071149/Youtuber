package TrpkGod.Youtuber;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import TrpkGod.Youtuber.Commands.TwitchCommand;
import TrpkGod.Youtuber.Commands.YoutubeCommand;
import TrpkGod.Youtuber.Commands.YoutuberCommand;
import TrpkGod.Youtuber.Commands.Twitch.AddLiveStreamer;
import TrpkGod.Youtuber.Commands.Twitch.LiveStream;
import TrpkGod.Youtuber.Commands.Twitch.TwitchList;
import TrpkGod.Youtuber.Commands.Youtube.AddYoutuber;
import TrpkGod.Youtuber.Commands.Youtube.Record;
import TrpkGod.Youtuber.Commands.Youtube.YoutubeList;
import TrpkGod.Youtuber.Commands.Youtuber.Author;
import TrpkGod.Youtuber.Commands.Youtuber.Reload;

public class CommandManager implements CommandExecutor {

	private ArrayList<YoutuberCommand> youtuber = new ArrayList<YoutuberCommand>();
	private ArrayList<YoutubeCommand> youtube = new ArrayList<YoutubeCommand>();
	private ArrayList<TwitchCommand> twitch = new ArrayList<TwitchCommand>();

	public CommandManager() {
		youtuber.add(new Reload());
		youtuber.add(new Author());
		youtube.add(new Record());
		youtube.add(new YoutubeList());
		youtube.add(new AddYoutuber());
		twitch.add(new LiveStream());
		twitch.add(new TwitchList());
		twitch.add(new AddLiveStreamer());
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("youtuber")) {
			if (args.length == 0) {
				Player p = (Player) sender;
				if (p instanceof Player) {
					p.sendMessage(ChatColor.DARK_GRAY + "-------:[" + ChatColor.DARK_RED + "Youtuber" + ChatColor.DARK_GRAY + "]:-------");
				}

				for (YoutuberCommand c : youtuber) {
					p.sendMessage("/youtuber " + c.getName() + " " + c.getArgs() + " - " + c.getDesc());
				}
				for (YoutubeCommand c : youtube) {
					p.sendMessage("/youtube " + c.getName() + " " + c.getArgs() + " - " + c.getDesc());
				}
				for(TwitchCommand c : twitch) {
					p.sendMessage("/twitch " + c.getName() + " " + c.getArgs() + " - " + c.getDesc());
				}
				return true;
			}
			ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
			a.remove(0);

			for (YoutuberCommand c : youtuber) {
				if (c.getName().equalsIgnoreCase(args[0])) {
					try {
						c.run(sender, a.toArray(new String[a.size()]));
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + "An error has occurred.");
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("youtube")) {
			if (args.length == 0) {
				Player p = (Player) sender;
				if (p instanceof Player) {
					p.sendMessage(ChatColor.DARK_GRAY + "-------:[" + ChatColor.DARK_RED + "Youtuber" + ChatColor.DARK_GRAY + "]:-------");
				}

				for (YoutubeCommand c : youtube) {
					p.sendMessage("/youtube " + c.getName() + " " + c.getArgs() + " - " + c.getDesc());
				}
				return true;
			}
			ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
			a.remove(0);

			for (YoutubeCommand c : youtube) {
				if (c.getName().equalsIgnoreCase(args[0])) {
					try {
						c.run(sender, a.toArray(new String[a.size()]));
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + "An error has occurred.");
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("twitch")) {
			if(args.length == 0) {
				Player p = (Player) sender;
				if(p instanceof Player) {
					p.sendMessage(ChatColor.DARK_GRAY + "-------:[" + ChatColor.DARK_RED + "Youtuber" + ChatColor.DARK_GRAY + "]:-------");
				}
				
				for(TwitchCommand c : twitch) {
					p.sendMessage("/twitch " + c.getName() + " " + c.getArgs() + " - " + c.getDesc());
				}
				return true;
			}
			ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
			a.remove(0);
			
			for(TwitchCommand c : twitch) {
				if(c.getName().equalsIgnoreCase(args[0])) {
					try {
						c.run(sender, a.toArray(new String[a.size()]));
					} catch(Exception e) {
						sender.sendMessage(ChatColor.RED + "An error has occured.");
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		return false;
	}

}
