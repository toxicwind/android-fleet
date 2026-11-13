import { describe, expect, test } from "bun:test";
import { mergeEpgClaims } from "../src/epg.ts";

const DAY = 24 * 60 * 60 * 1000;
const now = Date.UTC(2026, 10, 13, 12, 0, 0); // 2026-11-13 12:00 UTC
const horizon = now + 2 * DAY;

const fmt = (ts: number) => {
  const d = new Date(ts);
  const p = (n: number) => String(n).padStart(2, "0");
  return `${d.getUTCFullYear()}${p(d.getUTCMonth() + 1)}${p(d.getUTCDate())}${p(d.getUTCHours())}${p(d.getUTCMinutes())}${p(d.getUTCSeconds())} +0000`;
};

const p = (startOffsetMin: number, title: string, desc?: string) => ({
  start: fmt(now + startOffsetMin * 60_000),
  stop: fmt(now + (startOffsetMin + 60) * 60_000),
  title,
  description: desc,
});

describe("EPG quality merge", () => {
  test("merges multi-EPG claims for the same tvg-id", () => {
    const guides = new Map<string, Map<string, typeof p[]>>([
      ["s1", new Map([["bbc1", [p(0, "News at One", "dense feed"), p(60, "Weather")]]])],
      ["s2", new Map([["bbc1", [p(0, "News at One", "sparse feed"), p(120, "Film")]]])],
    ]);
    const merged = mergeEpgClaims(guides as any, "bbc1", now, horizon);
    // 3 unique programmes (p(0) deduped across feeds, denser desc wins)
    expect(merged).toHaveLength(3);
    const first = merged[0];
    expect(first.title).toBe("News at One");
    expect(first.description).toBe("dense feed");
  });

  test("dedupes identical (start,title) across feeds", () => {
    const guides = new Map<string, Map<string, typeof p[]>>([
      ["s1", new Map([["ch", [p(0, "Same Show"), p(60, "Other")]]])],
      ["s2", new Map([["ch", [p(0, "Same Show"), p(120, "More")]]])],
    ]);
    const merged = mergeEpgClaims(guides as any, "ch", now, horizon);
    expect(merged).toHaveLength(3);
  });

  test("returns empty for unknown epgId", () => {
    const guides = new Map<string, Map<string, typeof p[]>>([
      ["s1", new Map([["ch", [p(0, "X")]]])],
    ]);
    expect(mergeEpgClaims(guides as any, "nope", now, horizon)).toHaveLength(0);
    expect(mergeEpgClaims(guides as any, undefined, now, horizon)).toHaveLength(0);
  });

  test("filters programmes outside the lookahead window", () => {
    const guides = new Map<string, Map<string, typeof p[]>>([
      ["s1", new Map([["ch", [p(-5000, "Old"), p(0, "Now"), p(5000, "Far future")]]])],
    ]);
    const merged = mergeEpgClaims(guides as any, "ch", now, horizon);
    expect(merged.map((x) => x.title)).toEqual(["Now"]);
  });

  test("sorts merged output by start time", () => {
    const guides = new Map<string, Map<string, typeof p[]>>([
      ["s1", new Map([["ch", [p(120, "Later"), p(0, "First")]]])],
    ]);
    const merged = mergeEpgClaims(guides as any, "ch", now, horizon);
    expect(merged.map((x) => x.title)).toEqual(["First", "Later"]);
  });
});