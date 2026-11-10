package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zziu implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzb;
    public final /* synthetic */ zzjo zzc;

    public zziu(zzjo zzjoVar, zzp zzpVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzc = zzjoVar;
        this.zza = zzpVar;
        this.zzb = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfv zzfvVar;
        String strZzd = null;
        try {
            if (this.zzc.zzs.zzm().zzc().zzk()) {
                zzeb zzebVar = this.zzc.zzb;
                if (zzebVar == null) {
                    this.zzc.zzs.zzay().zzd().zza("Failed to get app instance id");
                    zzfvVar = this.zzc.zzs;
                } else {
                    Preconditions.checkNotNull(this.zza);
                    strZzd = zzebVar.zzd(this.zza);
                    if (strZzd != null) {
                        this.zzc.zzs.zzq().zzO(strZzd);
                        this.zzc.zzs.zzm().zze.zzb(strZzd);
                    }
                    this.zzc.zzQ();
                    zzfvVar = this.zzc.zzs;
                }
            } else {
                this.zzc.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzs.zzq().zzO(null);
                this.zzc.zzs.zzm().zze.zzb(null);
                zzfvVar = this.zzc.zzs;
            }
        } catch (RemoteException e2) {
            this.zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e2);
            zzfvVar = this.zzc.zzs;
        } finally {
            this.zzc.zzs.zzv().zzU(this.zzb, null);
        }
        zzkz zzkzVarZzv = zzfvVar.zzv();
    }
}
