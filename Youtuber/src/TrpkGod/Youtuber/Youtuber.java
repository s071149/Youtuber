package TrpkGod.Youtuber;

import org.bukkit.plugin.java.JavaPlugin;

public class Youtuber extends JavaPlugin {

	public void onEnable() {
		SettingsManager.getInstance().setup(this);

		CommandManager c = new CommandManager();
		getCommand("youtuber").setExecutor(c);
		getCommand("youtube").setExecutor(c);
		getCommand("twitch").setExecutor(c);
	}

}