package com.google.android.gms.flags;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzc extends com.google.android.gms.internal.flags.zza implements zze {
    zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.flags.IFlagProvider");
    }

    @Override // com.google.android.gms.flags.zze
    public final boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.flags.zzc.zza(parcelZza, z);
        parcelZza.writeInt(i);
        Parcel parcelZzb = zzb(2, parcelZza);
        boolean zZzc = com.google.android.gms.internal.flags.zzc.zzc(parcelZzb);
        parcelZzb.recycle();
        return zZzc;
    }

    @Override // com.google.android.gms.flags.zze
    public final int getIntFlagValue(String str, int i, int i2) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        Parcel parcelZzb = zzb(3, parcelZza);
        int i3 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i3;
    }

    @Override // com.google.android.gms.flags.zze
    public final long getLongFlagValue(String str, long j, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeLong(j);
        parcelZza.writeInt(i);
        Parcel parcelZzb = zzb(4, parcelZza);
        long j2 = parcelZzb.readLong();
        parcelZzb.recycle();
        return j2;
    }

    @Override // com.google.android.gms.flags.zze
    public final String getStringFlagValue(String str, String str2, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeInt(i);
        Parcel parcelZzb = zzb(5, parcelZza);
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.flags.zze
    public final void init(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.flags.zzc.zzb(parcelZza, iObjectWrapper);
        zzc(1, parcelZza);
    }
}
