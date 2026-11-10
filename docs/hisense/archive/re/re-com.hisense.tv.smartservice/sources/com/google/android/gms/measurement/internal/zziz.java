package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class zziz implements Runnable {
    public final /* synthetic */ zzat zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzc;
    public final /* synthetic */ zzjo zzd;

    public zziz(zzjo zzjoVar, zzat zzatVar, String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzd = zzjoVar;
        this.zza = zzatVar;
        this.zzb = str;
        this.zzc = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfv zzfvVar;
        byte[] bArrZzu = null;
        try {
            try {
                zzeb zzebVar = this.zzd.zzb;
                if (zzebVar == null) {
                    this.zzd.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                    zzfvVar = this.zzd.zzs;
                } else {
                    bArrZzu = zzebVar.zzu(this.zza, this.zzb);
                    this.zzd.zzQ();
                    zzfvVar = this.zzd.zzs;
                }
            } catch (RemoteException e2) {
                this.zzd.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e2);
                zzfvVar = this.zzd.zzs;
            }
            zzkz zzkzVarZzv = zzfvVar.zzv();
        } finally {
            this.zzd.zzs.zzv().zzR(this.zzc, bArrZzu);
        }
    }
}
