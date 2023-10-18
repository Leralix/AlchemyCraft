package org.leralix.alchemycraft.Items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.BrewAction.BrewingRecipe;
import org.leralix.alchemycraft.BrewAction.CustomItemBrew;
import org.leralix.alchemycraft.Lang.Lang;

import java.util.*;

public class ItemManager {

    private final Plugin plugin;
    private final HashMap<ItemKey, CustomItem> items;
    private static final Set<BrewingRecipe> brewItems = new HashSet<>();

    public ItemManager(Plugin plugin) {
        this.plugin = plugin;
        this.items = new HashMap<>();
    }

    public void registerItem(CustomItem item) {
        items.put(item.key,item);
    }

    public void registerItem(CustomItemBrew brewItem) {
        items.put(brewItem.key,brewItem);
        //brewItems.add(brewItem.recipe);
    }

    public static void registerBrewing(BrewingRecipe recipe) {
        brewItems.add(recipe);
    }

    public void applyRecipes() {
        for (CustomItem item : items.values()) {
            List<Recipe> recipes = item.getRecipes();
            for (Recipe recipe : recipes) {
                plugin.getServer().addRecipe(recipe);
            }
        }
    }




    public HashMap<ItemKey, CustomItem> getall() {
        return items;
    }

    public CustomItem get(ItemKey key){
        if(items.containsKey(key))
            return items.get(key);
        //AlchemyCraft.getPluginLogger().warning(Objects.requireNonNull(Lang.HASHMAP_ERROR.getTranslation()).());
        return null;
    }

    public static Set<BrewingRecipe> getBrewItems(){
        return brewItems;
    }


    public ItemStack getItemStack(ItemKey key){
        return get(key).itemStack;
    }

}