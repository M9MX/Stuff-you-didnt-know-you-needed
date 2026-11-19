package org.m9mx.sydkyn.helpers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.conditions.ICondition;

/**
 * Datapack condition: true when {@code system} is the one picked by {@code slotPriority}. Lets the
 * generated slot files follow the config instead of always existing for every accessory mod.
 */
public record SlotSystemCondition(String system) implements ICondition {

    public static final MapCodec<SlotSystemCondition> CODEC =
            Codec.STRING.fieldOf("system").xmap(SlotSystemCondition::new, SlotSystemCondition::system);

    @Override
    public boolean test(IContext context) {
        return AccessorySystems.isActive(system);
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
