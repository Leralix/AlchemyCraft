package org.leralix.alchemycraft;



import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Lang.Lang;
import org.leralix.alchemycraft.Listeners.*;
import org.leralix.alchemycraft.Storage.CraftManager;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.commands.DebugCommandManager;
import org.leralix.alchemycraft.Storage.DropManager;

import java.util.ArrayList;
import java.util.List;
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

        ConfigUtil.saveResource("items.yml");
        ConfigUtil.loadCustomConfig("items.yml");
        ConfigUtil.saveResource("drops.yml");
        ConfigUtil.loadCustomConfig("drops.yml");
        ConfigUtil.saveResource("crafts.yml");
        ConfigUtil.loadCustomConfig("crafts.yml");


        String lang = ConfigUtil.getCustomConfig("lang.yml").getString("language");
        Lang.loadTranslations(lang);

        logger.info("[Alc] -Loading Commands");
        getCommand("alchdebug").setExecutor(new DebugCommandManager());


        logger.info("[Alc] -Loading items, crafts & events");

        ItemManager.registerItems();
        DropManager.registerDrops();
        CraftManager.registerCrafts();
        CraftManager.registerFurnaceCraft();

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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void RegisterEvents() {
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BrewPotionEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerConsumeItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerEffectListener(), this);
    }
    public static AlchemyCraft getPlugin() {
        return plugin;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

}
