package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zao implements Parcelable.Creator<zaj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        int i = 0;
        String strCreateString = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 == 2) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, zam.CREATOR);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                strCreateString = SafeParcelReader.createString(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zaj(i, arrayListCreateTypedList, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaj[] newArray(int i) {
        return new zaj[i];
    }
}
