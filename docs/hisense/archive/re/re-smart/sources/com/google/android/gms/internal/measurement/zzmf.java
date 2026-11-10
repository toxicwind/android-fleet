package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zzmf implements Iterator<String> {
    public final Iterator<String> zza;
    public final /* synthetic */ zzmg zzb;

    public zzmf(zzmg zzmgVar) {
        this.zzb = zzmgVar;
        this.zza = this.zzb.zza.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
