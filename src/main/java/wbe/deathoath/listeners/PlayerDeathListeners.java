package wbe.deathoath.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

import java.time.Duration;

public class PlayerDeathListeners implements Listener {

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void manageDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(!player.hasPermission("deathoath.bypass")) {
            utilities.removeLifes(player.getUniqueId(), 1);
            int lifes = utilities.getLifes(player.getUniqueId());

            if(lifes < 1) {
                player.ban(DeathOath.messages.outOfLifes, Duration.ofHours(DeathOath.config.hoursOfTimeout),
                        "WbeIndustries");
                utilities.setLifes(player.getUniqueId(), DeathOath.config.unbanLifes);
            }
        } else {
            player.sendMessage(DeathOath.messages.bypass);
        }
    }
}
