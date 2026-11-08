# PlayTorrio source audit — Xtream finder (2026-11-09)

Source: `/home/toxic/tmp/pt-src` = `hgus7089/PlayTorrioV3-TW` (Flutter),
cloned 2026-11-08. Public source exists → no deobfuscation needed (the earlier
"audit its xtream finder" ask).

## Finder (`lib/services/iptv/`)
- `iptv_network.dart` — Xtream-Codes `player_api` client.
  - `IptvClient.login`: auth = `auth==1 || status==active || user_info present` — same contract as our seeder.
  - `IptvVerifier.verifyUntil`: bounded concurrency (4 workers), aborts at `target` alive portals —
    the "finder" = bulk-verify scraped credentials, **no stream-path/capacity probe** → the exact gap
    our `StreamProbe` + `XtreamCapacityGate` close. Their `verifyOrNull` would mark cabtx-style
    N1-gated portals alive.
  - `_isAlive(url)`: stream liveness = GET with **`VLC/3.0.20 LibVLC/3.0.20` UA + `Range: bytes=0-N`**
    + followRedirects + 2xx/206 + dead-content-type filter — the ONE good pattern here: it matches
    precisely the VLC-UA 302→token→LB unlock we proved independently (m3u8 200/2828B + ts 200 video/mp2t 3.6MB).
- `iptv_controller.dart`: channel scan tokens (`_channelScanToken`) guard stale async scans —
  a nice concurrency pattern; hardcoded_channels (sports keywords) fallback.
- `iptv_storage.dart` / `pastesh_decryptor.dart`: portal paste-import + decryptor.
- `m3u_parser.dart`/`m3u_store.dart`: M3U support (parallel to our M3uParser).

## Credential source (matches recon)
`xtreamity-plus-db.csv.gz` endpoints baked in:
- `https://pub-38f23eb5f3304328b9774fadfa233a38.r2.dev/xtreamity-plus-db.csv.gz`
- `https://s3.us-west-004.backblazeb2.com/Xtream-STBemu/xtreamity-plus-db.csv.gz`

## Verdict
- Borrow: the VLC-UA+Range stream check (we adopted into StreamProbe.VLC_UA).
- Gap confirmed in the finder: login-verifier without stream/capacity classification
  (our fleet audit + gate are the corrected design).