package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbs;
import com.google.android.gms.common.api.ApiMetadata;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzah extends com.google.android.gms.internal.cast.zza implements IInterface {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void zze(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(17, parcelZza);
    }

    public final void zzf(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(1, parcelZza);
    }

    public final void zzg(String str, String str2, zzbs zzbsVar, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, zzbsVar);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(14, parcelZza);
    }

    public final void zzh(String str, LaunchOptions launchOptions, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, launchOptions);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(13, parcelZza);
    }

    public final void zzi(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(4, parcelZza);
    }

    public final void zzj(zzaj zzajVar, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zze(parcelZza, zzajVar);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(18, parcelZza);
    }

    public final void zzk(String str, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(11, parcelZza);
    }

    public final void zzl(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(6, parcelZza);
    }

    public final void zzm(String str, String str2, long j, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeLong(j);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(9, parcelZza);
    }

    public final void zzn(boolean z, double d, boolean z2, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        int i = com.google.android.gms.internal.cast.zzc.zza;
        parcelZza.writeInt(z ? 1 : 0);
        parcelZza.writeDouble(d);
        parcelZza.writeInt(z2 ? 1 : 0);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(8, parcelZza);
    }

    public final void zzo(double d, double d2, boolean z, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeDouble(d);
        parcelZza.writeDouble(d2);
        int i = com.google.android.gms.internal.cast.zzc.zza;
        parcelZza.writeInt(z ? 1 : 0);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(7, parcelZza);
    }

    public final void zzp(String str, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(5, parcelZza);
    }

    public final void zzq(ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(19, parcelZza);
    }

    public final void zzr(String str, ApiMetadata apiMetadata) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, apiMetadata);
        zzd(12, parcelZza);
    }
}
