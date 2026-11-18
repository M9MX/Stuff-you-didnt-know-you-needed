package org.m9mx.sydkyn.config;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class SydkynConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DEBUG_LOGGING = BUILDER
            .comment("Write extra diagnostic messages to the log.")
            .define("debugLogging", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean debugLogging;

    private SydkynConfig() {
    }

    public static void onLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            debugLogging = DEBUG_LOGGING.get();
        }
    }
}
