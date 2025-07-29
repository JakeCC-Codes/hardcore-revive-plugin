package net.jakeccz.hrp.listener;

import net.jakeccz.hrp.HardcoreRevivePlugin;
import net.jakeccz.hrp.action.ReviveManager;
import net.jakeccz.hrp.util.HardcoreRevivePluginUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EntityPlace implements Listener {
    private final Map<EntityType, Consumer<BlockPlaceEvent>> entityActions = new HashMap();

    public EntityPlace() {

    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.PLAYER_HEAD) {
            if (event.getItemInHand().getItemMeta() instanceof SkullMeta meta) {
                if (meta.getOwningPlayer() instanceof OfflinePlayer skullOwner) {
                    ReviveManager.tryRevivePlayer(block.getWorld(), block.getLocation(), skullOwner.getPlayer(), event.getPlayer());
                }
            }
        }
    }
}
