/** Master provider configuration — sources, client identity, limits. */

export interface UpstreamSource {
  /** Unique id; also the EPG namespace prefix for merged guides. */
  id: string;
  kind: "xtream" | "m3u" | "stalker";
  /** Display name shown in OwnTV's source list. */
  name: string;
  /** Base URL without trailing slash (xtream: http://host:port). */
  url: string;
  /** Xtream credentials; m3u sources may set url as the playlist URL instead. */
  username?: string;
  password?: string;
  /** M3U playlist URL when kind === "m3u". */
  playlistUrl?: string;
  /** Optional XMLTV URL for this source (m3u); xtream EPG comes from the API. */
  epgUrl?: string;
  /** Stalker portal MAC (defaults to a deterministic fake like a MAG250). */
  mac?: string;
  /** Per-source stream connection cap (defaults to global). */
  maxConnections?: number;
  /** Channel name prefix, e.g. "US · " — empty for none. */
  prefix?: string;
  /** Only include channels whose name matches this regex (curation). */
  filter?: string;
}

export interface Config {
  port: number;
  host: string;
  clientUsername: string;
  clientPassword: string;
  maxConnections: number;
  catalogTtlMs: number;
  epgRefreshMs: number;
  epgLookaheadDays: number;
  sources: UpstreamSource[];
  /** Path to velvet/plugins.json for the series catalog. */
  velvetPluginsPath: string;
  /** Argos search endpoint (ES-compatible) for the /search override. */
  argosUrl: string;
  /** Argos index to search across (defaults to live channel index). */
  argosIndex: string;
  /** TorBox API key for the VOD/series requestdl lane (env: PROVIDER_TORBOX_KEY). */
  torboxKey: string;
  /** FreeInference API key for the translate/embed lanes (env: PROVIDER_FREEINFERENCE_KEY). */
  freeinferenceKey: string;
  /** Upstream fetch timeouts (ms). All panel/EPG fetches share these — no magic literals. */
  fetchTimeoutMs: number;
  streamConnectTimeoutMs: number;
  proxyTimeoutMs: number;
}

// Repo root = provider/ parent, so the default velvet path works on any checkout.
const REPO_ROOT = new URL("..", import.meta.url).pathname;

export function defineConfig(env: Record<string, string | undefined>): Config {
  return {
    port: Number(env.PROVIDER_PORT ?? "8081"),
    host: env.PROVIDER_HOST ?? "0.0.0.0",
    clientUsername: env.PROVIDER_USER ?? "master",
    clientPassword: env.PROVIDER_PASS ?? "master",
    maxConnections: Number(env.PROVIDER_MAX_CONNECTIONS ?? "4"),
    catalogTtlMs: Number(env.PROVIDER_CATALOG_TTL_MS ?? 10 * 60 * 1000),
    epgRefreshMs: Number(env.PROVIDER_EPG_REFRESH_MS ?? 6 * 60 * 60 * 1000),
    epgLookaheadDays: Number(env.PROVIDER_EPG_LOOKAHEAD_DAYS ?? 2),
    sources: parseSources(env.PROVIDER_SOURCES ?? ""),
    velvetPluginsPath: env.PROVIDER_VELVET_PLUGINS ?? `${REPO_ROOT}velvet/plugins.json`,
    argosUrl: env.PROVIDER_ARGOS_URL ?? "http://127.0.0.1:25254",
    argosIndex: env.PROVIDER_ARGOS_INDEX ?? "hisense-intel-docs",
    // Fork's TorBoxCloud.DEFAULT_API_KEY is the fallback; real deployments override via env.
    torboxKey: env.PROVIDER_TORBOX_KEY ?? "8e35833d-2af1-474b-8031-b218299c73ae",
    freeinferenceKey: env.PROVIDER_FREEINFERENCE_KEY ?? "",
    fetchTimeoutMs: Number(env.PROVIDER_FETCH_TIMEOUT_MS ?? 30_000),
    streamConnectTimeoutMs: Number(env.PROVIDER_STREAM_CONNECT_TIMEOUT_MS ?? 15_000),
    proxyTimeoutMs: Number(env.PROVIDER_PROXY_TIMEOUT_MS ?? 60_000),
  };
}

/** PROVIDER_SOURCES="xtream|id=main|name=Main|url=http://1tv41.icu:8080|user=VMh8na|pass=793530;m3u|id=extras|name=Extras|playlist=https://..." */
export function parseSources(spec: string): UpstreamSource[] {
  if (!spec.trim()) return [];
  return spec.split(";").map((part) => {
    const fields: Record<string, string> = {};
    let positionalKind: string | undefined;
    part.split("|").forEach((kv, idx) => {
      const i = kv.indexOf("=");
      if (i < 0) {
        if (idx === 0) positionalKind = kv.trim();
        else fields[kv.trim()] = "true";
      } else {
        // trimStart only: values may carry meaningful trailing whitespace (e.g. "US · " prefix)
        fields[kv.slice(0, i).trim()] = kv.slice(i + 1).trimStart();
      }
    });
    const kind = (fields.kind ?? positionalKind ?? "xtream") as UpstreamSource["kind"];
    if (kind === "m3u") {
      return {
        id: fields.id ?? "m3u",
        kind,
        name: fields.name ?? "M3U",
        url: fields.url ?? "",
        playlistUrl: fields.playlist ?? fields.url,
        epgUrl: fields.epg ?? undefined,
        prefix: fields.prefix ?? undefined,
        filter: fields.filter ?? undefined,
        maxConnections: fields.max ? Number(fields.max) : undefined,
      };
    }
    if (kind === "stalker") {
      return {
        id: fields.id ?? "stalker",
        kind,
        name: fields.name ?? "Stalker",
        url: fields.url ?? "",
        mac: fields.mac ?? undefined,
        prefix: fields.prefix ?? undefined,
        filter: fields.filter ?? undefined,
        maxConnections: fields.max ? Number(fields.max) : undefined,
      };
    }
    return {
      id: fields.id ?? "xtream",
      kind,
      name: fields.name ?? "Xtream",
      url: fields.url ?? "",
      username: fields.user,
      password: fields.pass,
      prefix: fields.prefix ?? undefined,
      filter: fields.filter ?? undefined,
      maxConnections: fields.max ? Number(fields.max) : undefined,
    };
  });
}