/** Upstream loaders: normalize Xtream panels + M3U playlists into one virtual catalog. */
import type { Config, UpstreamSource } from "./config.ts";
import { classifyNonArray, recordHealth, recordError } from "./health.ts";

export interface VirtualChannel {
  /** Stable numeric id (hash of source id + upstream id). */
  id: number;
  sourceId: string;
  sourceName: string;
  name: string;
  categoryId: string;
  categoryName: string;
  icon?: string;
  epgId?: string;
  catchup: boolean;
  /** Full catchup contract OwnTV reads from the panel (dex: CatchupUrl.kt). */
  catchupType?: string;      // "default" | "append" | "timeshift" | "disable"
  catchupSource?: string;    // URL template with {utc}/{start}/{end}/{duration}
  catchupDays?: number;
  catchupCorrectionMin?: number;
  catchupOffsetMin?: number;
  catchupTimezone?: string;
  catchupPhpAvailable?: boolean;
  addedAt: number;
  /** Where the stream actually lives (panel URL, playlist URL). */
  upstream: {
    kind: "xtream" | "m3u" | "stalker";
    url: string; // full playable URL template (ts/m3u8 chosen by client)
    base: string;
    username?: string;
    password?: string;
    streamId: string;
    /** Stalker only: portal cmd to resolve via create_link at play time. */
    cmd?: string;
    /** Stalker only: portal MAC + bearer token for create_link. */
    mac?: string;
    token?: string;
    apiBase?: string;
  };
}

export interface CatalogResult {
  channels: VirtualChannel[];
  categories: { category_id: string; category_name: string; parent_id: number }[];
}

const idFor = (sourceId: string, upstreamId: string): number => {
  let h = 2166136261;
  for (let i = 0; i < sourceId.length; i++) h = Math.imul(h ^ sourceId.charCodeAt(i), 16777619);
  for (let i = 0; i < upstreamId.length; i++) h = Math.imul(h ^ upstreamId.charCodeAt(i), 16777619);
  return (h >>> 0) % 2_000_000_000;
};

const matches = (candidate: string | undefined, filter?: string): boolean =>
  !filter || new RegExp(filter, "i").test(candidate ?? "");

async function loadXtream(src: UpstreamSource, out: VirtualChannel[], cats: Map<string, string>, config: Config): Promise<void> {
  const base = src.url.trimEnd("/");
  const q = `username=${encodeURIComponent(src.username ?? "")}&password=${encodeURIComponent(src.password ?? "")}`;
  const [streamsRes, catsRes] = await Promise.all([
    fetch(`${base}/player_api.php?${q}&action=get_live_streams`, { signal: AbortSignal.timeout(config.fetchTimeoutMs) }),
    fetch(`${base}/player_api.php?${q}&action=get_live_categories`, { signal: AbortSignal.timeout(config.fetchTimeoutMs) }),
  ]);
  if (!streamsRes.ok) {
    recordError(src.id, `streams HTTP ${streamsRes.status}`);
    throw new Error(`xtream ${src.id}: streams HTTP ${streamsRes.status}`);
  }
  const streamsRaw = (await streamsRes.json()) as unknown;
  // Same guard as categories: a panel can return an object (auth error / overload) instead of
  // an array. raw.map on that crashed the source every refresh.
  const streams = Array.isArray(streamsRaw) ? (streamsRaw as Array<Record<string, unknown>>) : [];
  if (!Array.isArray(streamsRaw)) {
    const cls = classifyNonArray(streamsRaw);
    recordHealth(src.id, cls);
    console.error(`[provider] xtream ${src.id}: get_live_streams ${cls.status} (${cls.keys.join(",")})`);
  }
  let categoryMap: Record<string, string> = {};
  if (catsRes.ok) {
    const raw = (await catsRes.json()) as unknown;
    if (Array.isArray(raw)) {
      categoryMap = Object.fromEntries(
        (raw as Array<{ category_id?: string; category_name?: string }>)
          .map((c) => [c.category_id ?? "", c.category_name ?? c.category_id ?? ""]),
      );
    } else {
      const cls = classifyNonArray(raw);
      recordHealth(src.id, cls);
      console.error(`[provider] xtream ${src.id}: get_live_categories ${cls.status} (${cls.keys.join(",")})`);
    }
  }
  // Streams array arrived — mark the source healthy (categories may still be degraded
  // above; the streams check is the liveness signal). Only when the array was real.
  if (Array.isArray(streamsRaw)) {
    recordHealth(src.id, { status: "ok", channels: streams.length, at: Date.now() });
  }
  for (const s of streams) {
    const name = String(s.name ?? "").replace(/^#EXTINF:0,\s*/, "");
    if (!name || !matches(name, src.filter)) continue;
    const streamId = String(s.stream_id ?? "");
    // SOURCE-SCOPED category id: raw panel category_ids collide across 27 feeds
    // (every panel has its own "3"/"4"/"5"...). Namespace with the source id so
    // categories stay distinct per feed — the "same number across feeds" anomaly.
    const rawCategoryId = String(s.category_id ?? "0");
    const categoryId = idFor(`${src.id}:cat`, rawCategoryId).toString();
    if (!cats.has(categoryId)) cats.set(categoryId, categoryMap[rawCategoryId] ?? "Uncategorized");
    const tvArchive = Number(s.tv_archive ?? 0) === 1;
    // Full catchup contract from the panel (OwnTV CatchupUrl.kt reads these).
    const catchupType = String(s.catchup_type ?? s.catchup ?? "").trim() || (tvArchive ? "default" : undefined);
    out.push({
      id: idFor(src.id, streamId),
      sourceId: src.id,
      sourceName: src.name,
      name: (src.prefix ?? "") + name,
      categoryId,
      categoryName: cats.get(categoryId)!,
      icon: s.stream_icon ? String(s.stream_icon) : undefined,
      epgId: s.epg_channel_id ? String(s.epg_channel_id) : undefined,
      catchup: tvArchive,
      catchupType,
      catchupSource: s.catchup_source ? String(s.catchup_source) : undefined,
      catchupDays: s.tv_archive_duration ? Number(s.tv_archive_duration) || undefined : undefined,
      catchupCorrectionMin: s.catchup_correction_min != null ? Number(s.catchup_correction_min) : undefined,
      catchupOffsetMin: s.catchup_offset_min != null ? Number(s.catchup_offset_min) : undefined,
      catchupTimezone: s.catchup_timezone ? String(s.catchup_timezone) : undefined,
      catchupPhpAvailable: s.catchup_php_available != null ? Number(s.catchup_php_available) === 1 : undefined,
      addedAt: Number(s.added ?? 0) * 1000 || Date.now(),
      upstream: {
        kind: "xtream",
        url: `${base}/live/${src.username}/${src.password}/${streamId}`,
        base,
        username: src.username,
        password: src.password,
        streamId,
      },
    });
  }
}

async function loadM3u(src: UpstreamSource, out: VirtualChannel[], cats: Map<string, string>, config: Config): Promise<void> {
  const url = src.playlistUrl ?? src.url;
  const res = await fetch(url, { signal: AbortSignal.timeout(config.fetchTimeoutMs) });
  if (!res.ok) throw new Error(`m3u ${src.id}: HTTP ${res.status}`);
  const text = await res.text();
  const lines = text.split(/\r?\n/);
  let current: { name: string; icon?: string; group?: string; tvgId?: string; catchup?: string } | null = null;
  for (const line of lines) {
    const trimmed = line.trim();
    if (trimmed.startsWith("#EXTINF")) {
      const nameMatch = trimmed.match(/,([^,]+)$/);
      const icon = trimmed.match(/tvg-logo="([^"]*)"/)?.[1];
      const group = trimmed.match(/group-title="([^"]*)"/)?.[1];
      const tvgId = trimmed.match(/tvg-id="([^"]*)"/)?.[1];
      const catchup = trimmed.match(/catchup="([^"]*)"/)?.[1];
      current = { name: nameMatch?.[1]?.trim() ?? "", icon, group, tvgId, catchup };
      continue;
    }
    if (current && trimmed && !trimmed.startsWith("#")) {
      const name = current.name;
      if (name && matches(name, src.filter)) {
        const categoryId = idFor(`${src.id}:cat`, current.group ?? "0").toString();
        const categoryName = current.group ?? "Uncategorized";
        if (!cats.has(categoryId)) cats.set(categoryId, categoryName);
        out.push({
          id: idFor(src.id, trimmed),
          sourceId: src.id,
          sourceName: src.name,
          name: (src.prefix ?? "") + name,
          categoryId,
          categoryName,
          icon: current.icon,
          epgId: current.tvgId || undefined,
          catchup: !!current.catchup && current.catchup !== "default",
          addedAt: Date.now(),
          upstream: { kind: "m3u", url: trimmed, base: new URL(trimmed, url).origin, streamId: trimmed },
        });
      }
      current = null;
    }
  }
}

/** Stalker/Ministra (MAG portal) protocol client — mirrors OwnTV core's
 *  StalkerClient.kt: portal handshake → Bearer token → get_all_channels.
 *  Streams are resolved lazily at play time via create_link (see stream.ts).
 */

const MAG_UA =
  "Mozilla/5.0 (QtEmbedded; U; Linux; C) AppleWebKit/533.3 (KHTML, like Gecko) MAG200 stbapp ver: 4 rev: 2721 Safari/533.3";

/** Deterministic fake MAC so a portal sees one stable identity per source. */
function macFor(sourceId: string): string {
  let h = 2166136261;
  for (let i = 0; i < sourceId.length; i++) h = Math.imul(h ^ sourceId.charCodeAt(i), 16777619);
  const hex = (h >>> 0).toString(16).padStart(8, "0");
  return `AA:${hex.slice(0, 2)}:${hex.slice(2, 4)}:${hex.slice(4, 6)}:${hex.slice(6, 8)}:00`;
}

function portalRoot(input: string): string {
  const trimmed = input.trim().replace(/\/+$/, "");
  // Accept a full portal.php URL or a bare host; keep scheme+host+port.
  const m = trimmed.match(/^(https?:\/\/[^/]+)/i);
  return m ? m[1] : trimmed;
}

async function stalkerRequest(
  apiBase: string, mac: string, token: string | null, url: string, config: Config,
): Promise<{ js: unknown }> {
  const res = await fetch(url, {
    headers: {
      "User-Agent": MAG_UA,
      Cookie: `mac=${encodeURIComponent(mac)}; stb_lang=en; timezone=UTC`,
      "X-User-Agent": "Model: MAG250; Link: WiFi",
      Referer: `${portalRoot(apiBase)}/c/`,
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
    },
    redirect: "follow",
    signal: AbortSignal.timeout(config.fetchTimeoutMs),
  });
  if (!res.ok) throw new Error(`stalker HTTP ${res.status}`);
  const text = await res.text();
  if (text.trim().startsWith("Authorization failed")) throw new Error("stalker refused (Authorization failed)");
  let parsed: unknown;
  try { parsed = JSON.parse(text); } catch { throw new Error("stalker non-JSON response"); }
  const js = (parsed as { js?: unknown }).js;
  if (js === false) throw new Error("stalker js=false (dead token/mac)");
  return { js };
}

/** Candidate portal API paths, mirroring OwnTV core's apiCandidates(): a full
 *  .php URL is used as-is; a bare host tries the common Ministra layouts. */
function stalkerApiCandidates(input: string): string[] {
  const trimmed = input.trim().trimEnd("/");
  if (trimmed.endsWith(".php", true)) {
    return [trimmed, ...stalkerApiCandidates(trimmed.slice(0, trimmed.lastIndexOf("/"))).filter((c) => c !== trimmed)];
  }
  const root = portalRoot(trimmed);
  return [`${root}/portal.php`, `${root}/stalker_portal/server/load.php`, `${root}/server/load.php`];
}

async function loadStalker(src: UpstreamSource, out: VirtualChannel[], cats: Map<string, string>, config: Config): Promise<void> {
  const mac = src.mac ?? macFor(src.id);
  // Try each candidate API path until one completes a handshake (redirects allowed).
  let token: string | undefined;
  let apiBase: string | undefined;
  let root = portalRoot(src.url);
  for (const candidate of stalkerApiCandidates(src.url)) {
    try {
      const hs = await stalkerRequest(root, mac, null, `${candidate}?type=stb&action=handshake&token=&JsHttpRequest=1-xml`, config);
      const t = (hs.js as { token?: string })?.token;
      if (t) { token = t; apiBase = candidate; break; }
    } catch { /* try next candidate */ }
  }
  if (!token || !apiBase) throw new Error(`stalker ${src.id}: no API candidate completed a handshake`);
  root = portalRoot(apiBase);
  // 2. get_all_channels (live). Response js is either an array or {data:[...]}.
  const ch = await stalkerRequest(root, mac, token, `${apiBase}?type=itv&action=get_all_channels&JsHttpRequest=1-xml`, config);
  const js = ch.js as unknown;
  const rows = Array.isArray(js) ? js : ((js as { data?: unknown[] })?.data ?? []);
  const seen = new Set<string>();
  for (const raw of rows as Array<Record<string, unknown>>) {
    const id = String(raw.id ?? "");
    const cmd = String(raw.cmd ?? "");
    const name = String(raw.name ?? raw.title ?? id).replace(/^#EXTINF:0,\s*/, "");
    if (!id || !cmd || !name || !matches(name, src.filter) || seen.has(id)) continue;
    seen.add(id);
    const genreId = String(raw.tv_genre_id ?? raw.genre_id ?? "0");
    const categoryId = idFor(`${src.id}:cat`, genreId).toString();
    const categoryName = String(raw.genre ?? raw.genre_title ?? "Uncategorized");
    if (!cats.has(categoryId)) cats.set(categoryId, categoryName);
    out.push({
      id: idFor(src.id, id),
      sourceId: src.id,
      sourceName: src.name,
      name: (src.prefix ?? "") + name,
      categoryId,
      categoryName,
      icon: raw.logo ? String(raw.logo) : undefined,
      epgId: raw.xmltv_id ? String(raw.xmltv_id) : undefined,
      catchup: !!raw.tv_archive && raw.tv_archive !== "0",
      addedAt: Date.now(),
      upstream: {
        kind: "stalker",
        url: "",
        base: root,
        streamId: id,
        cmd,
        mac,
        token,
        apiBase,
      },
    });
  }
}

export async function loadUpstreams(config: Config): Promise<CatalogResult> {
  const channels: VirtualChannel[] = [];
  const cats = new Map<string, string>();
  await Promise.all(
    config.sources.map(async (src) => {
      try {
        if (src.kind === "xtream") await loadXtream(src, channels, cats, config);
        else if (src.kind === "m3u") await loadM3u(src, channels, cats, config);
        else await loadStalker(src, channels, cats, config);
      } catch (err) {
        console.error(`[provider] source ${src.id} failed:`, (err as Error).message);
      }
    }),
  );
  const categories = [...cats.entries()].map(([category_id, category_name]) => ({ category_id, category_name, parent_id: 0 }));
  return { channels, categories };
}