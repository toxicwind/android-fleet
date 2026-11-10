package com.google.android.gms.measurement.internal;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class zzgx implements zzef {
    public final /* synthetic */ zzfv zza;

    public zzgx(zzgy zzgyVar, zzfv zzfvVar) {
        this.zza = zzfvVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzef
    public final boolean zza() {
        return this.zza.zzL() && Log.isLoggable(this.zza.zzay().zzq(), 3);
    }
}
