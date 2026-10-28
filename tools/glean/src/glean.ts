#!/usr/bin/env bun
/**
 * glean — cross-repo cherry-picker: pull a file, or a line range of a file,
 * out of ANY git repo/URL and lay it onto a target path in the current tree.
 *
 *   glean set <src> <dst>            # whole file, or path-equal when dst omitted
 *   glean set 'repo@ref:path:6-8' dst
 *   glean set 'url@ref:path' dst
 *
 * `src` forms:
 *   local-dir:path              local repo dir
 *   local-dir:path:6-8          line range (1-indexed inclusive)
 *   local-dir@ref:path          any ref (branch/tag/sha)
 *   https://host/org/repo:path  clone-on-demand (shallow, temp)
 *   https://host/org/repo@ref:path
 *
 * Range may be "6-8", "6-", "-8", or a list "6,9-11". When a range is given,
 * the output is written to <dst> (defaults to basename of src path). Whole-file
 * gleans default dst to the same relative path.
 *
 * Powered by git archive/git show under the hood — no full history needed.
 */
import { parseArgs } from "node:util";
import { existsSync } from "node:fs";

const USAGE = `glean <src> [dst]

src forms:
  DIR:path[:RANGE]            local repo (default ref = HEAD)
  DIR@REF:path[:RANGE]        local repo at a ref
  URL:path[:RANGE]            remote repo (shallow clone-on-demand to /tmp/glean-*)
  URL@REF:path[:RANGE]        remote repo at a ref

RANGE: "6-8" | "6-" | "-8" | "6,9-11"  (1-indexed inclusive; default whole file)
dst:   target path (default: basename for ranges, same relative path for whole)`;

function die(msg: string): never {
  console.error(`✘ ${msg}`);
  process.exit(1);
}

interface Spec {
  repo: string;
  ref: string;
  path: string;
  range: [number, number] | null;
}

const RANGE_RE = /^(\d+)(?:-(\d+)?)?$/;
function parseRange(s: string): [number, number] | null {
  if (s === "") return null;
  const parts = s.split(",");
  let min = Infinity, max = 0;
  for (const p of parts) {
    const m = p.match(RANGE_RE);
    if (!m) die(`bad range segment: ${p}`);
    const a = parseInt(m[1], 10);
    const b = m[2] !== undefined ? parseInt(m[2], 10) : a;
    if (a < 1 || b < a) die(`bad range: ${p}`);
    min = Math.min(min, a);
    max = Math.max(max, b);
  }
  return [min, max];
}

function parse(src: string): Spec {
  // split @ref first (only the first @ that precedes a path/colon boundary)
  const at = src.indexOf("@");
  let repo = src, ref = "HEAD";
  if (at > 0 && src[at + 1] && !src.slice(at + 1).startsWith("http")) {
    repo = src.slice(0, at);
    const rest = src.slice(at + 1);
    const colon = rest.indexOf(":");
    if (colon === -1) die(`ref form needs :path — got ${src}`);
    ref = rest.slice(0, colon);
    repo = src.slice(0, at);
    const tail = rest.slice(colon + 1);
    return finish(repo, ref, tail);
  }
  const colon = repo.indexOf(":");
  if (colon === -1) die(`need :path — got ${src}`);
  const tail = repo.slice(colon + 1);
  repo = repo.slice(0, colon);
  return finish(repo, ref, tail);
}

function finish(repo: string, ref: string, tail: string): Spec {
  // tail = path[:range]
  const m = tail.match(/^(.*?)(?::(\d[\d,:-]*))?$/);
  if (!m) die(`bad path/range: ${tail}`);
  const path = m[1] ?? "";
  const range = m[2] !== undefined ? parseRange(m[2]) : null;
  if (!path) die("empty path");
  return { repo, ref, path, range };
}

function isUrl(s: string): boolean {
  return /^https?:\/\//.test(s);
}

function run(cmd: string[], cwd?: string): string {
  const r = Bun.spawnSync(cmd, { cwd, stdout: "pipe", stderr: "pipe" });
  if (r.exitCode !== 0) {
    die(`${cmd[0]} ${cmd.slice(1).join(" ")} → ${new TextDecoder().decode(r.stderr).trim().slice(0, 400)}`);
  }
  return new TextDecoder().decode(r.stdout);
}

function resolveRepo(spec: Spec): string {
  if (!isUrl(spec.repo)) {
    if (!existsSync(spec.repo)) die(`local repo not found: ${spec.repo}`);
    return spec.repo;
  }
  const tmp = `/tmp/glean-${Math.random().toString(36).slice(2, 8)}`;
  run(["git", "clone", "--depth", "1", spec.repo, tmp, "--filter=blob:none"]);
  return tmp;
}

async function main() {
  const { positionals } = parseArgs({ args: Bun.argv.slice(2), allowPositionals: true });
  if (positionals.length === 0) { console.log(USAGE); process.exit(0); }
  const [src, dstArg] = positionals;
  const spec = parse(src);
  const repo = resolveRepo(spec);

  // fetch the file bytes at ref
  const sha = run(["git", "-C", repo, "rev-parse", spec.ref]).trim();
  const blob = run(["git", "-C", repo, "show", `${sha}:${spec.path}`]);
  const lines = blob.split("\n");

  let out: string;
  let dst: string;
  if (spec.range) {
    const [a, b] = spec.range;
    if (a > lines.length) die(`range starts past EOF (${lines.length} lines)`);
    out = lines.slice(a - 1, Math.min(b, lines.length)).join("\n") + "\n";
    dst = dstArg ?? spec.path.split("/").pop() ?? "out";
  } else {
    out = blob;
    dst = dstArg ?? spec.path;
  }

  await Bun.write(dst, out);
  console.log(`✓ gleaned ${spec.repo}@${sha.slice(0, 8)}:${spec.path}` +
    (spec.range ? ` lines ${spec.range[0]}-${spec.range[1]}` : "") +
    ` → ${dst} (${out.length} bytes)`);
}

await main();