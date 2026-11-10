package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zzdv extends zzch {
    public final com.google.android.gms.measurement.internal.zzgw zza;

    public zzdv(com.google.android.gms.measurement.internal.zzgw zzgwVar) {
        this.zza = zzgwVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zze(String str, String str2, Bundle bundle, long j) {
        this.zza.onEvent(str, str2, bundle, j);
    }
}
