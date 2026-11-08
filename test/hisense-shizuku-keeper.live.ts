// LIVE contract test: hits the real Hisense TV over adb. Server should be
// alive (keeper-managed). Run: bun test test/hisense-shizuku-keeper.live.ts
import { describe, test, expect } from "bun:test";
import { resolveApkPath, serverAlive, ensureServer, liveDeps } from "../src/hisense-shizuku-keeper";

const deps = liveDeps();

describe("hisense-shizuku-keeper LIVE", () => {
  test("TV adb target is connected", async () => {
    const r = await deps.adb(["-s", deps.tv, "shell", "echo ok"]);
    expect(r.code).toBe(0);
    expect(r.out.trim()).toBe("ok");
  });

  test("Shizuku package is installed and resolvable", async () => {
    const apk = await resolveApkPath(deps);
    expect(apk).toContain("moe.shizuku.privileged.api");
    expect(apk).toMatch(/base\.apk$/);
  });

  test("shizuku_server is alive on the TV", async () => {
    expect(await serverAlive(deps)).toBe(true);
  });

  test("ensureServer reports running (no restart spam)", async () => {
    expect(await ensureServer(deps)).toBe("running");
  });
});
