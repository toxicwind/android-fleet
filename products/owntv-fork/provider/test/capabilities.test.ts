import { describe, expect, test } from "bun:test";
import { parseCapabilities, isHevc, resolutionOf, negotiateRow, DEFAULT_CAPS } from "../src/capabilities.ts";

describe("device capability negotiation", () => {
  test("default caps = capable of everything (fail-open)", () => {
    expect(DEFAULT_CAPS).toEqual({ h265: true, maxRes: "2160" });
    expect(parseCapabilities(null)).toEqual(DEFAULT_CAPS);
  });

  test("parses h265=0 and max_res", () => {
    expect(parseCapabilities("h265=0,max_res=1080")).toEqual({ h265: false, maxRes: "1080" });
    expect(parseCapabilities("hevc=0")).toEqual({ h265: false, maxRes: "2160" });
    expect(parseCapabilities("h265=1,max_res=720")).toEqual({ h265: true, maxRes: "720" });
  });

  test("isHevc detects HEVC markers", () => {
    expect(isHevc("Channel HEVC")).toBe(true);
    expect(isHevc("Channel x265")).toBe(true);
    expect(isHevc("Channel H.265")).toBe(true);
    expect(isHevc("Channel 1080p")).toBe(false);
  });

  test("resolutionOf detects 4K/1080/720", () => {
    expect(resolutionOf("4K Channel")).toBe("2160");
    expect(resolutionOf("2160p UHD")).toBe("2160");
    expect(resolutionOf("FHD Channel")).toBe("1080");
    expect(resolutionOf("HDTV 720")).toBe("720");
    expect(resolutionOf("Plain")).toBe("1080"); // unknown → HD assumption
  });

  test("negotiateRow adds transcode hint for h265=0 devices", () => {
    const row = negotiateRow({ name: "Channel HEVC 4K", stream_id: 1 }, { h265: false, maxRes: "2160" });
    expect(row.transcode).toBe(1);
    expect(row.capability_rank).toBe(1); // 0 (hevc) + 1 (res ok)
  });

  test("negotiateRow never drops rows, ranks playable above unplayable", () => {
    const hevc = negotiateRow({ name: "Channel HEVC", stream_id: 1 }, { h265: false, maxRes: "2160" });
    const avc = negotiateRow({ name: "Channel AVC", stream_id: 2 }, { h265: false, maxRes: "2160" });
    expect(hevc.capability_rank).toBeLessThan(avc.capability_rank);
  });

  test("negotiateRow passes through unchanged for capable device", () => {
    const row = negotiateRow({ name: "Channel HEVC 4K", stream_id: 1 }, DEFAULT_CAPS);
    expect(row.transcode).toBeUndefined();
    expect(row.capability_rank).toBe(2);
  });
});