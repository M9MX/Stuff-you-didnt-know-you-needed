package org.m9mx.sydkyn.content.theaether;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.helpers.SlotSystemCondition;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;

/** Puts the warp stone in the Aether's own accessory slot by adding it to that slot's item tag. */
public final class AetherDataProvider implements DataProvider {

    private static final Path TAG = Path.of("aether", "tags", "item", "accessories_miscellaneous.json");

    private final PackOutput output;

    public AetherDataProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject tag = new JsonObject();
        tag.addProperty("replace", false);
        JsonArray values = new JsonArray();
        values.add("waystones:warp_stone");
        tag.add("values", values);

        ICondition.writeConditions(
                JsonOps.INSTANCE, tag, List.of(new SlotSystemCondition(AccessorySystems.AETHER)));

        return DataProvider.saveStable(
                cache, tag, output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(TAG));
    }

    @Override
    public String getName() {
        return "Aether accessory tag " + WarpstoneSlot.ID;
    }
}
