package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<ConnectionResult> {
    @Override // android.os.Parcelable.Creator
    public final ConnectionResult createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        PendingIntent pendingIntent = null;
        String strCreateString = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 2) {
                i2 = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, i3, PendingIntent.CREATOR);
            } else if (c2 != 4) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                strCreateString = SafeParcelReader.createString(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new ConnectionResult(i, i2, pendingIntent, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
