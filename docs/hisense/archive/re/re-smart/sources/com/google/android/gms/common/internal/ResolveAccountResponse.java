package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zan();

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(id = 2)
    public IBinder zapn;

    @SafeParcelable.Field(getter = "getConnectionResult", id = 3)
    public ConnectionResult zapo;

    @SafeParcelable.Field(getter = "getSaveDefaultAccount", id = 4)
    public boolean zapp;

    @SafeParcelable.Field(getter = "isFromCrossClientAuth", id = 5)
    public boolean zapq;

    @SafeParcelable.Constructor
    public ResolveAccountResponse(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2) {
        this.versionCode = i;
        this.zapn = iBinder;
        this.zapo = connectionResult;
        this.zapp = z;
        this.zapq = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.zapo.equals(resolveAccountResponse.zapo) && getAccountAccessor().equals(resolveAccountResponse.getAccountAccessor());
    }

    public IAccountAccessor getAccountAccessor() {
        return IAccountAccessor.Stub.asInterface(this.zapn);
    }

    public ConnectionResult getConnectionResult() {
        return this.zapo;
    }

    public boolean getSaveDefaultAccount() {
        return this.zapp;
    }

    public boolean isFromCrossClientAuth() {
        return this.zapq;
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor iAccountAccessor) {
        this.zapn = iAccountAccessor == null ? null : iAccountAccessor.asBinder();
        return this;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean z) {
        this.zapq = z;
        return this;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean z) {
        this.zapp = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zapn, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getConnectionResult(), i, false);
        boolean saveDefaultAccount = getSaveDefaultAccount();
        parcel.writeInt(262148);
        parcel.writeInt(saveDefaultAccount ? 1 : 0);
        boolean zIsFromCrossClientAuth = isFromCrossClientAuth();
        parcel.writeInt(262149);
        parcel.writeInt(zIsFromCrossClientAuth ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public ResolveAccountResponse(ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }

    public ResolveAccountResponse(int i) {
        this(new ConnectionResult(1, i, null, null));
    }
}
