import { describe, expect, test } from "bun:test";
import { audioLanguagesOf } from "../src/upstreams.ts";

describe("audio language extraction", () => {
  test("parses (Audio: ES) panel convention", () => {
    expect(audioLanguagesOf("Channel (Audio: ES)")).toEqual(["ES"]);
    expect(audioLanguagesOf("Channel (Audio: ES,EN)")).toEqual(["ES", "EN"]);
  });

  test("parses bracketed [ES-ESP] tags", () => {
    expect(audioLanguagesOf("Channel [ES-ESP]")).toEqual(["ES"]);
    expect(audioLanguagesOf("Channel [EN] HD")).toEqual(["EN"]);
  });

  test("parses |ES| pipe tags but skips quality-only tags", () => {
    expect(audioLanguagesOf("|ES| ES - Dune")).toEqual(["ES"]);
    expect(audioLanguagesOf("|FR-4K| Dune")).toEqual(["FR"]);
    expect(audioLanguagesOf("|4K| Channel")).toBeUndefined();
  });

  test("returns undefined for no signal (never filters unknown)", () => {
    expect(audioLanguagesOf("Plain Channel HD")).toBeUndefined();
    expect(audioLanguagesOf("")).toBeUndefined();
  });

  test("skips MULTI/SUB markers", () => {
    expect(audioLanguagesOf("Channel [MULTI]")).toBeUndefined();
    expect(audioLanguagesOf("Channel (Audio: Multi)")).toBeUndefined();
  });
});