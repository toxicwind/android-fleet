/**
 * Device-capability negotiation — 4K/HEVC by device, not by catalog.
 *
 * The catalog has 687k channels across every codec/resolution. A 1080p-only
 * device shouldn't drown in 4K HEVC rows it can't play; a 4K HDR device
 * shouldn't hide them. OwnTV/the TV signals capability via a query param:
 *
 *   GET /player_api.php?...&action=get_live_streams&capabilities=h265=0,max_res=1080
 *
 * Semantics (fail-open — a missing param means "capable of everything"):
 *   h265=0        HEVC-named channels get transcode:1 (a hint, never dropped)
 *   max_res=1080  4K/2160 rows are ranked below 1080p but still present
 *
 * Resolution/codec are parsed from the channel NAME (the only signal Xtream
 * gives live rows). This mirrors the audio-language lane: name hints, never
 * a hard filter that hides content.
 */
export interface DeviceCapabilities {
  h265: boolean; // can decode HEVC
  maxRes: "2160" | "1080" | "720" | "sd";
}

export const DEFAULT_CAPS: DeviceCapabilities = { h265: true, maxRes: "2160" };

/** Parse the capabilities query param ("h265=0,max_res=1080" or "h265=1"). */
export function parseCapabilities(raw: string | null): DeviceCapabilities {
  if (!raw) return DEFAULT_CAPS;
  const caps: DeviceCapabilities = { ...DEFAULT_CAPS };
  for (const part of raw.split(",")) {
    const [k, v] = part.split("=");
    if (!k) continue;
    switch (k.trim().toLowerCase()) {
      case "h265":
      case "hevc":
        caps.h265 = v !== "0" && v?.toLowerCase() !== "false";
        break;
      case "max_res":
      case "maxres":
        if (v === "1080" || v === "720" || v === "sd" || v === "2160") caps.maxRes = v;
        break;
    }
  }
  return caps;
}

const RES_RANK: Record<DeviceCapabilities["maxRes"], number> = { "2160": 4, "1080": 3, "720": 2, sd: 1 };

/** Detect HEVC from a channel name (HEVC/H.265/x265/265 markers). */
export function isHevc(name: string): boolean {
  return /(HEVC|H\.?265|X265|265)/i.test(name);
}

/** Detect resolution from a channel name. */
export function resolutionOf(name: string): DeviceCapabilities["maxRes"] {
  if (/(2160|4K|UHD)/i.test(name)) return "2160";
  if (/(1080|FHD)/i.test(name)) return "1080";
  if (/(720|HDTV)/i.test(name)) return "720";
  if (/(SD|CAM|TS)/i.test(name)) return "sd";
  return "1080"; // unknown → assume HD-capable (the vast majority)
}

/**
 * Annotate a channel row for the requesting device. Returns a new object with
 * `transcode` set when the device can't play HEVC, and `capability_rank` so a
 * sorting client can prefer playable rows. Never drops rows.
 */
export function negotiateRow(
  row: Record<string, unknown>,
  caps: DeviceCapabilities,
): Record<string, unknown> {
  const name = String(row.name ?? "");
  const out = { ...row };
  const hevc = isHevc(name);
  const res = resolutionOf(name);
  if (hevc && !caps.h265) out.transcode = 1;
  out.capability_rank = (hevc && !caps.h265 ? 0 : 1) + (RES_RANK[res] <= RES_RANK[caps.maxRes] ? 1 : 0);
  return out;
}