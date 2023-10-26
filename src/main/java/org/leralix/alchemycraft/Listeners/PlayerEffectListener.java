package org.leralix.alchemycraft.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.customEffect.CustomEffectKey;
import org.leralix.alchemycraft.customEffect.CustomEffectsManager;

public class PlayerEffectListener implements Listener {

    @EventHandler
    public void playerKillEntity(EntityDeathEvent event) {

        Entity deadEntity = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if(killer == null)
            return;

        if(CustomEffectsManager.hasEffect(killer, CustomEffectKey.MIDAS_TOUCH)){
            killer.sendMessage(CustomEffectKey.MIDAS_TOUCH.getName());

            deadEntity.getLocation().getWorld().dropItemNaturally(deadEntity.getLocation(), new ItemStack(Material.GOLD_NUGGET, 3));

        }


    }


}
