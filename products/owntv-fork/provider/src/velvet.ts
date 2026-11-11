/** Velvet series source: exposes the velvet CS3 plugin catalog as Xtream get_series_*.
 *  Each plugin = a series category; the catalog comes from velvet/plugins.json.
 *  Stream resolution stays on the device (CloudStream3) — the provider serves the
 *  browsable series schema so OwnTV's SERIES section lights up. */
import type { Config } from "./config.ts";

export interface VelvetPlugin {
  name: string;
  description?: string;
  url?: string;
  version?: number;
  status?: string;
}

export interface VelvetSeries {
  series_id: string;
  name: string;
  cover?: string;
  plot?: string;
  rating?: number;
  category_id: string;
  year?: number;
  added?: number;
  last_modified?: number;
}

export interface VelvetCatalog {
  categories: { category_id: string; category_name: string; parent_id: number }[];
  series: VelvetSeries[];
}

/** Load velvet/plugins.json into the Xtream series schema. */
export async function loadVelvetCatalog(config: Config): Promise<VelvetCatalog> {
  const plugins = await readPluginsJson(config.velvetPluginsPath);
  const categories = plugins.map((p, i) => ({
    category_id: String(i + 1),
    category_name: p.name,
    parent_id: 0,
  }));
  const series: VelvetSeries[] = plugins.map((p, i) => ({
    series_id: `velvet-${i + 1}`,
    name: p.name,
    plot: p.description ?? `${p.name} — velvet CS3 plugin`,
    cover: undefined,
    rating: undefined,
    category_id: String(i + 1),
    year: undefined,
    added: Math.floor(Date.now() / 1000),
    last_modified: Math.floor(Date.now() / 1000),
  }));
  return { categories, series };
}

async function readPluginsJson(path: string): Promise<VelvetPlugin[]> {
  const res = await fetch(`file://${path}`);
  const text = await res.text();
  const parsed = JSON.parse(text) as unknown;
  return Array.isArray(parsed) ? (parsed as VelvetPlugin[]) : [];
}