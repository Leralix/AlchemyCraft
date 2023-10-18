package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.leralix.alchemycraft.BrewAction.BrewAction;

public class WaterMelonJuiceBrew extends BrewAction {

    @Override
    public void brew(BrewerInventory inventory, ItemStack item, ItemStack ingredient) {


        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Component.text("big name"));
        item.setItemMeta(itemMeta);


    }

}