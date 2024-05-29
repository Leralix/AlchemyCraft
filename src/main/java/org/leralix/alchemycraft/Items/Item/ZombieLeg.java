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
import org.leralix.alchemycraft.interfaces.Consumable;
import org.leralix.alchemycraft.drops.DropItem;
import org.leralix.alchemycraft.drops.DropManager;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;

//@ItemData(name = "Zombie leg", base = Material.ROTTEN_FLESH, durability = 100, model_data = 101, version = 1)
public class ZombieLeg extends CustomItem implements Consumable {

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
        super(ItemKey.ZOMBIE_LEG,getItem());
        DropManager.registerDrop(EntityType.ZOMBIE_HORSE, new DropItem(getItem(),1));
        DropManager.registerDrop(EntityType.ZOMBIE, new DropItem(getItem(),0.05));

    }

    @Override
    public void onConsume(Player player, PlayerItemConsumeEvent event) {
        player.addPotionEffect(PotionEffectType.SLOW.createEffect(20 * 3, 4));
        player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(20 * 5, 1));
        player.addPotionEffect(PotionEffectType.CONFUSION.createEffect(20 * 20, 1));

        player.setFoodLevel(player.getFoodLevel() + 6);
    }

}
