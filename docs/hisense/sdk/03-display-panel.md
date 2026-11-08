# 03 Display & panel — the black-screen truth (SDK-deep)

## Panel spec (raw/dumpsys-display.txt)
- Internal display 1920x1080 @ 60Hz (single mode; UI renders from a 3840x2160 source)
- **HDR: max luminance 2000 nits**, supported HDR types [1,2,3] (Dolby Vision/HDR10/HLG)
- `mMinLuminance=5.0`, minimal post-processing supported, ALLM supported
- color modes [0,7]; FLAG_SECURE (protected content)

## Backlight truth (empirically established 2026-11-09)
1. `settings put system screen_brightness N` → compose-layer dim ONLY. Panel stays bright.
2. `cmd display set-brightness 0..1` → DisplayManager/HAL path; clamps ~3.5%,
   still **does NOT black the panel** (LightsService migrate; panel PWM independent).
3. `KEYCODE_SLEEP` → true display OFF (mWakefulness=Asleep) — blacks the panel but
   **pauses all apps** (focus null, audio stops).
4. Vendor PWM: DTS pins `ko_opwm0_pwm0_pins`, `ko_3d_ctrl_bl_off`; NO writable
   sysfs (`/sys/class/backlight` empty); factory JNI `setVideoBackLight_native`
   exists but shell can't call it (root-tier lane, see 07).
5. **THE working method = composited black overlay** — `TYPE_APPLICATION_OVERLAY`,
   `MATCH_PARENT`, `FLAG_NOT_FOCUSABLE`, black surface rendered by an app that
   holds SYSTEM_ALERT_WINDOW (nuvio does). Blacks output at ANY backlight while
   player+audio+input stay alive. Process death auto-removes it.

## Screen states table
| intent | wakefulness | display | apps | audio | use |
|---|---|---|---|---|---|
| awake + overlay OFF | Awake | ON + content | running | yes | normal |
| awake + black overlay | Awake | ON, composited black | running | yes | **night/radio mode** |
| SLEEP (KEYCODE_SLEEP) | Asleep | OFF | paused | no | true off at bedtime |

## Headless visibility
- `uiautomator dump` works while Awake incl. with overlay (a11y tree alive).
- `screencap` reads the compositor: with overlay = pure black (1 color);
  without = full content (147k colors at compose floor).
- At Awake+Display ON you can drive ANY UI headlessly; input does not wake SLEEP.

## Commands
```bash
TV=10.0.0.225:5555
adb -s $TV shell dumpsys power | grep -E "mWakefulness=|Display Power:"
adb -s $TV shell cmd display set-brightness 0      # compose only (not panel!)
adb -s $TV shell settings put system screen_brightness 120  # compose only
adb -s $TV shell input keyevent KEYCODE_SLEEP      # TRUE off (pauses apps)
# black overlay toggle: in nuvio PLAYER screen, MEDIA_PLAY_PAUSE (fleet build)
```
The overlay lives in fleet-tv `BlackOverlay`/`PanelDim` (abfc29c08 + 03).