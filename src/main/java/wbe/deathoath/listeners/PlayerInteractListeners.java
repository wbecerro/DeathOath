package wbe.deathoath.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import wbe.deathoath.DeathOath;
import wbe.deathoath.util.Utilities;

public class PlayerInteractListeners implements Listener {

    private Utilities utilities = new Utilities();

    @EventHandler(priority = EventPriority.NORMAL)
    public void useItemOnInteract(PlayerInteractEvent event) {
        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }

        ItemStack item = event.getItem();
        if(item == null) {
            return;
        }

        if(item.getType().equals(Material.AIR)) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if(meta == null) {
            return;
        }

        NamespacedKey lifeKey = new NamespacedKey(DeathOath.getInstance(), "LifesAmount");
        if(!meta.getPersistentDataContainer().has(lifeKey)) {
            return;
        }

        Player player = event.getPlayer();
        int amount = meta.getPersistentDataContainer().get(lifeKey, PersistentDataType.INTEGER);
        utilities.addLifes(player.getUniqueId(), amount);
        String plural = "";
        if(amount > 1) {
            plural = "s";
        }

        player.sendMessage(DeathOath.messages.lifeAddedPlayer
                .replace("%lifes%", String.valueOf(amount))
                .replace("%s%", plural));

        amount = item.getAmount();
        item.setAmount(amount - 1);

        event.setCancelled(true);
    }
}
