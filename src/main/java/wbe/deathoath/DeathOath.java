package wbe.deathoath;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.deathoath.commads.CommandListener;
import wbe.deathoath.config.Config;
import wbe.deathoath.config.Messages;
import wbe.deathoath.listeners.EventListeners;
import wbe.deathoath.papi.PapiExtension;

import java.io.File;

public final class DeathOath extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    private EventListeners eventListeners;

    private PapiExtension papiExtension;

    public static Economy economy;

    public static Config config;

    public static Messages messages;

    @Override
    public void onEnable() {
        new File(this.getDataFolder(), "players").mkdir();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papiExtension = new PapiExtension();
            papiExtension.register();
        }
        saveDefaultConfig();
        getLogger().info("DeathOath enabled correctly.");
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("deathoath").setExecutor(this.commandListener);
        eventListeners = new EventListeners();
        this.eventListeners.initializeListeners();
    }

    @Override
    public void onDisable() {
        reloadConfig();
        getLogger().info("DeathOath disabled correctly.");
    }

    public static DeathOath getInstance() {
        return getPlugin(DeathOath.class);
    }

    public void reloadConfiguration() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        configuration = getConfig();
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> registeredServiceProvider = Bukkit.getServicesManager()
                    .getRegistration(Economy.class);
            if(registeredServiceProvider != null) {
                economy = registeredServiceProvider.getProvider();
            }
        }
        messages = new Messages(configuration);
        config = new Config(configuration);
    }
}
