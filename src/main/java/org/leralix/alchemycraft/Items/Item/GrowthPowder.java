
package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class GrowthPowder extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUGAR);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.growth_powder");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5410);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GrowthPowder(){
        super(ItemKey.GrowthPowder,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_growth_powder");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, GrowthPowder.getItem());
        craftingRecipe.shape(
                "A  ",
                "B  ",
                "C  "
        );
        craftingRecipe.setIngredient('A', Material.BONE_MEAL);
        craftingRecipe.setIngredient('B', Material.RED_MUSHROOM);
        craftingRecipe.setIngredient('C', Material.BROWN_MUSHROOM);

        return List.of(craftingRecipe);
    }

}
