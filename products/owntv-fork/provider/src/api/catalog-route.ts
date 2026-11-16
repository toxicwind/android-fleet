/**
 * Native paginated catalog route.
 *
 * Exists because the Xtream `get_live_streams` shape pushes the WHOLE catalog
 * in one response. Measured on this box: 80 MB for 180k channels — and in
 * practice a client that cannot drain it wedges the socket with megabytes stuck
 * in Send-Q (observed: 500 MB sent, 3.2 MB queued, rwnd_limited 98.6%).
 *
 * This route serves bounded pages instead, so a client can start rendering
 * after page 1 and a resumed client re-issues exactly one request.
 *
 *   GET /api/v1/catalog?cursor=<id>&limit=500
 *     -> { items, nextCursor, etag }
 *
 *   GET /api/v1/catalog/delta?since=<seq>
 *     -> { changed, seq, truncated? }
 *
 *   GET /api/v1/catalog/:id
 *     -> one channel
 */
import { Paginator, type CatalogPage } from "../catalog/paginator.ts";
import { ChangeFeed, type Change } from "../catalog/change-feed.ts";
import { EtagStore } from "./etag.ts";

/** Minimal channel shape this route needs. The provider's Channel satisfies it. */
export interface CatalogChannel {
	id: string | number;
	name?: string;
	[key: string]: unknown;
}

export interface CatalogRouteOptions {
	/** Called when a delta consumer needs a resync because it fell too far behind. */
	onResync?: (since: number) => void;
}

export function createCatalogRoute(channels: CatalogChannel[], options: CatalogRouteOptions = {}) {
	const paginator = new Paginator<CatalogChannel>(channels, {
		idOf: channel => String(channel.id),
	});
	const feed = new ChangeFeed<CatalogChannel>();
	const etags = new EtagStore();

	/** Re-tag whenever the catalog identity changes, so clients can 304. */
	function revalidate(): void {
		etags.touch(channels.length);
	}

	return {
		paginator,
		feed,
		etags,
		revalidate,

		/** Handle `GET /api/v1/catalog...` — returns null when the path is not ours. */
		handle(path: string, url: URL, ifNoneMatch: string | null): Response | null {
			if (path === "/api/v1/catalog" || path === "/api/v1/catalog/") {
				const tag = etags.tag;
				if (ifNoneMatch && etags.matches(ifNoneMatch)) {
					return new Response(null, { status: 304, headers: { etag: tag } });
				}
				const cursor = url.searchParams.get("cursor");
				const limit = paginator.clampLimit(url.searchParams.get("limit"));
				const page: CatalogPage<CatalogChannel> = paginator.page(cursor, limit);
				return json({ items: page.items, nextCursor: page.nextCursor, etag: tag });
			}

			if (path === "/api/v1/catalog/delta") {
				const sinceRaw = url.searchParams.get("since");
				const since = sinceRaw === null ? 0 : Number.parseInt(sinceRaw, 10);
				const delta = feed.since(Number.isFinite(since) ? since : 0);
				// A client behind retained history must resync rather than trust a
				// truncated delta; tell it, and let the caller decide.
				if ((delta as { truncated?: boolean }).truncated) {
					options.onResync?.(since);
					return json({ changed: [], seq: delta.seq, truncated: true }, 409);
				}
				return json({ changed: delta.changed, seq: delta.seq });
			}

			const single = path.match(/^\/api\/v1\/catalog\/([^/]+)$/);
			if (single) {
				const id = decodeURIComponent(single[1]!);
				const found = channels.find(channel => String(channel.id) === id);
				if (!found) return json({ error: "not_found", id }, 404);
				return json(found);
			}

			return null;
		},
	};
}

function json(body: unknown, status = 200): Response {
	return new Response(JSON.stringify(body), {
		status,
		headers: { "content-type": "application/json; charset=utf-8" },
	});
}

export type { Change };