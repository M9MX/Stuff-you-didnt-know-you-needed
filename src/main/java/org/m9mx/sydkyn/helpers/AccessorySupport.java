package org.m9mx.sydkyn.helpers;

import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/** One accessory mod, reduced to the two things the warpstone needs: is it here, and what is worn. */
public interface AccessorySupport {

    String id();

    Optional<ItemStack> equipped(LivingEntity entity);
}
