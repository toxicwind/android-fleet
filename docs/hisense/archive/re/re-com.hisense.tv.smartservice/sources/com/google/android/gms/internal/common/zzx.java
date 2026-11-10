package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzx {
    public final zzo zza;
    public final boolean zzb;
    public final zzu zzc;

    public zzx(zzu zzuVar, boolean z, zzo zzoVar, int i, byte[] bArr) {
        this.zzc = zzuVar;
        this.zzb = z;
        this.zza = zzoVar;
    }

    public static zzx zzc(zzo zzoVar) {
        return new zzx(new zzu(zzoVar), false, zzn.zza, Integer.MAX_VALUE, null);
    }

    public static /* synthetic */ Iterator zze(zzx zzxVar, CharSequence charSequence) {
        return new zzt(zzxVar.zzc, zzxVar, charSequence);
    }

    private final Iterator<String> zzh(CharSequence charSequence) {
        return new zzt(this.zzc, this, charSequence);
    }

    public final zzx zzb() {
        return new zzx(this.zzc, true, this.zza, Integer.MAX_VALUE, null);
    }

    public final Iterable<String> zzd(CharSequence charSequence) {
        return new zzv(this, charSequence);
    }

    public final List<String> zzf(CharSequence charSequence) {
        if (charSequence == null) {
            throw null;
        }
        zzt zztVar = new zzt(this.zzc, this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zztVar.hasNext()) {
            arrayList.add(zztVar.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
