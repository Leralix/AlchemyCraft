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
            //System.out.println("termine");
            // Set ingredient to 1 less than the current. Otherwise set to air
            if (inventory.getIngredient().getAmount() > 1) {
                ItemStack is = inventory.getIngredient();
                is.setAmount(inventory.getIngredient().getAmount() - 1);
                inventory.setIngredient(is);
            } else {
                inventory.setIngredient(new ItemStack(Material.AIR));
            }
            // Check the fuel in the recipe is more than 0, and exists
            ItemStack newFuel = recipe.getFuel();

            //A fixer

            if (recipe.getFuel() != null && recipe.getFuel().getType() != Material.AIR &&
                    recipe.getFuel().getAmount() > 0) {
                /*
                 * We count how much fuel should be taken away in order to fill
                 * the whole fuel bar
                 */
                int count = 0;
                while (inventory.getFuel().getAmount() > 0 && stand.getFuelLevel() + recipe.getFuelCharge() < 100) {
                    stand.setFuelLevel(stand.getFuelLevel() + recipe.getFuelSet());
                    count++;
                }
                // If the fuel in the inventory is 0, set it to air.
                if (inventory.getFuel().getAmount() == 0) {
                    newFuel = new ItemStack(Material.AIR);
                } else {
                    /* Otherwise, set the percent of fuel level to 100 and update the
                     *  count of the fuel
                     */
                    stand.setFuelLevel(100);
                    newFuel.setAmount(inventory.getFuel().getAmount() - count);
                }
            } else {
                newFuel = new ItemStack(Material.AIR);
            }
            inventory.setFuel(newFuel);
            // Brew recipe for each item put in

            recipe.getAction().brew(inventory);


            for (int i = 0; i < 3; i++) {
                if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                    continue;
                }
                recipe.getAction().brew(inventory);
            }

            // Set the fuel level
            stand.setFuelLevel(stand.getFuelLevel() - recipe.getFuelCharge());

            //System.out.println("Fin du timer");

            cancel();
            BrewRegisterer.unregisterBrewingStand(stand);
            return;
        }

        // If a player drags an item, fuel, or any contents, reset it


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
    // Check if any slots were changed
    public boolean searchChanged(ItemStack[] before, ItemStack[] after, boolean mode) {
        for (int i = 0; i < before.length; i++) {



            if(before[i] == null && after[i] == null) continue;

            if(before[i] == null && !(after[i] == null)) return true;
            if(!(before[i] == null) && after[i] == null) return true;

            /*
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