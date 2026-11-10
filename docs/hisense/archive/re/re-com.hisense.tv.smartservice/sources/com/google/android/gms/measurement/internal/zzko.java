package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzko implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb = "_err";
    public final /* synthetic */ Bundle zzc;
    public final /* synthetic */ zzkp zzd;

    public zzko(zzkp zzkpVar, String str, String str2, Bundle bundle) {
        this.zzd = zzkpVar;
        this.zza = str;
        this.zzc = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzat zzatVarZzz = this.zzd.zza.zzv().zzz(this.zza, this.zzb, this.zzc, "auto", this.zzd.zza.zzav().currentTimeMillis(), false, true);
        zzks zzksVar = this.zzd.zza;
        Preconditions.checkNotNull(zzatVarZzz);
        zzksVar.zzE(zzatVarZzz, this.zza);
    }
}
