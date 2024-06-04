package org.leralix.alchemycraft.Utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemStack;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;

public class MaterialUtil {

    public static Material material_from(NamespacedKey key) {
        return Registry.MATERIAL.get(key);
    }

    public static boolean is_seeded_plant(Material type) {
        switch (type) {
            default:
                return false;
            case WHEAT:
            case CARROTS:
            case POTATOES:
            case BEETROOTS:
            case NETHER_WART:
                return true;
        }
    }

    public static Material seed_for(Material plant_type) {
        switch (plant_type) {
            default:
                return null;
            case WHEAT:
                return Material.WHEAT_SEEDS;
            case CARROTS:
                return Material.CARROT;
            case POTATOES:
                return Material.POTATO;
            case BEETROOTS:
                return Material.BEETROOT_SEEDS;
            case NETHER_WART:
                return Material.NETHER_WART;
        }
    }

    public static Material farmland_for(Material seed_type) {
        switch (seed_type) {
            default:
                return null;
            case WHEAT_SEEDS:
            case CARROT:
            case POTATO:
            case BEETROOT_SEEDS:
                return Material.FARMLAND;
            case NETHER_WART:
                return Material.SOUL_SAND;
        }
    }

    public static boolean is_replaceable_grass(Material type) {
        switch (type) {
            default:
                return false;
            case TALL_GRASS:
            case GRASS:
                return true;
        }
    }

    public static boolean is_tillable(Material type) {
        switch (type) {
            default:
                return false;
            case DIRT:
            case GRASS_BLOCK:
            case DIRT_PATH:
                return true;
        }
    }

    public static ItemStack getItem(String itemString, String key) {


        if(!itemString.contains(":"))
            throw new IllegalArgumentException("Invalid item for custom drop: " + key + " (use minecraft: or alchemy:)");

        String itemName = itemString.split(":")[1];
        ItemStack item;
        if(itemString.startsWith("minecraft")) {
            Material material = Material.matchMaterial(itemName.toUpperCase());
            if (material == null)
                throw new IllegalArgumentException("Invalid vanilla material for custom drop: " + key);
            item = new ItemStack(material);
        }
        else if (itemString.startsWith("alchemy")){
            CustomItem customItem = ItemManager.get(itemName);
            if (customItem == null)
                throw new IllegalArgumentException("Custom item not found : " + itemString);
            item = customItem.getItemStack();
        }
        else
            throw new IllegalArgumentException("Invalid item for custom drop: " + key);

        return item;
    }
}