package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<Feature> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String zza;

    @SafeParcelable.Field(getter = "getOldVersion", id = 2)
    @Deprecated
    public final int zzb;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getVersion", id = 3)
    public final long zzc;

    @SafeParcelable.Constructor
    public Feature(@NonNull @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) long j) {
        this.zza = str;
        this.zzb = i;
        this.zzc = j;
    }

    @KeepForSdk
    public Feature(@NonNull String str, long j) {
        this.zza = str;
        this.zzc = j;
        this.zzb = -1;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            if (((getName() != null && getName().equals(feature.getName())) || (getName() == null && feature.getName() == null)) && getVersion() == feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public String getName() {
        return this.zza;
    }

    @KeepForSdk
    public long getVersion() {
        long j = this.zzc;
        return j == -1 ? this.zzb : j;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getName(), Long.valueOf(getVersion())});
    }

    @NonNull
    public final String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("name", getName());
        stringHelper.add("version", Long.valueOf(getVersion()));
        return stringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        int i2 = this.zzb;
        parcel.writeInt(262146);
        parcel.writeInt(i2);
        long version = getVersion();
        parcel.writeInt(524291);
        parcel.writeLong(version);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }
}
