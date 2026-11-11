import { describe, expect, test, mock } from "bun:test";
import { defineConfig, parseSources } from "../src/config.ts";

describe("parseSources", () => {
  test("parses xtream source spec", () => {
    const srcs = parseSources("xtream|id=main|name=Main|url=http://1tv41.icu:8080|user=VMh8na|pass=793530");
    expect(srcs).toHaveLength(1);
    expect(srcs[0].kind).toBe("xtream");
    expect(srcs[0].id).toBe("main");
    expect(srcs[0].url).toBe("http://1tv41.icu:8080");
    expect(srcs[0].username).toBe("VMh8na");
    expect(srcs[0].password).toBe("793530");
  });

  test("parses m3u source with playlist url", () => {
    const srcs = parseSources("m3u|id=extras|name=Extras|playlist=https://example.com/list.m3u");
    expect(srcs).toHaveLength(1);
    expect(srcs[0].kind).toBe("m3u");
    expect(srcs[0].playlistUrl).toBe("https://example.com/list.m3u");
  });

  test("parses multiple sources separated by semicolons", () => {
    const srcs = parseSources("xtream|id=a|name=A|url=http://a;m3u|id=b|name=B|playlist=http://b");
    expect(srcs).toHaveLength(2);
    expect(srcs[0].id).toBe("a");
    expect(srcs[1].id).toBe("b");
  });

  test("returns empty for blank spec", () => {
    expect(parseSources("")).toEqual([]);
    expect(parseSources("   ")).toEqual([]);
  });

  test("parses prefix and filter fields", () => {
    const srcs = parseSources("xtream|id=a|name=A|url=http://a|prefix=US · |filter=^CNN");
    expect(srcs[0].prefix).toBe("US · ");
    expect(srcs[0].filter).toBe("^CNN");
  });

  test("parses max connections override", () => {
    const srcs = parseSources("xtream|id=a|name=A|url=http://a|max=2");
    expect(srcs[0].maxConnections).toBe(2);
  });
});

describe("defineConfig", () => {
  test("applies defaults", () => {
    const c = defineConfig({});
    expect(c.port).toBe(8081);
    expect(c.clientUsername).toBe("master");
    expect(c.maxConnections).toBe(4);
    expect(c.catalogTtlMs).toBe(600000);
    expect(c.epgRefreshMs).toBe(21600000);
    expect(c.epgLookaheadDays).toBe(2);
  });

  test("applies env overrides", () => {
    const c = defineConfig({
      PROVIDER_PORT: "9090",
      PROVIDER_USER: "toxic",
      PROVIDER_PASS: "secret",
      PROVIDER_MAX_CONNECTIONS: "8",
      PROVIDER_CATALOG_TTL_MS: "1000",
      PROVIDER_EPG_REFRESH_MS: "2000",
      PROVIDER_EPG_LOOKAHEAD_DAYS: "5",
      PROVIDER_SOURCES: "xtream|id=a|name=A|url=http://a",
      PROVIDER_VELVET_PLUGINS: "/tmp/plugins.json",
      PROVIDER_ARGOS_URL: "http://argos:9999",
      PROVIDER_ARGOS_INDEX: "test-index",
    });
    expect(c.port).toBe(9090);
    expect(c.clientUsername).toBe("toxic");
    expect(c.clientPassword).toBe("secret");
    expect(c.maxConnections).toBe(8);
    expect(c.catalogTtlMs).toBe(1000);
    expect(c.epgRefreshMs).toBe(2000);
    expect(c.epgLookaheadDays).toBe(5);
    expect(c.sources).toHaveLength(1);
    expect(c.velvetPluginsPath).toBe("/tmp/plugins.json");
    expect(c.argosUrl).toBe("http://argos:9999");
    expect(c.argosIndex).toBe("test-index");
  });
});