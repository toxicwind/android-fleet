/**
 * freeform.ts — keyboard-surviving freeform window launch (ported from
 * PortalPad's FreeformManager.kt, which solved "freeform window dies when the
 * IME/focused app reclaims the screen" on Android 15+).
 *
 * Why the bare `am start --windowingMode 5` fails on the Pixel: a translucent,
 * non-resizable activity launched this way can drop its `visibleRequested`
 * when the focused app + keyboard takes the foreground — the windowing mode
 * is never re-asserted, so the float dies. PortalPad's fix is a two-step:
 *   1. Gate: `force_resizable_activities 1` (apps that declare
 *      resizeableActivity=false otherwise silently launch fullscreen) +
 *      `enable_freeform_support 1`.
 *   2. Launch with FLAG_ACTIVITY_NEW_TASK|MULTIPLE_TASK (0x18000000) so a
 *      distinct task exists, settle, then RE-APPLY
 *      `am task <id> set-windowing-mode 5` on the concrete task id and
 *      `am task resize` to our bounds. Re-asserting the mode by task id is the
 *      keyboard-survival step — the windowing mode is pinned on the task, not
 *      just requested at launch.
 *
 * Testability: every command builder + parser is a pure export asserted by
 * tests/freeform.test.ts. The device-backed orchestrators accept an injected
 * `shell` (default = real adb shellText) so unit tests run with a fake shell.
 */

import { shellText } from "./device.ts";

const FREEORM_MODE = 5;
const NEW_TASK_MULTIPLE = 0x18000000; // FLAG_ACTIVITY_NEW_TASK | MULTIPLE_TASK
const SETTLE_MS = 700;

export interface FreeformBounds { left: number; top: number; right: number; bottom: number; }

/** Default float bounds — a right-side panel, portrait Pixel 9 Pro XL. */
export function defaultBounds(): FreeformBounds {
  return { left: 300, top: 240, right: 1020, bottom: 2280 };
}

/** A shell command runner — injected in tests (fake), real in prod. */
export type Shell = (cmd: string) => Promise<string>;

/** True when shell output signals success (no Error/Unknown/unknown command). */
export function isClean(out: string): boolean {
  return !/[Ee]rror|Unknown|unknown command/.test(out);
}

/** Idempotent freeform + resizable gates, in application order. */
export function buildGates(): string[] {
  return [
    "settings put global enable_freeform_support 1",
    "settings put global force_resizable_activities 1",
    "settings put global force_resizable 1",
  ];
}

/** Space-separated rect string for `am ... resize`. */
export function rectString(b: FreeformBounds): string {
  return `${b.left} ${b.top} ${b.right} ${b.bottom}`;
}

/** Build the freeform AM start command (new-task flag + windowingMode 5). */
export function buildLaunchCommand(opts: {
  url?: string; pkg: string; activity?: string; displayId?: number;
}): string {
  const { url, pkg, activity, displayId = 0 } = opts;
  const component = activity ? `${pkg}/${activity}` : pkg;
  const dataFlag = url ? `-d '${url}'` : "";
  return `am start --display ${displayId} --windowingMode ${FREEORM_MODE} -f ${NEW_TASK_MULTIPLE} ` +
    `-a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n '${component}' ${dataFlag}`.trim();
}

/** Fallback plain VIEW launch (used if the freeform launch errors). */
export function buildViewFallback(url: string, pkg: string): string {
  const dataFlag = url ? `-d '${url}'` : "";
  return `am start -a android.intent.action.VIEW ${dataFlag} -p '${pkg}'`.trim();
}

/** The two `am task` windowing-mode spellings, in probe order (id-first, verb-first). */
export function buildModeCmds(taskId: number, mode: number = FREEORM_MODE): [string, string] {
  return [`am task ${taskId} set-windowing-mode ${mode}`, `am task set-windowing-mode ${taskId} ${mode}`];
}

/** Resize commands: modern first, legacy `am stack resize` fallback. */
export function buildResizeCmds(taskId: number, b: FreeformBounds): [string, string] {
  const rect = rectString(b);
  return [`am task resize ${taskId} ${rect}`, `am stack resize ${taskId} ${rect}`];
}

/**
 * Parse a task id for `pkg` out of a `dumpsys activity activities` snapshot.
 * Pure + deterministic. Prefers `tN` on a pkg line, falls back to a nearby
 * `taskId=N`.
 */
export function parseTaskId(dumpsys: string, pkg: string): number | null {
  for (const line of dumpsys.split("\n")) {
    if (!line.includes(pkg)) continue;
    // 1) `t<id>` on a Hist/ActivityRecord line, 2) `task id=t?<id>`, 3) `#<id>` on
    //    the Task line: `Task{<hex> #<id> … A=<uid>:<pkg> …}` — the `#<id>` is the id.
    const m = line.match(/\bt(\d+)\b/) ?? line.match(/task id=t?(\d+)/) ?? line.match(/Task\{[0-9a-f]+\s+#(\d+)/);
    if (m) {
      const id = Number(m[1]);
      if (Number.isInteger(id) && id > 0) return id;
    }
  }
  const m = dumpsys.match(new RegExp(`[\\s\\S]{0,40}${pkg.replace(/\./g, "\\.")}[\\s\\S]{0,60}taskId=(\\d+)`));
  return m ? Number(m[1]) : null;
}

/** Validate the launch opts are sane (bounds have positive extent, pkg non-empty). */
export function validLaunchOpts(o: { pkg: string; bounds?: FreeformBounds }): boolean {
  if (!o.pkg || o.pkg.trim().length === 0) return false;
  if (!o.bounds) return true;
  const b = o.bounds;
  return b.right > b.left && b.bottom > b.top && b.left >= 0 && b.top >= 0;
}

// ── Device-backed orchestrators (shell injectable) ──────────────────────

/** Apply gates. Returns per-command results. */
export async function applyGates(shell: Shell = shellText): Promise<{ ok: boolean; gates: string[] }> {
  const gates: string[] = [];
  for (const c of buildGates()) {
    const out = await shell(c);
    gates.push(`${c} -> ${out.trim() || "ok"}`);
  }
  return { ok: true, gates };
}

/**
 * Set a task to freeform, probing both spellings. Caches which spelling this
 * ROM accepts. Returns true only when one executed cleanly.
 */
export function makeWindowingModeSetter(shell: Shell = shellText) {
  let verbStyle = 0; // 0 undetermined, 1 id-first, 2 verb-first, 3 neither
  return async function setWindowingMode(taskId: number, mode: number = FREEORM_MODE): Promise<boolean> {
    const [idFirst, verbFirst] = buildModeCmds(taskId, mode);
    if (verbStyle === 0 || verbStyle === 1) {
      const a = await shell(idFirst);
      if (isClean(a)) { verbStyle = 1; return true; }
      if (verbStyle === 1) return false;
    }
    if (verbStyle === 0 || verbStyle === 2) {
      const b = await shell(verbFirst);
      if (isClean(b)) { verbStyle = 2; return true; }
      if (verbStyle === 0) verbStyle = 3;
    }
    return false;
  };
}

/** Resize a task to bounds (modern, then legacy stack fallback). */
export async function resizeTask(taskId: number, b: FreeformBounds, shell: Shell = shellText): Promise<boolean> {
  const [modern, legacy] = buildResizeCmds(taskId, b);
  const a = await shell(modern);
  if (isClean(a)) return true;
  const s = await shell(legacy);
  return isClean(s);
}

/** Find a task id by package from a fresh dumpsys. */
export async function findTaskId(pkg: string, shell: Shell = shellText): Promise<number | null> {
  const out = await shell("dumpsys activity activities");
  return parseTaskId(out, pkg);
}

/**
 * Launch a URL/app into a freeform window that SURVIVES the keyboard.
 * Returns the task id (for bounds/focus control) or null.
 */
export async function launchFreeform(
  opts: { url: string; pkg: string; activity?: string; bounds?: FreeformBounds; displayId?: number },
  shell: Shell = shellText,
): Promise<{ taskId: number | null; launched: boolean; gates: string[]; resize?: boolean }> {
  const { url, pkg, activity, bounds = defaultBounds(), displayId = 0 } = opts;
  if (!validLaunchOpts({ pkg, bounds })) return { taskId: null, launched: false, gates: [], resize: false };

  const gates = (await applyGates(shell)).gates;

  const am = buildLaunchCommand({ url, pkg, activity, displayId });
  const out = await shell(am);

  if (!isClean(out) && !out.toLowerCase().includes("starting")) {
    await shell(buildViewFallback(url, pkg));
  }

  await Bun.sleep(SETTLE_MS);

  const taskId = await findTaskId(pkg, shell);
  let resized = false;
  if (taskId !== null) {
    const setMode = makeWindowingModeSetter(shell);
    await setMode(taskId); // THE keyboard-survival step
    resized = await resizeTask(taskId, bounds, shell);
  }

  return { taskId, launched: true, gates, resize: resized };
}

export const freeformVersion = "2.2.0-portalpad";