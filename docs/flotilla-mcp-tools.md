# flotilla MCP — tool reference (14 tools)

> Server: `pens/ranch/android-fleet/flotilla/mcp/server.ts` → `:25245/mcp`
> Federated via thresher (`:25127`), streamable-HTTP JSON-RPC (taas.ts shape).
> Device: Pixel 9 Pro XL via wireless ADB (auto-rescue rotated port).

## Input / focus tools (v1, focus-covenant)

| Tool | Purpose |
|---|---|
| `pixel_connect` | Ensure ADB to the Pixel; auto-rescues rotated wireless-debug port via mDNS/nmap. |
| `pixel_screenshot(file)` | Screencap → PNG on the box; returns `{file, bytes, focus}`. |
| `pixel_open(url)` | Headless http VIEW intent → Firefox (sole default, no chooser). |
| `pixel_openshot(url,file)` | Open then screencap once browser holds focus (polls ≤4s, no fixed sleeps). |
| `pixel_tap(x,y)` | Tap. FOCUS DOCTRINE: returns focus with every result. |
| `pixel_text(text)` | Type into focused window; returns focus. |
| `pixel_key(keycode)` | Keyevent (4=BACK, 66=ENTER, 111=ESC, 3=HOME); returns focus. |
| `pixel_ui()` | UI hierarchy XML dump (tap targets/labels/bounds) — safe prelude to any tap. |
| `pixel_focus()` | Which window owns the screen. |
| `flotilla_float(url)` | Freeform floating window over the user's SSH session (windowingMode 5). |

## Audit / forensics tools (v2, added 2026-10-28)

| Tool | Purpose |
|---|---|
| `pixel_shell(command)` | **Arbitrary adb shell, output ≤60KB.** The audit escape hatch: package/process/settings forensics past the focused tools. No `2>&1` merges. |
| `pixel_package(pkg)` | Deep package dive: version, install/update times, enabled/stopped, dataDir, granted permissions. |
| `pixel_appstate(pkg,process?)` | Live process state + RSS + recent dropbox crash entries for the package. "Is it running / what is it doing / has it crashed" in one call. |
| `pixel_shared(pkg,pattern?)` | Rootless shared-storage footprint: `/sdcard/Android/data/<pkg>` tree + `/sdcard` filename matches. |

## Use patterns (verified)

- Termius-type audit: `pixel_package` → `pixel_appstate` → `pixel_shared` →
  `pixel_shell` (dropbox/settings deep dive), then APK pull + `apk-autopsy schema`.
- The 02:45 Termius `TransactionTooLargeException` crash was fully
  root-caused using `pixel_appstate` + `pixel_shell` dropbox dumps — see
  `docs/termius-audit-20261028.md`.

## Development notes

- New tool = add to the `tools` array in `server.ts`; restart daemon
  (`pitchfork restart estate/flotilla-mcp`; stale PID on :25245 needs a kill —
  latest known gap, fixed by kill+start).
- Port SSOT: `FLOTILLA_MCP_PORT=25245` in `config/ports.env`.
- Rebuild check: `bun build flotilla/mcp/server.ts --target=bun`.