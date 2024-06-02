
package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
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
import org.leralix.alchemycraft.interfaces.Consumable;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.customEffect.CustomEffectKey;
import org.leralix.alchemycraft.customEffect.CustomEffectsManager;
import org.leralix.alchemycraft.interfaces.Craftable;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class GoldenSoup extends CustomItem implements Consumable, Craftable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.golden_soup");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5415);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GoldenSoup(){
        super("GOLDEN_SOUP",getItem());
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
        craftingRecipe.setIngredient('A', new RecipeChoice.ExactChoice(ItemManager.getItemStack("GOLDEN_BEETROOT")));
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemManager.getItemStack("WATERMELON_JUICE")));
        craftingRecipe.setIngredient('C', Material.BOWL);

        return List.of(craftingRecipe);
    }

    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        CustomEffectsManager.addTimedEffect(player, CustomEffectKey.MIDAS_TOUCH,30);
    }

}
