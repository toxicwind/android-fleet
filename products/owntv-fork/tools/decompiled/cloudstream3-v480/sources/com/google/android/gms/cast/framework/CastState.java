package com.google.android.gms.cast.framework;

import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class CastState {
    public static final int CONNECTED = 4;
    public static final int CONNECTING = 3;
    public static final int NOT_CONNECTED = 2;
    public static final int NO_DEVICES_AVAILABLE = 1;

    private CastState() {
    }

    public static String toString(int i) {
        if (i == 1) {
            return "NO_DEVICES_AVAILABLE";
        }
        if (i == 2) {
            return "NOT_CONNECTED";
        }
        if (i != 3) {
            return i != 4 ? String.format(Locale.ROOT, "UNKNOWN_STATE(%d)", Integer.valueOf(i)) : "CONNECTED";
        }
        return "CONNECTING";
    }
}
