package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
public final class zzar extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();

    @SafeParcelable.Field(getter = "z", id = 2)
    public final Bundle zza;

    @SafeParcelable.Constructor
    public zzar(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zza = bundle;
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzaq(this);
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzc(), false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zza.size();
    }

    public final Bundle zzc() {
        return new Bundle(this.zza);
    }

    public final Double zzd(String str) {
        return Double.valueOf(this.zza.getDouble("value"));
    }

    public final Long zze(String str) {
        return Long.valueOf(this.zza.getLong("value"));
    }

    public final Object zzf(String str) {
        return this.zza.get(str);
    }

    public final String zzg(String str) {
        return this.zza.getString(str);
    }
}
