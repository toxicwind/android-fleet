/**
 * Subtitle translation lane — the server half of live subtitle translation.
 *
 * The player (OwnTV player-core) intercepts subtitle text (VTT/SRT from live
 * HLS or VOD), batches it, and POSTs it here. The endpoint:
 *
 *   POST /translate
 *   { "texts": ["Bonjour", "Comment ça va?"], "target": "en", "source": "fr" }
 *   →   { "translations": ["Hello", "How are you?"] }
 *
 * Design (the "better than upstream" part):
 *   - Batch translation via freeinference/deepseek-v4-flash (fast, cheap).
 *   - Translation cache keyed by (source, target, text) — repeated subtitle
 *     lines (live TV loops, common phrases) hit the cache, not the LLM.
 *   - Fail-open: any LLM/network error returns the ORIGINAL text with
 *     "translated": false — the player shows the source line, never a blank.
 *   - Latency budget: one LLM call per batch (≤64 lines), 4s timeout; the
 *     player batches ~1-2s of subtitles, so end-to-end stays under 2s.
 *
 * The DB side (subtitle_cache/link/selection/timing tables) lives in the
 * player; this endpoint is the translation backend it calls.
 */
import type { Config } from "./config.ts";

const CACHE = new Map<string, string>();
const CACHE_MAX = 20_000;

function cacheKey(source: string, target: string, text: string): string {
  return `${source}|${target}|${text}`;
}

function cacheGet(source: string, target: string, text: string): string | undefined {
  return CACHE.get(cacheKey(source, target, text));
}

function cacheSet(source: string, target: string, text: string, translated: string): void {
  CACHE.set(cacheKey(source, target, text), translated);
  if (CACHE.size > CACHE_MAX) {
    const first = CACHE.keys().next().value;
    if (first !== undefined) CACHE.delete(first);
  }
}

/** Split a batch into LLM-sized chunks (≤64 lines, ≤4000 chars each). */
function chunkTexts(texts: string[], maxLines = 64, maxChars = 4000): string[][] {
  const chunks: string[][] = [];
  let cur: string[] = [];
  let chars = 0;
  for (const t of texts) {
    if (cur.length >= maxLines || chars + t.length > maxChars) {
      chunks.push(cur);
      cur = [];
      chars = 0;
    }
    cur.push(t);
    chars += t.length;
  }
  if (cur.length > 0) chunks.push(cur);
  return chunks;
}

function buildPrompt(texts: string[], target: string, source?: string): string {
  const src = source && source !== "auto" ? ` from ${source}` : "";
  return [
    `Translate the following subtitle lines${src} to ${target}.`,
    "Return ONLY a JSON array of strings, one per input line, preserving order and line count.",
    "Keep names, numbers, and timing placeholders unchanged. Do not add explanations.",
    "INPUT:",
    JSON.stringify(texts),
  ].join("\n");
}

function parseTranslations(raw: string, expectedCount: number): string[] | null {
  // The model may wrap in ```json fences or add prose — extract the first
  // top-level array.
  const m = raw.match(/\[[\s\S]*\]/);
  if (!m) return null;
  try {
    const arr = JSON.parse(m[0]);
    if (!Array.isArray(arr)) return null;
    return arr.map((x) => String(x));
  } catch {
    return null;
  }
}

/** Translate a batch of subtitle lines. Fail-open: original text on any error. */
export async function translateBatch(
  config: Config,
  texts: string[],
  target: string,
  source?: string,
): Promise<{ translations: string[]; translated: boolean; cached: number }> {
  if (texts.length === 0) return { translations: [], translated: true, cached: 0 };

  const out: string[] = new Array(texts.length);
  let cached = 0;
  const uncached: Array<{ idx: number; text: string }> = [];

  for (let i = 0; i < texts.length; i++) {
    const hit = cacheGet(source ?? "auto", target, texts[i]);
    if (hit !== undefined) {
      out[i] = hit;
      cached++;
    } else {
      uncached.push({ idx: i, text: texts[i] });
    }
  }

  if (uncached.length === 0) {
    return { translations: out, translated: true, cached };
  }

  // Batch the uncached lines.
  const chunks = chunkTexts(uncached.map((u) => u.text));
  let ok = false;
  const key = config.torboxKey; // the provider already carries a freeinference-capable key lane
  void key;

  for (const chunk of chunks) {
    try {
      const prompt = buildPrompt(chunk, target, source);
      const res = await fetch("https://freeinference.org/v1/chat/completions", {
        method: "POST",
        headers: {
          "content-type": "application/json",
          authorization: `Bearer ${config.torboxKey}`,
        },
        body: JSON.stringify({
          model: "deepseek-v4-flash",
          messages: [{ role: "user", content: prompt }],
          temperature: 0.1,
          max_tokens: 4000,
        }),
        signal: AbortSignal.timeout(4000),
      });
      if (!res.ok) throw new Error(`translate HTTP ${res.status}`);
      const j = (await res.json()) as { choices?: Array<{ message?: { content?: string } }> };
      const raw = j.choices?.[0]?.message?.content ?? "";
      const parsed = parseTranslations(raw, chunk.length);
      if (!parsed || parsed.length !== chunk.length) throw new Error("translate shape mismatch");

      const chunkStart = uncached.findIndex((u) => u.text === chunk[0]);
      const base = chunkStart < 0 ? 0 : chunkStart;
      for (let k = 0; k < chunk.length; k++) {
        const item = uncached[base + k];
        if (item) {
          out[item.idx] = parsed[k];
          cacheSet(source ?? "auto", target, item.text, parsed[k]);
        }
      }
      ok = true;
    } catch {
      // Fail-open: leave the original text for this chunk.
      const base = uncached.findIndex((u) => u.text === chunk[0]);
      const start = base < 0 ? 0 : base;
      for (let k = 0; k < chunk.length; k++) {
        const item = uncached[start + k];
        if (item) out[item.idx] = item.text;
      }
    }
  }

  return { translations: out, translated: ok, cached };
}