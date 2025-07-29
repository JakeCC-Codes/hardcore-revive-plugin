package net.jakeccz.hrp.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class HardcoreRevivePluginUtil {
    public static final String LORE_TEXT = "Used to revive this player";
    public static final List<String> LORE = List.of(ChatColor.translateAlternateColorCodes('&', LORE_TEXT));
    public static final String DEATH_COORDS_TEXT = "Death Coordinates: [{y} {x} {z}]\nDimension: {dTypeName}";

    public HardcoreRevivePluginUtil() {
    }

    private static void nonNullable(Object o, String name) {
        if (o == null) {
            throw new NullPointerException(name + " should not be null!");
        }
    }

    public static ItemStack createSkullWithName(String name) {
        ItemStack skullHead = new ItemStack(Material.PLAYER_HEAD);
        nonNullable(skullHead, "item");
        nonNullable(name, "name");
        SkullMeta meta = (SkullMeta)skullHead.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
        meta.setLore(LORE);
        skullHead.setItemMeta(meta);
        return skullHead;
    }

    public static ItemStack createSkullWithBase64(String base64, UUID uuid) {
        ItemStack skullHead = new ItemStack(Material.PLAYER_HEAD);
        nonNullable(skullHead, "item");
        nonNullable(base64, "base64");
        ItemMeta var4 = skullHead.getItemMeta();
        if (var4 instanceof SkullMeta meta) {
            try {
                String json = new String(Base64.getDecoder().decode(base64));
                JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
                String textureUrl = jsonObject.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
                PlayerProfile profile = Bukkit.createPlayerProfile(uuid);
                PlayerTextures textures = profile.getTextures();
                textures.setSkin(new URL(textureUrl));
                profile.setTextures(textures);
                meta.setOwnerProfile(profile);
                meta.setLore(LORE);
                skullHead.setItemMeta(meta);
            } catch (Exception var9) {
                Exception e = var9;
                e.printStackTrace();
            }

            return skullHead;
        }
        return null;
    }
}
