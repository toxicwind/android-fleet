package com.google.android.gms.internal.stats;

import androidx.annotation.Nullable;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class zzb implements Closeable {
    public static final zzb zza = new zzb(false, null);

    public zzb(boolean z, @Nullable zzd zzdVar) {
    }

    public static zzb zza(boolean z, @Nullable zzc zzcVar) {
        return zza;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
