package wbe.deathoath.items;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import wbe.deathoath.DeathOath;

import java.util.ArrayList;

public class Life extends ItemStack {

    public Life(int amount) {
        super(DeathOath.config.lifeMaterial);

        ItemMeta meta;
        if(hasItemMeta()) {
            meta = getItemMeta();
        } else {
            meta = Bukkit.getItemFactory().getItemMeta(DeathOath.config.lifeMaterial);
        }

        meta.setDisplayName(DeathOath.config.lifeName);

        String plural = "";
        if(amount > 1) {
            plural = "s";
        }

        ArrayList<String> lore = new ArrayList<>();
        for(String line : DeathOath.config.lifeLore) {
            lore.add(line.replace("&", "§")
                    .replace("%lifes%", String.valueOf(amount))
                    .replace("%s%", plural));
        }

        meta.setLore(lore);

        NamespacedKey lifeKey = new NamespacedKey(DeathOath.getInstance(), "LifesAmount");
        meta.getPersistentDataContainer().set(lifeKey, PersistentDataType.INTEGER, amount);

        setItemMeta(meta);
    }
}
