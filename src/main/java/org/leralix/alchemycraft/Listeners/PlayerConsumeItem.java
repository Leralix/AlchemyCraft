package org.leralix.alchemycraft.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.interfaces.behavior.OnConsume;


public class PlayerConsumeItem implements Listener {

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        handleItemConsumption(player, item, event);
    }

    public void handleItemConsumption(Player player, ItemStack item, PlayerItemConsumeEvent event) {


        CustomItem customItem = ItemManager.get(item);

        if (customItem == null)
            return;

        OnConsume consumeBehavior = customItem.getBehavior(OnConsume.class);
        if (consumeBehavior == null)
            return;
        event.setCancelled(true);
        consumeBehavior.onConsume(player);
    }

}
