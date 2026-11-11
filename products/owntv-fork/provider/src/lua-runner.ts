/** Lua runner endpoint — fengari (pure-JS Lua VM) with bun-side bindings.
 *  POST /run {"script":"...", "timeoutMs":5000} executes Lua with globals:
 *    fetch(url, opts)  — bun fetch
 *    exec(cmd)         — bun shell (adb/rish/curl)
 *    argos(query)      — argos fuzzy search
 *    read(path)/write(path,data) — filesystem
 *    log(...)          — stdout capture
 *  Response: {"ok":true,"output":"...","result":<lua return>}
 */
import * as fengari from "fengari";
const { lua, lauxlib, lualib, to_luastring, to_jsstring } = fengari;
const { luaL_loadbuffer, luaL_newstate } = lauxlib;
const { luaL_openlibs } = lualib;
const { lua_pcall, lua_pushcfunction, lua_gettop, lua_type, lua_tostring, lua_tonumber, lua_toboolean, lua_setglobal, lua_pop, lua_close, lua_pushstring, LUA_TSTRING, LUA_TNUMBER, LUA_TBOOLEAN } = lua;
import { spawnSync } from "node:child_process";
import { readFileSync, writeFileSync } from "node:fs";
import { searchArgos, type Config } from "./argos.ts";

export interface RunRequest {
  script: string;
  timeoutMs?: number;
}

export interface RunResult {
  ok: boolean;
  output: string;
  result?: unknown;
  error?: string;
}

export async function runLua(req: RunRequest, config: Config): Promise<RunResult> {
  const output: string[] = [];
  const L = luaL_newstate();
  luaL_openlibs(L);

  // log(...) — capture to output
  lua_pushcfunction(L, (L) => {
    const n = lua_gettop(L);
    const parts: string[] = [];
    for (let i = 1; i <= n; i++) {
      const t = lua_type(L, i);
      if (t === LUA_TSTRING) parts.push(to_jsstring(lua_tostring(L, i) as unknown as string));
      else if (t === LUA_TNUMBER) parts.push(String(lua_tonumber(L, i)));
      else parts.push(`<type ${t}>`);
    }
    output.push(parts.join(" "));
    return 0;
  });
  lua_setglobal(L, to_luastring("log"));

  // fetch(url, timeoutMs) — SYNCHRONOUS, CF-aware default scrape.
  // bun fetch first; on challenge (403/503/202, cf-mitigated, awsWaf, "Just a moment")
  // falls back to /usr/bin/curl_chrome120 (Chrome TLS-fingerprint impersonation).
  // Returns the response BODY as the Lua value (usable in expressions).
  lua_pushcfunction(L, (L) => {
    const url = to_jsstring(lua_tostring(L, 1) as unknown as string);
    const timeoutMs = Number(lua_tonumber(L, 2) ?? 8000);
    const script = `const r = await fetch(${JSON.stringify(url)}, { signal: AbortSignal.timeout(${Math.min(timeoutMs, 10000)}) });
const text = await r.text();
const challenged = r.status === 403 || r.status === 503 || r.status === 202 || /Just a moment|Attention Required|challenge-platform|awsWaf|cf-error/i.test(text.slice(0, 2000));
console.log(JSON.stringify({ status: r.status, challenged, body: text }));`;
    const p = spawnSync("bun", ["-e", script], { encoding: "utf8", timeout: Math.min(timeoutMs + 2000, 15000) });
    let body = String(p.stdout || "").trim();
    let status = 0;
    try {
      const j = JSON.parse(body) as { status: number; challenged: boolean; body: string };
      status = j.status;
      body = j.body;
      if (j.challenged) throw new Error("challenge");
    } catch (e) {
      if (String(e).includes("challenge") || status === 403 || status === 503 || status === 202) {
        const cf = spawnSync("/usr/bin/curl_chrome120", ["-s", "-m", String(Math.min(timeoutMs / 1000, 10)), url!], { encoding: "utf8", timeout: Math.min(timeoutMs + 2000, 15000) });
        body = String(cf.stdout || "").trim();
        status = cf.status === 0 ? 200 : 0;
      }
    }
    output.push(`fetch ${url} -> ${status || "CF"} (${body.length} bytes)`);
    lua_pushstring(L, to_luastring(body));
    return 1;
  });
  lua_setglobal(L, to_luastring("fetch"));

  // exec(cmd) — synchronous shell
  lua_pushcfunction(L, (L) => {
    const cmd = to_jsstring(lua_tostring(L, 1) as unknown as string);
    const p = spawnSync("bash", ["-c", cmd!], { encoding: "utf8", timeout: 10_000 });
    output.push(`$ ${cmd}\n${p.stdout ?? ""}${p.stderr ?? ""}`);
    return 0;
  });
  lua_setglobal(L, to_luastring("exec"));

  // argos(query) — SYNCHRONOUS fuzzy search via child (config via env)
  lua_pushcfunction(L, (L) => {
    const q = to_jsstring(lua_tostring(L, 1) as unknown as string);
    const script = `const url = process.env.ARGOS_URL + "/" + process.env.ARGOS_INDEX + "/_search";
const r = await fetch(url, { method: "POST", headers: { "content-type": "application/json" }, body: JSON.stringify({ query: { multi_match: { query: process.env.ARGOS_Q, fields: ["*"], fuzziness: "AUTO", operator: "and" } }, size: 5 }), signal: AbortSignal.timeout(5000) });
const j = await r.json();
const hits = (j.hits?.hits ?? []).map(h => (h._source?.path || h._source?.name || h._id) + " (" + (h._score ?? 0).toFixed(2) + ")");
console.log(hits.join("\n"));`;
    const p = spawnSync("bun", ["-e", script], {
      encoding: "utf8", timeout: 10000,
      env: { ...process.env, ARGOS_URL: config.argosUrl, ARGOS_INDEX: config.argosIndex, ARGOS_Q: q ?? "" },
    });
    const out = String(p.stdout || "").trim();
    output.push(`argos "${q}": ${out.split("\n").length} hit(s)`);
    if (out) output.push(out);
    return 0;
  });
  lua_setglobal(L, to_luastring("argos"));

  // read(path)
  lua_pushcfunction(L, (L) => {
    const path = to_jsstring(lua_tostring(L, 1) as unknown as string);
    try {
      const data = readFileSync(path!, "utf8");
      lua_pushstring(L, to_luastring(data));
      return 1;
    } catch (e) {
      lua_pushstring(L, to_luastring(`ERR ${(e as Error).message}`));
      return 1;
    }
  });
  lua_setglobal(L, to_luastring("read"));

  // write(path, data)
  lua_pushcfunction(L, (L) => {
    const path = to_jsstring(lua_tostring(L, 1) as unknown as string);
    const data = lua_tostring(L, 2);
    try {
      writeFileSync(path!, data!);
      output.push(`wrote ${path} (${data!.length} bytes)`);
    } catch (e) {
      output.push(`write ERR ${(e as Error).message}`);
    }
    return 0;
  });
  lua_setglobal(L, to_luastring("write"));

  const script = to_luastring(req.script);
  const status = luaL_loadbuffer(L, script, script.length, to_luastring("run"));
  if (status !== lua.LUA_OK) {
    const err = lua_tostring(L, -1) ?? "compile error";
    return { ok: false, output: output.join("\n"), error: err };
  }
  const pcall = lua_pcall(L, 0, 1, 0);
  if (pcall !== lua.LUA_OK) {
    const err = lua_tostring(L, -1) ?? "runtime error";
    return { ok: false, output: output.join("\n"), error: err };
  }
  // capture return value
  let result: unknown = null;
  const t = lua_type(L, -1);
  if (t === LUA_TSTRING) result = to_jsstring(lua_tostring(L, -1) as unknown as string);
  else if (t === LUA_TNUMBER) result = lua_tonumber(L, -1);
  else if (t === LUA_TBOOLEAN) result = lua_toboolean(L, -1) === 1;
  lua_pop(L, 1);
  lua_close(L);
  return { ok: true, output: output.join("\n"), result };
}

