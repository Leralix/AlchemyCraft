package org.leralix.alchemycraft.Items;

import org.bukkit.inventory.ItemStack;

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
}
