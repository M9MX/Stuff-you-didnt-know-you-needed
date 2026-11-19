package org.m9mx.sydkyn.mod.waystones;

import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.helpers.AccessorySupport;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.helpers.SlotSystemCondition;

/** The warpstone slot. Which accessory mod provides it is decided by the slotPriority config. */
public final class WarpstoneSlot {

    public static final String ID = "warpstone";
    public static final int ORDER = 200;

    /** Items the slot accepts, filled by data/sydkyn/tags/item/warpstone.json. */
    public static final TagKey<Item> ITEM_TAG = TagKey.create(Registries.ITEM, Sydkyn.id(ID));

    public static final ResourceLocation ICON = Sydkyn.id("slot/" + ID);

    /** Accessories stitches slot icons from {@code textures/gui/slot/}. */
    public static final ResourceLocation ACCESSORIES_ICON = Sydkyn.id("gui/slot/" + ID);

    private WarpstoneSlot() {
    }

    public static AccessorySupport system() {
        return AccessorySystems.active();
    }

    public static boolean available() {
        return ModList.get().isLoaded("waystones") && system() != null;
    }

    /** One system's slot files, present only when Waystones is loaded and that system won. */
    public static ICondition condition(String system) {
        return new AndCondition(List.of(
                new ModLoadedCondition("waystones"),
                new SlotSystemCondition(system)
        ));
    }
}
