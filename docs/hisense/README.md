# Hisense "wuyishan" Root Marathon — DOC HUB

> Live field archive for the headless, non-USB factory/root hunt on
> **Hisense SmartTV 4K FFM** (`wuyishan`, MTK, Android 11/SDK 30, build `2026.05.20`,
> serial `0000dc9a7d3e0b65`). Started 2026-11-08, ongoing. Every claim below is
> pointed at its evidence doc. Source apks/artifacts live in
> `/home/toxic/tmp/hisense-apks/` (archive map at the bottom).

## Status (2026-11-09)
- adb over WiFi: **UP** (5555; USB debugging on; `adb root` refused — `ro.secure=1`, `ro.debuggable=0`)
- Root: **NOT ACHIEVED.** All remote vectors RE'd; physical USB-stick host bus effectively dead; no `su`, no writable factory prop, no OTA bypass confirmed.
- Fully headless control planes discovered: 9001 RMP JSON-RPC (read/query only), uifac serial console (GATED), reboot broadcast (warm), settings/dev-options intents.

## Lane verdicts (one line each; details deeplinked)
| Lane | Verdict | Doc |
|---|---|---|
| FacRootMenu / AdbEnabled | dead-remote, app hostile (wifi-kill) | ROOT-APPLY.md → FacRootMenu findings |
| CVT USB remount (`FAC_BOOT_SMART.cvt`) | physical lane dead on this unit (stick never enumerates; see anomaly #12/#14) | ROOT-APPLY.md + SIDE-ANOMALIES.md #12/#14 |
| Recovery adbd | USB-only, unreachable headless; watchdog recovers | SIDE-ANOMALIES.md (recovery cycle) |
| RMP 9001 (ATSC JSON-RPC) | WORKS headless; read/query + launchApp(catalog); no key/power verbs | recon/rmp-9001.md |
| Factory serial console (uifac) | DECODED, runs as SYSTEM; GATED on `persist.sys.factory.mode` (root-only) | recon/factory-serial-console.md |
| Demura TCP server 8099 | SYSTEM-uid file write; only starts via IPC command; currently closed | recon/factory-serial-console.md |
| M-mode via remote key-combo | user-side experiment pending (OK+Menu @ Sound Balance) | recon/factory-serial-console.md → next-steps |
| AoL/WoL cold cycle | WoL FAILED from `reboot -p`; **never `reboot -p` again** | SIDE-ANOMALIES.md #16 |
| Hotel/RTC power schedule | firmware vocab only; RTC wakealarm readonly; no shell handle | TOKEN-WALK.md (signal) |
| ATSC parser attack surface | open research lane (priv-app C++ parsers) | recon/rmp-9001.md → research lane |

## Doc map (modular, deeplinked)
- **README.md** ← you are here (hub; re-point every new doc here)
- **ROOT-APPLY.md** — the CVT lane + requirements + ARMED/correction log + FacRootMenu findings
- **ROLLBACK.md** — rollback/inverse table for every applied op
- **ROOT-ROLLBACK-EFFECT.md** — effect-composition sketch (acquireRelease pattern) for the plant/unplant
- **SIDE-ANOMALIES.md** — numbered anomalies 1–16 (evidence-first; the running "what we learned")
- **TOKEN-WALK.md** — field manual: dex string-pool walking, obfuscation linguisitcs, AST cross-check
- **recon/rmp-9001.md** — ATSC 3.0 RMP JSON-RPC: method table, live calls, deviceInfo fields
- **recon/factory-serial-console.md** — decoded uifac serial console: gate, dispatch, TcpServer 8099, M-mode
- **recon/network-surface.md** — port→service→owner map (proc-level evidence)
- **recon/apk-inventory.md** — every pulled APK + what RE yielded (annotated)
- **annotations.md** — running cross-reference annotations (source → doc → evidence)
- **recon/rmp-deviceinfo.json** — captured live deviceInfo (raw evidence)

## Live sources (as of last probe)
- TV: `10.0.0.225:5555` (adb), MAC `38:64:07:f0:48:56`
- RMP: `http://10.0.0.225:9001` (WS JSON-RPC) | SDAP-like 9001 426/101
- Configs: `sm list-volumes` = only internal `public:8,1 mounted 7EA6-0BF6` (INTERNAL — see anomaly #12)

## Archive map (box-side, LOCAL — estate archive; gitignored at estate level)
| path | contents |
|---|---|
| `/home/toxic/pens/estate/archive/2026-11-09-toxic-hisense-re/archive/apks/` | pulled APKs (moved off /tmp 2026-11-09; estate-ignored) |
| `…/archive/re/` | jadx trees: `re-uif` (uifac/serialInfoTest), `re-smart`, `re-sync` |
| `…/archive/libs/` | Interactive.apk native libs (libwsapi/libinteract_jni/libatscws/libmwipc) |
| `…/archive/corpora/` | dex string-pool corpus dumps + classes.dex |
| `…/archive/evidence/` | earlier session UI dumps/logs/screenshots |
| `recon/rmp-deviceinfo.json` | live deviceInfo capture (TRACKED) |

## How to resume (5 min)
1. `adb kill-server; adb connect 10.0.0.225:5555` (always kill-server after any power event)
2. Read SIDE-ANOMALIES.md for context; check `sm list-volumes` — public volume other than the 116G = a REAL stick!
3. Try the M-mode remote experiment (user-side, 30s)
4. If factory-mode getter is ever writable → serial console + demura server unlock (docs ready)