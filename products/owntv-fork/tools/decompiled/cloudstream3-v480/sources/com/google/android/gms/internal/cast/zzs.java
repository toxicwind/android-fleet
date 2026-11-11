package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzs {
    private static zzs zza;

    private zzs(zzh zzhVar, String str) {
        new ConcurrentHashMap();
        DefaultClock.getInstance();
    }

    public static synchronized void zza(zzh zzhVar, String str) {
        if (zza == null) {
            zza = new zzs(zzhVar, str);
        }
    }
}
