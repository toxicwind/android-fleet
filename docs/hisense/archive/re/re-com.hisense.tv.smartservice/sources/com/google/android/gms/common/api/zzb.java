package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<Status> {
    @Override // android.os.Parcelable.Creator
    public final Status createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String strCreateString = null;
        PendingIntent pendingIntent = null;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 2) {
                strCreateString = SafeParcelReader.createString(parcel, i3);
            } else if (c2 == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, i3, PendingIntent.CREATOR);
            } else if (c2 == 4) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, i3, ConnectionResult.CREATOR);
            } else if (c2 != 1000) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                i = SafeParcelReader.readInt(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Status(i, i2, strCreateString, pendingIntent, connectionResult);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status[] newArray(int i) {
        return new Status[i];
    }
}
