# 04 Audio — devices, HAL routing, radio/audio-only lanes (SDK-deep)

## Audio devices (live `dumpsys audio` 2026-11-08)
- speaker (2) — internal
- headset (4), headphone (8)
- bt_a2dp (80) — Bluetooth audio
- hdmi (400) — HDMI output
- usb_device (4000), usb_headset (4000000)
- **hmdi_arc (40000)** — HDMI-ARC return (the AVR lane)
- default (40000000)
Current routed device at capture: `speaker` (may follow input source; ARC available).

## Apps / media
- `com.hisense.audio_update_service` — vendor audio update service
- Media stack: audio_hal logs tag the RUNNING app (`running app: com.nuvio.livetv`)
  → an AUDIO-side liveness signal that works headless.

## Audio-only / radio verification
- While a stream plays, `logcat -d | grep audio_hal` shows the app token;
  `dumpsys audio` shows the focused/active stream.
- The AVR path: route via HDMI-ARC and the panel state becomes irrelevant for
  hearing (audio-only listening with overlay black = radio mode).

## Notes
- Volume/brightness are independent layers; audio does NOT pause with the
  black overlay (only SLEEP pauses).
- No native "screen off with music" activity found (settings/secure/system keys
  probed); the overlay+ARC combo is the radio mode.