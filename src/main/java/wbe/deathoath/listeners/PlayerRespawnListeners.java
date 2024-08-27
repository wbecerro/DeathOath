package wbe.deathoath.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import wbe.deathoath.DeathOath;

public class PlayerRespawnListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void showDeathMessage(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("deathoath.bypass")) {
            player.sendTitle(DeathOath.messages.youDiedTitle,
                    DeathOath.messages.youDiedSubTitle, 10, 70, 20);
        }
    }
}
