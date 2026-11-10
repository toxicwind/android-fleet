package com.google.android.gms.internal.measurement;

import android.os.Binder;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zzhc {
    public static <V> V zza(zzhd<V> zzhdVar) {
        try {
            return zzhdVar.zza();
        } catch (SecurityException unused) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzhdVar.zza();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
