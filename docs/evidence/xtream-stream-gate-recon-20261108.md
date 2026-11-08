# Xtream stream-gate recon — WHY streams were "locked" (2026-11-08)

## The question
All traffic to /live/ on cabtxNjD's panel returned **407 Proxy Authentication
Required** with `N1-Backend: Echo` + random `N1-Request` ids. Proxies/geo were red herrings: the lock is the panel's own stream edge.

## Evidence
- `player_api.php` (API path) → 200 auth=1 for all 56 alive portals.
- `/live/user/pass/id.m3u8` on the SAME server+port → 407 (N1 edge) for every client: bare curl, curl_chrome116 (CFFI), through 130 free proxies, with Basic auth, token param, Browser UA, Referer, ports 80/8080/8888, HTTPS. pass=0/130 + 0/30 seeded.
- Port 2095 root → 401 (XUI panel locked).
- Stream-path audit of ALL 56 portals: **only android-iptv.com:80 returns stream bytes** (sid=42558).
- android-iptv flow (the WORKING pattern): `/live/...m3u8` → **302 Found** → `Location: http://lb-4.android-iptv.com:80/live/...?token=<panel-generated>`. Following with curl → **403** from the LB.

## Verdict
1. Xtream `/live` is fronted by a **player-gated edge** (N1 on cabtx, token-LB on android-iptv). API auth does NOT grant stream access; the panel mints per-request tokens/redirects bound to a privileged client (real players: nuvio/ExoPlayer on the TV).
2. Bare-curl / proxy-curl will never unlock player-gated edges -> the proxy-rotator saga was a red herring for THIS class.
3. The estate's privileged client = nuvio on the Hisense TV (its ExoPlayer flow is what the panel accepts).

## Actionable
- Stream classification (api-ok/stream-ok/stream-407) must be part of portal audits; ONE portal is stream-ok today (android-iptv.com).
- Gate already shipped: saturated-portal capacity gate (fleet-tv XtreamCapacityGate) prevents the disconnect fight.
- Proxy-rotator stays for genuine geo-gates (different class; verify against a target that returns 2xx-without-body, not 407-gated edges).
