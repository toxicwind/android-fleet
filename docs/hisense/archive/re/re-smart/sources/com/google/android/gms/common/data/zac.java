package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zac implements Parcelable.Creator<DataHolder> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataHolder createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String[] strArrCreateStringArray = null;
        CursorWindow[] cursorWindowArr = null;
        Bundle bundleCreateBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, i3);
            } else if (c2 == 2) {
                cursorWindowArr = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel, i3, CursorWindow.CREATOR);
            } else if (c2 == 3) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 4) {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, i3);
            } else if (c2 != 1000) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                i = SafeParcelReader.readInt(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        DataHolder dataHolder = new DataHolder(i, strArrCreateStringArray, cursorWindowArr, i2, bundleCreateBundle);
        dataHolder.zaby();
        return dataHolder;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
