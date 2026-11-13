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