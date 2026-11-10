package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "AuthAccountResultCreator")
public final class zab extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zab> CREATOR = new zaa();

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(getter = "getConnectionResultCode", id = 2)
    public int zasv;

    @Nullable
    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", id = 3)
    public Intent zasw;

    @SafeParcelable.Constructor
    public zab(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @Nullable @SafeParcelable.Param(id = 3) Intent intent) {
        this.versionCode = i;
        this.zasv = i2;
        this.zasw = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zasv == 0 ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        int i3 = this.zasv;
        parcel.writeInt(262146);
        parcel.writeInt(i3);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zasw, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public zab() {
        this(0, null);
    }

    public zab(int i, @Nullable Intent intent) {
        this(2, 0, null);
    }
}
