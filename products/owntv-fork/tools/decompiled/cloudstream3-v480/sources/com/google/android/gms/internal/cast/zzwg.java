package com.google.android.gms.internal.cast;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzwg extends zzwh {
    final /* synthetic */ zzwn zza;
    private int zzb = 0;
    private final int zzc;

    zzwg(zzwn zzwnVar) {
        this.zza = zzwnVar;
        this.zzc = zzwnVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzwj
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
