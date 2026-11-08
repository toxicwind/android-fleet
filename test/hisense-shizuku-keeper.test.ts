import { describe, test, expect } from "bun:test";
import {
  resolveApkPath, serverAlive, ensureServer, isTvEvent, runKeeper,
  NETLINK_CMD,
  type Deps, type AdbResult,
} from "../src/hisense-shizuku-keeper";

const TV = "10.0.0.225:5555";
const APK = "/data/app/~~l3qd/moe.shizuku.privileged.api-juDG/base.apk";

function makeDeps(overrides: Partial<Deps> = {}): { deps: Deps; calls: string[]; logLines: any[] } {
  const calls: string[] = [];
  const logLines: any[] = [];
  const adb = async (args: string[]): Promise<AdbResult> => {
    calls.push(args.join(" "));
    const cmd = args.join(" ");
    if (cmd.includes("pm path")) return { code: 0, out: `package:${APK}` };
    if (cmd.includes("pidof")) return { code: 1, out: "" };
    if (cmd.includes("--apk=")) return { code: 0, out: "info: shizuku_server pid is 42\ninfo: shizuku_starter exit with 0" };
    return { code: 0, out: "" };
  };
  const deps: Deps = {
    adb,
    ipMonitor: async (_onEvent: (line: string) => void) => {},
    log: (evt: Record<string, unknown>) => logLines.push(evt),
    tv: TV,
    ...overrides,
  };
  return { deps, calls, logLines };
}

describe("resolveApkPath", () => {
  test("strips package: prefix", async () => {
    const { deps } = makeDeps();
    expect(await resolveApkPath(deps)).toBe(APK);
  });

  test("returns null when pm path yields nothing", async () => {
    const { deps } = makeDeps({ adb: async () => ({ code: 0, out: "" }) });
    expect(await resolveApkPath(deps)).toBeNull();
  });
});

describe("serverAlive", () => {
  test("true when pidof prints a pid", async () => {
    const { deps } = makeDeps({ adb: async (a: string[]) => a.join(" ").includes("pidof") ? { code: 0, out: "16610" } : { code: 0, out: "" } });
    expect(await serverAlive(deps)).toBe(true);
  });

  test("false when pidof exits non-zero", async () => {
    const { deps } = makeDeps({ adb: async (a: string[]) => a.join(" ").includes("pidof") ? { code: 1, out: "" } : { code: 0, out: "" } });
    expect(await serverAlive(deps)).toBe(false);
  });
});

describe("ensureServer", () => {
  test("starts server when dead: runs libshizuku.so --apk, logs server.started", async () => {
    const { deps, calls, logLines } = makeDeps();
    const result = await ensureServer(deps);
    expect(result).toBe("started");
    expect(calls.some(c => c.includes("/lib/arm/libshizuku.so") && c.includes(`--apk=${APK}`))).toBe(true);
    expect(logLines.some(l => l.event === "server.started")).toBe(true);
  });

  test("skips start when alive: no --apk call, logs server.running", async () => {
    const { deps, calls, logLines } = makeDeps({
      adb: async (a) => {
        const cmd = a.join(" ");
        if (cmd.includes("pm path")) return { code: 0, out: `package:${APK}` };
        if (cmd.includes("pidof")) return { code: 0, out: "16610" };
        return { code: 0, out: "" };
      },
    });
    const result = await ensureServer(deps);
    expect(result).toBe("running");
    expect(calls.some(c => c.includes("--apk="))).toBe(false);
    expect(logLines.some(l => l.event === "server.running")).toBe(true);
  });

  test("no-apk when package missing", async () => {
    const { deps } = makeDeps({ adb: async () => ({ code: 0, out: "" }) });
    expect(await ensureServer(deps)).toBe("no-apk");
  });

  test("start-failed when starter exits non-zero", async () => {
    const { deps } = makeDeps({
      adb: async (a: string[]) => {
        const cmd = a.join(" ");
        if (cmd.includes("--apk=")) return { code: 134, out: "Aborted" };
        if (cmd.includes("pm path")) return { code: 0, out: `package:${APK}` };
        return { code: 1, out: "" };
      },
    });
    expect(await ensureServer(deps)).toBe("start-failed");
  });
});

describe("isTvEvent", () => {
  test("netlink monitor uses the neigh object (iproute2 rejects neighbor)", () => {
    expect(NETLINK_CMD).toEqual(["/usr/bin/ip", "monitor", "neigh"]);
  });
  test("REACHABLE for the TV ip fires", () => {
    expect(isTvEvent("10.0.0.225 dev enp12s0 lladdr 38:64:... used 0 REACHABLE", "10.0.0.225")).toBe(true);
  });
  test("INCOMPLETE for the TV ip does not fire", () => {
    expect(isTvEvent("10.0.0.225 dev enp12s0  INCOMPLETE", "10.0.0.225")).toBe(false);
  });
  test("other hosts never fire", () => {
    expect(isTvEvent("10.0.0.1 dev enp12s0 lladdr aa:bb REACHABLE", "10.0.0.225")).toBe(false);
  });
  test("Deleted lines never fire", () => {
    expect(isTvEvent("Deleted 10.0.0.225 dev enp12s0  REACHABLE", "10.0.0.225")).toBe(false);
  });
});

describe("runKeeper", () => {
  test("initial pass runs ensureServer once, then blocks on ipMonitor", async () => {
    let monitorEntered = false;
    const { deps, logLines } = makeDeps({
      ipMonitor: async (onEvent) => { monitorEntered = true; onEvent("10.0.0.1 dev enp12s0 REACHABLE"); },
    });
    await runKeeper(deps);
    expect(monitorEntered).toBe(true);
    expect(logLines.filter(l => l.event === "keeper.start").length).toBe(1);
  });

  test("TV REACHABLE event triggers ensureServer again", async () => {
    let fire: (line: string) => void = () => {};
    const { deps, calls, logLines } = makeDeps({
      ipMonitor: async (onEvent: (line: string) => void) => {
        fire = onEvent;
        fire("10.0.0.225 dev enp12s0 lladdr 38:64:... REACHABLE");
      },
    });
    await runKeeper(deps);
    // initial pass + one event-triggered pass = two pidof probes
    expect(calls.filter(c => c.includes("pidof")).length).toBe(2);
    expect(logLines.some(l => l.event === "server.started" || l.event === "server.running")).toBe(true);
  });
});
