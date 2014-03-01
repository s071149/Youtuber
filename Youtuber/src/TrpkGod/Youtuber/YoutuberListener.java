package TrpkGod.Youtuber;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class YoutuberListener implements Listener {
	@EventHandler
	public void JoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("youtuber.admin")) {
			p.sendMessage("If you would like to get your server sponsored than go to http://top-list.co/ and use the bonus code \"TrpkGod\" or \"xExpired\" for a 50% off sponsor.");
		}
	}
}
