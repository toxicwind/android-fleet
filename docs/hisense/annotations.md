# Archive annotations — `docs/hisense/archive/` (2026-11-09)

Every artifact, annotated: what it is, where it came from, what RE yielded, and
which doc cites it. Archive is **gitignored** (local only, ~138MB); the
annotations + docs are tracked.

## APKs (from `/home/toxic/tmp/hisense-apks/`, pulled live via `pm path`)
| file | package / origin path | size | annotation | cited by |
|---|---|---|---|---|
| `com.hisense.uifac.apk` | com.hisense.uifac / `/system/app/HisenseFactoryUI/` | 27MB | **THE factory UI + serial console.** `serialInfoTest` package = full factory protocol dispatcher running as SYSTEM; gate `persist.sys.factory.mode`; commands: enter M-mode, power off, source switch, test patterns, µkey reads, config-file write; network demura upload server on 8099 (`TcpServer`, HSPC framing) | factory-serial-console.md, TOKEN-WALK.md |
| `com.vt.interact.apk` | com.vt.interact / `/system_ext/priv-app/Interactive/` | 20MB | ATSC 3.0 RMP WS server owner of 9001/9000/8008/8443 (uid 10066). C++ app: libwsapi (JSON-RPC method table), libinteract_jni, libatscws (websocketpp), libmwipc. `org.atsc.*` namespace incl. query/launchApp/acquire | rmp-9001.md, network-surface.md |
| `com.hisense.tv.smartservice.apk` | com.hisense.tv.smartservice / `/system_ext/app/SmartsenseService/` | 4.3MB | MTK twoworlds TV API surface + cloud IoT SDK. Token walks yielded: RTC wakeup types (RMDR/5AM/OTA), hotel power ctrl, `g_factory__power_ctrl` NV keys, `startRebootUpgrade_native`, `1.0/iot/devices/*` | TOKEN-WALK.md, SIDE-ANOMALIES.md |
| `com.hisense.udpserver.apk` | com.hisense.udpserver / `/system_ext/app/SyncPlayServer/` | 1.2MB | AIDLUdpScanService — trivial UDP discovery (obfuscated); NOT the 9001 core. Keep for completeness | network-surface.md |
| `com.hisense.tv.ota.apk` | com.hisense.tv.ota / `/system_ext/app/OTA/` | 297KB | OTA receiver actions (`android.vt.intent.action.HIS_OTA`, `com.hisense.action.ota`) — USB/OTA lane inputs | — |
| `com.vt.tv.usbota.apk` | com.vt.tv.usbota / `/system_ext/app/USBOTA/` | 55KB | ONE receiver: `com.vt.action.usbota` (fires silently; no update file → nothing). USB-OTA candidate vector | SIDE-ANOMALIES.md |

## JADX trees (from `/tmp/hisense-apks/re-*`)
| dir | source apk | annotation |
|---|---|---|
| `re/re-uif/` | uifac | 21 files in `serialInfoTest` (SerialCommands 3006 lines, SerialFacService dispatch ~1532 lines, TcpServer, KeySwitcher, SerialStatusReceiver, SerialSaveFile, SerialCommandsType factory map: qingdao/huangdao/etc.), testPattern activities |
| `re/re-smart/` | smartservice | HisenseTvAPI (get/setHotelModeValue, setPowerOnMode via `CFG_FACTORY_POWER_CTRL`), MtkTvConfigTypeBase (800+ constants), MtkTvUpgradeBase/TVNative (startRebootUpgrade), iot cloud client |
| `re/re-sync/` | udpserver | com/hisense/udpserver/AIDLUdpScanService only (real handlers obfuscated into a/b packages with no protocol literals) |

## Native libs (from Interactive.apk, `/tmp/vix/lib/armeabi-v7a/`)
| lib | annotation |
|---|---|
| `libwsapi.so` | **RMP grammar source**: `org.atsc.query.*`, `org.atsc.subscribe/notify/launchApp/acquire.service/setRMPURL/…`; ATSC30 middleware logs (accepted-source, getVersionInfo-era transcode) |
| `libatscws.so` | websocketpp server lib + ATSC30 MW strings (RMP stop/resume, event threads) — attack surface for parser fuzz |
| `libinteract_jni.so` | interact_adapter JNI: appContextId/signaling, getTvserverState, notifyRequestAppKey — app-launch signaling |
| `libmwipc.so` | message IPC transport (boost deps) |

## Corpora
| file | annotation |
|---|---|
| `c2-strings.txt` | Full string pool of smartservice classes2.dex (~115k strings) — the power/RTC/hotel/NV-key token mine (TOKEN-WALK.md) |
| `classes.dex` | vt.interact main dex (2103 strings; powerOn/powerOff/registerListener tokens only — the meat is native) |

## Evidence (earlier sessions, swept from /tmp)
| file | annotation |
|---|---|
| `evidence/hisense-ui*.xml` | uiautomator dumps (dev options panel, launcher states) |
| `evidence/hisense-logcat.txt` / `hisense-*-install*.txt` | prior live-watch + install logs |
| `evidence/hisense-live*.png` | screen captures during channel-watch |

## Reference map — what cites what
- factory-serial-console.md ← uifac apk + re-uif + corpus strings
- rmp-9001.md ← vt.interact apk + libwsapi strings + live 9001 calls + deviceinfo.json
- TOKEN-WALK.md ← dex walker + c2-strings + smartservice uifac corpora
- network-surface.md ← /proc net maps + pm uid resolution
- SIDE-ANOMALIES.md ← every live probe outcome (stick, WoL, RMP, serial gate)