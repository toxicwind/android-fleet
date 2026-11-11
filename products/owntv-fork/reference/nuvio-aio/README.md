# nuvio-tv AIOStreams/Stremio integration — reference copy

Full Kotlin source of how nuvio-tv (vendored Android TV app) connects Stremio-style
addons (AIOStreams, Torrentio, MediaFusion) to its Movies/TV UI and search. Copied
2026-11-11 from `fleet/devices/android-fleet/vendored/nuvio-tv` as the porting
reference for the OwnTV fork's Movies/TV switcher hook + AIOStreams-as-search-provider.

Source tree root: `nuvio-tv/app/src/main/java/com/nuvio/tv` (+ `src/full` for the
CloudStream plugin runtime). Package root below is `com.nuvio.tv`.

## Layers (bottom → top)

### 1. API / DTO — `data/remote/api/`, `data/remote/dto/`
- `AddonApi.kt` — Retrofit interface: manifest, catalog, meta, stream, subtitles, configure.
- `AddonManifestDto.kt` — Stremio manifest JSON (id, version, resources, catalogs, types).
- `MetaResponseDto.kt` — meta (title/logo/poster/background) + stream response envelopes.
- `TmdbApi.kt` + `core/tmdb/TmdbService.kt` — TMDB metadata enrichment for catalog rows.
- `core/network/SafeApiCall.kt` — error-wrapping suspend call helper.

### 2. Repository / data — `data/repository/`, `data/local/`, `data/mapper/`
- `AddonRepositoryImpl.kt` — addon install/uninstall/list + manifest fetch/cache.
- `CatalogRepositoryImpl.kt` — catalog paging: `{manifest}/catalog/{type}/{id}/{extras}`.
- `MetaRepositoryImpl.kt` — `{manifest}/meta/{type}/{id}` → detail + poster.
- `StreamRepositoryImpl.kt` — `{manifest}/stream/{type}/{videoId}` → source list.
- `AddonPreferences.kt` — installed addons + order persisted (DataStore).
- `AddonMapper.kt` — DTO ↔ domain mapping.

### 3. Domain — `domain/model/`
- `Addon.kt` — installed addon (manifest URL, name, version, resources, catalogs).
- `Plugin.kt` — CloudStream plugin wrapper (the alternative provider lane).
- `Stream.kt` — resolved playback source (URL, quality, language, size, cached).
- `CatalogDescriptorExtensions.kt` — catalog-id parsing/extras helpers.

### 4. Plugin runtime (CloudStream) — `src/full/.../core/plugin/`
- `PluginRuntime.kt`, `PluginManager.kt` — load/run CloudStream extensions.
- `cloudstream/ExternalExtensionLoader.kt`, `ExternalExtensionRunner.kt`,
  `ExternalExtractorRegistry.kt`, `ExternalRepoParser.kt`, `TvTypeExtensions.kt` —
  CloudStream repo → plugin → extractor pipeline.
- `core/plugin/PluginEpisodeRequestRules.kt`, `PluginMediaType.kt`, `PluginSafety.kt`,
  `TestDiagnostics.kt` — main-source plugin helpers (safety/diagnostics).

### 5. Debrid stream shaping — `core/debrid/`
- `DebridProvider.kt` — RealDebrid/TorBox/Premiumize provider abstraction.
- `DebridStreamFormatter.kt` + `DebridStreamTemplateEngine.kt` — AIOStreams-style
  formatter templates (quality/language/size badges).
- `DirectDebridStreamFilter.kt` + `DirectDebridStreamPreparer.kt` — pick best source,
  pre-warm direct links.
- `LocalDebridAvailabilityService.kt`, `StreamTextSizeParser.kt`, `DebridStreamPresentation.kt`.

### 6. UI — `ui/screens/addon/`, `ui/screens/plugin/`, `ui/screens/search/`
- `AddonManagerScreen.kt` + `AddonManagerViewModel.kt` + `AddonManagerUiState.kt` +
  `AddonManagementAccess.kt` — install/manage addons (the "Movies & TV switcher" surface).
- `CatalogOrderScreen.kt` + `CatalogOrderViewModel.kt` — reorder catalog rows.
- `EssentialAddonSetupScreen.kt` — first-run essential addon picker.
- `PluginScreen.kt` + `PluginViewModel.kt` + `PluginUiState.kt` — CloudStream plugin UI.
- `SearchScreen.kt` + `SearchViewModel.kt` — cross-provider search (addons + local).
- `ui/screens/player/LivePlaybackUiPolicy.kt` — playback policy for addon streams.

### 7. Sync / server — `core/sync/`, `core/server/`
- `AddonSyncService.kt` — background addon catalog sync.
- `AddonWebPage.kt` — embedded addon configure web page bridge.

## Port plan into OwnTV fork

1. **Provider core**: copy `data/remote/api/AddonApi.kt` + DTOs + `AddonRepositoryImpl` +
   `CatalogRepositoryImpl` + `MetaRepositoryImpl` + `StreamRepositoryImpl` into
   `core/core/.../aiostreams/` (OwnTV already has Retrofit/OkHttp via `http`).
2. **Source integration**: OwnTV's `SourceEntity`/`SourceType` currently = M3U/XTREAM/
   STALKER. Add `AIO_STREAMS` source type pointing at the instance base URL
   (`http://<host>:25280`), reusing `SourceEntity.url` + a manifest-derived catalog.
3. **Search hook**: OwnTV `SearchReader` (FTS over synced tables) — add a parallel
   AIOStreams search lane via `{manifest}/catalog/movie/top/search=...` and
   `series`, merge into `SearchResults`.
4. **Movies/TV switcher hook**: `selectFromNav` in `OwnTVShell.kt` — when
   MOVIES/SERIES selected, also surface AIOStreams catalog rows (the "hook" the
   user asked for — no separate switcher UI needed).
5. **Playback**: reuse existing player; AIOStreams resolves to debrid/CDN direct
   links, so no new DataSource needed (unlike nuvio's ParaleloDataSource which
   only exists for the TCL TV's 2MB socket buffer).

## Notes
- AIOStreams instance: user's own fork on `:25280` (see pens docs). Stremio
  manifest at `{base}/manifest.json`; search via catalog extras `search={query}`.
- nuvio's `MediaBrowserModels.kt` maps Jellyfin-style `aiostreams` fields — only
  needed if Jellyfin media-server integration is in scope.