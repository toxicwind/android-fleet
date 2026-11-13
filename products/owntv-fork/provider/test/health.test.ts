import { describe, expect, test } from "bun:test";
import { classifyNonArray, recordHealth, snapshotHealth, degradedCount } from "../src/health.ts";

describe("upstream health ledger", () => {
  test("classifies auth-failed body", () => {
    const c = classifyNonArray({ user_info: { auth: 0 }, server_info: {} });
    expect(c.status).toBe("auth-failed");
  });

  test("classifies overloaded body", () => {
    const c = classifyNonArray({ server_info: { status: "overloaded" } });
    expect(c.status).toBe("overloaded");
  });

  test("classifies malformed body", () => {
    const c = classifyNonArray({ weird: true });
    expect(c.status).toBe("malformed");
  });

  test("ledger records and counts degradation", () => {
    recordHealth("a", { status: "ok", channels: 100, at: 1 });
    recordHealth("b", classifyNonArray({ user_info: {} }));
    expect(degradedCount()).toBe(1);
    expect(snapshotHealth()["b"].status).toBe("auth-failed");
  });
});