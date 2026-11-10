package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zzdu extends zzch {
    public final com.google.android.gms.measurement.internal.zzgv zza;

    public zzdu(com.google.android.gms.measurement.internal.zzgv zzgvVar) {
        this.zza = zzgvVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final int zzd() {
        return System.identityHashCode(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final void zze(String str, String str2, Bundle bundle, long j) {
        this.zza.interceptEvent(str, str2, bundle, j);
    }
}
