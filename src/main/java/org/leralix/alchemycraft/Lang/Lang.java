package org.leralix.alchemycraft.Lang;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.leralix.alchemycraft.AlchemyCraft;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public enum Lang {
    WELCOME,
    LANGUAGE_SUCCESSFULLY_LOADED,
    PLUGIN_STRING;


    private static final Map<Lang, String> translations = new HashMap<>();

    public static void loadTranslations(String filename) {

        File langFolder = new File(AlchemyCraft.getPlugin().getDataFolder(), "lang");

        if (!langFolder.exists()) {
            langFolder.mkdir();
        }

        File file = new File(langFolder, filename);

        AlchemyCraft.getPlugin().saveResource("lang/" + filename, true);


        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);


        for (Lang key : Lang.values()) {

            String message = config.getString("language." + key.name());
            if (message != null) {
                translations.put(key, message);
            }
        }
        AlchemyCraft.getPluginLogger().info(LANGUAGE_SUCCESSFULLY_LOADED.getTranslation());


    }

    public String getTranslation() {
        String translation = translations.get(this);
        if (translation != null) {
            //return ChatColor.translateAlternateColorCodes('§', translation);
        }
        return null;
    }

    public String getTranslation(Object... placeholders) {
        String translation = translations.get(this);
        if (translation != null) {
            //translation = NamedTextColor.translateAlternateColorCodes('§', translation);
            for (int i = 0; i < placeholders.length; i++) {
                translation = translation.replace("{" + i + "}", placeholders[i].toString());
            }
        }
        return translation;
    }


}