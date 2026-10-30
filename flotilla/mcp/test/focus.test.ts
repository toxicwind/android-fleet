import { describe, expect, test } from "bun:test";
import { focusPackage, isFocusOn, isBrowserFocus, deviceHost } from "../../focus.ts";

describe("focusPackage", () => {
  test("parses fenix pkg/act", () => {
    expect(focusPackage("mCurrentFocus=Window{abc u0 org.mozilla.fenix/org.mozilla.fenix.HomeActivity}")).toBe("org.mozilla.fenix");
  });
  test("parses Termius terminal", () => {
    expect(focusPackage("mCurrentFocus=Window{db2a1ed u0 com.server.auditor.ssh.client/com.server.auditor.ssh.client.ssh.terminal.TerminalActivity}")).toBe("com.server.auditor.ssh.client");
  });
  test("null on unknown/empty", () => {
    expect(focusPackage("unknown")).toBeNull();
    expect(focusPackage("")).toBeNull();
  });
});

describe("isFocusOn", () => {
  test("true when package present", () => {
    expect(isFocusOn("mCurrentFocus=Window{1 u0 org.mozilla.fenix/Home}", "org.mozilla.fenix")).toBe(true);
  });
  test("false when other app", () => {
    expect(isFocusOn("mCurrentFocus=Window{1 u0 org.mozilla.fenix/Home}", "com.server.auditor.ssh.client")).toBe(false);
  });
});

describe("isBrowserFocus", () => {
  test("true for fenix", () => {
    expect(isBrowserFocus("mCurrentFocus=Window{1 u0 org.mozilla.fenix/Home}")).toBe(true);
  });
  test("true for chrome", () => {
    expect(isBrowserFocus("mCurrentFocus=Window{1 u0 com.android.chrome/Main}")).toBe(true);
  });
  test("false for termius", () => {
    expect(isBrowserFocus("mCurrentFocus=Window{1 u0 com.server.auditor.ssh.client/Terminal}")).toBe(false);
  });
});

describe("deviceHost", () => {
  test("strips port", () => {
    expect(deviceHost("10.0.0.77:42357")).toBe("10.0.0.77");
  });
  test("keeps bare host", () => {
    expect(deviceHost("10.0.0.77")).toBe("10.0.0.77");
  });
});