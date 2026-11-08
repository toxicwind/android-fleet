# 01 Platform — SoC / firmware / kernel / partitions (SDK-deep)

## Identity (verified live)
- Marketing: SmartTV 4K FFM · `ro.vt.product.model` = **55E82GUA** · codename **wuyishan**
- SoC: MediaTek **MT5895** (board MT9900), 4× Cortex-A73, 32-bit userspace (`armeabi-v7a`, zygote32)
- Android 11, SDK 30 · build `RTT2.211209.001`/`00.00.00.101`
  fingerprint `Hisense/wuyishan/wuyishan:11/RTT2.211209.001/00.00.00.101:user/release-keys`
- Build type **user** · `ro.debuggable=0` · `ro.secure=1` · AVB **locked** (`vbmeta device_state: locked`)
- System flavor `hisense_us_Hisense_ATSC3U`; patch 2026-04-05
- Wi-Fi `mt7961`; BT via `38:64:07:f0:48:55`; MAC wifi `38:64:07:f0:48:56`

## Kernel (raw/kernel.txt)
- `uname -a` full capture; Sony/MTK BSP (see raw). `/proc/version` ~2 lines.
- Cmdline (`raw/proc-cmdline.txt`): init args, console, MTK boot flags.

## Partitions & mounts (raw/proc-mounts.txt, raw/partitions.txt)
- A/B dynamic partitions (system/system_ext/product/vendor), Treble, FBE encryption (dm-crypt default key)
- `/` vendor/product/system_ext 100% full (normal read-only roots)
- `/data` (dm-15) 6.0G — was 76% (2026-11-03), now **68%** (3.9G/6.0G)
- zram 800MB; RAM ~1.9GB — the memory floor explanation

## Boot chain (from alvidaa/carteakey + our probes)
- `rcS` → `rcS.utopia.linux` (rw mounts; `start-hisense.sh`: start-security → start-network →
  superwatch → tvmain → startgui) → `rcS.ko.linux`
- Logo-hang = stall in tvmain/startgui; network comes up AFTER startgui → logo-hang = network-dead.
- Standby ≠ reboot (only wall-plug re-runs rc.local); alvidaa verified.
- Root primitive: `/var/local/otaTest.sh` executed as root by rc.local every boot
  (`/var/local` bridge-writable). UART `getty -L ttyS0 115200` (key_check.sh USB gate).
- USB `.PKG` firmware reinstall lane: OTA flag intercepts before startgui (official unbrick).

## Property universe (raw/getprop.txt — 19KB)
Key families: `ro.hardware*`, `ro.board*`, `ro.product*`, `persist.sys*`,
`media.sf*`, `hwui*`, `display*` — see raw for the full snapshot. Highlighted in
`misc-props.txt`. The `g_system__backlight_mode`/`g_fusion_picture__brightness`
vendor keys are NOT in Android settings (vendor store).

## SDK notes
- No root path on-device (user build + AVB locked). All elevated lanes go THROUGH
  shell uid (Shizuku) or factory/physical primitives (see 07).