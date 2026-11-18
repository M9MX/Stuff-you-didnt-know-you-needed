package org.m9mx.sydkyn;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class Sydkyn {
    public static final String MODID = "sydkyn";
    public static final Logger LOGGER = LogUtils.getLogger();

    private Sydkyn() {
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
