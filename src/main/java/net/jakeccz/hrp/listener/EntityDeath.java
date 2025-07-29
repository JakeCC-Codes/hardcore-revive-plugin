package net.jakeccz.hrp.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import net.jakeccz.hrp.HardcoreRevivePlugin;
import net.jakeccz.hrp.util.HardcoreRevivePluginUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {
    //private final YamlDocument config = HeadDrop.getInstance().getConfiguration();
    private final Map<EntityType, Consumer<EntityDeathEvent>> entityActions = new HashMap();

    public EntityDeath() {
        this.entityActions.put(EntityType.PLAYER, (event) -> {
            ItemStack skull = HardcoreRevivePluginUtil.createSkullWithName(event.getEntity().getName());
            event.getDrops().add(skull);
            Location deathPos = event.getEntity().getEyeLocation();
            String dimensionName = event.getEntity().getWorld().getKey().asString();
            String deathMessage = HardcoreRevivePluginUtil.DEATH_COORDS_TEXT
                    .replace("{x}", String.valueOf(deathPos.getBlockX()))
                    .replace("{y}", String.valueOf(deathPos.getBlockY()))
                    .replace("{z}", String.valueOf(deathPos.getBlockZ()))
                    .replace("{dTypeName}", dimensionName);
            event.getEntity().sendMessage(deathMessage);
        });
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void entityDropHeadEvent(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Consumer<EntityDeathEvent> action = (Consumer) this.entityActions.get(event.getEntityType());
        if (action != null) {
            if (entity.getWorld().isHardcore()) {
                action.accept(event);
            }
        }
    }

}
