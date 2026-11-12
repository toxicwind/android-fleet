/** Master provider configuration — sources, client identity, limits. */

export interface UpstreamSource {
  /** Unique id; also the EPG namespace prefix for merged guides. */
  id: string;
  kind: "xtream" | "m3u";
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
}

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
    velvetPluginsPath: env.PROVIDER_VELVET_PLUGINS ?? "/home/toxic/pens/fleet/devices/android-fleet/products/owntv-fork/velvet/plugins.json",
    argosUrl: env.PROVIDER_ARGOS_URL ?? "http://127.0.0.1:25254",
    argosIndex: env.PROVIDER_ARGOS_INDEX ?? "hisense-intel-docs",
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