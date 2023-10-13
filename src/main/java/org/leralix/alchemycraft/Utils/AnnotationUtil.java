package org.leralix.alchemycraft.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Items.ItemData;

public class AnnotationUtil {

    public static void registerCustomItem(Object customItemObject) {
        Class<?> clazz = customItemObject.getClass();

        if (clazz.isAnnotationPresent(ItemData.class)) {
            ItemData annotation = clazz.getAnnotation(ItemData.class);

            Material base = annotation.base();
            String name = annotation.name();
            int modelData = annotation.model_data();

            ItemStack itemStack = new ItemStack(base);
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(name);
                meta.setCustomModelData(modelData);

                itemStack.setItemMeta(meta);
            }

        }
    }
}
