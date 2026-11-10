package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zao implements Parcelable.Creator<SignInButtonConfig> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SignInButtonConfig createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        Scope[] scopeArr = null;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c2 = (char) i4;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 == 2) {
                i3 = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 == 3) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 != 4) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, i4, Scope.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SignInButtonConfig(i, i3, i2, scopeArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SignInButtonConfig[] newArray(int i) {
        return new SignInButtonConfig[i];
    }
}
