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

/** Idempotent: turn on freeform support + force resizable activities. */
export async function enableFreeform(): Promise<{ ok: boolean; gates: string[] }> {
  const cmds = [
    "settings put global enable_freeform_support 1",
    "settings put global force_resizable_activities 1",
    "settings put global force_resizable 1",
  ];
  const gates: string[] = [];
  for (const c of cmds) {
    const out = await shellText(c);
    gates.push(`${c} -> ${out.trim() || "ok"}`);
  }
  return { ok: true, gates };
}

function clean(out: string): boolean {
  return !/[Ee]rror|Unknown|unknown command/.test(out);
}

/**
 * Set a task to freeform mode, probing both `am task` spellings (id-first vs
 * verb-first) — ROMs differ (One UI/Android 16 rejects id-first). Returns true
 * only when a spelling executed cleanly.
 */
let verbStyle = 0; // 0 undetermined, 1 id-first, 2 verb-first, 3 neither
export async function setWindowingMode(taskId: number, mode: number = FREEORM_MODE): Promise<boolean> {
  if (verbStyle === 0 || verbStyle === 1) {
    const a = await shellText(`am task ${taskId} set-windowing-mode ${mode}`);
    if (clean(a)) { verbStyle = 1; return true; }
    if (verbStyle === 1) return false;
  }
  if (verbStyle === 0 || verbStyle === 2) {
    const b = await shellText(`am task set-windowing-mode ${taskId} ${mode}`);
    if (clean(b)) { verbStyle = 2; return true; }
    if (verbStyle === 0) verbStyle = 3;
  }
  return false;
}

/** Resize a task to bounds (modern `am task resize`, legacy `am stack resize`). */
export async function resizeTask(taskId: number, b: FreeformBounds): Promise<boolean> {
  const rect = `${b.left} ${b.top} ${b.right} ${b.bottom}`;
  const a = await shellText(`am task resize ${taskId} ${rect}`);
  if (clean(a)) return true;
  const s = await shellText(`am stack resize ${taskId} ${rect}`);
  return clean(s);
}

/** Find a task id by package from the task list. */
export async function findTaskId(pkg: string): Promise<number | null> {
  const out = await shellText("dumpsys activity activities");
  // mResumedActivity / topResumedActivity lines carry the task id: `... t1234`
  for (const line of out.split("\n")) {
    if (!line.includes(pkg)) continue;
    const m = line.match(/\bt(\d+)\b/) ?? line.match(/task id=t?(\d+)/);
    if (m) {
      const id = Number(m[1]);
      if (Number.isInteger(id) && id > 0) return id;
    }
  }
  // fallback: latest started ActivityRecord for the pkg's stack
  const m = out.match(new RegExp(`[\\s\\S]{0,40}${pkg.replace(/\./g, "\\.")}[\\s\\S]{0,60}taskId=(\\d+)`));
  return m ? Number(m[1]) : null;
}

/**
 * Launch component/URL into a freeform window that SURVIVES the keyboard.
 *
 * component: "pkg/.Activity" flattened, or we derive from pkg + URL.
 * Returns the task id on success (for bounds/focus control) or null.
 */
export async function launchFreeform(
  opts: {
    url: string;
    pkg: string;
    activity?: string;
    bounds?: FreeformBounds;
    displayId?: number;
  },
): Promise<{ taskId: number | null; launched: boolean; gates: string[]; resize?: boolean }> {
  const { url, pkg, activity, bounds = defaultBounds(), displayId = 0 } = opts;

  await enableFreeform();

  // Build the component. For a browser/URL, use pkg/.Activity via MAIN/LAUNCHER
  // with the VIEW data so the app resolves the URL, but in a NEW distinct task
  // (0x18000000) + freeform mode.
  const component = activity ? `${pkg}/${activity}` : pkg;
  const dataFlag = url ? `-d '${url}'` : "";
  const am = `am start --display ${displayId} --windowingMode ${FREEORM_MODE} -f ${NEW_TASK_MULTIPLE} ` +
    `-a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n '${component}' ${dataFlag}`;
  const out = await shellText(am);

  const path = url ? url : pkg;
  if (clean(out) === false && !out.toLowerCase().includes("starting")) {
    // Fallback: plain launch (lands fullscreen), then move into freeform by id.
    await shellText(`am start -a android.intent.action.VIEW ${dataFlag} -p '${pkg}'`);
  }

  await Bun.sleep(SETTLE_MS);

  const taskId = await findTaskId(pkg);
  let resized = false;
  if (taskId !== null) {
    await setWindowingMode(taskId); // THE keyboard-survival step
    resized = await resizeTask(taskId, bounds);
  }

  return { taskId, launched: true, gates: [], resize: resized };
}

export const freeformVersion = "2.2.0-portalpad";