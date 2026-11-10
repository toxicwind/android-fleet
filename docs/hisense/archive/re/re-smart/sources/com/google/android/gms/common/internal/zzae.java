package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzae extends com.google.android.gms.internal.common.zzb implements zzaf {
    public static zzaf zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        return iInterfaceQueryLocalInterface instanceof zzaf ? (zzaf) iInterfaceQueryLocalInterface : new zzad(iBinder);
    }
}
