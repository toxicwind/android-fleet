# TV UI-drive & cred-extraction routes (2026-10-28)

How to drive the Hisense `wuyishan` TV UI headlessly, and the (exhausted vs live)
routes to the Xtream portal credentials. TV: `adb -s 10.0.0.225:5555`.

## Working primitives

| Primitive | Command | Notes |
|---|---|---|
| Focus check | `adb shell dumpsys window \| grep mCurrentFocus` | the ONLY reliable "where am I" |
| A11y tree (focused window only) | `uiautomator dump /sdcard/w.xml` + `cat` | Android limitation: dump = focused window's tree, occluded windows invisible |
| Screenshot | `screencap -p` + `adb pull` | 1920×1080 logical @ 320dpi |
| OCR | box-side `tesseract` 5.5.3, `--psm 11` | reads TV pixels headlessly |
| Tap | `input tap X Y` (logical px = uiautomator bounds) | taps work; DPAD nav is flaky/ambiguous |
| Keys | `input keyevent KEYCODE_DPAD_*`, `KEYCODE_CENTER`, `KEYCODE_MENU`, `KEYCODE_BACK` | MENU opens TV's own settings overlay, NOT app drawer |
| Backup+dialog | `adb backup -f out.ab -apk -nosystem <pkg>` + tap `com.android.backupconfirm:id/button_allow` | works ONLY for `allowBackup=true` pkgs; stream must finish naturally (truncate if client killed) |

## Cred-extraction routes — status

The Xtream portal creds (`portalUrl/portalUser/portalPass` shape) exist ONLY in
three apps, all walled:

| App | allowBackup | run-as | Root | Verdict |
|---|---|---|---|---|
| `com.nuvio.livetv` | false (0x0) | refused | none (locked AVB, `user` build) | walled |
| `com.playtorrio.tv` | false (0x0) | refused | none | walled (checked 2026-10-28: `allowBackup(0x01010280)=0x0`) |
| `com.nuvio.tv` | false | refused | none | walled |
| `wizard.red.the` (Red Light/Kodi) | **true** | refused | none | backed up OK 431MB BUT contained **zero** IPTV addon config (sources = TVAddons fusion + 127.0.0.1:8901 only) |

Exhausted: root (no su, Magisk, AVB-locked), debuggable (none), `run-as`
(all refused), FileProvider (sealed, not exported), TvProvider
(`android.media.tv/channel` empty), DataStore dirs (permission denied).

**Live route: UI-drive PlayTorrio → IPTV section + OCR.**

Proven: laundry list of screenshots as `/tmp/tv-ocr/*.png`.
PlayTorrio `MainActivity` has NO deep-link schemes registered (checked via
`dumpsys package`), so nav must be pushed: sidebar (DPAD_LEFT) → `IPTV` entry
ladled at uiautomator bounds `[156,445][233,484]` → tap center `(194,464)`.

Gotcha: sidebar opened once via DPAD_LEFT after BACK-to-home; DPAD pokes can
land on the content grid instead of the rail (movie detail!). Prefer
**uiautomator-bounds taps over DPAD sequences**; re-dump between every action.

## Maestro (vendored `vendored/maestro/`, head 3fc4ef3)

Emergent YAML UI-automation framework (ADB-driven, accessibility-service
-based, reads all windows not just focused). Vendored 2026-10-28 for
declarative flows as the "nav-intentive" replacement for blind DPAD pokes:

- Flow files: YAML (`appId`, `- tapOn: "IPTV"`, `- assertVisible:`, keys,
  coordinates). See `vendored/maestro/README.md`.
- CLI: Java — box has `java`; maestro jars/shell in `vendored/maestro/`.
- Integration target: flotilla lane `pixel_nav`/`tv_nav` MCP tools — declarative
  `tapOn`-style steps instead of raw keyevent guessing.

## Acceptable UI-drive exposure

Driving the TV briefly to read creds does NOT steal the Architect's phone
(flotilla = zero-focus-steal, verified live). TV now sits quiet on launcher
after each probe.