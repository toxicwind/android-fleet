package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzk implements Parcelable.Creator<zzj> {
    @Override // android.os.Parcelable.Creator
    public final zzj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration = null;
        int i = 0;
        Feature[] featureArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, i2);
            } else if (c2 == 2) {
                featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, i2, Feature.CREATOR);
            } else if (c2 == 3) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 != 4) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                connectionTelemetryConfiguration = (ConnectionTelemetryConfiguration) SafeParcelReader.createParcelable(parcel, i2, ConnectionTelemetryConfiguration.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzj(bundleCreateBundle, featureArr, i, connectionTelemetryConfiguration);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzj[] newArray(int i) {
        return new zzj[i];
    }
}
