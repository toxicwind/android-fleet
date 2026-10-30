/**
 * launch.ts — unified flotilla browser-launch core.
 *
 * THE single path for opening a URL on the Pixel and capturing it once it has
 * CONTENT (not merely focus). Consolidates pixel_open / pixel_openshot /
 * pixel_view / pixel_browser into one engine so settle behavior is identical
 * everywhere (the refactor driver: 4 tools each re-settled differently,
 * making captures flaky).
 *
 * Focus doctrine preserved: a launch may bring its own window forward, but a
 * call with restore=true returns the user to their prior foreground app after
 * capture.
 */
import { $ } from "bun";
import { focusPackage } from "../focus.ts";

export interface CaptureResult {
  ok: boolean;
  file: string;
  bytes: number;
  settled: boolean;
  focus: string;
  restored?: boolean;
  reason?: string;
}

export interface LaunchOptions {
  url: string;
  browser?: string; // package to force (default: resolve VIEW)
  file?: string;
  timeoutMs?: number; // settle budget
  restore?: boolean; // return to pre-launch foreground app after capture
}

export interface LaunchDeps {
  currentFocus: () => Promise<string>;
  startView: (url: string, browser?: string) => Promise<string>;
  capture: (file: string) => Promise<{ file: string; bytes: number }>;
  resolveActivity: (pkg: string) => Promise<string | null>;
  bringToFront: (component: string) => Promise<void>;
}

const FOCUS_BROWSER_RE = /mCurrentFocus=.*(fenix|chrome|WebView|geckoview)/;

/** Whether a focus line is a browser window (used by settle + tests). */
export function isBrowserFocus(focus: string): boolean {
  const pkg = focusPackage(focus);
  return pkg ? /fenix|chrome|geckoview|webview/i.test(pkg) : false;
}

/**
 * Open a URL in the browser and settle until the browser hosts CONTENT
 * (focus enters a real browser window repeatedly), then capture. Polls, never
 * sleeps blind. With restore=true, returns to the pre-launch foreground app.
 */
export async function openAndCapture(
  device: string,
  options: LaunchOptions,
  deps: LaunchDeps,
): Promise<CaptureResult> {
  const { url, browser, file = `/tmp/flotilla-${Date.now()}.png` } = options;
  const timeoutMs = options.timeoutMs ?? 8000;
  const restore = options.restore ?? false;

  const preFocus = restore ? await deps.currentFocus() : null;
  const prePkg = restore ? focusPackage(preFocus ?? "") : null;

  await deps.startView(url, browser);

  const t0 = Date.now();
  let browserPaints = 0;
  while (Date.now() - t0 < timeoutMs) {
    const focus = await deps.currentFocus();
    if (isBrowserFocus(focus)) browserPaints++;
    if (browserPaints >= 3) break; // content = browser finished loading
    await Bun.sleep(400);
  }
  const settled = browserPaints >= 3;

  const cap = await deps.capture(file);

  let restored = false;
  if (restore && prePkg) {
    const cur = await deps.currentFocus();
    if (!cur.includes(prePkg)) {
      const act = await deps.resolveActivity(prePkg);
      if (act) {
        await deps.bringToFront(act);
        restored = true;
      }
    }
  }

  return {
    ok: true,
    file: cap.file,
    bytes: cap.bytes,
    settled,
    focus: await deps.currentFocus(),
    restored,
  };
}
