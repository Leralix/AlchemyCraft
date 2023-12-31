package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.Brewing.BrewAction;
import org.leralix.alchemycraft.Brewing.BrewingRecipe;
import org.leralix.alchemycraft.Brewing.CustomItemBrew;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Items.ItemKey;

public class WatermelonJuice extends CustomItemBrew implements Consumable {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.watermelon_juice");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);


        itemMeta.setCustomModelData(5404);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public WatermelonJuice(){
        super(ItemKey.WATERMELON_JUICE, getItem());
    }


    @Override
    public void brew(BrewerInventory inventory) {

    }

    public BrewingRecipe getBrewRecipe() {
        return new BrewingRecipe(new ItemStack(Material.MELON),new ItemStack(Material.BLAZE_POWDER), inventory -> {

            for (int i = 0; i < 3; i++) {

                ItemStack currentItem = inventory.getItem(i);

                if(currentItem.getType() == Material.GLASS_BOTTLE){
                    inventory.setItem(i,getItem());
                }
            }
        },false,10,0);
    }
    public void onConsume(Player player) {
        player.addPotionEffect(PotionEffectType.SPEED.createEffect(20 * 3, 1));

        player.setFoodLevel(player.getFoodLevel() + 2);
    }
}