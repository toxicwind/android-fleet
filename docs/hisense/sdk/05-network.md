# 05 Network — surface, ports, gating, wake (SDK-deep)

## Liveness ladder (hisense-tv-liveness convention)
1. TCP 36669 RemoteNow MQTT (late in boot; can be DOWN while the box is healthy — observed 2026-11-08)
2. TCP 5555 adbd (FIXED port, pre-A11 adbd-tcp; pairing not needed)
3. cast 8009/8443, 9000/9001, 6466/6467, 7000 airplay
- ICMP disabled by firmware (`icmp_echo_ignore_all`) — ping proves nothing.
- `ip neigh` INCOMPLETE = AP dropped the association (boot stall signal).

## Port inventory (scan 2026-10-28)
5555 adbd · 8008/8009/8443 cast · 7000 airplay · 9000/9001 hisense/mdns ·
9500/9501,12345 local MTK · 38400/39500 tunnel. IPv6 live (Comcast).

## Panel front is an N1 edge (players only)
- `/player_api.php` (API) → 200 auth for valid creds.
- `/live/u/p/id.m3u8` → **407 Proxy-Authentication-Required** with
  `N1-Backend: Echo` (+ random N1-Request names) for EVERY client incl. CFFI
  browsers — the stream edge is player-gated, NOT geo. Root/index paths 401/404.
- Working flow (android-iptv.com proven): `/live` → **302** → `lb-N.host?token=<minted>`
  → **m3u8 200** + tokenized `/hlsr/` segments `.ts 200 video/mp2t` when the client
  sends **`VLC/3.0.20 LibVLC/3.0.20` UA + Range: bytes=0-N**.
- Bare-UA following the 302 = 403 from the LB.

## Wake lanes
- WoL (ports 9/7 to 38:64:07:f0:48:56) ignored (wifi-only SKU).
- Power-cycle semantics: standby ≠ reboot; wall-plug pull ≥60s is the reboot
  (rc.local re-runs); hold TV power 30s = hardware watchdog.

## adb specifics
- USB-debugging ON unlocks 5555; persists across reboots as long as enabled.
- No pairing flow (pre-A11 style). Authorized once.
- Interactive VPNs/hosts: `atv`/`atvs` helpers via bashx prelude.