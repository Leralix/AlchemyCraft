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
import org.leralix.alchemycraft.BrewAction.BrewingRecipe;

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

        ItemStack is = event.getCurrentItem(); // GETS ITEMSTACK THAT IS BEING CLICKED
        ItemStack is2 = event.getCursor(); // GETS CURRENT ITEMSTACK HELD ON MOUSE

        assert is != null;
        if (event.getClick() == ClickType.RIGHT && is.isSimilar(is2)) {
            is.setAmount(is.getAmount() + 1);
            is2.setAmount(is2.getAmount() - 1);
            return;
        }

        event.setCancelled(true);

        Player p = (Player)(event.getView().getPlayer());

        boolean compare = is.isSimilar(is2);
        ClickType type = event.getClick();

        int firstAmount = is.getAmount();
        int secondAmount = is2.getAmount();

        int stack = is.getMaxStackSize();
        int half = firstAmount / 2;

        int clickedSlot = event.getSlot();

        if (type == ClickType.LEFT) {

            if (is.getType() == Material.AIR) {

                p.setItemOnCursor(is);
                inv.setItem(clickedSlot, is2);

            } else if (compare) {

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

        } else if (type == ClickType.RIGHT) {

            if (is.getType() == Material.AIR) {

                p.setItemOnCursor(is);
                inv.setItem(clickedSlot, is2);

            } else if (is.getType() != Material.AIR && is2.getType() == Material.AIR) {

                ItemStack isClone = is.clone();
                isClone.setAmount(is.getAmount() % 2 == 0 ? firstAmount - half : firstAmount - half - 1);
                p.setItemOnCursor(isClone);

                is.setAmount(firstAmount - half);

            } else if (compare) {

                if ((firstAmount + 1) <= stack) {

                    is2.setAmount(secondAmount - 1);
                    is.setAmount(firstAmount + 1);

                }

            } else {

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
        System.out.println("On lance le brew");

        recipe.startBrewing((BrewerInventory) inv);

    }

}