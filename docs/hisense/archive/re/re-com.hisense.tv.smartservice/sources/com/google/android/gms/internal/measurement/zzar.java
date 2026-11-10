package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class zzar implements Iterator<zzap> {
    public final /* synthetic */ zzat zza;
    public int zzb = 0;

    public zzar(zzat zzatVar) {
        this.zza = zzatVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zza.zza.length();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        if (this.zzb >= this.zza.zza.length()) {
            throw new NoSuchElementException();
        }
        int i = this.zzb;
        this.zzb = i + 1;
        return new zzat(String.valueOf(i));
    }
}
