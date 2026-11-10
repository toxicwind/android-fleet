package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMapPairCreator")
public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zal> CREATOR = new zak();

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(id = 2)
    public final String zarm;

    @SafeParcelable.Field(id = 3)
    public final FastJsonResponse.Field<?, ?> zarn;

    @SafeParcelable.Constructor
    public zal(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) FastJsonResponse.Field<?, ?> field) {
        this.versionCode = i;
        this.zarm = str;
        this.zarn = field;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, this.zarm, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zarn, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public zal(String str, FastJsonResponse.Field<?, ?> field) {
        this.versionCode = 1;
        this.zarm = str;
        this.zarn = field;
    }
}
