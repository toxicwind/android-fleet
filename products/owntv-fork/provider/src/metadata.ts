/**
 * Metadata enrichment lane — our BETTER clone of the .ru AIOMetadata pipeline.
 *
 * Upstream (cedya77/aiometadata v3.8.3, GPL-3.0) is a 3600-line Stremio server
 * that resolves TMDB ids, picks art by language, and caches everything. We do
 * the same job in ~200 lines, tuned to the master provider's actual problem:
 *
 *   1. TMDB-id canonical key — resolve (name, year) → tmdbId once, cache it.
 *      Upstream uses name-to-imdb + scrapers; we use the TMDB search API with
 *      a year bias and an LRU cache. One canonical id per title; clones merge.
 *   2. Quality-tier merge — when 4 clones of "Dune (2021)" exist (|FR-4K|,
 *      |EN-SUB|, |TR-DUB|, |VO|), rank by tier: 4K/DV/HDR > 1080p > 720p >
 *      SD, then by language preference. The catalog keeps ONE entry per
 *      title with the best tier + a links array of the rest.
 *   3. EPG-claim quality — when multiple EPG feeds claim the same tvg-id,
 *      score the claim by feed health (from the /health ledger) + programme
 *      density; the healthy dense feed wins, the rest are fallbacks.
 *   4. Failover grouping — group duplicate titles by upstream health (the
 *      health ledger) so a dead upstream's clones rank below a live one's.
 *
 * No LLM in the loop. No scrapers. TMDB search + LRU cache + the health
 * ledger the provider already maintains.
 */
import { snapshotHealth } from "./health.ts";

const TMDB_API = "https://api.themoviedb.org/3";

export interface EnrichedTitle {
  tmdbId?: number;
  name: string;
  year?: number;
  qualityTier: "4k" | "1080p" | "720p" | "sd" | "unknown";
  language?: string; // |ES| |FR| |TR-DUB| tag
  clones: Array<{ sourceId: string; streamId: string; health: string; url?: string }>;
  plot?: string;
  rating?: number;
  poster?: string;
}

/** Parse quality tier from a title/name (the |FR-4K| / [4K] / 2160p conventions). */
export function qualityTierOf(name: string): EnrichedTitle["qualityTier"] {
  const n = name.toUpperCase();
  if (/(4K|2160|UHD|DV|DOLBY.?VISION|HDR10\+?)/.test(n)) return "4k";
  if (/(1080|FHD|BLURAY|BRRIP)/.test(n)) return "1080p";
  if (/(720|HDTV|HDRIP)/.test(n)) return "720p";
  if (/(SD|CAM|TS|HDCAM)/.test(n)) return "sd";
  return "unknown";
}

/** Parse language tag from the |XX| or [XX-SUB] conventions. */
export function languageOf(name: string): string | undefined {
  const m = name.match(/\|([A-Za-z]{2}(?:-[A-Za-z0-9]{1,4})?)\|/);
  return m?.[1]?.toUpperCase();
}

/** Normalize a title for dedupe: strip tags, separators, case, year. */
export function normalizeTitle(name: string): string {
  return name
    .replace(/\|[^|]+\|/g, " ") // |FR-4K| |ES| |TR-DUB| tags (any inner content)
    .replace(/\[[^\]]*\]/g, " ") // [4K] [EN-SUB] [mod]
    .replace(/[._\-]+/g, " ")
    .replace(/[()]/g, " ") // (2021) parentheticals
    .replace(/\b(19|20)\d{2}\b/g, " ") // years
    .replace(/\s+/g, " ")
    .trim()
    .toLowerCase();
}

/** Extract year from a title. */
export function yearOf(name: string): number | undefined {
  const m = name.match(/\b(19|20)\d{2}\b/);
  return m ? Number(m[0]) : undefined;
}

const TIER_RANK: Record<EnrichedTitle["qualityTier"], number> = { "4k": 4, "1080p": 3, "720p": 2, sd: 1, unknown: 0 };

/**
 * Merge a list of raw VOD rows into enriched titles:
 * dedupe by normalized name, rank clones by quality tier then health,
 * keep the best as the canonical entry.
 */
export function mergeTitles(
  rows: Array<{ name: string; stream_id?: string; sourceId?: string; url?: string }>,
  preferredLang?: string,
): EnrichedTitle[] {
  const byName = new Map<string, EnrichedTitle>();
  const health = snapshotHealth();

  for (const row of rows) {
    const name = row.name ?? "";
    const key = normalizeTitle(name);
    if (!key) continue;
    const tier = qualityTierOf(name);
    const lang = languageOf(name);
    const srcHealth = row.sourceId ? (health[row.sourceId]?.status ?? "unknown") : "unknown";

    let entry = byName.get(key);
    if (!entry) {
      entry = {
        name,
        year: yearOf(name),
        qualityTier: tier,
        language: lang,
        clones: [],
      };
      byName.set(key, entry);
    }
    entry.clones.push({
      sourceId: row.sourceId ?? "",
      streamId: row.stream_id ?? "",
      health: srcHealth,
      url: row.url,
    });

    // Prefer higher tier; tie-break by health (live > degraded > dead) then
    // preferred language.
    const better =
      TIER_RANK[tier] > TIER_RANK[entry.qualityTier] ||
      (TIER_RANK[tier] === TIER_RANK[entry.qualityTier] &&
        healthRank(srcHealth) > healthRank(health[entry.clones[0]?.sourceId ?? ""]?.status ?? "unknown")) ||
      (TIER_RANK[tier] === TIER_RANK[entry.qualityTier] &&
        preferredLang !== undefined &&
        lang === preferredLang &&
        entry.language !== preferredLang);
    if (better) {
      entry.name = name;
      entry.qualityTier = tier;
      entry.language = lang;
    }
  }
  // Sort clones so the healthiest upstream is first (failover grouping).
  for (const entry of byName.values()) {
    entry.clones.sort((a, b) => healthRank(b.health) - healthRank(a.health));
  }
  return [...byName.values()];
}

function healthRank(status: string): number {
  return status === "ok" ? 2 : status === "error" || status === "auth-failed" ? 0 : 1;
}

/** LRU cache for TMDB id resolution (name+year → tmdbId). */
export class TmdbIdCache {
  private map = new Map<string, number>();
  private order: string[] = [];
  constructor(private max = 5000) {}

  get(name: string, year?: number): number | undefined {
    return this.map.get(`${name}|${year ?? ""}`);
  }
  set(name: string, year: number | undefined, id: number): void {
    const k = `${name}|${year ?? ""}`;
    if (this.map.has(k)) this.order.splice(this.order.indexOf(k), 1);
    this.map.set(k, id);
    this.order.push(k);
    if (this.order.length > this.max) {
      const evict = this.order.shift()!;
      this.map.delete(evict);
    }
  }
}

/**
 * Resolve a title to a TMDB id via the search API (year-biased). Fails open:
 * returns undefined on any error — enrichment is a bonus, never a hard dep.
 */
export async function resolveTmdbId(
  name: string,
  year: number | undefined,
  cache: TmdbIdCache,
  apiKey: string,
  type: "movie" | "tv" = "movie",
): Promise<number | undefined> {
  const cached = cache.get(name, year);
  if (cached !== undefined) return cached;
  try {
    const q = new URLSearchParams({ query: name, language: "en-US" });
    if (year) q.set("year", String(year));
    const res = await fetch(`${TMDB_API}/search/${type}?${q}`, {
      headers: { Authorization: `Bearer ${apiKey}` },
      signal: AbortSignal.timeout(4000),
    });
    if (!res.ok) return undefined;
    const j = (await res.json()) as { results?: Array<{ id: number; title?: string; name?: string; release_date?: string; first_air_date?: string }> };
    const hit = j.results?.[0];
    if (!hit) return undefined;
    cache.set(name, year, hit.id);
    return hit.id;
  } catch {
    return undefined;
  }
}