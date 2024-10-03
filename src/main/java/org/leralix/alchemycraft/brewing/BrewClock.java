package org.leralix.alchemycraft.brewing;

import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.leralix.alchemycraft.AlchemyCraft;

/*
 * Slot 0: 3 Potion Slot Far Left
 * Slot 1: 3 Potion Slot Middle
 * Slot 2: 3 Potion Slot Far Right
 * Slot 3: Ingredient Slot 4: Fuel
 */
public class BrewClock extends BukkitRunnable {
    private final BrewerInventory inventory;
    private final BrewingRecipe recipe;
    private final ItemStack[] before;
    private final BrewingStand stand;
    private int current;

    public BrewClock(BrewingRecipe recipe, BrewerInventory inventory, int time) {
        this.recipe = recipe;
        this.inventory = inventory;
        this.stand = inventory.getHolder();
        this.before = inventory.getContents();
        this.current = time;
        runTaskTimer(AlchemyCraft.getPlugin(), 0, 1);
    }
    @Override
    public void run() {
        if (current == 0) {
            endBrew();
            return;
        }

        if (searchChanged(before, inventory.getContents(), true)) {
            //System.out.println("changed");
            cancel();
            BrewRegisterer.unregisterBrewingStand(stand);
            return;
        }


        // Decrement, set the brewing time, and update the stand

        current--;
        stand.setBrewingTime(current);
        stand.update(true);
    }

    private void endBrew() {





        ItemStack newFuel = recipe.getFuel();
        if (recipe.getFuel() != null
                && recipe.getFuel().getType() != Material.AIR
                && recipe.getFuel().getAmount() > 0) {
            System.out.println("Fuel: " + inventory.getFuel().getAmount() + " " + recipe.getFuel().getAmount());
            if(inventory.getFuel() == null)
                throw new NullPointerException("fuel is null");

            int count = 0;
            while (inventory.getFuel().getAmount() > 0 && stand.getFuelLevel() + recipe.getFuelCharge() < 100) {
                stand.setFuelLevel(stand.getFuelLevel() + recipe.getFuelSet());
                count++;
            }
            // If the fuel in the inventory is 0, set it to air.
            if (inventory.getFuel().getAmount() == 0) {
                newFuel = new ItemStack(Material.AIR);
            } else {
                stand.setFuelLevel(100);
                newFuel.setAmount(inventory.getFuel().getAmount() - count);
            }
        } else {
            newFuel = new ItemStack(Material.AIR);
        }
        inventory.setFuel(newFuel);


        recipe.getAction().brew(inventory);

        stand.setFuelLevel(stand.getFuelLevel() - recipe.getFuelCharge());

        cancel();
        BrewRegisterer.unregisterBrewingStand(stand);
    }

    // Check if any slots were changed
    public boolean searchChanged(ItemStack[] before, ItemStack[] after, boolean mode) {
        for (int i = 0; i < before.length; i++) {



            if(before[i] == null && after[i] == null) continue;

            if(before[i] == null && !(after[i] == null)) return true;
            if(!(before[i] == null) && after[i] == null) return true;

            /*a
            if (mode) {
                if (!(before[i].isSimilar(after[i]))) {
                    continue;
                }
            } else {
                if (!(before[i].getType() == after[i].getType())) {
                    return true;
                }
            }

             */
        }
        return false;
    }
}