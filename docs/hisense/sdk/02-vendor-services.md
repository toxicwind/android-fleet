# 02 Vendor services & HALs (SDK-deep)

## Binder services (raw/service-list.txt — full 8.4KB capture)
Everything registered with the Android binder (system services + vendor).
Notable from the live capture: `display`, `color_display`, `backup`,
`rollback`, plus MediaTek/Hisense services. Full list in raw — classes of
interest for control-plane work: display/backlight (see 03), audio (04),
power, input, package, activity.

## Vendor HAL binaries (raw/hal-bins.txt)
`/vendor/bin/hw/` inventory — the HTV HALs this BSP installs (see the raw list;
historically includes the hisense factory/pq/tvmain binaries).

## Vendor app packages (paths pulled 2026-11-09)
| package | path | role |
|---|---|---|
| com.hisense.uifac | /system/app/HisenseFactoryUI | factory UI — **backlight/PQ test surface** |
| com.hisense.tv.smartservice | /system_ext/app/SmartsenseService | smart services |
| com.hisense.tv.hitvinput | /system_ext/priv-app/HiTvInput | TV input |
| com.hisense.alexa | /system/app/HiAlexa | alexa |
+ media/drm/cast family (com.hisense.tv.dlna, com.mediatek.AirplayAPK, mediashell, netflix partner)

## Reverse-engineered factory API (dex of HisenseFactoryUI, 2026-11-09)
Strings reveal the vendor picture/backlight surface:
- `setBrightnessMid/20/80/Min/Max/ByFactory`, `setBackLight`, `setBackLightOn`, `getBackLight`
- `setVideoBackLight_native` (JNI) — the actual panel PWM hook
- `setPictureMode`, `setColor`, factory test labels
- internal keys `g_system__backlight_mode`, `g_fusion_picture__backlight`,
  `g_fusion_picture__brightness` (NOT in settings provider)
Verdict: shell uid (adb/Shizuku) cannot reach these; root-tier only (07).

## Notes
- android-mcp upstream currently down ("fetch failed") — adb direct is the reliable drive lane.
- flotilla :25245 = the controller overlay lane (Pixel), unrelated to Hisense vendor.