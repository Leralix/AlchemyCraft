package org.leralix.alchemycraft.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;

public class CraftingListener implements Listener {

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        // Check if the recipe matches your custom recipe
        if (event.getRecipe().getResult().isSimilar(ItemManager.get(ItemKey.GOLDEN_SOUP).getItemStack())){

            // Loop through the crafting matrix to find the specific ingredient
            ItemStack[] matrix = event.getInventory().getMatrix();
            for (int i = 0; i < matrix.length; i++) {
                ItemStack item = matrix[i];
                if (item != null && item.isSimilar(ItemManager.get(ItemKey.WATERMELON_JUICE).getItemStack())){

                    matrix[i] = new ItemStack(Material.GLASS_BOTTLE);

                    event.getInventory().setMatrix(matrix);
                    break;
                }
            }
        }
    }
}