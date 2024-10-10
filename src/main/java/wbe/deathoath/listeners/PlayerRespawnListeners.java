package wbe.deathoath.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

import java.time.Duration;

public class PlayerRespawnListeners implements Listener {

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void showDeathMessage(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("deathoath.bypass")) {
            player.sendTitle(DeathOath.messages.youDiedTitle,
                    DeathOath.messages.youDiedSubTitle, 10, 70, 20);

            int lifes = utilities.getLifes(player.getUniqueId());

            if(lifes < 1) {
                player.ban(DeathOath.messages.outOfLifes, Duration.ofHours(DeathOath.config.hoursOfTimeout),
                        "WbeIndustries");
                utilities.setLifes(player.getUniqueId(), DeathOath.config.unbanLifes);
            }
        }
    }
}
