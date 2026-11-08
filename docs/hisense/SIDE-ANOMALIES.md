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