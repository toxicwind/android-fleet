/**
 * stream-race — probe several experimental stream routes for a channel AT ONCE
 * and take the first one that answers. A channel flip should cost one budget
 * window (default 300ms for a batch of 5), not one serial timeout per route.
 *
 * Why race instead of try-in-order: a dead route costs its full connect timeout
 * (often 5-30s). Five channels serially at 1 route each is 25-150s of dead air.
 * Racing all routes for all channels in a batch turns that into max(worst route)
 * instead of sum(all routes).
 *
 * Routes are deliberately heterogeneous — a channel can be reachable through the
 * panel's Xtream path, its raw streamUrl, or the provider's own proxy, and which
 * one works varies per panel. The race discovers that instead of assuming.
 */
export interface StreamCandidate {
	/** Human label for logs/debugging, e.g. "xtream", "direct", "provider". */
	label: string;
	url: string;
	/** Extra headers for this route (referer/user-agent some panels demand). */
	headers?: Record<string, string>;
}

export interface StreamProbe {
	label: string;
	url: string;
	status: number;
	ms: number;
	contentType?: string;
}

export interface RaceOptions {
	/** Per-candidate ceiling. */
	timeoutMs?: number;
	/** Abort the whole race after this, regardless of candidate count. */
	budgetMs?: number;
	/** Only accept these HTTP statuses. Default: any 2xx. */
	accept?: (status: number, contentType: string | undefined) => boolean;
}

const DEFAULT_TIMEOUT = 1_500;
const DEFAULT_ACCEPT = (status: number) => status >= 200 && status < 300;

/**
 * Race every candidate for ONE channel. Resolves with the first acceptable
 * response, or null if all fail. Losers are aborted so they stop holding sockets.
 */
export async function raceStream(
	candidates: StreamCandidate[],
	options: RaceOptions = {},
): Promise<StreamProbe | null> {
	if (candidates.length === 0) return null;
	const timeoutMs = options.timeoutMs ?? DEFAULT_TIMEOUT;
	const accept = options.accept ?? DEFAULT_ACCEPT;

	const controllers = candidates.map(() => new AbortController());
	const started = performance.now();

	const attempts = candidates.map(async (candidate, index) => {
		const controller = controllers[index]!;
		const timer = setTimeout(() => controller.abort(), timeoutMs);
		const t0 = performance.now();
		try {
			const response = await fetch(candidate.url, {
				signal: controller.signal,
				headers: candidate.headers,
				// We only need headers: the body is megabytes of video.
				method: "GET",
			});
			const contentType = response.headers.get("content-type") ?? undefined;
			// Cancel the body immediately — we are not downloading it.
			try {
				await response.body?.cancel();
			} catch {
				/* body already gone */
			}
			if (!accept(response.status, contentType)) {
				throw new Error(`rejected ${response.status}`);
			}
			return {
				label: candidate.label,
				url: candidate.url,
				status: response.status,
				ms: performance.now() - t0,
				contentType,
			} satisfies StreamProbe;
		} finally {
			clearTimeout(timer);
		}
	});

	try {
		const winner = await Promise.any(attempts);
		// First success wins: abort everything still in flight.
		for (const controller of controllers) controller.abort();
		return winner;
	} catch {
		// Promise.any rejects only when every attempt rejected.
		for (const controller of controllers) controller.abort();
		return null;
	} finally {
		void started;
	}
}

/**
 * Race a batch of channels, each with its own candidate list, in parallel.
 *
 * `budgetMs` is the wall-clock ceiling for the WHOLE batch — the caller gets a
 * partial result rather than waiting for the slowest channel. That is what makes
 * "5 channels under 300ms" achievable: a dead channel cannot stall the batch.
 */
export async function raceBatch(
	batch: { channelId: string; candidates: StreamCandidate[] }[],
	options: RaceOptions & { budgetMs?: number } = {},
): Promise<Map<string, StreamProbe | null>> {
	const results = new Map<string, StreamProbe | null>();
	const budgetMs = options.budgetMs ?? 300;

	const work = batch.map(async entry => {
		const probe = await raceStream(entry.candidates, options);
		results.set(entry.channelId, probe);
		return probe;
	});

	// Let the batch settle, but never past the budget. Late results are dropped
	// rather than awaited — a channel that cannot be probed in budget is a miss.
	let timer: ReturnType<typeof setTimeout> | undefined;
	const deadline = new Promise<void>(resolve => {
		timer = setTimeout(resolve, budgetMs);
	});

	await Promise.race([Promise.allSettled(work).then(() => undefined), deadline]);
	if (timer) clearTimeout(timer);

	// Any channel that had not reported yet is recorded as a miss so callers see
	// a complete map instead of undefined.
	for (const entry of batch) {
		if (!results.has(entry.channelId)) results.set(entry.channelId, null);
	}

	return results;
}