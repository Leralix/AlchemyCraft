package org.leralix.alchemycraft.Drops;

import org.bukkit.entity.EntityType;

import java.util.*;

public class DropManager {
    private static Map<EntityType, List<DropItem>> dropItemsMap = new HashMap<>();

    public static void registerDrop(EntityType entityType, DropItem dropItem) {
        dropItemsMap.computeIfAbsent(entityType, k -> new ArrayList<>()).add(dropItem);
    }

    public static List<DropItem> getDrops(EntityType entityType) {
        return dropItemsMap.getOrDefault(entityType, Collections.emptyList());
    }
}