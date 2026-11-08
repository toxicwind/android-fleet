# Network surface — port → service → owner (proc-level evidence)

Method: `/proc/net/tcp{,6}` listener map (state 0A) + `pm list packages -U` uid
resolution + APK token-walk for protocol grammar. Probe date 2026-11-09.

| Port | Service | Owner (uid) | Evidence / notes |
|---|---|---|---|
| 5555 | adbd (TCP) | adbd | `sys.usb.config=adb`; USB debugging ON |
| 9001 | ATSC 3.0 RMP WS JSON-RPC | `com.vt.interact` (10066) | ws upgrade 101; method table from libwsapi; live deviceInfo capture → rmp-9001.md |
| 9000 | same stack (HTTP?) | `com.vt.interact` (10044 per tcp6 uid) | 000 on curl; likely alternate bind |
| 8008 | same stack HTTP | `com.vt.interact` (10044) | 404 on all tested paths (needs real resource) |
| 8443 | HTTPS variant | same | TLS-only; 000 with curl |
| 8009 | AirPlay/DD | AirPlayLaunchService (dtv stack, pid 1912) | config-capsule leak (anomaly #15) |
| 6466/6467 | unknown | uid 10047 | silent banners; no protocol yet |
| 7000 | unknown ftp-ish | uid 1020 | silent |
| 43815 | unknown | uid 10044 | silent |
| 38400/39500 | unknown | uid 10076 | silent |
| 8099 | factory demura upload | uifac TcpServer (SYSTEM) | CLOSED by default; starts via serial IPC cmd (factory-serial-console.md) |

Lanes discovered-but-closed: 36669 (RemoteNow MQTT — not listening),
9002 (nothing), service ports (RCP/SDAP variants).

Stale/ghost: `/dev/block/sdb*` nodes exist but are NOT live (absent from
`/sys/class/block`; vold registers only `disk:8,0` = internal eMMC) — anomaly #14.