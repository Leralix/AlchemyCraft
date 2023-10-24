package org.leralix.alchemycraft;



import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.BrewAction.CustomItemBrew;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.Item.*;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Lang.Lang;
import org.leralix.alchemycraft.Listeners.EntityDeathListener;
import org.leralix.alchemycraft.Listeners.PlayerJoinListener;
import org.leralix.alchemycraft.Listeners.BrewPotionEvent;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.commands.CommandManager;
import org.leralix.alchemycraft.commands.DebugCommandManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


public final class AlchemyCraft extends JavaPlugin {

    private static AlchemyCraft plugin;
    static Logger logger;
    private static ItemManager itemManager;
    private static DropManager dropManager;

    public final List<NamespacedKey> recipeKeys = new ArrayList<>();


    @Override
    public void onEnable() {

        plugin = this;
        logger = this.getLogger();


        logger.info("------------------Alchemy Craft--------------------");

        logger.info("[Alc] -Loading Lang");
        ConfigUtil.saveResource("lang.yml");
        ConfigUtil.loadCustomConfig("lang.yml");

        String lang = ConfigUtil.getCustomConfig("lang.yml").getString("language");
        Lang.loadTranslations(lang);
        logger.info(Lang.LANGUAGE_SUCCESSFULLY_LOADED.getTranslation());

        logger.info("[Alc] -Loading Commands");
        Objects.requireNonNull(getCommand("alch")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("alchdebug")).setExecutor(new DebugCommandManager());


        logger.info("[Alc] -Loading Items & Crafts");

        itemManager = new ItemManager(this);
        itemManager.registerItem(new GoldenBeetroot());
        itemManager.registerItem(new ZombieLeg());
        itemManager.registerItem(new ZombieBroth());
        itemManager.registerItem(new SkeletonFlesh());
        itemManager.registerItem(new GolemHeart());
        itemManager.registerItem(new PigTrotter());

        CustomItemBrew _watermelonJuice = new WatermelonJuice();
        itemManager.registerItem(_watermelonJuice);
        ItemManager.registerBrewing(_watermelonJuice.getBrewRecipe());

        itemManager.registerItem(new GrowthPowder());
        itemManager.registerItem(new FungalExplosive());

        CustomItemBrew _salt = new Salt();
        itemManager.registerItem(_salt);
        ItemManager.registerBrewing(_salt.getBrewRecipe());

        itemManager.applyRecipes();

        //dropManager = new DropManager();

        RegisterEvents();
        giveALlRecipes();


        logger.info("------------------Alchemy Craft--------------------");


    }

    private void giveALlRecipes() {
        getServer().recipeIterator().forEachRemaining(recipe -> {
            if (recipe instanceof ShapelessRecipe) {
                ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
                recipeKeys.add(shapelessRecipe.getKey());
            } else if (recipe instanceof ShapedRecipe) {
                ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                recipeKeys.add(shapedRecipe.getKey());
            }
        });
    }

    private void RegisterEvents() {
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BrewPotionEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AlchemyCraft getPlugin() {
        return plugin;
    }
    public static ItemManager getItemManager() {
        return itemManager;
    }
    public static Logger getPluginLogger() {
        return logger;
    }

}
