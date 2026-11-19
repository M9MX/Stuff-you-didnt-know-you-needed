package org.m9mx.sydkyn.content.accessories;

import io.wispforest.accessories.api.AccessoriesAPI;
import io.wispforest.accessories.api.slot.SlotBasedPredicate;
import net.minecraft.world.item.ItemStack;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;

/** Registers the validator our Accessories slot JSON points at. Only called with Accessories loaded. */
public final class AccessoryPredicates {

    public static final String VALIDATOR = "sydkyn:" + WarpstoneSlot.ID;

    private AccessoryPredicates() {
    }

    public static void register() {
        AccessoriesAPI.registerPredicate(
                Sydkyn.id(WarpstoneSlot.ID),
                SlotBasedPredicate.ofItem(item -> new ItemStack(item).is(WarpstoneSlot.ITEM_TAG))
        );
    }
}
