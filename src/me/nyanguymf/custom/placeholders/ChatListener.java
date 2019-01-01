/**
 * ChatListener.java 2019-01-01
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.custom.placeholders;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.nyanguymf.custom.placeholders.managers.PlaceholderManager;
import me.nyanguymf.custom.placeholders.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
public class ChatListener implements Listener {
    /**
     * Plugin's main class instance;
     */
    private static CustomPlaceholders plugin;

    public ChatListener() {
        plugin = CustomPlaceholders.getInstance();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChatMessage(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }

        String format = event.getFormat();

        for (String placeholder : PlaceholderManager.getPlaceholders()) {
            if (!format.contains("{" + placeholder + "}"))
                continue;

            String  text    = PlaceholderManager.get(placeholder);
                    format  = format.replace("{" + placeholder + "}", text);
                    text    = null;
        }

        if (plugin.getConfig().getBoolean("use-colors"))
            format = StringUtils.replaceColors(format);

        event.setFormat(format);
    }
}
