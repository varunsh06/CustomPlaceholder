/**
 * ReloadCommand.java 2018-12-31
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.custom.placeholders.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.nyanguymf.custom.placeholders.CustomPlaceholders;
import me.nyanguymf.custom.placeholders.managers.MessagesManager;
import me.nyanguymf.custom.placeholders.managers.PlaceholderManager;
import me.nyanguymf.custom.placeholders.utils.StringUtils;

/**
 * @author nyanguymf
 *
 */
public class ReloadCommand implements CommandExecutor {
    private static CustomPlaceholders plugin;

    private static MessagesManager mm;

    public ReloadCommand() {
        plugin  = CustomPlaceholders.getInstance();
        mm      = plugin.getMessagesManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        long start = System.currentTimeMillis();

        if (!sender.hasPermission("customplaceholders.reload")) {
            String message = mm.getMessage("no-permission");
            sender.sendMessage(StringUtils.replaceVarColored(message, label));
            return true;
        }

        plugin.reloadConfig();
        mm.reload();
        PlaceholderManager.reload();

        String  message = mm.getMessage("reload");
        long    end     = System.currentTimeMillis() - start;

        sender.sendMessage(StringUtils.replaceVarColored(message, String.valueOf(end)));

        return true;
    }
}
