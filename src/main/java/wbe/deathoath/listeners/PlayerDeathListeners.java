package wbe.deathoath.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import wbe.deathoath.DeathOath;
import wbe.deathoath.events.PlayerLoseLifeEvent;
import wbe.deathoath.util.Utilities;

public class PlayerDeathListeners implements Listener {

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void manageDeath(PlayerDeathEvent event) {
        DeathOath.getInstance().getServer().getPluginManager().callEvent(new PlayerLoseLifeEvent(event.getEntity()));
    }
}
