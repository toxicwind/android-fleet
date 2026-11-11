package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzah extends com.google.android.gms.internal.cast.zza implements zzaj {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastContext");
    }

    @Override // com.google.android.gms.cast.framework.zzaj
    public final Bundle zze() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        Bundle bundle = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.cast.framework.zzaj
    public final zzaq zzf() throws RemoteException {
        zzaq zzapVar;
        Parcel parcelZzb = zzb(6, zza());
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzapVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager");
            zzapVar = iInterfaceQueryLocalInterface instanceof zzaq ? (zzaq) iInterfaceQueryLocalInterface : new zzap(strongBinder);
        }
        parcelZzb.recycle();
        return zzapVar;
    }

    @Override // com.google.android.gms.cast.framework.zzaj
    public final zzay zzg() throws RemoteException {
        zzay zzaxVar;
        Parcel parcelZzb = zzb(5, zza());
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzaxVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager");
            zzaxVar = iInterfaceQueryLocalInterface instanceof zzay ? (zzay) iInterfaceQueryLocalInterface : new zzax(strongBinder);
        }
        parcelZzb.recycle();
        return zzaxVar;
    }

    @Override // com.google.android.gms.cast.framework.zzaj
    public final void zzh(zzae zzaeVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzaeVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaj
    public final void zzi(String str, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeMap(map);
        zzc(11, parcelZza);
    }
}
