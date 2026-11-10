package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaa implements Parcelable.Creator<zab> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 2) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                intent = (Intent) SafeParcelReader.createParcelable(parcel, i3, Intent.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zab(i, i2, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab[] newArray(int i) {
        return new zab[i];
    }
}
