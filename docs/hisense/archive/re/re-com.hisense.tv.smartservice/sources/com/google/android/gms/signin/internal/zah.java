package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zah implements Parcelable.Creator<zai> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zai createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ResolveAccountRequest resolveAccountRequest = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 2) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                resolveAccountRequest = (ResolveAccountRequest) SafeParcelReader.createParcelable(parcel, i2, ResolveAccountRequest.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zai(i, resolveAccountRequest);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zai[] newArray(int i) {
        return new zai[i];
    }
}
