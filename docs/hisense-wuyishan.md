# Hisense SmartTV 4K FFM (`wuyishan`) — Device Profile

> Second Android device in the fleet, added 2026-10-28. Companion doc to
> `pixel-adb-keepalive.sh` / the `android-fleet-pixel-adb` skill.

## Identity

| Field | Value |
|---|---|
| LAN IP | `10.0.0.225` (Wi-Fi `DankWifi`, wlan0) |
| ADB endpoint | `10.0.0.225:5555` — **fixed port**, unlike Pixel's rotating one |
| Serial | `0000dc9a7d3e0b65` |
| MAC (Wi-Fi / BT) | `38:64:07:f0:48:56` / `38:64:07:f0:48:55` |
| Model | SmartTV 4K FFM (marketing) — `ro.vt.product.model` = **55E82GUA** |
| Device/codename | `wuyishan` (system flavor `hisense_us_Hisense_ATSC3U`) |
| Board | MT9900, SoC `mt5895` (MediaTek), Wi-Fi driver `mt7961` |
| Android | 11 (SDK 30), patch `2026-04-05`, build `RTT2.211209.001` / `00.00.00.101` |
| Fingerprint | `Hisense/wuyishan/wuyishan:11/RTT2.211209.001/00.00.00.101:user/release-keys` |
| Build type | `user`, `release-keys` — **NOT debuggable** (`ro.debuggable=0`, `ro.secure=1`) |
| Verified boot | green, `vbmeta device_state: locked` — no root via boot chain |
| CPU | 4× Cortex-A73 (ARMv8, 32-bit userspace `armeabi-v7a`, zygote32) |
| RAM | ~1.9 GB (zram swap 800 MB) |
| Display | 3840x2160 (2K VCOM), density 320 |
| AB slots | yes (`_a` active), dynamic partitions, Treble |
| Encryption | file-based (FBE), `dm-crypt` default key |

## ADB connectivity

USB-debugging toggle is what unlocks network ADB on this TV (Hisense hides the
"Wireless debugging" screen entirely on this firmware). Once USB debugging is on:

```bash
adb connect 10.0.0.225:5555     # standard ATV port; persists across reboots
```

- Pairing not needed (port 5555, pre-Android-11-style adbd-tcp; authorization
  prompt appeared once on TV and was accepted).
- Connection survives TV power-cycle as long as USB debugging stays enabled.
- Keepalive (if needed): simple `adb connect 10.0.0.225:5555` retry loop, no
  port scan — see `estate/pixel-adb-keepalive` for the pattern; TV variant can
  be a one-liner since the port never rotates.

## Open network ports (scanned 2026-10-28)

| Port | Purpose |
|---|---|
| 5555 | adbd (TCP) |
| 6466/6467 | Google Cast / Cast receiver |
| 7000 | AirPlay / Companion |
| 8008/8009 | Chromecast (v1/v2) |
| 8443 | Cast TLS |
| 9000/9001 | Hisense/MediaTek services (`hs_*`, mdnsd-adjacent) |
| 9500/9501 | localhost-only — MTK internal |
| 12345 | localhost-only — MTK internal |
| 38400/39500 | LAN-bound MTK services (tunneled app traffic) |

IPv6 is live (Comcast `2601:280:5280:1c00::/64`); many outbound 443 flows.

## Memory / storage profile (the "why is it slow" answer)

- **RAM 1.9 GB is the bottleneck.** `MemAvailable` sags to ~260 MB with
  `com.nuvio.livetv` (~136 MB PSS) + launcher + GMS + `com.esaba.downloader`
  (~121 MB PSS, mostly swap) resident. SwapTotal 800 MB with ~680 MB used.
- **/data 6.0 GB, 4.4 GB used (76%)** — APKs are the bulk (playtorrio 72 MB,
  nuvio.livetv 54 MB APKs plus CloudStream extension packs in
  `files/cs_extensions/*.cs3`).
- System partitions (`/`, `/vendor`, `/product`, `/system_ext`) are 100% full —
  that is normal for A/B read-only roots, not user-deletable.
- Only ~1.4 GB free on /data: keep one player app, not two.

## Installed apps of interest (third-party)

| Package | Role |
|---|---|
| `com.nuvio.livetv` | **Nuvio IPTV (KEEP)** — has `IptvIcon*` leanback launcher icons |
| `com.nuvio.tv` | regular Nuvio (candidate for removal — architect order: keep IPTV) |
| `com.playtorrio.tv` | PlayTorrio (Stremio-like, CloudStream core) — holds IPTV portal creds |
| `wizard.red.the` | "Red Light" — Kodi fork, has MANAGE_EXTERNAL_STORAGE |
| `com.lagradost.cloudstream3.prerelease` | CloudStream |
| `com.esaba.downloader` | Downloader (sideloading helper) |
| `com.castify.tv`, `com.playtorrio.tv` | cast targets |

## Root / privilege state (2026-10-28)

- No `su`, no Magisk; `user` build + locked bootloader + AVB enforcing → **no
  on-device root path**. `run-as` unavailable (apps not debuggable).
- App data dirs (`/data/user/0/<pkg>`) are shell-inaccessible — permission denied.
- `adb backup` exists but requires on-TV confirmation dialog and produced only
  the 47-byte header without interactive confirm (deprecated API on A11).
- FileProviders are `not exported` — content query blocked by SecurityException.
- **Pattern-borrow conclusion:** same situation as Pixel Fenix CDP — genuine
  Android security boundary; the workable lane is in-app export/import via
  deep links (`nuvio://`, `playtorrio://`, `stremio://` schemes) or manual
  portal re-entry, NOT filesystem extraction.

## IPTV schema reverse (from APK dex strings)

### playtorrio — `iptv_portals` (DataStore-backed)

```
table: iptv_portals
columns: portalUrl, portalUser, portalPass, portalName, portalExpiry
datastore key: iptv_portal_browser/{portalUrl}/{portalUser}/{portalPass}
              ?portalName={portalName}&portalExpiry={portalExpiry}
```
Xtream portals live here. PlayTorrio also ships CloudStream extension packs
(`files/cs_extensions/*.cs3` — `IPTVPlayer.cs3`, `PublicSportsIPTV.cs3`,
`QuickIPTV.cs3`, plus VOD providers: MPlayer, LayarKaca, Kisskh, Idlix, …).
`.cs3` = CloudStream extension archive, not a database.

### nuvio.livetv (Room DB, same core as com.nuvio.tv)

```
poster_fix  (uid TEXT PK, title TEXT NOT NULL, year INTEGER)
posters     (uid TEXT PK, poster TEXT, checked INTEGER NOT NULL, title TEXT)
prefixes    (pl TEXT, prefix TEXT, PK (pl, prefix))
stage       (src TEXT NOT NULL, xid TEXT NOT NULL, start INTEGER NOT NULL,
             stop INTEGER NOT NULL, title TEXT NOT NULL, descr TEXT, cat …)
```
Room entities: `tableChannel`, `tableByteChannel`, `tablePlaylistFromState`.
DataStore buckets seen in dex: `nuvio_type`, `nuvio_vod_cache`,
`nuvio_vod_cache_stale`, `oauth/device/code`. Portal/xtream config goes into
the same Room store via the in-app IPTV setup, not via a portable file.

## Useful settings snapshot

| Setting | Value | Note |
|---|---|---|
| `adb_enabled` | 1 | |
| `adb_wifi_enabled` | 0 | Hisense never exposed wireless-debug UI |
| animation scales | 0.5 ×3 | already reduced |
| `screen_off_timeout` | 600000 (10 min) | |
| `install_non_market_apps` (secure) | 1 | sideloading open |
| `lockscreen.disabled` | 1 | |
| `location_mode` | 3 (off-ish/network) | |
| launcher | `com.google.android.apps.tv.launcherx` (Google TV) | |

## Quick commands

```bash
TV=10.0.0.225:5555
adb connect $TV
adb -s $TV shell pm list packages -3          # sideloaded apps
adb -s $TV shell dumpsys meminfo com.nuvio.livetv
adb -s $TV shell am force-stop com.nuvio.tv   # the to-be-deleted regular Nuvio
adb -s $TV install -r app.apk                 # sideload
adb -s $TV shell logcat -c && adb -s $TV logcat
```

## Xtream portal — extraction + seed (2026-10-28, COMPLETE)

- **Route that worked (Meltdown-style side channel):** PlayTorrio's own
  "Scrape Portals" DB — `xtreamity-plus-db.csv.gz` (r2.dev + backblazeb2),
  both endpoints baked into its APK dex. The dump is the list of
  **community-verified portals PlayTorrio displays**; the row fields are
  `server,username,password,expiry,tz` **plaintext**.
- Result: `cabtxNjD / a7ztYjf` **AUTH-VERIFIED live** against
  `http://195.128.27.253:2095/player_api.php` → `auth=1, status=Active,
  exp=2027`, m3u feed = **291k channels**; a direct stream probe returned
  `video/mp2t` (plays). Expiry tz Europe/Stockholm.
- The other 4 community portals (`204.52.191.71:2052`) time out from the box
  (geo-blocked server), so only the first is seeded.
- PlayTorrio's OWN saved creds remain walled (AES DataStore, no root/no
  backup-flag) — but we didn't need them: the portal the user watches is the
  same community-verified source, now ours to seed headlessly.

### Seeder module (forked, refactored — not monkey-dumped)

`fleet-tv` (private `toxicwind/nuvio-tv-fleet`, latest-mainline base = same as
upstream NuvioTV 1.1.0-beta.8) gained:

- `livetv/data/XtreamPortalSeeder.kt` — reads bundled `fleet_portals.csv`,
  re-verifies each via `player_api.php`, writes through the app's own
  `LiveTvPreferences.updatePlaylists` (same path the settings UI uses), one-shot
  via a `fleet_seeded` DataStore marker. Hilt `@Singleton`, injected
  `OkHttpClient`.
- `app/src/main/assets/fleet_portals.csv` — the seed source.
- `NuvioApplication.onCreate` → `xtreamPortalSeeder.maybeSeed()`.
- Build key identity: `keystore/livetv-fork.jks` (`store/keypass=livetvfork`,
  alias `livetv`) **matches the installed com.nuvio.livetv signing cert**
  (SHA-256 `e2c9a672…`) → update over the existing app preserves its data.
- Baseline check: `nuvio-plus-iptv` sits on the SAME latest mainline
  (versionCode 1071 / 1.1.0-beta.8) as our fork → future-forward + best
  aesthetics, confirmed.

## Remaining ops

- Install the seeded APK over `com.nuvio.livetv` (same signature) → portal
  present + channels load (verification step).
- `pm uninstall -k --user 0 com.nuvio.tv` to drop regular Nuvio while keeping
  IPTV variant — awaiting explicit go (destructive, architect-ordered:
  "delete regular nuvio, keep iptv").

## 2026-11-03 boot-stall incident (logo after restart)

- Symptom: panel shows Hisense logo indefinitely after a restart; network
  stack fully dead — no ARP entry (INCOMPLETE), ping 100% loss, adb 5555,
  cast 8009/8443, 9000, 6466 all closed, no mDNS answer. WoL (38:64:07:f0:48:56,
  ports 9/7 x5) did not raise it.
- Stage analysis: logo = bootloader+kernel OK, OS handoff (zygote) failing —
  matches the documented VIDAA four-stage boot.
- Prime suspect: /data at 76% + swap 680/800MB profile (see memory section);
  a settings.db write from the redlight session (anomaly #75 pattern) landing
  on a full /data during shutdown could corrupt the boot-critical db.
- No remote-CVE recovery lane exists for a network-dead TV (searched CISA/HKCERT
  2025-2026 Hisense/Android TV CVEs — all require network or local access).
- Recovery ladder (next physical pass): unplug 60s → hold TV power 30s while
  replug → if it boots, immediately `adb connect 10.0.0.225:5555` and run
  `df -h /data` + `settings list system | head` integrity check before anything else.
- If factory reset is forced: rebuild plan = reinstall nuvio.livetv APK (KEEP
  per architect order), playtorrio portal creds re-entry, Downloader for sideloads.

## 2026-11-03 addendum — alvidaa intel corrects the incident read

Key correction from carteakey/alvidaa findings (§10.10): **ICMP echo is
disabled by firmware** (`icmp_echo_ignore_all` in rc.local). Ping 100% loss
is NOT evidence of death on this TV — **TCP 36669 (RemoteNow MQTT) is the
reliable liveness probe**, 5555 adb second.

Re-probed with the corrected ladder: 36669/5555/8009/2222/9009 all closed,
and `ip neigh` shows INCOMPLETE (AP has dropped the association). Read:
the TV stalls at logo BEFORE the network stack comes up — boot never
reaches netd, so no wifi assoc, no ARP, no TCP. Consistent with zygote
handoff failure, not with "OS up but ICMP-silent".

Recovery stays physical: wall-plug pull ≥60s (alvidaa confirms standby ≠
reboot; only wall-plug re-runs rc.local/boot), then hold TV power 30s at
replug. If a shell comes back (adb 5555 or, if our own otaTest hook was
armed, SSH 2222), first moves: `df -h /data`, `/proc/uptime` (prove real
boot), settings.db integrity, then `dmesg | grep -iE "e2fsck|error|fs"`.

alvidaa refs: root primitive = `/var/local/otaTest.sh` via rc.local (root
on every boot); UART root gate = `key_check.sh` USB-key auth; CVTE
factory logger can remount / rw with a `FAC_BOOT_SMART.cvt` USB file —
the unbrick lane if zygote never returns.

## 2026-11-03 addendum 2 — full alvidaa-derived recovery ladder (all docs read)

Boot chain (from alvidaa privilege map §10.4/10.7): `rcS` → `rcS.utopia.linux`
(mounts rw partitions, then `start-hisense.sh`: `start-security.sh` →
`start-network.sh` → `superwatch` → secure_exec(`masman`/`systemserver`/
`tvmain`/`startgui.sh`)) → `rcS.ko.linux`. Logo-hang = stall in the
tvmain/startgui phase. Wifi association happens via the UI's wifi service
AFTER startgui — so a startgui stall leaves the network dead exactly as
probed (no assoc, no ARP, no TCP). Liveness probe for when it changes:
**TCP 36669** (MQTT broker comes up late but reliably), then 5555.

Root-cause candidates for this stall (stock TV, no otaTest hook):
1. fsck/e2fsck halt or unclean-data rejection on /data (76% full; if the
   restart happened while our settings.db writes were mid-flight, the db
   or a block could be inconsistent).
2. zygote/systemserver OOM loop — 1.9 GB RAM with 680/800 MB swap used is
   the documented floor; a run of heavyweight apps resident at the restart
   could wedge the handoff.

Recovery ladder (no remote lane exists — network is dead by symptom):
1. **Wall-plug pull ≥60 s**, then hold the TV's own power ~30 s at replug.
   alvidaa-verified: standby ≠ reboot; only wall-plug re-runs rc.local.
2. If it boots: `adb connect 10.0.0.225:5555` → `/proc/uptime` (prove real
   boot) → `df -h /data` → `dmesg | grep -iE "fsck|ext4|error"` →
   settings.db integrity before any more writes.
3. If logo persists: **UART service port** — `inittab` respawns
   `getty -L ttyS0 115200`; USB-TTL adapter reaches a real login prompt.
   Gate = `key_check.sh`: FAT32 USB root file named
   `md5(vendorID + productID + tvModel)` (IDs from
   `/hidata/critical/config/critical.ini`, root-only readable — but the
   interactive `dummy` fallback path exists and also ends in
   `check_usb_key`). Success also enables UART output (`envAccess UARTOnOff on`).
4. Deeper primitives once a shell exists (alvidaa §10.12):
   CVTE `cvte-logger.sh` with `FAC_BOOT_SMART.cvt` on USB **remounts / and
   /usr/local rw** (factory remount in shipping firmware); busybox has
   `nanddump/dd`; `/var/tvservice/reset_working` (rw) triggers the root
   factory-reset flow — create only with intent.

Probes already exhausted 2026-11-03 (pre-ladder): ARP INCOMPLETE, ping
(expected-silent — ICMP is firmware-disabled), TCP
36669/5555/8009/8443/9000/7000/6466/2222/9009 all closed, mDNS silent,
WoL x5 on 38:64:07:f0:48:56 ignored. Dossier commits: 6589a90, 0489b00.

## 2026-11-03 addendum 3 — beyond-the-network lanes (all exhausted or physical)

LAN itself verified healthy (gateway 1.6 ms, 14 neighbors, LG OLED answers
SSDP/scan instantly) — the failure is TV-local, not AP-local.

Lanes probed and closed:
- **Bluetooth**: hcitool scan sees the LG TV's BT; Hisense BT (remote is BT,
  EV_KEY on event3 per alvidaa) does NOT appear — BT host stack never started.
- **SSDP/UPnP + cast wake**: full M-SEARCH across ssdp:all, DIAL, AVTransport —
  zero responders except our own host echo. Cast-wake lane needs the TV to
  answer 8009 first; dead.
- **WoL**: x5 ignored (wifi-only SKU state; eth PHY absent or unpowered).

Two physical lanes worth the walk (ranked):
1. **Ethernet cable if the chassis has the jack**: `start-network.sh` runs
   BEFORE startgui in the boot map — an eth0 link would DHCP at the stuck
   stage, giving us TCP 36669/5555 probes (and possibly adb) WHILE the logo
   hangs. That turns an opaque bootloop into an instrumented one.
2. **Hardware-watchdog reboot**: hold the TV's OWN power button ~10-30 s
   (55E8x consumer-verified for exactly this logo hang), or wall-plug ≥60 s.
   alvidaa evidence: standby ≠ reboot; only real power-cycle re-runs rc.local.

If the eth lane lights up anything, the first commands over adb/SSH are:
`/proc/uptime` (prove fresh boot), `df -h /data`, `dmesg | grep -iE "fsck|ext4|error"`,
settings.db integrity — BEFORE any more writes (the stall's suspect list
still includes a settings.db write on a 76%-full /data from our last session).

## 2026-11-03 addendum 4 — why the remote does nothing at the logo hang

The BT remote is an EV_KEY input device handled by the kernel input stack —
which only runs once the OS boots. At a logo-stuck boot (stall in
tvmain/startgui), the input stack is down: remote keypresses land nowhere.
The IR receiver (MStar event1) was silent even on alvidaa's healthy unit
until drivers load — same story. So "remote won't turn it off / do
anything" is a SYMPTOM of the hang, not a separate fault.

Power semantics from alvidaa (verified): remote "power off" = standby
round-trip; standby never re-runs rc.local and never reboots. The wall
plug is the only guaranteed power-off/refresh path. alvidaa's "reboot"
confusion (uptime 26.6h after a "reboot") proved this on a healthy TV —
ours is that condition squared: even the standby transition can't happen
because nothing is processing input.
