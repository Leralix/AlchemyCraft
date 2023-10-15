package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
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
        itemMeta.setDisplayName(Lang.ZOMBIE_LEG.getTranslation());
        itemMeta.setCustomModelData(101);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GoldenBeetroot(ItemKey _key){
        super(_key, getItem());
    }
    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraftgoldenbeetroot");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, getItemStack());
        craftingRecipe.shape(" A ", "ABA", " A ");
        craftingRecipe.setIngredient('A', Material.GOLD_NUGGET);
        craftingRecipe.setIngredient('B', Material.BEETROOT);

        return List.of(craftingRecipe);
    }

}
