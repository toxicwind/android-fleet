package com.google.android.gms.internal.cast;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzin {
    private static final zziq zza = new zzil();
    private static final zzip zzb = new zzim();
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();

    /* synthetic */ zzin(zziq zziqVar, zzir zzirVar) {
    }

    public final zzis zza() {
        return new zzio(this, null);
    }

    final void zzd(zzie zzieVar) {
        zzka.zza(zzieVar, "key");
        if (!zzieVar.zzb()) {
            zziq zziqVar = zza;
            zzka.zza(zzieVar, "key");
            this.zzd.remove(zzieVar);
            this.zzc.put(zzieVar, zziqVar);
            return;
        }
        zzip zzipVar = zzb;
        zzka.zza(zzieVar, "key");
        if (!zzieVar.zzb()) {
            throw new IllegalArgumentException("key must be repeating");
        }
        this.zzc.remove(zzieVar);
        this.zzd.put(zzieVar, zzipVar);
    }
}
