package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.interfaces.Craftable;

import java.util.List;

public class Plate extends CustomItem implements Craftable {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.plate");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5417);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Plate(){
        super("PLATE",getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(NamespacedKey.minecraft("alchemycraft_plate"), getItem(), new RecipeChoice.ExactChoice(ItemManager.getItemStack("UNCOOKED_PLATE")), 1f, 1000);
        return List.of(recipe);
    }

}
