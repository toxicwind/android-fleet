package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzab {
    public zzaa zza;
    public zzaa zzb;
    public final List<zzaa> zzc;

    public zzab() {
        this.zza = new zzaa("", 0L, null);
        this.zzb = new zzaa("", 0L, null);
        this.zzc = new ArrayList();
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        zzab zzabVar = new zzab(this.zza.clone());
        Iterator<zzaa> it = this.zzc.iterator();
        while (it.hasNext()) {
            zzabVar.zzc.add(it.next().clone());
        }
        return zzabVar;
    }

    public final zzaa zza() {
        return this.zza;
    }

    public final zzaa zzb() {
        return this.zzb;
    }

    public final List<zzaa> zzc() {
        return this.zzc;
    }

    public final void zzd(zzaa zzaaVar) {
        this.zza = zzaaVar;
        this.zzb = this.zza.clone();
        this.zzc.clear();
    }

    public final void zze(String str, long j, Map<String, Object> map) {
        this.zzc.add(new zzaa(str, j, map));
    }

    public final void zzf(zzaa zzaaVar) {
        this.zzb = zzaaVar;
    }

    public zzab(zzaa zzaaVar) {
        this.zza = zzaaVar;
        this.zzb = this.zza.clone();
        this.zzc = new ArrayList();
    }
}
