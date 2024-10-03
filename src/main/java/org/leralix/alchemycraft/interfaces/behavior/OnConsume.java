package org.leralix.alchemycraft.interfaces.behavior;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

public interface OnConsume extends Behavior {
    void onConsume(Player player, EquipmentSlot hand);
}


