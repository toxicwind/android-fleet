# Side anomalies — Hisense marathon (tracked, 2026-11-08/09)

Every non-task anomaly worth remembering from the overnight run:

| # | anomaly | evidence / root-cause | status |
|---|---|---|---|
| 1 | **android-mcp "fetch failed" on Hisense** | `DEFAULT_DISPLAY=2` Pixel-desktop hardcode; Hisense is display 0 → every call crashed the stdio server | FIXED (`defaultDisplayFor` profile-aware, android-mcp dbfb7cb; second MCP `android-mcp-hisense` added) |
| 2 | **FacRootMenu launch kills wifi** | factory app dex has `setWifiEnabled` (its network factory test toggles radio) | DOCUMENTED; hostile app — enter via targeted sub-activities only |
| 3 | **Power key while factory app = full reboot** | user-verified twice; power handled as reboot in factory mode | documented in ROOT-APPLY (rollback=reboot) |
| 4 | **Keeper survives TV reboot** | TV rebooted several times overnight; keeper daemon healthy; Shizuku re-armed only when netlink REACHABLE (see keeper event logs) | static verified; end-to-end on awrawr-pc reboot = planned last-act |
| 5 | **Compose brightness ≠ panel brightness** | settings/`cmd display` only dim compose; panel PWM factory-owned | documented (display-panel doc) |
| 6 | **Streams: api-auth ≠ playable** | N1 edge 407s all; only android-iptv streams (VLC UA) | documented + seeder/gate/max-auto |
| 7 | **proxmint list = dict shape** and rate-limit flakiness | 429 API; `rows` key | rate-limiter added in proxy-rotator |
| 8 | **flotilla pixel_ui / display-2 defaults** in gateway tool sigs | Pixel desktop only | leave; Hisense lanes separate |
| 9 | **android-activity-mcp list-activities** worked instantly for uifac | activity-mcp OK; enumerates hidden singles cleanly | keep as the activity lane |
| 10 | **/data usage improved** 76% → 68% | measured 2026-11-08 | monitor |

Explore later (todo-tracked): zuli provider speed, proxmint page caps, CVE research lane for MTK-11 if USB never comes, keeper boot-prompt post-reboot verification, StreamProbe parallel runtime.

| 11 | **AI Gateway key rot (Vercel)** | `AI_GATEWAY_API_KEY` (`vck_…`) 401s at ai-gateway.vercel.sh; `~/.bashrc:200-202` wires Copilot to it. Fix: rotate at Vercel AI api-keys (human step) — bashrc reads env automatically; helper: `estate-ops bin/ai-gateway-rotate.sh`. | OPEN (needs dashboard) |

| 12 | **CVT lane mis-armed: `sm format` hit INTERNAL eMMC, not the USB stick** | `disk:8,0` = `/dev/block/sda` = the TV's INTERNAL 116G eMMC (mmcblk0, exported as SCSI). `sm format public:8,1` FORMATTED the internal partition; `touch /storage/7EA6-0BF6/FAC_BOOT_SMART.cvt` wrote to the TV's OWN storage (`df` = 116G). NO USB stick has EVER been visible: `/dev/block` has only mmcblk0 + sda (no sdb/sdc); `/proc/partitions` shows nothing. "cvte-logger never fired" = CORRECT: there was never a stick for it to see. ROOT-APPLY "ARMED 04:13" entry is INVALID — see corrected lane below. | OPEN (need a REAL stick, verified via `ls /dev/block/sd*` + `sm list-disks` BEFORE planting) |

**CVT-lane correction (verified 2026-11-09 04:4x):** the boot-time `cvte-logger.sh` mount-and-check happens in init, BEFORE vold — it mounts the REAL USB stick itself to `/mnt/usb` and looks for `/mnt/usb/FAC_BOOT_SMART.cvt` (root-rollback-effect.md path matches). Validation checklist BEFORE believing a plant:
  1. `adb shell ls /dev/block/ | grep -E "sd[bc]|sda[0-9]"` → a REAL second device must exist (sdb/sdc).
  2. `adb shell sm list-disks` → the disk id must NOT be 8,0 (8,0 = internal eMMC).
  3. `adb shell df -h /storage/<VOL>` → mount < 64G (a stick), never 116G.
  4. Only then: file at the FAT32 ROOT, `sync`, cold boot (wall), verify `mount | grep ' / '` rw.