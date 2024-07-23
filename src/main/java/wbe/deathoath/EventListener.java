package wbe.deathoath;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class EventListener implements Listener {

    private DeathOath plugin;

    private FileConfiguration config;

    public EventListener(DeathOath plugin) {
        this.plugin = plugin;
        this.config = this.plugin.getConfig();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void showDeathMessage(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("deathoath.bypass")) {
            player.sendTitle(config.getString("Messages.youDiedTitle").replace("&", "§"),
                    config.getString("Messages.youDiedSubTitle").replace("&", "§"),
                    10, 70, 20);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void manageDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(!player.hasPermission("deathoath.bypass")) {
            plugin.removeLifes(player.getUniqueId(), 1);
            int lifes = plugin.getLifes(player.getUniqueId());

            if(lifes < 1) {
                player.ban(config.getString("Messages.outOfLifes").replace("&", "§"),
                        Duration.ofHours(config.getInt("Variables.hoursOfTimeout")),
                        "WbeIndustries");
                plugin.setLifes(player.getUniqueId(), config.getInt("Variables.unbanLifes"));
            }
        } else {
            player.sendMessage(config.getString("Messages.bypass").replace("&", "§"));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void createFileIfNotExists(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String fileName = player.getUniqueId().toString() + ".json";
        File lifeFile = new File(plugin.getDataFolder(), "players/" + fileName);
        try {
            if(lifeFile.createNewFile()) {
                plugin.setLifes(player.getUniqueId(), config.getInt("Variables.initialLifes"));
                player.sendMessage(config.getString("Messages.addedToData")
                        .replace("%lifes%", String.valueOf(config.getInt("Variables.initialLifes")))
                        .replace("&", "§"));
            }
        } catch (IOException e) {
            player.kickPlayer(config.getString("Messages.couldNotBeAdded").replace("&", "§"));
            e.printStackTrace();
            return;
        }
    }
}
