# Development

## Layout

```
content/          registries every feature plugs into
  curios/         slot registry + its data provider
  controls/       keybind registry
  network/        payload registration
mod/<modname>/    features for one other mod, e.g. waystones/
datagen/          data generation wiring
client/           client only wiring
```

Adding a feature means a new package plus one line in the registry it uses.

## Development mods

`build.gradle` loads Waystones, Balm and Curios for the dev runs only. Versions live in
`gradle.properties` as Modrinth version ids (the short id on the mod's Versions page). Jars dropped
into `libs/` are loaded too. None of it ends up in the built jar.

## Curios slots

Slots are datapack JSON, so `content/curios/CuriosSlotRegistry` writes them through
`SydkynCuriosDataProvider` during `gradlew runData`:

- `data/sydkyn/curios/slots/<slot>.json`
- `data/sydkyn/curios/entities/player.json`

Items a slot accepts go in `data/curios/tags/item/<slot>.json`. To hide a slot unless another mod
is present, add `.addCondition(new ModLoadedCondition("modid"))`.

The icon is a 16x16 PNG in `assets/sydkyn/textures/slot/`, referenced as `sydkyn:slot/<name>`.
Curios adds every namespace's `textures/slot/` folder to the block atlas, so no atlas file is
needed.

## Keybinds

`content/controls/SydkynControls` lists a `Keybind` per control. `enabled` is checked while the
Controls screen is built, so a control whose mods are missing is never registered. Presses that
change the world send a payload from `content/network/SydkynNetwork`; the handler checks
availability again on the server.
