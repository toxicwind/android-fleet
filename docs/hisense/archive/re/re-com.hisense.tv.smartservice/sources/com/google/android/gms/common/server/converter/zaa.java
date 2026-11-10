package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaa implements Parcelable.Creator<zab> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        StringToIntConverter stringToIntConverter = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 2) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                stringToIntConverter = (StringToIntConverter) SafeParcelReader.createParcelable(parcel, i2, StringToIntConverter.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zab(i, stringToIntConverter);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab[] newArray(int i) {
        return new zab[i];
    }
}
