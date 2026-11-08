# FULL ROOT application — Hisense wuyishan (monadic, rollback-safe)

Goal: uid 0 on the TV for vendor/PWM/factory HAL access (the honest requirement
for panel-level control — Shizuku root mode needs existing root, there is
NO su on this production build).

## The lane (alvidaa-derived, shipping-firmware verified)
1. FAT32 USB stick with an EMPTY file `FAC_BOOT_SMART.cvt` at the root.
2. TV OFF (wall plug), stick into a REAR USB port, power on.
3. `cvte-logger.sh` sees the file → remounts `/` and `/usr/local` **rw**
   during boot (factory logging session). It is NOT persistent config by itself.
4. While rw: write a **setuid-root su shim** (copy of `toybox`/busybox with
   `chmod 6755 su-shim` into /system/xbin or /system/bin). Subsequent `adb shell`
   execs the shim → **uid 0 shell**.
5. Start Shizuku server root-mode: `su -c CLASSPATH=<apk> app_process ... ShizukuService`
   → server runs as root → Shizuku root mode for any app.
6. Verify: `id` → uid=0(rooot), `mount | grep ' / '`.

## Monadic pipeline (rollback-on-fail)
Each step is a `Result`; failure composes the inverse chain (LIFO), also as
Results, so a failure never leaves the TV dirty.

```text
ROOT-APPLY = step1(stick) >>= step2(remount) >>= step3(su-shim) >>= step4(shizuku-root) >>= verify
  where each `>>=` failure triggers: inverse(step_failed) >> inverse(prev) >> inverse(prev2)...  (until base)
```

Step / inverse:
| step | op | inverse (rollback) |
|---|---|---|
| 1 | create FAC_BOOT_SMART.cvt on stick | delete file from stick |
| 2 | power-on remount (auto) | remove stick + power cycle (no persistent boot code) |
| 3 | write su shim + chmod 6755 | delete shim (needs a rw session) — or same-lane repeat |
| 4 | start shizuku-root server | kill server (dies itself on next reboot) |
| verify | `id`/`mount` | n/a |

Effect-style (estate standard):
```typescript
const applyRoot = Effect.gen(function* () {
  yield* Effect.tryPromise({ try: () => plantCvt(stick), catch: (e) => rollback(STEP1, e) });
  yield* Effect.tryPromise({ try: () => waitRemount(), catch: (e) => rollback(STEP2, e) });
  yield* Effect.tryPromise({ try: () => installSuShim(), catch: (e) => rollback(STEP3, e) });
  yield* Effect.tryPromise({ try: () => startShizukuRoot(), catch: (e) => rollback(STEP4, e) });
  const uid = yield* Effect.tryPromise({ try: () => run("id"), catch: e => rollback(VERIFY, e) });
  return uid.includes("uid=0") ? ok : fail("root not reached");
});
```
`rollback(step, e)` = run that step's inverse (also Result); failures are
logged with `monadicRollback` event tag, never swallowed.

## ROI (why root matters here)
- Direct panel PWM control (bypass the factory JNI wall).
- Vendor service calls (setBackLight/getBackLight / picture engine) via uid 0.
- SELinux permissive flip if needed for HAL tests.
- BlackDex true system-app dumps (vendor dex without UI fights).
- Everything else (IPTV, gate, overlay, Shizuku-shell) already solved WITHOUT root.

## Requirements from the user
- ANY FAT32 USB stick (even 64MB), or I can partition a tiny slice of 8TB? No —
  the TV reads USB sticks; plug one into the box's front/rear port.
- I auto-detect it (`lsblk` poll) and drive the rest.
- Instructed: this is the authorized root application (explicit); rollback = remove
  stick + power cycle; nothing boot-persistent unless confirmed.
64|## Flux route-intel verdict (2026-11-09)
65|`forge borrow "hisense android tv root factory cvt otaTest backlight pwm"` raced 3 github
66|repos — all NAME-collisions (generic React Native / Java "OTA" apps), zero pattern match
67|for the Hisense factory lane. The CVT/remount mechanics are proprietary firmware; the
68|correct validation is empirical: plug stick → observe `mount | grep ' / '` rw.

## ARMED 2026-11-09 04:13 (empirical, remote)
- Previous `vold` state on this stick: `public:8,1 unmountable 1B21-2321` (Android would
  NOT mount it → could not plant the file). Remote fix (all via adb, no PC):
  `sm format public:8,1` (accepted disk-style ID, NOT the bare UUID) → `sm mount public:8,1`
  → volume became `mounted 7EA6-0BF6` → `touch /storage/7EA6-0BF6/FAC_BOOT_SMART.cvt`
  → `sync` → verified 0 bytes (md5 d41d8cd98f00b204e9800998ecf8427e). **ARMED.**
- NEXT (user action): TV wall power-cycle with stick in rear USB. Then observe
  `adb shell "mount | grep ' / '"` → expect rw. Mount TEST ONLY first, per rollback doc.

## FacRootMenu findings (2026-11-09, LIVE)
- `com.hisense.uifac.action.FacRootMenu` launches the factory root menu (single activity).
- Dex shows `AdbEnabled`/`AdbStatus` methods + exit intents (`exit_factory_intent`) +
  props `persist.sys.factory.mode`, `ro.boot.bootenterfactory`.
- Entering the menu set `persist.sys.factory.mode`, and its network/factory scanner dropped
  wifi once — displayed a giant keypad dialog (`WIFI LOST`). **Safe exit = TV reboot**
  (verified: reboot → launcher home, factory-mode value returns 0).
- Post-reboot gate check: `adb root` still refused ("production builds"), no su → factory
  mode alone does NOT grant root. Next lever: re-enter FacRootMenu → flip **AdbEnabled**
  (likely sets adbd root / ro.debuggable) — not yet attempted, rollback = reboot.

## setAdbStatus RE chain (2026-11-09, deep)
- FacRootMenu → TVNative.setAdbStatus_native(boolean) @ libcom_mediatek_twoworlds_tv_jni.so
  (pulled /vendor/lib, exported JNI; strings "Enter JNI_setAdbStatus") → calls a_mtktvapi_* (MTK TV
  API into the vendor factory service). Not shell-reachable; no factory binder service visible in
  service list; no su; adb root refused (user+production builds); RECOVERY lane verified: adbd still
  shell, /system unmounted, SELinux enforcing → dead end.
- Remote root verdict: NOT reachable without the USB CVT factory-remount (physical) or a CVE.
- FacRootMenu is hostile: launch kills wifi (setWifiEnabled in dex), power key = full reboot.
