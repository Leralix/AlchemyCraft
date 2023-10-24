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

//@ItemData(name = "Zombie leg", base = Material.ROTTEN_FLESH, durability = 100, model_data = 101, version = 1)
public class ZombieLeg extends CustomItem {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.zombie_leg");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);


        itemMeta.setCustomModelData(5402);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ZombieLeg(){
        super(ItemKey.ZombieLeg,getItem());
        DropManager.registerDrop(EntityType.ZOMBIE_HORSE, new DropItem(getItem(),0.5));
    }

}
