package org.m9mx.sydkyn.content.accessories;

import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.m9mx.sydkyn.helpers.AccessorySupport;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;

/** Loaded only while Accessories is the active system. */
public final class AccessoriesSupport implements AccessorySupport {

    public static final AccessoriesSupport INSTANCE = new AccessoriesSupport();

    private AccessoriesSupport() {
    }

    @Override
    public String id() {
        return AccessorySystems.ACCESSORIES;
    }

    @Override
    public Optional<ItemStack> equipped(LivingEntity entity) {
        return AccessoriesCapability.getOptionally(entity)
                .flatMap(capability -> capability.getAllEquipped().stream()
                        .map(SlotEntryReference::stack)
                        .filter(stack -> stack.is(WarpstoneSlot.ITEM_TAG))
                        .findFirst());
    }
}
