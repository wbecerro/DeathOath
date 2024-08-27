package wbe.deathoath.listeners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

public class EventListeners implements Listener {

    private DeathOath plugin = DeathOath.getInstance();

    private Utilities utilities;

    public EventListeners() {
        this.utilities = new Utilities();
    }

    public void initializeListeners() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDeathListeners(), plugin);
        pluginManager.registerEvents(new PlayerJoinListeners(), plugin);
        pluginManager.registerEvents(new PlayerRespawnListeners(), plugin);
        pluginManager.registerEvents(new PlayerInteractListeners(), plugin);
    }
}
