package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

import java.util.List;

public class GoldenBeetroot extends CustomItem {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BEETROOT);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.golden_beetroot");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5401);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GoldenBeetroot(){
        super(ItemKey.GOLDEN_BEETROOT, getItem());
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

    public void onConsume(Player player) {
        player.setFoodLevel(player.getFoodLevel() + 5);
    }

}
