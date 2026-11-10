package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {

    @SafeParcelable.VersionField(id = 1000)
    public final int zzb;

    @SafeParcelable.Field(getter = "getStatusCode", id = 1)
    public final int zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getStatusMessage", id = 2)
    public final String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    public final PendingIntent zze;

    @Nullable
    @SafeParcelable.Field(getter = "getConnectionResult", id = 4)
    public final ConnectionResult zzf;

    @NonNull
    @VisibleForTesting
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS = new Status(0);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED = new Status(14);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_TIMEOUT = new Status(15);

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_CANCELED = new Status(16);

    @NonNull
    @ShowFirstParty
    public static final Status zza = new Status(17);

    @NonNull
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT = new Status(18);

    @NonNull
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    @KeepForSdk
    public Status(int i, int i2, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = str;
        this.zze = pendingIntent;
        this.zzf = null;
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public Status(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) int i2, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 4) ConnectionResult connectionResult) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = str;
        this.zze = pendingIntent;
        this.zzf = connectionResult;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.zzb == status.zzb && this.zzc == status.zzc && Objects.equal(this.zzd, status.zzd) && Objects.equal(this.zze, status.zze) && Objects.equal(this.zzf, status.zzf);
    }

    @Nullable
    public ConnectionResult getConnectionResult() {
        return this.zzf;
    }

    @Nullable
    public PendingIntent getResolution() {
        return this.zze;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    @KeepForSdk
    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zzc;
    }

    @Nullable
    public String getStatusMessage() {
        return this.zzd;
    }

    @VisibleForTesting
    public boolean hasResolution() {
        return this.zze != null;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, this.zze, this.zzf});
    }

    public boolean isCanceled() {
        return this.zzc == 16;
    }

    public boolean isInterrupted() {
        return this.zzc == 14;
    }

    public boolean isSuccess() {
        return this.zzc <= 0;
    }

    public void startResolutionForResult(@NonNull Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            PendingIntent pendingIntent = this.zze;
            Preconditions.checkNotNull(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    @NonNull
    public String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("statusCode", zza());
        stringHelper.add("resolution", this.zze);
        return stringHelper.toString();
    }

    @Override // android.os.Parcelable
    @KeepForSdk
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int statusCode = getStatusCode();
        parcel.writeInt(262145);
        parcel.writeInt(statusCode);
        SafeParcelWriter.writeString(parcel, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zze, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getConnectionResult(), i, false);
        int i2 = this.zzb;
        parcel.writeInt(263144);
        parcel.writeInt(i2);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @NonNull
    public final String zza() {
        String str = this.zzd;
        return str != null ? str : CommonStatusCodes.getStatusCodeString(this.zzc);
    }

    @KeepForSdk
    public Status(int i) {
        this(1, i, null, null);
    }

    @KeepForSdk
    public Status(int i, @Nullable String str) {
        this(1, i, str, null);
    }

    @KeepForSdk
    public Status(int i, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str) {
        this(connectionResult, str, 17);
    }

    @KeepForSdk
    @Deprecated
    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str, int i) {
        this(1, i, str, connectionResult.getResolution(), connectionResult);
    }
}
