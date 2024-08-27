package wbe.deathoath.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

import java.io.File;
import java.io.IOException;

public class PlayerJoinListeners implements Listener {

    private DeathOath plugin = DeathOath.getInstance();

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void createFileIfNotExists(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String fileName = player.getUniqueId().toString() + ".json";
        File lifeFile = new File(plugin.getDataFolder(), "players/" + fileName);
        try {
            if(lifeFile.createNewFile()) {
                utilities.setLifes(player.getUniqueId(), DeathOath.config.initialLifes);
                player.sendMessage(DeathOath.messages.addedToData
                        .replace("%lifes%", String.valueOf(DeathOath.config.initialLifes)));
            }
        } catch (IOException e) {
            player.kickPlayer(DeathOath.messages.couldNotBeAdded);
            e.printStackTrace();
            return;
        }
    }
}
