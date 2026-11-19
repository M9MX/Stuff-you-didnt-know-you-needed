package org.m9mx.sydkyn.content.theaether;

import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.m9mx.sydkyn.helpers.AccessorySupport;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.content.accessories.AccessoriesSupport;

/**
 * The Aether nests its own copy of Accessories, so worn items are read the same way; only the slot
 * data differs (the warp stone goes into the Aether's generic accessory slot via a tag).
 */
public final class AetherSupport implements AccessorySupport {

    public static final AetherSupport INSTANCE = new AetherSupport();

    private AetherSupport() {
    }

    @Override
    public String id() {
        return AccessorySystems.AETHER;
    }

    @Override
    public Optional<ItemStack> equipped(LivingEntity entity) {
        return AccessoriesSupport.INSTANCE.equipped(entity);
    }
}
