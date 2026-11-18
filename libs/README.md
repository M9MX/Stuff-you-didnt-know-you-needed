# libs

Drop mod jars here to load them in the development runs (`runClient`, `runServer`, `runData`).

- These jars are **never** bundled into the built mod jar; they only exist to make testing easier.
- Files ending in `.jar` are git-ignored, so this folder stays a local scratch space.
- If a mod is available from a Maven repository (Curios, Modrinth, CurseForge), prefer declaring it
  in `build.gradle` instead, so the right version is downloaded automatically.
