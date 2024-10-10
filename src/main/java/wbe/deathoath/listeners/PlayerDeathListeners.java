package wbe.deathoath.listeners;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

import java.time.Duration;

public class PlayerDeathListeners implements Listener {

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void manageDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        double money = DeathOath.economy.getBalance(player) * DeathOath.config.moneyLossPercent;
        EconomyResponse response = DeathOath.economy.withdrawPlayer(player, money);
        if(response.transactionSuccess()) {
            player.sendMessage(DeathOath.messages.moneyLoss
                    .replace("%percent%", String.valueOf((int) (DeathOath.config.moneyLossPercent * 100)))
                    .replace("%money%", DeathOath.economy.format(money)));
        }

        if(!player.hasPermission("deathoath.bypass")) {
            utilities.removeLifes(player.getUniqueId(), 1);
        } else {
            player.sendMessage(DeathOath.messages.bypass);
        }
    }
}
