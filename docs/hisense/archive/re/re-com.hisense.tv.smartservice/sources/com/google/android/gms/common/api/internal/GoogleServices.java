package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.FirebaseOptions;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@Deprecated
public final class GoogleServices {
    public static final Object zza = new Object();

    @Nullable
    public static GoogleServices zzb;

    @Nullable
    public final String zzc;
    public final Status zzd;
    public final boolean zze;
    public final boolean zzf;

    @VisibleForTesting
    @KeepForSdk
    public GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z = true;
        if (identifier != 0) {
            int integer = resources.getInteger(identifier);
            boolean z2 = integer == 0;
            z = integer != 0;
            this.zzf = z2;
        } else {
            this.zzf = false;
        }
        this.zze = z;
        zzag.zzc(context);
        String string = zzag.zzc;
        if (string == null) {
            Preconditions.checkNotNull(context);
            Resources resources2 = context.getResources();
            int identifier2 = resources2.getIdentifier(FirebaseOptions.APP_ID_RESOURCE_NAME, "string", resources2.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
            string = identifier2 == 0 ? null : resources2.getString(identifier2);
        }
        if (TextUtils.isEmpty(string)) {
            this.zzd = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzc = null;
        } else {
            this.zzc = string;
            this.zzd = Status.RESULT_SUCCESS;
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public GoogleServices(String str, boolean z) {
        this.zzc = str;
        this.zzd = Status.RESULT_SUCCESS;
        this.zze = z;
        this.zzf = !z;
    }

    @KeepForSdk
    public static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (zza) {
            googleServices = zzb;
            if (googleServices == null) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Initialize must be called before ");
                sb.append(str);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
        }
        return googleServices;
    }

    @VisibleForTesting
    @KeepForSdk
    public static void clearInstanceForTest() {
        synchronized (zza) {
            zzb = null;
        }
    }

    @Nullable
    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzc;
    }

    @NonNull
    @KeepForSdk
    public static Status initialize(@NonNull Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (zza) {
            if (zzb == null) {
                zzb = new GoogleServices(context);
            }
            status = zzb.zzd;
        }
        return status;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices googleServicesCheckInitialized = checkInitialized("isMeasurementEnabled");
        return googleServicesCheckInitialized.zzd.isSuccess() && googleServicesCheckInitialized.zze;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzf;
    }

    @VisibleForTesting
    @KeepForSdk
    public Status checkGoogleAppId(String str) {
        String str2 = this.zzc;
        if (str2 == null || str2.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str3 = this.zzc;
        return new Status(10, a.a(new StringBuilder(String.valueOf(str3).length() + 97), "Initialize was called with two different Google App IDs.  Only the first app ID will be used: '", str3, "'."));
    }

    @NonNull
    @KeepForSdk
    public static Status initialize(@NonNull Context context, @NonNull String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (zza) {
            GoogleServices googleServices = zzb;
            if (googleServices != null) {
                return googleServices.checkGoogleAppId(str);
            }
            zzb = new GoogleServices(str, z);
            return zzb.zzd;
        }
    }
}
