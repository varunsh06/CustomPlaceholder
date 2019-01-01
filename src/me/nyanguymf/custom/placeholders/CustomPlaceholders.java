/**
 * CustomPlaceholders.java 2018-12-31
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.custom.placeholders;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import me.nyanguymf.custom.placeholders.commands.ReloadCommand;
import me.nyanguymf.custom.placeholders.managers.*;

/**
 * @author nyanguymf
 *
 */
public class CustomPlaceholders extends JavaPlugin {
    /**
     * Plugin's main class instance;
     */
    private static CustomPlaceholders instance;

    /**
     * Plugin messages manager. Takes messages from configuration
     * file.
     */
    private MessagesManager messagesManager;

    public void onEnable() {
        instance        = this;
        messagesManager = new MessagesManager();

        /* init placeholder manager */
        new PlaceholderManager();

        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            saveDefaultConfig();
        }

        regCommands();
        regListeners();
    }

    public void onDisable() {
        
    }

    /**
     * Gets instance of plugin's main class.
     *
     * @return
     */
    public static CustomPlaceholders getInstance() {
        return instance;
    }

    /**
     * Gets messages manager.
     *
     * @return Plugin's messages manager.
     */
    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    /**
     * Register commands executors.
     */
    private void regCommands() {
        getServer().getPluginCommand("customplaceholders").setExecutor(new ReloadCommand());
    }

    /**
     * Register event listeners.
     */
    private void regListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }
}
