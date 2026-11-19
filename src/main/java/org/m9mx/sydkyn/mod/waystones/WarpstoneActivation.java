package org.m9mx.sydkyn.mod.waystones;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.m9mx.sydkyn.helpers.AccessorySupport;

public final class WarpstoneActivation {

    private WarpstoneActivation() {
    }

    public static void activate(ServerPlayer player) {
        if (!WarpstoneSlot.available()) {
            return;
        }

        AccessorySupport support = WarpstoneSlot.system();
        Optional<ItemStack> equipped = support.equipped(player);
        if (equipped.isEmpty()) {
            player.displayClientMessage(Component.translatable("message.sydkyn.no_warpstone"), true);
            return;
        }

        ItemStack stack = equipped.get();
        if (stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage()) {
            player.displayClientMessage(Component.translatable("message.sydkyn.broken_warpstone"), true);
            return;
        }

        // use() would consume from the hand; this is the branch that opens the menu after a normal use.
        stack.getItem().finishUsingItem(stack, player.serverLevel(), player);
    }
}
