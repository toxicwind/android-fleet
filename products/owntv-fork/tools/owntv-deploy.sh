#!/bin/bash
# Headless OwnTV build + deploy to Hisense (bun-wrapped Gradle, no IDE)
set -e
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
export PATH="$JAVA_HOME/bin:$PATH"
cd /tmp/owntv
echo "=== Building OwnTV debug (JAVA_HOME=$JAVA_HOME) ==="
./gradlew :app:assembleStandardDebug --no-daemon -Dorg.gradle.java.home=$JAVA_HOME 2>&1 | grep -E "BUILD|FAILURE|error:|> Task :app:assemble" | sed -n '1,20p'
APK=$(find app/build/outputs/apk -name "*.apk" 2>/dev/null | sed -n '1p')
if [ -z "$APK" ]; then echo "NO APK"; exit 1; fi
echo "=== APK: $APK ==="
echo "=== Installing to Hisense (10.0.0.225) ==="
adb -s 10.0.0.225:5555 install -r "$APK" 2>&1 | sed -n '1,5p'
echo "=== Launching ==="
adb -s 10.0.0.225:5555 shell "monkey -p tv.own.owntv -c android.intent.category.LAUNCHER 1" 2>&1 | sed -n '1,3p'
