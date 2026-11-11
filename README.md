<h1 align="center">🐑 android-fleet</h1>

<p align="center"><b>ADB-managed Android devices on the LAN</b> — the Architect's Pixel 9 Pro XL and the Hisense SmartTV 4K FFM (OwnTV IPTV host), reachable from every agent, headlessly, at 2am.</p>

<div align="center">

[![license: MIT](https://img.shields.io/badge/license-MIT%20%2B%20upstream-blue?style=for-the-badge)](https://github.com/toxicwind/estate-scripts-projects#license)
[![pixel: wireless](https://img.shields.io/badge/pixel%20wireless%20debug-OK-22c55e?style=for-the-badge)]()
[![hisense: owntv](https://img.shields.io/badge/hisense%20owntv-v99.99.99-22c55e?style=for-the-badge)](products/owntv-fork/)
[![flotilla mcp](https://img.shields.io/badge/flotilla%20mcp%20%3A25245-live-6e56cf?style=for-the-badge)](docs/flotilla-mcp-tools.md)
[![luaj-dropin](https://img.shields.io/badge/luaj%20dropin-%3A38383-live-6e56cf?style=for-the-badge)](products/luaj-dropin/)
[![ui automation](https://img.shields.io/badge/ui%20automation-uiautomator%20headless-22c55e?style=for-the-badge)](docs/ui-automation.md)

</div>

---

## 📑 Table of contents

- [Why this exists](#-why-this-exists)
- [Devices](#-devices)
- [Products (consolidated)](#-products-consolidated)
- [The headless route](#-the-headless-route)
- [UI automation & the semantic bridge](#-ui-automation--the-semantic-bridge)
- [MCP surface — flotilla](#-mcp-surface--flotilla)
- [The arsenal (rebranded black-tier tooling)](#-the-arsenal-rebranded-black-tier-tooling)
- [Forensics dossiers](#-forensics-dossiers)
- [SSOT](#-ssot)
- [Quick start](#-quick-start)
- [License & security](#-license--security)

---

## 🎯 Why this exists

The fleet's reach ends where ADB begins — unless someone holds the sessions open.
Wireless debugging ports rotate, TVs need on-screen approval, and a dropped
session at 2am means no deploys until morning. This project keeps both devices
reachable: a **keepalive daemon** that rediscovers the Pixel's rotating port,
and **documented, approved access to the Hisense TV** that unlocks direct
OwnTV deploys, the Lua control plane, and headless UI automation.

> **Doctrine:** additive-only. The keepalive never touches other adb devices;
> pair keys are credentials — never committed, never shared.

---

## 📱 Devices

| | Device | Endpoint | Access | Notes |
|---|---|---|---|---|
| 🟢 | **Pixel 9 Pro XL** *(the Architect's phone)* | `10.0.0.77:<rotates>` | wireless debugging · paired | keepalive holds the session; port rediscovered via fast scan on rotation |
| 🟢 | **Hisense SmartTV 4K FFM** `wuyishan` *(bedroom)* | `10.0.0.225:5555` | ADB authorized 2026-09-18 | **OwnTV IPTV host** — the primary IPTV app + Lua control plane |
| 🟡 | **LG webOS TV** | developer mode / Homebrew Channel | webOS `ares` CLI over SSH | not ADB — see `../webos/lg-fleet/` |

📄 Full reference: [`ssot/devices.md`](../../ssot/devices.md) · [`docs/hisense-wuyishan.md`](docs/hisense-wuyishan.md)

---

## 📦 Products (consolidated)

All Android device work lives under [`products/`](products/) — every MCP server,
IPTV app, and control plane, in one place.

| Product | Port | What it is |
|---|---|---|
| [`owntv-fork/`](products/owntv-fork/) | — | **OwnTV IPTV fork** (v99.99.99 on the Hisense) — AIOStreams search, hot-reload, semantic UI automation |
| [`luaj-dropin/`](products/luaj-dropin/) | `:38383` | **Lua-over-HTTP control plane** for any Android app (SQLite, WorkManager, shell, protobuf) |
| [`nuvio/`](products/nuvio/) | — | Nuvio IPTV apps (Android TV build on the Hisense) |
| [`android-mcp/`](products/android-mcp/) | — | ADB device/UI/app control MCP; device profiles (Hisense TV, Pixel) |
| [`android-activity-mcp/`](products/android-activity-mcp/) | — | activity enumerate/cache/launch over ADB |
| [`phone-mcp/`](products/phone-mcp/) | — | phone control MCP (ADB commands) |
| [`fennec-cdp/`](products/fennec-cdp/) | — | Fennec (Firefox for Android) RDP→CDP on the Pixel |
| [`newblackdex/`](products/newblackdex/) | — | non-root dex unpacking tooling |

---

## 🛰 The headless route

The complete **zero-hands** path from "agent on the box" to "acting on the phone":

1. **Transport** — wireless-debug ADB; `bin/pixel-adb-keepalive.sh` (pitchfork)
   re-scans 30000-50000 every 60s so `adb devices` is normally already live.
   Rescue: mDNS (`adb mdns services`) → nmap fallback.
2. **Firewall** — the box drops LAN input by policy (`/etc/nftables.conf`,
   `inet awrawr_filter`). Any port the phone must hit goes in the `10.0.0.0/24`
   tcp set. **Box-side curls never traverse this path** — probe from the phone.
3. **Routing** — http VIEW intents resolve to Firefox (`org.mozilla.fenix`) as
   the **SOLE default**: `am start -d <url>` is headless, no chooser ever appears.
4. **Tools** — `flotilla/mcp/server.ts` (`:25245`, thresher-federated as
   `flotilla:*`): screenshot / tap / text / ui-dump / headless-open with the
   **focus covenant** — every input tool returns the focused window.

---

## 🧪 UI automation & the semantic bridge

The OwnTV fork's Compose UI was a **semantic black box** to uiautomator — the
rail had `focusGroup()`, zero `testTag`, zero `testTagsAsResourceId`. We fixed it:

- `testTagsAsResourceId = true` at the root + `testTag()` on rail items →
  uiautomator sees all 9 rail buttons as `android.widget.Button`.
- `stageClickable` uses `semantics(mergeDescendants=true)` → every interactive
  element exposes its label + click to uiautomator.
- Full headless flow verified: **rail Search → type "ruin" → 21 results
  (Movies 16 / Live TV 2 / Series 3) → select Blue Ruin → Play**.

📄 The complete lane: [`docs/ui-automation.md`](docs/ui-automation.md) + the
`owntv-compose-ui-automation-semantics` managed skill.

---

## 🧿 MCP surface — flotilla

`flotilla/mcp/server.ts` on `:25245`, federated into the estate's MCP gateway
(thresher `:25127`).

| Tier | Tools |
|---|---|
| **Input (focus-covenant)** | `pixel_screenshot` · `pixel_tap` · `pixel_text` · `pixel_key` · `pixel_ui` · `pixel_open` · `pixel_openshot` · `pixel_focus` · `pixel_connect` |
| **Audit / forensics (v2)** | `pixel_shell` · `pixel_package` · `pixel_appstate` · `pixel_shared` |
| **Float** | `flotilla_float` (picture-in-picture over the SSH session) |

📄 Full tool reference: [`docs/flotilla-mcp-tools.md`](docs/flotilla-mcp-tools.md)

---

## ⚔️ The arsenal (rebranded black-tier tooling)

> "Black" is a color, not a verdict. These are the non-mainstream, hacker-tier
> utilities the estate **actually uses** — renamed into the fleet so the name
> never leaks the original's bad reputation, and wired for the Architect's own
> devices only.

| Tool | Rebrand in-fleet | What it does for us | Status |
|---|---|---|---|
| [**Shizuku**](https://github.com/RikkaApps/Shizuku) | `pixel-priv-bridge` | spawns a privileged `app_process` → system-API elevation for non-rooted phones | installed on Pixel |
| [**BlackDex**](https://github.com/CodingGay/BlackDex) | `apk-unpack-vault` | non-root dex unpacking | installed + live on Pixel |
| [**apkeep**](https://github.com/EFForg/apkeep) | `apk-provenance` | clean APK acquisition | `~/.cargo/bin/apkeep` |
| [**Frida gadget**](https://github.com/kiks7/frida-non-root) | `in-house-instrument` | dynamic instrumentation of **our own** builds | 🔬 next build |
| [**STF / DeviceFarmer**](https://github.com/DeviceFarmer/stf) | `device-farm` | browser-based multi-device fleet control | 🗺 roadmap |

**Why not raw root?** The Pixel is `ro.build.type=user` (not debuggable, no
bootloader unlock). Shizuku + the sandbox lane are the *reachable* elevations
on a daily-driver user build.

---

## 🕵️ Forensics dossiers

| Dossier | What it proves |
|---|---|
| [**Termius on Pixel — full audit**](docs/termius-audit-20261028.md) | the "wtf crashed my termius" case, root-caused: terminal copy >1MB blows the clipboard binder (→ `TransactionTooLargeException`), 512MB heap OMM family. |
| [**Hisense wuyishan deep recon**](docs/hisense-wuyishan.md) | full device profile: mt5895 SoC, `user` build, NO root path, IPTV schema reverse. |
| [**Shizuku black-screen evidence**](docs/evidence/2026-11-08-shizuku-black-screen/) | the black-screen unlock lane on the Hisense. |

Write-up rule: dossiers go in `docs/` with the date, carry **provenance**
(which tool/command produced which fact), and say what was *actually*
observed vs inferred.

---

## 🗂 SSOT

- [`ssot/devices.md`](../../ssot/devices.md) — device inventory (IPs, ADB, codenames)
- [`ssot/ports.env`](../../ssot/ports.env) — the numeric port registry

---

## 🚀 Quick start

```bash
# Pixel (port rotates — keepalive tracks it)
adb -s 10.0.0.77:<port> shell

# Hisense TV — OwnTV host (fixed port)
adb -s 10.0.0.225:5555 shell
adb -s 10.0.0.225:5555 shell dumpsys package tv.own.owntv | grep versionName  # → 99.99.99

# Lua control plane (luaj-dropin on the TV)
curl http://localhost:38383/status   # via adb forward tcp:38383 tcp:38383

# Headless UI automation (uiautomator)
adb -s 10.0.0.225:5555 shell uiautomator dump /sdcard/ui.xml
```

---

## 🛡 License & security

MIT where marked. ADB is **root-equivalent on the device** — these are the
Architect's personal devices on his LAN. The pairing keys
(`/home/toxic/.android/adbkey*`) are **credentials: never commit, never share.**
Keepalive is additive-only by design.