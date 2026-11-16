/**
 * Stream passthrough tests — HLS playlist rewriting (relative segment paths →
 * /hls/ through-us routing), UA retry on 403, and /hls/ segment proxying.
 *
 * NOTE: each mock upstream binds to a DYNAMIC port (port 0) so tests never
 * collide with each other or with the keypool service (which probes 38992).
 * The actual bound port is read from `server.port` and threaded into the
 * channel/config so the code under test talks to the right server.
 */
import { test, expect } from "bun:test";
import { defineConfig } from "../src/config.ts";
import type { Config } from "../src/config.ts";
import { handleStream, handleHlsSegment, rewriteHlsPlaylist, BROWSER_UA } from "../src/stream.ts";
import type { VirtualChannel } from "../src/upstreams.ts";

function makeChannel(port: number, overrides: Partial<VirtualChannel> = {}): VirtualChannel {
  return {
    id: 741525989,
    sourceId: "s2",
    sourceName: "go.iptvhn.net",
    name: "ESPN 1 HD",
    categoryId: "1642864682",
    categoryName: "DEPORTES HD",
    catchup: false,
    addedAt: Date.now(),
    upstream: {
      kind: "xtream",
      url: `http://127.0.0.1:${port}/live/5213/52132021/836`,
      base: `http://127.0.0.1:${port}`,
      username: "5213",
      password: "52132021",
      streamId: "836",
      streamStatus: "stream-ok",
    },
    ...overrides,
  };
}

function makeConfig(port: number): Config {
  return defineConfig({
    PROVIDER_SOURCES: `xtream|id=s2|name=go.iptvhn.net|url=http://127.0.0.1:${port}|user=5213|pass=52132021|status=stream-ok`,
    PROVIDER_STREAM_CONNECT_TIMEOUT_MS: "3000",
    PROVIDER_MAX_CONNECTIONS: "4",
  });
}

test("rewriteHlsPlaylist routes relative segment paths through /hls/", () => {
  const channel = makeChannel(38992);
  const config = makeConfig(38992);
  const playlist = [
    "#EXTM3U",
    "#EXT-X-VERSION:3",
    "#EXT-X-MEDIA-SEQUENCE:7",
    "#EXTINF:9.609600,",
    "/hls/5213/52132021/836/4b264f155e8939528d82570a01b8a0a0/836_7.ts",
    "#EXTINF:10.310300,",
    "836_8.ts",
    "#EXTINF:9.709700,",
    "http://cdn.example.com/seg/836_9.ts",
  ].join("\n");

  const rewritten = rewriteHlsPlaylist(playlist, channel, config);
  const lines = rewritten.split("\n");

  // Already in /hls/{user}/{pass}/{streamId}/ shape → pass through unchanged.
  expect(lines[4]).toBe("/hls/5213/52132021/836/4b264f155e8939528d82570a01b8a0a0/836_7.ts");
  // Bare filename → /hls/{user}/{pass}/{streamId}/filename.
  expect(lines[6]).toBe("/hls/5213/52132021/836/836_8.ts");
  // Absolute URL → kept as-is (client reaches CDN directly).
  expect(lines[8]).toBe("http://cdn.example.com/seg/836_9.ts");
  // Comments untouched.
  expect(lines[0]).toBe("#EXTM3U");
});

test("handleStream retries with alternate UA when browser UA gets 403", async () => {
  // Mock upstream: 403 the browser UA, 200 the VLC UA.
  let browserUaSeen = false;
  let vlcUaSeen = false;
  const upstream = Bun.serve({
    port: 0,
    async fetch(req) {
      const ua = req.headers.get("user-agent") ?? "";
      if (ua === BROWSER_UA) {
        browserUaSeen = true;
        return new Response("forbidden", { status: 403 });
      }
      if (ua.includes("VLC")) {
        vlcUaSeen = true;
        const pkt = new Uint8Array(188);
        pkt[0] = 0x47;
        pkt[1] = 0xAB;
        const body = new Uint8Array(188 * 5);
        for (let i = 0; i < 5; i++) body.set(pkt, i * 188);
        return new Response(body, {
          status: 200,
          headers: { "content-type": "video/mp2t" },
        });
      }
      return new Response("forbidden", { status: 403 });
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    const url = new URL("http://127.0.0.1:25818/live/master/master/741525989.ts");
    const res = await handleStream(new Request(url), url, [channel], config);
    expect(res.status).toBe(200);
    expect(browserUaSeen).toBe(true);
    expect(vlcUaSeen).toBe(true);
    const reader = res.body!.getReader();
    const { value } = await reader.read();
    expect(value!.length).toBe(188);
    expect(value![0]).toBe(0x47);
    await reader.cancel();
  } finally {
    upstream.stop();
  }
});

test("handleStream returns 502 only after all UA attempts fail", async () => {
  let attempts = 0;
  const upstream = Bun.serve({
    port: 0,
    async fetch() {
      attempts++;
      return new Response("forbidden", { status: 403 });
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    const url = new URL("http://127.0.0.1:25818/live/master/master/741525989.ts");
    const res = await handleStream(new Request(url), url, [channel], config);
    expect(res.status).toBe(502);
    // All 7 UAs tried (browser, VLC, MAG, ExoPlayer, TiviMate, Kodi, anomalous).
    expect(attempts).toBe(7);
  } finally {
    upstream.stop();
  }
});

test("handleStream rewrites HLS playlist and returns it", async () => {
  const upstream = Bun.serve({
    port: 0,
    async fetch() {
      return new Response(
        "#EXTM3U\n#EXTINF:9.6,\n/hls/5213/52132021/836/seg/836_7.ts\n",
        { status: 200, headers: { "content-type": "application/x-mpegurl" } },
      );
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    const url = new URL("http://127.0.0.1:25818/live/master/master/741525989.m3u8");
    const res = await handleStream(new Request(url), url, [channel], config);
    expect(res.status).toBe(200);
    const body = await res.text();
    expect(body).toContain("/hls/5213/52132021/836/seg/836_7.ts");
    expect(res.headers.get("content-type")).toContain("mpegurl");
  } finally {
    upstream.stop();
  }
});

test("handleHlsSegment proxies a segment back to the upstream", async () => {
  let upstreamSeen = false;
  const upstream = Bun.serve({
    port: 0,
    async fetch(req) {
      const url = new URL(req.url);
      if (url.pathname.startsWith("/hls/5213/52132021/836/seg/836_7.ts")) {
        upstreamSeen = true;
        return new Response("segment-bytes", {
          status: 200,
          headers: { "content-type": "video/mp2t" },
        });
      }
      return new Response("not found", { status: 404 });
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    const url = new URL("http://127.0.0.1:25818/hls/5213/52132021/836/seg/836_7.ts");
    const res = await handleHlsSegment(new Request(url), url, [channel], config);
    expect(res.status).toBe(200);
    expect(upstreamSeen).toBe(true);
    expect(await res.text()).toBe("segment-bytes");
  } finally {
    upstream.stop();
  }
});

test("handleHlsSegment reuses the cached winning UA (single request, not 7×)", async () => {
  let segmentRequests = 0;
  let playlistVlcSeen = false;
  const upstream = Bun.serve({
    port: 0,
    async fetch(req) {
      const ua = req.headers.get("user-agent") ?? "";
      const url = new URL(req.url);
      if (url.pathname.endsWith(".m3u8")) {
        // Playlist: only the VLC UA gets 200, so VLC wins the race.
        if (ua.includes("VLC")) {
          playlistVlcSeen = true;
          return new Response(
            "#EXTM3U\n#EXTINF:9.6,\n/hls/5213/52132021/836/seg/836_7.ts\n",
            { status: 200, headers: { "content-type": "application/x-mpegurl" } },
          );
        }
        return new Response("forbidden", { status: 403 });
      }
      // Segment path: count every request; only VLC UA is accepted.
      segmentRequests++;
      if (ua.includes("VLC")) {
        return new Response("segment-bytes", { status: 200, headers: { "content-type": "video/mp2t" } });
      }
      return new Response("forbidden", { status: 403 });
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    // First fetch the playlist to populate the per-channel UA cache.
    const pUrl = new URL("http://127.0.0.1:25818/live/master/master/741525989.m3u8");
    await handleStream(new Request(pUrl), pUrl, [channel], config);
    expect(playlistVlcSeen).toBe(true);
    // Now fetch a segment — it should reuse the cached VLC UA with ONE request,
    // not re-race all 7 UAs (which would amplify upstream load ~7×).
    const sUrl = new URL("http://127.0.0.1:25818/hls/5213/52132021/836/seg/836_7.ts");
    const res = await handleHlsSegment(new Request(sUrl), sUrl, [channel], config);
    expect(res.status).toBe(200);
    expect(await res.text()).toBe("segment-bytes");
    expect(segmentRequests).toBe(1);
  } finally {
    upstream.stop();
  }
});

test("spliceLiveTsStream hides upstream cuts (re-fetches and splices)", async () => {
  const packet = (n: number) => {
    const p = new Uint8Array(188);
    p[0] = 0x47;
    p[1] = n & 0xff;
    return p;
  };
  let fetches = 0;
  const upstream = Bun.serve({
    port: 0,
    async fetch(req) {
      const url = new URL(req.url);
      fetches++;
      if (url.searchParams.has("_r")) {
        // Refetched segment: plenty of packets; the test cancels before it ends.
        const body = new Uint8Array(188 * 100);
        for (let i = 0; i < 100; i++) body.set(packet(100 + i), i * 188);
        return new Response(body, { status: 200, headers: { "content-type": "video/mp2t" } });
      }
      // First segment: 2 full packets + a trailing partial packet (50 bytes) — the
      // CDN cut lands mid-packet, which the splice must drop and realign past.
      const body = new Uint8Array(188 * 2 + 50);
      for (let i = 0; i < 2; i++) body.set(packet(i), i * 188);
      for (let i = 0; i < 50; i++) body[188 * 2 + i] = i;
      return new Response(body, { status: 200, headers: { "content-type": "video/mp2t" } });
    },
  });

  try {
    const channel = makeChannel(upstream.port);
    const config = makeConfig(upstream.port);
    const url = new URL("http://127.0.0.1:25818/live/master/master/741525989.ts");
    const res = await handleStream(new Request(url), url, [channel], config);
    expect(res.status).toBe(200);
    const reader = res.body!.getReader();
    const got: number[] = [];
    for (let i = 0; i < 5; i++) {
      const { value } = await reader.read();
      expect(value!.length).toBe(188);
      expect(value![0]).toBe(0x47);
      got.push(value![1]);
    }
    await reader.cancel();
    // First two packets come from segment 1, the next three from the refetched segment.
    expect(got.slice(0, 2)).toEqual([0, 1]);
    expect(got.slice(2)).toEqual([100, 101, 102]);
    expect(fetches).toBeGreaterThanOrEqual(2);
  } finally {
    upstream.stop();
  }
});
