package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzgy {
    public final Context zza;

    @Nullable
    public String zzb;

    @Nullable
    public String zzc;

    @Nullable
    public String zzd;

    @Nullable
    public Boolean zze;
    public long zzf;

    @Nullable
    public com.google.android.gms.internal.measurement.zzcl zzg;
    public boolean zzh;

    @Nullable
    public final Long zzi;

    @Nullable
    public String zzj;

    @VisibleForTesting
    public zzgy(Context context, @Nullable com.google.android.gms.internal.measurement.zzcl zzclVar, @Nullable Long l) {
        this.zzh = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzclVar != null) {
            this.zzg = zzclVar;
            this.zzb = zzclVar.zzf;
            this.zzc = zzclVar.zze;
            this.zzd = zzclVar.zzd;
            this.zzh = zzclVar.zzc;
            this.zzf = zzclVar.zzb;
            this.zzj = zzclVar.zzh;
            Bundle bundle = zzclVar.zzg;
            if (bundle != null) {
                this.zze = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
