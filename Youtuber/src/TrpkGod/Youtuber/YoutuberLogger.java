package TrpkGod.Youtuber;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;

public class YoutuberLogger {
	  private Youtuber plugin;
	  private Logger log;

	  public YoutuberLogger(Youtuber plugin)
	  {
	    this.plugin = plugin;
	    this.log = Logger.getLogger("Minecraft");
	  }

	private String getFormattedMessage(String message) {
	    PluginDescriptionFile pdf = this.plugin.getDescription();

	    return "[" + pdf.getName() + " Version: " + pdf.getVersion() + "];" + message;
	  }

	  public void info(String message) {
	    this.log.info(getFormattedMessage(message));
	  }
}