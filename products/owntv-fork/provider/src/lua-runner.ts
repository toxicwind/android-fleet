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
const { lua, lauxlib, lualib, to_luastring } = fengari;
const { luaL_loadbuffer, lua_pcall, lua_getglobal, lua_pushstring, lua_setglobal, lua_tostring, lua_pop, lua_pushcfunction, lua_tonumber, lua_type, LUA_TSTRING, LUA_TNUMBER, LUA_TBOOLEAN } = fengari;
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
  const L = lauxlib.luaL_newstate();
  lualib.luaL_openlibs(L);

  // log(...) — capture to output
  lua_pushcfunction(L, (L) => {
    const n = lua_gettop(L);
    const parts: string[] = [];
    for (let i = 1; i <= n; i++) {
      const t = lua_type(L, i);
      if (t === LUA_TSTRING) parts.push(lua_tostring(L, i));
      else if (t === LUA_TNUMBER) parts.push(String(lua_tonumber(L, i)));
      else parts.push(`<type ${t}>`);
    }
    output.push(parts.join(" "));
    return 0;
  });
  lua_setglobal(L, to_luastring("log"));

  // fetch(url, opts) — bun fetch, returns status + text
  lua_pushcfunction(L, (L) => {
    const url = lua_tostring(L, 1);
    const timeoutMs = Number(lua_tonumber(L, 2) ?? 8000);
    void (async () => {
      try {
        const r = await fetch(url!, { signal: AbortSignal.timeout(timeoutMs) });
        const text = await r.text();
        output.push(`fetch ${url} -> ${r.status} (${text.length} bytes)`);
      } catch (e) {
        output.push(`fetch ${url} -> ERR ${(e as Error).message}`);
      }
    })();
    return 0;
  });
  lua_setglobal(L, to_luastring("fetch"));

  // exec(cmd) — synchronous shell
  lua_pushcfunction(L, (L) => {
    const cmd = lua_tostring(L, 1);
    const p = spawnSync("bash", ["-c", cmd!], { encoding: "utf8", timeout: 10_000 });
    output.push(`$ ${cmd}\n${p.stdout ?? ""}${p.stderr ?? ""}`);
    return 0;
  });
  lua_setglobal(L, to_luastring("exec"));

  // argos(query) — fuzzy search
  lua_pushcfunction(L, (L) => {
    const q = lua_tostring(L, 1);
    void (async () => {
      try {
        const hits = await searchArgos(config, q!, 5);
        output.push(`argos "${q}": ${hits.length} hits`);
        for (const h of hits.slice(0, 5)) output.push(`  ${h.path ?? h.name ?? h.id} (${h.score.toFixed(2)})`);
      } catch (e) {
        output.push(`argos ERR ${(e as Error).message}`);
      }
    })();
    return 0;
  });
  lua_setglobal(L, to_luastring("argos"));

  // read(path)
  lua_pushcfunction(L, (L) => {
    const path = lua_tostring(L, 1);
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
    const path = lua_tostring(L, 1);
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
  if (t === LUA_TSTRING) result = lua_tostring(L, -1);
  else if (t === LUA_TNUMBER) result = lua_tonumber(L, -1);
  else if (t === LUA_TBOOLEAN) result = lua_toboolean(L, -1) === 1;
  lua_pop(L, 1);
  lauxlib.lua_close(L);
  return { ok: true, output: output.join("\n"), result };
}

function lua_toboolean(L: any, idx: number): number {
  // fengari exposes lua_toboolean via lua.lua_toboolean
  return lua.lua_toboolean(L, idx);
}
