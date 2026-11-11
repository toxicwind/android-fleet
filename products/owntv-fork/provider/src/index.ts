/**
 * Master provider — universal IPTV gateway that fronts OwnTV (and any Xtream
 * client) with a single clean panel: player_api.php + /live/ streams + xmltv.php
 * EPG. Behind it, any number of upstreams: Xtream panels, M3U playlists, Stalker.
 *
 * OwnTV speaks Xtream Codes natively, so the gateway's surface IS Xtream:
 *   GET /player_api.php?username=&password=&action=get_live_categories
 *   GET /player_api.php?username=&password=&action=get_live_streams
 *   GET /player_api.php?username=&password=&action=get_short_epg&stream_id=&limit=
 *   GET /player_api.php?username=&password=            (bare = account info)
 *   GET /live/{user}/{pass}/{id}.m3u8|.ts              (stream passthrough)
 *   GET /xmltv.php?username=&password=                 (merged EPG)
 *
 * Upstreams are declared in config and normalized into a single virtual channel
 * list. Streams route through us: connection caps, route-busting retries and
 * backoff live here, not on the panel.
 */
import { defineConfig } from "./config.ts";
import { loadUpstreams, type VirtualChannel } from "./upstreams.ts";
import { scheduleEpgRefresh, epgForChannel, epgXmltv } from "./epg.ts";
import { handleStream } from "./stream.ts";
import { loadVelvetCatalog, type VelvetCatalog } from "./velvet.ts";
import { searchArgos, toProviderResults } from "./argos.ts";
import { runLua } from "./lua-runner.ts";

const config = defineConfig(process.env);
const log = (msg: string, ...rest: unknown[]) =>
  console.log(`[provider ${new Date().toISOString().slice(11, 19)}] ${msg}`, ...rest);

// --- shared state -----------------------------------------------------------

let channels: VirtualChannel[] = [];
let categories: { category_id: string; category_name: string; parent_id: number }[] = [];
let loadedAt = 0;
let loading: Promise<void> | null = null;

async function refreshCatalog(force = false): Promise<void> {
  if (loading) return loading;
  if (!force && loadedAt && Date.now() - loadedAt < config.catalogTtlMs) return;
  loading = (async () => {
    try {
      const result = await loadUpstreams(config);
      channels = result.channels;
      categories = result.categories;
      loadedAt = Date.now();
      log(`catalog: ${channels.length} channels / ${categories.length} categories from ${config.sources.length} source(s)`);
    } catch (err) {
      log("catalog refresh failed", err);
    } finally {
      loading = null;
    }
  })();
  return loading;
}

// --- Xtream API surface -----------------------------------------------------

function authOk(url: URL): boolean {
  const u = url.searchParams.get("username");
  const p = url.searchParams.get("password");
  return u === config.clientUsername && p === config.clientPassword;
}

function json(res: Response, body: unknown): Response {
  return new Response(JSON.stringify(body), {
    headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
  });
}

function accountInfo(): Record<string, unknown> {
  return {
    user_info: {
      username: config.clientUsername,
      password: config.clientPassword,
      message: "OK",
      auth: 1,
      status: "Active",
      exp_date: String(Math.floor(Date.now() / 1000) + 365 * 86400),
      is_trial: "0",
      active_cons: "0",
      max_connections: String(config.maxConnections),
      allowed_output_formats: ["m3u8", "ts"],
    },
    server_info: {
      url: config.host,
      port: String(config.port),
      https_port: "0",
      server_protocol: "http",
      timezone: "UTC",
      timestamp_now: Math.floor(Date.now() / 1000),
      time_now: new Date().toISOString().replace("T", " ").slice(0, 19),
    },
  };
}

async function handleApi(url: URL): Promise<Response> {
  if (!authOk(url)) return json(new Response(null, { status: 401 }), { user_info: { auth: 0, status: "Unauthorized" } });
  const action = url.searchParams.get("action") ?? "";
  await refreshCatalog();
  switch (action) {
    case "get_live_categories":
      return json(new Response(), categories);
    case "get_live_streams": {
      const cat = url.searchParams.get("category_id");
      const rows = channels
        .filter((c) => !cat || c.categoryId === cat)
        .map((c, i) => ({
          num: i + 1,
          name: c.name,
          stream_type: "live",
          stream_id: c.id,
          stream_icon: c.icon ?? "",
          epg_channel_id: c.epgId ?? null,
          added: String(Math.floor(c.addedAt / 1000)),
          category_id: c.categoryId,
          custom_sid: "",
          tv_archive: c.catchup ? 1 : 0,
          direct_source: "",
          tv_archive_duration: c.catchup ? 7 : 0,
        }));
      return json(new Response(), rows);
    }
    case "get_short_epg": {
      const streamId = url.searchParams.get("stream_id") ?? "";
      const limit = Number(url.searchParams.get("limit") ?? "4");
      const channel = channels.find((c) => String(c.id) === streamId);
      if (!channel) return json(new Response(), []);
      const programmes = await epgForChannel(channel, limit);
      return json(new Response(), programmes);
    }
    case "get_vod_categories":
    case "get_vod_streams":
    case "get_series_categories":
    case "get_series":
    case "get_series_info":
      return json(new Response(), []);
    case "":
      return json(new Response(), accountInfo());
    default:
      return json(new Response(), { error: "unknown action" });
  }
}

// --- routing ----------------------------------------------------------------

const server = Bun.serve({
  port: config.port,
  hostname: "0.0.0.0",
  async fetch(req) {
    const url = new URL(req.url);
    const path = url.pathname;

    if (path === "/player_api.php" || path === "/player_api.php/") return handleApi(url);
    if (path === "/xmltv.php") {
      if (!authOk(url)) return new Response("Unauthorized", { status: 401 });
      await refreshCatalog();
      const xml = await epgXmltv(channels, config);
      return new Response(xml, { headers: { "content-type": "application/xml; charset=utf-8" } });
    }
    if (path.startsWith("/live/") || path.startsWith("/movie/") || path.startsWith("/series/")) {
      await refreshCatalog();
      return handleStream(req, url, channels, config);
    }
    if (path === "/health") {
      return new Response(JSON.stringify({ ok: true, channels: channels.length, loadedAt }), {
        headers: { "content-type": "application/json" },
      });
    }
    if (path === "/run" && req.method === "POST") {
      const body = (await req.json().catch(() => ({}))) as { script?: string; timeoutMs?: number };
      if (!body.script) return new Response(JSON.stringify({ ok: false, error: "missing script" }), { status: 400, headers: { "content-type": "application/json" } });
      const result = await runLua({ script: body.script, timeoutMs: body.timeoutMs }, config);
      return new Response(JSON.stringify(result), { headers: { "content-type": "application/json" } });
    }
    return new Response("not found", { status: 404 });
  },
});

// --- lifecycle --------------------------------------------------------------

await refreshCatalog(true);
scheduleEpgRefresh(config, () => refreshCatalog(false));
log(`master provider on :${config.port} — ${config.sources.length} upstream(s), ${config.clientUsername}@${config.host}`);