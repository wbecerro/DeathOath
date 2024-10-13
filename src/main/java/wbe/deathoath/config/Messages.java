package wbe.deathoath.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private FileConfiguration config;

    public String notEnoughArgs;
    public String noPermission;
    public String lifeAdded;
    public String lifeAddedPlayer;
    public String lifeAddArgs;
    public String lifeRemoved;
    public String lifeRemovedPlayer;
    public String lifeRemoveArgs;
    public String lifeSet;
    public String lifeSetPlayer;
    public String lifeSetArgs;
    public String lifeMessage;
    public String lifeMessagePlayer;
    public String transferArgs;
    public String notEnoughLifes;
    public String transferMessage;
    public String transferPlayer;
    public String cannotTransferSelf;
    public String devilPactArgs;
    public String cannotHaveLessContainers;
    public String cannotHaveMoreContainers;
    public String lostContainers;
    public String addedContainers;
    public String youDiedTitle;
    public String youDiedSubTitle;
    public String addedToData;
    public String couldNotBeAdded;
    public String outOfLifes;
    public String cannotBeNegative;
    public String bypass;
    public String itemGiven;
    public String itemGivenPlayer;
    public String reload;
    public String moneyLoss;
    public List<String> help = new ArrayList<>();

    public Messages(FileConfiguration config) {
        this.config = config;

        notEnoughArgs = config.getString("Messages.notEnoughArgs").replace("&", "§");
        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        lifeAdded = config.getString("Messages.lifeAdded").replace("&", "§");
        lifeAddedPlayer = config.getString("Messages.lifeAddedPlayer").replace("&", "§");
        lifeAddArgs = config.getString("Messages.lifeAddArgs").replace("&", "§");
        lifeRemoved = config.getString("Messages.lifeRemoved").replace("&", "§");
        lifeRemovedPlayer = config.getString("Messages.lifeRemovedPlayer").replace("&", "§");
        lifeRemoveArgs = config.getString("Messages.lifeRemoveArgs").replace("&", "§");
        lifeSet = config.getString("Messages.lifeSet").replace("&", "§");
        lifeSetPlayer = config.getString("Messages.lifeSetPlayer").replace("&", "§");
        lifeSetArgs = config.getString("Messages.lifeSetArgs").replace("&", "§");
        lifeMessage = config.getString("Messages.lifeMessage").replace("&", "§");
        lifeMessagePlayer = config.getString("Messages.lifeMessagePlayer").replace("&", "§");
        transferArgs = config.getString("Messages.transferArgs").replace("&", "§");
        notEnoughLifes = config.getString("Messages.notEnoughLifes").replace("&", "§");
        transferMessage = config.getString("Messages.transferMessage").replace("&", "§");
        transferPlayer = config.getString("Messages.transferPlayer").replace("&", "§");
        cannotTransferSelf = config.getString("Messages.cannotTransferSelf").replace("&", "§");
        devilPactArgs = config.getString("Messages.devilPactArgs").replace("&", "§");
        lostContainers = config.getString("Messages.lostContainers").replace("&", "§");
        cannotHaveMoreContainers = config.getString("Messages.cannotHaveMoreContainers").replace("&", "§");
        cannotHaveLessContainers = config.getString("Messages.cannotHaveLessContainers").replace("&", "§");
        addedContainers = config.getString("Messages.addedContainers").replace("&", "§");
        youDiedTitle = config.getString("Messages.youDiedTitle").replace("&", "§");
        youDiedSubTitle = config.getString("Messages.youDiedSubTitle").replace("&", "§");
        addedToData = config.getString("Messages.addedToData").replace("&", "§");
        couldNotBeAdded = config.getString("Messages.couldNotBeAdded").replace("&", "§");
        outOfLifes = config.getString("Messages.outOfLifes").replace("&", "§");
        cannotBeNegative = config.getString("Messages.cannotBeNegative").replace("&", "§");
        bypass = config.getString("Messages.bypass").replace("&", "§");
        itemGiven = config.getString("Messages.itemGiven").replace("&", "§");
        itemGivenPlayer = config.getString("Messages.itemGivenPlayer").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
        moneyLoss = config.getString("Messages.moneyLoss").replace("&", "§");
        help = config.getStringList("Messages.help");
    }
}
