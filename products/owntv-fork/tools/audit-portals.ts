#!/usr/bin/env -S bun run
/**
 * audit-portals.ts — re-audit fleet_portals.csv against current reality.
 *
 * For every live portal row it classifies (like the VLC-audit):
 *   unreachable/timeout  -> commented out as #dead-YYYYMMDD
 *   API answers but no playable stream -> status=no-streams
 *   a stream URL serves media (2xx)    -> status=stream-ok
 *
 * Usage: bun tools/audit-portals.ts [path-to-fleet_portals.csv]
 * Default: the fleet-tv SSOT.
 */

const t0 = Date.now();
const CSV =
  process.argv[2] ??
  "/home/toxic/pens/fleet/devices/android-fleet/fleet-tv/app/src/main/assets/fleet_portals.csv";
const TODAY = "20261116";

const CONNECT_MS = 8000;
const STREAM_MS = 8000;
const CONCURRENCY = 12;
const UA = "VLC/3.0.20";

async function probe(base: string, user: string, pass: string): Promise<"ok" | "no-streams" | "dead"> {
  const q = `username=${encodeURIComponent(user)}&password=${encodeURIComponent(pass)}`;
  let streamsRes: Response;
  try {
    streamsRes = await fetch(`${base}/player_api.php?${q}&action=get_live_streams`, {
      signal: AbortSignal.timeout(CONNECT_MS),
      headers: { "user-agent": UA },
      redirect: "follow",
    });
  } catch {
    return "dead";
  }
  if (!streamsRes.ok) return "dead";
  let streams: unknown;
  try {
    streams = await streamsRes.json();
  } catch {
    return "no-streams";
  }
  if (!Array.isArray(streams) || streams.length === 0) return "no-streams";
  const sid = String((streams[0] as Record<string, unknown>)?.stream_id ?? "");
  if (!sid) return "no-streams";
  for (const ext of ["m3u8", "ts"]) {
    const url = `${base}/live/${user}/${pass}/${sid}.${ext}`;
    try {
      const res = await fetch(url, {
        signal: AbortSignal.timeout(STREAM_MS),
        headers: { "user-agent": UA },
        redirect: "follow",
      });
      if (res.ok || res.status === 206) return "ok";
    } catch {
      /* try next ext / count as no-streams */
    }
  }
  return "no-streams";
}

const src = await Bun.file(CSV).text();
const lines = src.split("\n");
const results = new Array(lines.length).fill(null) as (string | null)[];

const jobs: { idx: number; base: string; user: string; pass: string }[] = [];
for (let i = 0; i < lines.length; i++) {
  const l = lines[i];
  const t = l.trim();
  if (!t || t.startsWith("#")) continue;
  const [url, user, pass] = t.split(",");
  if (!url || !user || !pass) continue;
  jobs.push({ idx: i, base: url.trimEnd("/"), user: user.trim(), pass: pass.trim() });
}

let done = 0;
for (let i = 0; i < jobs.length; i += CONCURRENCY) {
  const batch = jobs.slice(i, i + CONCURRENCY);
  await Promise.all(
    batch.map(async (j) => {
      const r = await probe(j.base, j.user, j.pass);
      results[j.idx] = r;
      done++;
      process.stderr.write(`\r[audit] ${done}/${jobs.length} ${r}`);
    }),
  );
}
process.stderr.write("\n");

const todayZ = new Date().toISOString().slice(0, 10);
const out = lines.map((l, i) => {
  if (results[i] === null) return l;
  const r = results[i]!;
  const [url, user, pass] = l.split(",");
  if (r === "dead") return `#dead-${TODAY} ${url},${user},${pass}`;
  const st = r === "ok" ? "stream-ok" : r;
  return `${url},${user},${pass},${st}`;
});

await Bun.write(CSV, out.join("\n"));

let ok = 0, nostreams = 0, dead = 0;
for (const r of results) {
  if (r === "ok") ok++;
  else if (r === "no-streams") nostreams++;
  else if (r === "dead") dead++;
}
console.log(`[audit] total=${jobs.length} ok=${ok} no-streams=${nostreams} dead=${dead} in ${((Date.now() - t0) / 1000).toFixed(1)}s`);
console.log(`[audit] wrote ${CSV}`);
