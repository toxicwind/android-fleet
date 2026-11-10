package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zap implements Parcelable.Creator<SafeParcelResponse> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Parcel parcelCreateParcel = null;
        int i = 0;
        zaj zajVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 == 2) {
                parcelCreateParcel = SafeParcelReader.createParcel(parcel, i2);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                zajVar = (zaj) SafeParcelReader.createParcelable(parcel, i2, zaj.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SafeParcelResponse(i, parcelCreateParcel, zajVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse[] newArray(int i) {
        return new SafeParcelResponse[i];
    }
}
