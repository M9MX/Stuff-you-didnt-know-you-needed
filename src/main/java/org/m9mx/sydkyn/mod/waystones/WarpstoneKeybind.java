package org.m9mx.sydkyn.mod.waystones;

import com.mojang.blaze3d.platform.InputConstants;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import org.m9mx.sydkyn.content.controls.Keybind;

public final class WarpstoneKeybind {

    private WarpstoneKeybind() {
    }

    public static Keybind create() {
        return new Keybind(
                "warpstone",
                InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_V),
                WarpstoneSlot::available,
                () -> PacketDistributor.sendToServer(new ActivateWarpstonePayload())
        );
    }
}
