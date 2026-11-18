package org.m9mx.sydkyn.mod.waystones;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public final class WarpstoneActivation {

    private WarpstoneActivation() {
    }

    public static void activate(ServerPlayer player) {
        if (!WarpstoneSlot.available()) {
            return;
        }

        Optional<ItemStack> equipped = CurioWarpstone.equipped(player);
        if (equipped.isEmpty()) {
            player.displayClientMessage(Component.translatable("message.sydkyn.no_warpstone"), true);
            return;
        }

        // use() would consume from the hand; this is the branch that opens the menu after a normal use.
        ItemStack stack = equipped.get();
        stack.getItem().finishUsingItem(stack, player.serverLevel(), player);
    }
}
