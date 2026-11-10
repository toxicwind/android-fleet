package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zan implements Parcelable.Creator<zam> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        int i = 0;
        ArrayList arrayListCreateTypedList = null;
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
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, zal.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zam(i, strCreateString, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam[] newArray(int i) {
        return new zam[i];
    }
}
