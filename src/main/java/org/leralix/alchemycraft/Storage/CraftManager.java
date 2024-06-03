package org.leralix.alchemycraft.Storage;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.Utils.MaterialUtil;

import java.util.List;

public class CraftManager {
    public static void registerCrafts() {

        FileConfiguration configFile = ConfigUtil.getCustomConfig("crafts.yml");
        ConfigurationSection craftingTableSection = configFile.getConfigurationSection("custom_crafts");

        if (craftingTableSection == null) {
            AlchemyCraft.getPlugin().getLogger().warning("'custom_crafts' section not found in the configuration. Crafting table custom crafts have not been loaded.");
            return;
        }

        for (String key : craftingTableSection.getKeys(false)) {
            ConfigurationSection craftConfig = craftingTableSection.getConfigurationSection(key);
            if (craftConfig == null) continue;

            ConfigurationSection resultConfig = craftConfig.getConfigurationSection("result");
            if (resultConfig == null) continue;

            String resultItemString = resultConfig.getString("item");
            if (resultItemString == null)
                throw new IllegalArgumentException("Invalid item for custom craft: " + key);
            int resultCount = resultConfig.getInt("count", 1);

            ItemStack item = MaterialUtil.getItem(resultItemString, key);

            ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(AlchemyCraft.getPlugin(), key + "_crafting"), item);


            ConfigurationSection recipeSection = craftConfig.getConfigurationSection("recipe");
            if (recipeSection == null)
                throw new IllegalArgumentException("Recipe section is required for custom item: " + key);


            List<String> recipeShape = recipeSection.getStringList("shape");

            if(recipeShape.size() != 3)
                throw new IllegalArgumentException("Invalid recipe shape for custom item: " + key);
            for(String shape : recipeShape){
                if(shape.length() != 3)
                    throw new IllegalArgumentException("Invalid recipe shape for custom item: " + key);
            }

            recipe.shape(recipeShape.get(0), recipeShape.get(1), recipeShape.get(2));

            ConfigurationSection ingredientsSection = recipeSection.getConfigurationSection("ingredients");
            if (ingredientsSection == null) {
                throw new IllegalArgumentException("Ingredients section is required for custom item: " + key);
            }

            for (String ingredientKey : ingredientsSection.getKeys(false)) {
                String ingredient = ingredientsSection.getString(ingredientKey);
                if (ingredient == null)
                    throw new IllegalArgumentException("Invalid ingredient for custom item: " + key);
                recipe.setIngredient(ingredientKey.charAt(0), MaterialUtil.getItem(ingredient, key));
            }
            AlchemyCraft.getPlugin().getServer().addRecipe(recipe);

            AlchemyCraft.getPluginLogger().info("Registered craft: " + key);

        }




    }

    public static void registerFurnaceCraft() {

    }
}
