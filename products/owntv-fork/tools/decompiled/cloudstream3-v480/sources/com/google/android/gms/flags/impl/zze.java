package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
final class zze implements Callable<SharedPreferences> {
    final /* synthetic */ Context zza;

    zze(Context context) {
        this.zza = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ SharedPreferences call() throws Exception {
        return this.zza.getSharedPreferences("google_sdk_flags", 0);
    }
}
