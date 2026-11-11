/** Upstream loaders: normalize Xtream panels + M3U playlists into one virtual catalog. */
import type { Config, UpstreamSource } from "./config.ts";

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
  addedAt: number;
  /** Where the stream actually lives (panel URL, playlist URL). */
  upstream: {
    kind: "xtream" | "m3u";
    url: string; // full playable URL template (ts/m3u8 chosen by client)
    base: string;
    username?: string;
    password?: string;
    streamId: string;
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

async function loadXtream(src: UpstreamSource, out: VirtualChannel[], cats: Map<string, string>): Promise<void> {
  const base = src.url.trimEnd("/");
  const q = `username=${encodeURIComponent(src.username ?? "")}&password=${encodeURIComponent(src.password ?? "")}`;
  const [streamsRes, catsRes] = await Promise.all([
    fetch(`${base}/player_api.php?${q}&action=get_live_streams`, { signal: AbortSignal.timeout(30_000) }),
    fetch(`${base}/player_api.php?${q}&action=get_live_categories`, { signal: AbortSignal.timeout(15_000) }),
  ]);
  if (!streamsRes.ok) throw new Error(`xtream ${src.id}: streams HTTP ${streamsRes.status}`);
  const streams = (await streamsRes.json()) as Array<Record<string, unknown>>;
  let categoryMap: Record<string, string> = {};
  if (catsRes.ok) {
    const raw = (await catsRes.json()) as Array<{ category_id?: string; category_name?: string }>;
    categoryMap = Object.fromEntries(raw.map((c) => [c.category_id ?? "", c.category_name ?? c.category_id ?? ""]));
  }
  for (const s of streams) {
    const name = String(s.name ?? "").replace(/^#EXTINF:0,\s*/, "");
    if (!name || !matches(name, src.filter)) continue;
    const streamId = String(s.stream_id ?? "");
    const categoryId = String(s.category_id ?? "0");
    if (!cats.has(categoryId)) cats.set(categoryId, categoryMap[categoryId] ?? "Uncategorized");
    out.push({
      id: idFor(src.id, streamId),
      sourceId: src.id,
      sourceName: src.name,
      name: (src.prefix ?? "") + name,
      categoryId,
      categoryName: cats.get(categoryId)!,
      icon: s.stream_icon ? String(s.stream_icon) : undefined,
      epgId: s.epg_channel_id ? String(s.epg_channel_id) : undefined,
      catchup: Number(s.tv_archive ?? 0) === 1,
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

async function loadM3u(src: UpstreamSource, out: VirtualChannel[], cats: Map<string, string>): Promise<void> {
  const url = src.playlistUrl ?? src.url;
  const res = await fetch(url, { signal: AbortSignal.timeout(30_000) });
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

export async function loadUpstreams(config: Config): Promise<CatalogResult> {
  const channels: VirtualChannel[] = [];
  const cats = new Map<string, string>();
  await Promise.all(
    config.sources.map(async (src) => {
      try {
        if (src.kind === "xtream") await loadXtream(src, channels, cats);
        else await loadM3u(src, channels, cats);
      } catch (err) {
        console.error(`[provider] source ${src.id} failed:`, (err as Error).message);
      }
    }),
  );
  const categories = [...cats.entries()].map(([category_id, category_name]) => ({ category_id, category_name, parent_id: 0 }));
  return { channels, categories };
}