package org.leralix.alchemycraft.Items.Item;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemData;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;

import java.util.List;

@ItemData(name = "better custom boat", base = Material.ACACIA_CHEST_BOAT, durability = 100, model_data = 102, version = 1)
public class TestBoat2 extends CustomItem {

    public TestBoat2(ItemKey _key){
        super(_key);
    }


    @Override
    public List<Recipe> getRecipes() {

        ItemManager itemManager = AlchemyCraft.getItemManager();

        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft:testboat2");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, makeItemStack());
        craftingRecipe.shape("AAA", "ABA", "AAA");

        craftingRecipe.setIngredient('A', Material.DIAMOND);
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(itemManager.getItemStack(ItemKey.testBoat)));



        return List.of(craftingRecipe);
    }
}
