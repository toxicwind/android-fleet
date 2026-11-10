package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: loaded from: classes.dex */
public final class zzag {
    public static final Object zza = new Object();
    public static boolean zzb;

    @Nullable
    public static String zzc;
    public static int zzd;

    public static int zza(Context context) {
        zzc(context);
        return zzd;
    }

    @Nullable
    public static String zzb(Context context) {
        zzc(context);
        return zzc;
    }

    public static void zzc(Context context) {
        synchronized (zza) {
            if (zzb) {
                return;
            }
            zzb = true;
            try {
                Bundle bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle == null) {
                    return;
                }
                zzc = bundle.getString("com.google.app.id");
                zzd = bundle.getInt("com.google.android.gms.version");
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("MetadataValueReader", "This should never happen.", e2);
            }
        }
    }
}
