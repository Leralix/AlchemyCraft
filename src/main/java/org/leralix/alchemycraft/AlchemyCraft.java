package org.leralix.alchemycraft;


import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.Item.GoldenBeetroot;
import org.leralix.alchemycraft.Items.Item.ZombieBroth;
import org.leralix.alchemycraft.Items.Item.ZombieLeg;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.Lang.Lang;
import org.leralix.alchemycraft.Listeners.EntityDeathListener;
import org.leralix.alchemycraft.Listeners.PlayerJoinListener;
import org.leralix.alchemycraft.Utils.ConfigUtil;
import org.leralix.alchemycraft.commands.CommandManager;
import org.leralix.alchemycraft.commands.DebugCommandManager;

import java.util.Objects;
import java.util.logging.Logger;


public final class AlchemyCraft extends JavaPlugin {

    private static AlchemyCraft plugin;
    static Logger logger;
    private static ItemManager itemManager;
    private static DropManager dropManager;

    @Override
    public void onEnable() {

        plugin = this;
        logger = this.getLogger();


        logger.info("------------------Alchemy Craft--------------------");

        logger.info("[TaN] -Loading Lang");
        ConfigUtil.saveResource("lang.yml");
        ConfigUtil.loadCustomConfig("lang.yml");

        String lang = ConfigUtil.getCustomConfig("lang.yml").getString("language");


        logger.info(lang);
        Lang.loadTranslations(lang);
        logger.info(Lang.LANGUAGE_SUCCESSFULLY_LOADED.getTranslation());




        Objects.requireNonNull(getCommand("alch")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("alchdebug")).setExecutor(new DebugCommandManager());



        itemManager = new ItemManager(this);
        itemManager.registerItem(new GoldenBeetroot(ItemKey.GoldenBeetroot));
        itemManager.registerItem(new ZombieLeg(ItemKey.ZombieLeg));
        itemManager.registerItem(new ZombieBroth(ItemKey.ZombieBroth));

        itemManager.applyRecipes();

        dropManager = new DropManager();

        RegisterEvents();


        logger.info("------------------Alchemy Craft--------------------");


    }

    private void RegisterEvents() {
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

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
