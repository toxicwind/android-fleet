// hisense-shizuku-keeper — keep the Shizuku server alive on the Hisense wuyishan TV.
//
// Contract (2026-11-08, verified live): the TV is a locked user build (Android 11,
// armeabi-v7a); the Shizuku server must be started via the bundled launcher
// `<apkdir>/lib/arm/libshizuku.so --apk=<base.apk>` (entry class
// rikka.shizuku.server.ShizukuService) and dies at every TV reboot. The manager
// app's BootCompleteReceiver cannot self-start it on this build.
//
// Event-driven, NO sleep-polling: block on `ip monitor neighbor` netlink events
// (fires the moment the TV re-associates after a boot) and run the start
// one-liner on each transition to REACHABLE. One initial ensure pass covers
// "TV already up, server dead".
//
// Logs: JSON lines with stable event keys (estate structured-log doctrine).

export interface AdbResult {
  code: number;
  out: string;
}

export interface Deps {
  adb(args: string[]): Promise<AdbResult>;
  /** Blocks forever; invokes onEvent for each netlink neighbor line. */
  ipMonitor(onEvent: (line: string) => void): Promise<void>;
  log(evt: Record<string, unknown>): void;
  /** adb target, e.g. "10.0.0.225:5555" */
  tv: string;
}

const PACKAGE = "moe.shizuku.privileged.api";
const SERVER_NAME = "shizuku_server";
/** iproute2 monitor object is `neigh` (NOT `neighbor` — rejects with "Argument unknown"). */
export const NETLINK_CMD = ["/usr/bin/ip", "monitor", "neigh"];

export function logLine(evt: Record<string, unknown>): void {
  console.log(JSON.stringify({ ts: new Date().toISOString(), ...evt }));
}

export async function resolveApkPath(deps: Deps): Promise<string | null> {
  const r = await deps.adb(["-s", deps.tv, "shell", `pm path ${PACKAGE}`]);
  const line = r.out.split("\n").map(l => l.trim()).find(l => l.startsWith("package:"));
  return line ? line.slice("package:".length) : null;
}

export async function serverAlive(deps: Deps): Promise<boolean> {
  const r = await deps.adb(["-s", deps.tv, "shell", `pidof ${SERVER_NAME}`]);
  return r.code === 0 && r.out.trim().length > 0;
}

export type EnsureResult = "running" | "started" | "no-apk" | "start-failed";

export async function ensureServer(deps: Deps): Promise<EnsureResult> {
  const apk = await resolveApkPath(deps);
  if (!apk) {
    deps.log({ event: "no.apk", tv: deps.tv, package: PACKAGE });
    return "no-apk";
  }
  if (await serverAlive(deps)) {
    deps.log({ event: "server.running", tv: deps.tv });
    return "running";
  }
  const lib = `${apk.replace(/\/base\.apk$/, "")}/lib/arm/libshizuku.so`;
  const start = await deps.adb(["-s", deps.tv, "shell", `${lib} --apk=${apk}`]);
  if (start.code !== 0 || !start.out.includes("shizuku_server pid is")) {
    deps.log({ event: "server.start_failed", tv: deps.tv, code: start.code, out: start.out.trim() });
    return "start-failed";
  }
  const pid = /shizuku_server pid is (\d+)/.exec(start.out)?.[1];
  deps.log({ event: "server.started", tv: deps.tv, pid });
  return "started";
}

/** Netlink neighbor monitor line that means "TV just became reachable". */
export function isTvEvent(line: string, tvIp: string): boolean {
  if (line.startsWith("Deleted")) return false;
  return line.includes(tvIp) && line.includes("REACHABLE");
}

export async function runKeeper(deps: Deps): Promise<void> {
  const tvIp = deps.tv.split(":")[0];
  deps.log({ event: "keeper.start", tv: deps.tv });
  await ensureServer(deps);
  await deps.ipMonitor((line) => {
    if (!isTvEvent(line, tvIp)) return;
    deps.log({ event: "tv.reachable", tv: deps.tv });
    ensureServer(deps).catch((e) =>
      deps.log({ event: "ensure.error", tv: deps.tv, error: String(e) }),
    );
  });
}

/** Live deps wired to the real adb + iproute2 (daemon entrypoint). */
export function liveDeps(): Deps {
  const ADB = "/usr/bin/adb";
  return {
    tv: "10.0.0.225:5555",
    adb: async (args) => {
      const proc = Bun.spawn([ADB, ...args], { stdout: "pipe", stderr: "pipe" });
      const out = await new Response(proc.stdout).text();
      const code = await proc.exited;
      return { code, out };
    },
    ipMonitor: (onEvent) =>
      new Promise<void>((resolve) => {
        const proc = Bun.spawn(NETLINK_CMD, {
          stdout: "pipe",
          stderr: "inherit",
        });
        const reader = proc.stdout.getReader();
        const dec = new TextDecoder();
        let buf = "";
        const pump = async () => {
          for (;;) {
            const { done, value } = await reader.read();
            if (done) {
              // netlink stream died — resolve so the daemon exits and the
              // supervisor's retry restarts it fresh (never hang handle-less).
              logLine({ event: "netlink.closed" });
              resolve();
              return;
            }
            buf += dec.decode(value, { stream: true });
            let nl: number;
            while ((nl = buf.indexOf("\n")) !== -1) {
              const line = buf.slice(0, nl).trim();
              buf = buf.slice(nl + 1);
              if (line) onEvent(line);
            }
          }
        };
        pump();
      }),
    log: logLine,
  };
}

if (import.meta.main) {
  runKeeper(liveDeps()).catch((e) => {
    logLine({ event: "keeper.fatal", error: String(e) });
    process.exit(1);
  });
}
