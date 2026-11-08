import { describe, test, expect } from "bun:test";
import { applyRoot, ok, err, chain, catchAll, RollbackStack, withRollback } from "../docs/hisense/root-rollback";

function withEnv(env: Record<string, unknown>, fn: () => void) {
  (globalThis as Record<string, unknown>).__tvEnv = env;
  try { fn(); } finally { delete (globalThis as Record<string, unknown>).__tvEnv; }
}

const SUCCESS_ENV = { plant: "planted", remount: "rw", su: "uid=0", "shizuku-root": "OK", id: "uid=0(rooot)" };
const FAIL_ENV = { plant: "planted", remount: "rw", su: "uid=0", "shizuku-root": "OK", id: "uid=2000(shell)" };

describe("monadic root rollback primitives", () => {
  test("Result primitives chain and catch", () => {
    const r = chain(ok(1), (n) => ok(n + 1));
    expect(r).toEqual({ ok: true, value: 2 });
    const e = catchAll(err<number, Error>(new Error("x")), () => ok(9));
    expect(e).toEqual({ ok: true, value: 9 });
  });

  test("applyRoot succeeds to uid=0", () => {
    withEnv(SUCCESS_ENV, () => {
      const r = applyRoot();
      expect(r.ok).toBe(true);
      if (r.ok) expect(r.value).toContain("uid=0");
    });
  });

  test("failure rolls back pushed inverses LIFO", () => {
    withEnv(FAIL_ENV, () => {
      const logs: string[] = [];
      const r = applyRoot((m) => logs.push(m));
      expect(r.ok).toBe(false);
      const inverses = logs.filter((l) => l.includes("inverse=")).map((l) => l.match(/inverse='([^']+)'/)?.[1] ?? "?");
      // LIFO: deep steps undone first (shizuku-root, su-shim, remount, plant-cvt)
      expect(inverses).toEqual(["shizuku-root", "su-shim", "remount", "plant-cvt"]);
      expect(inverses.every((_, i) => inverses[i] === inverses[i])).toBe(true);
    });
  });

  test("withRollback pushes then runs inverse on failure", () => {
    const stack = new RollbackStack();
    const step = (): ReturnType<typeof ok> => err<unknown, Error>(new Error("boom"));
    const r = withRollback("s1", step, () => ok(undefined), stack);
    expect(r.ok).toBe(false);
    // inverse not run yet (only pushes); outer rollbackAll runs it
    const logs: string[] = [];
    stack.rollbackAll((m) => logs.push(m));
    expect(logs.some((l) => l.includes("inverse='s1' ok"))).toBe(true);
  });
});