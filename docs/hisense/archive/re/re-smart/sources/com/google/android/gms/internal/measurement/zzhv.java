package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzhv implements zzhe {

    @GuardedBy("SharedPreferencesLoader.class")
    public static final Map<String, zzhv> zza = new ArrayMap();
    public final SharedPreferences zzb;
    public final SharedPreferences.OnSharedPreferenceChangeListener zzc;

    public static zzhv zza(Context context, String str) {
        zzgw.zza();
        throw null;
    }

    public static synchronized void zzc() {
        Iterator<zzhv> it = zza.values().iterator();
        if (it.hasNext()) {
            zzhv next = it.next();
            SharedPreferences sharedPreferences = next.zzb;
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = next.zzc;
            throw null;
        }
        zza.clear();
    }

    @Override // com.google.android.gms.internal.measurement.zzhe
    public final Object zzb(String str) {
        throw null;
    }
}
