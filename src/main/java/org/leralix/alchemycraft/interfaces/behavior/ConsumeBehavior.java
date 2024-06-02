package org.leralix.alchemycraft.interfaces.behavior;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.Items.ItemManager;

import java.util.Collection;

public class ConsumeBehavior implements OnConsume {

    private final int hunger;
    private final int saturation;
    private final boolean removeOnConsume;
    private final String itemGivenWhenConsumed;
    private final Collection<PotionEffect> effectOnConsume;


    public ConsumeBehavior(int hunger, int saturation, Collection<PotionEffect> effectOnConsume, boolean removeOnConsume, String itemGivenWhenConsumed) {

        this.hunger = hunger;
        this.saturation = saturation;
        this.removeOnConsume = removeOnConsume;
        this.itemGivenWhenConsumed = itemGivenWhenConsumed;
        this.effectOnConsume = effectOnConsume;
    }

    @Override
    public void onConsume(Player player) {

        if(hunger != 0)
            player.setFoodLevel(player.getFoodLevel() + hunger);
        if(saturation != 0)
            player.setSaturation(player.getSaturation() + saturation);
        if(removeOnConsume)
            player.getInventory().remove(player.getInventory().getItemInMainHand());
        if(itemGivenWhenConsumed != null){
            Material material = Material.getMaterial(itemGivenWhenConsumed);
            if(material == null)
                return;
            ItemStack itemStack = new ItemStack(material);
            player.getInventory().addItem(itemStack);
        }
        for (PotionEffect effect : effectOnConsume) {
            player.addPotionEffect(effect);
        }
        player.sendMessage("You consumed a custom item!");
    }
}
