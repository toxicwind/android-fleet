import { describe, expect, test } from "bun:test";
import { Paginator } from "../src/catalog/paginator.ts";
import { ChangeFeed } from "../src/catalog/change-feed.ts";
import { EtagStore } from "../src/api/etag.ts";

/**
 * TDD for the native catalog protocol replacing the Xtream `get_live_streams`
 * 80 MB monolith. Spec: plan.md → "Refactor: drop the Xtream protocol".
 *
 * Contracts under test:
 *  - Paginator: bounded pages, opaque resumable cursor, no gaps or repeats,
 *    stable while the catalog grows, terminates with nextCursor = null.
 *  - ChangeFeed: monotonic sequence, O(changes) delta, explicit resync signal
 *    when a client falls behind retained history.
 *  - EtagStore: monotonic weak validator, tolerant If-None-Match matching.
 */

const rows = (n: number, offset = 0) =>
	Array.from({ length: n }, (_, i) => ({ id: String(i + 1 + offset) }));

// ─── Paginator ───────────────────────────────────────────────────────────────

describe("Paginator", () => {
	test("empty catalog yields no items and no cursor", () => {
		expect(new Paginator<{ id: string }>([]).page(null)).toEqual({ items: [], nextCursor: null });
	});

	test("a catalog smaller than the limit fits in one page", () => {
		const page = new Paginator(rows(10)).page(null, 500);
		expect(page.items.length).toBe(10);
		expect(page.nextCursor).toBeNull();
	});

	test("a catalog exactly at the limit fits in one page", () => {
		const page = new Paginator(rows(500)).page(null, 500);
		expect(page.items.length).toBe(500);
		expect(page.nextCursor).toBeNull();
	});

	test("one row over the limit produces a second page of one", () => {
		const p = new Paginator(rows(501));
		const first = p.page(null, 500);
		expect(first.nextCursor).toBe("500");
		const second = p.page(first.nextCursor, 500);
		expect(second.items.map(i => i.id)).toEqual(["501"]);
		expect(second.nextCursor).toBeNull();
	});

	test("an exact multiple terminates without an empty trailing page", () => {
		const p = new Paginator(rows(1_000));
		const first = p.page(null, 500);
		expect(first.nextCursor).toBe("500");
		const second = p.page(first.nextCursor, 500);
		expect(second.items.length).toBe(500);
		expect(second.nextCursor).toBeNull();
	});

	test("the cursor round-trips into the next page", () => {
		const p = new Paginator(rows(1_500));
		expect(p.page(p.page(null, 500).nextCursor, 500).items[0]?.id).toBe("501");
	});

	test("walking the catalog yields every row exactly once", () => {
		const p = new Paginator(rows(1_000));
		const seen: string[] = [];
		let cursor: string | null = null;
		for (let i = 0; i < 20; i++) {
			const page = p.page(cursor, 500);
			seen.push(...page.items.map(r => r.id));
			cursor = page.nextCursor;
			if (cursor === null) break;
		}
		expect(seen.length).toBe(1_000);
		expect(new Set(seen).size).toBe(1_000);
	});

	test("a large catalog terminates in a bounded number of pages", () => {
		const p = new Paginator(rows(228_829));
		let cursor: string | null = null;
		let pages = 0;
		let total = 0;
		for (;;) {
			const page = p.page(cursor, 500);
			total += page.items.length;
			pages++;
			cursor = page.nextCursor;
			if (cursor === null) break;
		}
		expect(total).toBe(228_829);
		expect(pages).toBe(458);
	});

	test("order is preserved across pages", () => {
		const p = new Paginator(rows(300));
		const first = p.page(null, 100).items.map(i => i.id);
		const second = p.page(p.page(null, 100).nextCursor, 100).items.map(i => i.id);
		expect(first[0]).toBe("1");
		expect(second[0]).toBe("101");
		expect(second[99]).toBe("200");
	});

	test("a cursor from a grown catalog resumes where it left off", () => {
		const first = new Paginator(rows(600)).page(null, 500);
		expect(first.nextCursor).toBe("500");
		const grown = new Paginator(rows(900));
		expect(grown.page(first.nextCursor, 500).items[0]?.id).toBe("501");
	});

	test("rows appended after page one are not repeated", () => {
		const p = new Paginator(rows(500, 1_000));
		const first = p.page(null, 250);
		const grown = new Paginator(rows(700, 1_000));
		const second = grown.page(first.nextCursor, 250);
		expect(second.items[0]?.id).toBe("1251");
	});

	test("a limit of one pages one row at a time", () => {
		const p = new Paginator(rows(3));
		const a = p.page(null, 1);
		expect(a.items.length).toBe(1);
		expect(a.nextCursor).toBe("1");
		expect(p.page(a.nextCursor, 1).items[0]?.id).toBe("2");
	});

	test("a limit above the catalog returns everything at once", () => {
		const page = new Paginator(rows(10)).page(null, 5_000);
		expect(page.items.length).toBe(10);
		expect(page.nextCursor).toBeNull();
	});

	test("a limit above maxLimit is clamped", () => {
		expect(new Paginator(rows(100), { maxLimit: 50 }).page(null, 10_000).items.length).toBe(50);
	});

	test("a limit of zero falls back to the default", () => {
		expect(new Paginator(rows(100), { defaultLimit: 25 }).page(null, 0).items.length).toBe(25);
	});

	test("a negative limit falls back to the default", () => {
		expect(new Paginator(rows(100), { defaultLimit: 25 }).page(null, -5).items.length).toBe(25);
	});

	test("a non-numeric limit falls back to the default", () => {
		expect(new Paginator(rows(100), { defaultLimit: 25 }).page(null, "abc").items.length).toBe(25);
	});

	test("a numeric string limit is honoured", () => {
		expect(new Paginator(rows(100)).page(null, "10").items.length).toBe(10);
	});

	test("a fractional limit is floored", () => {
		expect(new Paginator(rows(100)).page(null, 10.9).items.length).toBe(10);
	});

	test("an empty-string cursor starts from the top", () => {
		expect(new Paginator(rows(10)).page("", 5).items[0]?.id).toBe("1");
	});

	test("an unknown cursor reports an exhausted catalog", () => {
		expect(new Paginator(rows(10)).page("does-not-exist", 5)).toEqual({ items: [], nextCursor: null });
	});

	test("clampLimit handles undefined and null", () => {
		const p = new Paginator(rows(10), { defaultLimit: 25, maxLimit: 50 });
		expect(p.clampLimit(undefined)).toBe(25);
		expect(p.clampLimit(null)).toBe(25);
		expect(p.clampLimit(10)).toBe(10);
		expect(p.clampLimit(999)).toBe(50);
	});

	test("pagesFor returns zero for an empty catalog", () => {
		expect(new Paginator(rows(10)).pagesFor(0, 500)).toBe(0);
	});

	test("pagesFor rounds up to whole pages", () => {
		const p = new Paginator(rows(10));
		expect(p.pagesFor(1, 500)).toBe(1);
		expect(p.pagesFor(500, 500)).toBe(1);
		expect(p.pagesFor(501, 500)).toBe(2);
		expect(p.pagesFor(1_000, 500)).toBe(2);
		expect(p.pagesFor(1_001, 500)).toBe(3);
	});

	test("pagesFor falls back to the default limit", () => {
		expect(new Paginator(rows(10), { defaultLimit: 30 }).pagesFor(61)).toBe(3);
	});

	test("custom idOf drives the cursor", () => {
		const p = new Paginator([{ key: "a" }, { key: "b" }], { idOf: x => x.key });
		expect(p.page(null, 1).nextCursor).toBe("a");
		expect(p.page("a", 1).items[0]?.key).toBe("b");
	});

	test("custom idOf walks the whole catalog", () => {
		const p = new Paginator([{ key: "a" }, { key: "b" }, { key: "c" }], { idOf: x => x.key });
		const first = p.page(null, 2);
		expect(first.items.map(i => i.key)).toEqual(["a", "b"]);
		expect(p.page(first.nextCursor, 2).items.map(i => i.key)).toEqual(["c"]);
	});

	test("default idOf reads row.id", () => {
		const p = new Paginator([{ id: 7 }, { id: 8 }]);
		expect(p.page(null, 1).nextCursor).toBe("7");
	});

	test("a row without an id still pages without throwing", () => {
		const p = new Paginator([{}, {}]);
		expect(p.page(null, 1).nextCursor).toBe("undefined");
	});

	test("size reports the catalog length", () => {
		expect(new Paginator(rows(42)).size).toBe(42);
		expect(new Paginator([]).size).toBe(0);
	});

	test("separate paginators do not share state", () => {
		const a = new Paginator(rows(100));
		const b = new Paginator(rows(100));
		expect(a.page(null, 10).nextCursor).toBe("10");
		expect(b.page(null, 10).nextCursor).toBe("10");
		expect(a.page("10", 10).items[0]?.id).toBe("11");
	});

	test("the default limit is 500", () => {
		expect(new Paginator(rows(1_000)).clampLimit(undefined)).toBe(500);
	});

	test("the default max limit is 5000", () => {
		expect(new Paginator(rows(10)).clampLimit(999_999)).toBe(5_000);
	});
});

// ─── ChangeFeed ──────────────────────────────────────────────────────────────

describe("ChangeFeed", () => {
	test("a new feed is empty at sequence zero", () => {
		const f = new ChangeFeed();
		expect(f.cursor).toBe(0);
		expect(f.size).toBe(0);
	});

	test("record returns an incrementing sequence", () => {
		const f = new ChangeFeed();
		expect([f.record({ id: "1" }), f.record({ id: "2" }), f.record({ id: "3" })]).toEqual([1, 2, 3]);
	});

	test("remove returns an incrementing sequence", () => {
		const f = new ChangeFeed();
		expect([f.remove({ id: "1" }), f.remove({ id: "2" })]).toEqual([1, 2]);
	});

	test("record and remove share one sequence", () => {
		const f = new ChangeFeed();
		expect(f.record({ id: "1" })).toBe(1);
		expect(f.remove({ id: "2" })).toBe(2);
	});

	test("cursor tracks the latest sequence", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.record({ id: "2" });
		expect(f.cursor).toBe(2);
	});

	test("since at the cursor yields nothing", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		expect(f.since(f.cursor).changed.length).toBe(0);
	});

	test("since returns only changes after the given sequence", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.record({ id: "2" });
		const mark = f.cursor;
		f.record({ id: "3" });
		const delta = f.since(mark);
		expect(delta.changed.length).toBe(1);
		expect(delta.changed[0]?.item).toEqual({ id: "3" });
	});

	test("since echoes the high-water sequence", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.record({ id: "2" });
		expect(f.since(0).seq).toBe(2);
	});

	test("changes are marked upsert", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		expect(f.since(0).changed[0]?.kind).toBe("upsert");
	});

	test("changes are marked remove", () => {
		const f = new ChangeFeed();
		f.remove({ id: "1" });
		expect(f.since(0).changed[0]?.kind).toBe("remove");
	});

	test("a removal carries the item so clients can delete it", () => {
		const f = new ChangeFeed();
		const mark = f.cursor;
		f.remove({ id: "gone" });
		expect(f.since(mark).changed[0]?.item).toEqual({ id: "gone" });
	});

	test("a negative since returns everything retained", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.record({ id: "2" });
		expect(f.since(-1).changed.length).toBe(2);
	});

	test("a since beyond the cursor yields nothing", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		expect(f.since(99).changed.length).toBe(0);
		expect(f.since(99).seq).toBe(1);
	});

	test("changes preserve insertion order", () => {
		const f = new ChangeFeed();
		f.record({ id: "a" });
		f.record({ id: "b" });
		f.record({ id: "c" });
		expect(f.since(0).changed.map(c => c.item)).toEqual([{ id: "a" }, { id: "b" }, { id: "c" }]);
	});

	test("a client behind retained history is told to resync", () => {
		const f = new ChangeFeed({ retention: 3 });
		for (let i = 0; i < 10; i++) f.record({ id: String(i) });
		const delta = f.since(0) as { changed: unknown[]; truncated?: boolean };
		expect(delta.truncated).toBe(true);
		expect(delta.changed.length).toBe(0);
	});

	test("a client inside the retention window is not flagged", () => {
		const f = new ChangeFeed({ retention: 3 });
		for (let i = 0; i < 10; i++) f.record({ id: String(i) });
		const delta = f.since(8) as { truncated?: boolean };
		expect(delta.truncated).toBeUndefined();
		expect(delta.changed.length).toBe(2);
	});

	test("a fresh feed never reports truncation", () => {
		expect((new ChangeFeed().since(0) as { truncated?: boolean }).truncated).toBeUndefined();
	});

	test("size reflects retained entries", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.record({ id: "2" });
		expect(f.size).toBe(2);
	});

	test("retention caps the retained entry count", () => {
		const f = new ChangeFeed({ retention: 5 });
		for (let i = 0; i < 20; i++) f.record({ id: String(i) });
		expect(f.size).toBe(5);
	});

	test("compact drops entries at or below the cutoff", () => {
		const f = new ChangeFeed();
		for (let i = 0; i < 5; i++) f.record({ id: String(i) });
		f.compact(3);
		expect(f.size).toBe(2);
		expect(f.cursor).toBe(5);
	});

	test("compact with a zero cutoff drops nothing", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.compact(0);
		expect(f.size).toBe(1);
	});

	test("compact beyond the cursor empties the log but keeps the sequence", () => {
		const f = new ChangeFeed();
		f.record({ id: "1" });
		f.compact(99);
		expect(f.size).toBe(0);
		expect(f.cursor).toBe(1);
	});

	test("compact is idempotent", () => {
		const f = new ChangeFeed();
		for (let i = 0; i < 5; i++) f.record({ id: String(i) });
		f.compact(2);
		f.compact(2);
		expect(f.size).toBe(3);
	});

	test("two feeds keep independent state", () => {
		const a = new ChangeFeed();
		const b = new ChangeFeed();
		a.record({ id: "1" });
		expect(a.cursor).toBe(1);
		expect(b.cursor).toBe(0);
	});

	test("record then remove the same item yields both changes", () => {
		const f = new ChangeFeed();
		f.record({ id: "x" });
		const mark = f.cursor;
		f.remove({ id: "x" });
		const delta = f.since(mark);
		expect(delta.changed[0]?.kind).toBe("remove");
		expect(delta.changed[0]?.item).toEqual({ id: "x" });
	});

	test("a custom clock stamps entries", () => {
		let clock = 1_000;
		const f = new ChangeFeed({ now: () => clock });
		f.record({ id: "1" });
		clock = 2_000;
		f.record({ id: "2" });
		const delta = f.since(0) as { changed: { at?: number }[] };
		expect(delta.changed[0]?.at).toBe(1_000);
		expect(delta.changed[1]?.at).toBe(2_000);
	});

	test("an empty feed yields an empty delta at any sequence", () => {
		expect(new ChangeFeed().since(0)).toEqual({ changed: [], seq: 0 });
	});
});

// ─── EtagStore ───────────────────────────────────────────────────────────────

describe("EtagStore", () => {
	test("a new store is at version zero", () => {
		expect(new EtagStore().version).toBe(0);
	});

	test("the tag is a quoted validator", () => {
		expect(new EtagStore().tag).toBe('"catalog-0"');
	});

	test("touch returns the tag", () => {
		const store = new EtagStore();
		expect(store.touch(0)).toBe(store.tag);
	});

	test("touch advances the version", () => {
		const store = new EtagStore();
		store.touch(1);
		expect(store.version).toBe(1);
	});

	test("touch never moves the version backwards", () => {
		const store = new EtagStore();
		store.touch(5);
		store.touch(2);
		expect(store.version).toBe(5);
	});

	test("touch floors a fractional version", () => {
		const store = new EtagStore();
		store.touch(3.9);
		expect(store.version).toBe(3);
	});

	test("the tag renders the version in hex", () => {
		const store = new EtagStore();
		store.touch(255);
		expect(store.tag).toBe('"catalog-ff"');
	});

	test("bump increments the version by one", () => {
		const store = new EtagStore();
		store.bump();
		expect(store.version).toBe(1);
		store.bump();
		expect(store.version).toBe(2);
	});

	test("bump returns the new tag", () => {
		const store = new EtagStore();
		const tag = store.bump();
		expect(tag).toBe(store.tag);
		expect(tag).not.toBe('"catalog-0"');
	});

	test("a changed catalog invalidates an old tag", () => {
		const store = new EtagStore();
		const before = store.touch(0);
		const after = store.touch(1);
		expect(after).not.toBe(before);
		expect(store.matches(before)).toBe(false);
	});

	test("an identical tag matches", () => {
		const store = new EtagStore();
		expect(store.matches(store.tag)).toBe(true);
	});

	test("a weak validator prefix matches", () => {
		const store = new EtagStore();
		expect(store.matches(`W/${store.tag}`)).toBe(true);
	});

	test("matching is case-insensitive", () => {
		const store = new EtagStore();
		expect(store.matches(store.tag.toUpperCase())).toBe(true);
	});

	test("a bare tag without quotes matches", () => {
		const store = new EtagStore();
		expect(store.matches("catalog-0")).toBe(true);
	});

	test("the wildcard matches", () => {
		expect(new EtagStore().matches("*")).toBe(true);
	});

	test("one entry of a comma-joined list matches", () => {
		const store = new EtagStore();
		expect(store.matches(`"other", ${store.tag}, "more"`)).toBe(true);
	});

	test("a list without the current tag does not match", () => {
		const store = new EtagStore();
		expect(store.matches('"other", "more"')).toBe(false);
	});

	test("an empty header does not match", () => {
		expect(new EtagStore().matches("")).toBe(false);
	});

	test("a whitespace-only header does not match", () => {
		expect(new EtagStore().matches("   ")).toBe(false);
	});

	test("null does not match", () => {
		expect(new EtagStore().matches(null)).toBe(false);
	});

	test("undefined does not match", () => {
		expect(new EtagStore().matches(undefined)).toBe(false);
	});

	test("an unrelated tag does not match", () => {
		expect(new EtagStore().matches('"catalog-zz"')).toBe(false);
	});

	test("a prefix of the current tag does not match", () => {
		const store = new EtagStore();
		store.touch(255);
		expect(store.matches('"catalog-f"')).toBe(false);
	});

	test("a longer tag does not match", () => {
		const store = new EtagStore();
		expect(store.matches('"catalog-00"')).toBe(false);
	});

	test("surrounding whitespace in a list is tolerated", () => {
		const store = new EtagStore();
		expect(store.matches(`  "other" ,  ${store.tag}  `)).toBe(true);
	});

	test("the tag is stable across reads", () => {
		const store = new EtagStore();
		store.bump();
		expect(store.tag).toBe(store.tag);
	});
});