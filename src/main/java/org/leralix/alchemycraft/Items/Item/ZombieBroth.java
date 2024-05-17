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
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;

import java.util.List;

//@ItemData(name = Lang.ZOMBIE_BROTH.getTranslation(), base = Material.POTION, durability = 10, model_data = 101, version = 1)
public class ZombieBroth extends CustomItem implements Consumable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.zombie_broth");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5403);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ZombieBroth(){
        super(ItemKey.ZOMBIE_BROTH,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        NamespacedKey craftingKey = new NamespacedKey(AlchemyCraft.getPlugin(), "alchemycraft_zombiebroth");
        ShapedRecipe craftingRecipe = new ShapedRecipe(craftingKey, ZombieBroth.getItem());
        craftingRecipe.shape(
                "A  ",
                "B  ",
                "C  "
        );
        craftingRecipe.setIngredient('A', Material.ROTTEN_FLESH);
        craftingRecipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemManager.getItemStack(ItemKey.ZOMBIE_LEG)));
        craftingRecipe.setIngredient('C', Material.BOWL);

        return List.of(craftingRecipe);
    }
    @Override
    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 5, 1));
        player.addPotionEffect(PotionEffectType.CONFUSION.createEffect(20 * 20, 1));
        player.addPotionEffect(PotionEffectType.POISON.createEffect(20 * 3, 2));

    }
}
