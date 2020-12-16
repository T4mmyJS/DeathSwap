package dev.tomstar.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SwapRunnable extends BukkitRunnable {

    private int time;
    private final DeathSwap plugin;
    private boolean playing;

    public SwapRunnable(DeathSwap plugin) {
        this.plugin = plugin;
        stop();
    }

    @Override
    public void run() {
        if (time == 100) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c5 seconds until death swap!"));
        }

        if (time == 80) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c4 seconds until death swap!"));
        }

        if (time == 60) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c3 seconds until death swap!"));
        }

        if (time == 40) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c2 seconds until death swap!"));
        }

        if (time == 20) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c1 seconds until death swap!"));
        }

        if (time == 0) {
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&lDEATH SWAP"));

            List<Location> locations = new ArrayList<>();
            for (Player player : plugin.getServer().getOnlinePlayers()) locations.add(player.getLocation());

            Location location = locations.get(0);
            locations.remove(0);
            locations.add(location);

            for (int i = 0; i < locations.size(); i++) {
                ((Player) plugin.getServer().getOnlinePlayers().toArray()[i]).teleport(locations.get(i));
            }

            this.time = plugin.getTickDelay();
        }

        time -= 1;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void stop() {
        this.time = plugin.getTickDelay();
        this.playing = false;

        plugin.getServer().getScheduler().cancelTasks(plugin);
    }

    public void start() {
        this.time = plugin.getTickDelay();
        this.playing = true;
    }
}
