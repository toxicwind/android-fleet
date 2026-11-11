# owntv-fork — sovereign OwnTV for the Hisense

One fork, everything in it:

| Path | What |
|---|---|
| `repo/` | OwnTV app fork (v5.2.2 + hot-reload lane, JDK pin) |
| `repo/core/` | OwnTV_Core submodule (the real dependency) |
| `velvet/` | CS3 plugin repo (19 plugins) + cloudstream/cs-repos submodules |
| `provider/` | Master provider: bun Xtream gateway → OwnTV (live + series + EPG) |
| `tools/sdk/` | CS3 SDK jars, kotlin-stdlib, nightly APKs |
| `tools/rish/` | rish/shizuku deploy tooling (adv-install, rish_installer) |
| `tools/decompiled/` | jadx output of CS3 v4.8.0 |
| `bin-hisense-deploy.ts` | headless APK deploy via rish bridge |

## Devices
- Hisense SmartTV 4K FFM — 10.0.0.225:5555, armeabi-v7a, Shizuku v13.6.0
- OwnTV installed (arm64 APK — WRONG ABI, needs standard flavor build)

## Deploy loop (no build)
1. `adb connect 10.0.0.225:5555`
2. `bun bin-hisense-deploy.ts <apk> --package tv.own.owntv --launch`

## Hot reload lane (debug builds)
- `HotReloadServer` binds :38383 in DEBUG only
- `POST /reload` → `activity.recreate()` (process survives)
- `POST /apk` → write to filesDir for the deploy lane