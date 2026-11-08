# Factory serial console — decoded (`com.hisense.uifac` = HisenseFactoryUI)

Reverse-engineered 2026-11-09 from `HisenseFactoryUI.apk` (27MB, `/system/app/HisenseFactoryUI/`):
`serialInfoTest` package = the FULL factory serial/tool console that runs as **uid 1000 (SYSTEM)**.

## The gate (everything dies here)
- `SerialStatusReceiver.onReceive` checks `persist.sys.factory.mode` (default "0"):
  `"0".equalsIgnoreCase(SystemProperties.get("persist.sys.factory.mode" , "0")) → "factory mode is 0,serial test do not respond!!!"` → return.
- The property is **root-only to set** (`setprop persist.sys.factory.mode 1` → "Failed to set property" as shell; `persist.vt.factory.mode` also denied).
- So: broadcast-driven serial test is gated; physical-UART worker is likely gated too.

## Command dispatch (`SerialFacService.localOnReadFromExternalControl(int action, byte[] value)`)
Triggered by `FactorySerialCallbackHandler (MtkTvTVCallbackHandler).notifySerialExternalControlCallback(action, value)` —
i.e., serial bytes arrive from the native MTK TV stack (physical factory UART) and are dispatched here as SYSTEM.
Signature samples (signed int32 values from the switch):
- `excutePowerOff()` — power off
- `enterOrExistFac(bArr)` — **Enter/Exit factory M-mode / Aging** (bArr[0]: 2=Enter M, 3=Exit M, 4=Aging on, 5=Aging off, 9=factory okay, 7/8=protect cleanups; requires `HIFAC_STATUS`/`HIFAC_AGING` config states)
- `switchAVSource / switchYPbPrSource / switchHDMISource / switchVGASource / switchDTVType / changeChannels`
- `setVolume / setBrightness / setContrast / setPictureMode / setColorTemprature / setGammaEnable / setLogo / setLanguage`
- `testPattern(bArr)` — panel test patterns (white/red/green/blue/black/circle) via `mMenuConfigManager.setTestPattern`
- **Key/µkey exfil: `getDTCPIPKey`, `getMarlinKey`, `getSeeQVaultKey`, `getPlayReadyKey`, `getACASId`, `getWifiMacAddress`, `getChipVersion`, `getProjectId`, `getOrderCode`** (paths like `/perm/keyname/tmp` per SerialCommands)
- **Config-file write: `initSerialFile(); saveConfigFile(bArr)`** (SerialSaveFile; SYSTEM-uid factory config write)
- `saveConfigFile` on action -536870912; `setServiceTag`, `getRunningTime`, `getFansSpeed`, `startMicTest`, `startBMWTest`

## Network demura upload server (`TcpServer`, port 8099)
- Started from SerialCommands' IPC/ethernet command path (`...setIPTakesEffectImmediately(...); new TcpServer(context).start()`).
- Protocol on 8099: header bytes `H S P C` (0x48 0x53 0x50 0x43), then 4-byte big-endian dataLen,
  4-byte cmd, payload; response identical with result byte at [12], checksum = sum of first 13 bytes.
- Handles cmd -1559232512 (demura upload): chunked write to **`/data/vendor/3rd_rw/factory/demura.bin`** (append),
  `buffer[12]==0` → delete file, `0xFF` + md5 → verify against file MD5. SYSTEM-uid fixed-path file write.
- **Currently CLOSED (8099/8089 refused)** — the server only spins up on the IPC command; not live on boot.

## Serial input channels
- Physical factory UART → MTK TV stack → `MtkTvTVCallbackHandler` → this app (primary).
- `com.jamdeo.intent.SERIAL_CONTROL` broadcast → `SerialStatusReceiver` (GATED by factory-mode prop).
- `com.hisense.fac.HISENSE_FAC_SERIAL_START_ACTION` → FacBluetoothReceiver (starts the work thread).
- TcpServer 8099 demura-only (needs the IPC trigger).

## Next-step notes
- If factory-mode becomes SET (M-mode via remote/key or another lane), the serial console enables
  factory writes incl. keys/settings — but still no direct root shell; look for a root-adb switch in
  the factory menu (MenuConfigManager) once M is active.
- The user-side experiment worth 30s: Settings → Sound → Sound Balance → alternate remote **OK + Menu**
  rapidly (cleanyourtv Hisense recipe for E5Q family — may or may not exist on SmartTV 4K FFM "wuyishan").
- `adb shell input keyevent` cannot reach the native M-mode recognizer (it's in the TV key service, not uifac).