#!/usr/bin/env bun
/**
 * flotilla/mcp/server.ts — the android-fleet MCP drop-in (REFACTORED).
 *
 * ANY agent can drive the Architect's Pixel 9 Pro XL headlessly: screenshots,
 * open URLs, taps, text, UI-tree reads, freeform floats. Federated via thresher
 * (:25127/mcp) — same streamable-HTTP JSON-RPC shape as taas.ts (proven
 * pattern).
 *
 *   POST /mcp  — JSON-RPC 2.0 (initialize / tools/list / tools/call)
 *   GET /health
 *
 * Focus doctrine (flotilla covenant): `input tap/text` lands on the FOCUSED
 * window and always returns focus. Browser capture uses ONE settle-until-
 * content engine (launch.ts) — no more per-tool bespoke polling. restore=true
 * returns the user to their prior foreground app after capture.
 *
 * Port: FLOTILLA_MCP_PORT (default 25245, ports.env SSOT).
 */
import { serve, $ } from "bun";
import { floatyCardHtml } from "./floaty-card.ts";
import { ensureDevice } from "./device.ts";
import { openAndCapture, type CaptureResult } from "./launch.ts";

const PORT = Number(process.env.FLOTILLA_MCP_PORT ?? 25245);
const SERIAL_BASE = process.env.FLOTILLA_DEVICE_IP ?? "10.0.0.77";

// ── floaty card serving (pixel_floaty aesthetic channel) ────────────────
const floatyPages = new Map<string, string>();
const servedFiles = new Map<string, { body: Uint8Array; contentType: string }>();

async function hostIp(): Promise<string> {
  const out = await $`ip -4 addr show scope global`.quiet().text();
  return out.match(/inet (\d+\.\d+\.\d+\.\d+)/)?.[1] ?? "10.0.0.218";
}

/** adb helper bound to the LIVE device (resolves once, not 19x). */
async function adb(...args: string[]): Promise<string> {
  const dev = await ensureDevice();
  return $`adb -s ${dev} ${args}`.quiet().nothrow().text();
}

async function currentFocus(): Promise<string> {
  const out = await adb("shell", "dumpsys window 2>/dev/null | grep mCurrentFocus");
  return out.trim() || "unknown";
}

/** in-process capture (screencap -> pull -> rm). */
async function capturePng(file: string): Promise<{ file: string; bytes: number }> {
  const dev = await ensureDevice();
  const remote = `/sdcard/flotilla-${Date.now()}.png`;
  await adb("shell", "screencap", "-p", remote);
  const pull = await $`adb -s ${dev} pull ${remote} ${file}`.quiet().nothrow().text();
  await $`adb -s ${dev} shell rm ${remote}`.quiet().nothrow().text();
  const bytes = (await Bun.file(file).exists()) ? (await Bun.file(file).size) : 0;
  return { file, bytes };
}

const launchDeps = {
  currentFocus,
  startView: async (url: string, browser?: string) => {
    const dev = await ensureDevice();
    const r = browser
      ? await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${url} -p ${browser}`.quiet().nothrow().text()
      : await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${url}`.quiet().nothrow().text();
    return r;
  },
  capture: capturePng,
  resolveActivity: async (pkg: string) => {
    const dev = await ensureDevice();
    const brief = await $`adb -s ${dev} shell cmd package resolve-activity --brief ${pkg}`.quiet().nothrow().text();
    return brief.split("\n").map((l) => l.trim()).find((l) => l.startsWith(pkg)) ?? null;
  },
  bringToFront: async (component: string) => {
    const dev = await ensureDevice();
    await $`adb -s ${dev} shell am start -n ${component}`.quiet().nothrow().text();
  },
};

function splitCmd(cmd: string): string[] {
  const argv: string[] = []; let cur = ""; let quote: string | null = null;
  for (const ch of cmd) {
    if (quote) { if (ch === quote) quote = null; else cur += ch; }
    else if (ch === "'" || ch === '"') quote = ch;
    else if (ch === " " || ch === "\t") { if (cur) { argv.push(cur); cur = ""; } }
    else cur += ch;
  }
  if (cur) argv.push(cur);
  return argv;
}

function need(args: Record<string, unknown>, key: string, tool: string): string {
  const v = args[key];
  if (typeof v !== "string" || v.length === 0) throw new Error(`${tool}: missing/empty string arg '${key}'`);
  return v;
}

function content(result: unknown, isError = false) {
  return { content: [{ type: "text", text: JSON.stringify(result) }], ...(isError ? { isError: true } : {}) };
}

// ── tool implementations ────────────────────────────────────────────────
const tools = [
  { name: "pixel_connect", description: "Ensure adb connectivity to the Pixel (10.0.0.77). Auto-rescues a rotated wireless-debug port.", inputSchema: { type: "object", properties: {} }, run: async () => ({ device: await ensureDevice() }) },
  { name: "pixel_screenshot", description: "Screencap the Pixel to a PNG file on the box.", inputSchema: { type: "object", properties: { file: { type: "string" } } }, run: async (a) => { const f = String(a.file ?? `/tmp/pixel-shot-${Date.now()}.png`); const p = await capturePng(f); return { ...p, focus: await currentFocus() }; } },
  { name: "pixel_focus", description: "Which window owns the screen right now.", inputSchema: { type: "object", properties: {} }, run: async () => ({ focus: await currentFocus() }) },
  { name: "pixel_view", description: "Open a URL, wait for browser CONTENT (settle), capture. restore=true returns to prior app after capture.", inputSchema: { type: "object", properties: { url: { type: "string" }, file: { type: "string" }, restore: { type: "boolean" }, browser: { type: "string" }, timeoutMs: { type: "number" } }, required: ["url"] }, run: async (a) => (await launch(a)) as CaptureResult },
  { name: "pixel_open", description: "Open a URL headlessly (no capture).", inputSchema: { type: "object", properties: { url: { type: "string" } }, required: ["url"] }, run: async (a) => { const dev = await ensureDevice(); const out = await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${need(a, "url", "pixel_open")}`.quiet().nothrow().text(); return { launched: out.includes("Starting"), focus: await currentFocus() }; } },
  { name: "pixel_openshot", description: "Open a URL then screencap once the browser settles. restore=true returns to prior app.", inputSchema: { type: "object", properties: { url: { type: "string" }, file: { type: "string" }, restore: { type: "boolean" } }, required: ["url"] }, run: async (a) => (await launch(a)) as CaptureResult },
  { name: "pixel_browser", description: "Open a URL in an EXPLICIT browser and wait for page CONTENT, screenshot + state.", inputSchema: { type: "object", properties: { url: { type: "string" }, browser: { type: "string" }, file: { type: "string" }, waitMs: { type: "number" } }, required: ["url"] }, run: async (a) => (await launch(a)) as CaptureResult },
  { name: "pixel_tap", description: "Tap at x,y. Lands on the FOCUSED window — returns focus.", inputSchema: { type: "object", properties: { x: { type: "number" }, y: { type: "number" } }, required: ["x", "y"] }, run: async (a) => { await adb("shell", "input", "tap", String(a.x), String(a.y)); return { tapped: [a.x, a.y], focus: await currentFocus() }; } },
  { name: "pixel_text", description: "Type into the focused window. Returns focus.", inputSchema: { type: "object", properties: { text: { type: "string" } }, required: ["text"] }, run: async (a) => { await adb("shell", "input", "text", String(a.text).replace(/ /g, "%s")); return { typed: String(a.text).length, focus: await currentFocus() }; } },
  { name: "pixel_key", description: "Send a keyevent (4=BACK, 66=ENTER, 111=ESC, 3=HOME).", inputSchema: { type: "object", properties: { keycode: { type: "number" } }, required: ["keycode"] }, run: async (a) => { await adb("shell", "input", "keyevent", String(a.keycode)); return { keyed: a.keycode, focus: await currentFocus() }; } },
  { name: "pixel_ui", description: "Dump + return the UI hierarchy XML (tap targets, labels, bounds).", inputSchema: { type: "object", properties: {} }, run: async () => { await adb("shell", "uiautomator", "dump", "/sdcard/pixel-ui.xml"); const dev = await ensureDevice(); await $`adb -s ${dev} pull /sdcard/pixel-ui.xml /tmp/pixel-ui.xml`.quiet().nothrow().text(); const xml = await Bun.file("/tmp/pixel-ui.xml").text(); return { file: "/tmp/pixel-ui.xml", bytes: xml.length, xml: xml.slice(0, 60000) }; } },
  { name: "flotilla_float", description: "Float a URL in a freeform (PiP) window over the user's app.", inputSchema: { type: "object", properties: { url: { type: "string" } }, required: ["url"] }, run: async (a) => { const dev = await ensureDevice(); const out = await $`FLOTILLA_DEVICE=${dev} flotilla open ${need(a, "url", "flotilla_float")}`.quiet().nothrow().text(); return { floated: !out.includes("error"), out: out.trim().slice(0, 400), device: dev }; } },
  { name: "pixel_floaty", description: "Keyboard-safe message card over any app (DepartureMono pixel font).", inputSchema: { type: "object", properties: { message: { type: "string" }, sub: { type: "string" }, accent: { type: "string" }, secs: { type: "number" } }, required: ["message"] }, run: async (a) => { const msg = String(a.message).slice(0, 400); const sub = String(a.sub ?? "").slice(0, 200); const accent = /^#[0-9a-fA-F]{3,8}$/.test(String(a.accent ?? "")) ? String(a.accent) : "#7CFC00"; const secs = Number(a.secs ?? 0); let fontB64 = ""; try { fontB64 = (await Bun.file(`${import.meta.dir}/../assets/fonts/DepartureMono-Regular.otf`).bytes()).toBase64(); } catch {} const html = floatyCardHtml({ message: msg, sub, accent, secs, fontB64 }); const route = `/floaty/${Date.now()}`; floatyPages.set(route, html); const dev = await ensureDevice(); await $`FLOTILLA_DEVICE=${dev} flotilla open http://${await hostIp()}:${PORT}${route}`.quiet().nothrow().text(); return { floated: true, route, font: fontB64 ? "DepartureMono" : "fallback-monospace" }; } },
  { name: "pixel_shell", description: "Run an adb shell command string on the Pixel (tokenized, ≤60KB).", inputSchema: { type: "object", properties: { command: { type: "string" }, mergeErr: { type: "boolean" } }, required: ["command"] }, run: async (a) => { const cmd = need(a, "command", "pixel_shell"); const argv = splitCmd(cmd); const dev = await ensureDevice(); const proc = Bun.spawn({ cmd: ["adb", "-s", dev, "shell", ...argv], stdout: "pipe", stderr: "pipe" }); const [out, err] = await Promise.all([new Response(proc.stdout).text(), new Response(proc.stderr).text()]); return { out: (out + (a.mergeErr ? "\n" + err : "")).slice(0, 60000), truncated: out.length > 60000, stderr: err.trim().slice(0, 2000) }; } },
  { name: "pixel_package", description: "Deep-dive one package: version, install/update, perms, state.", inputSchema: { type: "object", properties: { pkg: { type: "string" } }, required: ["pkg"] }, run: async (a) => { const pkg = String(a.pkg); const out = await adb("shell", "dumpsys", "package", pkg); const lines = out.split("\n"); const pick = (re) => lines.find((l) => re.test(l))?.trim() ?? null; return { pkg, version: pick(/versionName=|versionCode=/), firstInstall: pick(/firstInstallTime=/), lastUpdate: pick(/lastUpdateTime=/), dataDir: pick(/dataDir=/), apkPath: pick(/codePath=/) }; } },
  { name: "serve", description: "Serve a file from the box to the phone via an ephemeral HTTP server.", inputSchema: { type: "object", properties: { path: { type: "string" }, name: { type: "string" } }, required: ["path"] }, run: async (a) => { const filePath = String(a.path); const name = String(a.name ?? filePath.split("/").pop() ?? "file"); const body = await Bun.file(filePath).bytes(); servedFiles.set(name, { body, contentType: filePath.endsWith(".html") ? "text/html" : "application/octet-stream" }); const port = (serveServer as unknown as { port?: number }).port ?? 0; return { ok: true, url: `http://${await hostIp()}:${port}/serve/${name}`, size: body.byteLength }; } },
];

/** The ONE launch path all browser tools share. */
async function launch(a: Record<string, unknown>): Promise<CaptureResult | { error: string }> {
  try {
    const dev = await ensureDevice();
    return await openAndCapture(dev, {
      url: need(a, "url", "pixel_view"),
      browser: a.browser ? String(a.browser) : undefined,
      file: a.file ? String(a.file) : undefined,
      timeoutMs: a.timeoutMs ? Number(a.timeoutMs) : undefined,
      restore: a.restore === true,
    }, launchDeps);
  } catch (e) {
    return { error: e instanceof Error ? e.message : String(e) };
  }
}

const serveServer = serve({
  hostname: "0.0.0.0", port: 0,
  fetch: (req) => { const u = new URL(req.url); const file = servedFiles.get(u.pathname.replace(/^\/serve\//, "")); return file ? new Response(file.body, { headers: { "content-type": file.contentType } }) : new Response("not found", { status: 404 }); },
});

const server = serve({
  hostname: "127.0.0.1", port: PORT,
  async fetch(request) {
    const url = new URL(request.url);
    if (url.pathname.startsWith("/floaty/")) { const html = floatyPages.get(url.pathname); return html ? new Response(html, { headers: { "content-type": "text/html; charset=utf-8" } }) : new Response("expired", { status: 404 }); }
    if (url.pathname === "/health") return Response.json({ ok: true, service: "flotilla-mcp", device: await ensureDevice().catch(() => null) });
    if (url.pathname === "/mcp" && request.method === "POST") {
      const rpc = (await request.json()) as { id?: unknown; method?: string; params?: { name?: string; arguments?: Record<string, unknown> } };
      const reply = (result: unknown) => Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, result });
      if (rpc.method === "initialize") return reply({ protocolVersion: "2025-03-26", capabilities: { tools: {} }, serverInfo: { name: "flotilla-mcp", version: "1.1.0" } });
      if (rpc.method === "notifications/initialized") return new Response(null, { status: 202 });
      if (rpc.method === "tools/list") return reply({ tools: tools.map(({ name, description, inputSchema }) => ({ name, description, inputSchema })) });
      if (rpc.method === "tools/call") {
        const name = rpc.params?.name ?? "";
        const tool = tools.find((t) => t.name === name);
        if (!tool) return Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, error: { code: -32602, message: `unknown tool ${name}` } }, { status: 400 });
        try { return reply(content(await tool.run(rpc.params?.arguments ?? {}))); } catch (err) { return reply(content({ error: err instanceof Error ? err.message : String(err) }, true)); }
      }
      return Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, error: { code: -32601, message: `unknown method ${rpc.method}` } }, { status: 400 });
    }
    return Response.json({ ok: false, error: "not found" }, { status: 404 });
  },
});

console.log(`flotilla-mcp v1.1.0: http://127.0.0.1:${PORT}/mcp (unified launch core, focus-covenant on)`);