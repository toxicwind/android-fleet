package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInButtonConfig> CREATOR = new zao();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(getter = "getScopes", id = 4)
    @Deprecated
    public final Scope[] zaoc;

    @SafeParcelable.Field(getter = "getButtonSize", id = 2)
    public final int zapr;

    @SafeParcelable.Field(getter = "getColorScheme", id = 3)
    public final int zaps;

    @SafeParcelable.Constructor
    public SignInButtonConfig(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) Scope[] scopeArr) {
        this.zali = i;
        this.zapr = i2;
        this.zaps = i3;
        this.zaoc = scopeArr;
    }

    public int getButtonSize() {
        return this.zapr;
    }

    public int getColorScheme() {
        return this.zaps;
    }

    @Deprecated
    public Scope[] getScopes() {
        return this.zaoc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        int buttonSize = getButtonSize();
        parcel.writeInt(262146);
        parcel.writeInt(buttonSize);
        int colorScheme = getColorScheme();
        parcel.writeInt(262147);
        parcel.writeInt(colorScheme);
        SafeParcelWriter.writeTypedArray(parcel, 4, getScopes(), i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public SignInButtonConfig(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
    }
}
