package androidx.media3.common;

import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaLibraryInfo {
    public static final String TAG = "AndroidXMedia3";
    public static final boolean TRACE_ENABLED = true;
    public static final String VERSION = "1.9.3";
    public static final int VERSION_INT = 1009003300;
    public static final String VERSION_SLASHY = "AndroidXMedia3/1.9.3";
    private static final HashSet<String> registeredModules = new HashSet<>();
    private static String registeredModulesString = "media3.common";

    private MediaLibraryInfo() {
    }

    public static synchronized String registeredModules() {
        return registeredModulesString;
    }

    public static synchronized void registerModule(String str) {
        if (registeredModules.add(str)) {
            registeredModulesString += ", " + str;
        }
    }
}
