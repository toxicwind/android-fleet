import { describe, expect, test } from "bun:test";
import { bucketOf, curateCategories, facetChannels } from "../src/categories.ts";

describe("curated category tree", () => {
  test("bucketOf folds sports names", () => {
    expect(bucketOf("SPORTS")).toBe("cur-sports");
    expect(bucketOf("Futbol")).toBe("cur-sports");
    expect(bucketOf("beIN Sports 3")).toBe("cur-sports");
  });

  test("bucketOf folds news/religious/kids", () => {
    expect(bucketOf("Noticias 24")).toBe("cur-news");
    expect(bucketOf("RELIGION")).toBe("cur-religious");
    expect(bucketOf("Kids Channel")).toBe("cur-kids");
  });

  test("bucketOf 4K surfaces quality bucket", () => {
    expect(bucketOf("4K Movies")).toBe("cur-4k");
    expect(bucketOf("2160p UHD")).toBe("cur-4k");
  });

  test("curateCategories dedupes by normalized name", () => {
    const raw = [
      { category_id: "1", category_name: "RELIGIOSOS", parent_id: 0 },
      { category_id: "2", category_name: "Religion", parent_id: 0 },
      { category_id: "3", category_name: "GUATEMALA", parent_id: 0 },
      { category_id: "4", category_name: "NACIONALES", parent_id: 0 },
    ];
    const out = curateCategories(raw);
    // RELIGIOSOS + Religion fold into one religious bucket entry
    const religious = out.filter((c) => c.category_id.startsWith("cur-religious"));
    expect(religious.length).toBe(1);
    expect(religious[0].folded).toContain("RELIGIOSOS");
    expect(religious[0].folded).toContain("Religion");
  });

  test("curateCategories keeps distinct names distinct", () => {
    const raw = [
      { category_id: "1", category_name: "UK", parent_id: 0 },
      { category_id: "2", category_name: "US", parent_id: 0 },
    ];
    const out = curateCategories(raw);
    expect(out.length).toBe(2);
  });

  test("facetChannels counts language and quality tags", () => {
    const channels = [
      { name: "|ES| ES - Dune", categoryId: "1" },
      { name: "|FR-4K| Dune 2021", categoryId: "1" },
      { name: "Dune (2021)[4K]", categoryId: "2" },
      { name: "Plain Channel", categoryId: "3" },
    ];
    const { languages, qualities } = facetChannels(channels);
    expect(languages.get("ES")).toBe(1);
    expect(languages.get("FR-4K")).toBe(1);
    expect(qualities.get("4k")).toBe(2);
  });
});
describe("adult category folding (one Adult bucket per source)", () => {
  test("bucketOf maps adult/xxx names to cur-adult", () => {
    expect(bucketOf("For Adults")).toBe("cur-adult");
    expect(bucketOf("XXX")).toBe("cur-adult");
    expect(bucketOf("❌❌❌ADULTOS II")).toBe("cur-adult");
    expect(bucketOf("PORNBOX")).toBe("cur-adult");
    expect(bucketOf("ADULT SPECIAL BOX [NEW]")).toBe("cur-adult");
    expect(bucketOf("Sports")).not.toBe("cur-adult");
  });

  test("bucketCategoryId folds all adult names to the SAME id per source", async () => {
    const { bucketCategoryId, bucketCategoryName } = await import("../src/upstreams.ts");
    const id1 = bucketCategoryId("s1", "1", "For Adults");
    const id2 = bucketCategoryId("s1", "2", "XXX");
    const id3 = bucketCategoryId("s1", "3", "❌❌❌ADULTOS II");
    expect(id1).toBe(id2);
    expect(id1).toBe(id3);
    expect(bucketCategoryName("XXX")).toBe("Adult");
    // Non-adult unchanged behavior.
    expect(bucketCategoryId("s1", "4", "Sports")).not.toBe(id1);
    expect(bucketCategoryName("Sports")).toBe("Sports");
  });
});

describe("catalog keep-gate (English + Adult only)", () => {
  test("keepCategory drops foreign/acronym/radio/24-7/ppv, keeps EN + Adult", async () => {
    const { keepCategory } = await import("../src/upstreams.ts");
    // Keep: English-adjacent + Adult
    expect(keepCategory("USA News")).toBe(true);
    expect(keepCategory("For Adults")).toBe(true);
    expect(keepCategory("XXX")).toBe(true);
    expect(keepCategory("UK Sports")).toBe(true);
    expect(keepCategory("JP Drama")).toBe(true);
    expect(keepCategory("KR KBS")).toBe(true);
    // Drop: full-name foreign
    expect(keepCategory("NOTICIAS VENEZUELA")).toBe(false);
    expect(keepCategory("DEPORTES ARGENTINA")).toBe(false);
    expect(keepCategory("LA CASA DE LOS FAMOSOS MEXICO")).toBe(false);
    // Drop: acronym country prefixes (the AR| class)
    expect(keepCategory("AR| MBC SHAHID")).toBe(false);
    expect(keepCategory("BR | Globo")).toBe(false);
    expect(keepCategory("MX: Televisa")).toBe(false);
    expect(keepCategory("DE | Sport")).toBe(false);
    expect(keepCategory("RU| NTV")).toBe(false);
    // Keep: EN/JP/KR acronym prefixes
    expect(keepCategory("US| CNN")).toBe(true);
    expect(keepCategory("UK| BBC")).toBe(true);
    // Drop: radio/24-7/ppv
    expect(keepCategory("Radio Classics")).toBe(false);
    expect(keepCategory("24/7 Replays")).toBe(false);
    expect(keepCategory("PPV Events")).toBe(false);
  });
});
