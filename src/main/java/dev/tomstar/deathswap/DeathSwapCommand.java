package dev.tomstar.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeathSwapCommand implements CommandExecutor, TabCompleter {

    private final DeathSwap plugin;

    public DeathSwapCommand(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!commandSender.hasPermission("deathswap.admin")) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission to execute this command!"));
            return true;
        }

        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage: /deathswap <start|stop|reload>"));
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            if (plugin.getRunnable().isPlaying()) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDeath swap is already playing! Use &6/deathswap stop &cinstead!"));
                return true;
            }

            plugin.getServer().getScheduler().runTaskTimer(plugin, plugin.getRunnable(), 0, 1);
            plugin.getRunnable().start();
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Started the death swap!"));
            return true;
        }

        if (args[0].equalsIgnoreCase("stop")) {
            plugin.getRunnable().stop();
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cStopped the death swap!"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getRunnable().stop();
            plugin.reloadTickDelay();
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Configuration reloaded!"));
            return true;
        }

        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Usage: /deathswap <start|stop|reload>"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (!commandSender.hasPermission("deathswap.admin")) {
            return Collections.emptyList();
        }

        if (args.length == 0 || args.length == 1) {
            return Arrays.asList("start", "stop", "reload");
        }

        return Collections.emptyList();
    }

}
