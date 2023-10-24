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
public class ZombieBroth extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.POTION);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.zombie_broth");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5403);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ZombieBroth(){
        super(ItemKey.ZombieBroth,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_zombiebroth");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, ZombieBroth.getItem());
        craftingRecipe.shape(
                "A  ",
                "B  ",
                "C  "
        );
        craftingRecipe.setIngredient('A', Material.ROTTEN_FLESH);
        //craftingRecipe.setIngredient('B', Material.ROTTEN_FLESH);
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(AlchemyCraft.getItemManager().getItemStack(ItemKey.ZombieLeg)));
        craftingRecipe.setIngredient('C', Material.POTION);

        return List.of(craftingRecipe);
    }

}
