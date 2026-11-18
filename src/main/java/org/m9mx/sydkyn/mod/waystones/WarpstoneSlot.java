package org.m9mx.sydkyn.mod.waystones;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.m9mx.sydkyn.Sydkyn;

/** The warpstone slot: only exists when both Curios and Waystones are installed. */
public final class WarpstoneSlot {

    public static final String ID = "warpstone";

    /** Items the slot accepts, filled by data/curios/tags/item/warpstone.json. */
    public static final TagKey<Item> ITEM_TAG =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", ID));

    public static final ResourceLocation ICON = Sydkyn.id("slot/" + ID);

    private WarpstoneSlot() {
    }

    public static boolean available() {
        return ModList.get().isLoaded("curios") && ModList.get().isLoaded("waystones");
    }

    /** Curios itself never loads the file without Curios, so Waystones is the condition to add. */
    public static ICondition condition() {
        return new ModLoadedCondition("waystones");
    }
}
