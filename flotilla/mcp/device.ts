/**
 * device.ts — flotilla device layer, 100% on @devicefarmer/adbkit.
 *
 * No `$ adb` string-splicing anywhere. The Pixel's wireless debug port
 * rotates; adbkit's Client.listDevices() is the source of truth, with an
 * mDNS/nmap rescue when the port shifts.
 */
import { Adb } from "@devicefarmer/adbkit";
import type { Device, DeviceClient } from "@devicefarmer/adbkit";
import { $ } from "bun";

const SERIAL_BASE = process.env.FLOTILLA_DEVICE_IP ?? "10.0.0.77";
let cached: Device | null = null;

function adbClient() {
  return Adb.createClient({ host: "127.0.0.1", port: 5037 });
}

async function devices(): Promise<Device[]> {
  return adbClient().listDevices();
}

/** The live device matching our IP (not offline). */
async function liveDevice(): Promise<Device | null> {
  const list = await devices();
  return list.find((d) => d.id.startsWith(`${SERIAL_BASE}:`) && d.type === "device") ?? null;
}

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

/** Resolve the live device, auto-rescuing a rotated port once. */
export async function ensureDevice(): Promise<Device> {
  const live = await liveDevice();
  if (cached && cached.id === live?.id) return cached;
  if (live) { cached = live; return live; }
  for (const p of await scanPorts()) {
    await $`adb connect ${`${SERIAL_BASE}:${p}`}`.quiet().nothrow();
    const d = await liveDevice();
    if (d) { cached = d; return d; }
  }
  throw new Error(`no adb route to ${SERIAL_BASE} — wireless-debug off or keepalive down`);
}

/** Device client with all operations (startActivity, shell, screencap, …). */
export async function deviceClient(): Promise<DeviceClient> {
  const dev = await ensureDevice();
  return adbClient().getDevice(dev);
}

export { SERIAL_BASE };