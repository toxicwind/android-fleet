# nuvio.livetv — "restarts at same point, not live" bug diagnosis (2026-10-28)

**Symptom (Architect):** channels restart at the same point instead of playing a
live edge; "not live at all".

**Source found:** the TV's installed `com.nuvio.livetv` is a branded build of
`homelessbrian/NuvioPlusIPTV` (vendored at `vendored/nuvio-plus-iptv/`; the
`nuvio-livetv.patch` at its root IS the IPTV deltaset, incl. `applicationId
com.nuvio.livetv`). The live TV plumbing lives in
`app/src/main/java/com/nuvio/tv/livetv/`.

## Root cause: the stall→reconnect loop, not the live config

The live-edge config itself is **correct**:

- `LiveTvPlaybackController.buildMediaItem(url, isLive=true)` → builds a real
  `LiveConfiguration` with a **10–45 s live offset** and locks playback speed to
  1.0× (`player.setPlaybackSpeed(1f)`), so live streams legitimately start fresh
  from the edge.

The bug is the **error/stall recovery loop** in the SAME controller:

- `STALL_MS = 15_000` and `MAX_RECONNECTS = 8`
- Under the Hisense `mt5895`'s memory pressure (1.9 GB RAM, swap pressure), a
  slow HLS segment fetch >15 s trips `STALLED` → the reconnect path re-prepares
  the media item → **`goLiveFresh()`** ("Drops the buffer and plays the channel
  again from now") runs repeatedly.
- Net effect on screen: playback jumps back to the live edge every stall — reads
  as "restarts at the same point / never advances" if stalls dominate, or as a
  repeat-loop when the source is a 24/7 loop and the player keeps resetting to
  `start=0`-ish offset.

Supporting evidence in repo:
- `LiveTvPlaybackController.kt:211` — `goLiveFresh()` drops buffer, plays "again
  from now".
- `LiveHlsRepair.kt` — HLS repair/restart triggers wired into stall handling.
- `CatchupUrlBuilder.kt` is an **empty stub** in this fork — no catchup/timeshift
  URL shaping, so reconnects can't request a mid-stream resume; they go live-fresh.

## Fix directions (ranked)

1. **Raise `STALL_MS` / lower reconnect aggression** — 15 s is tight for a
   memory-starved TV; 30–45 s + exponential backoff kills the visible loop.
   One-line-ish config change, lowest risk.
2. **`goLiveFresh()` gate** — only reconnect when `catchupTitle == null &&
   player != null` already guarded; add a "reconnect but seek back to previous
   offset" path so a stall resumes, not restarts. This is upstream PR #4227's
   "skip-live-resume" idea → glean target:
   `glean 'https://github.com/NuvioMedia/NuvioTV:app/src/main/java/com/nuvio/tv/...'`.
3. **Memory diet** (the enabler): the TV runs TWO Nuvio-based apps resident
   (`com.nuvio.tv` + `com.nuvio.livetv`). Removing `com.nuvio.tv` (architect's
   standing order, post-cred-extraction) frees real RAM for the IPTV build.

## Status

Diagnosed in source, not yet patched. Patches land in the private fleet base
`toxicwind/nuvio-tv-fleet` (via glean), then a rebuilt APK installs on the TV
(`adb -s 10.0.0.225:5555 install -r`). Do not patch the TV's current APK
directly — the rebuild IS the migration vehicle.