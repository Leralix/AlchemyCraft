package org.leralix.alchemycraft.Items;

import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.interfaces.behavior.Behavior;

import java.util.HashMap;
import java.util.Map;

public class CustomItem {


    private final String key;
    private final ItemStack itemStack;
    private final Map<Class<? extends Behavior>, Behavior> behaviors;


    public CustomItem(String _key, ItemStack _itemStack){
        key = _key;
        itemStack = _itemStack;
        behaviors = new HashMap<>();
    }

    public ItemStack getItemStack(){
        return itemStack;
    }
    public String getKey(){
        return key;
    }

    public <T extends Behavior> void addBehavior(Class<T> behaviorClass, T behavior) {
        behaviors.put(behaviorClass, behavior);
    }

    public <T extends Behavior> T getBehavior(Class<T> behaviorClass) {
        return behaviorClass.cast(behaviors.get(behaviorClass));
    }


    public Map<Class<? extends Behavior>, Behavior> getBehaviors() {
        return behaviors;
    }
}
