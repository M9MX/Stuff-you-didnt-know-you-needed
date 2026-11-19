# Stuff you didnt know you needed

Small quality-of-life additions for modpacks.

## Features

| Feature | Requires | What it does |
| --- | --- | --- |
| **Warpstone slot** | Waystones, Accessories Slot mod | Slot for Waystones' Warp Stone, worn instead of held. |
| **Activate Warp Stone** | Waystones, Accessories Slot mod | Keybind, default `V`, in Options > Controls. Opens the Waystone menu from the equipped stone. |
| **Join messages** | None | Player-specific join messages with colors and stats. Replaces the vanilla join line. |

## Accessory mods supported

- Curios 9.x
- The Aether 1.5.x
- Accessories 1.1.x

## Configuration

`config/sydkyn-common.toml`:

```toml
#Write extra diagnostic messages to the log.
debugLogging = false
#Which accessory mod provides the warpstone slot, first installed one wins.
#Entries: curios, theaether, accessories
#Restart the game after changing.
slotPriority = ["curios", "theaether", "accessories"]
#Extra join messages. Format: name|#RRGGBB|message.
#Full placeholder list: https://github.com/M9MX/Stuff-you-didnt-know-you-needed/blob/master/docs/PLACEHOLDERS.md
joinMessages = ["Notch|#FFD700|%playername% sold Minecraft for $2.5 billion and spent it all on a god complex."]
```

Join messages support `#RRGGBB` colors and stats placeholders. The full list is in the
[placeholder docs](docs/PLACEHOLDERS.md).

## Requirements

| Component | Version |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.x |
| Waystones | 21.1.x, optional |

## Installation

Drop the jar into `mods/`. Tested with All the Mods 10.

## Building

```
gradlew build
gradlew runData
```

Development notes are in [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md).

## License

[CC BY-ND 4.0](LICENSE). Use it, ship it in packs, keep the credit. Modified copies may not be
shared.
