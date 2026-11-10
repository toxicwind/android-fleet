package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzo implements Parcelable.Creator<zzn> {
    @Override // android.os.Parcelable.Creator
    public final zzn createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i = parcel.readInt();
            char c2 = (char) i;
            if (c2 == 1) {
                strCreateString = SafeParcelReader.createString(parcel, i);
            } else if (c2 == 2) {
                z = SafeParcelReader.readBoolean(parcel, i);
            } else if (c2 == 3) {
                z2 = SafeParcelReader.readBoolean(parcel, i);
            } else if (c2 == 4) {
                iBinder = SafeParcelReader.readIBinder(parcel, i);
            } else if (c2 != 5) {
                SafeParcelReader.skipUnknownField(parcel, i);
            } else {
                z3 = SafeParcelReader.readBoolean(parcel, i);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzn(strCreateString, z, z2, iBinder, z3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzn[] newArray(int i) {
        return new zzn[i];
    }
}
