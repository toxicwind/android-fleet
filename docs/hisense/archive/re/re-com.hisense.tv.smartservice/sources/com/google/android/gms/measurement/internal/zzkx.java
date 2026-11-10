package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzkx {
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final long zzd;
    public final Object zze;

    public zzkx(String str, String str2, String str3, long j, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(obj);
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
        this.zze = obj;
    }
}
