package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzax extends com.google.android.gms.internal.cast.zza implements zzay {
    zzax(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISessionManager");
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final IObjectWrapper zzf() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final IObjectWrapper zzg() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzh(zzao zzaoVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzaoVar);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzi(zzba zzbaVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzbaVar);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzj(boolean z, boolean z2) throws RemoteException {
        Parcel parcelZza = zza();
        int i = com.google.android.gms.internal.cast.zzc.zza;
        parcelZza.writeInt(1);
        parcelZza.writeInt(z2 ? 1 : 0);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzk(zzao zzaoVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzaoVar);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzl(zzba zzbaVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzbaVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzay
    public final void zzm(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, bundle);
        zzc(9, parcelZza);
    }
}
