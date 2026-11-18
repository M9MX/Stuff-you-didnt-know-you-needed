package org.m9mx.sydkyn.content.curios;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.m9mx.sydkyn.Sydkyn;
import top.theillusivec4.curios.api.CuriosDataProvider;

/** Datagen only: writes the slot files, never loaded in game. */
public final class SydkynCuriosDataProvider extends CuriosDataProvider {

    public SydkynCuriosDataProvider(PackOutput output,
                                    ExistingFileHelper fileHelper,
                                    CompletableFuture<HolderLookup.Provider> registries) {
        super(Sydkyn.MODID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        CuriosSlotRegistry.generate(this);
    }
}
