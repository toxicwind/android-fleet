# Nuvio fork network — optimization matrix (2026-10-28)

Provenance for `fleet-tv/` (private `toxicwind/nuvio-tv-fleet`): which fork
contributes what, and how the IPTV merge was landed.

## The private base

| | |
|---|---|
| Repo | `github.com/toxicwind/nuvio-tv-fleet` (**private**) |
| Local | `pens/ranch/android-fleet/fleet-tv/` |
| Upstream | `github.com/NuvioMedia/NuvioTV` (tracked as `upstream` remote, full history) |
| Base | `9f17e8b` + **`nuvio-livetv.patch`** (the IPTV conversion diff) |
| Build | `com.nuvio.livetv` — matches the Hisense TV's installed IPTV app (verified: same `MainActivity` identity + `xtreamServer/Username/Password` PlaylistSource fields via dex reverse) |

## Why this is the TV's app

`homelessbrian/NuvioPlusIPTV` carries a `nuvio-livetv.patch` + `com.nuvio.tv.livetv`
source package whose data layer (`LiveTvRepository`, `LiveTvPreferences`,
`PlaylistSource{… xtreamServer, xtreamUsername, xtreamPassword …}`) is a 1:1
match with the strings pulled from the TV's `com.nuvio.livetv` APK dexes.
Upstream `NuvioTV` has none of it — the IPTV build is a fork overlay, now ours.

## Fork matrix (all vendored under `vendored/`)

| Fork | Strengths | Status |
|---|---|---|
| `nuvio-plus-iptv` (homelessbrian) | **The IPTV overlay** — M3U/EPG, TiviMate-style guide, recording, timeshift, catchup, DRM/ClearKey | Merged via patch into fleet-tv ✅ |
| `nuvio-plus-iptv-es` (esenciable) | Sports: per-match source channels, Magis VOD engine port, team-pair join | Candidate gleans (sports/, ondemand/) |
| `nuvio-arc` (ysosrs123) | Seek thumbnails, Jellyfin/Emby/Silo, watch party, release calendar, lossless surround, DTS-HD/X passthrough, TorBox backoff | Candidate gleans (player/, assessment/, audio) |
| `nuvio-tv` (upstream) | Baseline — active merges daily (skip-live-resume #4227, libass, codec badges) | Tracked as `upstream` remote |
| `nuvio-iptv-m3u` (esenciable) | **Nightly-playlist feed**: m3u + XMLTV with tvg-id regeneration | Data source for the app's M3U/EPG |
| `stremio-iptv-addon` (Speaker300) | iptv-org-powered live-TV addon (optional premium M3U) | Candidate pattern for m3u feed packaging |
| `m3u-toolkit` (ours, private) | Sovereign M3U Live Engine: RFC8216 parser, socket prober, truth-group clusterer, 329-channel FAST master | Playlist data + telemetry for the fork |

## Live-bug diagnosis (the "restarts at same point, not live" report)

From `LiveTvPlaybackController.kt` (merged in fleet-tv):

- Live handling is **correct on paper**: `isLive=true` → `LiveConfiguration` with
  target offset 10–45 s and playback speed locked at 1.0x (no live-edge chase).
- The restart loop lives in the **resilience layer**:
  `STALL_MS = 15_000` (no progress → reconnect) + `MAX_RECONNECTS = 8` +
  `FRAME_TIMEOUT_MS = 2_500` + `LOADING_AFTER_MOVE_MS = 7_000`.
  On the Hisense (MT5895, 1.9 GB RAM, swap pressure), a slow HLS segment >
  15 s triggers `reconnectJob` → `goLiveFresh()` → full re-prepare → the stream
  visibly "restarts at the same point". The SCTE-35 / "not really live" feel is
  the replay of the same channel fom the live edge after each reconnect.
- Fix candidates (next lane): raise `STALL_MS` (e.g. 30 s) or skip
  `goLiveFresh` on reconnect when `LiveConfiguration` is live; or drop
  `MAX_RECONNECTS` churn with backoff. Upstream PR #4227 "skip-live-resume"
  already exists — glean it.

## Glean helper

`tools/glean/src/glean.ts` — cross-repo line-range cherry-picker:

```bash
bun tools/glean/src/glean.ts 'DIR:path:6-8' dst        # lines 6-8 of a local file
bun tools/glean/src/glean.ts 'DIR@ref:path' dst        # whole file at a ref
bun tools/glean/src/glean.ts 'URL:path:40-80' dst      # from any remote, shallow clone
```

Tested live against nuvio-arc (whole-file DTS util + import-range snippet).
Next tranches: glean `skip-live-resume` from upstream, sports modules from the
es fork, DTS passthrough + TorBox backoff from nuvio-arc into fleet-tv.

## Keystore note

`keystore/livetv-fork.jks` (2.5 KB) came from the fork tree (the patch's binary
diff lacked a full index line, so it was copied directly). Keep it private —
it signs the IPTV APK.