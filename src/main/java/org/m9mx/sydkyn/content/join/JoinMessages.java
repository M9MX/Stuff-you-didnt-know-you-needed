package org.m9mx.sydkyn.content.join;

import java.util.Locale;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.config.SydkynConfig;
import org.m9mx.sydkyn.helpers.Placeholders;

@EventBusSubscriber(modid = Sydkyn.MODID)
public final class JoinMessages {

    private record Entry(String color, String text) {
    }

    private static final Entry DEFAULT = new Entry(
            "#FFFF55", "%playername% joined the game.");

    private static final Map<String, Entry> BUILT_IN = Map.of(
            "m9mx", new Entry("#FFAA00", "%playername% is back. %playtime% played. Still no grass touched."),
            "reoptimized", new Entry("#FF5555", "%playername% finally logged on. Must have run out of real-life things to do."),
            "norb0", new Entry("#AA00FF", "So when are you going to finish Hogwarts, %playername%?")
    );

    private JoinMessages() {
    }

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        Entry entry = find(player.getGameProfile().getName());
        player.server.getPlayerList().broadcastSystemMessage(format(entry, player), false);
    }

    private static Entry find(String playerName) {
        Entry entry = BUILT_IN.get(playerName.toLowerCase(Locale.ROOT));

        for (String raw : SydkynConfig.joinMessages()) {
            String[] parts = raw.split("\\|", 3);
            if (parts.length != 3 || !parts[0].trim().equalsIgnoreCase(playerName)) {
                continue;
            }
            entry = new Entry(parts[1].trim(), parts[2].trim());
        }

        return entry == null ? DEFAULT : entry;
    }

    private static Component format(Entry entry, ServerPlayer player) {
        return Component.literal(Placeholders.apply(entry.text(), player)).withStyle(style(entry.color()));
    }

    private static Style style(String color) {
        if (color.startsWith("#") && color.length() == 7) {
            try {
                return Style.EMPTY.withColor(TextColor.fromRgb(Integer.parseInt(color.substring(1), 16)));
            } catch (NumberFormatException ignored) {
            }
        }

        ChatFormatting formatting = ChatFormatting.getByName(color);
        return formatting == null ? Style.EMPTY : Style.EMPTY.withColor(formatting);
    }
}
