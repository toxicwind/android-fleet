/** Merged VOD + series catalogs for the master provider.
 *
 * OwnTV's Movies/Series rails were empty because the gateway answered get_vod_* /
 * get_series_* with []. This module builds ONE merged catalog per section from:
 *   1. upstream Xtream panels (get_vod_streams / get_series) — the "best catalogs"
 *   2. the user's TorBox cloud library (mylist files as movies)
 *   3. velvet CS3 plugins (series)
 * and serves it in the Xtream schema OwnTV already syncs natively.
 *
 * IDs are source-encoded so stream routing can find the owning upstream:
 *   movie  id  = `s{srcIdx}:{remoteId}`
 *   series id  = `s{srcIdx}:{remoteId}`
 * TorBox movies use id `tb:{itemId}:{fileId}` and route to the requestdl URL.
 */
import type { Config } from "./config.ts";
import type { VelvetCatalog } from "./velvet.ts";
import { loadVelvetCatalog } from "./velvet.ts";

export interface MovieRow {
  movie_id: string;
  name: string;
  stream_icon?: string | null;
  rating?: number | null;
  plot?: string | null;
  category_id: string;
  container_extension?: string | null;
  added?: string | null;
  /** Owning upstream for stream routing. */
  upstream?: { kind: "xtream"; base: string; username?: string; password?: string; streamId: string } | { kind: "torbox"; requestUrl: string };
}

export interface SeriesRow {
  series_id: string;
  name: string;
  cover?: string | null;
  plot?: string | null;
  rating?: number | null;
  category_id: string;
  year?: number | null;
  added?: string | null;
  last_modified?: string | null;
  upstream?: { kind: "xtream"; base: string; username?: string; password?: string; seriesId: string } | { kind: "velvet"; plugin: string };
}

export interface VodSeriesCatalog {
  movieCategories: { category_id: string; category_name: string; parent_id: number }[];
  seriesCategories: { category_id: string; category_name: string; parent_id: number }[];
  movies: MovieRow[];
  series: SeriesRow[];
}

const TORBOX_API = "https://api.torbox.app";

async function fetchJson(config: Config, url: string, timeoutMs?: number): Promise<unknown> {
  const res = await fetch(url, { signal: AbortSignal.timeout(timeoutMs ?? config.fetchTimeoutMs), headers: { Authorization: `Bearer ${config.torboxKey}` } });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}

/** TorBox mylist → movie rows (playable video files only). */
async function loadTorBoxMovies(config: Config, cats: Map<string, { category_id: string; category_name: string }>): Promise<MovieRow[]> {
  const rows: MovieRow[] = [];
  const kindPaths: Array<[string, string]> = [
    ["torrents", "torrent"],
    ["usenet", "usenet"],
    ["webdl", "webdl"],
  ];
  for (const [plural, kind] of kindPaths) {
    try {
      const j = (await fetchJson(config, `${TORBOX_API}/v1/api/${plural}/mylist`)) as { success?: boolean; data?: Array<Record<string, unknown>> };
      const items = Array.isArray(j.data) ? j.data : [];
      for (const item of items) {
        const itemId = String(item.id ?? "");
        const name = String(item.name ?? "");
        if (!itemId || !name) continue;
        const files = Array.isArray(item.files) ? (item.files as Array<Record<string, unknown>>) : [];
        for (const f of files) {
          const fileId = String(f.id ?? "");
          const fileName = String(f.name ?? "");
          if (!fileId || !fileName) continue;
          const mime = String(f.mimetype ?? f.mime_type ?? "");
          if (!/^video\//.test(mime) && !/\.(mp4|mkv|avi|mov|m4v|webm|ts|m2ts|flv|wmv|divx|ogm|ogv|mpeg|mpg|mts|3gp|3g2)$/i.test(fileName)) continue;
          const catKey = "tb:" + plural;
          if (!cats.has(catKey)) cats.set(catKey, { category_id: catKey, category_name: `My Cloud · ${plural}` });
          const requestUrl = `${TORBOX_API}/v1/api/${plural}/requestdl?${kind}_id=${encodeURIComponent(itemId)}&file_id=${encodeURIComponent(fileId)}&token=${encodeURIComponent(config.torboxKey)}`;
          rows.push({
            movie_id: `tb:${itemId}:${fileId}`,
            name: fileName,
            plot: name,
            category_id: catKey,
            added: String(Math.floor(Date.now() / 1000)),
            upstream: { kind: "torbox", requestUrl },
          });
        }
      }
    } catch {
      // one kind failing must not kill the rest of the merged catalog
    }
  }
  return rows;
}

/** Upstream Xtream VOD → movie rows (deduped by normalized name, best rating wins). */
async function loadXtreamVod(config: Config, srcIndex: number, src: { url: string; username?: string; password?: string }, cats: Map<string, { category_id: string; category_name: string }>): Promise<MovieRow[]> {
  const base = src.url.trimEnd("/");
  const q = `username=${encodeURIComponent(src.username ?? "")}&password=${encodeURIComponent(src.password ?? "")}`;
  const res = await fetch(`${base}/player_api.php?${q}&action=get_vod_streams`, { signal: AbortSignal.timeout(config.fetchTimeoutMs) });
  if (!res.ok) throw new Error(`vod HTTP ${res.status}`);
  const raw = (await res.json()) as Array<Record<string, unknown>>;
  const rows: MovieRow[] = [];
  for (const m of raw) {
    const name = String(m.name ?? "").trim();
    if (!name) continue;
    const catId = String(m.category_id ?? "0");
    const catKey = `s${srcIndex}:${catId}`;
    if (!cats.has(catKey)) cats.set(catKey, { category_id: catKey, category_name: String(m.category_name ?? `Source ${srcIndex} · ${catId}`) });
    rows.push({
      movie_id: `s${srcIndex}:${String(m.stream_id ?? "")}`,
      name,
      stream_icon: m.stream_icon ? String(m.stream_icon) : null,
      rating: typeof m.rating === "number" ? m.rating : null,
      plot: m.plot ? String(m.plot) : null,
      category_id: catKey,
      container_extension: m.container_extension ? String(m.container_extension) : null,
      added: m.added ? String(m.added) : null,
      upstream: { kind: "xtream", base, username: src.username, password: src.password, streamId: String(m.stream_id ?? "") },
    });
  }
  return rows;
}

/** Upstream Xtream series → series rows (deduped by normalized name). */
async function loadXtreamSeries(config: Config, srcIndex: number, src: { url: string; username?: string; password?: string }, cats: Map<string, { category_id: string; category_name: string }>): Promise<SeriesRow[]> {
  const base = src.url.trimEnd("/");
  const q = `username=${encodeURIComponent(src.username ?? "")}&password=${encodeURIComponent(src.password ?? "")}`;
  const res = await fetch(`${base}/player_api.php?${q}&action=get_series`, { signal: AbortSignal.timeout(config.fetchTimeoutMs) });
  if (!res.ok) throw new Error(`series HTTP ${res.status}`);
  const raw = (await res.json()) as Array<Record<string, unknown>>;
  const rows: SeriesRow[] = [];
  for (const s of raw) {
    const name = String(s.name ?? "").trim();
    if (!name) continue;
    const catId = String(s.category_id ?? "0");
    const catKey = `s${srcIndex}:${catId}`;
    if (!cats.has(catKey)) cats.set(catKey, { category_id: catKey, category_name: String(s.category_name ?? `Source ${srcIndex} · ${catId}`) });
    rows.push({
      series_id: `s${srcIndex}:${String(s.series_id ?? "")}`,
      name,
      cover: s.cover ? String(s.cover) : null,
      plot: s.plot ? String(s.plot) : null,
      rating: typeof s.rating === "number" ? s.rating : null,
      category_id: catKey,
      year: typeof s.year === "number" ? s.year : null,
      added: s.added ? String(s.added) : null,
      last_modified: s.last_modified ? String(s.last_modified) : null,
      upstream: { kind: "xtream", base, username: src.username, password: src.password, seriesId: String(s.series_id ?? "") },
    });
  }
  return rows;
}

const normName = (s: string): string => s.toLowerCase().replace(/[^a-z0-9]+/g, " ").trim();

export async function loadVodSeriesCatalog(config: Config): Promise<VodSeriesCatalog> {
  const movieCats = new Map<string, { category_id: string; category_name: string }>();
  const seriesCats = new Map<string, { category_id: string; category_name: string }>();
  const movies = new Map<string, MovieRow>();
  const series = new Map<string, SeriesRow>();

  // 1. upstream Xtream VOD + series (parallel per source)
  const srcTasks = config.sources.map(async (src, idx) => {
    if (src.kind !== "xtream") return;
    const base = src.url.trimEnd("/");
    const account = { url: src.url, username: src.username, password: src.password };
    const [vodRows, seriesRows] = await Promise.all([
      loadXtreamVod(config, idx, account, movieCats).catch(() => [] as MovieRow[]),
      loadXtreamSeries(config, idx, account, seriesCats).catch(() => [] as SeriesRow[]),
    ]);
    for (const m of vodRows) {
      const key = normName(m.name);
      const prev = movies.get(key);
      if (!prev || (m.rating ?? 0) > (prev.rating ?? 0)) movies.set(key, m);
    }
    for (const s of seriesRows) {
      const key = normName(s.name);
      const prev = series.get(key);
      if (!prev || (s.rating ?? 0) > (prev.rating ?? 0)) series.set(key, s);
    }
  });
  await Promise.all(srcTasks);

  // 2. TorBox cloud as movies
  for (const m of await loadTorBoxMovies(config, movieCats)) {
    const key = normName(m.name);
    if (!movies.has(key)) movies.set(key, m);
  }

  // 3. velvet plugins as series
  let velvet: VelvetCatalog = { categories: [], series: [] };
  try {
    velvet = await loadVelvetCatalog(config);
  } catch {
    velvet = { categories: [], series: [] };
  }
  for (const vc of velvet.categories) {
    const catKey = "velvet:" + vc.category_id;
    if (!seriesCats.has(catKey)) seriesCats.set(catKey, { category_id: catKey, category_name: vc.category_name });
  }
  for (const vs of velvet.series) {
    const catKey = "velvet:" + vs.category_id;
    series.set(`velvet:${vs.series_id}`, {
      series_id: `velvet:${vs.series_id}`,
      name: vs.name,
      plot: vs.plot,
      rating: vs.rating,
      category_id: catKey,
      year: vs.year,
      added: vs.added != null ? String(vs.added) : null,
      last_modified: vs.last_modified != null ? String(vs.last_modified) : null,
      upstream: { kind: "velvet", plugin: vs.name },
    });
  }

  return {
    movieCategories: [...movieCats.values()],
    seriesCategories: [...seriesCats.values()],
    movies: [...movies.values()],
    series: [...series.values()],
  };
}

/** Resolve a movie row to its playable upstream URL. */
export function movieStreamUrl(m: MovieRow): string | null {
  if (!m.upstream) return null;
  if (m.upstream.kind === "torbox") return m.upstream.requestUrl;
  const ext = m.container_extension && /^[a-z0-9]{2,5}$/i.test(m.container_extension) ? m.container_extension : "mp4";
  return `${m.upstream.base}/movie/${m.upstream.username}/${m.upstream.password}/${m.upstream.streamId}.${ext}`;
}

/** Resolve a series row to its playable upstream URL (first episode is fetched lazily by OwnTV via get_series_info). */
export function seriesStreamUrl(s: SeriesRow): string | null {
  if (!s.upstream || s.upstream.kind === "velvet") return null;
  return `${s.upstream.base}/series/${s.upstream.username}/${s.upstream.password}/${s.upstream.seriesId}.mp4`;
}