package org.m9mx.sydkyn.mod.waystones;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.m9mx.sydkyn.Sydkyn;

/** No data: the server reads the player's own curio. */
public record ActivateWarpstonePayload() implements CustomPacketPayload {

    public static final Type<ActivateWarpstonePayload> TYPE =
            new Type<>(Sydkyn.id("activate_warpstone"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ActivateWarpstonePayload> STREAM_CODEC =
            StreamCodec.unit(new ActivateWarpstonePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ActivateWarpstonePayload payload, IPayloadContext context) {
        if (context.player() instanceof ServerPlayer player) {
            context.enqueueWork(() -> WarpstoneActivation.activate(player));
        }
    }
}
