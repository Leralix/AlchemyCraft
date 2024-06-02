package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.interfaces.Craftable;

import java.util.List;

public class UncookedPlate extends CustomItem implements Craftable {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.uncooked_plate");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5416);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public UncookedPlate(){
        super("UNCOOKED_PLATE",getItem());
    }

    @Override
    public List<Recipe> getRecipes() {

        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_uncooked_plate");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, getItem());
        craftingRecipe.shape(
                "   ",
                "A A",
                "AAA"
        );
        craftingRecipe.setIngredient('A', Material.CLAY_BALL);


        return List.of(craftingRecipe);
    }
}
