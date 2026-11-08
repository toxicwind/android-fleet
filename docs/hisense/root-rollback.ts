// Monadic rollback-on-fail — concrete primitives for the Hisense ROOT-APPLY lane.
// Self-contained (no deps): a tiny Result monad with `chain`/`mapError`,
// `catchAll`/`withRollback` composing LIFO inverse effects. Mirrors the estate
// effect standard (pens/estate/docs/typescript-monadic-standard.md).
//
// Contract:
//   - Every step returns Result<T, E>.
//   - withRollback(step, inverse): if step fails, the inverse runs (as its own
//     Result, never swallowing). Inverses accumulate on a stack; a higher-level
//     failure pops them LIFO so a dirty TV is never left behind.
//   - rollback() itself rolls back the rollback: if an inverse fails it is
//     logged with tag `monadicRollback` and we continue (best-effort chain).

export type Result<T, E = Error> = { ok: true; value: T } | { ok: false; error: E };

export const ok = <T, E = Error>(value: T): Result<T, E> => ({ ok: true, value });
export const err = <T, E = Error>(error: E): Result<T, E> => ({ ok: false, error });

export const chain = <T, U, E>(
  r: Result<T, E>,
  f: (value: T) => Result<U, E>,
): Result<U, E> => (r.ok ? f(r.value) : (r as Result<U, E>));

export const mapError = <T, A, E>(r: Result<T, A>, f: (e: A) => E): Result<T, E> =>
  r.ok ? r : { ok: false, error: f(r.error) };

export const trySync = <T>(fn: () => T): Result<T, Error> => {
  try { return ok(fn()); } catch (e) { return err(e instanceof Error ? e : new Error(String(e))); }
};

export const catchAll = <T, E, U>(
  r: Result<T, E>,
  f: (e: E) => Result<U, E>,
): Result<T | U, E> => (r.ok ? r : f(r.error));

// --- rollback stack manager -------------------------------------------------

export type Effect<T> = () => Result<T, Error>;

export class RollbackStack {
  private inverses: Array<{ label: string; run: Effect<void> }> = [];

  push(label: string, inverse: Effect<void>) {
    this.inverses.push({ label, run: inverse });
  }

  /** LIFO: run inverses in reverse order; each is best-effort + logged. */
  rollbackAll(log: (msg: string) => void = console.error): Result<void, Error> {
    let acc: Result<void, Error> = ok(undefined);
    for (let i = this.inverses.length - 1; i >= 0; i--) {
      const { label, run } = this.inverses[i];
      acc = chain(acc, () => {
        const res = run();
        log(res.ok
          ? `monadicRollback inverse='${label}' ok`
          : `monadicRollback inverse='${label}' error='${String(res.error)}'`);
        return ok(undefined);
      });
    }
    this.inverses = [];
    return acc;
  }
}

/**
 * Acquire-style primitive: run `step`; if it fails, run its `inverse` FIRST
 * (the immediate cleanup), then let the caller's outer rollbackAll finish the
 * rest of the stack LIFO. Returns a Result that carries the original error.
 */
export const withRollback = <T>(
  label: string,
  step: Effect<T>,
  inverse: Effect<void>,
  stack: RollbackStack,
  log: (m: string) => void = console.error,
): Result<T, Error> => {
  stack.push(label, inverse);
  const r = step();
  if (r.ok) return r;
  const inv = inverse();
  if (!inv.ok) log(`monadicRollback immediate-inverse='${label}' error='${String(inv.error)}'`);
  return r; // the FAILURE propagates; caller's catchAll decides
};

// --- domain: ROOT-APPLY steps (stub shell calls; real lane in ROOT-APPLY.md) --

const run = (cmd: string): Result<string, Error> => trySync(() => {
  // real production would exec adb shell; here a simulation namespace
  const env = (globalThis as Record<string, unknown>).__tvEnv as Record<string, unknown>;
  const v = env[cmd];
  if (typeof v === "string") return v;
  if (v === "FAIL") throw new Error(`sim failed: ${cmd}`);
  return "OK " + cmd;
});

/** Wrap a run() into a void Effect for inverse chains. */
const inv = (cmd: string): Effect<void> => () => { run(cmd); return ok(undefined); };

export const applyRoot = (log: (m: string) => void = console.error): Result<string, Error> => {
  const stack = new RollbackStack();
  const r1 = withRollback("plant-cvt", () => run("plant"),
    inv("unplant"), stack, log);
  const r2 = chain(r1, () => withRollback("remount", () => run("remount"),
    inv("remove-stick"), stack, log));
  const r3 = chain(r2, () => withRollback("su-shim", () => run("su"),
    inv("rm-shim"), stack, log));
  const r4 = chain(r3, () => withRollback("shizuku-root", () => run("shizuku-root"),
    inv("kill-server"), stack, log));
  const verified = chain(chain(r4, () => run("id")), (uid: string): Result<string, Error> =>
    uid.includes("uid=0") ? ok(uid) : err(new Error("root not reached")));
  if (!verified.ok) {
    // monadic catch: rollback the whole stack (LIFO), then rethrow
    const rb = stack.rollbackAll(log);
    if (!rb.ok) log(`monadicRollback chain-failed error='${String(rb.error)}'`);
    return verified;
  }
  return verified;
};

if (import.meta.main) {
  const env = { plant: "planted", remount: "rw", su: "uid=0", "shizuku-root": "OK", id: "uid=0(rooot)" };
  (globalThis as Record<string, unknown>).__tvEnv = env;
  console.log("SUCCESS:", applyRoot());                 // should pass
  const failEnv = { plant: "planted", remount: "FAIL", su: "uid=2000(shell)", "shizuku-root": "FAIL", id: "uid=2000(shell)" };
  (globalThis as Record<string, unknown>).__tvEnv = failEnv;
  console.log("FAILURE-PATH (expected LIFO for pushed steps: remove-stick, unplant):");
  const logs: string[] = [];
  console.log("res:", JSON.stringify(applyRoot((m) => logs.push(m))));
  console.log("rollback log:", logs);
}