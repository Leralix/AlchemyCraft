package org.leralix.alchemycraft.Items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.leralix.alchemycraft.brewing.BrewingRecipe;
import org.leralix.alchemycraft.interfaces.Craftable;

import java.util.*;

public class ItemManager {

    private static final HashMap<ItemKey, CustomItem> items = new HashMap<>();
    private static final Set<BrewingRecipe> brewItems = new HashSet<>();


    public void registerItem(CustomItem item) {
        items.put(item.key,item);
        if(item instanceof CustomItemBrew customItemBrew){
            registerBrewing(customItemBrew.getBrewRecipe());
        }
    }


    public void registerBrewing(BrewingRecipe recipe) {
        brewItems.add(recipe);
    }

    public void applyRecipes(Plugin plugin) {
        for (CustomItem item : items.values()) {
            System.out.println(item.key);
            if (item instanceof Craftable craftable){
                List<Recipe> recipes = craftable.getRecipes();
                for (Recipe recipe : recipes) {
                    plugin.getServer().addRecipe(recipe);
                }
            }
        }
    }

    public static HashMap<ItemKey, CustomItem> getAll() {
        return items;
    }

    public static CustomItem get(ItemKey key){
        if(items.containsKey(key))
            return items.get(key);
        return null;
    }

    public static CustomItem get(ItemStack newItem){

        for (CustomItem item : items.values()) {
            if(item.itemStack.isSimilar(newItem))
                return item;
        }
        return null;
    }

    public static Set<BrewingRecipe> getBrewItems(){
        return brewItems;
    }

    public static ItemStack getItemStack(ItemKey key){
        return Objects.requireNonNull(get(key)).itemStack;
    }


}