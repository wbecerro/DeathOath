package wbe.deathoath;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import wbe.deathoath.papi.PapiExtension;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;

public final class DeathOath extends JavaPlugin {

    private final CommandListener commandListener = new CommandListener(this);

    private final EventListener eventListener = new EventListener(this);

    private PapiExtension papiExtension = new PapiExtension(this);

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papiExtension.register();
        }
        saveDefaultConfig();
        getLogger().info("DeathOath enabled correctly.");
        getCommand("deathoath").setExecutor(this.commandListener);
        getServer().getPluginManager().registerEvents(this.eventListener, this);
    }

    @Override
    public void onDisable() {
        reloadConfig();
        getLogger().info("DeathOath disabled correctly.");
    }

    public int getLifes(UUID uuid) {
        String fileName = "players/" + uuid.toString() + ".json";
        File lifeFile = new File(getDataFolder(), fileName);
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader(lifeFile)) {
            Object lifeObject = jsonParser.parse(reader);
            JSONObject lifeData = (JSONObject) lifeObject;
            return Long.valueOf((Long) lifeData.get("lifes")).intValue();
        } catch(Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public boolean removeLifes(UUID uuid, int amount) {
        String fileName = "players/" + uuid.toString() + ".json";
        int lifes = getLifes(uuid) - amount;
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", lifes);

        try(FileWriter writer = new FileWriter(new File(getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean addLifes(UUID uuid, int amount) {
        String fileName = "players/" + uuid.toString() + ".json";
        int lifes = getLifes(uuid) + amount;
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", lifes);

        try(FileWriter writer = new FileWriter(new File(getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean setLifes(UUID uuid, int amount) {
        String fileName = "players/" + uuid.toString() + ".json";
        int lifes = amount;
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", lifes);

        try(FileWriter writer = new FileWriter(new File(getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
