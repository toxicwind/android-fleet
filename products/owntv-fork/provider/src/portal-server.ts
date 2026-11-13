/**
 * Stalker portal.php SERVING surface — makes the unified Xtream+M3U+Stalker
 * catalog (already served as player_api.php + m3u) ALSO speak the MAG/Stalker
 * wire protocol, so a Stalker-only client (OwnTV Stalker source, MAG box) can
 * point at ONE panel and get the whole catalog.
 *
 * Wire format mirrors OwnTV core's StalkerClient.kt expectations exactly:
 *   handshake        -> {"js":{"token":"..."}}
 *   get_profile      -> {"js":{...}}
 *   get_genres       -> {"js":[{id,title},...]}
 *   get_all_channels -> {"js":[{id,name,number,cmd,logo,xmltv_id,tv_genre_id,...}]}
 *                      (array directly, or {"data":[...]} — OwnTV accepts both)
 *   create_link      -> {"js":{"cmd":"<playable url>"}}
 *
 * Channel `cmd` values are this provider's OWN /live/{user}/{pass}/{id}.m3u8
 * URLs, so create_link just echoes the cmd (it is already playable through the
 * same server). This is the same trick the provider's own Stalker upstream
 * client uses in reverse, and it keeps every channel on one origin.
 */
import type { Config } from "./config.ts";
import type { VirtualChannel } from "./upstreams.ts";

const TOKEN_TTL_MS = 12 * 60 * 60 * 1000; // 12h, like a real portal session
const sessions = new Map<string, { token: string; at: number }>();

function sessionFor(mac: string, config: Config): { token: string; at: number } {
  let s = sessions.get(mac);
  if (!s || Date.now() - s.at > TOKEN_TTL_MS) {
    // Deterministic per-MAC token: a real portal mints a random one, but the
    // client only needs it to round-trip Bearer auth. Stable = cacheable.
    let h = 2166136261;
    for (let i = 0; i < mac.length; i++) h = Math.imul(h ^ mac.charCodeAt(i), 16777619);
    s = { token: (h >>> 0).toString(36) + Date.now().toString(36).slice(-6), at: Date.now() };
    sessions.set(mac, s);
  }
  return s;
}

function js(body: unknown): Response {
  return new Response(JSON.stringify({ js: body }), {
    headers: { "content-type": "application/json; charset=utf-8", "cache-control": "no-store" },
  });
}

function authToken(url: URL): string | null {
  // Stalker sends the token as a Bearer header OR ?token= ; accept both.
  const q = url.searchParams.get("token");
  if (q && q.length > 0) return q;
  return null;
}

export function handlePortal(
  url: URL,
  req: Request,
  channels: VirtualChannel[],
  config: Config,
): Response {
  const type = url.searchParams.get("type") ?? "";
  const action = url.searchParams.get("action") ?? "";
  const mac = url.searchParams.get("mac") ?? "AA:BB:CC:DD:EE:00";

  // handshake is the ONLY unauthenticated call (it mints the token).
  if (type === "stb" && action === "handshake") {
    const s = sessionFor(mac, config);
    return js({ token: s.token, stb_type: "MAG250", stb_version: "1.0" });
  }

  const token = authToken(url);
  const s = sessions.get(mac);
  if (!token || !s || token !== s.token) {
    return new Response("Authorization failed", { status: 401 });
  }

  const origin = url.origin;
  const liveBase = `${origin}/live/${config.clientUsername}/${config.clientPassword}`;

  switch (`${type}:${action}`) {
    case "stb:get_profile": {
      return js({
        id: mac,
        name: "Master Portal",
        mac,
        status: "on",
        phone: "",
        account_info: { status: "on", end_date: "2035-01-01 00:00:00" },
      });
    }
    case "itv:get_genres": {
      const seen = new Map<string, string>();
      for (const c of channels) {
        if (!seen.has(c.categoryId)) seen.set(c.categoryId, c.categoryName);
      }
      return js([...seen.entries()].map(([id, title]) => ({ id, title })));
    }
    case "itv:get_all_channels": {
      const out = channels.map((c, idx) => ({
        id: String(c.id),
        name: c.name,
        number: String(idx + 1),
        cmd: `${liveBase}/${c.id}.m3u8`,
        logo: c.icon ?? "",
        xmltv_id: c.epgId ?? "",
        tv_genre_id: c.categoryId,
        tv_archive: c.catchup ? 1 : 0,
        tv_archive_duration: c.catchupDays ?? 0,
      }));
      return js(out);
    }
    case "itv:create_link": {
      const cmd = url.searchParams.get("cmd");
      if (!cmd) return js({ cmd: "" });
      // cmd is one of OUR /live/ URLs — already playable through this server.
      return js({ cmd });
    }
    default:
      return js({ error: "unknown action" });
  }
}