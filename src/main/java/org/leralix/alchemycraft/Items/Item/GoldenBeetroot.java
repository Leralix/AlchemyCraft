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
import org.leralix.alchemycraft.Items.ItemData;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Lang.Lang;

import java.util.List;

public class GoldenBeetroot extends CustomItem {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BEETROOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.translatable("alchemy_craft.item.golden_beetroot"));
        itemMeta.setCustomModelData(5401);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GoldenBeetroot(){
        super(ItemKey.GoldenBeetroot, getItem());
    }
    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_goldenbeetroot");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, getItemStack());
        craftingRecipe.shape(" A ", "ABA", " A ");
        craftingRecipe.setIngredient('A', Material.GOLD_NUGGET);
        craftingRecipe.setIngredient('B', Material.BEETROOT);

        return List.of(craftingRecipe);
    }

}
