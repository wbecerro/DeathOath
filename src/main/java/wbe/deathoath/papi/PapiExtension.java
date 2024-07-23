package wbe.deathoath.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import wbe.deathoath.DeathOath;

public class PapiExtension extends PlaceholderExpansion {
    private DeathOath plugin;

    public PapiExtension(DeathOath plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "wbe";
    }

    @Override
    public String getIdentifier() {
        return "DeathOath";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("lifes")) {
            return String.valueOf(plugin.getLifes(player.getUniqueId()));
        }

        return null;
    }
}
