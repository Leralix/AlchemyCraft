package org.leralix.alchemycraft.Storage;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.Utils.MaterialUtil;
import org.leralix.alchemycraft.brewing.BrewingRecipe;

import java.util.List;

import static org.leralix.alchemycraft.Items.ItemManager.registerBrewing;

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

        FileConfiguration configFile = ConfigUtil.getCustomConfig("furnace.yml");
        ConfigurationSection smeltingSection = configFile.getConfigurationSection("custom_smelting");

        if (smeltingSection == null) {
            AlchemyCraft.getPlugin().getLogger().warning("'custom_smelting' section not found in the configuration. Crafting table custom crafts have not been loaded.");
            return;
        }

        for (String key : smeltingSection.getKeys(false)) {
            ConfigurationSection recipeConfig = smeltingSection.getConfigurationSection(key);
            if (recipeConfig == null) continue;

            ConfigurationSection recipeSection = recipeConfig.getConfigurationSection("recipe");
            if(recipeSection == null)
                throw new IllegalArgumentException("Recipe section is required for furnace recipe for custom item: " + key);

            String ingredientName = recipeSection.getString("ingredient");
            if(ingredientName == null)
                throw new IllegalArgumentException("Ingredient is required for furnace recipe for custom item: " + key);
            ItemStack ingredientItem = MaterialUtil.getItem(ingredientName, key);
            RecipeChoice.ExactChoice exactIngredient = new RecipeChoice.ExactChoice(ingredientItem);


            String resultName = recipeSection.getString("result");
            if(resultName == null)
                throw new IllegalArgumentException("Result is required for furnace recipe for custom item: " + key);
            ItemStack resultItem = MaterialUtil.getItem(resultName, key);

            float experience = (float) recipeSection.getDouble("experience");
            int burningDuration = recipeSection.getInt("burning_duration");

            FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(AlchemyCraft.getPlugin(), key + "_furnace"), resultItem, exactIngredient, experience, burningDuration);
            AlchemyCraft.getPlugin().getServer().addRecipe(recipe);
            AlchemyCraft.getPluginLogger().info("Registered furnace craft: " + key);
        }

    }

    public static void registerBrew() {

        FileConfiguration configFile = ConfigUtil.getCustomConfig("brewing.yml");
        ConfigurationSection brewingSection = configFile.getConfigurationSection("custom_brewing");

        if(brewingSection == null) {
            AlchemyCraft.getPlugin().getLogger().warning("'custom_brewing' section not found in the configuration. Brewing custom crafts have not been loaded.");
            return;
        }

        for (String key : brewingSection.getKeys(false)) {

            ConfigurationSection recipeConfig = brewingSection.getConfigurationSection(key);
            if (recipeConfig == null) continue;


            String ingredientName = recipeConfig.getString("ingredient");
            if(ingredientName == null)
                throw new IllegalArgumentException("Ingredient name is required for brewing recipe for custom item: " + key);
            ItemStack ingredient = MaterialUtil.getItem(ingredientName, key);

            String ingredientAfterBrewingName = recipeConfig.getString("ingredient_after_brewing");
            if(ingredientAfterBrewingName == null)
                throw new IllegalArgumentException("Ingredient name after brewing is required for brewing recipe for custom item: " + key);
            ItemStack ingredientAfterBrewing = MaterialUtil.getItem(ingredientAfterBrewingName, key);

            String fuelName = recipeConfig.getString("fuel");
            if(fuelName == null)
                throw new IllegalArgumentException("Fuel name is required for brewing recipe for custom item: " + key);
            ItemStack fuel = MaterialUtil.getItem(fuelName, key);


            ItemStack recipient;
            String recipientName = recipeConfig.getString("recipient");
            if(recipientName == null)
                recipient = new ItemStack(Material.AIR);
            else {
                recipient = MaterialUtil.getItem(recipientName, key);
            }

            ItemStack recipientAfter;
            String recipientAfterName = recipeConfig.getString("recipient_after_brewing");
            if(recipientAfterName == null)
                recipientAfter = new ItemStack(Material.AIR);
            else{
                recipientAfter = MaterialUtil.getItem(recipientAfterName, key);
            }


            BrewingRecipe brewingRecipe = new BrewingRecipe(ingredient,fuel, inventory -> {

                inventory.setIngredient(ingredientAfterBrewing);
                for (int i = 0; i < 3; i++) {
                    ItemStack currentItem = inventory.getItem(i);
                    if(currentItem == null)
                        continue;
                    if(currentItem.getType() == recipient.getType()){
                        inventory.setItem(i,recipientAfter);
                    }
                }
            },false,1,0);
            registerBrewing(brewingRecipe);
            AlchemyCraft.getPluginLogger().info("Registered brew: " + key);
        }


    }
}
