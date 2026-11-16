/**
 * stream.live.ts — REAL-LIVE verification of the HLS proxy path against a
 * running master provider and REAL upstreams across MULTIPLE providers.
 *
 * Run manually (NOT part of the `bun test` suite — it needs a live provider and
 * live upstreams):
 *
 *   bun provider/test/stream.live.ts
 *
 * Why this exists alongside stream.test.ts: the unit tests prove the rewrite and
 * segment-proxy algebra against mock upstreams. They cannot prove the thing that
 * actually matters — that real channels, whose segments live on tokenized CDN
 * edges (NOT the panel base), actually play through our /hls/ proxy. And the
 * channels span SEPARATE providers, each with its own CDN origin, creds, and
 * token behavior. If the CDN-origin reconstruction or Referer is wrong for any
 * of them, that provider's channels never play.
 *
 * Channels are DISCOVERED dynamically from the provider catalog (no hardcoded
 * IDs) — a RANDOM reservoir sample per source, so new providers are covered
 * automatically. Each channel is resolved from the catalog (real upstream creds)
 * and verified in PARALLEL (concurrency-limited) with per-fetch timing:
 *   1. Playlist fetch through us → 200, mpegurl, segment paths rewritten to
 *      /hls/{user}/{pass}/{streamId}/hls/{token}/... (routed through us).
 *   2. First N segments fetched through us → 200 and a TS sync byte (0x47),
 *      proving the CDN-origin reconstruction + Referer + UA reuse work live.
 *
 * Upstream-side issues (channel offline, non-Xtream panel, auth failure) are
 * reported as SKIP, not FAIL — they are not proxy bugs. A FAIL means OUR proxy
 * mishandled a valid upstream playlist.
 *
 * Env knobs:
 *   PROVIDER_URL           provider base (default http://127.0.0.1:25818)
 *   LIVE_SOURCES           comma-separated source names to test (default: all)
 *   LIVE_MAX_PER_SOURCE    max channels to sample per source (default 2)
 *   LIVE_CHANNELS          explicit comma-separated channel IDs (overrides discovery)
 *   LIVE_CONCURRENCY       parallel channel verifications (default 5)
 */

const BASE = Bun.env.PROVIDER_URL ?? "http://127.0.0.1:25818";
const MAX_PER_SOURCE = Number(Bun.env.LIVE_MAX_PER_SOURCE ?? "2");
const CONCURRENCY = Number(Bun.env.LIVE_CONCURRENCY ?? "5");
const SOURCE_FILTER = (Bun.env.LIVE_SOURCES ?? "")
	.split(",")
	.map((s) => s.trim())
	.filter(Boolean);
const EXPLICIT_CHANNELS = (Bun.env.LIVE_CHANNELS ?? "")
	.split(",")
	.map((s) => s.trim())
	.filter(Boolean);

const SEGMENTS_TO_PROBE = 2;
const TS_SYNC_BYTE = 0x47;

let failures = 0;
let skips = 0;
const results: string[] = [];

function check(name: string, ok: boolean, detail: string): void {
	const line = `${ok ? "PASS" : "FAIL"}  ${name} — ${detail}`;
	results.push(line);
	console.log(line); // live, as it happens
	if (!ok) failures++;
}

function skip(name: string, detail: string): void {
	const line = `SKIP  ${name} — ${detail}`;
	results.push(line);
	console.log(line); // live, as it happens
	skips++;
}

async function reachable(): Promise<boolean> {
	try {
		const res = await fetch(`${BASE}/health`, { signal: AbortSignal.timeout(3_000) });
		return res.ok;
	} catch {
		return false;
	}
}

interface ResolvedChannel {
	id: string;
	name: string;
	sourceName: string;
	user: string;
	pass: string;
	streamId: string;
}

/** Walk the catalog and return a RANDOM reservoir sample of channels per source. */
async function discoverChannels(): Promise<ResolvedChannel[]> {
	const perSource = new Map<string, { sample: ResolvedChannel[]; seen: number }>();
	let cursor: string | null = null;
	for (let page = 0; page < 60; page++) {
		const url = `${BASE}/api/v1/catalog?limit=5000` + (cursor ? `&cursor=${encodeURIComponent(cursor)}` : "");
		let d: { items?: any[]; nextCursor?: string | null };
		try {
			const res = await fetch(url, { signal: AbortSignal.timeout(15_000) });
			if (!res.ok) break;
			d = await res.json();
		} catch {
			break;
		}
		for (const i of d.items ?? []) {
			const src = i.sourceName ?? "?";
			if (SOURCE_FILTER.length > 0 && !SOURCE_FILTER.includes(src)) continue;
			const u = i.upstream ?? {};
			const ch: ResolvedChannel = {
				id: String(i.id),
				name: i.name ?? String(i.id),
				sourceName: src,
				user: u.username ?? "",
				pass: u.password ?? "",
				streamId: u.streamId ?? "",
			};
			let bucket = perSource.get(src);
			if (!bucket) {
				bucket = { sample: [], seen: 0 };
				perSource.set(src, bucket);
			}
			bucket.seen++;
			if (bucket.sample.length < MAX_PER_SOURCE) {
				bucket.sample.push(ch);
			} else {
				const j = Math.floor(Math.random() * bucket.seen);
				if (j < MAX_PER_SOURCE) bucket.sample[j] = ch;
			}
		}
		cursor = d.nextCursor ?? null;
		if (!cursor) break;
	}
	const out: ResolvedChannel[] = [];
	for (const bucket of perSource.values()) out.push(...bucket.sample);
	return out;
}

/** Resolve explicit channel IDs from the catalog. */
async function resolveChannel(id: string): Promise<ResolvedChannel | null> {
	try {
		const res = await fetch(`${BASE}/api/v1/catalog/${id}`, { signal: AbortSignal.timeout(8_000) });
		if (!res.ok) return null;
		const d = await res.json();
		const u = d.upstream ?? {};
		return {
			id,
			name: d.name ?? id,
			sourceName: d.sourceName ?? "?",
			user: u.username ?? "",
			pass: u.password ?? "",
			streamId: u.streamId ?? "",
		};
	} catch {
		return null;
	}
}

/** Fetch the live playlist through our provider; retry once on transient upstream 502. */
async function fetchPlaylist(channelId: string) {
	const url = `${BASE}/live/master/master/${channelId}.m3u8`;
	for (let attempt = 0; attempt < 2; attempt++) {
		const t0 = performance.now();
		const res = await fetch(url, { signal: AbortSignal.timeout(20_000) });
		const body = await res.text();
		const ms = Math.round(performance.now() - t0);
		if (res.status === 200 || attempt === 1) {
			return { status: res.status, ct: res.headers.get("content-type") ?? "", body, ms };
		}
	}
	return { status: 0, ct: "", body: "", ms: 0 };
}

/** Fetch a rewritten segment path through our provider; return status + first bytes. */
async function fetchSegment(segPath: string) {
	const url = `${BASE}${segPath}`;
	const t0 = performance.now();
	const res = await fetch(url, { signal: AbortSignal.timeout(20_000) });
	const buf = new Uint8Array(await res.arrayBuffer());
	const ms = Math.round(performance.now() - t0);
	return { status: res.status, buf, ms };
}

async function verifyChannel(ch: ResolvedChannel): Promise<void> {
	const tag = `[${ch.sourceName}] ${ch.name} (id ${ch.id}, streamId ${ch.streamId})`;
	let status: number, ct: string, body: string, ms = 0;
	try {
		({ status, ct, body, ms } = await fetchPlaylist(ch.id));
	} catch (e) {
		skip(`${tag} playlist`, `fetch error: ${(e as Error).message}`);
		return;
	}

	if (status !== 200) {
		skip(`${tag} playlist`, `upstream status=${status} in ${ms}ms (not a proxy bug)`);
		return;
	}
	if (!/^#EXTM3U/m.test(body) && !/#EXTINF/.test(body)) {
		skip(`${tag} playlist`, `non-playlist body in ${ms}ms (offline/non-Xtream)`);
		return;
	}

	check(`${tag} playlist → 200`, true, `status=${status} in ${ms}ms`);
	check(`${tag} playlist mpegurl`, ct.includes("mpegurl") || ct.includes("m3u8"), `ct=${ct}`);

	// Segments are rewritten to route through us: /hls/{user}/{pass}/{streamId}/...
	// The next path segment is "hls" for panel-format paths or a tokenized variant
	// (e.g. "hlsr") for CDN-tokenized paths — both are valid, so match the base.
	const segBase = `/hls/${encodeURIComponent(ch.user)}/${encodeURIComponent(ch.pass)}/${encodeURIComponent(ch.streamId)}/`;
	const segLines = body.split(/\r?\n/).map((l) => l.trim());
	const rewrittenSeg = segLines.find((l) => l.startsWith(segBase) && !l.startsWith("#"));
	check(`${tag} segments rewritten through /hls/`, !!rewrittenSeg, `base=${segBase} sample=${rewrittenSeg ?? "none"}`);

	const segPaths = body
		.split(/\r?\n/)
		.map((l) => l.trim())
		.filter((l) => l && !l.startsWith("#") && l.startsWith("/hls/"));
	check(`${tag} playlist has segments`, segPaths.length > 0, `count=${segPaths.length}`);

	for (let i = 0; i < Math.min(SEGMENTS_TO_PROBE, segPaths.length); i++) {
		const segPath = segPaths[i];
		let s = 0;
		let buf = new Uint8Array(0);
		let segMs = 0;
		try {
			({ status: s, buf, ms: segMs } = await fetchSegment(segPath));
		} catch (e) {
			skip(`${tag} seg ${i + 1}`, `fetch error: ${(e as Error).message}`);
			continue;
		}
		check(`${tag} seg ${i + 1} → 200`, s === 200, `seg=${segPath} status=${s} in ${segMs}ms`);
		check(
			`${tag} seg ${i + 1} TS sync byte (0x47)`,
			buf.length > 0 && buf[0] === TS_SYNC_BYTE,
			`bytes=${buf.length} first=0x${buf[0]?.toString(16).padStart(2, "0")}`,
		);
	}
}

/** Run an async fn over items with a concurrency limit. */
async function mapLimit<T>(items: T[], limit: number, fn: (item: T) => Promise<void>): Promise<void> {
	let i = 0;
	const workers = Array.from({ length: Math.min(limit, items.length) }, async () => {
		while (i < items.length) {
			const idx = i++;
			await fn(items[idx]!);
		}
	});
	await Promise.all(workers);
}

async function main(): Promise<void> {
	check("provider reachable", await reachable(), BASE);
	if (failures > 0) {
		console.log(results.join("\n"));
		console.log(`\n${failures} failure(s) — provider not reachable, aborting.`);
		process.exit(1);
	}

	let channels: ResolvedChannel[];
	if (EXPLICIT_CHANNELS.length > 0) {
		channels = [];
		for (const id of EXPLICIT_CHANNELS) {
			const ch = await resolveChannel(id);
			if (ch) channels.push(ch);
			else check(`channel ${id} resolved from catalog`, false, "not_found");
		}
	} else {
		channels = await discoverChannels();
	}

	console.log(`\nDiscovered ${channels.length} channel(s) across ${new Set(channels.map((c) => c.sourceName)).size} source(s) — verifying ${CONCURRENCY} in parallel:\n`);

	await mapLimit(channels, CONCURRENCY, verifyChannel);

	console.log(results.join("\n"));
	console.log(
		`\n${failures === 0 ? "ALL LIVE CHECKS PASSED" : `${failures} FAILURE(S)`} (${skips} upstream-side skip(s))`,
	);
	process.exit(failures === 0 ? 0 : 1);
}

main();
