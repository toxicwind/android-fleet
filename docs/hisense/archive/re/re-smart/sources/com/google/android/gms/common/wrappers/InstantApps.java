package com.google.android.gms.common.wrappers;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class InstantApps {
    public static Context zza;

    @Nullable
    public static Boolean zzb;

    @KeepForSdk
    public static synchronized boolean isInstantApp(@NonNull Context context) {
        Boolean bool;
        Context applicationContext = context.getApplicationContext();
        Context context2 = zza;
        if (context2 != null && (bool = zzb) != null && context2 == applicationContext) {
            return bool.booleanValue();
        }
        zzb = null;
        int i = Build.VERSION.SDK_INT;
        zzb = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
        zza = applicationContext;
        return zzb.booleanValue();
    }
}
