package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "RootTelemetryConfigurationCreator")
public class RootTelemetryConfiguration extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = new zzai();

    @SafeParcelable.Field(getter = "getVersion", id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getMethodInvocationTelemetryEnabled", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(getter = "getMethodTimingTelemetryEnabled", id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(getter = "getBatchPeriodMillis", id = 4)
    public final int zzd;

    @SafeParcelable.Field(getter = "getMaxMethodInvocationsInBatch", id = 5)
    public final int zze;

    @SafeParcelable.Constructor
    public RootTelemetryConfiguration(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) int i3) {
        this.zza = i;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = i2;
        this.zze = i3;
    }

    @KeepForSdk
    public int getBatchPeriodMillis() {
        return this.zzd;
    }

    @KeepForSdk
    public int getMaxMethodInvocationsInBatch() {
        return this.zze;
    }

    @KeepForSdk
    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    @KeepForSdk
    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @KeepForSdk
    public int getVersion() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int version = getVersion();
        parcel.writeInt(262145);
        parcel.writeInt(version);
        boolean methodInvocationTelemetryEnabled = getMethodInvocationTelemetryEnabled();
        parcel.writeInt(262146);
        parcel.writeInt(methodInvocationTelemetryEnabled ? 1 : 0);
        boolean methodTimingTelemetryEnabled = getMethodTimingTelemetryEnabled();
        parcel.writeInt(262147);
        parcel.writeInt(methodTimingTelemetryEnabled ? 1 : 0);
        int batchPeriodMillis = getBatchPeriodMillis();
        parcel.writeInt(262148);
        parcel.writeInt(batchPeriodMillis);
        int maxMethodInvocationsInBatch = getMaxMethodInvocationsInBatch();
        parcel.writeInt(262149);
        parcel.writeInt(maxMethodInvocationsInBatch);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }
}
