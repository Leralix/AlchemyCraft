package org.leralix.alchemycraft;

import org.bukkit.plugin.java.JavaPlugin;
import org.leralix.alchemycraft.CustomCrafts.CustomCrafts;
import org.leralix.alchemycraft.commands.CommandManager;
import org.leralix.alchemycraft.commands.DebugCommandManager;

import java.util.Objects;
import java.util.logging.Logger;

public final class AlchemyCraft extends JavaPlugin {

    private static AlchemyCraft plugin;
    static Logger logger;
    @Override
    public void onEnable() {
        plugin = this;
        logger = this.getLogger();

        Objects.requireNonNull(getCommand("tan")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("tandebug")).setExecutor(new DebugCommandManager());

        CustomCrafts.createCustomRecipe("custom_sword_test", CustomCrafts.getCustomItem());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AlchemyCraft getPlugin() {
        return plugin;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

}
