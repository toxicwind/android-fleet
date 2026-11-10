package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();

    @SafeParcelable.Field(getter = "getCallingPackage", id = 1)
    public final String zza;

    @SafeParcelable.Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    public final zzi zzb;

    @SafeParcelable.Field(getter = "getAllowTestKeys", id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", id = 4)
    public final boolean zzd;

    @SafeParcelable.Constructor
    public zzs(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) boolean z2) {
        this.zza = str;
        zzj zzjVar = null;
        if (iBinder != null) {
            try {
                IObjectWrapper iObjectWrapperZzd = com.google.android.gms.common.internal.zzy.zzg(iBinder).zzd();
                byte[] bArr = iObjectWrapperZzd == null ? null : (byte[]) ObjectWrapper.unwrap(iObjectWrapperZzd);
                if (bArr != null) {
                    zzjVar = new zzj(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e2) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e2);
            }
        }
        this.zzb = zzjVar;
        this.zzc = z;
        this.zzd = z2;
    }

    public zzs(String str, zzi zziVar, boolean z, boolean z2) {
        this.zza = str;
        this.zzb = zziVar;
        this.zzc = z;
        this.zzd = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        zzi zziVar = this.zzb;
        if (zziVar == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            zziVar = null;
        }
        SafeParcelWriter.writeIBinder(parcel, 2, zziVar, false);
        boolean z = this.zzc;
        parcel.writeInt(262147);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.zzd;
        parcel.writeInt(262148);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }
}
