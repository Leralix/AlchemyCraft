package org.leralix.alchemycraft.interfaces.behavior;

import org.bukkit.entity.Player;

public class ConsumeBehavior implements OnConsume {
    @Override
    public void onConsume(Player player) {
        player.sendMessage("You consumed a custom item!");
    }
}
