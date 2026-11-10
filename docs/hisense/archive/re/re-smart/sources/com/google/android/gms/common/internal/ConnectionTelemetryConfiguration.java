package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "ConnectionTelemetryConfigurationCreator")
public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<ConnectionTelemetryConfiguration> CREATOR = new zzl();

    @SafeParcelable.Field(getter = "getRootTelemetryConfiguration", id = 1)
    public final RootTelemetryConfiguration zza;

    @SafeParcelable.Field(getter = "getMethodInvocationTelemetryEnabled", id = 2)
    public final boolean zzb;

    @SafeParcelable.Field(getter = "getMethodTimingTelemetryEnabled", id = 3)
    public final boolean zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getMethodInvocationMethodKeyAllowlist", id = 4)
    public final int[] zzd;

    @SafeParcelable.Field(getter = "getMaxMethodInvocationsLogged", id = 5)
    public final int zze;

    @Nullable
    @SafeParcelable.Field(getter = "getMethodInvocationMethodKeyDisallowlist", id = 6)
    public final int[] zzf;

    @SafeParcelable.Constructor
    public ConnectionTelemetryConfiguration(@NonNull @SafeParcelable.Param(id = 1) RootTelemetryConfiguration rootTelemetryConfiguration, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @Nullable @SafeParcelable.Param(id = 4) int[] iArr, @SafeParcelable.Param(id = 5) int i, @Nullable @SafeParcelable.Param(id = 6) int[] iArr2) {
        this.zza = rootTelemetryConfiguration;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = iArr;
        this.zze = i;
        this.zzf = iArr2;
    }

    @KeepForSdk
    public int getMaxMethodInvocationsLogged() {
        return this.zze;
    }

    @Nullable
    @KeepForSdk
    public int[] getMethodInvocationMethodKeyAllowlist() {
        return this.zzd;
    }

    @Nullable
    @KeepForSdk
    public int[] getMethodInvocationMethodKeyDisallowlist() {
        return this.zzf;
    }

    @KeepForSdk
    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    @KeepForSdk
    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        boolean methodInvocationTelemetryEnabled = getMethodInvocationTelemetryEnabled();
        parcel.writeInt(262146);
        parcel.writeInt(methodInvocationTelemetryEnabled ? 1 : 0);
        boolean methodTimingTelemetryEnabled = getMethodTimingTelemetryEnabled();
        parcel.writeInt(262147);
        parcel.writeInt(methodTimingTelemetryEnabled ? 1 : 0);
        SafeParcelWriter.writeIntArray(parcel, 4, getMethodInvocationMethodKeyAllowlist(), false);
        int maxMethodInvocationsLogged = getMaxMethodInvocationsLogged();
        parcel.writeInt(262149);
        parcel.writeInt(maxMethodInvocationsLogged);
        SafeParcelWriter.writeIntArray(parcel, 6, getMethodInvocationMethodKeyDisallowlist(), false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final RootTelemetryConfiguration zza() {
        return this.zza;
    }
}
