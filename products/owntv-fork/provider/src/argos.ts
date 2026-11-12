/** Argos-backed fuzzy search for the master provider — OwnTV's search override.
 *  Proxies to the local argos ES-compatible engine (:25254) with fuzziness,
 *  returning results in OwnTV's SearchResults-ish shape (channels/series/movies). */
import type { Config } from "./config.ts";

export interface ArgosHit {
  index: string;
  id: string;
  score: number;
  path?: string;
  content?: string;
  name?: string;
}

export interface ProviderSearchResult {
  channels: Array<{ stream_id: number; name: string; category_id: string }>;
  series: Array<{ series_id: string; name: string; category_id: string }>;
  movies: Array<{ movie_id: string; name: string; category_id: string }>;
  programmes: Array<{ title: string; channel: string; start: string; stop: string }>;
  raw: ArgosHit[];
}

/** Fuzzy multi_match across the argos index; fuzziness AUTO like a search-as-you-type box. */
export async function searchArgos(config: Config, query: string, limit = 20): Promise<ArgosHit[]> {
  if (!query.trim()) return [];
  const body = {
    query: {
      multi_match: {
        query: query.trim(),
        fields: ["*"],
        fuzziness: "AUTO",
        operator: "and",
      },
    },
    size: limit,
  };
  try {
    const res = await fetch(`${config.argosUrl}/${config.argosIndex}/_search`, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(body),
      signal: AbortSignal.timeout(config.fetchTimeoutMs),
    });
    if (!res.ok) return [];
    const j = (await res.json()) as { hits?: { hits?: Array<{ _index: string; _id: string; _score?: number; _source?: Record<string, unknown> }> } };
    return (j.hits?.hits ?? []).map((h) => ({
      index: h._index,
      id: h._id,
      score: h._score ?? 0,
      path: typeof h._source?.path === "string" ? h._source.path : undefined,
      content: typeof h._source?.content === "string" ? h._source.content : undefined,
      name: typeof h._source?.name === "string" ? h._source.name : undefined,
    }));
  } catch {
    return [];
  }
}

/** Cap per section in search results (channels/series/movies). */
const SECTION_RESULT_CAP = 12;

/** Map argos hits into the provider's search-result shape (channels first). */
export function toProviderResults(hits: ArgosHit[]): ProviderSearchResult {
  const channels = hits
    .filter((h) => h.path?.includes("channel") || h.name)
    .slice(0, SECTION_RESULT_CAP)
    .map((h, i) => ({
      stream_id: 900_000 + i,
      name: h.name ?? h.path?.split("/").pop() ?? `hit-${i}`,
      category_id: "search",
    }));
  const series = hits
    .filter((h) => h.path?.includes("series") || h.path?.includes("movie"))
    .slice(0, SECTION_RESULT_CAP)
    .map((h, i) => ({
      series_id: `argos-${i}`,
      name: h.name ?? h.path?.split("/").pop() ?? `hit-${i}`,
      category_id: "search",
    }));
  return {
    channels,
    series,
    movies: [],
    programmes: [],
    raw: hits,
  };
}