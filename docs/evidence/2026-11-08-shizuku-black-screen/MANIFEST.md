# Evidence manifest — Hisense Shizuku black-screen fix (2026-11-08)

Moved from /home/toxic/tmp/hisense-shizuku-20261108 (scratch) into the android-fleet recon product.

## Provenance
- TV: Hisense wuyishan 55E82GUA, 10.0.0.225:5555, Android 11 (SDK 30), armeabi-v7a, locked user build.
- Session evidence captured live during the fix (see docs/hisense-wuyishan.md addendum 2026-11-08).
- shizuku-base.apk = TV-installed APK pulled via adb. shizuku-gh-release.apk = GitHub release v13.6.0 asset.

## Checksums (at capture)
```
6e273ab0e991c4e79bc8b1bbb9b9dd739ccac1a8712a541a214078886b7b790f  /home/toxic/tmp/hisense-shizuku-20261108/shizuku-base.apk
6e273ab0e991c4e79bc8b1bbb9b9dd739ccac1a8712a541a214078886b7b790f  /home/toxic/tmp/hisense-shizuku-20261108/shizuku-gh-release.apk
0529d8884eef532445524c5591b72aa0e233dd0b40d509d2bdedf58ba8c36c50  /home/toxic/tmp/hisense-shizuku-20261108/check2.png
8d268d217a480b771216da69ea62b2200baad5cf53b7bb90a547229018f94b63  /home/toxic/tmp/hisense-shizuku-20261108/blackscreen-check.png
```

- shizuku-base.apk == shizuku-gh-release.apk (identical sha256) — the installed build IS the official release.
- The GitHub release duplicate was dropped after equality proof (re-downloadable: https://github.com/RikkaApps/Shizuku/releases/download/v13.6.0/shizuku-v13.6.0.r1086.2650830c-release.apk).
- shizuku-src clone (upstream @ b844bc491f1790c72328e1a8e5b2349f8978f0ea) NOT archived here — cloneable; key source: manager/src/main/jni/starter.cpp.
