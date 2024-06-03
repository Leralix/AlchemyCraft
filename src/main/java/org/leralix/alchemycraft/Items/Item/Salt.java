package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.brewing.BrewingRecipe;

public class Salt extends CustomItem {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUGAR);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.salt");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);


        itemMeta.setCustomModelData(5412);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Salt(){
        super("SALT", getItem());
    }


}