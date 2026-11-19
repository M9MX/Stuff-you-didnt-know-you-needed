package org.m9mx.sydkyn.config;

import java.util.List;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.m9mx.sydkyn.helpers.AccessorySystems;

public final class SydkynConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DEBUG_LOGGING = BUILDER
            .comment("Write extra diagnostic messages to the log.")
            .define("debugLogging", false);

    private static final ModConfigSpec.ConfigValue<List<? extends String>> SLOT_PRIORITY = BUILDER
            .comment("Which accessory mod provides the warpstone slot, first installed one wins.",
                    "Entries: " + String.join(", ", AccessorySystems.ALL),
                    "Restart the game after changing.")
            .defineListAllowEmpty("slotPriority", AccessorySystems.ALL, () -> AccessorySystems.CURIOS,
                    entry -> entry instanceof String system && AccessorySystems.ALL.contains(system));

    private static final ModConfigSpec.ConfigValue<List<? extends String>> JOIN_MESSAGES = BUILDER
            .comment("Extra join messages. Format: name|#RRGGBB|message.",
                    "Full placeholder list: https://github.com/M9MX/Stuff-you-didnt-know-you-needed/blob/master/docs/PLACEHOLDERS.md")
            .defineListAllowEmpty("joinMessages",
                    List.of("Notch|#FFD700|%playername% sold Minecraft for $2.5 billion and spent it all on a god complex."),
                    () -> "", entry -> entry instanceof String);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean debugLogging;
    private static List<String> slotPriority = AccessorySystems.ALL;
    private static List<String> joinMessages = List.of();

    private SydkynConfig() {
    }

    public static List<String> slotPriority() {
        return slotPriority;
    }

    public static List<String> joinMessages() {
        return joinMessages;
    }

    public static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            debugLogging = DEBUG_LOGGING.get();
            slotPriority = List.copyOf(SLOT_PRIORITY.get());
            joinMessages = List.copyOf(JOIN_MESSAGES.get());
        }
    }
}
