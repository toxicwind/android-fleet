/**
 * TDD contract for flotilla's floatyCardHtml — the fleet's visual channel.
 * Asserts the production-grade aesthetic surface: no broken CSS-JS injection,
 * accent contract, safe text rendering (textContent, not innerHTML), dismiss
 * behavior, font fallback, and structural invariants.
 */
import { describe, expect, it } from "bun:test";
import { floatyCardHtml } from "../mcp/floaty-card.ts";

describe("floatyCardHtml visual channel", () => {
  it("1. returns a complete HTML document", () => {
    const html = floatyCardHtml({ message: "hello" });
    expect(html.startsWith("<!doctype html>")).toBe(true);
    expect(html.endsWith("</html>")).toBe(true);
    expect(html).toContain("</style>");
    expect(html).toContain("</script>");
  });

  it("2. renders the message via textContent (XSS-safe, never innerHTML)", () => {
    const html = floatyCardHtml({ message: "<img src=x onerror=alert(1)>" });
    expect(html).toContain("textContent");
    expect(html).not.toContain("innerHTML");
    // The payload must be JSON-stringified into textContent, not raw HTML
    expect(html).toContain(JSON.stringify("<img src=x onerror=alert(1)>"));
  });

  it("3. applies the default terminal-green accent", () => {
    const html = floatyCardHtml({ message: "up" });
    expect(html).toContain("#7CFC00");
  });

  it("4. honors a custom accent and rejects malformed ones", () => {
    const ok = floatyCardHtml({ message: "x", accent: "#FF8800" });
    expect(ok).toContain("#FF8800");
    const bad = floatyCardHtml({ message: "x", accent: "javascript:alert(1)" });
    expect(bad).toContain("#7CFC00"); // falls back to default
  });

  it("5. never emits the broken @media{setTimeout JS-in-CSS injection", () => {
    for (const secs of [0, 3, 10]) {
      const html = floatyCardHtml({ message: "x", secs });
      expect(html).not.toContain("@media{setTimeout");
      expect(html).not.toContain("window.close");
    }
  });

  it("6. sets auto-dismiss timer only when secs > 0", () => {
    const withTimer = floatyCardHtml({ message: "x", secs: 5 });
    expect(withTimer).toContain("setTimeout(()=>close(),5000);");
    const noTimer = floatyCardHtml({ message: "x", secs: 0 });
    expect(noTimer).not.toContain("setTimeout");
  });

  it("7. includes the sub-line only when provided", () => {
    const withSub = floatyCardHtml({ message: "x", sub: "detail" });
    expect(withSub).toContain("class=sub");
    const without = floatyCardHtml({ message: "x" });
    expect(without).not.toContain("class=sub");
  });

  it("8. embeds the DepartureMono font as base64 when given", () => {
    const html = floatyCardHtml({ message: "x", fontB64: "QUJD" });
    expect(html).toContain("data:font/otf;base64,QUJD");
  });

  it("9. degrades to monospace fallback without a font", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("DM,monospace");
  });

  it("10. caps message length at 400 chars", () => {
    const long = "a".repeat(900);
    const html = floatyCardHtml({ message: long });
    expect(html).not.toContain("a".repeat(401));
  });

  it("11. caps sub-line at 200 chars", () => {
    const long = "b".repeat(500);
    const html = floatyCardHtml({ message: "x", sub: long });
    expect(html).not.toContain("b".repeat(201));
  });

  it("12. clamps negative/NaN secs to no-timer", () => {
    expect(floatyCardHtml({ message: "x", secs: -5 })).not.toContain("setTimeout");
    expect(floatyCardHtml({ message: "x", secs: Number.NaN })).not.toContain("setTimeout");
  });

  it("13. includes safe-area inset for notched screens", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("env(safe-area-inset-bottom)");
  });

  it("14. carries the flotilla channel tag for provenance", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("flotilla channel");
  });

  it("15. respects prefers-reduced-motion", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("prefers-reduced-motion");
  });

  it("16. viewport meta present for mobile rendering", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain('name=viewport');
  });

  it("17. scanline texture overlay present (lo-fi aesthetic)", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("repeating-linear-gradient");
  });

  it("18. backdrop blur applied for depth over any app", () => {
    const html = floatyCardHtml({ message: "x" });
    expect(html).toContain("backdrop-filter:blur(6px)");
  });
});