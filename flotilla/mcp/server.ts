#!/usr/bin/env bun
/**
 * flotilla/mcp/server.ts — the android-fleet MCP drop-in.
 *
 * ANY agent can drive the Architect's Pixel 9 Pro XL headlessly:
 * screenshots, open URLs, taps, text, UI-tree reads, freeform floats.
 * Federated via thresher (:25127) — same streamable-HTTP JSON-RPC shape
 * as taas.ts (proven pattern).
 *
 *   POST /mcp  — JSON-RPC 2.0 (initialize / tools/list / tools/call)
 *   GET /health
 *
 * Focus doctrine (flotilla covenant, enforced here): `input tap/text`
 * lands on the FOCUSED window. pixel_tap/pixel_text therefore return the
 * current focus with every result — an agent that taps blind gets shown
 * what window it hit. Nothing on this surface ever steals focus.
 *
 * Port: FLOTILLA_MCP_PORT (default 25245, ports.env SSOT).
 */
import { serve } from "bun";
import { $ } from "bun";
import { floatyCardHtml } from "./floaty-card.ts";

const PORT = Number(process.env.FLOTILLA_MCP_PORT ?? 25245);
const SERIAL_BASE = process.env.FLOTILLA_DEVICE_IP ?? "10.0.0.77";

// ── floaty card serving (pixel_floaty aesthetic channel) ────────────────
const PORT_FLOATY = PORT; // same HTTP surface; /floaty/<route> serves message cards
const floatyPages = new Map<string, string>();
/** In-memory file cache for /serve/<name> requests. */
const servedFiles = new Map<string, { body: Uint8Array; contentType: string }>();
/** LAN host IP the phone can reach (default: the box's LAN address). */
async function hostIp(): Promise<string> {
	const out = await $`ip -4 addr show scope global`.quiet().text();
	return out.match(/inet (\d+\.\d+\.\d+\.\d+)/)?.[1] ?? "10.0.0.218";
}

// ── adb device resolution (keepalive normally keeps this warm) ──────────
let device: string | null = null;

async function liveDevice(): Promise<string | null> {
	const out = await $`adb devices`.quiet().text();
	for (const line of out.split("\n")) {
		if (line.startsWith(`${SERIAL_BASE}:`) && line.trimEnd().endsWith("device"))
			return line.split("\t")[0]!;
	}
	return null;
}

async function scanPorts(): Promise<string[]> {
	const mdns = await $`adb mdns services`.quiet().nothrow().text();
	const fromMdns = [...mdns.matchAll(new RegExp(`${SERIAL_BASE.replace(/\./g, "\\.")}:(\\d+)`, "g"))].map((m) => m[1]!);
	if (fromMdns.length) return fromMdns;
	const nmap = await $`nmap -T4 --open -p 30000-50000 ${SERIAL_BASE}`.quiet().nothrow().text();
	return [...nmap.matchAll(/^(\d+)\/tcp\s+open/gm)].map((m) => m[1]!);
}

async function ensureDevice(): Promise<string> {
	if (device && (await liveDevice()) === device) return device;
	const live = await liveDevice();
	if (live) { device = live; return live; }
	for (const p of await scanPorts()) {
		await $`adb connect ${`${SERIAL_BASE}:${p}`}`.quiet().nothrow();
		const d = await liveDevice();
		if (d) { device = d; return d; }
	}
	throw new Error("no adb route to phone — wireless-debug off or keepalive daemon down");
}

async function sh(...args: string[]): Promise<string> {
	const dev = await ensureDevice();
	return $`adb -s ${dev} ${args}`.quiet().nothrow().text();
}

/** Split a single command string into argv (respects quotes) without executing a shell. */
function splitCmd(cmd: string): string[] {
	const argv: string[] = [];
	let cur = "";
	let quote: string | null = null;
	for (const ch of cmd) {
		if (quote) { if (ch === quote) quote = null; else cur += ch; }
		else if (ch === "'" || ch === '"') quote = ch;
		else if (ch === " " || ch === "\t") { if (cur) { argv.push(cur); cur = ""; } }
		else cur += ch;
	}
	if (cur) argv.push(cur);
	return argv;
}

async function currentFocus(): Promise<string> {
	const out = await sh("shell", "dumpsys window 2>/dev/null | grep mCurrentFocus");
	return out.trim() || "unknown";
}

/**
 * adb raw-byte capture (screencap etc.) — returns Uint8Array.
 * Bun.spawn cannot stream `adb exec-out` binary (returns 0 bytes in bun 1.4.x),
 * so the proven path is on-device screencap + pull via the `$` shell helper.
 */
async function shBytes(): Promise<Uint8Array> {
	const dev = await ensureDevice();
	const remote = `/sdcard/flotilla-${Date.now()}.png`;
	await sh("shell", "screencap", "-p", remote);
	await $`adb -s ${dev} pull ${remote} /tmp/flotilla-shot.png`.quiet().nothrow().text();
	await $`adb -s ${dev} shell rm ${remote}`.quiet().nothrow().text();
	return new Uint8Array(await Bun.file("/tmp/flotilla-shot.png").arrayBuffer());
}

/** Canonical string-arg reader that fails loud (no silent undefined), naming the tool. */
function need(args: Record<string, unknown>, key: string, tool: string): string {
	const v = args[key];
	if (typeof v !== "string" || v.length === 0) throw new Error(`${tool}: missing/empty string arg '${key}' (got ${JSON.stringify(v)})`);
	return v;
}

function content(result: unknown, isError = false) {
	return { content: [{ type: "text", text: JSON.stringify(result) }], ...(isError ? { isError: true } : {}) };
}

// ── tool implementations ────────────────────────────────────────────────
const tools = [
	{
		name: "pixel_connect",
		description: "Ensure adb connectivity to the Pixel (10.0.0.77). Auto-rescues a rotated wireless-debug port via mDNS/nmap. Returns the live device serial.",
		inputSchema: { type: "object", properties: {} },
		run: async () => ({ device: await ensureDevice() }),
	},
	{
		name: "pixel_screenshot",
		description: "Screencap the Pixel to a PNG file on the box. Returns {file, bytes, focus}. Use pixel_ui for coordinates instead of guessing taps.",
		inputSchema: { type: "object", properties: { file: { type: "string", description: "output path (default /tmp/pixel-shot-<ts>.png)" } } },
		run: async (args: Record<string, unknown>) => {
			const file = String(args.file ?? `/tmp/pixel-shot-${Date.now()}.png`);
			const dev = await ensureDevice();
			const png = await shBytes();
			await Bun.write(file, png);
			return { file, bytes: png.byteLength, focus: await currentFocus() };
		},
	},
	{
		name: "pixel_open",
		description: "Open a URL on the phone, headlessly. http VIEW intents resolve to Firefox as sole default — no chooser ever appears. Does not steal focus from the user's app beyond the launch itself.",
		inputSchema: { type: "object", properties: { url: { type: "string" } }, required: ["url"] },
		run: async (args: Record<string, unknown>) => {
			const out = await sh("shell", "am", "start", "-a", "android.intent.action.VIEW", "-d", String(args.url));
			return { launched: out.includes("Starting"), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_openshot",
		description: "Open a URL then screencap once the browser holds focus (polls ~4s, no fixed sleeps). One round trip — use instead of open+shot.",
		inputSchema: { type: "object", properties: { url: { type: "string" }, file: { type: "string" } }, required: ["url"] },
		run: async (args: Record<string, unknown>) => {
			const dev = await ensureDevice();
			await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${String(args.url)}`.quiet().nothrow();
			const file = String(args.file ?? `/tmp/pixel-shot-${Date.now()}.png`);
			const t0 = Date.now();
			while (Date.now() - t0 < 4000) {
				if (/mCurrentFocus=.*(fenix|chrome|WebView)/.test(await sh("shell", "dumpsys window"))) break;
				await Bun.sleep(150);
			}
			const png = await shBytes();
			await Bun.write(file, png);
			return { file, bytes: png.byteLength, ms: Date.now() - t0, focus: await currentFocus() };
		},
	},
	{
		name: "pixel_tap",
		description: "Tap at x,y. FOCUS DOCTRINE: lands on the focused window — always pixel_ui first, and check the returned focus. Returning focus is the covenant.",
		inputSchema: { type: "object", properties: { x: { type: "number" }, y: { type: "number" } }, required: ["x", "y"] },
		run: async (args: Record<string, unknown>) => {
			await sh("shell", "input", "tap", String(args.x), String(args.y));
			return { tapped: [args.x, args.y], focus: await currentFocus() };
		},
	},
	{
		name: "pixel_text",
		description: "Type text into the focused window. FOCUS DOCTRINE: returns focus so a blind type is immediately visible.",
		inputSchema: { type: "object", properties: { text: { type: "string" } }, required: ["text"] },
		run: async (args: Record<string, unknown>) => {
			await sh("shell", "input", "text", String(args.text).replace(/ /g, "%s"));
			return { typed: String(args.text).length, focus: await currentFocus() };
		},
	},
	{
		name: "pixel_key",
		description: "Send a keyevent (4=BACK, 66=ENTER, 111=ESC, 3=HOME). Returns focus.",
		inputSchema: { type: "object", properties: { keycode: { type: "number" } }, required: ["keycode"] },
		run: async (args: Record<string, unknown>) => {
			await sh("shell", "input", "keyevent", String(args.keycode));
			return { keyed: args.keycode, focus: await currentFocus() };
		},
	},
	{
		name: "pixel_ui",
		description: "Dump + return the UI hierarchy XML (tap targets, labels, bounds). The safe prelude to any tap.",
		inputSchema: { type: "object", properties: {} },
		run: async () => {
			await sh("shell", "uiautomator", "dump", "/sdcard/pixel-ui.xml");
			const dev = await ensureDevice();
			await $`adb -s ${dev} pull /sdcard/pixel-ui.xml /tmp/pixel-ui.xml`.quiet().nothrow();
			const xml = await Bun.file("/tmp/pixel-ui.xml").text();
			return { file: "/tmp/pixel-ui.xml", bytes: xml.length, xml: xml.slice(0, 60000) };
		},
	},
	{
		name: "pixel_focus",
		description: "Which window owns the screen right now.",
		inputSchema: { type: "object", properties: {} },
		run: async () => ({ focus: await currentFocus() }),
	},
	{
		name: "flotilla_float",
		description: "Float a URL in a freeform window on the phone (flotilla: picture-in-picture card over the user's SSH session). Uses the flotilla CLI so the alpha-tax levers (windowingMode 5) stay in one place.",
		inputSchema: { type: "object", properties: { url: { type: "string" } }, required: ["url"] },
		run: async (args: Record<string, unknown>) => {
			const out = await $`flotilla open ${String(args.url)}`.quiet().nothrow().text();
			return { floated: !out.includes("error"), out: out.trim().slice(0, 400), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_floaty",
		description: "Aesthetic message delivery: float a pixel-font (DepartureMono) message card over any app on the Pixel. The fleet's visual channel — deliver status, results, or nags with lo-fi techy styling instead of a plain toast. Optionally dark or light card, custom accent, auto-dismiss.",
		inputSchema: {
			type: "object",
			properties: {
				message: { type: "string", description: "The message to deliver (rendered large, DepartureMono pixel font)" },
				sub: { type: "string", description: "Optional smaller sub-line under the message" },
				accent: { type: "string", description: "accent color hex (default #7CFC00 terminal green)" },
				secs: { type: "number", description: "auto-dismiss seconds (default 0 = stays until flotilla close)" },
			},
			required: ["message"],
		},
		run: async (args: Record<string, unknown>) => {
			const msg = String(args.message).slice(0, 400);
			const sub = String(args.sub ?? "").slice(0, 200);
			const accent = /^#[0-9a-fA-F]{3,8}$/.test(String(args.accent ?? "")) ? String(args.accent) : "#7CFC00";
			const secs = Number(args.secs ?? 0);
			// embed the pixel font from the flotilla assets dir (base64 into the page — no font install needed)
			let fontB64 = "";
			try { fontB64 = (await Bun.file(`${import.meta.dir}/../assets/fonts/DepartureMono-Regular.otf`).bytes()).toBase64(); } catch { /* falls back to monospace */ }
			const html = floatyCardHtml({ message: msg, sub, accent, secs, fontB64 });
			// serve the card from this same HTTP surface, then float it on the phone
			const route = `/floaty/${Date.now()}`;
			floatyPages.set(route, html);
			const dev = await ensureDevice();
			await $`flotilla open http://${await hostIp()}:${PORT_FLOATY}${route}`.quiet().nothrow().text();
			return { floated: true, route, focus: await currentFocus(), font: fontB64 ? "DepartureMono" : "fallback-monospace" };
		},
	},
	{
		name: "pixel_shell",
		description: "Run an arbitrary adb shell command string on the Pixel and return stdout (≤60KB). The audit escape hatch: package/process/settings forensics past the focused tools. The string is tokenized locally (quotes respected) and passed as argv to adb shell — no remote shell, no 2>&1 merging; keep streams clean.",
		inputSchema: { type: "object", properties: { command: { type: "string", description: "shell command as a tokenizable string, e.g. 'pm list packages 3 grep termius' or 'settings get global http_proxy'" }, mergeErr: { type: "boolean", description: "append stderr to stdout (2>&1). Default false — stream discipline." } }, required: ["command"] },
		run: async (args: Record<string, unknown>) => {
			const cmd = need(args, "command", "pixel_shell");
			const mergeErr = args.mergeErr === true;
			const argv = splitCmd(cmd);
			const proc = Bun.spawn({ cmd: ["adb", "-s", await ensureDevice(), "shell", ...argv], stdout: "pipe", stderr: mergeErr ? "pipe" : "pipe" });
			const [out, err] = await Promise.all([new Response(proc.stdout).text(), new Response(proc.stderr).text()]);
			const merged = mergeErr ? `${out}\n${err}` : out;
			return { out: merged.slice(0, 60000), truncated: merged.length > 60000, stderr: err.trim().slice(0, 4000), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_package",
		description: "Deep-dive one package: version, install/update times, enabled/stopped flags, dataDir, granted permissions. The package-state prelude for any app audit (Termius-style).",
		inputSchema: { type: "object", properties: { pkg: { type: "string", description: "package name, e.g. com.server.auditor.ssh.client" } }, required: ["pkg"] },
		run: async (args: Record<string, unknown>) => {
			const pkg = String(args.pkg);
			const out = await sh("shell", "dumpsys", "package", pkg);
			const lines = out.split("\n");
			const pick = (re: RegExp) => { const m = lines.find((l) => re.test(l)); return m?.trim() ?? null; };
			const perms = lines.filter((l) => /granted=true/.test(l)).map((l) => l.trim().split(":")[0]).slice(0, 40);
			return {
				pkg,
				version: pick(/versionName=|versionCode=/),
				firstInstall: pick(/firstInstallTime=/),
				lastUpdate: pick(/lastUpdateTime=/),
				state: pick(/installed=|enabled=|stopped=|notLaunched=/),
				dataDir: pick(/dataDir=/),
				apkPath: pick(/codePath=/),
				grantedPermissions: perms,
			};
		},
	},
	{
		name: "pixel_appstate",
		description: "Live app state: process alive? foreground? RSS? recent ANR/crash entries in dropbox for the package. Answers 'is it running, what is it doing, has it been crashing' in one call.",
		inputSchema: { type: "object", properties: { pkg: { type: "string" }, process: { type: "string", description: "process name fragment to grep (defaults to pkg substring)" } }, required: ["pkg"] },
		run: async (args: Record<string, unknown>) => {
			const pkg = String(args.pkg);
			const frag = String(args.process ?? pkg);
			const ps = await sh("shell", "ps", "-A");
			const procs = ps.split("\n").filter((l) => l.includes(frag)).map((l) => l.trim()).slice(0, 6);
			const dropbox = await sh("shell", "dumpsys", "dropbox", "--print");
			const dbLines = dropbox.split("\n");
			const crashes: Array<Record<string, string>> = [];
			for (let i = 0; i < dbLines.length; i++) {
				if (dbLines[i]!.includes(`Package: ${pkg}`)) {
					const block = dbLines.slice(Math.max(0, i - 8), i + 6).join("\n");
					const ts = block.match(/Timestamp: ([^\n]+)/)?.[1] ?? "?";
					const kind = block.match(/^\s*(java\.lang\.[A-Za-z.]+|Process:|Activity:|System.*)?/m)?.[0]?.trim() ?? "";
					const cause = block.match(/(java\.lang\.[A-Za-z.]+|android\.os\.[A-Za-z.]+|reason: [^\n]+)/)?.[1] ?? "";
					crashes.push({ ts: ts.slice(0, 33), kind, cause });
				}
			}
			return { pkg, processAlive: procs.length > 0, processes: procs.slice(0, 3), recentDropbox: crashes.slice(-5), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_shared",
		description: "List a package's shared-storage footprint without root: /sdcard/Android/data/<pkg> tree + matching files under /sdcard (exports, backups, config dumps). App-private data stays private; this finds what the app itself wrote out.",
		inputSchema: { type: "object", properties: { pkg: { type: "string" }, pattern: { type: "string", description: "optional filename pattern for the /sdcard find, e.g. '*termius*'" } }, required: ["pkg"] },
		run: async (args: Record<string, unknown>) => {
			const pkg = String(args.pkg);
			const pat = String(args.pattern ?? `*${pkg.split(".").pop()}*`);
			const dataTree = await sh("shell", "ls", "-laR", `/sdcard/Android/data/${pkg}/`);
			const findOut = await sh("shell", "find", "/sdcard", "-maxdepth", "4", "-iname", pat);
			return { pkg, androidData: dataTree.slice(0, 8000) || "(empty / not present)", matches: findOut.trim().split("\n").filter(Boolean).slice(0, 40) };
		},
	},
	{
		name: "pixel_clipboard",
		description: "Set or get Pixel clipboard safely with parcel size guard (caps at 800KB to prevent Android Binder TransactionTooLargeException crashes in apps like Termius).",
		inputSchema: {
			type: "object",
			properties: {
				action: { type: "string", enum: ["get", "set"], description: "get or set clipboard" },
				text: { type: "string", description: "text to set (guarded to <800KB)" }
			},
			required: ["action"]
		},
		run: async (args: Record<string, unknown>) => {
			const action = String(args.action);
			if (action === "set") {
				const text = String(args.text ?? "");
				const bytes = Buffer.byteLength(text, "utf8");
				const MAX_PARCEL_BYTES = 800 * 1024; // 800KB safety ceiling well under ~1MB Binder transaction limit
				if (bytes > MAX_PARCEL_BYTES) {
					throw new Error(`Clipboard text too large (${bytes} bytes > 800KB cap) — rejected to prevent TransactionTooLargeException`);
				}
				const dev = await ensureDevice();
				const b64 = Buffer.from(text, "utf8").toString("base64");
				await $`adb -s ${dev} shell "echo -n ${b64} | base64 -d | cmd clipboard set-text"`.quiet().nothrow();
				return { ok: true, action: "set", bytes, focus: await currentFocus() };
			} else {
				const dev = await ensureDevice();
				const clip = await $`adb -s ${dev} shell cmd clipboard get-text`.quiet().nothrow().text();
				return { ok: true, action: "get", text: clip.trim().slice(0, 10000), bytes: clip.length, focus: await currentFocus() };
			}
		}
	},
	{
		name: "pixel_settings",
		description: "Read or write an Android system setting (global/system/secure) via adb. The about:config/settings primitive: change a pref, then pixel_foreground to restart the app.",
		inputSchema: { type: "object", properties: { namespace: { type: "string", enum: ["global", "system", "secure"] }, key: { type: "string" }, value: { type: "string", description: "value to set (omit to get)" } }, required: ["namespace", "key"] },
		run: async (args: Record<string, unknown>) => {
			const ns = need(args, "namespace", "pixel_settings");
			const key = need(args, "key", "pixel_settings");
			const value = args.value;
			const dev = await ensureDevice();
			if (value !== undefined) {
				await $`adb -s ${dev} shell settings put ${ns} ${key} ${String(value)}`.quiet().nothrow().text();
				const readback = await $`adb -s ${dev} shell settings get ${ns} ${key}`.quiet().nothrow().text();
				return { ok: true, action: "set", ns, key, value: String(value), readback: readback.trim(), focus: await currentFocus() };
			}
			const out = await $`adb -s ${dev} shell settings get ${ns} ${key}`.quiet().nothrow().text();
			return { ok: true, action: "get", ns, key, value: out.trim(), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_foreground",
		description: "Force-stop and relaunch a package (the 'change prefs then restart the app' primitive), or just bring the running app to foreground. Safest way to apply an about:config/settings change that needs a clean process.",
		inputSchema: { type: "object", properties: { pkg: { type: "string" }, activity: { type: "string", description: "optional explicit Activity; default: resolve main via resolve-activity" }, restart: { type: "boolean", description: "force-stop before relaunch (true = clean restart picks up new prefs). Default true." } }, required: ["pkg"] },
		run: async (args: Record<string, unknown>) => {
			const pkg = need(args, "pkg", "pixel_foreground");
			const restart = args.restart !== false;
			const dev = await ensureDevice();
			let target = args.activity ? String(args.activity) : null;
			if (!target) {
				const dump = await $`adb -s ${dev} shell cmd package resolve-activity --brief ${pkg}`.quiet().nothrow().text();
				target = (dump.split("\n").map((l) => l.trim()).find((l) => l.startsWith(pkg)) ?? `${pkg}/.App`).trim();
			}
			if (restart) { await $`adb -s ${dev} shell am force-stop ${pkg}`.quiet().nothrow(); await Bun.sleep(500); }
			await $`adb -s ${dev} shell am start -n ${target}`.quiet().nothrow().text();
			const dump = await sh("shell", "dumpsys activity activities 2>/dev/null | grep -m1 ResumedActivity");
			return { pkg, target, restarted: restart, resumed: dump.trim(), focus: await currentFocus() };
		},
	},
	{
		name: "pixel_browser",
		description: "Open a URL in an EXPLICIT browser component and wait for page CONTENT (not focus), then screenshot + return URL/page state. Fixes pixel_open's race where focus is captured before the page paints.",
		inputSchema: { type: "object", properties: { url: { type: "string" }, browser: { type: "string", description: "package name to force (default: resolve VIEW intent)" }, file: { type: "string", description: "screenshot out (default /tmp/pixel-browser-<ts>.png)" }, waitMs: { type: "number", description: "poll settle budget (default 6000)" } }, required: ["url"] },
		run: async (args: Record<string, unknown>) => {
			const url = need(args, "url", "pixel_browser");
			const dev = await ensureDevice();
			const file = String(args.file ?? `/tmp/pixel-browser-${Date.now()}.png`);
			const waitMs = Number(args.waitMs ?? 6000);
			const browser = args.browser ? String(args.browser) : null;
			if (browser) await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${url} -p ${browser}`.quiet().nothrow().text();
			else await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${url}`.quiet().nothrow().text();
			const t0 = Date.now();
			let paints = 0;
			while (Date.now() - t0 < waitMs) {
				const focus = await currentFocus();
				if (/(fenix|chrome|WebView)/.test(focus)) paints++;
				if (paints >= 3) break;
				await Bun.sleep(400);
			}
			const png = await shBytes();
			await Bun.write(file, png);
			return { file, bytes: png.byteLength, url, focus: await currentFocus(), settled: paints >= 3 };
		},
	},
	{
		name: "pixel_intent",
		description: "Fire an arbitrary Android intent with an explicit component and extras on the Pixel. The deep-link escape hatch: launcher, settings panes, per-app document providers, ACTION_VIEW with mime.",
		inputSchema: { type: "object", properties: { action: { type: "string" }, component: { type: "string", description: "'pkg/activity' to target explicitly" }, data: { type: "string", description: "data URI" }, type: { type: "string", description: "MIME type" }, flags: { type: "string", description: "hex/category flags e.g. '0x10000000'", extras: { type: "object", description: "flat string-extras key->value" } } }, required: ["action"] },
		run: async (args: Record<string, unknown>) => {
			const action = need(args, "action", "pixel_intent");
			const dev = await ensureDevice();
			const parts = ["start"];
			if (args.component) parts.push("-n", String(args.component));
			if (args.data) parts.push("-d", String(args.data));
			if (args.type) parts.push("-t", String(args.type));
			if (args.flags) parts.push("-f", String(args.flags));
			if (args.extras && typeof args.extras === "object") for (const [k, v] of Object.entries(args.extras as Record<string, unknown>)) parts.push("--es", k, String(v));
			parts.push("-a", action);
			const p = Bun.spawn({ cmd: ["adb", "-s", dev, "shell", "am", ...parts], stdout: "pipe", stderr: "pipe" });
			const [so, se] = await Promise.all([new Response(p.stdout).text(), new Response(p.stderr).text()]);
			return { ok: !se.includes("Error"), out: (so || se).trim().slice(0, 1000), action, focus: await currentFocus() };
		},
	},
	{
		name: "pixel_install",
		description: "Install an APK on the Pixel via adb install (-r -g: replace, grant all runtime perms). The deploy primitive for pushing an updated bundle to the phone.",
		inputSchema: { type: "object", properties: { path: { type: "string" }, replace: { type: "boolean", description: "-r replace (default true)" } }, required: ["path"] },
		run: async (args: Record<string, unknown>) => {
			const path = need(args, "path", "pixel_install");
			const dev = await ensureDevice();
			const replace = args.replace !== false;
			const out = await $`adb -s ${dev} install ${replace ? "-r" : ""} -g ${path}`.quiet().nothrow().text();
			const ok = /Success/i.test(out);
			return { ok, out: out.trim().slice(0, 2000), path, focus: await currentFocus() };
		},
	},
	{
		name: "serve",
		description: "Serve a file from the box to the phone via an ephemeral HTTP server. Best for delivering self-contained HTML pages that don't depend on a local bridge. Returns the URL.",
		inputSchema: { type: "object", properties: { path: { type: "string", description: "Absolute path to file on the box" }, name: { type: "string", description: "Optional URL slug (defaults to basename)" } }, required: ["path"] },
		run: async (args: Record<string, unknown>) => {
			const filePath = String(args.path);
			const name = String(args.name ?? filePath.split("/").pop() ?? "file");
			const body = await Bun.file(filePath).bytes();
			const contentType = filePath.endsWith(".html") ? "text/html; charset=utf-8" : filePath.endsWith(".json") ? "application/json" : "application/octet-stream";
			const route = `/serve/${name}`;
			servedFiles.set(name, { body, contentType });
			// port is resolved lazily after serveServer is created (circular init)
			const port = (serveServer as unknown as { port?: number }).port ?? 0;
			const url = `${await hostIp()}:${port}`;
			return { ok: true, url: `${url}${route}`, size: body.byteLength, contentType };
		},
	},
	{
		name: "pixel_view",
		description: "Open a URL on the phone, wait for browser to settle, capture a screenshot. Best for proving what the browser actually rendered.",
		inputSchema: { type: "object", properties: { url: { type: "string", description: "URL to open on the phone's browser" }, file: { type: "string", description: "output screenshot path" } }, required: ["url"] },
		run: async (args: Record<string, unknown>) => {
			const dev = await ensureDevice();
			const url = String(args.url);
			const file = String(args.file ?? `/tmp/pixel-view-${Date.now()}.png`);
			await $`adb -s ${dev} shell am start -a android.intent.action.VIEW -d ${url}`.quiet().nothrow();
			const t0 = Date.now();
			while (Date.now() - t0 < 8000) {
				if (/mCurrentFocus=.*(fenix|chrome|WebView)/.test(await sh("shell", "dumpsys window"))) break;
				await Bun.sleep(200);
			}
			const png = await shBytes();
			await Bun.write(file, png);
			return { file, bytes: png.byteLength, ms: Date.now() - t0, focus: await currentFocus(), url };
		},
	},
];

// ── /serve HTTP endpoint (ephemeral file server) ───────────────────────
const serveServer = serve({
	hostname: "0.0.0.0",
	port: 0,
	fetch: (req: Request) => {
		const url = new URL(req.url);
		const file = servedFiles.get(url.pathname.replace(/^\/serve\//, ""));
		if (!file) return new Response("not found", { status: 404 });
		return new Response(file.body, { headers: { "content-type": file.contentType, "cache-control": "no-cache" } });
	},
});

// ── streamable-HTTP JSON-RPC surface (taas.ts shape) ────────────────────
const server = serve({
	hostname: "127.0.0.1",
	port: PORT,
	async fetch(request) {
		const url = new URL(request.url);
		if (url.pathname.startsWith("/floaty/")) {
			const html = floatyPages.get(url.pathname);
			if (!html) return new Response("expired", { status: 404 });
			return new Response(html, { headers: { "content-type": "text/html; charset=utf-8" } });
		}
		if (url.pathname === "/health") {
			return Response.json({ ok: true, service: "flotilla-mcp", device });
		}
		if (url.pathname === "/mcp" && request.method === "POST") {
			const rpc = (await request.json()) as { id?: unknown; method?: string; params?: { name?: string; arguments?: Record<string, unknown> } };
			const reply = (result: unknown) => Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, result });
			if (rpc.method === "initialize")
				return reply({ protocolVersion: "2025-03-26", capabilities: { tools: {} }, serverInfo: { name: "flotilla-mcp", version: "1.0.0" } });
			if (rpc.method === "notifications/initialized") return new Response(null, { status: 202 });
			if (rpc.method === "tools/list")
				return reply({ tools: tools.map(({ name, description, inputSchema }) => ({ name, description, inputSchema })) });
			if (rpc.method === "tools/call") {
				const name = rpc.params?.name ?? "";
				const tool = tools.find((t) => t.name === name);
				if (!tool) return Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, error: { code: -32602, message: `unknown tool ${name}` } }, { status: 400 });
				try {
					return reply(content(await tool.run(rpc.params?.arguments ?? {})));
				} catch (err) {
					return reply(content({ error: err instanceof Error ? err.message : String(err) }, true));
				}
			}
			return Response.json({ jsonrpc: "2.0", id: rpc.id ?? null, error: { code: -32601, message: `unknown method ${rpc.method}` } }, { status: 400 });
		}
		return Response.json({ ok: false, error: "not found" }, { status: 404 });
	},
});

console.log(`flotilla-mcp: http://127.0.0.1:${PORT}/mcp (phone bridge, focus-covenant on)`);
