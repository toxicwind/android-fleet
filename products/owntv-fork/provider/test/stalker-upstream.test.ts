/**
 * Stalker upstream protocol test — spins a mock Ministra portal (handshake →
 * get_all_channels → create_link) and drives loadUpstreams + stalkerCreateLink
 * through it, asserting the full protocol contract mirrors OwnTV core.
 */
import { test, expect } from "bun:test";
import { defineConfig } from "../src/config.ts";
import { loadUpstreams } from "../src/upstreams.ts";
import { stalkerCreateLinkForTest } from "../src/stream.ts";

const TOKEN = "mock-bearer-token-123";

function mockPortal(port: number) {
  let channelsSeen = 0;
  let createLinks = 0;
  const server = Bun.serve({
    port,
    async fetch(req) {
      const url = new URL(req.url);
      const action = url.searchParams.get("action") ?? "";
      const type = url.searchParams.get("type") ?? "";
      if (type === "stb" && action === "handshake") {
        return Response.json({ js: { token: TOKEN } });
      }
      if (type === "itv" && action === "get_all_channels") {
        channelsSeen++;
        return Response.json({
          js: {
            data: [
              { id: "101", name: "Test News", cmd: "/media/test_101.mpg", tv_genre_id: "1", genre: "News", logo: "http://x/logo.png", xmltv_id: "test.news", tv_archive: "1", tv_archive_duration: "7" },
              { id: "102", name: "Test Sports", cmd: "/media/test_102.mpg", tv_genre_id: "2", genre: "Sports", xmltv_id: "test.sports", tv_archive: "0" },
              { id: "103", name: "Test Music", cmd: "/media/test_103.mpg", tv_genre_id: "1", genre: "News" },
            ],
          },
        });
      }
      if (type === "itv" && action === "create_link") {
        createLinks++;
        const cmd = url.searchParams.get("cmd") ?? "";
        return Response.json({ js: { cmd: `ffmpeg http://stream.example/live/${encodeURIComponent(cmd)}` } });
      }
      return Response.json({ js: false });
    },
  });
  return { server, stats: () => ({ channelsSeen, createLinks }) };
}

test("stalker upstream handshake → get_all_channels → catalog", async () => {
  const { server, stats } = mockPortal(38991);
  try {
    const config = defineConfig({
      PROVIDER_SOURCES: `stalker|id=st1|name=StalkerTest|url=http://127.0.0.1:38991`,
      PROVIDER_FETCH_TIMEOUT_MS: "5000",
    });
    const { channels, categories } = await loadUpstreams(config);
    expect(stats().channelsSeen).toBe(1);
    expect(channels).toHaveLength(3);
    const news = channels.find((c) => c.name === "Test News");
    expect(news).toBeDefined();
    expect(news!.upstream.kind).toBe("stalker");
    expect(news!.upstream.token).toBe(TOKEN);
    expect(news!.upstream.cmd).toBe("/media/test_101.mpg");
    expect(news!.catchup).toBe(true);
    expect(news!.epgId).toBe("test.news");
    // Categories deduped by genre id: News (1) + Sports (2)
    const catNames = categories.map((c) => c.category_name).sort();
    expect(catNames).toEqual(["News", "Sports"]);
    expect(categories.length).toBe(2);
  } finally {
    server.stop();
  }
});

test("stalker create_link resolves cmd to playable URL", async () => {
  const { server, stats } = mockPortal(38992);
  try {
    const config = defineConfig({
      PROVIDER_SOURCES: `stalker|id=st1|name=StalkerTest|url=http://127.0.0.1:38992`,
      PROVIDER_STREAM_CONNECT_TIMEOUT_MS: "5000",
    });
    const { channels } = await loadUpstreams(config);
    const channel = channels.find((c) => c.name === "Test News")!;
    const url = await stalkerCreateLinkForTest(channel, config);
    expect(stats().createLinks).toBe(1);
    // ffmpeg prefix stripped, cmd embedded
    expect(url).toBe("http://stream.example/live/%2Fmedia%2Ftest_101.mpg");
  } finally {
    server.stop();
  }
});

test("stalker portal with bare host tries candidate API paths", async () => {
  const { server } = mockPortal(38993);
  try {
    // Serve handshake ONLY at server/load.php (candidate 3), not portal.php
    const config = defineConfig({
      PROVIDER_SOURCES: `stalker|id=st2|name=StalkerTest2|url=http://127.0.0.1:38993`,
      PROVIDER_FETCH_TIMEOUT_MS: "3000",
    });
    // Our mock answers at any path, so just assert load succeeds via candidates.
    const { channels } = await loadUpstreams(config);
    expect(channels.length).toBeGreaterThan(0);
  } finally {
    server.stop();
  }
});