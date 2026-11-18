package org.m9mx.sydkyn.content.controls;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

/** Turns {@link Keybind}s into Controls entries and dispatches their presses. */
public final class KeybindRegistry {

    public static final String CATEGORY = "key.categories.sydkyn";

    private static final Map<KeyMapping, Keybind> BOUND = new LinkedHashMap<>();

    private KeybindRegistry() {
    }

    public static void registerAll(Collection<Keybind> binds, RegisterKeyMappingsEvent event) {
        BOUND.clear();
        for (Keybind bind : binds) {
            if (!bind.enabled().getAsBoolean()) {
                continue;
            }
            KeyMapping mapping = new KeyMapping(
                    bind.translationKey(),
                    bind.defaultKey().getType(),
                    bind.defaultKey().getValue(),
                    CATEGORY
            );
            event.register(mapping);
            BOUND.put(mapping, bind);
        }
    }

    public static void tick() {
        BOUND.forEach((mapping, bind) -> {
            while (mapping.consumeClick()) {
                bind.action().run();
            }
        });
    }
}
