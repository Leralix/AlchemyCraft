package org.leralix.alchemycraft.Items;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Drops.DropItem;

import javax.sound.midi.Receiver;
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


}
