/**
 * ChangeFeed — monotonic change log so repeat launches cost O(changes).
 *
 * The Xtream path re-pulls the entire catalog every sync. A feed keyed by a
 * monotonic sequence lets a client say "give me what changed since seq N" and
 * receive only upserts and removals. Most launches then return near-empty.
 *
 * Per-source: one feed instance per upstream, so a change in source A never
 * makes a client of source B re-read.
 */

export type ChangeKind = "upsert" | "remove";

export interface Change<T = unknown> {
	seq: number;
	kind: ChangeKind;
	/** Present for `upsert`; absent for `remove`. */
	item?: T;
}

export interface ChangeBatch<T = unknown> {
	changed: Change<T>[];
	/** High-water sequence; pass this back as `since` next time. */
	seq: number;
}

export interface ChangeFeedOptions {
	/** Bound retained history. Older entries are dropped, not merged. */
	retention?: number;
	now?: () => number;
}

interface Entry<T> {
	seq: number;
	kind: ChangeKind;
	item?: T;
	at: number;
}

export class ChangeFeed<T = unknown> {
	private seq = 0;
	private readonly entries: Entry<T>[] = [];
	private readonly retention: number;
	private readonly now: () => number;

	constructor(options: ChangeFeedOptions = {}) {
		this.retention = Math.max(1, options.retention ?? 10_000);
		this.now = options.now ?? Date.now;
	}

	/** Current high-water sequence. Zero means "nothing recorded yet". */
	get cursor(): number {
		return this.seq;
	}

	record(item: T): number {
		return this.push({ seq: ++this.seq, kind: "upsert", item, at: this.now() });
	}

	remove(item: T): number {
		return this.push({ seq: ++this.seq, kind: "remove", item, at: this.now() });
	}

	private push(entry: Entry<T>): number {
		this.entries.push(entry);
		if (this.entries.length > this.retention) {
			this.entries.splice(0, this.entries.length - this.retention);
		}
		return entry.seq;
	}

	/**
	 * Changes after `since`. A `since` older than retained history is treated as
	 * "client is too far behind" — the caller should fall back to a full sync
	 * rather than silently receiving a truncated delta.
	 */
	since(since: number): ChangeBatch<T> {
		const oldest = this.entries[0]?.seq ?? this.seq;
		if (since < oldest - 1 && this.entries.length >= this.retention) {
			return { changed: [], seq: this.seq, ...{ truncated: true } };
		}
		const changed = this.entries.filter(e => e.seq > since);
		return { changed, seq: this.seq };
	}

	/**
	 * Drop entries no client could still need. Called by the provider after a
	 * successful delta ack so memory stays flat across long-lived processes.
	 */
	compact(aheadOfAllClients: number): void {
		const cutoff = Math.max(0, aheadOfAllClients);
		while (this.entries.length > 0 && (this.entries[0]?.seq ?? 0) <= cutoff) {
			this.entries.shift();
		}
	}

	get size(): number {
		return this.entries.length;
	}
}