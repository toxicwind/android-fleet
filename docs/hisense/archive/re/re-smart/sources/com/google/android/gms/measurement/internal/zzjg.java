package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzjg implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzp zzc;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzd;
    public final /* synthetic */ zzjo zze;

    public zzjg(zzjo zzjoVar, String str, String str2, zzp zzpVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zze = zzjoVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzpVar;
        this.zzd = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfv zzfvVar;
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            try {
                zzeb zzebVar = this.zze.zzb;
                if (zzebVar == null) {
                    this.zze.zzs.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                    zzfvVar = this.zze.zzs;
                } else {
                    Preconditions.checkNotNull(this.zzc);
                    arrayList = zzkz.zzG(zzebVar.zzf(this.zza, this.zzb, this.zzc));
                    this.zze.zzQ();
                    zzfvVar = this.zze.zzs;
                }
            } catch (RemoteException e2) {
                this.zze.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e2);
                zzfvVar = this.zze.zzs;
            }
            zzkz zzkzVarZzv = zzfvVar.zzv();
        } finally {
            this.zze.zzs.zzv().zzP(this.zzd, arrayList);
        }
    }
}
