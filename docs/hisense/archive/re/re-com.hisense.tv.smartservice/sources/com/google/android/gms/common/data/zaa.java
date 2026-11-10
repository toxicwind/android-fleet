package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaa implements Parcelable.Creator<BitmapTeleporter> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BitmapTeleporter createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i3 = parcel.readInt();
            char c2 = (char) i3;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i3);
            } else if (c2 == 2) {
                parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, i3, ParcelFileDescriptor.CREATOR);
            } else if (c2 != 3) {
                SafeParcelReader.skipUnknownField(parcel, i3);
            } else {
                i2 = SafeParcelReader.readInt(parcel, i3);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new BitmapTeleporter(i, parcelFileDescriptor, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BitmapTeleporter[] newArray(int i) {
        return new BitmapTeleporter[i];
    }
}
