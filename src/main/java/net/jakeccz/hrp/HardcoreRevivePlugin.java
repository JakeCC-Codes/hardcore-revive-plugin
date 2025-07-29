package net.jakeccz.hrp;

import net.jakeccz.hrp.listener.EntityDeath;
import net.jakeccz.hrp.listener.EntityPlace;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Tag;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public final class HardcoreRevivePlugin extends JavaPlugin {
    public static final String PLUGIN_ID = "hardcorerplug";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);

    public static Set<Material> SOUL_SAND_BLOCKS = Tag.WITHER_SUMMON_BASE_BLOCKS.getValues();
    public static Set<Material> FLOWER_BLOCKS = Tag.FLOWERS.getValues();
    public static Set<Material> ORE_BLOCKS = Tag.BEACON_BASE_BLOCKS.getValues();
    public static Set<Material> FENCE_BLOCKS = Tag.FENCES.getValues();
    public static Set<Material> STAIR_BLOCKS = Tag.STAIRS.getValues();

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getServer().isHardcore())
            LOGGER.warn("Hardcore Mode is currently disabled, certain plugin functionalities will fail to run.");
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new EntityDeath(), this);
        pluginManager.registerEvents(new EntityPlace(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
