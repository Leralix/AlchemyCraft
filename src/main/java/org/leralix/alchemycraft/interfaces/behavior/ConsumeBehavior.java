package org.leralix.alchemycraft.interfaces.behavior;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.ItemManager;

import java.util.Collection;

public class ConsumeBehavior implements OnConsume {

    private final int hunger;
    private final int saturation;
    private final boolean removeOnConsume;
    private final ItemStack itemGivenWhenConsumed;
    private final Collection<PotionEffect> effectOnConsume;


    public ConsumeBehavior(int hunger, int saturation, Collection<PotionEffect> effectOnConsume, boolean removeOnConsume, ItemStack itemGivenWhenConsumed) {
        this.hunger = hunger;
        this.saturation = saturation;
        this.removeOnConsume = removeOnConsume;
        this.itemGivenWhenConsumed = itemGivenWhenConsumed;
        this.effectOnConsume = effectOnConsume;
    }

    @Override
    public void onConsume(Player player) {

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        int mainHandSlot = player.getInventory().getHeldItemSlot();

        if(hunger != 0)
            player.setFoodLevel(player.getFoodLevel() + hunger);
        if(saturation != 0)
            player.setSaturation(player.getSaturation() + saturation);



        if (removeOnConsume) {
            int newAmount = itemInHand.getAmount() - 1;
            if (newAmount > 0) {
                itemInHand.setAmount(newAmount);
            } if (newAmount > 0) {
                itemInHand.setAmount(newAmount);
            } else {
                player.getInventory().setItem(mainHandSlot, null);
            }
        }


        if(itemGivenWhenConsumed != null){
            player.getInventory().addItem(itemGivenWhenConsumed);
        }
        for (PotionEffect effect : effectOnConsume) {
            player.addPotionEffect(effect);
        }
    }
}
