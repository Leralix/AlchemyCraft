package org.leralix.alchemycraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class AlchemyCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AlchemyCraft getPlugin(){
        return this;
    }
}
