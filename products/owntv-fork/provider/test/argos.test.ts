import { describe, expect, test } from "bun:test";
import { toProviderResults, type ArgosHit } from "../src/argos.ts";

describe("toProviderResults", () => {
  test("maps channel hits to stream results", () => {
    const hits: ArgosHit[] = [
      { index: "test", id: "1", score: 0.9, path: "channels/cnn", name: "CNN" },
      { index: "test", id: "2", score: 0.8, path: "channels/espn", name: "ESPN" },
    ];
    const r = toProviderResults(hits);
    expect(r.channels).toHaveLength(2);
    expect(r.channels[0].name).toBe("CNN");
    expect(r.channels[0].stream_id).toBe(900000);
    expect(r.raw).toHaveLength(2);
  });

  test("maps series/movie hits to series results", () => {
    const hits: ArgosHit[] = [
      { index: "test", id: "1", score: 0.7, path: "series/breaking-bad", name: "Breaking Bad" },
    ];
    const r = toProviderResults(hits);
    expect(r.series).toHaveLength(1);
    expect(r.series[0].name).toBe("Breaking Bad");
    expect(r.series[0].series_id).toBe("argos-0");
  });

  test("returns empty results for no hits", () => {
    const r = toProviderResults([]);
    expect(r.channels).toHaveLength(0);
    expect(r.series).toHaveLength(0);
    expect(r.movies).toHaveLength(0);
    expect(r.programmes).toHaveLength(0);
  });

  test("caps channels at 12", () => {
    const hits: ArgosHit[] = Array.from({ length: 20 }, (_, i) => ({
      index: "test", id: String(i), score: 1 - i / 100, path: `channels/c${i}`, name: `C${i}`,
    }));
    const r = toProviderResults(hits);
    expect(r.channels.length).toBeLessThanOrEqual(12);
  });
});