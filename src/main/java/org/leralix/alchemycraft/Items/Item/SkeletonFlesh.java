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

public class SkeletonFlesh extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.translatable("alchemy_craft.item.skeleton_flesh"));
        itemMeta.setCustomModelData(5405);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public SkeletonFlesh(){
        super(ItemKey.SKELETON_FLESH,getItem());
        DropManager.registerDrop(EntityType.PIGLIN, new DropItem(getItem(),0.25));
    }

}
