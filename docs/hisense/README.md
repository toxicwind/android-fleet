# Hisense `wuyishan` — SDK-deep recon (extreme dive, 2026-11-08/09)

Device: Hisense SmartTV 4K FFM, 55E82GUA · MT5895 (MT9900) · Android 11 (SDK 30) ·
10.0.0.225:5555 · locked user build (`ro.build.type=user`, AVB enforcing, no root).

This tree is the systematic, SDK-grade documentation of everything learned —
hardware, firmware, vendor services, display/backlight, audio, network, IPTV
internals, security lanes. It reads like a vendor SDK manual and records things
Hisense never documents. Live dumps live in `raw/` (2026-11-08/09).

## Index
| doc | scope |
|---|---|
| [sdk/01-platform.md](sdk/01-platform.md) | SoC, firmware, kernel, partitions, mounts, boot chain, properties |
| [sdk/02-vendor-services.md](sdk/02-vendor-services.md) | binder services + vendor HALs inventory |
| [sdk/03-display-panel.md](sdk/03-display-panel.md) | panel spec, HDR, modes, backlight truth, black-screen lanes |
| [sdk/04-audio.md](sdk/04-audio.md) | audio devices/HAL routing (speaker/HDMI/ARC) |
| [sdk/05-network.md](sdk/05-network.md) | network surface, ports, adb, N1/gating, wake |
| [sdk/06-iptv-apps.md](sdk/06-iptv-apps.md) | nuvio/playtorrio internals, stream-gate, VLC UA, capacity/max-auto |
| [sdk/07-security-lanes.md](sdk/07-security-lanes.md) | Shizuku, BlackDex, root primitives (alvidaa/otaTest/USB .PKG), boundaries |
| [sdk/08-black-screen-guide.md](sdk/08-black-screen-guide.md) | DEFINITIVE screen semantics + keeper daemon |
| [raw/](raw/) | live dumps: getprop, display, audio, service list, hal bins, mounts, kernel, cmdline, features, power, input |

## Session discovery timeline (the parts "even Hisense" wouldn't tell you)
1. Screen "black" = display asleep (wakeable); compose brightness ≠ panel backlight.
2. Shizuku v13.6 server start = bundled `libshizuku.so` launcher → `rikka.shizuku.server.ShizukuService`
   (v12-era `moe.shizuku.server.ShizukuServer` is ABSENT from v13 dex → SIGABRT).
3. Xtream streams are player-gated: N1 edge 407s everyone; working flow = 302→token→LB
   with **VLC/3.0.20 UA** (android-iptv proven: m3u8 200 + ts video/mp2t 3.6MB).
4. Only 1/56 api-alive portals has a working stream path.
5. Panel backlight = factory-only (setVideoBackLight_native JNI), private PWM pins in DTS;
   the real black-while-playing = composited black overlay (TYPE_APPLICATION_OVERLAY).
6. nuvio deep links EXIST (`stremio://` + `nuvio://`) though routes are dynamic.
7. PlayTorrio finder = login-verifier (no stream/capacity probe); its VLC+Range `_isAlive` is right.

## Raw evidence
`raw/*.txt` — captured 2026-11-08/09 via adb (see file list in `raw/manifest`.
Checksums optional; preserve dumps when re-probing.