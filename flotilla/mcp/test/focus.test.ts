import { describe, expect, test } from "bun:test";
import { focusPackage, isFocusOn } from "../../focus.ts";

describe("focusPackage", () => {
  test("parses pkg/act focus line", () => {
    expect(focusPackage("mCurrentFocus=Window{abc u0 org.mozilla.fenix/org.mozilla.fenix.HomeActivity}")).toBe("org.mozilla.fenix");
  });
  test("parses colon-style TerminalActivity focus", () => {
    expect(focusPackage("mCurrentFocus=Window{db2a1ed u0 com.server.auditor.ssh.client/com.server.auditor.ssh.client.ssh.terminal.TerminalActivity}")).toBe("com.server.auditor.ssh.client");
  });
  test("parses focused=false oddity", () => {
    expect(focusPackage("mCurrentFocus=Window{1 pkg/com.x8bit.bitwarden/.MainActivity}")).toBe("com.x8bit.bitwarden");
  });
  test("returns null on unknown/empty", () => {
    expect(focusPackage("mCurrentFocus=unknown")).toBeNull();
    expect(focusPackage("")).toBeNull();
  });
});

describe("isFocusOn", () => {
  test("true when package present in focus", () => {
    expect(isFocusOn("mCurrentFocus=Window{721395c u0 org.mozilla.fenix/org.mozilla.fenix.HomeActivity}", "org.mozilla.fenix")).toBe(true);
  });
  test("false when other app focused", () => {
    expect(isFocusOn("mCurrentFocus=Window{721395c u0 org.mozilla.fenix/org.mozilla.fenix.HomeActivity}", "com.server.auditor.ssh.client")).toBe(false);
  });
});
