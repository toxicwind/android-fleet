# Xtream connection capacity — gate recon (2026-11-08)

Discovery + design notes for the connection-limit gate. Captured live while
implementing; evidence below is probe output, not inference.

## Where "limit" / "inuse" come from

Every Xtream-Codes portal exposes current user capacity in `player_api.php`
`user_info`:

| Portal | max_connections | active_cons | read |
|---|---|---|---|
| `195.128.27.253:2095` (cabtxNjD) | **1** | 0 | single-slot portal — ANY other stream kicks us off |
| `go.iptvhn.net:25461` (5213) | 5 | 0 | small shared portal |
| `android-iptv.com:80` (NTVBESTDIG) | **2** | **1** | one slot already in use by another streamer |
| `94.130.11.12:8080` (@Tv4Krestream) | **550** | **246** | heavily-shared portal (246 others live) |

### The "odd 250-user one" decoded

`94.130.11.12:8080` (`@Tv4Krestream`) is a **reseller/wholesale-tier
account**: `max_connections=550` (the reseller license tier these panels
sell), `created_at=2025-09`, `exp_date=2027-10`, `is_trial=0`. And
`active_cons` moves in real time: **246 → 244 between two probes seconds
apart** — 240+ real streamers on it, ~44% of its license at this hour.
Conclusion: a shared pool with slack, not our personal slot; the gate just
needs to respect the (distant) ceiling and never add to a fight when it
gets there. The other extreme is `195.128.27.253` (max=1) — a
single-personal-slot account where ANY second stream starts the eviction
fight immediately.

Probe: `GET http://<server>/player_api.php?username=<u>&password=<p>`
→ `user_info.auth` (1=ok), `status` (Active), `max_connections`,
`active_cons`. Timestamps/exp also available.

## The fight

When `active_cons` reaches `max_connections`, the portal evicts older
sessions to admit the new one, or rejects playback server-side. Two
streamers on the same single-slot account bounce each other forever:
connect → evict the other → the other reconnects → evict us. The only fix
is not to open a stream at all on a saturated portal.

## Gate design (implementation parity)

- **Entry**: `LiveTvViewModel` channel-select → `playback.play()` (both
  the plain play + catchup paths at ViewModel:184/195).
- **Check**: `XtreamCapacityGate.check(LiveChannel)`:
  - resolve the channel's `sourceId` → `PlaylistSource`; no `xtreamServer`
    (M3U) → **Allow** (no probe).
  - unknown source → **Allow** (fail-open).
  - else probe `player_api` (injectable `probe` fn; default real HTTP).
  - `active_cons >= max_connections` → **Blocked(portal, max, active)**.
  - probe error / missing fields → **Allow** (fail-open; never brick live
    TV because a portal is flaky).
  - **cache per (server,user) for 60 s** — playback retries/channel hops
    don't hammer player_api.
- **UX**: Blocked surfaces "Portal <name> is at its connection limit
  (active/max) — in use by other streams" instead of buffering forever.
- Explicitly NOT auto-switching portals in v1 (adding silent cross-portal
  failover changes channel identity; log a future lane).

## Single-slot tuning note

`195.128.27.253:2095` max=1 means only ever play it when `active_cons=0`,
and a second TV screen on the same account WILL fight. Portals with
slack (94.130.11.12: 246/550) are safe to hammer.

## Test contract (TDD, `XtreamCapacityGateTest`)

m3u-no-probe / free-allow / saturated-block / ttl-caches / fail-open /
missing-portal-allow / one-below-max-allow.

## Status

- [x] player_api fields confirmed live (4 portals above)
- [ ] gate implementation + tests green
- [ ] ViewModel wiring + build + deploy + live verify