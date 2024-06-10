package org.leralix.alchemycraft.brewing;

import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.interfaces.BrewAction;
import org.leralix.alchemycraft.brewing.BrewClock;

public class BrewingRecipe {

    private final ItemStack ingredient;
    private final ItemStack fuel;

    private int fuelSet;
    private int fuelCharge;

    private BrewAction action;
    private BrewClock clock;

    private boolean perfect;

    public BrewingRecipe(ItemStack ingredient, ItemStack fuel, BrewAction action, boolean perfect, int fuelSet, int fuelCharge) {
        this.ingredient = ingredient;
        this.fuel = (fuel == null ? new ItemStack(Material.AIR) : fuel);
        this.setFuelSet(fuelSet);
        this.setFuelCharge(fuelCharge);
        this.action = action;
        this.perfect = perfect;
    }

    public BrewingRecipe(Material ingredient, BrewAction action) {
        this(new ItemStack(ingredient), null, action, true, 40, 0);
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public BrewAction getAction() {
        return action;
    }

    public void setAction(BrewAction action) {
        this.action = action;
    }

    public BrewClock getClock() {
        return clock;
    }

    public void setClock(BrewClock clock) {
        this.clock = clock;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }

    public static BrewingRecipe getRecipe(BrewerInventory inventory) {

        for (BrewingRecipe recipe: ItemManager.getBrewItems()) {
            if (inventory.getFuel() == null) {
                if(recipe.getFuel() != null) {
                    continue;
                }

                if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType()) {
                    return recipe;
                }
                if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient())) {
                    return recipe;
                }
            } else {

                if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType() &&
                        inventory.getFuel().getType() == recipe.getFuel().getType()) {

                    return recipe;
                }
                if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient()) &&
                        inventory.getFuel().isSimilar(recipe.getFuel())) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public void startBrewing(BrewerInventory inventory) {

        if(BrewRegisterer.isRegistered(inventory.getHolder())) {
            return;
        }

        clock = new BrewClock(this, inventory, 400);
        BrewRegisterer.registerBrewingStand(inventory.getHolder());

        clock.run();
    }

    public int getFuelSet() {
        return fuelSet;
    }

    public void setFuelSet(int fuelSet) {
        this.fuelSet = fuelSet;
    }

    public int getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(int fuelCharge) {
        this.fuelCharge = fuelCharge;
    }


}