package TrpkGod.Youtuber;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {

	private SettingsManager() {

	}

	private static SettingsManager instance = new SettingsManager();

	public static SettingsManager getInstance() {
		return instance;
	}

	private Plugin p;
	private FileConfiguration config;
	private File cfile;

	public void setup(Plugin p) {
		this.p = p;
		
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		p.getConfig().options().copyDefaults(true);
		p.saveConfig();

		/*cfile = new File(p.getDataFolder(), "config.yml");

		if (!cfile.exists()) {
			try {
				cfile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(cfile); */
	}

	public void addYoutuber(String p, String link) {
		setYoutuber(p, link);
	}
	
	public void addLivestreamer(String p, String link) {
		setLivestreamer(p, link);
	}
	
	public void setLivestreamer(String p, String link) {
		config.set("config." + p.toLowerCase(), link);
		save();
	}
	
	public void setYoutuber(String p, String link) {
		config.set("config." + p.toLowerCase(), link);
		save();
	}

	public ArrayList<String> getValues() {
		Map<String, Object> map = config.getValues(true);
		ArrayList<String> lines = new ArrayList<String>();

		for (Entry<String, Object> e : map.entrySet()) {
			lines.add(e.getValue() + " " + e.getKey());
		}

		return lines;
	}

	private void save() {
		try {
			config.save("config.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getVersion() {
		return "1.8.9";
	}

	public Plugin getPlugin() {
		return p;
	}

}
