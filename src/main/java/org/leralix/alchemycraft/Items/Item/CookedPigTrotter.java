package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Drops.DropItem;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Listeners.PlayerConsumeItem;

import java.util.List;

public class CookedPigTrotter extends CustomItem implements Consumable {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.COOKED_PORKCHOP);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.cooked_pig_trotter");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5413);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public CookedPigTrotter(){
        super(ItemKey.COOKED_PIG_TROTTER,getItem());
    }

    @Override
    public List<Recipe> getRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(NamespacedKey.minecraft("alchemycraft_cooked_pig_trotter"), getItem(), new RecipeChoice.ExactChoice(ItemManager.getItemStack(ItemKey.PIG_TROTTER)), 0.35f, 200);
        return List.of(recipe);
    }

    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(20 * 4, 1));
        player.setFoodLevel(player.getFoodLevel() + 7);
    }
}
