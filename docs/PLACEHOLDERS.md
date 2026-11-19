# Join message placeholders

Use `%name%` in a join message. Parameterized placeholders use `%name{id}%`.

## Player

| Placeholder | Value |
| --- | --- |
| `%playername%` | Player name |
| `%displayname%` | Display name, including team formatting |
| `%uuid%` | Player UUID |
| `%health%` | Current health in hearts |
| `%food%` | Food level |
| `%level%` | Experience level |
| `%playtime%` | Formatted play time |
| `%distance%` | Formatted distance walked |

## Parameterized stats

Arguments are registry ids. `minecraft:` can be omitted. An unknown id gives `0`.

| Placeholder | Value |
| --- | --- |
| `%blocksbroken%` | Total blocks broken |
| `%blocksbroken{minecraft:stone}%` | Stone broken |
| `%blocksmined%` | Alias for `%blocksbroken%` |
| `%itemscrafted%` | Total items crafted |
| `%itemscrafted{minecraft:diamond_pickaxe}%` | Diamond pickaxes crafted |
| `%itemsused%` | Total items used |
| `%itemsused{minecraft:golden_apple}%` | Golden apples used |
| `%itemsbroken%` | Total items broken |
| `%itemsbroken{minecraft:diamond_pickaxe}%` | Diamond pickaxes broken |
| `%itemspickedup%` | Total items picked up |
| `%itemspickedup{minecraft:dirt}%` | Dirt picked up |
| `%itemsdropped%` | Total items dropped |
| `%itemsdropped{minecraft:diamond}%` | Diamonds dropped |
| `%entitykilled%` | Total entities killed |
| `%entitykilled{minecraft:zombie}%` | Zombies killed |
| `%entitykilledby%` | Total entities that killed the player |
| `%entitykilledby{minecraft:creeper}%` | Deaths to creepers |
| `%stat{namespace:path}%` | Any custom stat, including modded stats |
| `%customstat{namespace:path}%` | Alias for `%stat%` |

## Custom stats

| Placeholder | Stat |
| --- | --- |
| `%leave_game%` | leave_game |
| `%play_time%` | play_time |
| `%total_world_time%` | total_world_time |
| `%time_since_death%` | time_since_death |
| `%time_since_rest%` | time_since_rest |
| `%sneak_time%` | sneak_time |
| `%walk_one_cm%` | walk_one_cm |
| `%crouch_one_cm%` | crouch_one_cm |
| `%sprint_one_cm%` | sprint_one_cm |
| `%walk_on_water_one_cm%` | walk_on_water_one_cm |
| `%fall_one_cm%` | fall_one_cm |
| `%climb_one_cm%` | climb_one_cm |
| `%fly_one_cm%` | fly_one_cm |
| `%walk_under_water_one_cm%` | walk_under_water_one_cm |
| `%minecart_one_cm%` | minecart_one_cm |
| `%boat_one_cm%` | boat_one_cm |
| `%pig_one_cm%` | pig_one_cm |
| `%horse_one_cm%` | horse_one_cm |
| `%aviate_one_cm%` | aviate_one_cm |
| `%swim_one_cm%` | swim_one_cm |
| `%strider_one_cm%` | strider_one_cm |
| `%jump%` | jump |
| `%drop%` | drop |
| `%damage_dealt%` | damage_dealt |
| `%damage_dealt_absorbed%` | damage_dealt_absorbed |
| `%damage_dealt_resisted%` | damage_dealt_resisted |
| `%damage_taken%` | damage_taken |
| `%damage_blocked_by_shield%` | damage_blocked_by_shield |
| `%damage_absorbed%` | damage_absorbed |
| `%damage_resisted%` | damage_resisted |
| `%deaths%` | deaths |
| `%mob_kills%` | mob_kills |
| `%animals_bred%` | animals_bred |
| `%player_kills%` | player_kills |
| `%fish_caught%` | fish_caught |
| `%talked_to_villager%` | talked_to_villager |
| `%traded_with_villager%` | traded_with_villager |
| `%eat_cake_slice%` | eat_cake_slice |
| `%fill_cauldron%` | fill_cauldron |
| `%use_cauldron%` | use_cauldron |
| `%clean_armor%` | clean_armor |
| `%clean_banner%` | clean_banner |
| `%clean_shulker_box%` | clean_shulker_box |
| `%interact_with_brewingstand%` | interact_with_brewingstand |
| `%interact_with_beacon%` | interact_with_beacon |
| `%inspect_dropper%` | inspect_dropper |
| `%inspect_hopper%` | inspect_hopper |
| `%inspect_dispenser%` | inspect_dispenser |
| `%play_noteblock%` | play_noteblock |
| `%tune_noteblock%` | tune_noteblock |
| `%pot_flower%` | pot_flower |
| `%trigger_trapped_chest%` | trigger_trapped_chest |
| `%open_enderchest%` | open_enderchest |
| `%enchant_item%` | enchant_item |
| `%play_record%` | play_record |
| `%interact_with_furnace%` | interact_with_furnace |
| `%interact_with_crafting_table%` | interact_with_crafting_table |
| `%open_chest%` | open_chest |
| `%sleep_in_bed%` | sleep_in_bed |
| `%open_shulker_box%` | open_shulker_box |
| `%open_barrel%` | open_barrel |
| `%interact_with_blast_furnace%` | interact_with_blast_furnace |
| `%interact_with_smoker%` | interact_with_smoker |
| `%interact_with_lectern%` | interact_with_lectern |
| `%interact_with_campfire%` | interact_with_campfire |
| `%interact_with_cartography_table%` | interact_with_cartography_table |
| `%interact_with_loom%` | interact_with_loom |
| `%interact_with_stonecutter%` | interact_with_stonecutter |
| `%bell_ring%` | bell_ring |
| `%raid_trigger%` | raid_trigger |
| `%raid_win%` | raid_win |
| `%interact_with_anvil%` | interact_with_anvil |
| `%interact_with_grindstone%` | interact_with_grindstone |
| `%target_hit%` | target_hit |
| `%interact_with_smithing_table%` | interact_with_smithing_table |

## Short aliases

| Placeholder | Same as |
| --- | --- |
| `%mobkills%` | `%mob_kills%` |
| `%playerkills%` | `%player_kills%` |
| `%damagetaken%` | `%damage_taken%` |
| `%damagedealt%` | `%damage_dealt%` |
| `%fishcaught%` | `%fish_caught%` |
| `%jumps%` | `%jump%` |

## Formatting

- Play time, world time, time since death/rest and sneak time are shown as `2d 3h` or `1h 20m`.
- Every `*_one_cm` stat is shown in kilometres with one decimal.
- Damage stats are raw damage points.
- Health is shown in hearts.
