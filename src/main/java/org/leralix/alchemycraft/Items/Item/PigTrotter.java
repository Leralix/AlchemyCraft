package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.interfaces.Consumable;
import org.leralix.alchemycraft.drops.DropItem;
import org.leralix.alchemycraft.drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;

public class PigTrotter extends CustomItem implements Consumable {

    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.PORKCHOP);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.pig_trotter");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);

        itemMeta.setCustomModelData(5408);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public PigTrotter(){
        super("PIG_TROTTER",getItem());
        DropManager.registerDrop(EntityType.PIGLIN, new DropItem(getItem(),0.25));
    }

    @Override
    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.setFoodLevel(player.getFoodLevel() + 3);
    }

}
