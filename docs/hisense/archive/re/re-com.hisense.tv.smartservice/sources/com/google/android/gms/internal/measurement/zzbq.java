package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzbq extends zzbn implements zzbr {
    public static zzbr zzb(IBinder iBinder) {
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        return iInterfaceQueryLocalInterface instanceof zzbr ? (zzbr) iInterfaceQueryLocalInterface : new zzbp(iBinder);
    }
}
