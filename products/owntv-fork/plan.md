# OwnTV fork — Max Plan (2026-11-11)

## Goal
Pixel-first headless TDD (LuaJ lane) → maximal APK deploy. The AIOStreams integration is a
**merge of all fork code** — cutting edge, aesthetic, one lane that joins every public instance.

## THRESHER AWARENESS (read first)
The thresher gateway (:25127, X-API-Key from mcp_config.json) is the MCP federation plane.
Everything the agent touches routes through it:
- `kryptos` MCP (stdio, olympus/products/kryptos/mcp/server.ts) — secrets. **CLI banned in agent
  shells** (2026-11-08); key access = MCP only. The on-box fallback file
  `~/.tau/freeinference.key` is the sanctioned non-MCP lane (argos uses it too).
- `argos` (:25254) — ES-compatible search + bge-m3 embed proxy (model
  `nvidia/nemotron-3.5-embed-1b` via router :25193 — currently circuit-broken; freeinference
  direct works).
- `plan-mcp:tools_retrieve` — the bge-oracle tool-selector (embed + cosine top-N).
- `trio:trio_semantic_rerank` / `trio:trio_code_sim` — bge-m3 cosine rerank/similarity (the
  title-merge oracle for dupes).
- flotilla MCP (:25245) — Pixel input/audit tools. **DOWN right now**; adb forward is the lane.
- blackbird MCP (:25113) — repo/code search.
- **Bash policy bans**: sed/tail/head pipes abort; kryptos/secretsmith/kryptos CLIs abort.
  No `sleep` polling. 60s ceiling on bash; eval worker 30s.

## Test device: Pixel 9 Pro XL (10.0.0.77:37849, port rotates)
HEADLESS-ONLY: no taps/screenshots/UI driving — dumpsys/logcat/proc/LuaJ bridge only.
Terminus (user's SSH client) is NEVER closed/killed. Pixel read-only except: OwnTV install +
OwnTV launch + app-owned DataStore/DB writes via the LuaJ bridge.
LuaJ lane: `adb -s 10.0.0.77:<port> forward tcp:38383 tcp:38383` → POST /run {"script":...}.
Bridges: `datastoreEdit(key,kind,val)`, `enqueueEpg(sourceId)`, `enqueueSync(sourceId)` (new),
`sql(q)`, `httpGet`, `log`.

## State
- Pixel DB seeded: sources 1-5 (1TV41 ×2, New8K, 99GoPro, MAX JP) + profile Main +
  active_profile_id=1. Fresh install → channels/movies/series/EPG all 0 until first sync.
- MAX JP/KR: eu.vipmax.link:8080 orhanbeLvip/JPm9716yom (source 7 on TV; 5 on Pixel). 22,217
  live streams; JP cat 2666, KR cat 2480.
- TV DB: 144,284 channels / 173,118 movies / 24,016 series / EPG 0. 30,224 duplicated movie
  titles (17%) — panels list one film per category; "Ar - 2021" ×58. 2,799 `[XX]`-tagged
  (GR 289, 4K 268, FR 235, PL 139, HD 88, IT 77 — quality tags stay, language tags merge).
- Live search "ruin" audit: 30 results — radio channel in Live TV (RuinNation Arts Radio,
  category "New Radio Stations" = 39,932 channels), Fantasy Football Ruined Our Lives ×3
  (same source, 3 category copies), adult dupes ×2.

## Code (committed-ready in owntv-fork)
- `AioStreamsSearch.kt` — hit model (audioLanguages/dubbed/subbed/isEnglishOnly/isHindiRelease),
  parseHits (org.json), dedup (infoHash ?: title), RANK (english-only > cached > seeders > tier),
  userDataConfig (TorBox key + AllDebrid demo + 10 presets incl. OpenSubtitles ×2), FORKS list.
- `AioStreamsFetcher.kt` — title→TMDB candidates (max 5) → parallel fork fan-out (5 forks,
  6s timeout) → per-title AioStreamsTitle(hits). Needs MetadataProvider (nullable).
- `SearchReader.kt` — aioStreams lane (own title search, separate from local), movies/series
  `distinctBy { name.searchTitleKey }` (language-tag-stripping join key), channels
  `filterNot { isAudioOnlyChannel() }` (radio category + name patterns).
- `SearchScreen.kt` — Entry.Aio(title) one row per title, line = service/quality/year/audio/DUB/
  SUBBED/CACHED/seeders/N LINKS, category ON TOP of local results. OK = no-op (selector next).
- `AppLuaRunner.kt` — enqueueSync bridge added (CatalogSyncScheduler.enqueueSync).
- `ChannelDao.kt` — isAudioOnlyChannel() extension.
- Tests: AioStreamsSearchTest (11), SearchHygieneTest (5) — all green.
- bge-oracle: resolveApiKey now reads ~/.tau/freeinference.key (secretsmith CLI banned) — 9 tests pass.

## Known gaps / emergence candidates
- TMDB resolution dead in debug build (Worker 403 without OWNTV_EDGE_KEY) → AIOStreams lane
  returns nothing until fixed. Options: (a) bake edge key, (b) resolve via local catalogue
  MetadataRepository (offline local→tmdb map), (c) AIOStreams catalog route with user config.
- Link selector: OK on Aio title → link sheet → pick → play via existing player (URL playback).
- EPG: epg_channels=0 everywhere. enqueueEpg fires; worker runs; tables stay empty — diagnose.
- Sync perf: 10K chunks at 25-55s for 52K channels. insertAllPrepared batch + adaptive chunking
  in code; measure live on Pixel sync.
- Hindi release filter: lane-level filterNot(isHindiRelease) — verify on Pixel after sync.

## Recursive todo doctrine
Every error → verbose todo (details + notes + error line). Every feature emergence → new todo.
Board = goal state; goal completes only when board is green and APK is deployed + verified.
