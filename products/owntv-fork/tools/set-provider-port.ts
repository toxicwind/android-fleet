#!/usr/bin/env bun
/**
 * set-provider-port — rewrite a stored provider endpoint in an OwnTV database.
 *
 * The Xtream provider gateway moved off 8081 into the estate 25xxx range
 * (25818). Every stored absolute URL embeds the old endpoint: sources.url,
 * channels.streamUrl, EPG links, catch-up templates, and so on. A move needs a
 * sweep across *every* text column of *every* table, not just the obvious ones.
 *
 * HOST SCOPING MATTERS. A bare `:8081` sweep is wrong: the library also holds
 * third-party panels that genuinely serve on 8081 (e.g. `http://23.131.184.86:8081`).
 * Rewriting those breaks working sources. Always pass --host so only this
 * provider's own rows are touched.
 *
 * Usage:
 *   bun tools/set-provider-port.ts <db> --host 10.0.0.218 --from 8081 --to 25818
 *   bun tools/set-provider-port.ts <db> --host 10.0.0.218 --from 8081 --to 25818 --check
 *
 * --check reports without writing. The sweep is idempotent. VACUUM runs only
 * when rows changed, so freed-page copies of the old string do not survive to
 * confuse a later `grep` on the file.
 */
import { Database } from "bun:sqlite";

const args = process.argv.slice(2);
const dbPath = args.find(a => !a.startsWith("--")) ?? "owntv.db";
const flag = (name: string): string | undefined => {
	const i = args.indexOf(`--${name}`);
	return i >= 0 ? args[i + 1] : undefined;
};

const host = flag("host");
const from = flag("from");
const to = flag("to");
const check = args.includes("--check");

if (!host || !from || !to) {
	console.error("usage: set-provider-port.ts <db> --host <ip|host> --from <oldPort> --to <newPort> [--check]");
	console.error("  --host is required: without it the sweep would also rewrite third-party");
	console.error("  panels that legitimately serve on the old port.");
	process.exit(2);
}

const OLD = `${host}:${from}`;
const NEW = `${host}:${to}`;

const db = new Database(dbPath, { create: false, readonly: check });

const tables = db
	.query<{ name: string }, []>("SELECT name FROM sqlite_master WHERE type='table'")
	.all()
	.map(t => t.name)
	.filter(n => !n.startsWith("sqlite_") && !n.startsWith("android_") && !n.includes("_fts"));

let rows = 0;
const touched: string[] = [];

for (const table of tables) {
	let cols: { name: string }[] = [];
	try {
		cols = db.query<{ name: string }, []>(`PRAGMA table_info("${table}")`).all();
	} catch {
		continue;
	}
	for (const col of cols) {
		const key = `${table}.${col.name}`;
		try {
			const seen = db
				.query(`SELECT COUNT(*) AS n FROM "${table}" WHERE IFNULL("${col.name}", '') LIKE ?`)
				.get(`%${OLD}%`) as { n: number };
			if (seen.n === 0) continue;
			if (check) {
				console.log(`${key}: ${seen.n} row(s) would change`);
				rows += seen.n;
				touched.push(key);
				continue;
			}
			const res = db
				.query(
					`UPDATE "${table}" SET "${col.name}" = replace("${col.name}", ?, ?) WHERE IFNULL("${col.name}", '') LIKE ?`,
				)
				.run(OLD, NEW, `%${OLD}%`);
			if (res.changes > 0) {
				console.log(`${key}: ${res.changes}`);
				rows += res.changes;
				touched.push(key);
			}
		} catch {
			/* not a text-comparable column */
		}
	}
}

if (!check && rows > 0) {
	db.exec("PRAGMA wal_checkpoint(TRUNCATE)");
	db.exec("VACUUM");
}
db.close();

console.log(`\n${check ? "WOULD_CHANGE" : "CHANGED"}: ${rows} row(s) across ${touched.length} column(s)`);
if (touched.length) console.log(touched.join("\n"));