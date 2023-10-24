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

public class PigTrotter extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.pig_trotter");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5408);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public PigTrotter(){
        super(ItemKey.PIG_TROTTER,getItem());
        DropManager.registerDrop(EntityType.PIGLIN, new DropItem(getItem(),0.25));
    }

}
