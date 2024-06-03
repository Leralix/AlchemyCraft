package org.leralix.alchemycraft.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.leralix.alchemycraft.drops.DropItem;
import org.leralix.alchemycraft.Storage.DropManager;

public class EntityDeathListener implements Listener {


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        for (DropItem drop : DropManager.getDrops(entityType)) {
            double rand = Math.random();
            if (rand <= drop.getDropRate()) {
                event.getDrops().add(drop.getItem().clone());
            }
        }
    }
}
