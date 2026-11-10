package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zaa implements Parcelable.Creator<AuthAccountRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AuthAccountRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Integer integerObject = null;
        Integer integerObject2 = null;
        Account account = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel, i2);
                    break;
                case 3:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, i2, Scope.CREATOR);
                    break;
                case 4:
                    integerObject = SafeParcelReader.readIntegerObject(parcel, i2);
                    break;
                case 5:
                    integerObject2 = SafeParcelReader.readIntegerObject(parcel, i2);
                    break;
                case 6:
                    account = (Account) SafeParcelReader.createParcelable(parcel, i2, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new AuthAccountRequest(i, iBinder, scopeArr, integerObject, integerObject2, account);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ AuthAccountRequest[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
