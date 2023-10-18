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

public class GolemHeart extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.translatable("alchemy_craft.item.golem_heart"));
        itemMeta.setCustomModelData(5406);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GolemHeart(){
        super(ItemKey.GOLEM_HEART,getItem());
        DropManager.registerDrop(EntityType.IRON_GOLEM, new DropItem(getItem(),0.75));
    }

}
