package org.m9mx.sydkyn.content.controls;

import java.util.List;
import org.m9mx.sydkyn.mod.waystones.WarpstoneKeybind;

/** Every control this mod adds, in screen order. */
public final class SydkynControls {

    private SydkynControls() {
    }

    public static List<Keybind> binds() {
        return List.of(
                WarpstoneKeybind.create()
        );
    }
}
