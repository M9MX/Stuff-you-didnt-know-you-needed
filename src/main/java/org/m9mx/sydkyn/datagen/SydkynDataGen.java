package org.m9mx.sydkyn.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.content.accessories.AccessoriesDataProvider;
import org.m9mx.sydkyn.content.curios.SydkynCuriosDataProvider;
import org.m9mx.sydkyn.content.theaether.AetherDataProvider;

@EventBusSubscriber(modid = Sydkyn.MODID)
public final class SydkynDataGen {

    private SydkynDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (!event.includeServer()) {
            return;
        }

        event.getGenerator().addProvider(true, new SydkynCuriosDataProvider(
                event.getGenerator().getPackOutput(),
                event.getExistingFileHelper(),
                event.getLookupProvider()
        ));
        event.getGenerator().addProvider(true, new AetherDataProvider(
                event.getGenerator().getPackOutput()
        ));
        event.getGenerator().addProvider(true, new AccessoriesDataProvider(
                event.getGenerator().getPackOutput()
        ));
    }
}
