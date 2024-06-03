package org.leralix.alchemycraft.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.brewing.BrewingRecipe;
import org.leralix.alchemycraft.drops.DropItem;
import org.leralix.alchemycraft.Storage.DropManager;
import org.leralix.alchemycraft.interfaces.Craftable;
import org.leralix.alchemycraft.interfaces.behavior.ConsumeBehavior;
import org.leralix.alchemycraft.interfaces.behavior.OnConsume;

import java.util.*;
import java.util.logging.Logger;

public class ItemManager {

    private static final HashMap<String, CustomItem> items = new HashMap<>();
    private static final Set<BrewingRecipe> brewItems = new HashSet<>();


    public static void registerItem(CustomItem item) {
        items.put(item.getKey(),item);
    }


    public static void registerBrewing(BrewingRecipe recipe) {
        brewItems.add(recipe);
    }

    public void applyRecipes(Plugin plugin) {
        for (CustomItem item : items.values()) {
            System.out.println(item.getKey());
            if (item instanceof Craftable craftable){
                List<Recipe> recipes = craftable.getRecipes();
                for (Recipe recipe : recipes) {
                    plugin.getServer().addRecipe(recipe);
                }
            }
        }
    }

    public static HashMap<String, CustomItem> getAll() {
        return items;
    }

    public static CustomItem get(String key){
        if(items.containsKey(key))
            return items.get(key);
        return null;
    }

    public static CustomItem get(ItemStack newItem){

        for (CustomItem item : items.values()) {
            if(item.getItemStack().isSimilar(newItem))
                return item;
        }
        return null;
    }

    public static Set<BrewingRecipe> getBrewItems(){
        return brewItems;
    }

    public static ItemStack getItemStack(String key){
        CustomItem itemStack = get(key);
        if (itemStack != null)
            return itemStack.getItemStack();
        else
            throw new IllegalArgumentException("Item not found: " + key);
    }


    public static void registerItems() {

        FileConfiguration configFile = ConfigUtil.getCustomConfig("items.yml");
        ConfigurationSection configurationSection = configFile.getConfigurationSection("custom_items");
        Logger pluginLogger = AlchemyCraft.getPlugin().getLogger();


        if(configurationSection == null) {
            pluginLogger.warning("'custom_items:' not found, not loading any custom items.");
            return;
        }

        for (String key : configurationSection.getKeys(false)) {
            ConfigurationSection itemSection = configurationSection.getConfigurationSection(key);
            if (itemSection == null) {
                continue;
            }

            String displayName = itemSection.getString("display_name");
            if(displayName == null)
                throw new IllegalArgumentException("'display_name' value is required for custom item: " + key);

            String materialName = itemSection.getString("material");
            if(materialName == null)
                throw new IllegalArgumentException("'material' value is required for custom item: " + key);
            Material material = Material.getMaterial(materialName);
            if(material == null)
                throw new IllegalArgumentException("Invalid material name for custom item: " + key);

            Integer customModelData = itemSection.getInt("custom_model_data");

            List<String> lore = itemSection.getStringList("lore");

            ItemStack customItemStack = createCustomItemStack(material, displayName, customModelData);
            CustomItem customItem = new CustomItem(key, customItemStack);

            // Process obtainable_by section
            ConfigurationSection obtainableBySection = itemSection.getConfigurationSection("obtainable_by");
            if (obtainableBySection != null) {


                // Furnace
                ConfigurationSection furnaceSection = obtainableBySection.getConfigurationSection("furnace");
                if (furnaceSection != null) {
                    ConfigurationSection recipeSection = furnaceSection.getConfigurationSection("recipe");
                    if(recipeSection == null)
                        throw new IllegalArgumentException("Recipe section is required for furnace recipe for custom item: " + key);

                    String ingredientName = recipeSection.getString("ingredient");
                    if(ingredientName == null)
                        throw new IllegalArgumentException("Ingredient is required for furnace recipe for custom item: " + key);
                    Material ingredient = Material.getMaterial(ingredientName);
                    if(ingredient == null)
                        throw new IllegalArgumentException("Invalid ingredient material for furnace recipe for custom item: " + key);

                    ItemStack result = new ItemStack(customItemStack);
                    float experience = (float) recipeSection.getDouble("experience");
                    int burningDuration = recipeSection.getInt("burning_duration");

                    FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(AlchemyCraft.getPlugin(), key + "_furnace"), result, ingredient, experience, burningDuration);
                    AlchemyCraft.getPlugin().getServer().addRecipe(recipe);
                }

                ConfigurationSection brewingSection = obtainableBySection.getConfigurationSection("brewing_stand");
                if (brewingSection != null){
                    ConfigurationSection ReceipeSection = brewingSection.getConfigurationSection("recipe");
                    if(ReceipeSection == null)
                        throw new IllegalArgumentException("Recipe section is required for brewing recipe for custom item: " + key);

                    String ingredientName = ReceipeSection.getString("ingredient");
                    if(ingredientName == null)
                        throw new IllegalArgumentException("Ingredient is required for brewing recipe for custom item: " + key);
                    Material ingredientMaterial = Material.getMaterial(ingredientName);
                    if(ingredientMaterial == null)
                        throw new IllegalArgumentException("Invalid ingredient material for brewing recipe for custom item: " + key);
                    ItemStack ingredient = new ItemStack(ingredientMaterial);


                    String fuelName = ReceipeSection.getString("fuel");
                    if(fuelName == null)
                        throw new IllegalArgumentException("Fuel is required for brewing recipe for custom item: " + key);
                    Material fuelMaterial = Material.getMaterial(fuelName);
                    if(fuelMaterial == null)
                        throw new IllegalArgumentException("Invalid fuel material for brewing recipe for custom item: " + key);
                    ItemStack fuel = new ItemStack(fuelMaterial);


                    ItemStack recipient;
                    String recipientName = ReceipeSection.getString("recipient");
                    if(recipientName == null)
                        recipient = new ItemStack(Material.AIR);
                    Material recipientMaterial = Material.getMaterial(recipientName);
                    if(recipientMaterial == null)
                        throw new IllegalArgumentException("Invalid recipient material for brewing recipe for custom item: " + key);
                    recipient = new ItemStack(recipientMaterial);

                    ItemStack ingredient_after;
                    String ingredient_after_brewing = ReceipeSection.getString("ingredient_after_brewing");
                    if(ingredient_after_brewing == null)
                        ingredient_after = new ItemStack(Material.AIR);
                    else{
                        Material ingredient_after_material = Material.getMaterial(ingredient_after_brewing);
                        if(ingredient_after_material == null)
                            throw new IllegalArgumentException("Invalid ingredient after brewing material for brewing recipe for custom item: " + key);
                        ingredient_after = new ItemStack(ingredient_after_material);
                    }


                    ItemStack finalRecipient = recipient;
                    BrewingRecipe brewingRecipe = new BrewingRecipe(ingredient,fuel, inventory -> {
                        inventory.setIngredient(ingredient_after);
                        for (int i = 0; i < 3; i++) {
                            ItemStack currentItem = inventory.getItem(i);
                            if(currentItem == null || currentItem.getType() == Material.AIR){
                                if(finalRecipient.getType() == Material.AIR){
                                    inventory.setItem(i, customItemStack);
                                }
                                continue;
                            }
                            if(currentItem.getType() == finalRecipient.getType()){
                                inventory.setItem(i,customItemStack);
                            }
                        }
                    },false,1,0);
                    registerBrewing(brewingRecipe);
                }
            }

            // Process consumable section
            ConfigurationSection consumableSection = itemSection.getConfigurationSection("consumable");
            if (consumableSection != null) {
                int hunger = consumableSection.getInt("hunger");
                int saturation = consumableSection.getInt("saturation");
                List<PotionEffect> effects = new ArrayList<>();

                List<Map<?, ?>> effectsList = consumableSection.getMapList("effects");
                for (Map<?, ?> effectMap : effectsList) {
                    String effectTypeStr = (String) effectMap.get("type");
                    PotionEffectType effectType = PotionEffectType.getByName(effectTypeStr);
                    int duration = (int) effectMap.get("duration") * 20;
                    int amplifier = (int) effectMap.get("amplifier");

                    if (effectType != null) {
                        effects.add(new PotionEffect(effectType, duration, amplifier));
                    }
                }

                boolean removeOnConsume = consumableSection.getBoolean("remove_on_consume");
                String itemGivenWhenConsumed = consumableSection.getString("give_item_on_consume.item_given_when_consumed");

                customItem.addBehavior(OnConsume.class, new ConsumeBehavior(hunger, saturation, effects, removeOnConsume, itemGivenWhenConsumed));
            }

            registerItem(customItem);
            System.out.printf("Registered custom item: %s%n", key);
        }

        pluginLogger.info("Custom items loaded successfully.");
    }

    private static ItemStack createCustomItemStack(@NotNull Material material, @NotNull String translatableName, Integer customModelData) {
        ItemStack itemStack = new ItemStack(material);
        Component name = Component.translatable(translatableName)
                .style(style -> style.decoration(TextDecoration.ITALIC, false));

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(name);
        itemMeta.setCustomModelData(customModelData);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}