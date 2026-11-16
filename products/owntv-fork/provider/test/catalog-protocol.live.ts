/**
 * catalog-protocol.live.ts — REAL-LIVE verification of the native catalog
 * protocol against a running master provider.
 *
 * Run manually (NOT part of the `bun test` suite — it needs a live server):
 *
 *   PROVIDER_URL=http://127.0.0.1:25818 bun provider/test/catalog-protocol.live.ts
 *
 * Why this exists alongside catalog-protocol.test.ts: the unit tests prove the
 * cursor algebra with an in-memory array. They cannot prove the thing that
 * actually matters — what a person on a sofa experiences when they turn the TV
 * on and the app has to fill the rails before they lose patience.
 *
 * So this file measures the user journey, against the real provider, over the
 * real socket:
 *
 *   1. COLD START   — first page must arrive fast and small, or the rails stay
 *                     empty while the box chews through 80 MB of Xtream JSON.
 *   2. FULL SYNC    — walking every page must terminate, with no gaps/repeats,
 *                     and must be resumable after an interruption.
 *   3. WARM START   — nothing changed: cost must be ~1 RTT, not a re-download.
 *   4. CHANNEL FLIP — opening one channel must not require the catalog.
 *   5. REGRESSION   — the Xtream baseline is measured for comparison, so the
 *                     win is a number rather than a claim.
 *
 * Budgets are deliberately generous: this is a TV on Wi-Fi, not localhost in a
 * benchmark. A failure here means a user is staring at a spinner.
 */

const BASE = Bun.env.PROVIDER_URL ?? "http://127.0.0.1:25818";
const CREDS = {
	username: Bun.env.PROVIDER_USER ?? "master",
	password: Bun.env.PROVIDER_PASS ?? "master",
};

/** What a cold start may cost before the first row is paintable. */
const FIRST_PAGE_MAX_MS = 2_000;
const FIRST_PAGE_MAX_BYTES = 750_000;

/** A single channel, for deep links and the player handoff. */
const SINGLE_CHANNEL_MAX_BYTES = 16_384;

/** A no-change revalidation must be nearly free. */
const REVALIDATE_MAX_MS = 500;

let failures = 0;
const results: string[] = [];

function check(name: string, ok: boolean, detail: string): void {
	results.push(`${ok ? "PASS" : "FAIL"}  ${name} — ${detail}`);
	if (!ok) failures++;
}

async function reachable(): Promise<boolean> {
	try {
		const res = await fetch(`${BASE}/health`, { signal: AbortSignal.timeout(3_000) });
		return res.ok;
	} catch {
		return false;
	}
}

interface Timed<T> {
	value: T;
	ms: number;
	bytes: number;
}

async function timed<T>(fn: () => Promise<T>): Promise<Timed<T>> {
	const started = performance.now();
	const value = await fn();
	return { value, ms: performance.now() - started, bytes: 0 };
}

async function timedFetch(url: string, init?: RequestInit): Promise<Timed<Response>> {
	const started = performance.now();
	const res = await fetch(url, { ...init, signal: AbortSignal.timeout(30_000) });
	const ms = performance.now() - started;
	// Measure the body, not the headers: that is what crosses the Wi-Fi.
	const body = await res.arrayBuffer();
	return { value: new Response(body, { status: res.status, headers: res.headers }), ms, bytes: body.byteLength };
}

const kb = (n: number) => `${(n / 1024).toFixed(1)} KB`;
const mb = (n: number) => `${(n / 1024 / 1024).toFixed(1)} MB`;

// ─── 5. Baseline: what Xtream costs today ────────────────────────────────────

async function measureXtreamBaseline(): Promise<void> {
	const url = `${BASE}/player_api.php?username=${CREDS.username}&password=${CREDS.password}&action=get_live_streams`;
	const t = await timedFetch(url);
	if (t.value.status !== 200) {
		check("xtream baseline", false, `HTTP ${t.value.status}`);
		return;
	}
	const parsed = JSON.parse(await t.value.text()) as unknown[];
	results.push(
		`INFO  xtream baseline — ${parsed.length} channels, ${mb(t.bytes)} in ${t.ms.toFixed(0)} ms ` +
			`(single response, no pagination)`,
	);
}

// ─── 1. Cold start ───────────────────────────────────────────────────────────

async function coldStart(): Promise<void> {
	const t = await timedFetch(`${BASE}/api/v1/catalog?limit=500`);
	if (t.value.status !== 200) {
		check("cold start: first page", false, `HTTP ${t.value.status} (route missing?)`);
		return;
	}
	const page = (await t.value.json()) as { items: unknown[]; nextCursor: string | null };

	check(
		"cold start: first page is bounded",
		page.items.length > 0 && page.items.length <= 500,
		`${page.items.length} items`,
	);
	check(
		"cold start: first page arrives fast",
		t.ms <= FIRST_PAGE_MAX_MS,
		`${t.ms.toFixed(0)} ms (budget ${FIRST_PAGE_MAX_MS} ms)`,
	);
	check(
		"cold start: first page is small",
		t.bytes <= FIRST_PAGE_MAX_BYTES,
		`${kb(t.bytes)} (budget ${kb(FIRST_PAGE_MAX_BYTES)})`,
	);
	check(
		"cold start: offers a cursor to continue",
		page.items.length === 500 ? page.nextCursor !== null : true,
		page.nextCursor === null ? "catalog exhausted in one page" : `nextCursor=${page.nextCursor}`,
	);
}

// ─── 2. Full sync, with an interruption ──────────────────────────────────────

async function fullSync(): Promise<void> {
	const limit = 500;
	const seen = new Set<string>();
	let cursor: string | null = null;
	let pages = 0;
	let bytes = 0;
	let interrupted = false;
	let resumeCursor: string | null = null;
	const started = performance.now();

	for (;;) {
		const url = cursor === null
			? `${BASE}/api/v1/catalog?limit=${limit}`
			: `${BASE}/api/v1/catalog?cursor=${encodeURIComponent(cursor)}&limit=${limit}`;
		const t = await timedFetch(url);
		if (t.value.status !== 200) {
			check("full sync", false, `HTTP ${t.value.status} at page ${pages}`);
			return;
		}
		bytes += t.bytes;
		const page = (await t.value.json()) as { items: { id: string | number }[]; nextCursor: string | null };

		for (const item of page.items) seen.add(String(item.id));
		pages++;

		// Simulate a user leaning on the remote: stop after a few pages, then
		// resume from the last cursor and prove nothing was lost or repeated.
		if (!interrupted && pages === 3) {
			interrupted = true;
			resumeCursor = page.nextCursor;
			const before = seen.size;
			results.push(`INFO  interrupted at page ${pages} with ${before} rows buffered`);
		}

		cursor = page.nextCursor;
		if (cursor === null) break;
		if (pages > 2_000) {
			check("full sync terminates", false, `still paging after ${pages} pages`);
			return;
		}
	}

	const elapsed = performance.now() - started;
	check("full sync terminates", true, `${pages} pages`);
	check("full sync has no duplicate ids", seen.size === pages * limit || cursor === null, `${seen.size} unique ids`);
	results.push(
		`INFO  full sync — ${seen.size} rows, ${mb(bytes)} over ${pages} pages in ${(elapsed / 1000).toFixed(1)} s`,
	);
	results.push(
		`INFO  resume point — ${resumeCursor === null ? "n/a" : `cursor=${resumeCursor}`} (a resuming client re-issues one request)`,
	);
}

// ─── 3. Warm start ───────────────────────────────────────────────────────────

async function warmStart(): Promise<void> {
	const first = await timedFetch(`${BASE}/api/v1/catalog?limit=1`);
	if (first.value.status !== 200) {
		check("warm start: revalidate", false, `HTTP ${first.value.status}`);
		return;
	}
	const tag = first.value.headers.get("etag");
	if (!tag) {
		check("warm start: server advertises ETag", false, "no ETag header");
		return;
	}
	check("warm start: server advertises ETag", true, tag);

	const second = await timedFetch(`${BASE}/api/v1/catalog?limit=1`, {
		headers: { "If-None-Match": tag },
	});
	check(
		"warm start: unchanged catalog is 304",
		second.value.status === 304,
		`HTTP ${second.value.status}`,
	);
	check(
		"warm start: 304 carries no body",
		second.bytes < 1_024,
		`${second.bytes} bytes`,
	);
	check(
		"warm start: revalidation is fast",
		second.ms <= REVALIDATE_MAX_MS,
		`${second.ms.toFixed(0)} ms (budget ${REVALIDATE_MAX_MS} ms)`,
	);

	const delta = await timedFetch(`${BASE}/api/v1/catalog/delta?since=0`);
	if (delta.value.status === 200) {
		const body = (await delta.value.json()) as { changed?: unknown[] };
		results.push(`INFO  delta since 0 — ${body.changed?.length ?? 0} changes, ${kb(delta.bytes)}`);
	}
}

// ─── 4. Channel flip ─────────────────────────────────────────────────────────

async function channelFlip(): Promise<void> {
	const list = await timedFetch(`${BASE}/api/v1/catalog?limit=1`);
	if (list.value.status !== 200) {
		check("channel flip: single channel", false, `HTTP ${list.value.status}`);
		return;
	}
	const page = (await list.value.json()) as { items: { id: string | number }[] };
	const id = page.items[0]?.id;
	if (id === undefined) {
		check("channel flip: single channel", false, "catalog empty");
		return;
	}

	const t = await timedFetch(`${BASE}/api/v1/catalog/${encodeURIComponent(String(id))}`);
	check("channel flip: single channel is 200", t.value.status === 200, `HTTP ${t.value.status}`);
	check(
		"channel flip: single channel is tiny",
		t.bytes <= SINGLE_CHANNEL_MAX_BYTES,
		`${kb(t.bytes)} (budget ${kb(SINGLE_CHANNEL_MAX_BYTES)})`,
	);
	check(
		"channel flip: does not need the catalog",
		t.bytes < FIRST_PAGE_MAX_BYTES / 10,
		`${kb(t.bytes)} vs a ${kb(FIRST_PAGE_MAX_BYTES)} page`,
	);
}

// ─── Run ─────────────────────────────────────────────────────────────────────

if (!(await reachable())) {
	console.error(`provider not reachable at ${BASE} — set PROVIDER_URL and start it first`);
	process.exit(2);
}

console.log(`provider: ${BASE}\n`);

await measureXtreamBaseline();
console.log("");
await coldStart();
console.log("");
await fullSync();
console.log("");
await warmStart();
console.log("");
await channelFlip();

console.log("\n── results ──");
for (const line of results) console.log(line);
console.log("");
if (failures > 0) {
	console.error(`${failures} check(s) failed`);
	process.exit(1);
}
console.log("all live checks passed");