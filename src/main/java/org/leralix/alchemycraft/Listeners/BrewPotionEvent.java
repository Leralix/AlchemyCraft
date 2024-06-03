package org.leralix.alchemycraft.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.brewing.BrewingRecipe;

public class BrewPotionEvent implements Listener {

    @EventHandler
    public void customPotionItemStackClick(InventoryClickEvent event) {

        Inventory inv = event.getClickedInventory();
        if (inv == null || inv.getType() != InventoryType.BREWING) {
            return;
        }

        if (!(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)) {
            return;
        }
        event.setCancelled(true);


        ItemStack is = event.getCurrentItem(); // GETS ITEMSTACK THAT IS BEING CLICKED
        ItemStack is2 = event.getCursor(); // GETS CURRENT ITEMSTACK HELD ON MOUSE

        if(is == null){
            event.setCancelled(false);
            return;
        }





        Player p = (Player)(event.getView().getPlayer());

        boolean sameItem = is.isSimilar(is2);
        ClickType type = event.getClick();

        int firstAmount = is.getAmount();
        int secondAmount = is2.getAmount();

        int stack = is.getMaxStackSize();
        int half = firstAmount / 2;

        int clickedSlot = event.getSlot();

        if(!(event.getClick() == ClickType.RIGHT) && !(event.getClick() == ClickType.LEFT)){
            return;
        }

        if (type == ClickType.RIGHT) {
            if(is.getType() == Material.AIR){
                ItemStack placedItem = new ItemStack(is2);
                placedItem.setAmount(1);

                event.getClickedInventory().setItem(event.getSlot(), placedItem);

                is2.setAmount(is2.getAmount() - 1);
            }

            if (sameItem){
                if(is.getAmount() < stack && is2.getAmount() > 0){
                    is.setAmount(is.getAmount() + 1);
                    is2.setAmount(is2.getAmount() - 1);
                }
            }

        }
        if (type == ClickType.LEFT) {
            if (is.getType() == Material.AIR) {
                p.setItemOnCursor(is);
                inv.setItem(clickedSlot, is2);
            } else if (sameItem) {

                int used = stack - firstAmount;
                if (secondAmount <= used) {
                    is.setAmount(firstAmount + secondAmount);
                    p.setItemOnCursor(null);
                } else {
                    is2.setAmount(secondAmount - used);
                    is.setAmount(firstAmount + used);
                    p.setItemOnCursor(is2);
                }
            } else{
                inv.setItem(clickedSlot, is2);
                p.setItemOnCursor(is);
            }
        }


        if (((BrewerInventory) inv).getIngredient() == null) {
            return;
        }

        BrewingRecipe recipe = BrewingRecipe.getRecipe((BrewerInventory) inv);

        if (recipe == null) {
            return;
        }
        recipe.startBrewing((BrewerInventory) inv);
    }

}