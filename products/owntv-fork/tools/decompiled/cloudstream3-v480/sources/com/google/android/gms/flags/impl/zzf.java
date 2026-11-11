package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzf {
    private static SharedPreferences zza;

    public static SharedPreferences zza(Context context) throws Exception {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (zza == null) {
                zza = (SharedPreferences) com.google.android.gms.internal.flags.zzd.zza(new zze(context));
            }
            sharedPreferences = zza;
        }
        return sharedPreferences;
    }
}
