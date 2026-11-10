package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zak implements Parcelable.Creator<zal> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zal createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        int i = 0;
        FastJsonResponse.Field field = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                field = (FastJsonResponse.Field) SafeParcelReader.createParcelable(parcel, i2, FastJsonResponse.Field.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zal(i, strCreateString, field);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zal[] newArray(int i) {
        return new zal[i];
    }
}
