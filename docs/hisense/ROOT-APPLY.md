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
## Flux route-intel verdict (2026-11-09)
`forge borrow "hisense android tv root factory cvt otaTest backlight pwm"` raced 3 github
repos — all NAME-collisions (generic React Native / Java "OTA" apps), zero pattern match
for the Hisense factory lane. The CVT/remount mechanics are proprietary firmware; the
correct validation is empirical: plug stick → observe `mount | grep ' / '` rw.
