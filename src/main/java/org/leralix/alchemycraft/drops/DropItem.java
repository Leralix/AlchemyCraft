package org.leralix.alchemycraft.drops;

import org.bukkit.inventory.ItemStack;

public class DropItem {
    private final ItemStack item;
    private final double dropRate;

    public DropItem(ItemStack item, double dropRate) {
        this.item = item;
        this.dropRate = dropRate;
    }

    public double getDropRate(){
        return dropRate;
    }

    public ItemStack getItem(){
        return item;
    }

}