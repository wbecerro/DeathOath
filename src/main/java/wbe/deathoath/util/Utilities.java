package wbe.deathoath.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import wbe.deathoath.DeathOath;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

public class Utilities {

    DeathOath plugin = DeathOath.getInstance();

    public Utilities() {

    }

    public int getLifes(UUID uuid) {
        String fileName = "players/" + uuid.toString() + ".json";
        File lifeFile = new File(plugin.getDataFolder(), fileName);
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

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
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

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
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

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
