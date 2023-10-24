package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
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

        Component italic_name = Component.translatable("alchemy_craft.item.skeleton_flesh");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5405);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public SkeletonFlesh(){
        super(ItemKey.SKELETON_FLESH,getItem());
        DropManager.registerDrop(EntityType.SKELETON, new DropItem(getItem(),0.05));
        DropManager.registerDrop(EntityType.HORSE, new DropItem(getItem(),1));
    }

}
