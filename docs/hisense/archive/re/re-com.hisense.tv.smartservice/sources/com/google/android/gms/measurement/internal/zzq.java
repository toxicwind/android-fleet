package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzq implements Parcelable.Creator<zzp> {
    @Override // android.os.Parcelable.Creator
    public final zzp createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = true;
        boolean z2 = true;
        boolean z3 = false;
        int i = 0;
        boolean z4 = false;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        String strCreateString7 = null;
        Boolean booleanObject = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString8 = null;
        String strCreateString9 = "";
        long j6 = -2147483648L;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, i2);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, i2);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, i2);
                    break;
                case '\b':
                    strCreateString5 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\t':
                    z = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case '\n':
                    z3 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 11:
                    j6 = SafeParcelReader.readLong(parcel, i2);
                    break;
                case '\f':
                    strCreateString6 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\r':
                    j3 = SafeParcelReader.readLong(parcel, i2);
                    break;
                case 14:
                    j4 = SafeParcelReader.readLong(parcel, i2);
                    break;
                case 15:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 16:
                    z2 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 17:
                case 20:
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
                case 18:
                    z4 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 19:
                    strCreateString7 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 21:
                    booleanObject = SafeParcelReader.readBooleanObject(parcel, i2);
                    break;
                case 22:
                    j5 = SafeParcelReader.readLong(parcel, i2);
                    break;
                case 23:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, i2);
                    break;
                case 24:
                    strCreateString8 = SafeParcelReader.createString(parcel, i2);
                    break;
                case 25:
                    strCreateString9 = SafeParcelReader.createString(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzp(strCreateString, strCreateString2, strCreateString3, strCreateString4, j, j2, strCreateString5, z, z3, j6, strCreateString6, j3, j4, i, z2, z4, strCreateString7, booleanObject, j5, arrayListCreateStringList, strCreateString8, strCreateString9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzp[] newArray(int i) {
        return new zzp[i];
    }
}
