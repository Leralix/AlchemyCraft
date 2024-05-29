package org.leralix.alchemycraft.interfaces;

import org.bukkit.inventory.Recipe;

import java.util.Collections;
import java.util.List;

public interface Craftable {

    default List<Recipe> getRecipes(){
        return Collections.emptyList();
    }


}
