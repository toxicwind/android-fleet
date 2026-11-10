package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzkn implements Callable<String> {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzks zzb;

    public zzkn(zzks zzksVar, zzp zzpVar) {
        this.zzb = zzksVar;
        this.zza = zzpVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ String call() {
        zzks zzksVar = this.zzb;
        String str = this.zza.zza;
        Preconditions.checkNotNull(str);
        if (zzksVar.zzh(str).zzk() && zzag.zzb(this.zza.zzv).zzk()) {
            return this.zzb.zzd(this.zza).zzu();
        }
        this.zzb.zzay().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
