package org.m9mx.sydkyn.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.content.curios.SydkynCuriosDataProvider;

@EventBusSubscriber(modid = Sydkyn.MODID)
public final class SydkynDataGen {

    private SydkynDataGen() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                event.includeServer(),
                new SydkynCuriosDataProvider(
                        event.getGenerator().getPackOutput(),
                        event.getExistingFileHelper(),
                        event.getLookupProvider()
                )
        );
    }
}
