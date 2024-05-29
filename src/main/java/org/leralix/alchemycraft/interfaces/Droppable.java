package org.leralix.alchemycraft.interfaces;

import org.leralix.alchemycraft.drops.DropItem;

import java.util.Collections;
import java.util.List;

public interface Droppable {
    default List<DropItem> getDrops() {
        return Collections.emptyList();
    }

}
