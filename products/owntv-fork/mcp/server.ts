#!/usr/bin/env bun
/**
 * owntv-mcp — fork-owned MCP server for autonomous OwnTV control on the Hisense TV.
 *
 * Exposes the two control surfaces as MCP tools:
 *   1. Lua lane  — POST http://127.0.0.1:38383/run  (app-private: db/sql/prefs/exec/WorkManager)
 *   2. One-offs  — adb shell / uiautomator / input / pm install / am start / screencap
 *
 * Transport: stdio JSON-RPC (MCP). Run: `bun mcp/server.ts` (registered in thresher).
 */
import { spawnSync } from "node:child_process";

const SERIAL = "10.0.0.225:5555";
const LUA = "http://127.0.0.1:38383/run";
const PKG = "tv.own.owntv";

function adb(args: string[], timeoutMs = 15000): { ok: boolean; out: string; code: number } {
  const r = spawnSync("adb", ["-s", SERIAL, ...args], { encoding: "utf8", timeout: timeoutMs, maxBuffer: 64 * 1024 * 1024 });
  return { ok: r.status === 0, out: (r.stdout ?? "") + (r.stderr ?? ""), code: r.status ?? -1 };
}

function ensureForward(): void {
  // Idempotent: adb forward is cheap; re-assert every call so a dropped device never wedges the lane.
  adb(["forward", "tcp:38383", "tcp:38383"], 5000);
}

function luaRun(script: string): { ok: boolean; output: string; error: string | null } {
  ensureForward();
  const r = spawnSync("curl", ["-s", "-m", "30", "-X", "POST", LUA, "-H", "content-type: application/json", "-d", JSON.stringify({ script })], {
    encoding: "utf8", timeout: 35000, maxBuffer: 16 * 1024 * 1024,
  });
  try {
    const j = JSON.parse(r.stdout || "{}");
    return { ok: !!j.ok, output: j.output ?? "", error: j.error ?? null };
  } catch {
    return { ok: false, output: r.stdout, error: r.stderr || "lua lane unreachable" };
  }
}

// --- MCP plumbing -----------------------------------------------------------

type Tool = { name: string; description: string; inputSchema: Record<string, unknown>; run: (args: Record<string, unknown>) => unknown };

const tools: Tool[] = [
  {
    name: "lua_run",
    description: "Execute a Lua script in the OwnTV app's debug lane (:38383). Full app-private access: sql(), db, prefs, koin(clazz), exec(cmd), enqueueSync(id), enqueueEpg(id), refreshSource(id), runSearch(q), view(), focused(), shot(path).",
    inputSchema: { type: "object", properties: { script: { type: "string", description: "Lua source to execute" } }, required: ["script"] },
    run: (a) => luaRun(String(a.script ?? "")),
  },
  {
    name: "sql",
    description: "Run raw SQL against the app's live owntv.db (via the Lua lane). SELECT returns rows keyed by column name; other statements execute and return ok.",
    inputSchema: { type: "object", properties: { query: { type: "string", description: "SQL statement" } }, required: ["query"] },
    run: (a) => luaRun(`local rows = sql(${JSON.stringify(String(a.query ?? ""))}) local out = {} for i=1,#rows do local r = rows[i] local parts = {} for k,v in pairs(r) do parts[#parts+1] = k .. "=" .. tostring(v) end out[#out+1] = table.concat(parts, " ") end log(table.concat(out, "\\n")) return #rows`),
  },
  {
    name: "adb",
    description: "Run one adb shell command on the Hisense (10.0.0.225:5555). E.g. 'dumpsys activity activities | grep mResumedActivity', 'pidof tv.own.owntv'.",
    inputSchema: { type: "object", properties: { command: { type: "string", description: "Shell command to run on the device" } }, required: ["command"] },
    run: (a) => adb(["shell", String(a.command ?? "")]),
  },
  {
    name: "ui_dump",
    description: "uiautomator dump of the current screen (full, not --compressed — Compose clickable attrs need it). Returns the XML hierarchy.",
    inputSchema: { type: "object", properties: {} },
    run: () => {
      adb(["shell", "uiautomator dump /sdcard/ui.xml"], 15000);
      return adb(["shell", "cat /sdcard/ui.xml"], 15000);
    },
  },
  {
    name: "input",
    description: "Send an input event: keyevent KEYCODE_DPAD_UP, tap X Y, text 'string', swipe X1 Y1 X2 Y2 DURATION.",
    inputSchema: { type: "object", properties: { event: { type: "string", description: "input subcommand, e.g. 'keyevent KEYCODE_DPAD_CENTER' or 'tap 74 267'" } }, required: ["event"] },
    run: (a) => adb(["shell", "input " + String(a.event ?? "")]),
  },
  {
    name: "install_apk",
    description: "Push + pm install the OwnTV APK via the Shizuku rish bridge (shell uid 2000). Preserves app data (-r -d -g).",
    inputSchema: { type: "object", properties: { apk: { type: "string", description: "Local path to the APK" } }, required: ["apk"] },
    run: (a) => {
      const apk = String(a.apk ?? "");
      const push = adb(["push", apk, "/data/local/tmp/deploy.apk"], 60000);
      if (!push.ok) return push;
      const rish = "RISH_APPLICATION_ID=moe.shizuku.privileged.api LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/rish -c 'pm install -r -d -g /data/local/tmp/deploy.apk'";
      return adb(["shell", rish], 120000);
    },
  },
  {
    name: "launch",
    description: "Launch (or bring to front) the OwnTV app on the Hisense.",
    inputSchema: { type: "object", properties: {} },
    run: () => adb(["shell", `am start -n ${PKG}/.MainActivity`]),
  },
  {
    name: "screenshot",
    description: "Capture the TV screen to a local PNG and return the path.",
    inputSchema: { type: "object", properties: { name: { type: "string", description: "Output filename (default shot.png)" } } },
    run: (a) => {
      const name = String(a.name ?? "shot.png");
      adb(["shell", `screencap -p /sdcard/${name}`], 15000);
      const pull = adb(["pull", `/sdcard/${name}`, `/tmp/${name}`], 30000);
      return { ...pull, path: `/tmp/${name}` };
    },
  },
  {
    name: "enqueue_sync",
    description: "Force a catalog sync for a source id through WorkManager (CatalogSyncScheduler.enqueueSync).",
    inputSchema: { type: "object", properties: { sourceId: { type: "number", description: "Source id in owntv.db" } }, required: ["sourceId"] },
    run: (a) => luaRun(`enqueueSync(${Number(a.sourceId)}) return "enqueued"`),
  },
  {
    name: "provider_health",
    description: "Health of the master Xtream provider gateway on :8081 (channel/category counts).",
    inputSchema: { type: "object", properties: {} },
    run: () => {
      const r = spawnSync("curl", ["-s", "-m", "4", "http://127.0.0.1:8081/health"], { encoding: "utf8", timeout: 8000 });
      return r.stdout || "provider unreachable";
    },
  },
];

function toolDef(t: Tool) {
  return { name: t.name, description: t.description, inputSchema: t.inputSchema };
}

const encoder = new TextEncoder();
function send(obj: unknown) {
  process.stdout.write(encoder.encode(JSON.stringify(obj) + "\n"));
}

let buf = "";
process.stdin.setEncoding("utf8");
process.stdin.on("data", (chunk: string) => {
  buf += chunk;
  let idx: number;
  while ((idx = buf.indexOf("\n")) >= 0) {
    const line = buf.slice(0, idx);
    buf = buf.slice(idx + 1);
    if (!line.trim()) continue;
    let msg: any;
    try { msg = JSON.parse(line); } catch { continue; }

    if (msg.method === "initialize") {
      send({ jsonrpc: "2.0", id: msg.id, result: { protocolVersion: "2024-11-05", capabilities: { tools: {} }, serverInfo: { name: "owntv-mcp", version: "0.1.0" } } });
    } else if (msg.method === "notifications/initialized") {
      // no-op
    } else if (msg.method === "tools/list") {
      send({ jsonrpc: "2.0", id: msg.id, result: { tools: tools.map(toolDef) } });
    } else if (msg.method === "tools/call") {
      const tool = tools.find((t) => t.name === msg.params?.name);
      if (!tool) {
        send({ jsonrpc: "2.0", id: msg.id, error: { code: -32602, message: `unknown tool: ${msg.params?.name}` } });
      } else {
        try {
          const result = tool.run(msg.params?.arguments ?? {});
          const text = typeof result === "string" ? result : JSON.stringify(result, null, 2);
          send({ jsonrpc: "2.0", id: msg.id, result: { content: [{ type: "text", text }] } });
        } catch (e: any) {
          send({ jsonrpc: "2.0", id: msg.id, error: { code: -32603, message: String(e?.message ?? e) } });
        }
      }
    } else if (msg.method === "ping") {
      send({ jsonrpc: "2.0", id: msg.id, result: {} });
    } else if (msg.id !== undefined) {
      send({ jsonrpc: "2.0", id: msg.id, error: { code: -32601, message: `method not found: ${msg.method}` } });
    }
  }
});