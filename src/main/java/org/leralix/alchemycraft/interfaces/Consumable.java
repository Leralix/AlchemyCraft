package org.leralix.alchemycraft.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.leralix.alchemycraft.interfaces.behavior.Behavior;

public interface Consumable extends Behavior {
    void onConsume(Player player, PlayerItemConsumeEvent event);
}
