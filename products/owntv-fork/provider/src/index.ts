/**
 * Master provider — universal IPTV gateway that fronts OwnTV (and any Xtream
 * client) with a single clean panel: player_api.php + /live/ streams + xmltv.php
 * EPG. Behind it, any number of upstreams: Xtream panels, M3U playlists, Stalker.
 *
 * OwnTV speaks Xtream Codes natively, so the gateway's surface IS Xtream:
 *   GET /player_api.php?username=&password=&action=get_live_categories
 *   GET /player_api.php?username=&password=&action=get_live_streams
 *   GET /player_api.php?username=&password=&action=get_short_epg&stream_id=&limit=
 *   GET /player_api.php?username=&password=            (bare = account info)
 *   GET /live/{user}/{pass}/{id}.m3u8|.ts              (stream passthrough)
 *   GET /xmltv.php?username=&password=                 (merged EPG)
 *
 * Upstreams are declared in config and normalized into a single virtual channel
 * list. Streams route through us: connection caps, route-busting retries and
 * backoff live here, not on the panel.
 */
import { defineConfig } from "./config.ts";
import { loadUpstreams, type VirtualChannel } from "./upstreams.ts";
import { scheduleEpgRefresh, epgForChannel, epgXmltv } from "./epg.ts";
import { handleStream } from "./stream.ts";
import { loadVelvetCatalog, type VelvetCatalog } from "./velvet.ts";
import { searchArgos, toProviderResults } from "./argos.ts";
import { runLua } from "./lua-runner.ts";
import { loadVodSeriesCatalog, movieStreamUrl, seriesStreamUrl, type MovieRow, type SeriesRow } from "./vod-series.ts";

const config = defineConfig(process.env);
const log = (msg: string, ...rest: unknown[]) =>
  console.log(`[provider ${new Date().toISOString().slice(11, 19)}] ${msg}`, ...rest);

// --- shared state -----------------------------------------------------------

let channels: VirtualChannel[] = [];
let categories: { category_id: string; category_name: string; parent_id: number }[] = [];
let loadedAt = 0;
let loading: Promise<void> | null = null;

// Precomputed Xtream API bodies. The app syncs 722 categories sequentially, and each
// category request used to re-filter + re-map + re-serialize the whole 281K-channel
// catalog on the fly — O(N) per request, which is what blew the 10s idleTimeout and
// stalled the TV's CatalogSyncWorker at ~230K channels. Building the serialized JSON
// once at catalog load makes every get_live_streams request O(1) (a string slice).
let liveStreamsByCategory = new Map<string, string>();
let liveStreamsAll = "[]";
let categoriesBody = "[]";

// Merged VOD + series catalog (upstream Xtream VOD/series + TorBox cloud + velvet).
// Precomputed serialized bodies like the live cache, so OwnTV's Movies/Series sync
// (which fetches per category) is O(1) per request.
let movies: MovieRow[] = [];
let series: SeriesRow[] = [];
let moviesByCategory = new Map<string, string>();
let moviesAll = "[]";
let movieCategoriesBody = "[]";
let seriesByCategory = new Map<string, string>();
let seriesAll = "[]";
let seriesCategoriesBody = "[]";
let vodSeriesLoadedAt = 0;
let vodSeriesLoading: Promise<void> | null = null;

async function refreshVodSeries(force = false): Promise<void> {
  if (vodSeriesLoading) return vodSeriesLoading;
  if (!force && vodSeriesLoadedAt && Date.now() - vodSeriesLoadedAt < config.catalogTtlMs) return;
  vodSeriesLoading = (async () => {
    try {
      const t0 = Date.now();
      const cat = await loadVodSeriesCatalog(config);
      movies = cat.movies;
      series = cat.series;
      movieCategoriesBody = JSON.stringify(cat.movieCategories);
      seriesCategoriesBody = JSON.stringify(cat.seriesCategories);
      const mByCat = new Map<string, unknown[]>();
      const mAll: unknown[] = [];
      for (const m of cat.movies) {
        const row = {
          movie_id: m.movie_id,
          name: m.name,
          stream_icon: m.stream_icon ?? "",
          rating: m.rating ?? 0,
          plot: m.plot ?? "",
          category_id: m.category_id,
          container_extension: m.container_extension ?? "",
          added: m.added ?? String(Math.floor(Date.now() / 1000)),
        };
        mAll.push(row);
        let arr = mByCat.get(m.category_id);
        if (!arr) { arr = []; mByCat.set(m.category_id, arr); }
        arr.push(row);
      }
      moviesByCategory = new Map([...mByCat].map(([k, v]) => [k, JSON.stringify(v)]));
      moviesAll = JSON.stringify(mAll);
      const sByCat = new Map<string, unknown[]>();
      const sAll: unknown[] = [];
      for (const s of cat.series) {
        const row = {
          series_id: s.series_id,
          name: s.name,
          cover: s.cover ?? "",
          plot: s.plot ?? "",
          rating: s.rating ?? 0,
          category_id: s.category_id,
          year: s.year ?? 0,
          added: s.added ?? String(Math.floor(Date.now() / 1000)),
          last_modified: s.last_modified ?? String(Math.floor(Date.now() / 1000)),
        };
        sAll.push(row);
        let arr = sByCat.get(s.category_id);
        if (!arr) { arr = []; sByCat.set(s.category_id, arr); }
        arr.push(row);
      }
      seriesByCategory = new Map([...sByCat].map(([k, v]) => [k, JSON.stringify(v)]));
      seriesAll = JSON.stringify(sAll);
      vodSeriesLoadedAt = Date.now();
      log(`vod/series: ${movies.length} movies / ${series.length} series / ${cat.movieCategories.length} movie cats / ${cat.seriesCategories.length} series cats in ${Date.now() - t0}ms`);
    } catch (err) {
      log("vod/series catalog refresh failed", err);
    } finally {
      vodSeriesLoading = null;
    }
  })();
  return vodSeriesLoading;
}

function channelRow(c: VirtualChannel, num: number) {
  return {
    num,
    name: c.name,
    stream_type: "live",
    stream_id: c.id,
    stream_icon: c.icon ?? "",
    epg_channel_id: c.epgId ?? null,
    added: String(Math.floor(c.addedAt / 1000)),
    category_id: c.categoryId,
    custom_sid: "",
    tv_archive: c.catchup ? 1 : 0,
    direct_source: "",
    tv_archive_duration: c.catchup ? 7 : 0,
  };
}

function rebuildApiCache(channelList: VirtualChannel[], categoryList: { category_id: string; category_name: string; parent_id: number }[]): void {
  const perCat = new Map<string, unknown[]>();
  let all: unknown[] = [];
  let num = 0;
  for (const c of channelList) {
    num++;
    const row = channelRow(c, num);
    all.push(row);
    let arr = perCat.get(c.categoryId);
    if (!arr) {
      arr = [];
      perCat.set(c.categoryId, arr);
    }
    arr.push(row);
  }
  liveStreamsByCategory = new Map();
  for (const [catId, rows] of perCat) {
    liveStreamsByCategory.set(catId, JSON.stringify(rows));
  }
  liveStreamsAll = JSON.stringify(all);
  categoriesBody = JSON.stringify(categoryList);
}

async function refreshCatalog(force = false): Promise<void> {
  if (loading) return loading;
  if (!force && loadedAt && Date.now() - loadedAt < config.catalogTtlMs) return;
  loading = (async () => {
    try {
      const result = await loadUpstreams(config);
      channels = result.channels;
      categories = result.categories;
      rebuildApiCache(channels, categories);
      loadedAt = Date.now();
      log(`catalog: ${channels.length} channels / ${categories.length} categories from ${config.sources.length} source(s)`);
    } catch (err) {
      log("catalog refresh failed", err);
    } finally {
      loading = null;
    }
  })();
  return loading;
}

// --- Xtream API surface -----------------------------------------------------

function authOk(url: URL): boolean {
  const u = url.searchParams.get("username");
  const p = url.searchParams.get("password");
  return u === config.clientUsername && p === config.clientPassword;
}

function json(res: Response, body: unknown): Response {
  return new Response(JSON.stringify(body), {
    headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
  });
}

function accountInfo(): Record<string, unknown> {
  return {
    user_info: {
      username: config.clientUsername,
      password: config.clientPassword,
      message: "OK",
      auth: 1,
      status: "Active",
      exp_date: String(Math.floor(Date.now() / 1000) + 365 * 86400),
      is_trial: "0",
      active_cons: "0",
      max_connections: String(config.maxConnections),
      allowed_output_formats: ["m3u8", "ts"],
    },
    server_info: {
      url: config.host,
      port: String(config.port),
      https_port: "0",
      server_protocol: "http",
      timezone: "UTC",
      timestamp_now: Math.floor(Date.now() / 1000),
      time_now: new Date().toISOString().replace("T", " ").slice(0, 19),
    },
  };
}

async function handleApi(url: URL): Promise<Response> {
  if (!authOk(url)) return json(new Response(null, { status: 401 }), { user_info: { auth: 0, status: "Unauthorized" } });
  const action = url.searchParams.get("action") ?? "";
  await refreshCatalog();
  switch (action) {
    case "get_live_categories":
      return new Response(categoriesBody, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    case "get_live_streams": {
      const cat = url.searchParams.get("category_id");
      const body = cat ? (liveStreamsByCategory.get(cat) ?? "[]") : liveStreamsAll;
      return new Response(body, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    }
    case "get_short_epg": {
      const streamId = url.searchParams.get("stream_id") ?? "";
      const limit = Number(url.searchParams.get("limit") ?? "4");
      const channel = channels.find((c) => String(c.id) === streamId);
      if (!channel) return json(new Response(), []);
      const programmes = await epgForChannel(channel, limit);
      return json(new Response(), programmes);
    }
    case "get_vod_categories": {
      await refreshVodSeries();
      return new Response(movieCategoriesBody, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    }
    case "get_vod_streams": {
      await refreshVodSeries();
      const cat = url.searchParams.get("category_id");
      const body = cat ? (moviesByCategory.get(cat) ?? "[]") : moviesAll;
      return new Response(body, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    }
    case "get_series_categories": {
      await refreshVodSeries();
      return new Response(seriesCategoriesBody, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    }
    case "get_series": {
      await refreshVodSeries();
      const cat = url.searchParams.get("category_id");
      const body = cat ? (seriesByCategory.get(cat) ?? "[]") : seriesAll;
      return new Response(body, {
        headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
      });
    }
    case "get_series_info": {
      await refreshVodSeries();
      const seriesId = url.searchParams.get("series_id") ?? "";
      // Only upstream Xtream series have episodes; velvet/TorBox rows have none.
      const s = series.find((x) => x.series_id === seriesId);
      if (!s?.upstream || s.upstream.kind !== "xtream") {
        return json(new Response(), { episodes: [] });
      }
      const up = s.upstream;
      const base = up.base;
      const q = `username=${encodeURIComponent(up.username ?? "")}&password=${encodeURIComponent(up.password ?? "")}`;
      try {
        const res = await fetch(`${base}/player_api.php?${q}&action=get_series_info&series_id=${encodeURIComponent(up.seriesId)}`, {
          signal: AbortSignal.timeout(30_000),
        });
        if (!res.ok) return json(new Response(), { episodes: [] });
        const info = (await res.json()) as Record<string, unknown>;
        return json(new Response(), info);
      } catch {
        return json(new Response(), { episodes: [] });
      }
    }
    case "":
      return json(new Response(), accountInfo());
    default:
      return json(new Response(), { error: "unknown action" });
  }
}

// --- routing ----------------------------------------------------------------

const server = Bun.serve({
  port: config.port,
  hostname: "0.0.0.0",
  // Big get_live_streams responses (a 273K-channel catalog) take >10s to build on the
  // first hit; Bun's default idleTimeout (10s) killed those mid-flight and the TV's
  // CatalogSyncWorker stalled at ~230K channels. 120s covers the slowest category page.
  idleTimeout: 120,
  async fetch(req) {
    const url = new URL(req.url);
    const path = url.pathname;

    if (path === "/player_api.php" || path === "/player_api.php/") return handleApi(url);
    if (path === "/xmltv.php") {
      if (!authOk(url)) return new Response("Unauthorized", { status: 401 });
      await refreshCatalog();
      const xml = await epgXmltv(channels, config);
      return new Response(xml, { headers: { "content-type": "application/xml; charset=utf-8" } });
    }
    if (path.startsWith("/live/")) {
      await refreshCatalog();
      return handleStream(req, url, channels, config);
    }
    if (path.startsWith("/movie/") || path.startsWith("/series/")) {
      await refreshVodSeries();
      const segments = url.pathname.split("/").filter(Boolean); // movie|series, user, pass, id.ext
      const idPart = segments[segments.length - 1] ?? "";
      const rawId = decodeURIComponent(idPart.split(".")[0]);
      if (path.startsWith("/movie/")) {
        const m = movies.find((x) => x.movie_id === rawId);
        const upstream = m ? movieStreamUrl(m) : null;
        if (!upstream) return new Response("unknown movie", { status: 404 });
        return proxyStream(req, upstream);
      }
      const s = series.find((x) => x.series_id === rawId);
      const upstream = s ? seriesStreamUrl(s) : null;
      if (!upstream) return new Response("unknown series", { status: 404 });
      return proxyStream(req, upstream);
    }
    if (path === "/health") {
      return new Response(JSON.stringify({ ok: true, channels: channels.length, loadedAt }), {
        headers: { "content-type": "application/json" },
      });
    }
    if (path === "/run" && req.method === "POST") {
      const body = (await req.json().catch(() => ({}))) as { script?: string; timeoutMs?: number };
      if (!body.script) return new Response(JSON.stringify({ ok: false, error: "missing script" }), { status: 400, headers: { "content-type": "application/json" } });
      const result = await runLua({ script: body.script, timeoutMs: body.timeoutMs }, config);
      return new Response(JSON.stringify(result), { headers: { "content-type": "application/json" } });
    }
    return new Response("not found", { status: 404 });
  },
});

// --- lifecycle --------------------------------------------------------------

await refreshCatalog(true);
scheduleEpgRefresh(config, () => refreshCatalog(false));
refreshVodSeries(true);
log(`master provider on :${config.port} — ${config.sources.length} upstream(s), ${config.clientUsername}@${config.host}`);

/** Minimal passthrough for movie/series upstream URLs (VOD panels answer full URLs). */
async function proxyStream(req: Request, upstream: string): Promise<Response> {
  try {
    const upstreamRes = await fetch(upstream, {
      headers: { "user-agent": req.headers.get("user-agent") ?? "VLC/3.0" },
      redirect: "follow",
      signal: AbortSignal.timeout(60_000),
    });
    const headers = new Headers();
    for (const [k, v] of upstreamRes.headers) {
      if (k.toLowerCase() === "content-length" || k.toLowerCase() === "transfer-encoding") continue;
      headers.set(k, v);
    }
    headers.set("access-control-allow-origin", "*");
    return new Response(upstreamRes.body, { status: upstreamRes.status, headers });
  } catch (err) {
    return new Response(`upstream error: ${(err as Error).message}`, { status: 502 });
  }
}