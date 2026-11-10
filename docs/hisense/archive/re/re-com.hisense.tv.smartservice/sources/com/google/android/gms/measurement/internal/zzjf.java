package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzjf implements Runnable {
    public final /* synthetic */ AtomicReference zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ zzp zzd;
    public final /* synthetic */ zzjo zze;

    public zzjf(zzjo zzjoVar, AtomicReference atomicReference, String str, String str2, String str3, zzp zzpVar) {
        this.zze = zzjoVar;
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.zza) {
            try {
                try {
                    zzeb zzebVar = this.zze.zzb;
                    if (zzebVar == null) {
                        this.zze.zzs.zzay().zzd().zzd("(legacy) Failed to get conditional properties; not connected to service", null, this.zzb, this.zzc);
                        this.zza.set(Collections.emptyList());
                        this.zza.notify();
                        return;
                    }
                    if (TextUtils.isEmpty(null)) {
                        Preconditions.checkNotNull(this.zzd);
                        this.zza.set(zzebVar.zzf(this.zzb, this.zzc, this.zzd));
                    } else {
                        this.zza.set(zzebVar.zzg(null, this.zzb, this.zzc));
                    }
                    this.zze.zzQ();
                    atomicReference = this.zza;
                    atomicReference.notify();
                } catch (RemoteException e2) {
                    this.zze.zzs.zzay().zzd().zzd("(legacy) Failed to get conditional properties; remote exception", null, this.zzb, e2);
                    this.zza.set(Collections.emptyList());
                    atomicReference = this.zza;
                }
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
