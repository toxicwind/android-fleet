package com.google.android.gms.internal.measurement;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class zzgw {
    public static volatile boolean zza;

    static {
        int i = Build.VERSION.SDK_INT;
        zza = false;
    }

    public static boolean zza() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
