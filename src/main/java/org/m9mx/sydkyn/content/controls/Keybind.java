package org.m9mx.sydkyn.content.controls;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.function.BooleanSupplier;

/**
 * @param enabled false means the control is not registered at all, e.g. a required mod is missing
 * @param action  client side; send a payload if the server has to do something
 */
public record Keybind(String id, InputConstants.Key defaultKey, BooleanSupplier enabled, Runnable action) {

    public String translationKey() {
        return "key.sydkyn." + id;
    }
}
