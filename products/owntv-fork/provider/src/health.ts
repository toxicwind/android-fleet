/**
 * Upstream health ledger — the failure signal the catalog never had.
 *
 * Anomaly #3 (POC 2, measured): 16 of 123 Xtream panels return an OBJECT
 * ({user_info} / {server_info}) where the spec says an ARRAY. The old guard
 * logged and moved on — channels imported under "Uncategorized" and the
 * success metric (687k channels assembled) hid the degradation.
 *
 * This ledger makes the failure loud and queryable:
 *   - auth-failed:  {user_info} body — creds dead, source is a corpse
 *   - overloaded:   {server_info} body — transient, retry next refresh
 *   - malformed:    any other non-array — spec violation, flag it
 *   - ok:           array returned
 *
 * Consumers: /health endpoint, failover grouping in the metadata lane,
 * and the catalog log line (degraded vs correct is now visible).
 */
export type UpstreamHealth =
  | { status: "ok"; channels: number; at: number }
  | { status: "auth-failed"; keys: string[]; at: number }
  | { status: "overloaded"; keys: string[]; at: number }
  | { status: "malformed"; keys: string[]; at: number }
  | { status: "error"; message: string; at: number };

const health = new Map<string, UpstreamHealth>();

/** Classify a non-array response body by its shape — fail loudly, never silently. */
export function classifyNonArray(raw: unknown): Extract<UpstreamHealth, { status: string }> {
  const keys = raw && typeof raw === "object" ? Object.keys(raw as object) : [];
  const k = keys.join(",");
  if (k.includes("user_info")) return { status: "auth-failed", keys, at: Date.now() };
  if (k.includes("server_info")) return { status: "overloaded", keys, at: Date.now() };
  return { status: "malformed", keys, at: Date.now() };
}

export function recordHealth(sourceId: string, h: UpstreamHealth): void {
  health.set(sourceId, h);
}

export function recordError(sourceId: string, message: string): void {
  health.set(sourceId, { status: "error", message, at: Date.now() });
}

export function snapshotHealth(): Record<string, UpstreamHealth> {
  return Object.fromEntries(health);
}

export function degradedCount(): number {
  let n = 0;
  for (const h of health.values()) if (h.status !== "ok") n++;
  return n;
}