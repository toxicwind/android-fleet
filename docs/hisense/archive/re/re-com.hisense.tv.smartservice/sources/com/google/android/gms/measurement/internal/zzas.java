package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzas implements Parcelable.Creator<zzar> {
    @Override // android.os.Parcelable.Creator
    public final zzar createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            if (((char) i) != 2) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzar(bundleCreateBundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzar[] newArray(int i) {
        return new zzar[i];
    }
}
