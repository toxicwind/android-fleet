package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzai implements Parcelable.Creator<RootTelemetryConfiguration> {
    @Override // android.os.Parcelable.Creator
    public final RootTelemetryConfiguration createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c2 = (char) i4;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 == 2) {
                z = SafeParcelReader.readBoolean(parcel, i4);
            } else if (c2 == 3) {
                z2 = SafeParcelReader.readBoolean(parcel, i4);
            } else if (c2 == 4) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 != 5) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                i3 = SafeParcelReader.readInt(parcel, i4);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new RootTelemetryConfiguration(i, z, z2, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RootTelemetryConfiguration[] newArray(int i) {
        return new RootTelemetryConfiguration[i];
    }
}
