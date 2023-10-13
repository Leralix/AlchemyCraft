package org.leralix.alchemycraft.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.leralix.alchemycraft.Drops.DropItem;
import org.leralix.alchemycraft.Drops.DropManager;

import java.util.List;

public class EntityDeathListener implements Listener {

    private final DropManager dropManager;

    public EntityDeathListener(DropManager dropManager) {
        this.dropManager = dropManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        List<DropItem> drops = dropManager.getDrops(entityType);

        for (DropItem drop : drops) {
            double rand = Math.random();
            if (rand <= drop.getDropRate()) {
                event.getDrops().add(drop.getItem().clone());
            }
        }
    }
}
