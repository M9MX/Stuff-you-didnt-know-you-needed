package org.m9mx.sydkyn;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.m9mx.sydkyn.config.SydkynConfig;
import org.m9mx.sydkyn.content.network.SydkynNetwork;

@Mod(Sydkyn.MODID)
public final class SydkynMain {
    public SydkynMain(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, SydkynConfig.SPEC);
        modBus.addListener(SydkynConfig::onLoad);
        modBus.addListener(SydkynNetwork::register);
    }
}
