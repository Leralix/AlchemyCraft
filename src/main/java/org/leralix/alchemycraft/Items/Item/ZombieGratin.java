package org.leralix.alchemycraft.Items.Item;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class ZombieGratin extends CustomItem implements Consumable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.zombie_gratin");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5407);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ZombieGratin(){
        super(ItemKey.ZOMBIE_GRATIN,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_zombie_gratin");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, getItem());
        craftingRecipe.shape(
                "AB ",
                "CC ",
                "DE "
        );

        craftingRecipe.setIngredient('A', new RecipeChoice.ExactChoice(ItemManager.getItemStack(ItemKey.ZOMBIE_ELIXIR)));
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemManager.getItemStack(ItemKey.PIG_TROTTER)));
        craftingRecipe.setIngredient('C', Material.POTATO);
        craftingRecipe.setIngredient('D', Material.BOWL);
        craftingRecipe.setIngredient('E', new RecipeChoice.ExactChoice(ItemManager.getItemStack(ItemKey.SALT)));



        return List.of(craftingRecipe);
    }

    @Override
    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.setFoodLevel(player.getFoodLevel() + 14);
    }

}
