# Master Xtream provider gateway (:8081)

Universal IPTV gateway that fronts any Xtream client (OwnTV, LG webos-iptv-player,
Nuvio) with one clean panel. Behind it: 56 upstream Xtream panels aggregated from
`fleet-tv/app/src/main/assets/fleet_portals.csv` (the tracked SSOT).

## Launch

```bash
cd products/owntv-fork/provider
./launch.sh                      # sources auto-built from fleet_portals.csv
PROVIDER_SOURCES="xtream|id=s1|name=Main|url=http://1tv41.icu:8080|user=VMh8na|pass=793530" ./launch.sh
```

Defaults: `master/master` on `0.0.0.0:8081`. Log: `/tmp/provider.log` (override
`PROVIDER_LOG`). All knobs are `PROVIDER_*` env vars — see `src/config.ts`.

## Xtream surface (LG app contract)

| Endpoint | Notes |
|---|---|
| `player_api.php` (bare) | account info |
| `get_live_categories` / `get_live_streams` | precomputed bodies — O(1) per request |
| `get_short_epg` | upstream get_short_epg, falls back to merged xmltv |
| `get_vod_categories` / `get_vod_streams` | merged upstream + TorBox + velvet |
| `get_vod_info?vod_id=` | `{info, movie_data}` shape; also accepts `movie_id` |
| `get_series_categories` / `get_series` | merged catalog |
| `get_series_info?series_id=` | proxied upstream; episode ids are source-encoded (`s{idx}:{episodeId}`) so `/series/` playback routes to the owning panel |
| `get.php?type=m3u_plus&output=ts|m3u8` | M3U playlist (LG app's primary channel source) |
| `xmltv.php` | streaming merged EPG (headers flush immediately) |
| `/live/{u}/{p}/{id}.ts|.m3u8` | stream passthrough with caps + retries |
| `/movie/{u}/{p}/{id}.{ext}` | VOD passthrough |
| `/series/{u}/{p}/{episodeId}.{ext}` | episode passthrough (source-encoded ids) |
| `/timeshift/{u}/{p}/{dur}/{start}/{id}.ts` | catchup (requires channel catchup flag) |
| `/streaming/timeshift.php` | legacy catchup form |
| `/health` | `{ok, channels, loadedAt}` |

## Verify

```bash
curl -s http://127.0.0.1:8081/health
curl -s "http://127.0.0.1:8081/player_api.php?username=master&password=master&action=get_live_streams"
curl -s "http://127.0.0.1:8081/get.php?username=master&password=master&type=m3u_plus&output=ts"
```

## Notes

- Catalog is ~686K channels with all 56 portals (old hand-curated 27-source config
  is unrecoverable — it died with the process). Curate via `PROVIDER_SOURCES` or
  `filter=` per source if the client chokes on the M3U size.
- `get_vod_info`, `/series/` episode routing, `get.php`, and `/timeshift/` were
  added for the LG webos-iptv-player client (2026-11-12).