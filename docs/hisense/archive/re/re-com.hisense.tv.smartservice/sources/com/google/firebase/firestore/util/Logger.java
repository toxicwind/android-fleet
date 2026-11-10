package com.google.firebase.firestore.util;

import android.util.Log;
import com.google.firebase.firestore.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class Logger {
    public static Level logLevel = Level.WARN;

    /* JADX INFO: renamed from: com.google.firebase.firestore.util.Logger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$util$Logger$Level = new int[Level.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum Level {
        DEBUG,
        WARN,
        NONE
    }

    public static void debug(String str, String str2, Object... objArr) {
        doLog(Level.DEBUG, str, str2, objArr);
    }

    public static void doLog(Level level, String str, String str2, Object... objArr) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String str3 = String.format("(%s) [%s]: ", BuildConfig.VERSION_NAME, str) + String.format(str2, objArr);
            int iOrdinal = level.ordinal();
            if (iOrdinal == 0) {
                Log.i("Firestore", str3);
            } else if (iOrdinal == 1) {
                Log.w("Firestore", str3);
            } else if (iOrdinal == 2) {
                throw new IllegalStateException("Trying to log something on level NONE");
            }
        }
    }

    public static boolean isDebugEnabled() {
        return logLevel.ordinal() >= Level.DEBUG.ordinal();
    }

    public static void setLogLevel(Level level) {
        logLevel = level;
    }

    public static void warn(String str, String str2, Object... objArr) {
        doLog(Level.WARN, str, str2, objArr);
    }
}
