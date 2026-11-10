package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzak implements Parcelable.Creator<zzaj> {
    @Override // android.os.Parcelable.Creator
    public final zzaj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            if (((char) i2) != 1) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                i = SafeParcelReader.readInt(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzaj(i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaj[] newArray(int i) {
        return new zzaj[i];
    }
}
