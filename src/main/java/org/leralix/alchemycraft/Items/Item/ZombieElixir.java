package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class ZombieElixir extends CustomItem implements Consumable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.zombie_elixir");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5409);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ZombieElixir(){
        super(ItemKey.ZOMBIE_ELIXIR,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_zombie_elixir");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, getItem());
        craftingRecipe.shape(
                "AAA",
                " B ",
                "   "
        );
        craftingRecipe.setIngredient('A', new RecipeChoice.ExactChoice(AlchemyCraft.getItemManager().getItemStack(ItemKey.ZOMBIE_BROTH)));
        craftingRecipe.setIngredient('B', Material.HONEY_BOTTLE);

        return List.of(craftingRecipe);
    }

    public void onConsume(Player player) {
        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 5, 1));
        player.addPotionEffect(PotionEffectType.CONFUSION.createEffect(20 * 20, 1));

    }
}
