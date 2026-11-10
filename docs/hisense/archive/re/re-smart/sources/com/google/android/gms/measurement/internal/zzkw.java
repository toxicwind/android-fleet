package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public final class zzkw implements Parcelable.Creator<zzkv> {
    public static void zza(zzkv zzkvVar, Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = zzkvVar.zza;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, zzkvVar.zzb, false);
        long j = zzkvVar.zzc;
        parcel.writeInt(524291);
        parcel.writeLong(j);
        SafeParcelWriter.writeLongObject(parcel, 4, zzkvVar.zzd, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, zzkvVar.zze, false);
        SafeParcelWriter.writeString(parcel, 7, zzkvVar.zzf, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, zzkvVar.zzg, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final zzkv createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        Long longObject = null;
        Float floatObject = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        Double doubleObject = null;
        int i = 0;
        long j = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, i2);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, i2);
                    break;
                case 4:
                    longObject = SafeParcelReader.readLongObject(parcel, i2);
                    break;
                case 5:
                    floatObject = SafeParcelReader.readFloatObject(parcel, i2);
                    break;
                case 6:
                    strCreateString2 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 7:
                    strCreateString3 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\b':
                    doubleObject = SafeParcelReader.readDoubleObject(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzkv(i, strCreateString, j, longObject, floatObject, strCreateString2, strCreateString3, doubleObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkv[] newArray(int i) {
        return new zzkv[i];
    }
}
