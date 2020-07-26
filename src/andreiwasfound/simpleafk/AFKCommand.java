package andreiwasfound.simpleafk;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {

    private Main plugin;
    public AFKCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console cannot use this command");
            return true;
        }
        Player player = (Player) sender;
        if (plugin.hasAFKPlayers()) {
            if (plugin.getAFKPlayers().contains(player)) {
                plugin.removeAFKPlayer(player);
                player.sendMessage(ChatColor.RED + "You are no longer AFK");
                return true;
            }
        }
        plugin.addAFKPlayer(player);
        player.sendMessage(ChatColor.GREEN + "You are now AFK");
        return true;
    }
}
