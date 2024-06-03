package org.leralix.alchemycraft.Storage;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.Utils.MaterialUtil;
import org.leralix.alchemycraft.drops.DropItem;

import java.util.*;

public class DropManager {
    private static final Map<EntityType, List<DropItem>> dropItemsMap = new HashMap<>();

    public static void registerDrop(EntityType entityType, DropItem dropItem) {
        dropItemsMap.computeIfAbsent(entityType, k -> new ArrayList<>()).add(dropItem);
    }

    public static List<DropItem> getDrops(EntityType entityType) {
        return dropItemsMap.getOrDefault(entityType, Collections.emptyList());
    }

    public static void registerDrops() {
        FileConfiguration configFile = ConfigUtil.getCustomConfig("drops.yml");


        ConfigurationSection customDropsSection = configFile.getConfigurationSection("custom_drops");

        if (customDropsSection == null) {
            AlchemyCraft.getPlugin().getLogger().warning("'custom_drops' section not found in the configuration. Drops has not been loaded.");
            return;
        }

        for (String key : customDropsSection.getKeys(false)) {

            ConfigurationSection dropConfig = customDropsSection.getConfigurationSection(key);
            if (dropConfig == null) continue;

            String itemString = dropConfig.getString("item");

            String mobString = dropConfig.getString("mob");

            double dropRate = dropConfig.getDouble("drop_rate");

            int amount = dropConfig.getInt("amount");


            if(itemString == null)
                throw new IllegalArgumentException("Invalid item for custom drop: " + key);

            ItemStack item = MaterialUtil.getItem(itemString, key);

            if(mobString == null)
                throw new IllegalArgumentException("Missing name for custom drop: " + key);
            EntityType mob = EntityType.valueOf(mobString);
            if(mob == null)
                throw new IllegalArgumentException("Invalid mob for custom drop: " + key);


            registerDrop(mob, new DropItem(item, dropRate));
            AlchemyCraft.getPluginLogger().info("Registered drop: " + key);
        }
    }
}