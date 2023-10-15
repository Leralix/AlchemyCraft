package org.leralix.alchemycraft.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.leralix.alchemycraft.AlchemyCraft;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {

    private static final Map<String, FileConfiguration> configs = new HashMap<>();

    public static FileConfiguration getCustomConfig(String fileName) {
        return configs.get(fileName);
    }

    public static void saveResource(String fileName) {
        File file = new File(AlchemyCraft.getPlugin().getDataFolder(),fileName);
        if (!file.exists()) {
            AlchemyCraft.getPlugin().saveResource(fileName, false);
        }
    }

    public static void loadCustomConfig(String fileName) {

        File configFile = new File(AlchemyCraft.getPlugin().getDataFolder(), fileName);
        if (!configFile.exists()) {
            AlchemyCraft.getPluginLogger().severe(fileName + " does not exist!");
            return;
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        configs.put(fileName, config);
    }




}
