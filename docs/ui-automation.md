# Headless UI automation — OwnTV on the Hisense

The OwnTV fork's Compose UI was a **semantic black box** to uiautomator. This
doc captures the fix and the working drive lane.

## The semantic void (root cause)

Compose emits a semantics tree, not a view hierarchy. uiautomator sees it only
if interactive elements expose `contentDescription` / `Role.Button` AND
`testTagsAsResourceId = true` is set at the root.

The upstream OwnTV shipped:
- **0** `testTag`, **0** `testTagsAsResourceId`
- **20** Espresso androidTest fossils (no `ui-test-junit4` — never migrated to Compose)
- **63** `contentDescription` usages, mostly `= null` (decorative-image markers, not accessibility)
- `focusGroup()` on the rail — hid per-item click semantics

## The fix (landed in `products/owntv-fork`)

| File | Change |
|---|---|
| `MainActivity.kt` | root Box `.semantics { testTagsAsResourceId = true }` + `@OptIn(ExperimentalComposeUiApi)` |
| `StageComponents.kt` | `stageClickable` → `semantics(mergeDescendants=true)` wraps `clickable()`; `StageSurface` gained `label` param |
| `StageRail.kt` | removed `focusGroup()`; added `testTag("rail_<label>")` + `.clickable()` + `Role.Button` + `contentDescription` |
| `StageControls.kt` | `StageSearchField` → `testTag("search_field")` |
| `PlayerHudControls.kt` | `CtrlButton` exposes `label — value` as contentDescription |

## uiautomator gotchas

- **`--compressed` hides clickable** — use `uiautomator dump /sdcard/full.xml` (no `--compressed`).
- Rail items render as `android.widget.Button` with content-desc (Search/Home/Live TV/...).
- TV screensaver (DreamActivity) steals focus — `KEYCODE_WAKEUP` + `am start -n tv.own.owntv/.MainActivity --activity-clear-top`.
- "null root node" from dump = screen off — wake first.

## Drive the search → play flow headlessly

```bash
ADB=/usr/bin/adb; TV=10.0.0.225:5555
$ADB -s $TV shell input keyevent KEYCODE_WAKEUP
$ADB -s $TV shell am start -n tv.own.owntv/.MainActivity --activity-clear-top
$ADB -s $TV shell input tap 74 255              # rail Search [29,222][120,289]
$ADB -s $TV shell "input keyevent KEYCODE_DPAD_UP; input keyevent KEYCODE_DPAD_CENTER"  # focus EditText
$ADB -s $TV shell input text 'ruin'
$ADB -s $TV shell uiautomator dump /sdcard/ui.xml
$ADB -s $TV pull /sdcard/ui.xml /tmp/ui.xml
$ADB -s $TV shell input tap 588 581             # select a result row
```

Verified end-to-end: rail Search → "ruin" → 21 results (Movies 16 / Live TV 2 /
Series 3) → Blue Ruin detail with Play.

## Managed skill

The full lane is codified in the `owntv-compose-ui-automation-semantics`
managed skill (reusable in future sessions).