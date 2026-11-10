package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "SignInResponseCreator")
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zaj();

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(getter = "getConnectionResult", id = 2)
    public final ConnectionResult zapo;

    @Nullable
    @SafeParcelable.Field(getter = "getResolveAccountResponse", id = 3)
    public final ResolveAccountResponse zata;

    @SafeParcelable.Constructor
    public zak(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ConnectionResult connectionResult, @Nullable @SafeParcelable.Param(id = 3) ResolveAccountResponse resolveAccountResponse) {
        this.versionCode = i;
        this.zapo = connectionResult;
        this.zata = resolveAccountResponse;
    }

    public final ConnectionResult getConnectionResult() {
        return this.zapo;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zapo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zata, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Nullable
    public final ResolveAccountResponse zacv() {
        return this.zata;
    }

    public zak(int i) {
        this(1, new ConnectionResult(8, null), null);
    }

    public zak(ConnectionResult connectionResult, @Nullable ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, null);
    }
}
