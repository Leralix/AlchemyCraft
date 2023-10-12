package org.leralix.alchemycraft.CustomCrafts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.AlchemyCraft;

public class CustomCrafts {

    private static final JavaPlugin plugin = AlchemyCraft.getPlugin();


    public static void createCustomRecipe(String customItemKey, ItemStack customItem) {


        NamespacedKey key = new NamespacedKey(plugin, customItemKey);
        ShapedRecipe recipe = new ShapedRecipe(key, customItem);

        recipe.shape("###", "# #", "###");
        recipe.setIngredient('#', Material.IRON_INGOT);

        Bukkit.addRecipe(recipe);
    }

    public static ItemStack getCustomItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Custom Sword");
        //meta.setCustomModelData(1); // Set the custom model data. The model should be defined in your resource pack.

        item.setItemMeta(meta);
        return item;
    }
}