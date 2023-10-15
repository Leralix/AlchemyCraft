package org.leralix.alchemycraft.Items;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Drops.DropItem;

import java.util.Collections;
import java.util.List;

public abstract class CustomItem {

    public ItemKey key;
    public ItemStack itemStack;

    public CustomItem(ItemKey _key, ItemStack _itemStack){
        key = _key;
        itemStack = _itemStack;
    }

    public ItemStack getItemStack(){
        return itemStack;
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
        meta.setDisplayName(ChatColor.WHITE + itemData.name());

        item.setItemMeta(meta);
        item.setDurability((short) itemData.durability());

        return item;
    }
}
