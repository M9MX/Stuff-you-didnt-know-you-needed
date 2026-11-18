package org.m9mx.sydkyn.content.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.m9mx.sydkyn.mod.waystones.ActivateWarpstonePayload;

/** One line per payload. Handlers do their own availability checks. */
public final class SydkynNetwork {

    private static final String VERSION = "1";

    private SydkynNetwork() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(VERSION);
        registrar.playToServer(
                ActivateWarpstonePayload.TYPE,
                ActivateWarpstonePayload.STREAM_CODEC,
                ActivateWarpstonePayload::handle
        );
    }
}
