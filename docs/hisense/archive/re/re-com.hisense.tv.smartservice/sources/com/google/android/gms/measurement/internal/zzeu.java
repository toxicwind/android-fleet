package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzeu {
    public final /* synthetic */ zzfa zza;
    public final String zzb;
    public final boolean zzc;
    public boolean zzd;
    public boolean zze;

    public zzeu(zzfa zzfaVar, String str, boolean z) {
        this.zza = zzfaVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = z;
    }

    @WorkerThread
    public final void zza(boolean z) {
        SharedPreferences.Editor editorEdit = this.zza.zza().edit();
        editorEdit.putBoolean(this.zzb, z);
        editorEdit.apply();
        this.zze = z;
    }

    @WorkerThread
    public final boolean zzb() {
        if (!this.zzd) {
            this.zzd = true;
            this.zze = this.zza.zza().getBoolean(this.zzb, this.zzc);
        }
        return this.zze;
    }
}
