package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Brewing.BrewAction;
import org.leralix.alchemycraft.Brewing.BrewingRecipe;
import org.leralix.alchemycraft.Brewing.CustomItemBrew;
import org.leralix.alchemycraft.Items.ItemKey;

public class Salt extends CustomItemBrew {


    private static ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.SUGAR);
        ItemMeta itemMeta = itemStack.getItemMeta();

        Component italic_name = Component.translatable("alchemy_craft.item.salt");
        Component name = italic_name.style(style -> style.decoration(TextDecoration.ITALIC, false));
        itemMeta.displayName(name);


        itemMeta.setCustomModelData(5412);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Salt(){
        super(ItemKey.SALT, getItem());
    }


    @Override
    public void brew(BrewerInventory inventory) {

    }

    public BrewingRecipe getBrewRecipe() {
        return new BrewingRecipe(new ItemStack(Material.WATER_BUCKET),new ItemStack(Material.COAL), new BrewAction(){
            @Override
            public void brew(BrewerInventory inventory) {

                inventory.setIngredient(new ItemStack(Material.BUCKET));


                for (int i = 0; i < 3; i++) {

                    ItemStack currentItem = inventory.getItem(i);

                    if(currentItem == null){
                        inventory.setItem(i,getItem());
                    }
                }
            }
        },false,10,0);
    }

}