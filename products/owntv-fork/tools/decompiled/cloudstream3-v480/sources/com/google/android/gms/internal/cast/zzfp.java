package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzfp extends zza implements IInterface {
    zzfp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.usagereporting.internal.IUsageReportingService");
    }

    public final void zze(zzfn zzfnVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzfnVar);
        zzc(2, parcelZza);
    }

    public final void zzf(zzfo zzfoVar, zzfn zzfnVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzfoVar);
        zzc.zze(parcelZza, zzfnVar);
        zzc(5, parcelZza);
    }
}
