package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zan implements Parcelable.Creator<ResolveAccountResponse> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ResolveAccountResponse createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            char c2 = (char) i2;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i2);
            } else if (c2 == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, i2);
            } else if (c2 == 3) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, i2, ConnectionResult.CREATOR);
            } else if (c2 == 4) {
                z = SafeParcelReader.readBoolean(parcel, i2);
            } else if (c2 != 5) {
                SafeParcelReader.skipUnknownField(parcel, i2);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, i2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ResolveAccountResponse(i, iBinder, connectionResult, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ResolveAccountResponse[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }
}
