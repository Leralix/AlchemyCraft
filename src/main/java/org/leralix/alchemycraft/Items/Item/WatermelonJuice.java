package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.leralix.alchemycraft.BrewAction.BrewingRecipe;
import org.leralix.alchemycraft.BrewAction.CustomItemBrew;
import org.leralix.alchemycraft.Items.ItemKey;

public class WatermelonJuice extends CustomItemBrew {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.POTION);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.translatable("alchemy_craft.item.watermelon_juice"));
        itemMeta.setCustomModelData(5404);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }



    public WatermelonJuice(){
        super(ItemKey.WatermelonJuice, getItem());
    }


    @Override
    public void brew(BrewerInventory inventory, ItemStack item, ItemStack ingredient) {

        System.out.println("brew !");
        inventory.setContents(new ItemStack[]{item, getItem(), getItem(), getItem()});


    }

}