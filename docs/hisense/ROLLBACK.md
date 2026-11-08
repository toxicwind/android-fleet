# Hisense recon — rollback-on-fail contract (2026-11-09)

Every experiment below is **additive/reversible**. Nothing destructive, no
forced state that can't be undon. Checkpoints before risky steps.

## Change inventory + revert
| change | what | friendly rollback |
|---|---|---|
| APK updates (install -r) | nuvio fleet builds replace app, KEEP data | `git` refs (06a8e61c5..03) — rebuild any prior commit to restore; prefs/DataStore survive install -r |
| Black overlay | extra window, OFF by default | process death auto-cleans; toggle key toggles off; no persistent state |
| screen/brightness | settings writes only | `adb shell settings put system screen_brightness 120` + `cmd display set-brightness 0.6` |
| Shizuku server start | process only | dies on TV reboot automatically |
| Shizuku APK | installed app | `adb uninstall moe.shizuku.privileged.api` |
| Keeper daemon | awrawr-pc pitchfork daemon | `pitchfork stop estate/hisense-shizuku-keeper` |
| Billboard overlay (pending) | future feature | OFF default; auto-clear |

## Root-lane safety (USB factory remount / cvt)
- Lane effect is session-local per the factory logger; removing the USB stick +
  a power cycle reverts (no persistent boot code from the .cvt itself).
- We will NOT write boot-persistent code until the stick lane is confirmed.
- First attempt = mount test only (`/` rw visible), no writes.

## TV state checkpoint (2026-11-09 ~03:30)
- nuvio APK: fleet build v1062 (last DEPLOYED) — data preserved.
- Shizuku: package installed; server under keeper.
- Panel: reference state for experiments = Awake + content; rollback via brightness cmds.
- Backups: `/home/toxic/pens/fleet/devices/android-fleet/docs/hisense/raw/` dumps; git-committed.

## Verify-after-failure (the reflex)
1. `adb devices` — adbd up.
2. `dumpsys power` — expected wakefulness.
3. `pitiful` — no; `pitchfork status estate/hisense-shizuku-keeper` — running.
4. If nuvio misbehaves: `am force-stop` + relaunch; worst case `install -r` same APK.