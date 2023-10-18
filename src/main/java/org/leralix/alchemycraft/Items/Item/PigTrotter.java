package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Drops.DropItem;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

public class PigTrotter extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.translatable("alchemy_craft.item.pig_trotter"));
        itemMeta.setCustomModelData(5403);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public PigTrotter(){
        super(ItemKey.PIG_TROTTER,getItem());
        DropManager.registerDrop(EntityType.PIGLIN, new DropItem(getItem(),0.25));
    }

}
