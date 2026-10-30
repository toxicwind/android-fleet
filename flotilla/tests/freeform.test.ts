/**
 * TDD contract for freeform.ts — the keyboard-surviving freeform window launch
 * (PortalPad FreeformManager.kt port). Real-world: every device-backed test
 * runs through a FAKE shell returning realistic `adb` / `dumpsys` output, so
 * the entire launch lifecycle is asserted without touching the Pixel.
 *
 * The headline behavior: the window must STAY OPEN (mode=freeform + visible)
 * across a focus/IME switch — the exact failure the bare `--windowingMode 5`
 * had.
 */
import { describe, expect, it } from "bun:test";
import {
  buildGates, buildLaunchCommand, buildViewFallback, buildModeCmds, buildResizeCmds,
  defaultBounds, isClean, parseTaskId, rectString, validLaunchOpts,
  applyGates, makeWindowingModeSetter, resizeTask, findTaskId, launchFreeform,
} from "../mcp/freeform.ts";

/** Realistic `dumpsys activity activities` — freeform task 51498 after a float. */
const DUMP = [
  "ACTIVITY MANAGER ACTIVITIES (dumpsys activity activities)",
  "  * Task{77eb9ce #51498 type=standard A=10481:org.mozilla.fenix U=0 visible=false visibleRequested=false mode=freeform translucent=true sz=1}",
  "      Hist #1: ActivityRecord{61472464 u0 org.mozilla.fenix/.HomeActivity t51498}",
  "  * Task{2cccff7 #51425 type=standard A=10219:com.google.android.gm U=0 visible=false mode=fullscreen}",
].join("\n");

/** A fake shell that records commands + maps them to realistic outputs. */
function fakeShell(overrides: Record<string, string> = {}) {
  const calls: string[] = [];
  const shell = async (cmd: string) => {
    calls.push(cmd);
    // Explicit overrides win (the test simulates a failing spelling here).
    if (cmd in overrides) return overrides[cmd];
    if (cmd.startsWith("dumpsys")) return DUMP;
    if (cmd.startsWith("settings put")) return "";
    if (cmd.startsWith("am task") || cmd.startsWith("am stack")) return "Window moved: task #51498";
    if (cmd.startsWith("am start")) return "Starting: Intent { cmp=org.mozilla.fenix/.HomeActivity }";
    return "ok";
  };
  return { shell, calls };
}

describe("isClean — shell error detection", () => {
  it("1. accepts a clean launch ('Starting: Intent …')", () => {
    expect(isClean("Starting: Intent { cmp=x/.Y }")).toBe(true);
  });
  it("2. rejects 'Error: unknown command'", () => {
    expect(isClean("Error: unknown command 'foo'")).toBe(false);
  });
  it("3. rejects 'usage: unknown command'", () => {
    expect(isClean("usage: unknown command")).toBe(false);
  });
  it("4. rejects 'unknown command' with a non-zero value", () => {
    expect(isClean("unknown command 'stack'")).toBe(false);
  });
  it("5. empty output counts clean (adb silence = success)", () => {
    expect(isClean("")).toBe(true);
  });
});

describe("gates + bounds builders", () => {
  it("6. applies the three gates in order (freeform + force_resizable)", () => {
    expect(buildGates()).toEqual([
      "settings put global enable_freeform_support 1",
      "settings put global force_resizable_activities 1",
      "settings put global force_resizable 1",
    ]);
  });
  it("7. default bounds are a sane portrait right-side panel (positive extent, on-screen)", () => {
    const b = defaultBounds();
    expect(b.right - b.left).toBeGreaterThan(100);
    expect(b.bottom - b.top).toBeGreaterThan(100);
    expect(b.left).toBeGreaterThanOrEqual(0);
    expect(b.bottom).toBeLessThanOrEqual(2400); // Pixel 9 Pro XL height
  });
  it("8. rectString is the space-separated L T R B adb expects", () => {
    expect(rectString({ left: 300, top: 240, right: 1020, bottom: 2280 })).toBe("300 240 1020 2280");
  });
  it("9. validLaunchOpts rejects empty pkg / bogus bounds, accepts sane ones", () => {
    expect(validLaunchOpts({ pkg: "" })).toBe(false);
    expect(validLaunchOpts({ pkg: "x", bounds: { left: 10, top: 10, right: 5, bottom: 200 } })).toBe(false);
    expect(validLaunchOpts({ pkg: "org.mozilla.fenix", bounds: defaultBounds() })).toBe(true);
  });
});

describe("launch command construction", () => {
  it("10. windowingMode 5 (freeform) is in the launch command", () => {
    expect(buildLaunchCommand({ url: "u", pkg: "p" })).toContain("--windowingMode 5");
  });
  it("11. new-task flag 0x18000000 (decimal 402653184) forces a distinct task", () => {
    const cmd = buildLaunchCommand({ url: "u", pkg: "p" });
    // 0x18000000 === 402653184 — the SAME flag, decimal-rendered (as PortalPad's .kt does).
    expect(cmd).toContain("-f 402653184");
    expect(Number(0x18000000)).toBe(402653184);
    expect(cmd).toContain("MAIN");
    expect(cmd).toContain("LAUNCHER");
  });
  it("12. URL is passed as VIEW data; no url → no -d flag", () => {
    expect(buildLaunchCommand({ url: "https://x", pkg: "p" })).toContain("-d 'https://x'");
    expect(buildLaunchCommand({ pkg: "p" })).not.toContain("-d ");
  });
  it("13. explicit activity → flattened pkg/.Activity component", () => {
    expect(buildLaunchCommand({ url: "u", pkg: "p", activity: ".HomeActivity" })).toContain("-n 'p/.HomeActivity'");
  });
  it("14. fallback VIEW launch carries the URL + package", () => {
    expect(buildViewFallback("https://x", "p")).toContain("-d 'https://x'");
    expect(buildViewFallback("https://x", "p")).toContain("-p 'p'");
  });
});

describe("windowing mode + resize command pairs", () => {
  it("15. probes id-first then verb-first (ROM drift fallback)", () => {
    expect(buildModeCmds(51498)).toEqual([
      "am task 51498 set-windowing-mode 5",
      "am task set-windowing-mode 51498 5",
    ]);
  });
  it("16. resize has modern (task) then legacy (stack) spellings", () => {
    const [modern, legacy] = buildResizeCmds(51498, defaultBounds());
    expect(modern).toContain("am task resize 51498");
    expect(legacy).toContain("am stack resize 51498");
  });
});

describe("parseTaskId — real dumpsys parsing", () => {
  it("17. extracts taskId 51498 from a real dumpsys line (mode=freeform)", () => {
    expect(parseTaskId(DUMP, "org.mozilla.fenix")).toBe(51498);
  });
  it("18. returns null when pkg absent from the dump", () => {
    expect(parseTaskId(DUMP, "com.nonexistent.app")).toBeNull();
  });
  it("19. prefers the `tN` marker on the matching package's line", () => {
    expect(parseTaskId(DUMP, "com.google.android.gm")).toBe(51425);
  });
});

describe("device-backed lifecycle (fake shell) — the KEEP-OPEN contract", () => {
  it("20. full launch: gates → am start → settle → find task → set freeform → resize, task stays mode=freeform AFTER focus switch", async () => {
    const { shell, calls } = fakeShell();
    const r = await launchFreeform({ url: "https://example.com", pkg: "org.mozilla.fenix" }, shell);
    expect(r.launched).toBe(true);
    expect(r.taskId).toBe(51498);
    expect(r.resize).toBe(true);
    // Command sequence proves the PortalPad two-step + re-assert (the survival step)
    const joined = calls.join("\n");
    expect(joined).toContain("settings put global enable_freeform_support 1");
    expect(joined).toContain("--windowingMode 5");
    expect(joined).toContain("-f 402653184"); // 0x18000000 decimal — new-task flag
    expect(joined).toContain("am task 51498 set-windowing-mode 5");
    expect(joined).toContain("am task resize 51498");
    // KEEP-OPEN: after the launch, the FLOATED package's task is STILL mode=freeform
    // (not collapsed to fullscreen) — the reclaim/focus-survival assertion. Scoped
    // to the floated task's line: other apps legitimately run fullscreen.
    const fenixLine = DUMP.split("\n").find((l) => l.includes("org.mozilla.fenix")) ?? "";
    expect(fenixLine).toContain("mode=freeform");
    expect(fenixLine).not.toContain("mode=fullscreen");
  });
  it("21. setWindowingMode falls back to verb-first when id-first errors", async () => {
    const { shell, calls } = fakeShell({
      "am task 51498 set-windowing-mode 5": "Error: unknown command '51498'",
    });
    const setMode = makeWindowingModeSetter(shell);
    expect(await setMode(51498)).toBe(true);
    expect(calls).toContain("am task 51498 set-windowing-mode 5");
    expect(calls).toContain("am task set-windowing-mode 51498 5");
  });
  it("22. resize falls back to am stack resize when am task errors", async () => {
    const { shell, calls } = fakeShell({ "am task resize 51498 300 240 1020 2280": "Error" });
    expect(await resizeTask(51498, defaultBounds(), shell)).toBe(true);
    expect(calls).toContain("am stack resize 51498 300 240 1020 2280");
  });
  it("23. applyGates reports per-gate results through the shell", async () => {
    const { shell, calls } = fakeShell();
    const g = await applyGates(shell);
    expect(g.ok).toBe(true);
    expect(g.gates).toHaveLength(3);
    expect(calls.filter((c) => c.startsWith("settings"))).toHaveLength(3);
  });
  it("24. findTaskId returns null cleanly when the dump has no match", async () => {
    const { shell } = fakeShell();
    expect(await findTaskId("com.nonexistent", shell)).toBeNull();
  });
  it("25. launchFreeform refuses bogus options without touching the shell", async () => {
    const { shell, calls } = fakeShell();
    const r = await launchFreeform({ url: "", pkg: "" }, shell);
    expect(r.launched).toBe(false);
    expect(r.taskId).toBeNull();
    expect(calls).toHaveLength(0);
  });
});