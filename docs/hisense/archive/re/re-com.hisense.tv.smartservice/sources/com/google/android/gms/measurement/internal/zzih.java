package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class zzih {

    @Nullable
    public final String zza;

    @Nullable
    public final String zzb;
    public final long zzc;
    public boolean zzd;
    public final boolean zze;
    public final long zzf;

    public zzih(@Nullable String str, @Nullable String str2, long j) {
        this(str, str2, j, false, 0L);
    }

    public zzih(@Nullable String str, @Nullable String str2, long j, boolean z, long j2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = false;
        this.zze = z;
        this.zzf = j2;
    }
}
