# AGENTS.md — owntv-fork

**Root:** `/home/toxic/pens/fleet/devices/android-fleet/products/owntv-fork`
**Stack:** Kotlin/Android (`build.gradle.kts`), plus a TypeScript Xtream provider (`provider/`).

## Network reality — read this before touching any host or port

| Host | What it is | Evidence |
|------|-----------|----------|
| `10.0.0.218` | **This PC** (the dev box). Runs the provider gateway. | `ip -4 addr` → `10.0.0.218` on `enp12s0` |
| `10.0.0.225:5555` | **Hisense SmartTV 4K FFM** (the device under test), armeabi-v7a, Shizuku v13.6.0 | live: MAC `38:64:07:f0:48:56`, REACHABLE, tcp/5555 open |
| `10.0.0.77:<port>` | Pixel 9 Pro XL test device — **port rotates**, never hardcode | `plan.md:24` |

Do not confuse these. `.225` is the TV, **not** the PC. `.218` is the PC, **not** the TV.
`adb connect` targets go to `.225` (or `.77`); provider URLs go to `.218`.

## Ports

Provider gateway (Xtream): **`25818`**, bound `0.0.0.0`.

```bash
curl -s http://127.0.0.1:25818/health          # on the PC
curl -s http://10.0.0.218:25818/health         # from the TV / another host
```

Override with `PROVIDER_PORT`, not by editing `config.ts`:

```bash
PROVIDER_PORT=25818 ./provider/launch.sh
```

Source of truth: `provider/src/config.ts` (`PROVIDER_PORT ?? "25818"`).
Mirrors: `provider/launch.sh`, `provider/README.md`, `mcp/server.ts`,
`provider/test/{config,portal-server}.test.ts`.

> History: this was `8081`. It moved into the `25xxx` estate range on
> 2026-11-16. `8081` collided with unrelated dev servers and sat outside the
> scheme. Do not reintroduce `8081`.

## Rules

1. **Never scan or search `tools/decompiled/**`.** Decompiled third-party bytecode
   (Cloudstream, Compose, OkHttp). Numeric arrays and class names like `C08081`
   make a naive `rg 8081` return thousands of false positives. Always pass
   `--glob '!tools/decompiled/**'`.
2. **Use `rg -uu` under `pens/`.** The parent `.gitignore` contains `*`, so plain
   `rg` silently skips everything and reports false "no matches".
3. **`10.0.0.2` / `10.0.0.3` / `10.0.0.5` in `repo/core/.../test/` are test
   fixtures**, not hosts. `LocalAutoSyncTest`, `CompanionLinkTest`, and
   `ContentHashV43Test` use them as arbitrary private-LAN strings. Leave them.
4. **Build before claiming.** `bun build provider/src/config.ts --target=node`
   for the provider; the Android side uses `build.gradle.kts` — read it, do not
   assume a shared command.
5. **Report nested-repo status per repo.** `git -C <repo> status --porcelain
   --branch`, never from the parent.

## Provider

- Defaults: `master/master` on `0.0.0.0:25818`.
- Log: `provider/provider.log` (repo-local; `PROVIDER_LOG` overrides). Never
  write logs to `/tmp` — this box's `/tmp` is tmpfs and gets wiped.
- MCP surface: `mcp/server.ts` exposes provider health to the MCP gateway.
- Deploy: `tools/owntv-deploy.sh`, `tools/hisense-deploy.ts` (both target
  `10.0.0.225:5555`).