package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "GoogleSignInOptionsExtensionCreator")
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleSignInOptionsExtensionParcelable> CREATOR = new zaa();

    @SafeParcelable.Field(getter = "getBundle", id = 3)
    public Bundle mBundle;

    @SafeParcelable.Field(getter = "getType", id = 2)
    public int mType;

    @SafeParcelable.VersionField(id = 1)
    public final int versionCode;

    @SafeParcelable.Constructor
    public GoogleSignInOptionsExtensionParcelable(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) Bundle bundle) {
        this.versionCode = i;
        this.mType = i2;
        this.mBundle = bundle;
    }

    @KeepForSdk
    public int getType() {
        return this.mType;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.versionCode;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        int type = getType();
        parcel.writeInt(262146);
        parcel.writeInt(type);
        SafeParcelWriter.writeBundle(parcel, 3, this.mBundle, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public GoogleSignInOptionsExtensionParcelable(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, googleSignInOptionsExtension.getExtensionType(), googleSignInOptionsExtension.toBundle());
    }
}
