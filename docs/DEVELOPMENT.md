# Development

## Layout

```
helpers/          accessory system resolver + slot condition
content/
  curios/         Curios slot registry, data provider, lookup
  theaether/      Aether accessory tag, lookup
  accessories/    Accessories slot data, validator, lookup
  controls/       keybind registry
  network/        payload registration
mod/<modname>/    features for one other mod, e.g. waystones/
datagen/          data generation wiring
client/           client only wiring
```

## Slot priority

`AccessorySystems.active()` walks `slotPriority` from the config and returns the first installed
mod. Everything else asks it, so there is exactly one active system:

- `CuriosSupport` reads the Curios slot
- `AetherSupport` reads worn items through the Accessories API
- `AccessoriesSupport` reads worn items through the Accessories API

The generated slot files are gated with `sydkyn:slot_system`, a condition that is true only for the
active system, so the other systems' files are skipped at load.

## Datagen

`gradlew runData` writes into `src/generated/resources`:

- `data/sydkyn/curios/*` from `CuriosSlotRegistry`
- `data/sydkyn/accessories/*` from `AccessoriesDataProvider`
- `data/aether/tags/item/accessories_miscellaneous.json` from `AetherDataProvider`

Item tags are hand written: `data/sydkyn/tags/item/warpstone.json` is the source, and
`data/curios/tags/item/warpstone.json` points at it.

Curios slot icons live in `assets/sydkyn/textures/slot/` and are referenced as `sydkyn:slot/<name>`.
Accessories icons live in `assets/sydkyn/textures/gui/slot/` and are referenced as
`sydkyn:gui/slot/<name>`; Accessories stitches that folder from every namespace, so no atlas file
is needed.

## Development mods

`build.gradle` loads Waystones, Balm, The Aether, Accessories and owo-lib for the dev runs only, and
compiles against the Accessories API. Versions live in `gradle.properties` as Modrinth version ids
(the short id on the mod's Versions page). Jars dropped into `libs/` are loaded too. None of it ends
up in the built jar.

The Aether ships Accessories 1.1.0-beta.48 as a jar-in-jar, and that build's `EntityRenderersMixin`
cannot apply in a dev client. The standalone jar from `gradle.properties` is newer, so it wins and
the mixin applies.

## Keybinds

`content/controls/SydkynControls` lists a `Keybind` per control. `enabled` is checked while the
Controls screen is built, so a control whose mods are missing is never registered. Presses that
change the world send a payload from `content/network/SydkynNetwork`; the handler checks
availability again on the server.
