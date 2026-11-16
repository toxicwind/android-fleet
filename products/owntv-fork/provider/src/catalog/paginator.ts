/**
 * Paginator — stable cursor pagination over an ordered catalog.
 *
 * Replaces the Xtream `get_live_streams` 80 MB monolith with bounded pages.
 *
 * The cursor is the id of the last row the client received, so a page always
 * starts *after* it. That makes paging stable while the catalog is being
 * refreshed: rows appended with higher ids simply land on a later page instead
 * of shifting content the client has already seen.
 *
 * The catalog is held in memory here (the provider already has it), so this is
 * a pure, synchronous, allocation-light helper — no I/O to mock in tests.
 * A future DB-backed variant should push the cursor into SQL
 * (`WHERE id > :cursor ORDER BY id LIMIT :n`) and keep this same page shape.
 */

export interface CatalogPage<T> {
	items: T[];
	/** Null when the catalog is exhausted, so the client can stop. */
	nextCursor: string | null;
}

export interface PaginatorOptions<T> {
	/** Rows per page when the caller does not ask. */
	defaultLimit?: number;
	/** Hard ceiling so one request cannot ask for the whole catalog. */
	maxLimit?: number;
	/** Extract the ordering key. Defaults to `String(item.id)`. */
	idOf?: (item: T) => string;
}

const DEFAULT_LIMIT = 500;
const MAX_LIMIT = 5_000;

export class Paginator<T> {
	private readonly defaultLimit: number;
	private readonly maxLimit: number;
	private readonly idOf: (item: T) => string;

	constructor(
		private readonly items: readonly T[],
		options: PaginatorOptions<T> = {},
	) {
		this.defaultLimit = options.defaultLimit ?? DEFAULT_LIMIT;
		this.maxLimit = options.maxLimit ?? MAX_LIMIT;
		this.idOf = options.idOf ?? (item => String((item as { id?: unknown }).id));
	}

	/** Coerce a requested limit into [1, maxLimit]; junk falls back to the default. */
	clampLimit(requested?: number | string | null): number {
		const n = typeof requested === "string" ? Number.parseInt(requested, 10) : requested;
		if (n == null || !Number.isFinite(n) || n <= 0) return this.defaultLimit;
		return Math.min(Math.floor(n), this.maxLimit);
	}

	/** Index of the first row strictly after `cursor`. */
	private startIndex(cursor: string | null | undefined): number {
		if (cursor == null || cursor === "") return 0;
		for (let i = 0; i < this.items.length; i++) {
			if (this.idOf(this.items[i] as T) === cursor) return i + 1;
		}
		// A cursor we no longer recognise (catalog replaced, or hand-edited by a
		// client) means "nothing to resume" — report an exhausted catalog rather
		// than silently restarting from the top and duplicating rows.
		return this.items.length;
	}

	/** Read one page. Pass `nextCursor` back verbatim until it is null. */
	page(cursor?: string | null, limit?: number | string): CatalogPage<T> {
		const size = this.clampLimit(limit);
		const start = this.startIndex(cursor);
		const end = Math.min(start + size, this.items.length);
		const page = this.items.slice(start, end) as T[];

		const hasMore = end < this.items.length;
		const last = page[page.length - 1];
		const nextCursor = hasMore && last !== undefined ? this.idOf(last) : null;

		return { items: page, nextCursor };
	}

	/** How many pages a total row count needs — for logging and capacity checks. */
	pagesFor(total: number, limit?: number | string): number {
		if (total <= 0) return 0;
		return Math.ceil(total / this.clampLimit(limit));
	}

	get size(): number {
		return this.items.length;
	}
}