package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zza implements Parcelable.Creator<WakeLockEvent> {
    @Override // android.os.Parcelable.Creator
    public final WakeLockEvent createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        String strCreateString = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            switch ((char) i5) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, i5);
                    break;
                case 3:
                case 7:
                case '\t':
                default:
                    SafeParcelReader.skipUnknownField(parcel, i5);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, i5);
                    break;
                case 5:
                    i3 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 6:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, i5);
                    break;
                case '\b':
                    j2 = SafeParcelReader.readLong(parcel, i5);
                    break;
                case '\n':
                    strCreateString3 = SafeParcelReader.createString(parcel, i5);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case '\f':
                    strCreateString2 = SafeParcelReader.createString(parcel, i5);
                    break;
                case '\r':
                    strCreateString4 = SafeParcelReader.createString(parcel, i5);
                    break;
                case 14:
                    i4 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 15:
                    f2 = SafeParcelReader.readFloat(parcel, i5);
                    break;
                case 16:
                    j3 = SafeParcelReader.readLong(parcel, i5);
                    break;
                case 17:
                    strCreateString5 = SafeParcelReader.createString(parcel, i5);
                    break;
                case 18:
                    z = SafeParcelReader.readBoolean(parcel, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new WakeLockEvent(i, j, i2, strCreateString, i3, arrayListCreateStringList, strCreateString2, j2, i4, strCreateString3, strCreateString4, f2, j3, strCreateString5, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WakeLockEvent[] newArray(int i) {
        return new WakeLockEvent[i];
    }
}
