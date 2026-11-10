package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<FavaDiagnosticsEntity> CREATOR = new zaa();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(id = 2)
    public final String zapx;

    @SafeParcelable.Field(id = 3)
    public final int zapy;

    @SafeParcelable.Constructor
    public FavaDiagnosticsEntity(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
        this.zali = i;
        this.zapx = str;
        this.zapy = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, this.zapx, false);
        int i3 = this.zapy;
        parcel.writeInt(262147);
        parcel.writeInt(i3);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @KeepForSdk
    public FavaDiagnosticsEntity(String str, int i) {
        this.zali = 1;
        this.zapx = str;
        this.zapy = i;
    }
}
