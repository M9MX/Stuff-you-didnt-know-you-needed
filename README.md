# Stuff you didnt know you needed

A handful of small things for modpacks. Nothing big: the kind of detail a pack author usually
patches in with a datapack or a tiny add-on mod.

Each feature stays off unless the mods it needs are installed.

## Features

### Warpstone slot

A Curios slot for Waystones' Warp Stone, so the stone can be worn instead of taking up a hotbar
slot.

- Holds anything in the `curios:warpstone` item tag (`waystones:warp_stone` by default)
- One slot per player
- Needs Curios and Waystones; without either of them the slot is not created

### Activate Warp Stone

A keybind (default <kbd>V</kbd>), listed in Options > Controls, that opens the Waystones menu from
the equipped warp stone. It only appears when Curios and Waystones are installed, and tells you when
the slot is empty.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.x
- Curios 9.x *(optional)*
- Waystones 21.1.x *(optional)*

## Installation

Drop the jar into `mods/`. Tested with All the Mods 10.

## Building

```
gradlew build
gradlew runData
```

## Development

See [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md).

## License

[CC BY-ND 4.0](LICENSE). Use it, put the jar in a modpack, keep the credit. Modified copies may not
be shared.
