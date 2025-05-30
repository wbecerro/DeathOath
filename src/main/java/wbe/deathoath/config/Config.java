package wbe.deathoath.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private FileConfiguration config;

    public int initialLifes;
    public int unbanLifes;
    public int hoursOfTimeout;
    public boolean enableMoneyLoss;
    public float moneyLossPercent;
    public int minContainers;
    public int maxContainers;

    public Material lifeMaterial;
    public String lifeName;
    public List<String> lifeLore = new ArrayList<>();
    public int baseItemAmount;

    public Config(FileConfiguration config) {
        this.config = config;

        initialLifes = config.getInt("Config.initialLifes");
        unbanLifes = config.getInt("Config.unbanLifes");
        hoursOfTimeout = config.getInt("Config.hoursOfTimeout");
        enableMoneyLoss = config.getBoolean("Config.enableMoneyLoss");
        moneyLossPercent = (float) config.getDouble("Config.moneyLossPercent");
        minContainers = config.getInt("Config.minContainers");
        maxContainers = config.getInt("Config.maxContainers");

        lifeMaterial = Material.valueOf(config.getString("Items.LifeItem.material"));
        lifeName = config.getString("Items.LifeItem.name").replace("&", "§");
        lifeLore = config.getStringList("Items.LifeItem.lore");
        baseItemAmount = config.getInt("Items.LifeItem.baseItemAmount");
    }
}
