package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzan extends zza implements zzao {
    zzan(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzf(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzc(parcelZza, bundle);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzg(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzc(parcelZza, bundle);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzh(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzc(parcelZza, bundle);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzi(String str, String str2, Bundle bundle, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzc(parcelZza, bundle);
        parcelZza.writeInt(i);
        zzc(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzj(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzc(parcelZza, bundle);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzk(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzc(parcelZza, bundle);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzl(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzc(parcelZza, bundle);
        zzc(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzao
    public final void zzm(String str, Bundle bundle, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzc(parcelZza, bundle);
        parcelZza.writeInt(i);
        zzc(6, parcelZza);
    }
}
