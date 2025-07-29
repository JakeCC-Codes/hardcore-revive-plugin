package net.jakeccz.hrp;

import net.jakeccz.hrp.listener.EntityDeath;
import net.jakeccz.hrp.listener.EntityPlace;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HardcoreRevivePlugin extends JavaPlugin {
    public static final String PLUGIN_ID = "hardcorerplug";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);

    @Override
    public void onEnable() {
        // Plugin startup logic
        LOGGER.info("Hello World!");
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new EntityDeath(), this);
        pluginManager.registerEvents(new EntityPlace(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
