package org.m9mx.sydkyn.content.accessories;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.m9mx.sydkyn.Sydkyn;
import org.m9mx.sydkyn.helpers.AccessorySystems;
import org.m9mx.sydkyn.helpers.SlotSystemCondition;
import org.m9mx.sydkyn.mod.waystones.WarpstoneSlot;

/** Accessories slots are datapack JSON, so the slot lives here instead of a DeferredRegister. */
public final class AccessoriesDataProvider implements DataProvider {

    private final PackOutput.PathProvider slots;
    private final PackOutput.PathProvider entities;

    public AccessoriesDataProvider(PackOutput output) {
        this.slots = output.createPathProvider(PackOutput.Target.DATA_PACK, "accessories/slot");
        this.entities = output.createPathProvider(PackOutput.Target.DATA_PACK, "accessories/entity");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<ICondition> conditions =
                List.of(new SlotSystemCondition(AccessorySystems.ACCESSORIES));

        JsonObject slot = new JsonObject();
        slot.addProperty("replace", false);
        slot.addProperty("amount", 1);
        slot.addProperty("operation", "set");
        slot.addProperty("order", WarpstoneSlot.ORDER);
        slot.addProperty("icon", WarpstoneSlot.ACCESSORIES_ICON.toString());
        slot.add("validators", strings(AccessoryPredicates.VALIDATOR));
        ICondition.writeConditions(JsonOps.INSTANCE, slot, conditions);

        JsonObject entity = new JsonObject();
        entity.addProperty("replace", false);
        entity.add("entities", strings("minecraft:player"));
        entity.add("slots", strings(WarpstoneSlot.ID));
        ICondition.writeConditions(JsonOps.INSTANCE, entity, conditions);

        String name = WarpstoneSlot.ID;
        return CompletableFuture.allOf(
                DataProvider.saveStable(cache, slot, slots.json(Sydkyn.id(name))),
                DataProvider.saveStable(cache, entity, entities.json(Sydkyn.id("player")))
        );
    }

    private static JsonArray strings(String... values) {
        JsonArray array = new JsonArray();
        for (String value : values) {
            array.add(value);
        }
        return array;
    }

    @Override
    public String getName() {
        return "Accessories warpstone slot";
    }
}
