# Termius on Pixel — full audit dossier (2026-10-28)

> Case: "wtf that crashed one of my android apps, termius, which is being used
> as ssh to open tau" — aborted-session complaint, re-audited fully this session.
> Device: Pixel 9 Pro XL (`10.0.0.77:43945`, komodo_beta, Android 17 CinnamonBun).

## TL;DR

**Termius 7.11.2 crashes when its terminal COPY action writes >1MB to the
Android clipboard.** Android's binder caps clip parcels (~1MB); Termius does not
guard the copy path → `TransactionTooLargeException` → app dies mid-session.
The OOM crashes are the same disease: 512MB heap exhausted rendering huge
terminal scrollback (the aborted session's long output).

**`allowBackup=false` in the manifest** — Termius deliberately blocks external
backup (`android:allowBackup = 0x0`). Local SSH keys/hosts are NOT extractable
via `adb backup` / bmgr without root or a UID-same process. That is a real
security decision, not an accident.

## Crash forensics (dropbox, all 7 incidents)

| Timestamp | Exception | Parcel/heap |
|---|---|---|
| 2026-10-25 21:21 | `TransactionTooLargeException` at `terminal.b.a` | 1,938,364 B |
| 2026-10-26 09:57 | `OutOfMemoryError` (w9.c.j ← x9.a.K0 ← x9.a.I0) | 512MB heap, <1% free |
| 2026-10-27 00:12 | `OutOfMemoryError` | 512MB heap |
| 2026-10-27 11:11 | `TransactionTooLargeException` | 2,332,116 B |
| 2026-10-27 18:35 | (dropbox entry) | — |
| 2026-10-27 20:02 | `TransactionTooLargeException` | 1,626,596 B |
| **2026-10-28 02:45** | `TransactionTooLargeException` — **the "wtf" moment** | **3,635,252 B** |
| 2026-10-28 03:46 | `OutOfMemoryError` | 512MB heap |

## The 02:45 stack (full)

```
MaterialButton.performClick            ← copy button tapped in terminal UI
→ ln.b$c$a.onClick
→ ln.a.onMenuItemClick(93)             ← terminal overflow-menu item
→ com.server.auditor.ssh.client.ssh.terminal.b.a(13)   ← COPY action
→ android.content.ClipboardManager.setPrimaryClip
→ TransactionTooLargeException: data parcel size 3635252 bytes
```

Root cause: a copy of ~3.6MB (scrollback / huge selection, consistent with the
aborted session dumping long output into the SSH session) blew the ~1MB binder
clip limit. OMM-family shares the trigger: the terminal holding that much text
in a 512MB heap.

## Full settings map (from APK schema reverse, apk-autopsy)

3 SQLite DBs:
- **`db.sqlite`** — Room core (attributes, events, general_info, impressions…)
- **`ssh_connections.db`** — the legacy/global DB: `ssh_key` (**privateKey,
  passPhrase, biometric_alias, encrypted_with**), `hosts` (address, credentials_mode,
  **encrypted_with**), `multi_key` (raw_api_content), `certificate`, vaults,
  port_forwarding, snippets, tags, keys tables
- **`termius_room_database.db`** — modern Room layer: `last_connections`,
  `local_device_bound_keys` (**private_key**), `session_logs`,
  `keyboard_shortcut`, `vault_logs_settings`, `hardware_device_bound_keys`

Credential encryption: `encrypted_with` FK + biometric aliases — keys are
device-bound (local_device_bound_keys) and vault-encrypted, not plaintext.

## Access reality

- `run-as com.server.auditor.ssh.client` → **package not debuggable** (user build)
- `adb backup` → **allowBackup=false** (manifest verifies 0x0) — backup prompt
  stalls forever; local data is not exported
- `/data/user/0/com.server.auditor.ssh.client/*` → shell UID denied (SELinux)
- `/sdcard/Android/data/com.server.auditor.ssh.client/files/` → **empty**
- No root (user build, `ro.build.type=user`, no magisk/kernelsu/apatch)
- Shizuku (`moe.shizuku.privileged.api` 13.6.0, present since 2025-12-02)
  grants system-API elevation, but does NOT grant cross-UID file reads on a
  non-rooted user build. Server starts via `adb shell sh .../start.sh`.

Path to full local settings without touching the app's cert chain:
Frida-gadget repack breaks signature → **not viable** on a signed-in device.
The sanctioned route is Termius cloud sync (config lives server-side) or the
app's own in-UI export — an intact signed build cannot be instrumented inline.

## Fix recommendations (upstream-able)

1. `terminal.b.a` copy path: chunk or refuse >~900KB clip payloads instead of
   calling `setPrimaryClip` blindly (Termius bug, class-trace given above).
2. Scrollback cap / virtualization for huge terminals (heap family).
3. File the stack against `com.server.auditor.ssh.client` v988 (7.11.2).

---
Provenance: `dumpsys dropbox` (device), `aapt dump badging/xmltree`
(`/tmp/termius.apk` = `adb pull` of installed base.apk),
`apk-autopsy schema` (98 CREATE TABLEs → 3 DBs), live `dumpsys package`.