package dev.tomstar.deathswap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {

    private FileConfiguration config;
    private SwapRunnable runnable;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        runnable = new SwapRunnable(this);

        DeathSwapCommand command = new DeathSwapCommand(this);
        getCommand("deathswap").setExecutor(command);
        getCommand("deathswap").setTabCompleter(command);
    }

    @Override
    public void onDisable() {

    }

    public void reloadTickDelay() {
        config = getConfig();
    }

    public int getTickDelay() {
        return config.getInt("time");
    }

    public SwapRunnable getRunnable() {
        return runnable;
    }

}
