package org.m9mx.sydkyn.content.curios;

import java.util.List;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;

/**
 * Curios slots are datapack JSON, so this registry feeds the data provider instead of a
 * DeferredRegister. Add a slot here, run {@code gradlew runData}, done.
 */
public final class CuriosSlotRegistry {

    public static final List<String> ALL = List.of(WarpstoneSlot.ID);

    private CuriosSlotRegistry() {
    }

    static void generate(SydkynCuriosDataProvider provider) {
        provider.createSlot(WarpstoneSlot.ID)
                .size(1)
                .order(WarpstoneSlot.ORDER)
                .icon(WarpstoneSlot.ICON)
                .addCondition(WarpstoneSlot.condition(AccessorySystems.CURIOS));

        // Curios hands slots to no entity by default.
        provider.createEntities("player")
                .addPlayer()
                .addSlots(WarpstoneSlot.ID)
                .addCondition(WarpstoneSlot.condition(AccessorySystems.CURIOS));
    }
}
