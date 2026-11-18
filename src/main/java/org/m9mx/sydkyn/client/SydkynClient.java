package org.m9mx.sydkyn.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.content.controls.KeybindRegistry;
import org.m9mx.sydkyn.content.controls.SydkynControls;

@EventBusSubscriber(modid = Sydkyn.MODID, value = Dist.CLIENT)
public final class SydkynClient {

    private SydkynClient() {
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        KeybindRegistry.registerAll(SydkynControls.binds(), event);
    }

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Post event) {
        KeybindRegistry.tick();
    }
}
