
package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.interfaces.Craftable;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class FungalExplosive extends CustomItem implements Craftable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.GUNPOWDER);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.fungal_explosive");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5410);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public FungalExplosive(){
        super(ItemKey.FUNGAL_EXPLOSIVE,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_fungal_explosive");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, FungalExplosive.getItem());
        craftingRecipe.shape(
                "AB "
        );
        craftingRecipe.setIngredient('A', Material.GUNPOWDER);
        craftingRecipe.setIngredient('B', Material.CRIMSON_FUNGUS);

        return List.of(craftingRecipe);
    }

}
