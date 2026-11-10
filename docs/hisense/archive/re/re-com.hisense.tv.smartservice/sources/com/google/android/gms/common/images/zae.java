package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zae implements Parcelable.Creator<WebImage> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WebImage createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i4 = parcel.readInt();
            char c2 = (char) i4;
            if (c2 == 1) {
                i = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 == 2) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, i4, Uri.CREATOR);
            } else if (c2 == 3) {
                i2 = SafeParcelReader.readInt(parcel, i4);
            } else if (c2 != 4) {
                SafeParcelReader.skipUnknownField(parcel, i4);
            } else {
                i3 = SafeParcelReader.readInt(parcel, i4);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new WebImage(i, uri, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
