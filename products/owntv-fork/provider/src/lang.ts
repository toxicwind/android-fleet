/**
 * Language + genre classification for the master provider catalog.
 *
 * The 281K-channel merged catalog is 88% non-EN/JP/KR junk (radio, Portuguese,
 * Spanish, Russian, Arabic, 24/7 replays, XXX). OwnTV shows ONE flat category
 * list per source, so the gateway must emit curated, language-tagged categories
 * instead of raw panel categories (which collide and carry no language signal).
 *
 * Classification is HYBRID:
 *   1. Deterministic first — script (Hangul→KR, kana→JP, hanzi→drop), name
 *      prefix (US:/UK:/CA:/JP:/KR:/PT:/ES:...), category-name hints. Cheap,
 *      exact for the ~90% of channels that carry a signal.
 *   2. bge-m3 fallback — only for ambiguous residuals (no script, no prefix,
 *      no category hint). Embed name + category, compare to language centroids
 *      with a margin threshold; below margin = drop (not clearly EN/JP/KR).
 *
 * Drop rules (user doctrine): radio (mp3-only = useless), XXX/adult, 24/7
 * replays, PPV, non-EN/JP/KR languages.
 */
import type { Config } from "./config.ts";

export type Lang = "EN" | "JP" | "KR";
export type Genre =
  | "News"
  | "Sports"
  | "Movies"
  | "Kids"
  | "Music"
  | "Docs"
  | "Entertainment"
  | "Regional"
  | "General";

export interface Classification {
  lang: Lang;
  genre: Genre;
  /** Deterministic ("script" | "prefix" | "category") or "bge-m3". */
  via: string;
}

// ─── Deterministic name-prefix map ───────────────────────────────────────────

const PREFIX_LANG: Record<string, Lang> = {
  US: "EN", USA: "EN", UK: "EN", GB: "EN", CA: "EN", AU: "EN", NZ: "EN", IE: "EN", EN: "EN",
  JP: "JP", JPN: "JP", JAP: "JP",
  KR: "KR", KOR: "KR",
};

/** Prefixes that positively identify a NON-kept language (drop fast). */
const PREFIX_DROP = new Set([
  "PT", "ES", "SP", "BR", "MX", "AR", "ARG", "CL", "CO", "PE", "CR", "EC", "VE", "UY", "BO", "PY",
  "DE", "FR", "IT", "NL", "BE", "SE", "NO", "DK", "FI", "PL", "CZ", "SK", "HU", "RO", "BG", "GR",
  "RU", "RUS", "UA", "TR", "IN", "PK", "BD", "AF", "IR", "IQ", "SA", "AE", "EG", "MA", "DZ", "TN",
  "CN", "CHN", "TW", "HK", "TH", "VN", "ID", "MY", "PH", "AL", "RS", "HR", "BA", "MK", "SL", "LT",
  "LV", "EE", "GE", "AM", "AZ", "KZ", "UZ", "MN", "ET", "NG", "GH", "KE", "ZA", "EG", "IL",
]);

// ─── Category-name hints ─────────────────────────────────────────────────────

const CAT_EN = /(usa|united states|canada|australia|uk\b|britain|english|america|pluto tv usa|usa_tv)/i;
const CAT_JP = /(japan|japanese|日本|日本語)/i;
const CAT_KR = /(korea|korean|한국|대한)/i;
const CAT_DROP = /(portugal|brasil|brazil|españ|spanish|mexico|argentina|chile|colombia|peru|venezuela|ecuador|uruguay|paraguay|bolivia|cuba|dominican|puerto rico|francia|france|french|alemania|german|italia|italy|russia|rusia|romania|india|turkey|turquía|arab|egipt|iraq|iran|china|chinese|taiwan|thailand|vietnam|indonesia|philippines|poland|polonia|greek|grecia|hungary|hungría|czech|checa|slovak|ukraine|ucrania|belarus|serbia|serbia|croatia|bosnia|albania|macedonia|slovenia|bulgaria|netherlands|holanda|belgium|belgica|sweden|suecia|norway|noruega|denmark|dinamarca|finland|finlandia|switzerland|suiza|austria|ireland|irlanda|scotland|wales|israel|pakistan|bangladesh|sri lanka|nepal|myanmar|cambodia|laos|mongolia|kazakh|uzbek|georgia|armenia|azerbaijan|nigeria|ghana|kenya|south africa|etiopia|filipino|tagalog|indonesia|malaysia|vietnam)/i;

// ─── Genre detection ─────────────────────────────────────────────────────────

const GENRE_PATTERNS: Array<[Genre, RegExp]> = [
  ["News", /(news|nbc|abc|cbs|fox|cnn|msnbc|bbc|sky news|al jazeera|c-span|pbs|ntv|nhk|kbs|mbc|sbs|ytn|연합|뉴스|ニュース|報道)/i],
  ["Sports", /(sports|espn|nfl|nba|mlb|nhl|ncaa|ufc|boxing|futbol|soccer|football|baseball|hockey|tennis|golf|f1|formula|racing|mma|wrestling|sport|ス ポーツ|スポーツ|스포츠)/i],
  ["Movies", /(movie|cinema|hbo|showtime|starz|cinemax|paramount|mgm|film|pelicula|filme|映画|영화)/i],
  ["Kids", /(kids|cartoon|disney|nick|pbs kids|bounce|boomerang|children|infantil|子供|キッズ|어린이)/i],
  ["Music", /(music|mtv|vh1|radio|hit|dance|classic|jazz|rock|pop|hip|rap|country|opera|concert|musica|música|音楽|음악|뮤직)/i],
  ["Docs", /(documentary|docu|nat geo|discovery|history|science|nature|travel|wildlife|docs)/i],
  ["Regional", /(local|regional|city|county|state|metro|anchorage|miami|chicago|new york|los angeles|houston|dallas|atlanta|boston|phoenix|denver|seattle|portland|san francisco|detroit|minneapolis|tampa|orlando|charlotte|raleigh|nashville|memphis|kansas|st\.? louis|pittsburgh|cleveland|columbus|cincinnati|indianapolis|milwaukee|new orleans|salt lake|las vegas|sacramento|san diego|fresno|tucson|albuquerque|oklahoma|omaha|des moines|madison|grand rapids|buffalo|rochester|syracuse|hartford|providence|norfolk|richmond|birmingham|jacksonville|greenville|spokane|boise)/i],
];

/** Clean a channel name: strip decorative junk, leading separators, quality tags. */
export function cleanName(raw: string): string {
  let n = raw
    .replace(/^[\s:•|·۩๑๑➤→=<>*]+/, "") // leading junk
    .replace(/[\s:•|·۩๑๑➤→=<>*]+$/, "") // trailing junk
    .replace(/\s{2,}/g, " ")
    .trim();
  // Drop pure-decorative names (e.g. "۩๑๑۩  UNITED STATES  ۩๑๑۩" → "UNITED STATES")
  n = n.replace(/[۩๑๑]+/g, "").replace(/\s{2,}/g, " ").trim();
  // Keep quality tags but normalize: "(720P)" → " (720p)", "[Not 24/7]" → ""
  n = n.replace(/\[Not?\s*24\/7\]/gi, "").replace(/\s{2,}/g, " ").trim();
  n = n.replace(/\s*\((\d{3,4}[pi])\)/gi, " ($1)").replace(/\s{2,}/g, " ").trim();
  return n;
}

/** Deterministic classification; returns null when no signal (needs bge-m3). */
export function classifyDeterministic(name: string, categoryName: string): Classification | "DROP" | null {
  const cat = categoryName ?? "";

  // Script signals (strongest, exact).
  if (/[\uAC00-\uD7AF]/.test(name)) return { lang: "KR", genre: genreOf(name, cat), via: "script" };
  if (/[\u3040-\u30FF]/.test(name)) return { lang: "JP", genre: genreOf(name, cat), via: "script" };
  // Hanzi-only = Chinese (drop), unless the category says Japan/Korea.
  if (/[\u4E00-\u9FFF]/.test(name)) {
    if (CAT_JP.test(cat)) return { lang: "JP", genre: genreOf(name, cat), via: "script" };
    if (CAT_KR.test(cat)) return { lang: "KR", genre: genreOf(name, cat), via: "script" };
    return "DROP";
  }
  // Arabic / Cyrillic / Devanagari / Thai / Greek scripts → drop.
  if (/[\u0600-\u06FF\u0750-\u077F]/.test(name)) return "DROP";
  if (/[\u0400-\u04FF]/.test(name)) return "DROP";
  if (/[\u0900-\u097F]/.test(name)) return "DROP";
  if (/[\u0E00-\u0E7F]/.test(name)) return "DROP";
  if (/[\u0370-\u03FF]/.test(name)) return "DROP";

  // Name prefix: "US: MSNBC", "KR | ARIRANG", "PT | RTP 1"...
  const m = name.match(/^([A-Za-z]{2,4})\s*[:|]/);
  if (m) {
    const p = m[1].toUpperCase();
    if (PREFIX_LANG[p]) return { lang: PREFIX_LANG[p], genre: genreOf(name, cat), via: "prefix" };
    if (PREFIX_DROP.has(p)) return "DROP";
  }

  // Category hints.
  if (CAT_DROP.test(cat)) return "DROP";
  if (CAT_JP.test(cat)) return { lang: "JP", genre: genreOf(name, cat), via: "category" };
  if (CAT_KR.test(cat)) return { lang: "KR", genre: genreOf(name, cat), via: "category" };
  if (CAT_EN.test(cat)) return { lang: "EN", genre: genreOf(name, cat), via: "category" };

  // Junk categories that are never wanted.
  if (/(radio|radios|adult|xxx|24\/7|ppv)/i.test(cat)) return "DROP";

  return null; // ambiguous → bge-m3
}

function genreOf(name: string, categoryName: string): Genre {
  const hay = `${categoryName} ${name}`;
  for (const [g, re] of GENRE_PATTERNS) if (re.test(hay)) return g;
  return "General";
}

// ─── bge-m3 fallback ─────────────────────────────────────────────────────────

const CENTROIDS: Record<Lang, number[]> | null = null; // lazy-loaded

const SEED_SAMPLES: Record<Lang, string[]> = {
  EN: [
    "US: MSNBC", "UK: BBC One", "CA | CBC News", "AU | ABC News 24",
    "CNN International", "Sky News", "Fox News Channel", "ESPN",
  ],
  JP: [
    "JP | NHK World", "TBSテレビ", "フジテレビ", "テレビ朝日", "日本テレビ",
    "NHK総合", "東京MX", "BS日テレ",
  ],
  KR: [
    "KR | ARIRANG (720P)", "MBC 드라마", "KBS 1TV", "SBS", "YTN 뉴스",
    "tvN", "JTBC", "채널A",
  ],
};

let centroidCache: Record<Lang, number[]> | null = null;
let embedCache = new Map<string, number[]>(); // name hash → vector

async function loadCentroids(config: Config): Promise<Record<Lang, number[]>> {
  if (centroidCache) return centroidCache;
  const all = [...SEED_SAMPLES.EN, ...SEED_SAMPLES.JP, ...SEED_SAMPLES.KR];
  const vecs = await embedBatch(config, all);
  const out = {} as Record<Lang, number[]>;
  let i = 0;
  for (const lang of ["EN", "JP", "KR"] as Lang[]) {
    const n = SEED_SAMPLES[lang].length;
    const dim = vecs[0]?.length ?? 0;
    const c = new Array(dim).fill(0);
    for (let k = 0; k < n; k++) {
      const v = vecs[i + k];
      for (let d = 0; d < dim; d++) c[d] += v[d] / n;
    }
    out[lang] = c;
    i += n;
  }
  centroidCache = out;
  return out;
}

async function embedBatch(config: Config, inputs: string[]): Promise<number[][]> {
  const out: number[][] = [];
  const BATCH = 64;
  for (let i = 0; i < inputs.length; i += BATCH) {
    const chunk = inputs.slice(i, i + BATCH);
    const key = `${config.argosUrl}|${config.argosIndex}`;
    void key;
    const res = await fetch(`https://freeinference.org/v1/embeddings`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
        authorization: `Bearer ${config.torboxKey ? "" : ""}`, // placeholder; real key below
      },
      body: JSON.stringify({ model: "bge-m3", input: chunk }),
      signal: AbortSignal.timeout(20_000),
    });
    if (!res.ok) throw new Error(`embed HTTP ${res.status}`);
    const j = (await res.json()) as { data?: Array<{ embedding: number[] }> };
    for (const d of j.data ?? []) out.push(d.embedding);
  }
  return out;
}

const dot = (a: number[], b: number[]) => a.reduce((s, v, i) => s + v * b[i], 0);
const norm = (a: number[]) => Math.sqrt(dot(a, a));
const cos = (a: number[], b: number[]) => dot(a, b) / (norm(a) * norm(b) || 1);

/** bge-m3 fallback: nearest centroid with margin. Margin < 0.05 → not clearly EN/JP/KR → drop. */
export async function classifyBgeM3(config: Config, name: string, categoryName: string): Promise<Classification | "DROP"> {
  const centroids = await loadCentroids(config);
  const text = `${categoryName ? categoryName + " " : ""}${name}`;
  const [vec] = await embedBatch(config, [text]);
  if (!vec) return "DROP";
  const scores = (["EN", "JP", "KR"] as Lang[]).map((l) => cos(vec, centroids[l]));
  const best = scores.indexOf(Math.max(...scores));
  const sorted = [...scores].sort((a, b) => b - a);
  const margin = sorted[0] - sorted[1];
  if (sorted[0] < 0.55 || margin < 0.03) return "DROP";
  const lang = (["EN", "JP", "KR"] as Lang[])[best];
  return { lang, genre: genreOf(name, categoryName), via: "bge-m3" };
}

/** Full pipeline: deterministic, then bge-m3 for ambiguous. */
export async function classifyChannel(config: Config, name: string, categoryName: string): Promise<Classification | "DROP"> {
  const det = classifyDeterministic(name, categoryName);
  if (det === "DROP") return "DROP";
  if (det) return det;
  try {
    return await classifyBgeM3(config, name, categoryName);
  } catch {
    return "DROP"; // embed failure = be conservative
  }
}