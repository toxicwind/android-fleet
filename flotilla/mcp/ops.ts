/**
 * ops.ts — flotilla device operations on @devicefarmer/adbkit.
 *
 * Every primitive (focus read, screencap, shell, startActivity) goes through
 * adbkit — no `$ adb` shell-splicing. These are the atomic operations the MCP
 * tools wrap; settle logic stays here (shared by every browser tool).
 */
import { deviceClient } from "./device.ts";
import { isBrowserFocus } from "../focus.ts";

export interface Capture { file: string; bytes: number; }

/** Read the current focus window line. */
export async function currentFocus(): Promise<string> {
  try {
    const dc = await deviceClient();
    const out = await dc.shell("dumpsys window 2>/dev/null | grep mCurrentFocus");
    return out.toString().trim() || "unknown";
  } catch {
    return "unknown";
  }
}

/** Screencap the device to a png on the box. */
export async function screencap(file: string): Promise<Capture> {
  const dc = await deviceClient();
  // adbkit framebuffer doesn't write PNG; use screencap -> exec-out via shell
  const dev = await deviceClient();
  void dev;
  const buf = await dc.shell(["screencap", "-p"]);
  await Bun.write(file, new Uint8Array(buf));
  return { file, bytes: (await Bun.file(file).exists()) ? (await Bun.file(file).size) : 0 };
}

/** Launch a URL via the VIEW intent (browser from a fresh tab). */
export async function openUrl(url: string, browser?: string): Promise<string> {
  const dc = await deviceClient();
  const args = browser
    ? ["start", "-a", "android.intent.action.VIEW", "-d", url, "-p", browser]
    : ["start", "-a", "android.intent.action.VIEW", "-d", url];
  const out = await dc.shell(["am", ...args]);
  return out.toString().trim();
}

/**
 * Open a URL and capture once the browser owns the screen (settle-until-
 * content). With restore=true, returns to the pre-launch foreground app.
 */
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
      const act = res.toString().split("\n").map((l) => l.trim()).find((l) => l.startsWith(prePkg));
      if (act) await dc.startActivity({ wait: true, action: "android.intent.action.MAIN", data: undefined, package: prePkg, activity: act.replace(prePkg + "/", "") }).catch(() => null);
      restored = true;
    }
  }

  return { ok: true, file: cap.file, bytes: cap.bytes, settled, focus: await currentFocus(), restored };
}