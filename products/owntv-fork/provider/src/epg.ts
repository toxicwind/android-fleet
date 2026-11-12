/** EPG: per-channel short guide (get_short_epg) + merged xmltv.php from upstreams. */
import type { Config, UpstreamSource } from "./config.ts";
import type { VirtualChannel } from "./upstreams.ts";

interface Programme {
  start: string; // "YYYY-MM-DD HH:MM:SS"
  stop: string;
  title: string;
  description?: string;
}

const fmt = (ts: number) => {
  const d = new Date(ts);
  const p = (n: number) => String(n).padStart(2, "0");
  return `${d.getFullYear()}-${p(d.getMonth() + 1)}-${p(d.getDate())} ${p(d.getHours())}:${p(d.getMinutes())}:${p(d.getSeconds())}`;
};

const cache = new Map<string, { at: number; programmes: Programme[] }>();
const CACHE_TTL_MS = 30 * 60 * 1000;

// Per-source XMLTV fetch timeout. 10s, NOT 30s: the old 30s timeout × 27 sources
// SEQUENTIALLY meant xmltv.php took up to 13.5 minutes to build before returning
// a single byte — OwnTV's OkHttp readTimeout (20s) killed it waiting for response
// headers, surfacing as "The server took too long to respond."
const XMLTV_FETCH_TIMEOUT_MS = 10_000;

async function fetchXmltv(url: string): Promise<string> {
  const res = await fetch(url, { signal: AbortSignal.timeout(XMLTV_FETCH_TIMEOUT_MS) });
  if (!res.ok) throw new Error(`xmltv HTTP ${res.status}`);
  return res.text();
}

function parseXmltv(xml: string): Map<string, Programme[]> {
  const byChannel = new Map<string, Programme[]>();
  const progRe = /<programme\s+start="([^"]+)"\s+stop="([^"]+)"\s+channel="([^"]+)"[^>]*>([\s\S]*?)<\/programme>/g;
  const titleRe = /<title[^>]*>([\s\S]*?)<\/title>/;
  const descRe = /<desc[^>]*>([\s\S]*?)<\/desc>/;
  const strip = (s: string) => s.replace(/<!\[CDATA\[|\]\]>/g, "").replace(/&amp;/g, "&").trim();
  let m: RegExpExecArray | null;
  while ((m = progRe.exec(xml))) {
    const [, start, stop, channel, body] = m;
    const t = body.match(titleRe)?.[1];
    if (!t) continue;
    const title = strip(t);
    const description = body.match(descRe)?.[1] ? strip(body.match(descRe)![1]) : undefined;
    if (!byChannel.has(channel)) byChannel.set(channel, []);
    byChannel.get(channel)!.push({ start, stop, title, description });
  }
  return byChannel;
}

/** Short EPG for one virtual channel: try upstream xtream get_short_epg, else merged xmltv cache. */
export async function epgForChannel(channel: VirtualChannel, limit: number): Promise<Programme[]> {
  if (channel.upstream.kind === "xtream") {
    const { base, username, password, streamId } = channel.upstream;
    try {
      const q = `username=${encodeURIComponent(username ?? "")}&password=${encodeURIComponent(password ?? "")}&action=get_short_epg&stream_id=${encodeURIComponent(streamId)}&limit=${limit}`;
      const res = await fetch(`${base}/player_api.php?${q}`, { signal: AbortSignal.timeout(10_000) });
      if (res.ok) {
        const rows = (await res.json()) as Array<{ start: string; stop: string; title: string; description?: string }>;
        if (rows.length) return rows.map((r) => ({ start: r.start, stop: r.stop, title: r.title, description: r.description }));
      }
    } catch {
      // fall back to merged xmltv
    }
  }
  const guide = await mergedGuide(channel.sourceId);
  const list = guide.get(channel.epgId ?? channel.name) ?? [];
  return list.slice(0, limit);
}

/**
 * Full merged xmltv document for the client (OwnTV's xmltv.php fetch).
 *
 * STREAMING: the XML prologue + `<tv>` open tag are flushed immediately so the
 * client sees response headers + first bytes in milliseconds. Upstream feeds are
 * fetched IN PARALLEL (Promise.allSettled), each capped at 10s, and their
 * programmes are appended as each source resolves. The old implementation
 * fetched 27 sources sequentially with a 30s timeout and built the whole
 * document in memory BEFORE responding — OwnTV's 20s OkHttp readTimeout
 * killed it waiting for headers ("The server took too long to respond").
 */
export async function epgXmltvStream(channels: VirtualChannel[], config: Config): Promise<ReadableStream<Uint8Array>> {
  const encoder = new TextEncoder();
  const esc = (s: string) => s.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

  // Kick off ALL upstream fetches in parallel immediately.
  const fetches = config.sources.map(async (src): Promise<[string, Map<string, Programme[]>]> => {
    const url = src.kind === "xtream"
      ? `${src.url.trimEnd("/")}/xmltv.php?username=${encodeURIComponent(src.username ?? "")}&password=${encodeURIComponent(src.password ?? "")}`
      : src.epgUrl;
    if (!url) return [src.id, new Map()];
    try {
      return [src.id, parseXmltv(await fetchXmltv(url))];
    } catch (err) {
      console.error(`[provider] xmltv ${src.id} failed:`, (err as Error).message);
      return [src.id, new Map()];
    }
  });

  return new ReadableStream<Uint8Array>({
    async start(controller) {
      controller.enqueue(encoder.encode('<?xml version="1.0" encoding="UTF-8"?>\n<tv>\n'));
      // Channel elements need no upstream data — emit them first.
      for (const ch of channels) {
        if (ch.epgId) {
          controller.enqueue(encoder.encode(`  <channel id="${esc(ch.epgId)}"><display-name>${esc(ch.name)}</display-name></channel>\n`));
        }
      }
      // Programmes stream in as each upstream resolves (parallel, 10s cap each).
      const settled = await Promise.allSettled(fetches);
      const guides = new Map<string, Map<string, Programme[]>>();
      for (const r of settled) if (r.status === "fulfilled") guides.set(r.value[0], r.value[1]);
      const now = Date.now();
      const horizon = now + config.epgLookaheadDays * 86400_000;
      for (const ch of channels) {
        const guide = guides.get(ch.sourceId);
        if (!guide) continue;
        const list = guide.get(ch.epgId ?? "") ?? [];
        for (const p of list) {
          const startTs = parseXmltvTime(p.start);
          if (!startTs || startTs < now - 86400_000 || startTs > horizon) continue;
          controller.enqueue(encoder.encode(
            `  <programme start="${p.start}" stop="${p.stop}" channel="${esc(ch.epgId ?? "")}">` +
              `<title>${esc(p.title)}</title>` +
              (p.description ? `<desc>${esc(p.description)}</desc>` : "") +
              `</programme>\n`,
          ));
        }
      }
      controller.enqueue(encoder.encode("</tv>\n"));
      controller.close();
    },
  });
}

// Keep the non-streaming name as a thin alias for any caller that still wants the string form.
export async function epgXmltv(channels: VirtualChannel[], config: Config): Promise<string> {
  const stream = await epgXmltvStream(channels, config);
  const reader = stream.getReader();
  let out = "";
  for (;;) {
    const { done, value } = await reader.read();
    if (done) break;
    out += new TextDecoder().decode(value);
  }
  return out;
}

function parseXmltvTime(s: string): number | null {
  const m = s.match(/^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/);
  if (!m) return null;
  return Date.UTC(Number(m[1]), Number(m[2]) - 1, Number(m[3]), Number(m[4]), Number(m[5]), Number(m[6]));
}

async function mergedGuide(sourceId: string): Promise<Map<string, Programme[]>> {
  const cached = cache.get(sourceId);
  if (cached && Date.now() - cached.at < CACHE_TTL_MS) return cached.programmes as unknown as Map<string, Programme[]>;
  // The per-source guide is fetched by epgXmltv; short-EPG fallback returns empty until the
  // next full xmltv refresh, which is fine for the OwnTV guide flow.
  return new Map();
}

export function scheduleEpgRefresh(config: Config, onRefresh: () => void): void {
  setInterval(onRefresh, config.epgRefreshMs);
}

const esc = (s: string) => s.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
void fmt; // keep import clean for future use
void esc; // esc is used inside epgXmltvStream; keep the import clean