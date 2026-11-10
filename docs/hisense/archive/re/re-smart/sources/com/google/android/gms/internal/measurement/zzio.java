package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class zzio extends zzir {
    public final /* synthetic */ zzix zza;
    public int zzb = 0;
    public final int zzc;

    public zzio(zzix zzixVar) {
        this.zza = zzixVar;
        this.zzc = this.zza.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzit
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
