package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.Consumable.Consumable;
import org.leralix.alchemycraft.Drops.DropItem;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

public class SkeletonFlesh extends CustomItem implements Consumable {

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
    @Override
    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.addPotionEffect(PotionEffectType.SLOW.createEffect(20 * 3, 4));
        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 5, 1));
        player.addPotionEffect(PotionEffectType.CONFUSION.createEffect(20 * 10, 1));

        player.setFoodLevel(player.getFoodLevel() + 4);
    }
}
