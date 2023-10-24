
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
public class GoldenSoup extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.golden_stew");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5415);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GoldenSoup(){
        super(ItemKey.GoldenSoup,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_golden_soup");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, GoldenSoup.getItem());
        craftingRecipe.shape(
                "AA ",
                "AA ",
                "CB "
        );
        craftingRecipe.setIngredient('A', new RecipeChoice.ExactChoice(AlchemyCraft.getItemManager().getItemStack(ItemKey.GoldenBeetroot)));
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(AlchemyCraft.getItemManager().getItemStack(ItemKey.WatermelonJuice)));
        craftingRecipe.setIngredient('C', Material.BOWL);

        return List.of(craftingRecipe);
    }

}
