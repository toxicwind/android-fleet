# Token-walk field manual — Hisense marathon (2026-11-09)

## What a token walk IS (in the widest sense)

A token walk is any technique that pulls the *raw vocabulary* out of a system
instead of reading its grammar through a parser. Every binary, firmware blob,
dex, native library, log stream, or protocol contains strings — labels the
author left lying around — and those strings are tokens. Walking means: collect
the tokens wholesale, then read the collection like a corpus, looking for the
meaningful ones among the noise. It is the archaeologist's trench: you don't
read the building, you sift the dirt and inventory every shard.

There are many forms, and they differ by WHAT you walk:

- **`strings(1)` stroll** — the oldest and crudest: scan a file for runs of
  printable bytes, print them. Fast, rough, misses UTF-16, hits everything.
- **Dex string-pool walk** — what we did here. Android dex files keep a
  *string_ids table*: every literal the app uses is stored once, with a length
  and its bytes. Walking that table gives you the app's ENTIRE vocabulary with
  full fidelity — not just the printable runs, but every short token too.
- **Runtime walk** — attach (Frida, Xposed, `strace`, `ltrace`, logcat) and
  collect strings as they're *used*: what gets compared, concatenated, sent.
  Great when static tokens are encrypted or built dynamically.
- **Protocol walk** — sniff a socket (tcpdump on loopback) and inventory the
  frames the server actually emits, instead of reading its handler code.
- **Config/property walk** — dump every setting (`settings list`, `getprop`,
  `dumpsys`, NV-store keys) and diff against known-good.

All of them share the same discipline: **collect first, filter second, read
the survivors like evidence, never trust a single hit until you cross-check
it against the structure (the "AST") of the code around it.**

---

## How the dex walk works, concretely

A `.dex` file (Dalvik Executable — the compiled form of an Android app) begins
with a header that includes:

- `string_ids_size` (at byte offset 0x28): how many unique strings the app holds
- `string_ids_off` (at byte offset 0x2c): where the table of 4-byte offsets lives

For each entry in the table:
1. read the 4-byte offset → that's where the string's *length* is stored,
   encoded as a **ULEB128** (a variable-length integer: 7 bits per byte, high
   bit = "more bytes coming").
2. After the length come the string's bytes, in **MUTF-8** (a variant of UTF-8
   that also encodes the null character as a two-byte sequence, and uses 0x00
   as its terminator).
3. Decode and collect.

A tiny walker (30 lines of JS in our case — see the recipe at the bottom)
dumps every string in the app, `len >= 3`, to a flat file. On
`com.hisense.tv.smartservice` that was ~115,000 strings from ONE of its dex
files. The obfuscator had renamed every class and method to `a`, `b`, `c` —
but it cannot rename string constants without breaking the app, so the
vocabulary survives intact. That is the whole trick: **obfuscation hides
structure, not vocabulary.**

---

## The trap we almost fell into (read this before you panic)

Looking at the raw dump you meet walls like:

```
?1&2oa08--nx?apg6qpcbgm--nx?hbbgm--nx?rdceqa08--nx??2&8ugbgm--nx...
&gro?lim?moc?t&en?opsgolb,?ude?vog??abila?c?ihsot?m?n??c!...
```

It *looks* encrypted or malicious. It is neither. Linger on it and you can
read it like a map:

- the strings are stored **backwards**, one letter-group per token
- `?` marks the end of a token; `&` marks a branch point (a fork in a trie)
- `moc` read backwards = **com**; `gro` = **org**; `lim` = **mil**; `ude` vog` = **edu** + **gov**
- `--nx?` read backwards = **`xn--`**, the punycode prefix that real international
  domain names start with (`xn--80ao2a` is a real Russian-ish TLD, etc.)

In other words: this blob is **Mozilla's Public Suffix List**, compiled by
Google's Guava library (`InternetDomainName`) into a *reversed trie / DAWG*
(directed acyclic word graph) so the app can answer "is this a real TLD?"
without shipping a text file. It appears in the string pool because Guava was
bundled into the app. It has nothing to do with the TV's factory mode.

Rule learned: **identify the noise by its shape, not by its size.** A giant,
highly-structured, repeating blob is usually a *data structure in string form*
(list, trie, template, regex) — read it like that first; only then decide it's
the enemy.

---

## What the walk actually produced (and how we used it)

From `com.hisense.tv.smartservice` (the factory/control app, 4.3 MB), the
surviving tokens cluster into groups that read like a table of contents for
the TV's power system:

- **Power verbs** — `getPowerOnStatus`, `setStandby`, `setStandbyToAll`, `powerOnDeviceByLogicAddr`
- **Power messages** — `ACFG_MSG_POWER_ON`, `ACFG_MSG_POWER_OFF`
- **Power-mode constants** — `APP_CFG_POWER_CTRL_BOOT / LAST_STATUS / STANDBY`,
  plus the *hotel-market* variants `APP_CFG_HOTEL_POWER_CTRL_*`
- **RTC wakeup types** — `APP_CFG_RTC_WAKEUP_TYPE_NONE / 5AM / CAST_ONLY /
  CI_EMM_ONLY / DUMMY_BGM / OAD / RMDR / SCAN / SYSTEM_UPDATE`.
  This tells us the TV CAN schedule itself to wake at a time, possibly from
  deep standby — and that a server called **RMDR** (RemoteNow daemon) exists
  in the firmware's vocabulary.
- **Config keys** — `CFG_FACTORY_POWER_CTRL = "g_factory__power_ctrl"`,
  `CFG_HOTEL_HOTEL_POWER_CTRL = "g_hotel__hotel_power_ctrl"` — the NV-store
  names that factory menu writes into.
- **Upgrade JNI** — `TVNative.startRebootUpgrade_native(deliveryType)`:
  the native entry that reboots the TV into an upgrade/OTA flow.
- **Config resources** — `json_dynamic_domain.xml`, `ADDRESS.json` (server
  addresses for the cloud/IoT layer).

And a *null result* that matters: the 9001 port's server
(`com.hisense.udpserver.AIDLUdpScanService`) has obfuscated handlers that
contain only mundane `"socket"` strings — **no protocol literals at all.**
Cross-checked against the decompiled tree, that means the 9001 server builds
its command frames at runtime (or expects a handshake we haven't sent).
Conclusion: don't keep guessing payloads; **walk the traffic instead**
(tcpdump on loopback, replay the phone app's session, or hook the server).

---

## Cross-checking hits against the code (the "AST" step)

A token is a hypothesis, not a fact. Before acting on `startRebootUpgrade`:

1. Found the string in the pool → located the class that declares it
   (`MtkTvUpgradeBase` → `TVNativeWrapper` → `TVNative.startRebootUpgrade_native`)
   via the decompiled sources.
2. Found the *callers* of that method in the same source tree — which told us
   the upgrade flow (OTA app) is the one that invokes it, not some open API.
3. Cross-checked the live side: `ps`, `service list`, `cmd -l` showed no
   RMDR process and no TV shell-command service — so the RTC-wake and
   RemoteNow power lanes are real in firmware, but NOT reachable through the
   Android shell surface we have. That mismatch is itself the finding:
   the power-control surface is bound to the cloud/IoT SDK, not adb.

Every token therefore got a "structure verdict": *reachable from shell*,
*reachable only from an app with special permissions*, or *firmware-internal
only*. Verdicts live in SIDE-ANOMALIES.md.

---

## Recipe (reusable in ~30 lines)

1. `unzip -o app.apk 'classes*.dex'` (apps may have several dex files;
   walk each — the interesting control code was in `classes2.dex` here).
2. Walker: read `string_ids_size` (u32 @0x28) and `string_ids_off` (u32 @0x2c)
   from the header; for each of the `size` u32 offsets: ULEB128 length, then
   MUTF-8 bytes until terminator; keep `len>=3`.
3. Write the full corpus to a file (don't truncate — token walks are inventory,
   and you'll re-grep them with new eyes later).
4. Grep with *tight, domain-specific* regexes. Generic greps drown: the pool
   is dominated by bundled libraries (Guava, Firebase, okhttp, WorkManager).
   For this TV: `devicecontrol|getPower|setPower|RTC_WAKEUP|POWER_CTRL|json|9001|RMDR`.
5. Treat each hit as a lead, then verify in the decompiled tree (which classes
   reference it? who calls it? is it exported?) and against the live device
   (ps, service list, cmd -l, settings). A token that matches no live surface
   is still data — it says the capability exists but is gated somewhere.

## Other walk forms to reach for next

- **Traffic walk**: tcpdump loopback while driving the phone's RemoteNow app.
- **Runtime walk**: frida `enumerateLoadedClasses` + string class dump on the
  smartservice process; or `logcat` verbatim around a factory-menu toggle.
- **NV/config walk**: `settings list`, `getprop`, factory store dump — pair
  each token (e.g. `g_factory__power_ctrl`) with its live value and change
  one bit at a time to map read/write behavior.
- **Strings-file walk**: resources.arsc / assets JSON (like `json_dynamic_domain.xml`)
  often hold endpoint + feature-flag vocabulary not present in dex at all.