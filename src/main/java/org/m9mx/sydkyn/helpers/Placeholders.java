package org.m9mx.sydkyn.helpers;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/** Placeholder registry for join messages and anything else that needs player stats. */
public final class Placeholders {

    private static final Pattern PATTERN =
            Pattern.compile("%([a-zA-Z0-9_]+)(?:\\{([^}]*)\\})?%");

    private static final Map<String, Resolver> RESOLVERS = new LinkedHashMap<>();

    static {
        register("playername", (player, argument) -> player.getGameProfile().getName());
        register("displayname", (player, argument) -> player.getDisplayName().getString());
        register("uuid", (player, argument) -> player.getUUID().toString());
        register("health", (player, argument) -> String.format(Locale.ROOT, "%.1f", player.getHealth() / 2.0));
        register("food", (player, argument) -> Integer.toString(player.getFoodData().getFoodLevel()));
        register("level", (player, argument) -> Integer.toString(player.experienceLevel));

        register("playtime", (player, argument) -> duration(value(player, Stats.PLAY_TIME)));
        register("distance", (player, argument) -> distance(value(player, Stats.WALK_ONE_CM)));

        registerBlockStats();
        registerItemStats();
        registerEntityStats();

        alias("mobkills", "mob_kills");
        alias("playerkills", "player_kills");
        alias("damagetaken", "damage_taken");
        alias("damagedealt", "damage_dealt");
        alias("fishcaught", "fish_caught");
        alias("jumps", "jump");

        register("stat", (player, argument) -> {
            ResourceLocation id = argument == null ? null : id(argument);
            if (id == null || !BuiltInRegistries.CUSTOM_STAT.containsKey(id)) {
                return "0";
            }
            return format(id.getPath(), value(player, id));
        });
        alias("customstat", "stat");
    }

    private Placeholders() {
    }

    public static String apply(String text, ServerPlayer player) {
        Matcher matcher = PATTERN.matcher(text);
        StringBuilder out = new StringBuilder();

        while (matcher.find()) {
            String replacement = resolve(matcher.group(1).toLowerCase(Locale.ROOT), player, matcher.group(2));
            matcher.appendReplacement(out,
                    Matcher.quoteReplacement(replacement == null ? matcher.group() : replacement));
        }

        matcher.appendTail(out);
        return out.toString();
    }

    private static void register(String name, Resolver resolver) {
        RESOLVERS.put(name, resolver);
    }

    private static void alias(String name, String target) {
        register(name, (player, argument) -> resolve(target, player, argument));
    }

    private static String resolve(String name, ServerPlayer player, String argument) {
        Resolver resolver = RESOLVERS.get(name);
        if (resolver != null) {
            return resolver.resolve(player, argument);
        }
        return argument == null ? customStat(player, name) : null;
    }

    private static String customStat(ServerPlayer player, String name) {
        ResourceLocation id = customStatId(name);
        return id == null ? null : format(id.getPath(), value(player, id));
    }

    private static ResourceLocation customStatId(String name) {
        ResourceLocation direct = ResourceLocation.tryParse("minecraft:" + name);
        if (direct != null && BuiltInRegistries.CUSTOM_STAT.containsKey(direct)) {
            return direct;
        }

        ResourceLocation match = null;
        for (ResourceLocation id : BuiltInRegistries.CUSTOM_STAT.keySet()) {
            if (!id.getPath().equals(name)) {
                continue;
            }
            if (match != null) {
                return null;
            }
            match = id;
        }
        return match;
    }

    private static void registerBlockStats() {
        register("blocksbroken", (player, argument) -> {
            if (argument == null) {
                return Integer.toString(total(player, Stats.BLOCK_MINED, BuiltInRegistries.BLOCK));
            }
            Block block = lookup(BuiltInRegistries.BLOCK, argument);
            return block == null ? "0" : Integer.toString(player.getStats().getValue(Stats.BLOCK_MINED.get(block)));
        });
        alias("blocksmined", "blocksbroken");
    }

    private static void registerItemStats() {
        registerItemStat("itemscrafted", Stats.ITEM_CRAFTED);
        registerItemStat("itemsused", Stats.ITEM_USED);
        registerItemStat("itemsbroken", Stats.ITEM_BROKEN);
        registerItemStat("itemspickedup", Stats.ITEM_PICKED_UP);
        registerItemStat("itemsdropped", Stats.ITEM_DROPPED);
    }

    private static void registerItemStat(String name, StatType<Item> type) {
        register(name, (player, argument) -> {
            if (argument == null) {
                return Integer.toString(total(player, type, BuiltInRegistries.ITEM));
            }
            Item item = lookup(BuiltInRegistries.ITEM, argument);
            return item == null ? "0" : Integer.toString(player.getStats().getValue(type.get(item)));
        });
    }

    private static void registerEntityStats() {
        register("entitykilled", (player, argument) -> {
            if (argument == null) {
                return Integer.toString(total(player, Stats.ENTITY_KILLED, BuiltInRegistries.ENTITY_TYPE));
            }
            EntityType<?> entityType = lookup(BuiltInRegistries.ENTITY_TYPE, argument);
            return entityType == null ? "0"
                    : Integer.toString(player.getStats().getValue(Stats.ENTITY_KILLED.get(entityType)));
        });
        register("entitykilledby", (player, argument) -> {
            if (argument == null) {
                return Integer.toString(total(player, Stats.ENTITY_KILLED_BY, BuiltInRegistries.ENTITY_TYPE));
            }
            EntityType<?> entityType = lookup(BuiltInRegistries.ENTITY_TYPE, argument);
            return entityType == null ? "0"
                    : Integer.toString(player.getStats().getValue(Stats.ENTITY_KILLED_BY.get(entityType)));
        });
    }

    private static int value(ServerPlayer player, ResourceLocation stat) {
        ResourceLocation registered = BuiltInRegistries.CUSTOM_STAT.get(stat);
        return registered == null ? 0 : player.getStats().getValue(Stats.CUSTOM.get(registered));
    }

    private static <T> int total(ServerPlayer player, StatType<T> type, Registry<T> registry) {
        return registry.stream()
                .mapToInt(entry -> player.getStats().getValue(type.get(entry)))
                .sum();
    }

    private static String format(String stat, int value) {
        if (stat.equals("play_time") || stat.equals("total_world_time")
                || stat.equals("time_since_death") || stat.equals("time_since_rest")
                || stat.equals("sneak_time")) {
            return duration(value);
        }
        if (stat.endsWith("_one_cm")) {
            return distance(value);
        }
        return Integer.toString(value);
    }

    private static String duration(int ticks) {
        long minutes = ticks / (20L * 60L);
        long days = minutes / (60L * 24L);
        long hours = minutes / 60L % 24L;
        long rest = minutes % 60L;

        if (days > 0) {
            return days + "d " + hours + "h";
        }
        if (hours > 0) {
            return hours + "h " + rest + "m";
        }
        return rest + "m";
    }

    private static String distance(int centimeters) {
        return String.format(Locale.ROOT, "%.1f km", centimeters / 100_000.0);
    }

    private static <T> T lookup(Registry<T> registry, String raw) {
        ResourceLocation id = id(raw);
        return id == null ? null : registry.getOptional(id).orElse(null);
    }

    private static ResourceLocation id(String raw) {
        return ResourceLocation.tryParse(raw.contains(":") ? raw : "minecraft:" + raw);
    }

    @FunctionalInterface
    public interface Resolver {
        String resolve(ServerPlayer player, String argument);
    }
}
