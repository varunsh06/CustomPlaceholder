/**
 * MessagesManager.java 2018-12-15

 * @author nyanguymf

 * @version 1.0
 */
package me.nyanguymf.custom.placeholders.managers;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import me.nyanguymf.custom.placeholders.CustomPlaceholders;
import me.nyanguymf.custom.placeholders.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
public class MessagesManager {
    private YamlConfiguration   config;

    /**
     * Plugin's main class instance;
     */
    private CustomPlaceholders  plugin;

    public MessagesManager() {
        plugin = CustomPlaceholders.getInstance();

        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);

       plugin.saveResource(configFile.getName(), false);
    }

    /**
     * Gets message from configuration file.
     *
     * @param path Path to message in configuration file
     *
     * @return Message from configuration file.
     */
    public String getMessage(String path) {
        return config.getString(path).replace("\\n", "\n");
    }

    /**
     * Same as {@link #getMessage(String)}, but with all
     * Bukkit colors replaced.
     */
    public String getColoredMessage(String path) {
        return StringUtils.replaceColors(getMessage(path));
    }

    /**
     * Reload messages configuration.
     */
    public void reload() {
        File    configFile  = new File(plugin.getDataFolder(), "messages.yml");
                config      = YamlConfiguration.loadConfiguration(configFile);
    }
}
