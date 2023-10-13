package org.leralix.alchemycraft.Items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Lang.Lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemManager {

    private final Plugin plugin;
    private final HashMap<ItemKey, CustomItem> items;

    public ItemManager(Plugin plugin) {
        this.plugin = plugin;
        this.items = new HashMap<>();
    }

    // Méthode pour enregistrer un CustomItem
    public void registerItem(CustomItem item) {
        items.put(item.key,item);
    }

    // Appliquer les recettes de tous les items
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
        AlchemyCraft.getPluginLogger().warning(Lang.HASHMAP_ERROR.getTranslation());
        return null;
    }

    public ItemStack getItemStack(ItemKey key){
        return get(key).makeItemStack();
    }
}