package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zza implements Parcelable.Creator<Scope> {
    @Override // android.os.Parcelable.Creator
    public final Scope createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        String strCreateString = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 2) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Scope(i, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Scope[] newArray(int i) {
        return new Scope[i];
    }
}
