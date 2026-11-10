package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzt implements Parcelable.Creator<zzs> {
    @Override // android.os.Parcelable.Creator
    public final zzs createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        boolean z = false;
        boolean z2 = false;
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c2 = (char) i;
            if (c2 == 1) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c2 == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, i);
            } else if (c2 == 3) {
                z = SafeParcelReader.readBoolean(parcel, i);
            } else if (c2 != 4) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                z2 = SafeParcelReader.readBoolean(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzs(strCreateString, iBinder, z, z2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzs[] newArray(int i) {
        return new zzs[i];
    }
}
