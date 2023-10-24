package org.leralix.alchemycraft.BrewAction;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

public abstract class CustomItemBrew extends CustomItem {

    private ItemStack ingredient;
    private ItemStack fuel;
    private int fuelSet;
    private int fuelCharge;
    private CustomItemBrew action;
    private BrewClock clock;
    private boolean perfect;
    public BrewingRecipe recipe;


    public CustomItemBrew(ItemKey _key, ItemStack _itemStack) {
        super(_key, _itemStack);
    }

    public abstract void brew(BrewerInventory inventory);

    public abstract BrewingRecipe getBrewRecipe();


}