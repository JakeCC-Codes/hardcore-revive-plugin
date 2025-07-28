package net.jakeccz.hrp.hardcoreRevivePlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class HardcoreRevivePlugin extends JavaPlugin {
    public Logger HRP_LOGGER = getLogger();
    public String HRP_PLUGINID = "hardcorerplug";

    @Override
    public void onEnable() {
        // Plugin startup logic
        HRP_LOGGER.info("Hello World!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
