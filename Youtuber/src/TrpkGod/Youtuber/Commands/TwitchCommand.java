package TrpkGod.Youtuber.Commands;

import org.bukkit.command.CommandSender;

public abstract class TwitchCommand {
	
	private String name, desc, args;
	
	public TwitchCommand(String name, String desc, String args) {
		this.name = name;
		this.desc = desc;
		this.args = args;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getArgs() {
		return args;
	}
	
	public abstract void run(CommandSender sender, String[] args);
	
}
