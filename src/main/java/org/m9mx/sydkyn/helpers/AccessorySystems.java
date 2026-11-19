package org.m9mx.sydkyn.helpers;

import java.util.List;
import net.neoforged.fml.ModList;
import org.m9mx.sydkyn.config.SydkynConfig;
import org.m9mx.sydkyn.content.accessories.AccessoriesSupport;
import org.m9mx.sydkyn.content.curios.CuriosSupport;
import org.m9mx.sydkyn.content.theaether.AetherSupport;

/**
 * Picks the accessory system that owns the warpstone slot. The config lists them in priority order
 * and the first one that is installed wins, so a pack with all three still gets a single slot.
 */
public final class AccessorySystems {

    public static final String CURIOS = "curios";
    public static final String AETHER = "theaether";
    public static final String ACCESSORIES = "accessories";

    public static final List<String> ALL = List.of(CURIOS, AETHER, ACCESSORIES);

    private AccessorySystems() {
    }

    /** The Aether's mod id differs from its config name. */
    public static String modId(String system) {
        return AETHER.equals(system) ? "aether" : system;
    }

    public static String activeId() {
        for (String system : SydkynConfig.slotPriority()) {
            if (ModList.get().isLoaded(modId(system))) {
                return system;
            }
        }
        return "";
    }

    public static boolean isActive(String system) {
        return activeId().equals(system);
    }

    /** Null when none of the listed systems is installed. */
    public static AccessorySupport active() {
        return switch (activeId()) {
            case CURIOS -> CuriosSupport.INSTANCE;
            case AETHER -> AetherSupport.INSTANCE;
            case ACCESSORIES -> AccessoriesSupport.INSTANCE;
            default -> null;
        };
    }
}
