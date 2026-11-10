package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zad implements Parcelable.Creator<GoogleSignInOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        ArrayList arrayListCreateTypedList = null;
        Account account = null;
        String strCreateString = null;
        String strCreateString2 = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString3 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i2 = parcel.readInt();
            switch ((char) i2) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i2);
                    break;
                case 2:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, i2, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.createParcelable(parcel, i2, Account.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 5:
                    z2 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 6:
                    z3 = SafeParcelReader.readBoolean(parcel, i2);
                    break;
                case 7:
                    strCreateString = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\b':
                    strCreateString2 = SafeParcelReader.createString(parcel, i2);
                    break;
                case '\t':
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, i2, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case '\n':
                    strCreateString3 = SafeParcelReader.createString(parcel, i2);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, i2);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GoogleSignInOptions(i, (ArrayList<Scope>) arrayListCreateTypedList, account, z, z2, z3, strCreateString, strCreateString2, (ArrayList<GoogleSignInOptionsExtensionParcelable>) arrayListCreateTypedList2, strCreateString3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
