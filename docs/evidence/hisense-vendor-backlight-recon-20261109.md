# Hisense vendor backlight reverse-engineering (2026-11-09)

Pulled + dex-grepped `/system/app/HisenseFactoryUI/HisenseFactoryUI.apk`
(com.hisense.uifac, classes.dex 6.4MB) and SmartsenseService.apk.

## Discovered vendor surface
- Factory brightness API (diagnostics/test tier, not shell-callable):
  `setBrightnessMid/20/80/Min/Max/ByFactory`, `setBackLight`, `setBackLightOn`,
  `getBackLight`, `setVideoBackLight_native` (JNI into picture engine), `setPictureMode/setColor`.
- Internal keys (vendor store, NOT Android settings — probe returned null):
  `g_system__backlight_mode`, `g_fusion_picture__backlight`, `g_fusion_picture__brightness`,
  `system__backlight_mode` strings.
- PWM backlight pins exist in the DTS (`ko_opwm0_pwm0_pins`, `ko_3d_ctrl_bl_off`)
  but are private: no writable sysfs, sysfs /sys/class/backlight empty,
  Android LightsService backlight (mActualBacklight) is compose-only; panel is
  2000-nit HDR — PWM/backlight is controlled by the Hisense picture engine.

## Verdict
- Shell uid (adb/Shizuku) CANNOT reach the panel backlight (factory service +
  private PWM). A root-tier (otaTest.sh lane per alvidaa, needs physical) could
  write the PWM node or call the factory HAL.
- **The working unlock = black overlay** (fleet-tv `BlackOverlay`,
  TYPE_APPLICATION_OVERLAY) — composited black at ANY backlight, player/audio/
  input alive. Already deployed.

## Cleanup
Vendor APKs: /tmp/vendor-audit/{hfui,smart}.apk (+dex). Evidence docs:
android-fleet docs/evidence/xtream-stream-gate-* + this file.