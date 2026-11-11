/** Stream passthrough: proxy /live/{u}/{p}/{id}.ts|.m3u8 to the right upstream with
 *  connection caps + route-busting retries. The client (OwnTV) opens ONE connection
 *  to us; we manage the panel-facing side so per-account panel caps are never hit. */
import type { Config } from "./config.ts";
import type { VirtualChannel } from "./upstreams.ts";

const active = new Map<number, number>(); // channelId -> open upstream fetches
const retries = new Map<number, number>(); // channelId -> consecutive failures

function upstreamUrlFor(channel: VirtualChannel, url: URL): string {
  const ext = url.pathname.endsWith(".m3u8") ? "m3u8" : "ts";
  if (channel.upstream.kind === "xtream") {
    return `${channel.upstream.base}/live/${channel.upstream.username}/${channel.upstream.password}/${channel.upstream.streamId}.${ext}`;
  }
  // m3u: passthrough the resolved playlist URL; if the client asked for .ts but
  // the playlist is HLS, the panel's own redirect logic applies upstream.
  return channel.upstream.url;
}

export async function handleStream(req: Request, url: URL, channels: VirtualChannel[], config: Config): Promise<Response> {
  const segments = url.pathname.split("/").filter(Boolean); // live, user, pass, id.ext
  const idPart = segments[segments.length - 1] ?? "";
  const id = Number(idPart.split(".")[0]);
  const channel = channels.find((c) => c.id === id);
  if (!channel) return new Response("unknown stream", { status: 404 });

  const cap = config.maxConnections;
  const current = active.get(id) ?? 0;
  if (current >= cap) {
    return new Response("connection limit reached for channel", { status: 429 });
  }
  active.set(id, current + 1);

  const upstream = upstreamUrlFor(channel, url);
  const attempt = (retries.get(id) ?? 0) + 1;
  retries.set(id, attempt);
  // Route-bust: vary the request per attempt so a dead panel edge/CDN cache cannot
  // serve the same failure twice (the fix shipped to the LG player, now server-side).
  const busted = new URL(upstream);
  if (attempt > 1) busted.searchParams.set("_r", `${Date.now()}-${attempt}`);

  try {
    const upstreamRes = await fetch(busted.toString(), {
      headers: { "user-agent": req.headers.get("user-agent") ?? "VLC/3.0" },
      redirect: "follow",
      signal: AbortSignal.timeout(15_000),
    });
    if (!upstreamRes.ok && upstreamRes.status !== 206) {
      // Backoff: tell the client to retry after a short delay (OwnTV honors Retry-After
      // on 503 for live streams; 502 also triggers its reconnect ladder).
      const delay = Math.min(2_000 * 2 ** (attempt - 1), 10_000);
      retries.set(id, attempt);
      return new Response(`upstream ${upstreamRes.status}`, {
        status: upstreamRes.status === 403 || upstreamRes.status === 404 ? 502 : upstreamRes.status,
        headers: { "retry-after": String(Math.ceil(delay / 1000)) },
      });
    }
    retries.set(id, 0);
    const headers = new Headers();
    for (const [k, v] of upstreamRes.headers) {
      if (k.toLowerCase() === "content-length" || k.toLowerCase() === "transfer-encoding") continue;
      headers.set(k, v);
    }
    headers.set("access-control-allow-origin", "*");
    return new Response(upstreamRes.body, { status: upstreamRes.status, headers });
  } catch (err) {
    const msg = (err as Error).message;
    retries.set(id, attempt);
    return new Response(`upstream error: ${msg}`, { status: 502, headers: { "retry-after": "2" } });
  } finally {
    active.set(id, (active.get(id) ?? 1) - 1);
  }
}