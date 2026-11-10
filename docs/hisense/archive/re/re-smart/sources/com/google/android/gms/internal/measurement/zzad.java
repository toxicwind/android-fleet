package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class zzad implements Iterator<zzap> {
    public final /* synthetic */ zzae zza;
    public int zzb = 0;

    public zzad(zzae zzaeVar) {
        this.zza = zzaeVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zza.zzc();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        if (this.zzb >= this.zza.zzc()) {
            throw new NoSuchElementException(a.a(32, "Out of bounds index: ", this.zzb));
        }
        zzae zzaeVar = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        return zzaeVar.zze(i);
    }
}
