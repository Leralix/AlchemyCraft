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
    PLUGIN_STRING,
    HASHMAP_ERROR,
    WATERMELON_BLOOD,
    GOLDEN_BEETROOT,
    GOLDEN_SOUP,
    ZOMBIE_LEG,
    ZOMBIE_BROTH,
    ZOMBIE_ELIXIR,
    ZOMBIE_GRATIN,
    PIG_TROTTER,
    SKELETON_FLESH,
    GOLEM_HEART,
    GROWTH_POWDER,
    CONSUMABLE_GUNPOWDER,
    FUNGAL_EXPLOSIVE,
    MUSH_BOOM,
    SALT,
    HERB_MIXTURE,
    INHALING_INCENSE,
    STRONG_INHALING_INCENSE,
    HIDDEN_INCENSE,
    NIGHT_PEARL,
    MAGIC_SALAD,
    SUSPICIOUS_BALL,
    PHOENIX_FEATHER,
    ;


    private static final Map<Lang, String> translations = new HashMap<>();

    public static void loadTranslations(String filename) {

        File langFolder = new File(AlchemyCraft.getPlugin().getDataFolder(), "lang");

        if (!langFolder.exists()) {
            if(!langFolder.mkdir()){
                AlchemyCraft.getPluginLogger().warning("Could not create lang folder");
            }
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
        if(translation != null) {
            return translation;
        }
        return null;
    }

    public String getTranslation(Object... placeholders) {
        String translation = translations.get(this);
        if (translation != null) {
            //translation = ChatColor.translateAlternateColorCodes('§', translation);
            for (int i = 0; i < placeholders.length; i++) {
                translation = translation.replace("{" + i + "}", placeholders[i].toString());
            }
        }
        return translation;
    }


}