package org.leralix.alchemycraft.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Drops.DropItem;

import java.util.Collections;
import java.util.List;

public abstract class CustomItem {

    public ItemKey key;
    public CustomItem(ItemKey _key){
        key = _key;
    }

    public List<Recipe> getRecipes(){
        return Collections.emptyList();
    }
    public List<DropItem> getDrops() {
        return Collections.emptyList();
    }

    public ItemStack makeItemStack() {
        ItemData itemData = this.getClass().getAnnotation(ItemData.class);

        ItemStack item = new ItemStack(itemData.base());
        ItemMeta meta = item.getItemMeta();

        meta.setCustomModelData(itemData.model_data());
        meta.setDisplayName(itemData.name());

        item.setItemMeta(meta);
        item.setDurability((short) itemData.durability());

        return item;
    }


}
