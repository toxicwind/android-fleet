package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzfj extends LruCache<String, com.google.android.gms.internal.measurement.zzc> {
    public final /* synthetic */ zzfm zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfj(zzfm zzfmVar, int i) {
        super(20);
        this.zza = zzfmVar;
    }

    @Override // androidx.collection.LruCache
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.measurement.zzc create(String str) {
        String str2 = str;
        Preconditions.checkNotEmpty(str2);
        return zzfm.zzd(this.zza, str2);
    }
}
