# Sync Pipeline Refactor Audit — 129K-channel import (2026-11-11)

**Observed:** first full sync of 6 Xtream sources imported 129,451 channels +
140,056 movies + 24,016 series in ~3 minutes. Per-chunk timing:
`LIVE chunk raw=10000 applyMs=25087` and `applyMs=55666` — 2.5–5.5 ms/row.

## Bottleneck analysis

The insert path for a fresh import:

```
SyncSupport.insertFresh(rows)
  → rows.map { adapter.copyWith(it, null, adapter.hashOf(it)) }   // content hash
  → adapter.insertAll(hashed)                                       // Room @Insert
```

`ChannelDao.insertAll` is `@Insert(onConflict = OnConflictStrategy.IGNORE)` —
**Room generates one INSERT statement per entity**. A 10,000-row chunk becomes
10,000 individual INSERTs inside one implicit transaction. At ~2.5–5.5 ms/row
that's the observed 25–55 s per chunk, and 52 K channels ≈ 5 chunks ≈ 2–5 min
just for LIVE.

The codebase ALREADY has the fast path: `BulkInsertHelper.usePrepared(...)`
(used for `updateChannelStreamUrls`, the index-drop/restore machinery, and the
FTS rebuild). The sync simply never uses it for the fresh-insert path.

## Proposed refactor

1. **Add `insertAllPrepared(table, rows, columns)` to BulkInsertHelper** — one
   prepared INSERT statement, `bindText`/`bindLong` per column, `step()` +
   `reset()` per row, all inside a single `immediateTransaction`. This is the
   same shape as `updateChannelStreamUrls` which already batches.

2. **Route `insertFresh` through it** — `SyncSupport` gets a prepared-insert
   adapter hook (the `ContentAdapter<T>` already provides column access via
   `copyWith`/`hashOf`; add a `writePrepared(statement, row)` lambda).

3. **Expected gain:** 10 K rows via prepared statement ≈ 1–2 s (SQLite does
   ~5–10 K binds/sec on a TV-class SoC in a single transaction vs 10 K
   statement parses). Import time for 52 K channels: ~5 min → ~30–60 s.

4. **Keep the Room `@Insert` path** for the stable/upsert path (hash-diffed
   rows are few) and for non-optimized tables.

## Secondary observations

- `computeContentHash()` runs per-row on the writer coroutine — could be
  moved to the producer (parse) side, but the rendezvous channel already
  overlaps parse/write so the win is marginal.
- `CHUNK_FRESH = 10_000` is right for prepared inserts (fewer transactions);
  the per-row statement count was the cost, not the chunk size.
- Movies imported 140 K in the same window — same fix applies via
  `MovieDao.insertAll` / `SeriesDao.insertSeries`.

## Status

- [ ] Implement `insertAllPrepared` in BulkInsertHelper
- [ ] Wire `insertFresh` to use it (channels + movies + series)
- [ ] Benchmark: re-sync source 1, compare applyMs before/after
- [ ] Regression: favorites/history keyed to `id` must survive (INSERT OR
      IGNORE semantics preserved — no REPLACE)