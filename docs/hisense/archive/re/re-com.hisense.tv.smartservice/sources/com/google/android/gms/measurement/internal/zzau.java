package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public final class zzau implements Parcelable.Creator<zzat> {
    public static void zza(zzat zzatVar, Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, zzatVar.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, zzatVar.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 4, zzatVar.zzc, false);
        long j = zzatVar.zzd;
        parcel.writeInt(524293);
        parcel.writeLong(j);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final zzat createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        zzar zzarVar = null;
        String strCreateString2 = null;
        long j = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c2 = (char) i;
            if (c2 == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c2 == 3) {
                zzarVar = (zzar) SafeParcelReader.createParcelable(parcel, i, zzar.CREATOR);
            } else if (c2 == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, i);
            } else if (c2 != 5) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                j = SafeParcelReader.readLong(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzat(strCreateString, zzarVar, strCreateString2, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzat[] newArray(int i) {
        return new zzat[i];
    }
}
