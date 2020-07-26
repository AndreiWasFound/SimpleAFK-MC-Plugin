package andreiwasfound.simpleafk;

import andreiwasfound.simpleafk.utilities.MetricsLite;
import andreiwasfound.simpleafk.utilities.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements Listener {

    Map<Player, GameMode> PlayerGamemode = new HashMap<Player, GameMode>();

    private List<Player> afkplayers = new ArrayList<Player>();

    @Override
    public void onEnable() {
        printToConsole("UpdateChecker is trying to register");
        updateChecker();
        printToConsole("UpdateChecker has been registered successfully");
        printToConsole("Commands are trying to register");
        registerCommands();
        printToConsole("Commands have been registered successfully");
        printToConsole("Events are trying to register");
        registerEvents();
        printToConsole("Events have been registered successfully");
        printToConsole("bStats is trying to register");
        int pluginId = 8266;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        printToConsole("bStats has been registered successfully");
    }

    @Override
    public void onDisable() {

    }

    public void addAFKPlayer(Player player) {
        PlayerGamemode.put(player, player.getGameMode());
        player.setGameMode(GameMode.SPECTATOR);
        afkplayers.add(player);
    }

    public void removeAFKPlayer(Player player) {
        player.setGameMode(PlayerGamemode.get(player));
        afkplayers.remove(player);
    }

    public List<Player> getAFKPlayers() {
        return afkplayers;
    }

    public boolean hasAFKPlayers() {
        if (afkplayers.isEmpty())
            return false;
        return true;
    }

    public void registerCommands() {
        getCommand("afk").setExecutor(new AFKCommand(this));
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Events(this), this);
    }

    public void updateChecker() {
        new UpdateChecker(this, 81963).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                printToConsole("SimpleAFK is up to date!");
            } else {
                printToConsole("SimpleAFK is outdated!");
                printToConsole("Newest version: " + version);
                printToConsole("Your version: " + configVersion);
                printToConsole("Please Update Here: " + configWebsite);
            }
        });
    }

    PluginDescriptionFile config = this.getDescription();
    String configVersion = config.getVersion();
    String configWebsite = config.getWebsite();

    public void printToConsole(String msg) {
        this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "SimpleAFK" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + " " + msg);
    }

}
