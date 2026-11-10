package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zad implements Parcelable.Creator<StringToIntConverter> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StringToIntConverter createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ArrayList arrayListCreateTypedList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 2) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, StringToIntConverter.zaa.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StringToIntConverter(i, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StringToIntConverter[] newArray(int i) {
        return new StringToIntConverter[i];
    }
}
