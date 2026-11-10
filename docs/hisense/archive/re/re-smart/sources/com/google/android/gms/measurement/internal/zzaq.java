package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zzaq implements Iterator<String> {
    public final Iterator<String> zza;
    public final /* synthetic */ zzar zzb;

    public zzaq(zzar zzarVar) {
        this.zzb = zzarVar;
        this.zza = this.zzb.zza.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final String next() {
        return this.zza.next();
    }
}
