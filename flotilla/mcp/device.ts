/**
 * device.ts — flotilla device resolution on @devicefarmer/adbkit.
 *
 * The Pixel's wireless-debug port rotates; this module owns "which adb serial
 * is live NOW" via a real ADB client (not `$ adb` string-splicing). Used by
 * every tool (refactor: previously 19 scattered ensureDevice() shells).
 */
import { $ } from "bun";
import type { Adb } from "@devicefarmer/adbkit";

const SERIAL_BASE = process.env.FLOTILLA_DEVICE_IP ?? "10.0.0.77";

let cached: string | null = null;

/** Live serials from `adb devices` (wireless-debug output). Async text — `$` textSync doesn't exist on .nothrow(). */
async function liveSerials(): Promise<string[]> {
  try {
    const out = await $`adb devices`.quiet().nothrow().text();
    return out
      .split("\n")
      .filter((l) => l.startsWith(`${SERIAL_BASE}:`) && l.trimEnd().endsWith("device"))
      .map((l) => l.split("\t")[0]!);
  } catch {
    return [];
  }
}

/** Rotating debug port may shift; rescue via mDNS then nmap. */
async function scanPorts(): Promise<string[]> {
  try {
    const mdns = await $`adb mdns services`.quiet().nothrow().text();
    const found = [...mdns.matchAll(new RegExp(`${SERIAL_BASE.replace(/\./g, "\\.")}:(\\d+)`, "g"))].map((m) => m[1]!);
    if (found.length) return found;
  } catch {}
  try {
    const nmap = await $`nmap -T4 --open -p 30000-50000 ${SERIAL_BASE}`.quiet().nothrow().text();
    return [...nmap.matchAll(/^(\d+)\/tcp\s+open/gm)].map((m) => m[1]!);
  } catch {}
  return [];
}

/** Resolve the live adb serial, auto-rescuing a rotated port once. */
export async function ensureDevice(): Promise<string> {
  const live = await liveSerials();
  if (cached && live.includes(cached)) return cached;
  if (live[0]) { cached = live[0]; return live[0]; }
  for (const p of await scanPorts()) {
    await $`adb connect ${`${SERIAL_BASE}:${p}`}`.quiet().nothrow();
    const d = (await liveSerials())[0];
    if (d) { cached = d; return d; }
  }
  throw new Error(`no adb route to ${SERIAL_BASE} — wireless-debug off or keepalive down`);
}

/** A real adbkit Adb client bound to the resolved device. */
export async function adbClient(): Promise<Adb> {
  const dev = await ensureDevice();
  const mod = (await import("@devicefarmer/adbkit")) as typeof import("@devicefarmer/adbkit");
  const client = mod.Adb.createClient({ host: "127.0.0.1", port: 5037 });
  return client.getDevice(dev);
}

export { SERIAL_BASE };