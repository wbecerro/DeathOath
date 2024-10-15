package wbe.deathoath.util;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
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
        lifesData.put("containers", getContainers(uuid));

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
        lifesData.put("containers", getContainers(uuid));

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
        lifesData.put("containers", getContainers(uuid));

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean hasEnoughLifes(int lifes, UUID uuid) {
        int playerLifes = getLifes(uuid);
        if(playerLifes - lifes >= 1) {
            return true;
        }

        return false;
    }

    public boolean transferLifes(UUID player, UUID otherPlayer, int lifes) {
        boolean removed = removeLifes(player, lifes);
        boolean added = addLifes(otherPlayer, lifes);
        if(!removed || !added) {
            return false;
        }

        return true;
    }

    public boolean setContainers(UUID player, int amount) {
        String fileName = "players/" + player.toString() + ".json";
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", getLifes(player));
        lifesData.put("containers", amount);

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean addContainers(UUID player, int amount) {
        String fileName = "players/" + player.toString() + ".json";
        int containers = getContainers(player) + amount;
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", getLifes(player));
        lifesData.put("containers", containers);

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public int getContainers(UUID uuid) {
        String fileName = "players/" + uuid.toString() + ".json";
        File lifeFile = new File(plugin.getDataFolder(), fileName);
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader(lifeFile)) {
            Object lifeObject = jsonParser.parse(reader);
            JSONObject lifeData = (JSONObject) lifeObject;
            return Long.valueOf((Long) lifeData.get("containers")).intValue();
        } catch(Exception exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public boolean removeContainers(UUID uuid, int amount) {
        String fileName = "players/" + uuid.toString() + ".json";
        int containers = getContainers(uuid) - amount;
        JSONObject lifesData = new JSONObject();
        lifesData.put("lifes", getLifes(uuid));
        lifesData.put("containers", containers);

        try(FileWriter writer = new FileWriter(new File(plugin.getDataFolder(), fileName), false)) {
            writer.write(lifesData.toJSONString());
            writer.flush();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private void removeHealth(UUID uuid, int amount) {
        Player player = Bukkit.getServer().getPlayer(uuid);
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth - amount * 2);
        removeContainers(uuid, amount);
    }

    public boolean sacrifice(UUID player, int amount) {
        int containers = getContainers(player);
        if(containers - amount < DeathOath.config.minContainers) {
            Bukkit.getServer().getPlayer(player).sendMessage(DeathOath.messages.cannotHaveLessContainers
                    .replace("%containers%", String.valueOf(DeathOath.config.minContainers * -1)));
            return false;
        }

        removeHealth(player, amount);
        addLifes(player, amount);

        Bukkit.getPlayer(player).sendMessage(DeathOath.messages.lostContainers
                .replace("%containers%", String.valueOf(amount))
                .replace("%s%", amount > 1 ? "s" : "")
                .replace("%lifes%", String.valueOf(amount)));

        return true;
    }

    private void addHealth(UUID uuid, int amount) {
        Player player = Bukkit.getServer().getPlayer(uuid);
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth + amount * 2);
        addContainers(uuid, amount);
    }

    public boolean regain(UUID player, int amount) {
        int containers = getContainers(player);
        if(containers + amount > DeathOath.config.maxContainers) {
            Bukkit.getServer().getPlayer(player).sendMessage(DeathOath.messages.cannotHaveMoreContainers
                    .replace("%containers%", "+" + DeathOath.config.maxContainers));
            return false;
        }

        if(getLifes(player) <= amount) {
            Bukkit.getServer().getPlayer(player).sendMessage(DeathOath.messages.notEnoughLifes);
            return false;
        }

        addHealth(player, amount);
        removeLifes(player, amount);

        Bukkit.getPlayer(player).sendMessage(DeathOath.messages.addedContainers
                .replace("%containers%", String.valueOf(amount))
                .replace("%s%", amount > 1 ? "s" : "")
                .replace("%lifes%", String.valueOf(amount)));

        return true;
    }
}
