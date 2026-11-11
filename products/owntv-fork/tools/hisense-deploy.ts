#!/usr/bin/env bun
/**
 * hisense-deploy — headless APK deploy to Hisense SmartTV via Shizuku rish bridge.
 *
 * No Gradle, no adb install needed after setup. Uses the TV's own Shizuku
 * (moe.shizuku.privileged.api) server + staged rish binary to run `pm install`
 * as shell uid (2000) over the ADB shell — the adv-install pattern, bun-native.
 *
 * Usage:
 *   bun hisense-deploy.ts <path-to.apk> [--package tv.own.owntv] [--fresh] [--launch]
 *
 * Requires on TV (already staged):
 *   /data/local/tmp/rish             (from Shizuku APK assets/rish)
 *   /data/local/tmp/rish_shizuku.dex (assets/rish_shizuku.dex, chmod 400)
 *   /data/local/tmp/librish.so       (lib/<abi>/librish.so)
 *   Shizuku server running (shizuku_server process)
 */
import { spawnSync } from "node:child_process";
import { statSync } from "node:fs";

const DEVICE = "10.0.0.225:5555";
const RISH = "RISH_APPLICATION_ID=moe.shizuku.privileged.api LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/rish";

const args = process.argv.slice(2);
const apk = args.find(a => a.endsWith(".apk"));
const pkg = args.find((a, i) => args[i - 1] === "--package");
const fresh = args.includes("--fresh");
const launch = args.includes("--launch");

if (!apk) {
  console.error("usage: bun hisense-deploy.ts <path-to.apk> [--package ID] [--fresh] [--launch]");
  process.exit(2);
}

function adb(remoteCmd: string): { ok: boolean; out: string } {
  const p = spawnSync("adb", ["-s", DEVICE, "shell", remoteCmd], {
    encoding: "utf8",
    timeout: 30_000,
  });
  return { ok: p.status === 0, out: (p.stdout ?? "").trim() };
}

function rish(cmd: string): { ok: boolean; out: string } {
  // Wrap in a shell -c so env vars + the rish invocation survive adb shell splitting.
  return adb(`sh -c '${RISH} -c \"${cmd.replace(/'/g, "'\\''")}\"'`);
}

function stage(apkPath: string, remote: string): void {
  const p = spawnSync("adb", ["-s", DEVICE, "push", apkPath, remote], {
    encoding: "utf8",
    timeout: 60_000,
  });
  if (p.status !== 0) {
    console.error("push failed:", p.stderr?.toString());
    process.exit(1);
  }
  console.log(`staged ${apkPath} -> ${remote}`);
}

// Verify rish bridge
const check = adb(`${RISH} -c 'id'`);
if (!check.ok || !check.out.includes("uid=2000")) {
  console.error("rish bridge not ready:", check.out || "no output");
  console.error("stage rish from Shizuku APK: assets/rish, assets/rish_shizuku.dex, lib/<abi>/librish.so -> /data/local/tmp");
  process.exit(1);
}
console.log("rish bridge ok:", check.out.split(" ")[0]);

const size = statSync(apk).size;
const remote = "/data/local/tmp/deploy.apk";
stage(apk, remote);

if (fresh && pkg) {
  const u = adb(`${RISH} -c 'pm uninstall --user current ${pkg}'`);
  console.log("fresh uninstall:", u.out || "ok");
}

const cmd = `pm install -r -d -g '${remote}'`;
console.log("installing via rish pm...");
const inst = adb(`${RISH} -c '${cmd}'`);
console.log("pm:", inst.out || "(no output)");
if (!inst.out.includes("Success")) {
  console.error("install may have failed (check output above)");
  process.exit(1);
}

if (launch && pkg) {
  const l = adb(`monkey -p ${pkg} -c android.intent.category.LAUNCHER 1`);
  console.log("launch:", l.out || "ok");
}

console.log("done.");