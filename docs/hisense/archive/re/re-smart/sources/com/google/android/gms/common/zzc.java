package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzc implements Parcelable.Creator<Feature> {
    @Override // android.os.Parcelable.Creator
    public final Feature createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            } else if (c2 == 2) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                j = SafeParcelReader.readLong(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Feature(strCreateString, i, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Feature[] newArray(int i) {
        return new Feature[i];
    }
}
