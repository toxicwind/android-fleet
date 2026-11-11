package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiMetadata;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzel extends zza implements IInterface {
    zzel(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void zze(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, apiMetadata);
        zzd(3, parcelZza);
    }

    public final void zzf(zzek zzekVar, int i, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzekVar);
        parcelZza.writeInt(i);
        zzc.zzc(parcelZza, apiMetadata);
        zzd(5, parcelZza);
    }

    public final void zzg(zzek zzekVar, zzen zzenVar, String str, String str2, Bundle bundle, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzekVar);
        zzc.zze(parcelZza, zzenVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzc(parcelZza, bundle);
        zzc.zzc(parcelZza, apiMetadata);
        zzd(7, parcelZza);
    }

    public final void zzh(zzek zzekVar, PendingIntent pendingIntent, String str, String str2, Bundle bundle, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzekVar);
        zzc.zzc(parcelZza, pendingIntent);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzc(parcelZza, bundle);
        zzc.zzc(parcelZza, apiMetadata);
        zzd(8, parcelZza);
    }

    public final void zzi(zzek zzekVar, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzekVar);
        zzc.zzc(parcelZza, apiMetadata);
        zzd(6, parcelZza);
    }
}
