/**
 * Curated category tree — our BETTER clone of the upstream category structure.
 *
 * Raw Xtream panels emit 8,546 categories across 56 sources, each with its own
 * ids that collide ("3"/"4"/"5" mean different things per panel). The provider
 * already namespaces ids per source; this module adds the curated layer:
 *
 *   1. Dedupe by normalized name — "RELIGIOSOS" and "Religion" and "RELIGION"
 *      collapse into one curated bucket (Religious).
 *   2. Language-tag merge — |ES|, |FR|, |TR-DUB| channel-name tags become a
 *      Language facet; "|ES| ES - Dune" lands under Spanish, not "Uncategorized".
 *   3. Quality tags preserved — |FR-4K|, [4K], 2160p surface as a 4K facet so
 *      the TV's "4K" rail actually lists 4K content.
 *   4. Curated tree — a fixed set of top-level buckets (Movies, Series, Live,
 *      Sports, News, Kids, Music, Religious, Regional, Adult, Other) with the
 *      raw category names preserved as sub-labels.
 *
 * The output is a stable category list for get_live_categories / get_vod_categories
 * that the TV can render without drowning in 8,546 near-duplicate rows.
 */
import { normalizeTitle, qualityTierOf, languageOf } from "./metadata.ts";

export interface CuratedCategory {
  category_id: string;
  category_name: string;
  parent_id: number;
  /** Raw names that folded into this bucket (dedupe provenance). */
  folded: string[];
  /** Count of channels/movies in this bucket (filled by the caller). */
  count?: number;
}

const BUCKETS: Array<{ id: string; label: string; test: RegExp }> = [
  { id: "cur-4k", label: "4K", test: /4k|2160|uhd|hdr/i },
  { id: "cur-sports", label: "Sports", test: /sport|football|soccer|futbol|futebol|tennis|basket|nba|nfl|nhl|mlb|ufc|boxing|wrestl|racing|formula|golf|cricket|hockey|baseball|beIN|ESPN|DAZN|sky sport/i },
  { id: "cur-news", label: "News", test: /news|noticias|cnn|bbc news|al jazeera|sky news|france 24|dw |euronews|rt |nhk world|press tv|teleSUR|cctv news/i },
  { id: "cur-kids", label: "Kids", test: /kids|children|cartoon|disney|nick|pbs kids|boomerang|baby|infantil/i },
  { id: "cur-music", label: "Music", test: /music|musica|mtv|vh1|radio|hit|dance|clubbing|concert/i },
  { id: "cur-religious", label: "Religious", test: /religio|church|gospel|islam|quran|christian|catholic|bible|devocional|iglesia|mezquita/i },
  { id: "cur-adult", label: "Adult", test: /adult|xxx|porn|erotic|playboy|18\+/i },
  { id: "cur-regional", label: "Regional", test: /regional|local|national|nacional|provincia|estado|municipal|comunitat/i },
  { id: "cur-movies", label: "Movies", test: /movie|film|pelicula|cinema|cine|vod|estrenos|series 24/i },
  { id: "cur-series", label: "Series", test: /series|tv shows|shows|drama|novela|telenovela|sitcom/i },
  { id: "cur-documentary", label: "Documentary", test: /document|nat geo|discovery|history|science|nature|travel|wild/i },
  { id: "cur-live", label: "Live", test: /live|24\/7|24 7|247|en vivo/i },
  { id: "cur-other", label: "Other", test: /.*/ },
];

/** Fold a raw category name into a curated bucket id. */
export function bucketOf(rawName: string): string {
  const n = rawName.toLowerCase();
  for (const b of BUCKETS) {
    if (b.test.test(n)) return b.id;
  }
  return "cur-other";
}

/** Canonical dedupe key: stem common language variants so "RELIGIOSOS" and
 *  "Religion" fold together, while genuinely distinct names stay apart. */
export function canonicalKey(rawName: string): string {
  const n = normalizeTitle(rawName);
  if (!n) return rawName.toLowerCase();
  // English/Spanish/Portuguese/French/Italian common stems.
  const stems: Array<[RegExp, string]> = [
    [/^religio/, "religion"],
    [/^noticia/, "news"],
    [/^deport/, "sports"],
    [/^futbol|^futebol|^football/, "sports"],
    [/^pelicul/, "movies"],
    [/^cine/, "movies"],
    [/^infantil|^kids|^children/, "kids"],
    [/^musica|^music/, "music"],
    [/^documental|^documentar/, "documentary"],
    [/^nacional|^national/, "regional"],
    [/^internacional|^international/, "regional"],
    [/^series|^serie/, "series"],
  ];
  for (const [re, stem] of stems) {
    if (re.test(n)) return stem;
  }
  return n;
}

/** Dedupe a raw category list by normalized name, folding into curated buckets. */
export function curateCategories(
  raw: Array<{ category_id: string; category_name: string; parent_id: number }>,
): CuratedCategory[] {
  const byBucket = new Map<string, Map<string, CuratedCategory>>();
  for (const r of raw) {
    const bucket = bucketOf(r.category_name);
    const key = canonicalKey(r.category_name);
    let bucketMap = byBucket.get(bucket);
    if (!bucketMap) {
      bucketMap = new Map();
      byBucket.set(bucket, bucketMap);
    }
    let entry = bucketMap.get(key);
    if (!entry) {
      entry = {
        category_id: `${bucket}:${bucketMap.size + 1}`,
        category_name: r.category_name,
        parent_id: 0,
        folded: [],
      };
      bucketMap.set(key, entry);
    }
    if (!entry.folded.includes(r.category_name)) entry.folded.push(r.category_name);
  }
  const out: CuratedCategory[] = [];
  for (const b of BUCKETS) {
    const bucketMap = byBucket.get(b.id);
    if (!bucketMap) continue;
    for (const entry of bucketMap.values()) out.push(entry);
  }
  return out;
}

/** Facet a channel list by language/quality tags (the |XX| / [4K] conventions). */
export function facetChannels(
  channels: Array<{ name: string; categoryId: string }>,
): { languages: Map<string, number>; qualities: Map<string, number> } {
  const languages = new Map<string, number>();
  const qualities = new Map<string, number>();
  for (const c of channels) {
    const lang = languageOf(c.name);
    if (lang) languages.set(lang, (languages.get(lang) ?? 0) + 1);
    const tier = qualityTierOf(c.name);
    if (tier !== "unknown") qualities.set(tier, (qualities.get(tier) ?? 0) + 1);
  }
  return { languages, qualities };
}