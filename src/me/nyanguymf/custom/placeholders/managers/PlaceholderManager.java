/**
 
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.custom.placeholders.managers;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.configuration.ConfigurationSection;

import me.nyanguymf.custom.placeholders.CustomPlaceholders;

/**
 * Proxy pattern.
 *
 * @author nyanguymf
 */
public class PlaceholderManager {
    /**
     * Plugin's main class instance;
     */
    private static CustomPlaceholders plugin;

    /* TreeMap cause of the higher speed of the algorithm */
    /**
     * Map with placeholders, where key is placeholder and value is text.
     */
    private static Map<String, String> placeholders;

    public PlaceholderManager() {
        plugin = CustomPlaceholders.getInstance();
        reload();
    }

    /**
     * Gets placeholder's text or <tt>null</tt> if it doesn't exists.
     *
     * @param placeholder Placeholder name to get text.
     *
     * @return Placeholder's text from Map.
     */
    public static String get(String placeholder) {
        String text = placeholders.get(placeholder);

        if (text == null) {
            System.out.println("Placeholder {" + placeholder + "} doesn't exists!");
            return null;
        }

        return text;
    }

    /**
     * Returns {@link Set} of placeholders.
     *
     * @return {@link Set} with all placeholders.
     */
    public static Set<String> getPlaceholders(){
        return placeholders.keySet();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * placeholder.
     *
     * @param placeholder placeholder whose presence in this map is to be tested.
     *
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * placeholder.
     */
    public static boolean contains(String placeholder) {
        return placeholders.containsKey(placeholder);
    }

    /**
     * Load placeholders from configuration.
     */
    public static void reload() {
        /* free memory */
        placeholders = null;
        /* free Map */
        placeholders = new TreeMap<String, String>();

        ConfigurationSection config = plugin.getConfig().getConfigurationSection("placeholders");

        for (String placeholder : config.getKeys(false)) {
            placeholders.put(placeholder, config.getString(placeholder));
        }
    }
}
