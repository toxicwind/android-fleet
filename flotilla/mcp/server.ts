#!/usr/bin/env bun
/**
 * flotilla/mcp/server.ts — REBUILT from scratch on the real dependencies.
 *
 * Deps (all imported, none monkey-patched):
 *   - @modelcontextprotocol/sdk  -> McpServer + WebStandardStreamableHTTPServerTransport
 *   - @devicefarmer/adbkit        -> all device ops (startActivity/shell/listDevices)
 *   - local modules               -> device.ts / ops.ts / focus.ts
 *
 * SDK architecture: one McpServer instance per session (each owns its own
 * Protocol — a Protocol can connect to exactly ONE transport). A factory
 * `createMcpServer()` emits a fresh server per new session; transports are
 * keyed by session_id. Port: 25245 (FLOTILLA_MCP_PORT).
 */
import { McpServer } from "@modelcontextprotocol/sdk/server/mcp.js";
import { WebStandardStreamableHTTPServerTransport } from "@modelcontextprotocol/sdk/server/webStandardStreamableHttp.js";
import { z } from "zod";
import { serve, $ } from "bun";
import { floatyCardHtml } from "./floaty-card.ts";
import { ensureDevice } from "./device.ts";
import { currentFocus, screencap, openUrl, openAndCapture } from "./ops.ts";

const PORT = Number(process.env.FLOTILLA_MCP_PORT ?? 25245);
const floatyPages = new Map<string, string>();
const servedFiles = new Map<string, { body: Uint8Array; contentType: string }>();

async function hostIp(): Promise<string> {
  const out = await $`ip -4 addr show scope global`.quiet().text();
  return out.match(/inet (\d+\.\d+\.\d+\.\d+)/)?.[1] ?? "10.0.0.218";
}

/**
 * Build a fresh McpServer with all flotilla tools registered. Called per
 * session — each instance owns one Protocol connected to one transport.
 */
function createMcpServer(): McpServer {
  const server = new McpServer(
    { name: "flotilla-mcp", version: "2.1.0" },
    { capabilities: { tools: {} } },
  );

  server.tool("pixel_connect", "Ensure adb connectivity to the Pixel (auto-rescues rotated port)", {}, async () => {
    const dev = await ensureDevice();
    return { content: [{ type: "text" as const, text: JSON.stringify({ device: dev.id }) }] };
  });

  server.tool("pixel_screenshot", "Screencap the Pixel to a PNG on the box", { file: z.string().optional() }, async ({ file }) => {
    const cap = await screencap(file ?? `/tmp/pixel-shot-${Date.now()}.png`);
    return { content: [{ type: "text" as const, text: JSON.stringify({ ...cap, focus: await currentFocus() }) }] };
  });

  server.tool("pixel_focus", "Which window owns the screen right now", {}, async () => {
    return { content: [{ type: "text" as const, text: JSON.stringify({ focus: await currentFocus() }) }] };
  });

  server.tool("pixel_open", "Open a URL headlessly (no capture)", { url: z.string() }, async ({ url }) => {
    const out = await openUrl(url);
    return { content: [{ type: "text" as const, text: JSON.stringify({ launched: out.includes("Starting"), focus: await currentFocus() }) }] };
  });

  server.tool(
    "pixel_view",
    "Open a URL, wait for browser CONTENT, capture. restore=true returns to prior app",
    { url: z.string(), file: z.string().optional(), browser: z.string().optional(), timeoutMs: z.number().optional(), restore: z.boolean().optional() },
    async (args) => {
      const r = await openAndCapture(args.url, { file: args.file, browser: args.browser, timeoutMs: args.timeoutMs, restore: args.restore });
      return { content: [{ type: "text" as const, text: JSON.stringify(r) }] };
    },
  );

  server.tool("pixel_tap", "Tap at x,y. Lands on the FOCUSED window — returns focus", { x: z.number(), y: z.number() }, async ({ x, y }) => {
    const { deviceClient } = await import("./device.ts");
    const dc = await deviceClient();
    await dc.shell(["input", "tap", String(x), String(y)]);
    return { content: [{ type: "text" as const, text: JSON.stringify({ tapped: [x, y], focus: await currentFocus() }) }] };
  });

  server.tool("pixel_text", "Type into the focused window. Returns focus", { text: z.string() }, async ({ text }) => {
    const { deviceClient } = await import("./device.ts");
    const dc = await deviceClient();
    await dc.shell(["input", "text", text.replace(/ /g, "%s")]);
    return { content: [{ type: "text" as const, text: JSON.stringify({ typed: text.length, focus: await currentFocus() }) }] };
  });

  server.tool("pixel_key", "Send a keyevent (4=BACK, 66=ENTER, 111=ESC, 3=HOME)", { keycode: z.number() }, async ({ keycode }) => {
    const { deviceClient } = await import("./device.ts");
    const dc = await deviceClient();
    await dc.shell(["input", "keyevent", String(keycode)]);
    return { content: [{ type: "text" as const, text: JSON.stringify({ keyed: keycode, focus: await currentFocus() }) }] };
  });

  server.tool("pixel_ui", "Dump + return the UI hierarchy XML", {}, async () => {
    const dev = await ensureDevice();
    const { deviceClient } = await import("./device.ts");
    const dc = await deviceClient();
    await dc.shell(["uiautomator", "dump", "/sdcard/pixel-ui.xml"]);
    const { $ } = await import("bun");
    await $`adb -s ${dev.id} pull /sdcard/pixel-ui.xml /tmp/pixel-ui.xml`.quiet().nothrow().text();
    const xml = (await Bun.file("/tmp/pixel-ui.xml").exists()) ? await (await Bun.file("/tmp/pixel-ui.xml").text()) : "";
    return { content: [{ type: "text" as const, text: JSON.stringify({ file: "/tmp/pixel-ui.xml", bytes: xml.length, xml: xml.slice(0, 60000) }) }] };
  });

  server.tool("flotilla_float", "Float a URL in a freeform (PiP) window over the user's app — keyboard-surviving (PortalPad launch)", { url: z.string() }, async ({ url }) => {
    const { launchFreeform } = await import("./freeform.ts");
    await ensureDevice();
    const r = await launchFreeform({ url, pkg: "org.mozilla.fenix" });
    return { content: [{ type: "text" as const, text: JSON.stringify({ floated: r.taskId !== null, taskId: r.taskId, resized: r.resize, gates: r.gates }) }] };
  });

  server.tool(
    "pixel_floaty",
    "Keyboard-safe message card over any app (DepartureMono pixel font)",
    { message: z.string(), sub: z.string().optional(), accent: z.string().optional(), secs: z.number().optional() },
    async ({ message, sub, accent, secs }) => {
      const dev = await ensureDevice();
      let fontB64 = "";
      try { fontB64 = (await Bun.file(`${import.meta.dir}/../assets/fonts/DepartureMono-Regular.otf`).bytes()).toBase64(); } catch {}
      const html = floatyCardHtml({ message, sub, accent, secs, fontB64 });
      const route = `/floaty/${Date.now()}`;
      floatyPages.set(route, html);
      const { $ } = await import("bun");
      await $`FLOTILLA_DEVICE=${dev.id} flotilla open http://${await hostIp()}:${PORT}${route}`.quiet().nothrow().text();
      return { content: [{ type: "text" as const, text: JSON.stringify({ floated: true, route, font: fontB64 ? "DepartureMono" : "fallback-monospace" }) }] };
    },
  );

  return server;
}

// ── ephemeral file server (serve tool) ───────────────────────────────────
const serveServer = serve({
  hostname: "0.0.0.0", port: 0,
  fetch: (req) => {
    const u = new URL(req.url);
    const file = servedFiles.get(u.pathname.replace(/^\/serve\//, ""));
    return file ? new Response(file.body, { headers: { "content-type": file.contentType } }) : new Response("not found", { status: 404 });
  },
});

// ── HTTP surface: sessioned, per SDK canonical example ───────────────────
// One McpServer per session (each owns its own Protocol); transports keyed by
// the `mcp-session-id` header the SDK returns. Idle-reaped.
const sessions = new Map<string, { transport: WebStandardStreamableHTTPServerTransport; lastActive: number }>();
const IDLE_MS = 30 * 60_000;
const MAX_SESSIONS = 1000;

setInterval(() => {
  const cutoff = Date.now() - IDLE_MS;
  for (const [sid, { transport, lastActive }] of sessions) {
    if (lastActive < cutoff) { transport.close().catch(() => {}); sessions.delete(sid); }
  }
}, 60_000).unref();

serve({
  hostname: "127.0.0.1", port: PORT,
  async fetch(request) {
    const url = new URL(request.url);
    if (url.pathname.startsWith("/floaty/")) {
      const html = floatyPages.get(url.pathname);
      return html ? new Response(html, { headers: { "content-type": "text/html; charset=utf-8" } }) : new Response("expired", { status: 404 });
    }
    if (url.pathname === "/health") {
      return Response.json({ ok: true, service: "flotilla-mcp", device: (await ensureDevice().catch(() => null))?.id ?? null });
    }
    if (url.pathname === "/mcp") {
      try {
        const sessionId = request.headers.get("mcp-session-id");
        // Peek the method WITHOUT consuming the body: clone the request first.
        const clone = request.clone();
        let isInit = false;
        try { const b = await clone.json() as { method?: string }; isInit = b?.method === "initialize"; } catch { isInit = false; }
        const existing = sessionId ? sessions.get(sessionId) : undefined;

        if (existing) {
          existing.lastActive = Date.now();
          return await existing.transport.handleRequest(request);
        }
        // New session: only valid on an initialize request (no session header).
        if (isInit) {
          if (sessions.size >= MAX_SESSIONS) {
            return Response.json({ jsonrpc: "2.0", error: { code: -32000, message: "Too many open sessions" }, id: null }, { status: 503 });
          }
          let transport: WebStandardStreamableHTTPServerTransport;
          transport = new WebStandardStreamableHTTPServerTransport({
            sessionIdGenerator: () => crypto.randomUUID(),
            onsessioninitialized: (sid) => {
              sessions.set(sid, { transport, lastActive: Date.now() });
            },
          });
          transport.onclose = () => { if (transport.sessionId) sessions.delete(transport.sessionId); };
          const server = createMcpServer();
          await server.connect(transport);
          return await transport.handleRequest(request);
        }
        if (sessionId) {
          return Response.json({ jsonrpc: "2.0", error: { code: -32001, message: "Session not found" }, id: null }, { status: 404 });
        }
        return Response.json({ jsonrpc: "2.0", error: { code: -32600, message: "Invalid Request: initialize required to start a session" }, id: null }, { status: 400 });
      } catch (err) {
        return Response.json({ jsonrpc: "2.0", error: { code: -32000, message: err instanceof Error ? err.message : String(err) }, id: null }, { status: 500 });
      }
    }
    return Response.json({ ok: false, error: "not found" }, { status: 404 });
  },
});

console.log(`flotilla-mcp v2.1.0: http://127.0.0.1:${PORT}/mcp (SDK per-session, adbkit) — OK`);