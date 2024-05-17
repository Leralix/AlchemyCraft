package org.leralix.alchemycraft.Consumable;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface Consumable {
    void onConsume(Player player, PlayerItemConsumeEvent event);
}
