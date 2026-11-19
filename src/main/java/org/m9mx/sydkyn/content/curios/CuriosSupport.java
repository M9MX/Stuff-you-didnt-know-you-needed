package org.m9mx.sydkyn.content.curios;

import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.m9mx.sydkyn.helpers.AccessorySupport;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

/** Loaded only while Curios is the active system. */
public final class CuriosSupport implements AccessorySupport {

    public static final CuriosSupport INSTANCE = new CuriosSupport();

    private CuriosSupport() {
    }

    @Override
    public String id() {
        return AccessorySystems.CURIOS;
    }

    @Override
    public Optional<ItemStack> equipped(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .flatMap(inventory -> inventory.findCurio(WarpstoneSlot.ID, 0))
                .map(SlotResult::stack)
                .filter(stack -> !stack.isEmpty());
    }
}
