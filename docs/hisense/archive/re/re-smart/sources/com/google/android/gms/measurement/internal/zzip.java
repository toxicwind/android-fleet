package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzip implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzp zzc;
    public final /* synthetic */ boolean zzd;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zze;
    public final /* synthetic */ zzjo zzf;

    public zzip(zzjo zzjoVar, String str, String str2, zzp zzpVar, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzf = zzjoVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzpVar;
        this.zzd = z;
        this.zze = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Bundle bundle;
        Throwable th;
        RemoteException e2;
        Bundle bundle2 = new Bundle();
        try {
            try {
                zzeb zzebVar = this.zzf.zzb;
                if (zzebVar == null) {
                    this.zzf.zzs.zzay().zzd().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);
                    this.zzf.zzs.zzv().zzQ(this.zze, bundle2);
                    return;
                }
                Preconditions.checkNotNull(this.zzc);
                List<zzkv> listZzh = zzebVar.zzh(this.zza, this.zzb, this.zzd, this.zzc);
                bundle = new Bundle();
                if (listZzh != null) {
                    for (zzkv zzkvVar : listZzh) {
                        String str = zzkvVar.zze;
                        if (str != null) {
                            bundle.putString(zzkvVar.zzb, str);
                        } else {
                            Long l = zzkvVar.zzd;
                            if (l != null) {
                                bundle.putLong(zzkvVar.zzb, l.longValue());
                            } else {
                                Double d2 = zzkvVar.zzg;
                                if (d2 != null) {
                                    bundle.putDouble(zzkvVar.zzb, d2.doubleValue());
                                }
                            }
                        }
                    }
                }
                try {
                    this.zzf.zzQ();
                    this.zzf.zzs.zzv().zzQ(this.zze, bundle);
                } catch (RemoteException e3) {
                    e2 = e3;
                    this.zzf.zzs.zzay().zzd().zzc("Failed to get user properties; remote exception", this.zza, e2);
                    this.zzf.zzs.zzv().zzQ(this.zze, bundle);
                }
            } catch (Throwable th2) {
                th = th2;
                this.zzf.zzs.zzv().zzQ(this.zze, bundle);
                throw th;
            }
        } catch (RemoteException e4) {
            bundle = bundle2;
            e2 = e4;
        } catch (Throwable th3) {
            bundle = bundle2;
            th = th3;
            this.zzf.zzs.zzv().zzQ(this.zze, bundle);
            throw th;
        }
    }
}
