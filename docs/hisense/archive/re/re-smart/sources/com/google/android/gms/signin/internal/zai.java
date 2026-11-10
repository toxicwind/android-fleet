package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "SignInRequestCreator")
public final class zai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zai> CREATOR = new zah();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(getter = "getResolveAccountRequest", id = 2)
    public final ResolveAccountRequest zasz;

    @SafeParcelable.Constructor
    public zai(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ResolveAccountRequest resolveAccountRequest) {
        this.zali = i;
        this.zasz = resolveAccountRequest;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zasz, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public zai(ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
}
