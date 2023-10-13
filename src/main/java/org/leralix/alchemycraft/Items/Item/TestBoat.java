package org.leralix.alchemycraft.Items.Item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Drops.DropItem;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemData;
import org.leralix.alchemycraft.Items.ItemKey;

import java.util.List;

@ItemData(name = "small custom boat", base = Material.ACACIA_BOAT, durability = 10, model_data = 101, version = 1)
public class TestBoat extends CustomItem {

    public TestBoat(ItemKey _key){
        super(_key);
        DropManager.registerDrop(EntityType.ZOMBIE_HORSE, new DropItem(makeItemStack(),0.5));
    }
    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft:testboat");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, makeItemStack());
        craftingRecipe.shape("AAA", "ABA", "AAA");
        craftingRecipe.setIngredient('A', Material.IRON_INGOT);
        craftingRecipe.setIngredient('B', Material.DIAMOND);

        return List.of(craftingRecipe);
    }

}
