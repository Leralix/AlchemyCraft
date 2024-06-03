package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.drops.DropItem;
import org.leralix.alchemycraft.Storage.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;

public class GolemHeart extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.golem_heart");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5406);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public GolemHeart(){
        super("GOLEM_HEART",getItem());
        DropManager.registerDrop(EntityType.IRON_GOLEM, new DropItem(getItem(),0.75));
    }

}
