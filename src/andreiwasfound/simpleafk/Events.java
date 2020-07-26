package andreiwasfound.simpleafk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class Events implements Listener {

    private Main plugin;
    public Events(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (plugin.getAFKPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAnimation(PlayerAnimationEvent e) {
        if (plugin.getAFKPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerInteractEvent e) {
        if (plugin.getAFKPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerInteractEntityEvent e) {
        if (plugin.getAFKPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerInteractAtEntityEvent e) {
        if (plugin.getAFKPlayers().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
