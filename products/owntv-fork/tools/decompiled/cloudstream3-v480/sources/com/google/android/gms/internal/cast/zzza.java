package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzza implements Iterator {
    final /* synthetic */ zzzd zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* synthetic */ zzza(zzzd zzzdVar, zzzc zzzcVar) {
        this.zza = zzzdVar;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzzd zzzdVar = this.zza;
        if (i >= zzzdVar.zzb) {
            return !zzzdVar.zzc.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzzd zzzdVar = this.zza;
        return i < zzzdVar.zzb ? (zzyz) zzzdVar.zza[i] : (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        zzzd zzzdVar = this.zza;
        zzzdVar.zzo();
        int i = this.zzb;
        if (i >= zzzdVar.zzb) {
            zza().remove();
        } else {
            this.zzb = i - 1;
            zzzdVar.zzm(i);
        }
    }
}
