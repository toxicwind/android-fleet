/**
 * device.ts — device resolution + low-level shell over @devicefarmer/adbkit.
 *
 * adbkit v4: DeviceClient.shell() returns a Node Socket stream — read it via
 * shellText() (chunk-by-chunk await). screencap()/startActivity() are native
 * DeviceClient methods.
 *
 * Contract: ensureDevice() -> live serial string; deviceClient() -> adbkit
 * DeviceClient bound to it; shellText() -> full stdout of a shell command.
 */

import { execFileSync } from "node:child_process";
import { Adb, type Client, type DeviceClient } from "@devicefarmer/adbkit";

const SERIAL_BASE = process.env.FLOTILLA_DEVICE ?? "10.0.0.77";

let cached: string | null = null;

function adbClient(): Client {
  return Adb.createClient({ host: "127.0.0.1", port: 5037 });
}

function serialize(): string {
  const text = execFileSync("adb", ["devices"], { encoding: "utf8" });
  return text
    .split("\n")
    .filter((l) => l && !l.startsWith("List of devices attached"))
    .map((l) => {
      const [serial, state] = l.split(/\t/).map((s) => s.trim());
      return serial && state ? `${serial}:${state}` : "";
    })
    .filter(Boolean)
    .join("\n");
}

type LiveEntry = { serial: string; state: string };
function liveSerials(): LiveEntry[] {
  const out: LiveEntry[] = [];
  for (const line of serialize().split("\n")) {
    const m = line.match(/^(.+?):(device|recovery|sideload|unauthorized|offline|bootloader)$/);
    if (m) out.push({ serial: m[1].replace(/:\d+$/, "") + (m[1].includes(":") ? ":" + m[1].split(":")[1] : ""), state: m[2] });
  }
  return out;
}

export async function resolveLive(): Promise<{ serial: string; adb: Client }> {
  const list = liveSerials();
  const live = list.find((d) => d.state === "device" && d.serial.startsWith(SERIAL_BASE.split(":")[0]));
  if (!live) throw new Error(`no adb route to ${SERIAL_BASE} — wireless-debug off or keepalive down`);
  return { serial: live.serial, adb: adbClient() };
}

export async function ensureDevice(): Promise<string> {
  const live = await resolveLive();
  cached = live.serial;
  return live.serial;
}

/** The live adbkit DeviceClient (has shell/screencap/startActivity). */
export async function deviceClient(): Promise<DeviceClient> {
  const live = await resolveLive();
  const dc = live.adb.getDevice(live.serial);
  if (!dc) throw new Error(`no device client for ${live.serial}`);
  return dc;
}

/** Full stdout of a shell command (adbkit returns a Socket stream). */
export async function shellText(cmd: string): Promise<string> {
  const live = await resolveLive();
  const dc = live.adb.getDevice(live.serial);
  return socketText(await dc.shell(cmd));
}

/** Read an adbkit stream fully to a string. */
export async function socketText(stream: unknown): Promise<string> {
  const chunks: Buffer[] = [];
  for await (const chunk of stream as AsyncIterable<Buffer>) {
    chunks.push(Buffer.isBuffer(chunk) ? chunk : Buffer.from(chunk));
  }
  return Buffer.concat(chunks).toString("utf8");
}

export { adbClient, liveSerials, SERIAL_BASE };