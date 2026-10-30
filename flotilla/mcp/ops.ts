/**
 * ops.ts — flotilla device operations on @devicefarmer/adbkit (v4-correct).
 *
 * adbkit v4: DeviceClient.shell() returns a Socket stream — read via the
 * socketText() helper. screencap()/startActivity() are native methods, not
 * shell strings.
 */
import { adbClient as deviceClient, shellText, ensureDevice } from "./device.ts";
import { isBrowserFocus } from "../focus.ts";

export interface Capture { file: string; bytes: number; }

/** Read the current focus window line. */
export async function currentFocus(): Promise<string> {
  try {
    const out = await shellText("dumpsys window 2>/dev/null | grep mCurrentFocus");
    return out.trim() || "unknown";
  } catch { return "unknown"; }
}

/** Screencap the device to a png on the box (adbkit native framebuffer-free route). */
export async function screencap(file: string): Promise<Capture> {
  const dc = await deviceClient();
  const stream = await dc.shell(["screencap", "-p"]);
  const buf = await new Response(stream as BodyInit).arrayBuffer();
  await Bun.write(file, new Uint8Array(buf));
  return { file, bytes: buf.byteLength };
}

/** Launch a URL via the VIEW intent. */
export async function openUrl(url: string, browser?: string): Promise<string> {
  const dc = await deviceClient();
  const opts = browser
    ? { wait: true, action: "android.intent.action.VIEW", data: url, package: browser }
    : { wait: true, action: "android.intent.action.VIEW", data: url };
  await dc.startActivity(opts);
  return "Starting: Intent";
}

/** Open a URL and capture once the browser owns the screen (settle-until-content). */
export async function openAndCapture(
  url: string,
  opts: { file?: string; browser?: string; timeoutMs?: number; restore?: boolean } = {},
): Promise<{ ok: boolean; file: string; bytes: number; settled: boolean; focus: string; restored?: boolean }> {
  const file = opts.file ?? `/tmp/flotilla-${Date.now()}.png`;
  const timeoutMs = opts.timeoutMs ?? 8000;
  const restore = opts.restore ?? false;

  const preFocus = restore ? await currentFocus() : null;
  const prePkg = restore ? /(?:\s)([A-Za-z0-9_.]+\/[A-Za-z0-9_.]+)/.exec(preFocus ?? "")?.[1]?.split("/")[0] ?? null : null;

  await openUrl(url, opts.browser);

  const t0 = Date.now();
  let paints = 0;
  while (Date.now() - t0 < timeoutMs) {
    const f = await currentFocus();
    if (isBrowserFocus(f)) paints++;
    if (paints >= 3) break;
    await Bun.sleep(400);
  }
  const settled = paints >= 3;
  const cap = await screencap(file);

  let restored = false;
  if (restore && prePkg) {
    const dc = await deviceClient();
    const cur = await currentFocus();
    if (!cur.includes(prePkg)) {
      const res = await dc.shell(["cmd", "package", "resolve-activity", "--brief", prePkg]);
      const act = (await shellText("cmd package resolve-activity --brief " + prePkg)).split("\n").map((l) => l.trim()).find((l) => l.startsWith(prePkg));
      if (act) await dc.startActivity({ wait: false, action: "android.intent.action.MAIN", data: undefined, package: prePkg, activity: act.replace(prePkg + "/", "") }).catch(() => null);
      restored = true;
    }
  }

  return { ok: true, file: cap.file, bytes: cap.bytes, settled, focus: await currentFocus(), restored };
}