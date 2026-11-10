package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zac implements Parcelable.Creator<StringToIntConverter.zaa> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StringToIntConverter.zaa createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        String strCreateString = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i3);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                i2 = SafeParcelReader.readInt(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StringToIntConverter.zaa(i, strCreateString, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StringToIntConverter.zaa[] newArray(int i) {
        return new StringToIntConverter.zaa[i];
    }
}
