package org.leralix.alchemycraft;

import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.Drops.DropManager;
import org.leralix.alchemycraft.Items.Item.TestBoat;
import org.leralix.alchemycraft.Items.Item.TestBoat2;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;
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

        Objects.requireNonNull(getCommand("alch")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("alchdebug")).setExecutor(new DebugCommandManager());


        logger.info("partie de test");

        itemManager = new ItemManager(this);
        itemManager.registerItem(new TestBoat(ItemKey.testBoat));
        itemManager.registerItem(new TestBoat2(ItemKey.testBoat2));
        itemManager.applyRecipes();

        dropManager = new DropManager();

        logger.info("fin de test");
        logger.info("------------------Alchemy Craft--------------------");

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
