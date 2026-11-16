/** Stream passthrough: proxy /live/{u}/{p}/{id}.ts|.m3u8 to the right upstream with
 *  connection caps + route-busting retries. The client (OwnTV) opens ONE connection
 *  to us; we manage the panel-facing side so per-account panel caps are never hit.
 *
 *  HLS: when the client asks for .m3u8 we return the upstream playlist but REWRITE
 *  its relative segment paths to go through us (/hls/...), then proxy those segments
 *  back to the upstream. Without this the client resolves segments against our origin
 *  and hits a 404 (the /hls/ route did not exist). */
import type { Config } from "./config.ts";
import type { VirtualChannel } from "./upstreams.ts";

const active = new Map<number, number>(); // channelId -> open upstream fetches
const retries = new Map<number, number>(); // channelId -> consecutive failures

// Winning UA per response (set by the race) so callers can learn which UA an
// upstream accepted and reuse it for that channel's HLS segments — avoiding a
// 7× parallel request amplification on the hot segment path.
const uaForResponse = new WeakMap<Response, string>();
// channelId -> the UA that successfully fetched this channel's playlist. Segments
// reuse it (single request) and only fall back to the full race if it fails.
const cachedUa = new Map<number, string>();

// Many Xtream panels sit behind Cloudflare and UA-filter non-browser clients
// (ffmpeg/mpv/exoplayer UAs get 403/502). Upstream requests always use a
// desktop-browser UA — never the client's — so CF passes the stream through.
export const BROWSER_UA =
  "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36";

// Alternate UAs tried in order when the browser UA gets a 403/406 — some panels
// only accept a VLC, MAG (STB), or a specific IPTV-app UA for the stream path.
// TiviMate/Kodi strings are real device-reported values (verified via
// whatmyuseragent.com, Oct 2026) — providers that "make you download an app"
// check for these exact app UAs.
const VLC_UA = "VLC/3.0.24 LibVLC/3.0.24";
const MAG_UA =
  "Mozilla/5.0 (QtEmbedded; U; Linux; C) AppleWebKit/533.3 (KHTML, like Gecko) MAG200 stbapp ver: 2 rev: 250 Safari/533.3";
const EXO_UA = "ExoPlayer/2.19.1";
const TIVIMATE_UA = "TiviMate/5.3.3 (Android 13)";
const KODI_UA = "Kodi/21.3 (Windows NT 10.0.26100; Win64; x64) App_Bitness/64 Version/21.3-(21.3.0)";

// Anomalous UA: a distinctive, non-standard client string. Panels that filter
// by UA blocklist well-known apps (TiviMate/Kodi/VLC/MAG/ExoPlayer) and generic
// browser UAs, but rarely block an obscure/unique client. This one is shaped
// like a real niche IPTV player so it passes naive allow/deny lists while not
// colliding with any known blocklisted fingerprint.
const ANOMALOUS_UA =
  "Mozilla/5.0 (Linux; Android 13; SM-T970 Build/TP1A.220624.014) " +
  "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 " +
  "OTT Navigator/1.7.5.5";

const STREAM_UAS = [BROWSER_UA, VLC_UA, MAG_UA, EXO_UA, TIVIMATE_UA, KODI_UA, ANOMALOUS_UA];

/** Stalker/Ministra create_link: resolve a portal cmd to a short-lived stream URL.
 *  Mirrors OwnTV core's StalkerClient.createLink — the returned cmd may carry an
 *  `ffmpeg `/`ffrt*`/`auto ` prefix, which is stripped (the URL itself is playable). */
async function stalkerCreateLink(channel: VirtualChannel, config: Config): Promise<string> {
  const { apiBase, mac, token, cmd, streamId } = channel.upstream;
  if (!apiBase || !mac || !token || !cmd) throw new Error("stalker channel missing portal state");
  const url = `${apiBase}?type=itv&action=create_link&cmd=${encodeURIComponent(cmd)}&forced_storage=0&disable_ad=0&JsHttpRequest=1-xml`;
  const res = await fetch(url, {
    headers: {
      "User-Agent": MAG_UA,
      Cookie: `mac=${encodeURIComponent(mac)}; stb_lang=en; timezone=UTC`,
      "X-User-Agent": "Model: MAG250; Link: WiFi",
      Referer: `${channel.upstream.base}/c/`,
      Authorization: `Bearer ${token}`,
    },
    redirect: "follow",
    signal: AbortSignal.timeout(config.streamConnectTimeoutMs),
  });
  if (!res.ok) throw new Error(`stalker create_link HTTP ${res.status}`);
  const text = await res.text();
  let js: unknown;
  try { js = (JSON.parse(text) as { js?: unknown }).js; } catch { throw new Error("stalker create_link non-JSON"); }
  const raw = (js as { cmd?: string } | null)?.cmd;
  if (!raw) throw new Error(`stalker create_link returned no cmd for ${streamId} (token may have expired)`);
  return raw.trim().replace(/^(ffmpeg\s+|ffrt\s+|auto\s+)/i, "");
}

/** Test-only export: lets the protocol test drive create_link directly. */
export const stalkerCreateLinkForTest = stalkerCreateLink;

function upstreamUrlFor(channel: VirtualChannel, url: URL): string {
  const ext = url.pathname.endsWith(".m3u8") ? "m3u8" : "ts";
  if (channel.upstream.kind === "xtream") {
    return `${channel.upstream.base}/live/${channel.upstream.username}/${channel.upstream.password}/${channel.upstream.streamId}.${ext}`;
  }
  if (channel.upstream.kind === "stalker") {
    // Resolved lazily by handleStream (async create_link); upstreamUrlFor stays sync.
    return "";
  }
  // m3u: passthrough the resolved playlist URL; if the client asked for .ts but
  // the playlist is HLS, the panel's own redirect logic applies upstream.
  return channel.upstream.url;
}

/**
 * Fetch an upstream URL with a UA retry ladder. Some panels 403/406 the browser
 * UA on the stream path but accept a VLC/MAG UA. We try each UA in order and
 * return the first 2xx (or 206). Returns null if every UA fails.
 */
/**
 * Fetch an upstream URL reusing the cached winning UA for this channel when we
 * have one (set after the first successful race). A single-UA fetch avoids the
 * 7-way parallel race on every re-fetch — the CDN cuts multi-connection clients
 * short (~2s) but streams 6-13s to a single connection, so this both reduces
 * upstream load and makes each segment last much longer. Falls back to the full
 * race if the cached UA is rejected.
 */
async function fetchUpstreamWithCachedUa(
  channelId: number,
  url: string,
  config: Config,
): Promise<Response | null> {
  const ua = cachedUa.get(channelId);
  if (ua) {
    try {
      const res = await fetch(url, {
        headers: { "user-agent": ua },
        redirect: "follow",
        signal: AbortSignal.timeout(config.streamConnectTimeoutMs),
      });
      if (res.ok || res.status === 206) return res;
      try { await res.body?.cancel(); } catch { /* ignore */ }
    } catch { /* fall through to the race */ }
  }
  return fetchUpstreamWithUaRetry(url, config);
}

export async function fetchUpstreamWithUaRetry(
  url: string,
  config: Config,
  extraHeaders: Record<string, string> = {},
): Promise<Response | null> {
  // Race every UA in parallel and take the first 2xx/206. A filtering upstream
  // then fail-overs in ONE budget window instead of N serial connect timeouts —
  // this is what makes channel surfing fast (a dead/blocked route costs ~1.5s,
  // not 6×15s). Losers are aborted so they stop holding sockets.
  const budgetMs = Math.min(config.streamConnectTimeoutMs, 4000);
  const controllers = STREAM_UAS.map(() => new AbortController());
  let lastStatus = 0;
  let hardMiss: Response | null = null;

  const attempts = STREAM_UAS.map(async (ua, i) => {
    const controller = controllers[i]!;
    const timer = setTimeout(() => controller.abort(), budgetMs);
    try {
      const res = await fetch(url, {
        headers: { "user-agent": ua, ...extraHeaders },
        redirect: "follow",
        signal: controller.signal,
      });
      if (res.ok || res.status === 206) return res;
      // 404 is a hard miss (genuinely missing stream) — record it but keep
      // racing the other UAs in case one of them is the accepted client.
      if (res.status === 404) hardMiss = res;
      lastStatus = res.status;
      // Drain the body so the socket is reusable.
      try { await res.body?.cancel(); } catch { /* ignore */ }
      throw new Error(`rejected ${res.status}`);
    } finally {
      clearTimeout(timer);
    }
  });

  try {
    // First success wins. We must know WHICH candidate won so we abort only
    // the losers — aborting the winner would kill its response body.
    let winnerIndex = -1;
    const winner = await Promise.any(
      attempts.map((p, i) => p.then((res) => { winnerIndex = i; return res; })),
    );
    for (let i = 0; i < controllers.length; i++) {
      if (i !== winnerIndex) controllers[i]!.abort();
    }
    // Remember which UA won so callers can reuse it for this channel's segments.
    if (winnerIndex >= 0) uaForResponse.set(winner, STREAM_UAS[winnerIndex]!);
    return winner;
  } catch {
    for (const c of controllers) c.abort();
    if (hardMiss) return hardMiss;
    // Return a synthetic response carrying the last status so the caller can
    // decide how to map it.
    return new Response(`upstream ${lastStatus} after ${STREAM_UAS.length} UA attempts`, {
      status: lastStatus || 502,
    });
  }
}

/**
 * Single-UA fetch with a connect timeout — used by the HLS segment path after a
 * channel's winning UA is known, so segments don't re-race all 7 UAs (which
 * would amplify upstream load ~7× on the hot segment path). Returns the response
 * or null on network error / non-2xx.
 */
async function fetchUpstreamWithUa(
  url: string,
  ua: string,
  config: Config,
  extraHeaders: Record<string, string> = {},
): Promise<Response | null> {
  try {
    const res = await fetch(url, {
      headers: { "user-agent": ua, ...extraHeaders },
      redirect: "follow",
      signal: AbortSignal.timeout(config.streamConnectTimeoutMs),
    });
    if (res.ok || res.status === 206) return res;
    try { await res.body?.cancel(); } catch { /* ignore */ }
    return null;
  } catch {
    return null;
  }
}

/**
 * Rewrite an upstream HLS playlist so its relative segment paths go through us.
 * Upstreams emit segments like `/hls/5213/52132021/836/.../836_7.ts` (relative to
 * their origin). We rewrite them to `/hls/{user}/{pass}/{streamId}/...` so the
 * client fetches segments from us and we proxy them back upstream.
 */
export function rewriteHlsPlaylist(
  playlist: string,
  channel: VirtualChannel,
  config: Config,
): string {
  const up = channel.upstream;
  if (up.kind !== "xtream") return playlist;
  const segPrefix = `/hls/${encodeURIComponent(up.username)}/${encodeURIComponent(up.password)}/${encodeURIComponent(up.streamId)}`;
  const lines = playlist.split(/\r?\n/);
  const out = lines.map((line) => {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith("#")) return line;
    // Absolute URL (CDN/other host) → keep as-is; the client can reach it
    // directly and we cannot reconstruct it through our /hls/ proxy.
    if (/^https?:\/\//i.test(trimmed)) {
      return trimmed;
    }
    // Already in our /hls/{user}/{pass}/{streamId}/ shape → pass through; the
    // /hls/ route proxies it back to the owning upstream.
    if (trimmed.startsWith("/hls/")) {
      return trimmed;
    }
    // Other relative path (starts with /) → route through us.
    if (trimmed.startsWith("/")) {
      return `${segPrefix}${trimmed}`;
    }
    // Bare filename → relative to the playlist's directory; route through us.
    return `${segPrefix}/${trimmed}`;
  });
  return out.join("\n");
}

/**
 * Proxy an HLS segment (/hls/{user}/{pass}/{streamId}/...) back to the upstream.
 * The segment path after the streamId is the upstream's own relative path.
 */
export async function handleHlsSegment(
  req: Request,
  url: URL,
  channels: VirtualChannel[],
  config: Config,
): Promise<Response> {
  const segments = url.pathname.split("/").filter(Boolean); // hls, user, pass, streamId, ...rest
  if (segments.length < 4) return new Response("bad hls path", { status: 404 });
  const [, user, pass, streamId, ...rest] = segments;
  // Find a channel whose upstream matches this user/pass/streamId.
  const channel = channels.find(
    (c) =>
      c.upstream.kind === "xtream" &&
      c.upstream.username === decodeURIComponent(user) &&
      c.upstream.password === decodeURIComponent(pass) &&
      c.upstream.streamId === decodeURIComponent(streamId),
  );
  if (!channel) return new Response("unknown hls segment", { status: 404 });
  const upstreamPath = `/hls/${decodeURIComponent(user)}/${decodeURIComponent(pass)}/${decodeURIComponent(streamId)}/` + rest.map((s) => decodeURIComponent(s)).join("/");
  const upstream = `${channel.upstream.base}${upstreamPath}`;
  // The upstream 403s HLS segments unless we present a Referer matching its
  // live playlist URL (verified: segment 403 without referer, 200 with it).
  const referer = `${channel.upstream.base}/live/${channel.upstream.username}/${channel.upstream.password}/${channel.upstream.streamId}.m3u8`;
  // Reuse the winning UA from the playlist fetch (single request) so segments
  // don't re-race all 7 UAs — that would amplify upstream load ~7× on the hot
  // segment path. Fall back to the full race only if the cached UA fails.
  const cached = cachedUa.get(channel.id);
  let res = cached
    ? await fetchUpstreamWithUa(upstream, cached, config, { referer })
    : null;
  if (!res) {
    res = await fetchUpstreamWithUaRetry(upstream, config, { referer });
  }
  if (!res) return new Response("upstream hls segment failed", { status: 502 });
  if (!res.ok && res.status !== 206) {
    const status = res.status === 403 || res.status === 404 ? 502 : res.status;
    return new Response(`upstream hls segment ${res.status}`, { status });
  }
  const headers = new Headers();
  for (const [k, v] of res.headers) {
    if (k.toLowerCase() === "content-length" || k.toLowerCase() === "transfer-encoding") continue;
    headers.set(k, v);
  }
  headers.set("access-control-allow-origin", "*");
  return new Response(res.body, { status: res.status, headers });
}

const TS_PACKET_BYTES = 188;
const TS_SYNC_BYTE = 0x47;

/**
 * Wrap a progressive .ts upstream body so that when the upstream closes — a
 * time-limited signed stream the CDN cuts every ~25s — we transparently re-fetch
 * a fresh upstream and splice the MPEG-TS streams together. The client sees ONE
 * continuous stream: the cut is invisible (no ENDED, no spinner). If a refetch
 * fails, the stream just ends and the client's own reconnect ladder takes over.
 */
function spliceLiveTsStream(
  firstBody: ReadableStream<Uint8Array>,
  refetch: () => Promise<ReadableStream<Uint8Array> | null>,
): ReadableStream<Uint8Array> {
  // A segment that ends without yielding a single full TS packet is either a
  // broken/empty upstream or non-TS data — refetching it would loop forever.
  // Give up after a few such empty segments and let the client's ladder handle it.
  const MAX_EMPTY_SEGMENTS = 3;
  let reader = firstBody.getReader();
  let tail = new Uint8Array(0);
  let ended = false;
  let emptySegments = 0;
  let segmentProducedPackets = false;

  return new ReadableStream({
    async pull(controller) {
      if (ended) {
        controller.close();
        return;
      }
      try {
        while (true) {
          const { value, done } = await reader.read();
          if (done) {
            if (!segmentProducedPackets) {
              if (++emptySegments >= MAX_EMPTY_SEGMENTS) {
                ended = true;
                controller.close();
                return;
              }
            } else {
              emptySegments = 0;
            }
            const next = await refetch();
            if (!next) {
              ended = true;
              controller.close();
              return;
            }
            reader.releaseLock();
            reader = next.getReader();
            tail = new Uint8Array(0); // drop the partial packet; the new segment realigns
            segmentProducedPackets = false;
            continue;
          }
          const merged = new Uint8Array(tail.length + value.length);
          merged.set(tail, 0);
          merged.set(value, tail.length);
          tail = merged;
          let off = 0;
          let enqueued = false;
          while (tail.length - off >= TS_PACKET_BYTES) {
            if (tail[off] === TS_SYNC_BYTE) {
              controller.enqueue(tail.slice(off, off + TS_PACKET_BYTES));
              enqueued = true;
              off += TS_PACKET_BYTES;
            } else {
              let i = off + 1;
              while (i < tail.length && tail[i] !== TS_SYNC_BYTE) i++;
              off = i;
            }
          }
          tail = tail.slice(off);
          if (enqueued) {
            segmentProducedPackets = true;
            return;
          }
        }
      } catch {
        ended = true;
        try { controller.close(); } catch { /* already closed */ }
      }
    },
    cancel() {
      try { reader.releaseLock(); } catch { /* ignore */ }
    },
  });
}

export async function handleStream(req: Request, url: URL, channels: VirtualChannel[], config: Config): Promise<Response> {
  const segments = url.pathname.split("/").filter(Boolean); // live, user, pass, id.ext
  const idPart = segments[segments.length - 1] ?? "";
  const id = Number(idPart.split(".")[0]);
  const channel = channels.find((c) => c.id === id);
  if (!channel) return new Response("unknown stream", { status: 404 });
  // Stream capability from the VLC-audit SSOT: a no-streams panel answers the
  // API but serves no playable streams — fail fast instead of proxying to a
  // dead host (the stream host vs portal host divergence).
  if (channel.upstream.streamStatus === "no-streams") {
    return new Response("source has no playable streams (no-streams)", { status: 502 });
  }
  const cap = config.maxConnections;
  const current = active.get(id) ?? 0;
  if (current >= cap) {
    return new Response("connection limit reached for channel", { status: 429 });
  }
  active.set(id, current + 1);

  // Stalker channels resolve their playable URL lazily via create_link (short-lived token).
  let upstream = upstreamUrlFor(channel, url);
  if (channel.upstream.kind === "stalker") {
    try {
      upstream = await stalkerCreateLink(channel, config);
    } catch (err) {
      active.delete(id);
      return new Response(`stalker create_link failed: ${(err as Error).message}`, { status: 502 });
    }
  }
  const attempt = (retries.get(id) ?? 0) + 1;
  retries.set(id, attempt);
  // Route-bust: vary the request per attempt so a dead panel edge/CDN cache cannot
  // serve the same failure twice (the fix shipped to the LG player, now server-side).
  const busted = new URL(upstream);
  if (attempt > 1) busted.searchParams.set("_r", `${Date.now()}-${attempt}`);

  try {
    const upstreamRes = await fetchUpstreamWithUaRetry(busted.toString(), config);
    if (!upstreamRes) {
      retries.set(id, attempt);
      return new Response("upstream unreachable after UA retries", { status: 502, headers: { "retry-after": "2" } });
    }
    if (!upstreamRes.ok && upstreamRes.status !== 206) {
      // Backoff: tell the client to retry after a short delay (OwnTV honors Retry-After
      // on 503 for live streams; 502 also triggers its reconnect ladder).
      const delay = Math.min(2_000 * 2 ** (attempt - 1), 10_000);
      retries.set(id, attempt);
      const status = upstreamRes.status === 403 || upstreamRes.status === 404 ? 502 : upstreamRes.status;
      return new Response(`upstream ${upstreamRes.status}`, {
        status,
        headers: { "retry-after": String(Math.ceil(delay / 1000)) },
      });
    }
    retries.set(id, 0);
    // Remember which UA this upstream accepted so HLS segments reuse it (single
    // request) instead of re-racing all 7 UAs per segment.
    const won = uaForResponse.get(upstreamRes);
    if (won) cachedUa.set(channel.id, won);
    const headers = new Headers();
    for (const [k, v] of upstreamRes.headers) {
      if (k.toLowerCase() === "content-length" || k.toLowerCase() === "transfer-encoding") continue;
      headers.set(k, v);
    }
    headers.set("access-control-allow-origin", "*");

    // HLS playlist: rewrite relative segment paths to route through us so the
    // client can actually fetch the segments (the /hls/ route proxies them).
    const isHls = url.pathname.endsWith(".m3u8") ||
      (upstreamRes.headers.get("content-type") ?? "").includes("mpegurl") ||
      (upstreamRes.headers.get("content-type") ?? "").includes("m3u8");
    if (isHls) {
      const playlist = await upstreamRes.text();
      const rewritten = rewriteHlsPlaylist(playlist, channel, config);
      headers.set("content-type", "application/x-mpegurl");
      return new Response(rewritten, { status: 200, headers });
    }

    // Progressive .ts live streams: wrap the upstream body so that when the CDN
    // cuts a time-limited signed stream (every ~25s), we transparently re-fetch a
    // fresh upstream and splice the TS streams — the cut is invisible to the client.
    if (upstreamRes.body) {
      const spliced = spliceLiveTsStream(upstreamRes.body, async () => {
        const nextAttempt = (retries.get(id) ?? 0) + 1;
        retries.set(id, nextAttempt);
        const bustedNext = new URL(upstream);
        bustedNext.searchParams.set("_r", `${Date.now()}-${nextAttempt}`);
        const nextRes = await fetchUpstreamWithCachedUa(channel.id, bustedNext.toString(), config);
        if (!nextRes || (!nextRes.ok && nextRes.status !== 206)) return null;
        return nextRes.body;
      });
      return new Response(spliced, { status: upstreamRes.status, headers });
    }
    return new Response(upstreamRes.body, { status: upstreamRes.status, headers });
  } catch (err) {
    const msg = (err as Error).message;
    retries.set(id, attempt);
    return new Response(`upstream error: ${msg}`, { status: 502, headers: { "retry-after": "2" } });
  } finally {
    active.set(id, (active.get(id) ?? 1) - 1);
  }
}
