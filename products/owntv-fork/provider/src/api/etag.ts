/**
 * EtagStore — cheap change detection so an idle launch costs one round trip.
 *
 * The Xtream path re-pulls the entire catalog on every sync regardless of
 * whether anything moved. A weak ETag derived from a monotonic version lets the
 * provider answer `304 Not Modified` with no body.
 *
 * The version is the catalog's high-water mark: bump it whenever any source
 * upserts or removes a channel. It never decreases, so a stale client can never
 * be told "unchanged" by a rollback.
 */

export class EtagStore {
	#version = 0;

	/** Record a new catalog version and return the ETag to advertise. */
	touch(version: number): string {
		// Monotonic: a caller passing a lower number cannot move the catalog back.
		this.#version = Math.max(this.#version, Math.floor(version));
		return this.tag;
	}

	/** Bump by one after a mutation. */
	bump(): string {
		return this.touch(this.#version + 1);
	}

	get tag(): string {
		return `"catalog-${this.#version.toString(16)}"`;
	}

	get version(): number {
		return this.#version;
	}

	/**
	 * Does an incoming `If-None-Match` select this tag?
	 *
	 * Tolerant by design: clients send `*`, weak prefixes (`W/`), quoted or bare
	 * opaque tags, and comma-joined lists. All of those mean "matches".
	 */
	matches(ifNoneMatch: string | null | undefined): boolean {
		if (!ifNoneMatch) return false;

		const wanted = ifNoneMatch.trim();
		if (wanted === "" || wanted === ",") return false;
		if (wanted === "*") return true;

		const current = normalize(this.tag);
		return wanted.split(",").some(part => {
			const candidate = normalize(part);
			return candidate === "*" || candidate === current;
		});
	}
}

/** Lowercase and strip the weak validator prefix plus surrounding quotes. */
function normalize(tag: string): string {
	return tag.trim().replace(/^W\//i, "").replace(/"/g, "").toLowerCase();
}