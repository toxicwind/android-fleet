# Sync Pipeline Refactor — Latency-Aware Architecture (recalibrated 2026-11-11)

**Doctrine:** the tau latency doctrine applies to sync too — measure ttstMs /
worstGapMs / gapP50 / gapP90 per phase, adapt to the device, never block the
visible surface. The basic prepared-statement fix is the FLOOR, not the goal.

## Measured baseline (129K-channel first sync)

```
LIVE chunk raw=10000 applyMs=25087  (2.5 ms/row)
LIVE chunk raw=10000 applyMs=55666  (5.6 ms/row — device under load)
SERIES chunk raw=1094 applyMs=4178  (3.8 ms/row)
MOVIES chunk raw=10000 applyMs=~30s (est.)
```

`worstGapMs` = 55.7 s between visible catalog updates on a 52K source.
`gapP50` ≈ 25 s. That's the latency problem: the UI shows "0 channels" for
minutes while the bulk import runs.

## The latency-aware design (3 layers)

### L1 — Prepared-statement batch (the floor, already implemented)

Single INSERT OR IGNORE statement, one immediate transaction, 10K binds.
Expected: applyMs 25-55 s → 1-2 s per chunk. This alone fixes the floor.

### L2 — Adaptive chunking (AIMD, mirroring verify.ts stealth scheduler)

Chunk size is NOT constant. Track per-chunk applyMs and adapt:

```
chunkSize = clamp(
  chunkSize * (targetApplyMs / measuredApplyMs),   // AIMD-ish multiplicative
  2_000, 25_000
)
targetApplyMs = 2_000   // 2s per chunk = visible progress every 2s
```

- Device idle → chunks grow (25K), sync finishes fast
- Device under load (playback, EPG parse) → chunks shrink (2K), UI stays
  responsive, applyMs stays ≈ target
- Report `syncLatency = { chunkP50, chunkP90, worstGapMs }` in SyncRunStats —
  the same shape as tau's ttstMs/gapP50/gapP90, so regressions are visible

### L3 — Priority-aware scheduling (the "EPG is the moat" insight)

Don't import 52K channels in catalog order. Import in visibility order:

1. **Favorites + history + resume channels FIRST** (user's actual content) —
   the app already has `CatalogPriority`; wire it into the chunk producer so
   the first chunk is the user's channels.
2. **Catchup-capable channels next** (tv_archive=1) — they're the diamonds.
3. **Everything else** in provider order.

Result: the UI shows the user's channels + EPG within the FIRST chunk
(~2 s), not after 5 minutes. `worstGapMs` for visible content ≈ 2 s.

### L4 — Streaming progress (gap telemetry)

`SyncCounters` already reports per-phase counts. Add:
- `chunkLatencyMs` rolling window (last 10 chunks: p50, p90, max)
- `estRemainingMs` (chunksLeft × chunkP90) — the import screen shows a real
  ETA instead of an indeterminate spinner
- Emit through the existing `CatalogSyncWorker` progress Data so the UI pill
  shows `2.1s/chunk · ~40s left` instead of `Syncing…`

## Implementation order

1. [x] L1: `insertAllPrepared` (BulkInsertHelper) + channel binder + insertFresh
       routing — DONE, needs build + benchmark
2. [ ] L2: adaptive chunkSize in `SyncSupport.chunked` (AIMD on applyMs)
3. [ ] L3: CatalogPriority → first-chunk favorites/catchup ordering
4. [ ] L4: latency telemetry in SyncRunStats + progress Data + UI pill
5. [ ] Benchmark: re-sync source 1, compare applyMs + worstGapMs before/after

## Success criteria (latency, not just speed)

- `worstGapMs` for the visible catalog: 55.7 s → < 3 s (L1+L2)
- First visible content (favorites/catchup): < 5 s from sync start (L3)
- UI pill shows real ETA from chunkP90 (L4)
- Full 52K source: 5 min → < 60 s total (L1 floor)