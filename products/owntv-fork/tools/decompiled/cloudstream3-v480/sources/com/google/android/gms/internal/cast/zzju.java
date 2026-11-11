package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzju implements Iterator {
    final /* synthetic */ zzjv zza;
    private int zzb = 0;

    zzju(zzjv zzjvVar) {
        this.zza = zzjvVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb;
        zzjv zzjvVar = this.zza;
        return i < zzjvVar.zza() - zzjvVar.zzb();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.zzb;
        zzjv zzjvVar = this.zza;
        if (i >= zzjvVar.zza() - zzjvVar.zzb()) {
            throw new NoSuchElementException();
        }
        Object obj = zzjvVar.zzb.zzb[zzjvVar.zzb() + i];
        this.zzb = i + 1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
