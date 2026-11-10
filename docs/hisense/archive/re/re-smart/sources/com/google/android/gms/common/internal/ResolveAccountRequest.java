package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new zam();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(getter = "getSessionId", id = 3)
    public final int zapl;

    @SafeParcelable.Field(getter = "getSignInAccountHint", id = 4)
    public final GoogleSignInAccount zapm;

    @SafeParcelable.Field(getter = "getAccount", id = 2)
    public final Account zax;

    @SafeParcelable.Constructor
    public ResolveAccountRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Account account, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) GoogleSignInAccount googleSignInAccount) {
        this.zali = i;
        this.zax = account;
        this.zapl = i2;
        this.zapm = googleSignInAccount;
    }

    public Account getAccount() {
        return this.zax;
    }

    public int getSessionId() {
        return this.zapl;
    }

    @Nullable
    public GoogleSignInAccount getSignInAccountHint() {
        return this.zapm;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, getAccount(), i, false);
        int sessionId = getSessionId();
        parcel.writeInt(262147);
        parcel.writeInt(sessionId);
        SafeParcelWriter.writeParcelable(parcel, 4, getSignInAccountHint(), i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }
}
