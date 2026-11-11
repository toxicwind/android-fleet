# Android fleet devices

> Full dossiers: `pixel` → `android-fleet-pixel-adb` skill; Hisense TV →
> `docs/hisense-wuyishan.md` (deep recon: hardware, ports, IPTV schema
> reverse, root-state verdict). Authoritative inventory: `ssot/devices.md`.

## Pixel 9 Pro XL — the Architect's phone

- LAN IP 10.0.0.77, ADB over wireless debugging. Connect port rotates
  (currently ~44933); pairing persists in `/home/toxic/.android/adbkey*`.
- `bin/pixel-adb-keepalive.sh` (pitchfork daemon) holds the session: reconnect
  every 60s, fast port re-scan (30000-50000) when the port rotates.
- Android 17. Auth vault (Bitwarden, Authy) + Fennec browser.

## Hisense SmartTV 4K FFM `wuyishan` — bedroom (OwnTV host)

- 10.0.0.225:5555, ADB authorized 2026-09-18 (was `unauthorized` until the
  Architect approved the on-TV dialog).
- Android 11 (SDK 30). **OwnTV IPTV host** — `tv.own.owntv` v99.99.99 (our
  fork), plus nuvio `com.nuvio.livetv`, Kodi fork `wizard.red.the`, playtorrio,
  cloudstream.
- Lua control plane: `luaj-dropin` on :38383 (via adb forward).
- 2026-10-28 deep recon: codename `wuyishan` (55E82GUA), mt5895 SoC, `user`
  build, NO root path (locked AVB, no su/Magisk, non-debuggable apps).
  Full dossier: `docs/hisense-wuyishan.md`.
- Headless UI automation: see `docs/ui-automation.md`.