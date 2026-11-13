import { describe, expect, test } from "bun:test";
import {
  qualityTierOf,
  languageOf,
  normalizeTitle,
  yearOf,
  mergeTitles,
  TmdbIdCache,
  resolveTmdbId,
} from "../src/metadata.ts";
import { recordHealth } from "../src/health.ts";

describe("metadata enrichment lane", () => {
  test("qualityTierOf ranks 4K/DV/HDR above 1080p", () => {
    expect(qualityTierOf("Dune.2021.2160p.DV.HDR10")).toBe("4k");
    expect(qualityTierOf("|FR-4K| Dune 2021")).toBe("4k");
    expect(qualityTierOf("Dune (2021)[1080p]")).toBe("1080p");
    expect(qualityTierOf("Dune 720p")).toBe("720p");
    expect(qualityTierOf("Dune 2021")).toBe("unknown");
  });

  test("languageOf extracts |XX| tags", () => {
    expect(languageOf("|ES| ES - Dune")).toBe("ES");
    expect(languageOf("|TR-DUB| Dune")).toBe("TR-DUB");
    expect(languageOf("Dune 2021")).toBeUndefined();
  });

  test("normalizeTitle strips tags for dedupe", () => {
    expect(normalizeTitle("|FR-4K| Dune.2021.Multi")).toBe("dune multi");
    expect(normalizeTitle("Dune (2021)[EN-SUB]")).toBe("dune");
  });

  test("yearOf extracts year", () => {
    expect(yearOf("Dune (2021)")).toBe(2021);
    expect(yearOf("Dune Part Two - 2024")).toBe(2024);
  });

  test("mergeTitles dedupes clones and picks best tier", () => {
    const rows = [
      { name: "Dune (2021)[SD]", stream_id: "1", sourceId: "s1" },
      { name: "|FR-4K| Dune 2021", stream_id: "2", sourceId: "s1" },
      { name: "Dune (2021)[1080p]", stream_id: "3", sourceId: "s2" },
    ];
    const merged = mergeTitles(rows);
    expect(merged).toHaveLength(1);
    expect(merged[0].qualityTier).toBe("4k");
    expect(merged[0].clones).toHaveLength(3);
  });

  test("mergeTitles ranks live upstream above dead one at same tier", () => {
    recordHealth("live-src", { status: "ok", channels: 10, at: 1 });
    recordHealth("dead-src", { status: "auth-failed", keys: ["user_info"], at: 1 });
    const rows = [
      { name: "Dune (2021)[1080p]", stream_id: "1", sourceId: "dead-src" },
      { name: "Dune (2021)[1080p]", stream_id: "2", sourceId: "live-src" },
    ];
    const merged = mergeTitles(rows);
    expect(merged[0].clones[0].sourceId).toBe("live-src");
  });

  test("mergeTitles prefers preferred language at same tier", () => {
    const rows = [
      { name: "|ES| Dune (2021)", stream_id: "1", sourceId: "s1" },
      { name: "Dune (2021)", stream_id: "2", sourceId: "s2" },
    ];
    const merged = mergeTitles(rows, "ES");
    expect(merged[0].language).toBe("ES");
  });

  test("TmdbIdCache evicts oldest", () => {
    const c = new TmdbIdCache(2);
    c.set("a", 2021, 1);
    c.set("b", 2022, 2);
    c.set("c", 2023, 3);
    expect(c.get("a", 2021)).toBeUndefined();
    expect(c.get("c", 2023)).toBe(3);
  });

  test("resolveTmdbId fails open on bad key/network", async () => {
    const c = new TmdbIdCache();
    const id = await resolveTmdbId("this title does not exist anywhere", 1999, c, "invalid-key");
    expect(id).toBeUndefined();
  });
});