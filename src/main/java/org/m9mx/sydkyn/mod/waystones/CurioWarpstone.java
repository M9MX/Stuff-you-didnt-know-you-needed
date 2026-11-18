package org.m9mx.sydkyn.mod.waystones;

import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

/** Only touched after {@link WarpstoneSlot#available()}, so it is never loaded without Curios. */
final class CurioWarpstone {

    private CurioWarpstone() {
    }

    static Optional<ItemStack> equipped(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .flatMap(inventory -> inventory.findCurio(WarpstoneSlot.ID, 0))
                .map(SlotResult::stack)
                .filter(stack -> !stack.isEmpty());
    }
}
