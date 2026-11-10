package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@WorkerThread
public final class zzep implements Runnable {
    public final zzen zza;
    public final int zzb;
    public final Throwable zzc;
    public final byte[] zzd;
    public final String zze;
    public final Map<String, List<String>> zzf;

    public /* synthetic */ zzep(String str, zzen zzenVar, int i, Throwable th, byte[] bArr, Map map, zzeo zzeoVar) {
        Preconditions.checkNotNull(zzenVar);
        this.zza = zzenVar;
        this.zzb = i;
        this.zzc = th;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zze, this.zzb, this.zzc, this.zzd, this.zzf);
    }
}
